package com.palringo.candidate.chat;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.palringo.candidate.R;

import java.util.ArrayList;
import java.util.List;


class ChatRecyclerAdapter extends RecyclerView.Adapter<ChatRecyclerAdapter.ViewHolder> {

    private static final String TAG = "ChatRecyclerAdapter";
    private final List<ChatItem> messages = new ArrayList<>();
    private final View emptyStateView;

    ChatRecyclerAdapter(List<ChatItem> items, View emptyStateView) {
        this.emptyStateView = emptyStateView;
        if(items != null)
            messages.addAll(items);
        checkAndShowEmptyState();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ChatItem chatItem = messages.get(position);
        Glide.with(holder.mItem.getContext())
                .load(chatItem.getAvatarUrl())
                .into(holder.mAvatarImageView);
        holder.mMessageTextView.setText(chatItem.getMessage());
        Log.d(TAG, "onBindViewHolder: message:"+chatItem.getMessage() +" avatar: " + chatItem.getAvatarUrl());

        holder.mItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "item clicked: "
                        + ((ViewHolder)v.getParent()).getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View mItem;
        ImageView mAvatarImageView;
        TextView mMessageTextView;

        ViewHolder(View view) {
            super(view);
            mItem = view;
            mAvatarImageView = view.findViewById(R.id.chatAvatar);
            mMessageTextView = view.findViewById(R.id.chatMessage);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + mMessageTextView.getText() + "'";
        }
    }

    void addMessage(String avatarUrl, String message) {
        messages.add(new ChatItem(avatarUrl, message));
        checkAndShowEmptyState();
        notifyDataSetChanged();

    }

    private void checkAndShowEmptyState() {
        if(emptyStateView != null)
            emptyStateView.setVisibility(messages.size() != 0 ? View.GONE : View.VISIBLE);
    }
}
