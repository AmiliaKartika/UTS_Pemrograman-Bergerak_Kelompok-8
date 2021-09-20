package com.ahmadabuhasan.appgithubuser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserDetailActivity extends AppCompatActivity {

    public static final String EXTRA_USER = "extra_user";
    CircleImageView img_avatar_received;
    TextView tv_name_received;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        img_avatar_received = findViewById(R.id.img_avatar_received);
        tv_name_received = findViewById(R.id.tv_name_received);

        User user = getIntent().getParcelableExtra(EXTRA_USER);
        int img_avatar = user.getAvatar();
        String text = user.getName();
        img_avatar_received.setImageResource(img_avatar);
        tv_name_received.setText(text);
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