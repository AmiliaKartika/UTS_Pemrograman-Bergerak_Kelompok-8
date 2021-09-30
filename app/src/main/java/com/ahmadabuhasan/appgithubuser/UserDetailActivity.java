package com.ahmadabuhasan.appgithubuser;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ahmadabuhasan.appgithubuser.databinding.ActivityUserDetailBinding;
import com.bumptech.glide.Glide;

import java.util.Objects;

public class UserDetailActivity extends AppCompatActivity {

    public static final String EXTRA_USER = "extra_user";
    User user = new User();

    ActivityUserDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        setTitle("Detail User");

        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding = ActivityUserDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        user = getIntent().getParcelableExtra(EXTRA_USER);
        int imgAvatar = user.getAvatar();
        String textName = user.getName();
        String textUsername = user.getUsername();
        String textLocation = user.getLocation();
        String textRepository = user.getRepository();
        String textCompany = user.getCompany();
        String textFollowers = user.getFollowers();
        String textFollowing = user.getFollowing();

        Glide.with(this)
                .load(imgAvatar)
                .into(binding.imgAvatarReceived);
        binding.tvNameReceived.setText(textName);
        binding.tvUsernameReceived.setText(String.format("@%s", textUsername));
        binding.tvLocationReceived.setText(String.format("Location : %s", textLocation));
        binding.tvCompanyReceived.setText(String.format("Company : %s", textCompany));

        binding.repositoryReceived.setTextColor(Color.BLACK);
        binding.repositoryReceived.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        binding.repositoryReceived.setText(String.format("Repository \n \n%s", textRepository));

        binding.followersReceived.setTextColor(Color.BLACK);
        binding.followersReceived.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        binding.followersReceived.setText(String.format("Followers \n \n%s", textFollowers));

        binding.followingReceived.setTextColor(Color.BLACK);
        binding.followingReceived.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        binding.followingReceived.setText(String.format("Following \n \n%s", textFollowing));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        } else if (item.getItemId() == R.id.share) {
            String shareIntent = "Github User" +
                    "\n Name: " + user.getName() +
                    "\n Username: " + user.getUsername() +
                    "\n Company: " + user.getCompany() +
                    "\n Location: " + user.getLocation() +
                    "\n Repository: " + user.getRepository() +
                    "\n Followers: " + user.getFollowers() +
                    "\n Following: " + user.getFollowing();
            Intent i = new Intent();
            i.setAction(Intent.ACTION_SEND);
            i.putExtra(Intent.EXTRA_TEXT, shareIntent);
            i.setType("text/plain");
            startActivity(Intent.createChooser(i, "Share using"));
        }
        return true;
    }
}