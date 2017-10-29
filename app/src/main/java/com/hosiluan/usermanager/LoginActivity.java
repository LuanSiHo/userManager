package com.hosiluan.usermanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {


    public static final String USER_NAME = "user name";
    public static final String LOGIN_PREFERENCES = "login_preferences";
    public static final String USERNAME_PREFERENCES = "username_preferences";
    public static final String PASSWORD_PREFERENCES = "password_preferences";

    private EditText usernameEdiText, passwordEditText;
    private TextView invalidUsernameTextView, invalidPasswordTextView;
    private Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setView();
        setEvent();

        String username = checkUserLoginStatus();
        if (username != null) {
            startHomeActivityWithUsername(username);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        SharedPreferences sharedPreferences = getSharedPreferences(LOGIN_PREFERENCES,
//                Context.MODE_PRIVATE);
//        Log.d("Luan",sharedPreferences.getString(USERNAME_PREFERENCES,"ahihi"));

        passwordEditText.setText("");
        usernameEdiText.setText("");
    }

    private void setView() {
        usernameEdiText = (EditText) findViewById(R.id.edt_username);
        passwordEditText = (EditText) findViewById(R.id.edt_password);
        invalidPasswordTextView = (TextView) findViewById(R.id.tv_invalid_password);
        invalidUsernameTextView = (TextView) findViewById(R.id.tv_invalid_username);
        loginButton = (Button) findViewById(R.id.btn_login);
    }

    private void setEvent() {

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEdiText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (!checkValidInfo(username)) {
                    invalidUsernameTextView.setText("Your username is invalid");
                } else {
                    invalidUsernameTextView.setText("");
                }


                if (!checkValidInfo(password)) {
                    invalidPasswordTextView.setText("Your password is invalid");
                } else {
                    invalidPasswordTextView.setText("");
                }


                if (checkValidInfo(username) && checkValidInfo(password)) {
                    SharedPreferences sharedPreferences = getSharedPreferences(LOGIN_PREFERENCES,
                            Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(USERNAME_PREFERENCES, username);
                    editor.putString(PASSWORD_PREFERENCES, password);
                    editor.apply();
                    startHomeActivityWithUsername(username);
                }
            }
        });
    }

    private void startHomeActivityWithUsername(String info) {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        intent.putExtra(USER_NAME, info);
        startActivity(intent);
    }

    private boolean checkValidInfo(String info) {
        if ((info.length() < 3) || (info.length() > 10)) {
            return false;
        }
        return true;
    }

    /**
     * return username if user already login
     *
     * @return
     */
    private String checkUserLoginStatus() {
        SharedPreferences sharedPreferences = getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(USERNAME_PREFERENCES, null);
        String password = sharedPreferences.getString(PASSWORD_PREFERENCES, null);
        if ((username != null) && (password != null)) {
            return username;
        }
        return null;
    }
}
