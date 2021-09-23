package com.ahmadabuhasan.appgithubuser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserDetailActivity extends AppCompatActivity {

    public static final String EXTRA_USER = "extra_user";
    CircleImageView img_avatar_received;
    TextView tv_name_received, tv_username_received, tv_location_received, tv_company_received;
    Button repository_received, followers_received, following_received;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        setTitle("Detail User");

        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        img_avatar_received = findViewById(R.id.img_avatar_received);
        tv_name_received = findViewById(R.id.tv_name_received);
        tv_username_received = findViewById(R.id.tv_username_received);
        tv_location_received = findViewById(R.id.tv_location_received);
        tv_company_received = findViewById(R.id.tv_company_received);
        repository_received = findViewById(R.id.repository_received);
        followers_received = findViewById(R.id.followers_received);
        following_received = findViewById(R.id.following_received);

        User user = getIntent().getParcelableExtra(EXTRA_USER);
        int img_avatar = user.getAvatar();
        String text = user.getName();
        String text1 = user.getUsername();
        String text2 = user.getLocation();
        int text3 = user.getRepository();
        String text4 = user.getCompany();
        int text5 = user.getFollowers();
        int text6 = user.getFollowing();

        img_avatar_received.setImageResource(img_avatar);
        tv_name_received.setText(text);
        tv_username_received.setText("@" + text1);
        tv_location_received.setText("Location : " + text2);
        tv_company_received.setText("Company : " + text3);

        repository_received.setTextColor(Color.BLACK);
        repository_received.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        repository_received.setText("Repository \n \n" + user.getRepository());

        followers_received.setTextColor(Color.BLACK);
        followers_received.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        followers_received.setText("Followers \n \n" + text5);

        following_received.setTextColor(Color.BLACK);
        following_received.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        following_received.setText("Following \n \n" + text6);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}