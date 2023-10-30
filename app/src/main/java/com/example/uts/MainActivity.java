package com.example.uts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView rv_Mahasiswa;
    private ArrayList<Mahasiswa> listMahasiswa = new ArrayList<>(); // Inisialisasi ArrayList

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_Mahasiswa = findViewById(R.id.rv_Mahasiswa);
        rv_Mahasiswa.setHasFixedSize(true);

        listMahasiswa.addAll(getListMahasiswa());

        showRecyclerList();


    }

    public ArrayList<Mahasiswa> getListMahasiswa() {
        String[] dataName = getResources().getStringArray(R.array.data_name);
        String[] dataNIM = getResources().getStringArray(R.array.data_nim);
        TypedArray dataPhoto = getResources().obtainTypedArray(R.array.data_photo);
        String[] dataAlamat = getResources().getStringArray(R.array.data_alamat);
        String[] dataEmail = getResources().getStringArray(R.array.data_email);
        ArrayList<Mahasiswa> listMahasiswa = new ArrayList<>();
        for (int i = 0; i < dataName.length; i++) {
            Mahasiswa mahasiswa = new Mahasiswa();
            mahasiswa.setName(dataName[i]);
            mahasiswa.setNIM(dataNIM[i]);
            mahasiswa.setAlamat(dataAlamat[i]);
            mahasiswa.setEmail(dataEmail[i]);
            mahasiswa.setPhoto(dataPhoto.getResourceId(i, -1));
            listMahasiswa.add(mahasiswa);
        }
        dataPhoto.recycle();
        return listMahasiswa;
    }

    private void showRecyclerList() {
        rv_Mahasiswa.setLayoutManager(new LinearLayoutManager(this));
        ListMahasiswaAdapter listMahasiswaAdapter = new ListMahasiswaAdapter(listMahasiswa);
        rv_Mahasiswa.setAdapter(listMahasiswaAdapter);

        listMahasiswaAdapter.setOnItemClickCallback(data -> showSelectedMahasiswa(data));
    }

    private void showSelectedMahasiswa(Mahasiswa mahasiswa) {
        Toast.makeText(this, "Kamu memilih " + mahasiswa.getName(), Toast.LENGTH_SHORT).show();
        //intent
        Intent intent = new Intent(this, MoveWithDataActivity.class);
        intent.putExtra("NAMA_MAHASISWA", mahasiswa.getName());
        intent.putExtra("NIM_MAHASISWA", mahasiswa.getNIM());
        intent.putExtra("ALAMAT_MAHASISWA", mahasiswa.getAlamat());
        intent.putExtra("EMAIL_MAHASISWA", mahasiswa.getEmail());
        intent.putExtra("FOTO_MAHASISWA", mahasiswa.getPhoto());
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

    }
}