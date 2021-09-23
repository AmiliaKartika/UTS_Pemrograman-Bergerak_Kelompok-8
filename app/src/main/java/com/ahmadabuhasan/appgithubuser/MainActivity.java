package com.ahmadabuhasan.appgithubuser;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv_github;
    private final ArrayList<User> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_github = findViewById(R.id.rv_github);
        rv_github.setHasFixedSize(true);

        list.addAll(getUserGithub());
        showRecyclerUser();
    }

    private ArrayList<User> getUserGithub() {

    }

    private void showRecyclerUser() {
        rv_github.setLayoutManager(new LinearLayoutManager(this));
        UserAdapter userAdapter = new UserAdapter(list);
        rv_github.setAdapter(userAdapter);

        userAdapter.setOnItemClickCallback(this::showSelectedUser);
    }

    private void showSelectedUser(User user) {
        Toast.makeText(this, "You choose " + user.getName(), Toast.LENGTH_SHORT).show();

        Intent i = new Intent(MainActivity.this, UserDetailActivity.class);
        i.putExtra(UserDetailActivity.EXTRA_USER, user);
        startActivity(i);
    }
}