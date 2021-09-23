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
        @SuppressLint("Recycle") TypedArray dataAvatar = getResources().obtainTypedArray(R.array.avatar);
        String[] dataName = getResources().getStringArray(R.array.name);
        String[] dataUsername = getResources().getStringArray(R.array.username);
        String[] dataLocation = getResources().getStringArray(R.array.location);
        int[] dataRepository = getResources().getIntArray(R.array.repository);
        String[] dataCompany = getResources().getStringArray(R.array.company);
        int[] dataFollowers = getResources().getIntArray(R.array.followers);
        int[] dataFollowing = getResources().getIntArray(R.array.following);

        ArrayList<User> listUser = new ArrayList<>();
        for (int i = 0; i < dataName.length; i++) {
            User user = new User();
            user.setAvatar(dataAvatar.getResourceId(i, -1));
            user.setName(dataName[i]);
            user.setUsername(dataUsername[i]);
            user.setLocation(dataLocation[i]);
            user.setRepository(dataRepository[i]);
            user.setCompany(dataCompany[i]);
            user.setFollowers(dataFollowers[i]);
            user.setFollowing(dataFollowing[i]);

            listUser.add(user);
        }
        return listUser;
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