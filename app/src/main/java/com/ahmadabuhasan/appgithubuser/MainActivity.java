package com.ahmadabuhasan.appgithubuser;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Bundle;

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

    public ArrayList<User> getUserGithub() {
        @SuppressLint("Recycle") TypedArray dataAvatar = getResources().obtainTypedArray(R.array.avatar);
        String[] dataName = getResources().getStringArray(R.array.name);
        String[] dataUsername = getResources().getStringArray(R.array.username);

        ArrayList<User> listUser = new ArrayList<>();
        for (int i = 0; i < dataName.length; i++) {
            User user = new User();
            user.setAvatar(dataAvatar.getResourceId(i, -1));
            user.setName(dataName[i]);
            user.setUsername(dataUsername[i]);

            listUser.add(user);
        }
        return listUser;
    }

    private void showRecyclerUser() {
        rv_github.setLayoutManager(new LinearLayoutManager(this));
        UserAdapter userAdapter = new UserAdapter(list);
        rv_github.setAdapter(userAdapter);
    }
}