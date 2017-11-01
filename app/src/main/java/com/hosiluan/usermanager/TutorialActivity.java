package com.hosiluan.usermanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TutorialActivity extends AppCompatActivity {

    private static final String FIRST_LAUNCH_PREF = "first_launch_pref";
    private static final String IS_PREVIOUS_LAUNCH = "is_first_launch";
    private ViewPager pager;
    private TabLayout tabLayout;
    private TextView skipTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        setView();
        setEvent();
        checkFirstLaunchApp();
    }

    private void setView(){

        skipTextView = (TextView) findViewById(R.id.tv_skip);

        pager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        FragmentManager fragmentManager = getSupportFragmentManager();
        PagerAdapter adapter = new PagerAdapter(fragmentManager);

        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);

    }

    private void setEvent(){
        skipTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TutorialActivity.this, "skip", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(TutorialActivity.this,LoginActivity.class));
            }
        });
    }

    private void checkFirstLaunchApp(){
        SharedPreferences sharedPreferences = getSharedPreferences(FIRST_LAUNCH_PREF, Context.MODE_PRIVATE);
        boolean ispreviousLaunch = sharedPreferences.getBoolean(IS_PREVIOUS_LAUNCH,false);

        if(!ispreviousLaunch){

            // first time launch app
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(IS_PREVIOUS_LAUNCH,true);
            editor.apply();

        }else {
            startActivity(new Intent(TutorialActivity.this,LoginActivity.class));
        }
    }
}
