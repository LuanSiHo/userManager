package com.hosiluan.usermanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.hosiluan.usermanager.LoginActivity.LOGIN_PREFERENCES;
import static com.hosiluan.usermanager.LoginActivity.USER_NAME;

public class HomeActivity extends AppCompatActivity {

    private TextView welcomeTextView;
    private Button logoutButton,addUserButton,viewListUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setView();
        setEvent();
        String username = getUserInfo();
        if (username != null) {
            welcomeTextView.setText("Welcome " + username);
        }

    }

    private void setView() {
        welcomeTextView = (TextView) findViewById(R.id.tv_welcome);
        logoutButton = (Button) findViewById(R.id.btn_logout);
        logoutButton.setPaintFlags(logoutButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        addUserButton = (Button) findViewById(R.id.btn_add_user);
        viewListUserButton = (Button) findViewById(R.id.btn_view_list_user);

    }

    private void setEvent(){
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(LOGIN_PREFERENCES,
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                finish();
            }
        });

        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,NewUserActivity.class));
            }
        });

        viewListUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,ListUserActivity.class));
            }
        });
    }


    private String getUserInfo() {
        Intent intent = getIntent();
        if (intent != null) {
            return intent.getStringExtra(USER_NAME);
        }
        return null;
    }
}
