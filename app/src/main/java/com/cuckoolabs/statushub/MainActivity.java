package com.cuckoolabs.statushub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import com.cuckoolabs.statushub.adapters.StatusAdapter;
import com.cuckoolabs.statushub.models.Post;
import com.cuckoolabs.statushub.models.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private List<Post> postList = new ArrayList<>();
    private RecyclerView recyclerView;
    private StatusAdapter mAdapter;

    @BindView(R.id.txtStatus) EditText _statusText;
    @BindView(R.id.btnShare) Button _shareButton;
    @BindView(R.id.recycler_view) RecyclerView _recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind Butterknife
        ButterKnife.bind(this);

        // Recycler View Config
        mAdapter = new StatusAdapter(postList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        _recyclerView.setLayoutManager(mLayoutManager);
        _recyclerView.setItemAnimator(new DefaultItemAnimator());
        _recyclerView.setAdapter(mAdapter);

        prepareMPostData();
    }

    private void prepareMPostData() {

        User user = new User("Pic 1", "1", "Subodh Jena");

        Post newPost = new Post("23 March 2017", "Sample Message 1", "1", "23 March 2017", user);
        postList.add(newPost);

        Post newPost2 = new Post("23 March 2017", "Sample Message 2", "2", "23 March 2017", user);
        postList.add(newPost2);

        Post newPost3 = new Post("23 March 2017", "Sample Message 3", "3", "23 March 2017", user);
        postList.add(newPost3);

        mAdapter.notifyDataSetChanged();
    }
}
