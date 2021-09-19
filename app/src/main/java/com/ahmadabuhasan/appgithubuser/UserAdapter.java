package com.ahmadabuhasan.appgithubuser;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.CardViewHolder> {

    private final ArrayList<User> listUser;

    public UserAdapter(ArrayList<User> list) {
        this.listUser = list;
    }

    @NonNull
    @Override
    public UserAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.CardViewHolder holder, int position) {
        User user = listUser.get(position);
        Glide.with(holder.itemView.getContext())
                .load(user.getAvatar())
                .apply(new RequestOptions().override(350, 350))
                .into(holder.img_avatar);
        holder.tv_name.setText(user.name);
        holder.tv_username.setText(user.username);
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView img_avatar;
        TextView tv_name;
        TextView tv_username;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            img_avatar = itemView.findViewById(R.id.img_avatar);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_username = itemView.findViewById(R.id.tv_username);
        }
    }
}