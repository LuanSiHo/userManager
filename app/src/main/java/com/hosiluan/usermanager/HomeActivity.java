package com.hosiluan.usermanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import static com.hosiluan.usermanager.LoginActivity.LOGIN_PREFERENCES;
import static com.hosiluan.usermanager.LoginActivity.USER_NAME;

public class HomeActivity extends AppCompatActivity
        implements RecyclerViewSlideMenuAdapter.SlideMenuAdapterListener {

    private TextView welcomeTextView;
    private Button logoutButton, addUserButton, viewListUserButton, logoutButtonSlideMenu;
    private ImageButton openSlideMenuImageButton;
    private RecyclerView slideMenuRecyclerView;
    private RecyclerViewSlideMenuAdapter recyclerViewSlideMenuAdapter;
    private ArrayList<String> mSlideMenuList;

    private DrawerLayout mDrawerLayout;


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

    @Override
    protected void onResume() {
        super.onResume();
        recyclerViewSlideMenuAdapter.setDefaultSelectedPosition();
        this.overridePendingTransition(R.anim.anim_slideup, R.anim.anim_slidedown);
    }

    private void setView() {

        welcomeTextView = (TextView) findViewById(R.id.tv_welcome);
        logoutButton = (Button) findViewById(R.id.btn_logout);
        logoutButton.setPaintFlags(logoutButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        logoutButtonSlideMenu = (Button) findViewById(R.id.btn_logout_slide_menu);
        logoutButtonSlideMenu.setPaintFlags(logoutButtonSlideMenu.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        addUserButton = (Button) findViewById(R.id.btn_add_user);
        viewListUserButton = (Button) findViewById(R.id.btn_view_list_user);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        openSlideMenuImageButton = (ImageButton) findViewById(R.id.img_btn_open_slide_menu);

        slideMenuRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_slide_menu);
        mSlideMenuList = new ArrayList<>();
        mSlideMenuList.add("Home");
        mSlideMenuList.add("List User");
        mSlideMenuList.add("About");

        recyclerViewSlideMenuAdapter = new RecyclerViewSlideMenuAdapter(mSlideMenuList,
                getApplicationContext(), this);

        slideMenuRecyclerView.setAdapter(recyclerViewSlideMenuAdapter);
        slideMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));


    }

    private void setEvent() {
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
                startActivity(new Intent(HomeActivity.this, NewUserActivity.class));
            }
        });

        viewListUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, ListUserActivity.class));
            }
        });

        openSlideMenuImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(Gravity.START);
            }
        });

        logoutButtonSlideMenu.setOnClickListener(new View.OnClickListener() {
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

    }

    private String getUserInfo() {
        Intent intent = getIntent();
        if (intent != null) {
            return intent.getStringExtra(USER_NAME);
        }
        return null;
    }

    @Override
    public void onItemClick(int position) {
        switch (position) {
            case 0:
                mDrawerLayout.closeDrawers();
                break;
            case 1:
                startActivity(new Intent(HomeActivity.this, ListUserActivity.class));
                break;
            case 2:
                startActivity(new Intent(HomeActivity.this, CompanyWebViewActivity.class));
                break;
        }
    }

}
