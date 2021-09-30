package com.ahmadabuhasan.appgithubuser;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    UserAdapter userAdapter;
    private RecyclerView rvGithub;
    private final ArrayList<User> list = new ArrayList<>();
    private static long backPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvGithub = findViewById(R.id.rvGithub);
        rvGithub.setHasFixedSize(true);

        list.addAll(getUserGithub());
        showRecyclerUser();
    }

    public ArrayList<User> getUserGithub() {
        TypedArray dataAvatar = getResources().obtainTypedArray(R.array.avatar);
        String[] dataName = getResources().getStringArray(R.array.name);
        String[] dataUsername = getResources().getStringArray(R.array.username);
        String[] dataLocation = getResources().getStringArray(R.array.location);
        String[] dataRepository = getResources().getStringArray(R.array.repository);
        String[] dataCompany = getResources().getStringArray(R.array.company);
        String[] dataFollowers = getResources().getStringArray(R.array.followers);
        String[] dataFollowing = getResources().getStringArray(R.array.following);

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
        if (getApplicationContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rvGithub.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            rvGithub.setLayoutManager(new LinearLayoutManager(this));
        }

        userAdapter = new UserAdapter(list);
        rvGithub.setAdapter(userAdapter);

        userAdapter.setOnItemClickCallback(this::showSelectedUser);
    }

    private void showSelectedUser(User user) {
        Toast.makeText(this, "You choose " + user.getName(), Toast.LENGTH_SHORT).show();

        Intent i = new Intent(MainActivity.this, UserDetailActivity.class);
        i.putExtra(UserDetailActivity.EXTRA_USER, user);
        startActivity(i);
    }

    public void onBackPressed() {
        if (backPressed + 2000 > System.currentTimeMillis()) {
            finishAffinity();
        } else {
            Toast.makeText(this, "Press once again to exit", Toast.LENGTH_SHORT).show();
        }
        backPressed = System.currentTimeMillis();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.searchmenu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );
        searchView.setBackgroundColor(Color.BLACK);
        searchView.setQueryHint("Search Name...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                userAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (userAdapter != null) {
                    userAdapter.getFilter().filter(newText);
                }
                return false;
            }
        });
        searchMenuItem.getIcon().setVisible(false, false);
        return true;
    }
}