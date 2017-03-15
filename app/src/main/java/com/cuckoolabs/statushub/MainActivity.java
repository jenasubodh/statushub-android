package com.cuckoolabs.statushub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cuckoolabs.statushub.adapters.StatusAdapter;
import com.cuckoolabs.statushub.models.Post;
import com.cuckoolabs.statushub.models.User;
import com.cuckoolabs.statushub.models.ui.UIPost;
import com.cuckoolabs.statushub.services.ServiceGenerator;
import com.cuckoolabs.statushub.services.interfaces.IPostService;
import com.cuckoolabs.statushub.utilities.SharedPreferenceHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private List<Post> postList = new ArrayList<>();
    private RecyclerView recyclerView;
    private StatusAdapter mAdapter;

    private Socket mSocket;

    @BindView(R.id.txtStatus) EditText _statusText;
    @BindView(R.id.btnShare) Button _shareButton;
    @BindView(R.id.recycler_view) RecyclerView _recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind Butterknife
        ButterKnife.bind(this);


        if(!isLoggedIn()) {
            startLogin();
        }

        _shareButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String statusMessage = _statusText.getText().toString();

                if(statusMessage != null && !statusMessage.isEmpty()) {
                    shareStatus(statusMessage);
                }
            }
        });

        try {
            mSocket = IO.socket("https://statushub-dev.herokuapp.com/");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        mSocket.on(Socket.EVENT_CONNECT,onConnect);
        mSocket.on(Socket.EVENT_DISCONNECT,onDisconnect);
        mSocket.on("status",onStatusRecieved);

        mSocket.connect();

        // Recycler View Config
        mAdapter = new StatusAdapter(postList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        _recyclerView.setLayoutManager(mLayoutManager);
        _recyclerView.setItemAnimator(new DefaultItemAnimator());
        _recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mSocket.disconnect();

        mSocket.off(Socket.EVENT_CONNECT, onConnect);
        mSocket.off(Socket.EVENT_DISCONNECT, onDisconnect);
        mSocket.off("status",onStatusRecieved);
    }

    private void startLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean isLoggedIn(){
        String authKey = SharedPreferenceHelper.getSharedPreferenceString(getApplicationContext(), "authKey", null);

        if(authKey == null){
            return false;
        }
        return true;
    }

    private void shareStatus(String statusMessage) {

        String authKey = SharedPreferenceHelper.getSharedPreferenceString(getApplicationContext(), "authKey", null);
        if(authKey != null) {

            UIPost post = new UIPost(authKey, statusMessage);

            IPostService postService = ServiceGenerator.createService(IPostService.class);
            Call<Post> call = postService.createPost(post);
            call.enqueue(new Callback<Post>() {

                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    if (response.isSuccessful()) {
                        Log.d("Success ", response.message());
                    } else {
                        Log.d("Error ", response.message());
                    }
                }
                @Override
                public void onFailure(Call<Post> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                }
            });

        }
    }

    // On Socket Connected
    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "User Connected !!", Toast.LENGTH_LONG).show();
                }
            });
        }
    };

    // On Socket Disconnected
    private Emitter.Listener onDisconnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "User Disconnected !!", Toast.LENGTH_LONG).show();
                }
            });
        }
    };

    // On New Message Recieved
    private Emitter.Listener onStatusRecieved = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    JSONObject data = (JSONObject) args[0];

                    try {
                        User user = new User(data.getJSONObject("user").getString("picture"), data.getJSONObject("user").getString("id"), data.getJSONObject("user").getString("name"));
                        Post post = new Post(data.getString("updatedAt"), data.getString("message"), data.getString("id"), data.getString("createdAt"), user);

                        postList.add(post);
                        Collections.reverse(postList);
                        mAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        Log.e(TAG, e.getMessage());
                        return;
                    }
                }
            });
        }
    };
}
