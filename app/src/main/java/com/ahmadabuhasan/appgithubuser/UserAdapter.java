package com.ahmadabuhasan.appgithubuser;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmadabuhasan.appgithubuser.databinding.ItemUserBinding;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.CardViewHolder> implements Filterable {

    ArrayList<User> listUser, dataFilter;
    SearchFilter searchFilter;

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public UserAdapter(ArrayList<User> list) {
        this.listUser = list;
        this.dataFilter = list;
    }

    @NonNull
    @Override
    public UserAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUserBinding binding = ItemUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CardViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.CardViewHolder holder, int position) {
        User user = listUser.get(position);
        Glide.with(holder.itemView.getContext())
                .load(user.getAvatar())
                .apply(new RequestOptions().override(350, 350))
                .into(holder.binding.imgAvatar);
        holder.binding.tvName.setText(user.getName());
        holder.binding.tvUsername.setText(String.format("@%s", user.getUsername()));

        holder.itemView.setOnClickListener(v -> onItemClickCallback.onItemClicked(listUser.get(position)));
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    @Override
    public Filter getFilter() {
        if (searchFilter == null) {
            searchFilter = new SearchFilter(dataFilter, this);
        }
        return searchFilter;
    }

    /*public void clear() {
        listUser.clear();
        notifyDataSetChanged();
    }*/

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        ItemUserBinding binding;

        public CardViewHolder(@NonNull ItemUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(User data);
    }
}