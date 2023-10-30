package com.example.uts;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MoveWithDataActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_with_data);

        // Mendapatkan data yang dikirim melalui Intent
        Intent intent = getIntent();
        if (intent != null) {
            String namaMahasiswa = intent.getStringExtra("NAMA_MAHASISWA");
            String nimMahasiswa = intent.getStringExtra("NIM_MAHASISWA");
            String alamatMahasiswa = intent.getStringExtra("ALAMAT_MAHASISWA");
            String emailMahasiswa = intent.getStringExtra("EMAIL_MAHASISWA");
            int fotoMahasiswa = intent.getIntExtra("FOTO_MAHASISWA", R.drawable.default_image);

            TextView tvName = findViewById(R.id.tv_item_name);
            TextView tvNIM = findViewById(R.id.tv_item_nim);
            TextView tvAlamat = findViewById(R.id.tv_item_alamat);
            TextView tvEmail = findViewById(R.id.tv_item_email);
            ImageView ivPhoto = findViewById(R.id.img_item_photo);

            tvName.setText(namaMahasiswa);
            tvNIM.setText(nimMahasiswa);
            tvAlamat.setText(alamatMahasiswa);
            tvEmail.setText(emailMahasiswa);
            ivPhoto.setImageResource(fotoMahasiswa);

            // Center the text in TextViews
            tvName.setGravity(Gravity.CENTER);
            tvNIM.setGravity(Gravity.CENTER);
            tvAlamat.setGravity(Gravity.CENTER);
            tvEmail.setGravity(Gravity.CENTER);
        }
    }
}