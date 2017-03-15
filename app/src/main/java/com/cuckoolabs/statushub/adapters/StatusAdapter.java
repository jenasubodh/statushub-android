package com.cuckoolabs.statushub.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cuckoolabs.statushub.R;
import com.cuckoolabs.statushub.models.Post;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by insjena021 on 3/15/2017.
 */

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.MyViewHolder> {

    private List<Post> postList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtName) TextView name;
        @BindView(R.id.txtStatus) TextView status;
        @BindView(R.id.txtDateTime) TextView dateTime;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public StatusAdapter(List<Post> postList) {
        this.postList = postList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_post_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Post post = postList.get(position);

        holder.name.setText(post.getUser().getName());
        holder.status.setText(post.getMessage());
        holder.dateTime.setText(post.getCreatedAt());
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }
}