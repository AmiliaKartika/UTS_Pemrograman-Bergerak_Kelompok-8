package com.example.uts;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListMahasiswaAdapter extends RecyclerView.Adapter<ListMahasiswaAdapter.ListViewHolder>{

    private ArrayList<Mahasiswa> listMahasiswa;
    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }
    public ListMahasiswaAdapter(ArrayList<Mahasiswa> list) {
        this.listMahasiswa = list;
    }
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_mahasiswa, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Mahasiswa mahasiswa = listMahasiswa.get(position);
        holder.imgPhoto.setImageResource(mahasiswa.getPhoto());
        holder.tvName.setText(mahasiswa.getName());
        holder.tvNim.setText(mahasiswa.getNIM());
        holder.tvAlamat.setText(mahasiswa.getAlamat());
        holder.tvEmail.setText(mahasiswa.getEmail());

        holder.itemView.setOnClickListener(v -> onItemClickCallback.onItemClicked(listMahasiswa.get(holder.getAdapterPosition())));
    }

    public interface OnItemClickCallback {
        void onItemClicked(Mahasiswa data);
    }

    @Override
    public int getItemCount() {
        return listMahasiswa.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvNim, tvAlamat, tvEmail;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvNim = itemView.findViewById(R.id.tv_item_nim);
            tvAlamat = itemView.findViewById(R.id.tv_item_alamat);
            tvEmail = itemView.findViewById(R.id.tv_item_email);
        }
    }
}
