package com.hosiluan.usermanager;

import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ShowDialogActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{


    private Button showProgressbarButton, showSingleDialogButton, showTwoOptionDialog, showSlideUpFromBottomButton;
    private ProgressBar progressBar;
    GestureDetector detector;

    private WarningLayout warningLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_dialog);
        setView();
        setEvent();
    }

    private void setView() {

        detector=new GestureDetector(this);

        showProgressbarButton = (Button) findViewById(R.id.btn_show_progress_bar);
        showSingleDialogButton = (Button) findViewById(R.id.btn_show_single_dialog);
        showTwoOptionDialog = (Button) findViewById(R.id.btn_show_two_option_dialog);
        showSlideUpFromBottomButton = (Button) findViewById(R.id.btn_show_slide_up_from_bottom_dialog);

        warningLayout = (WarningLayout) findViewById(R.id.warning_layout);

        warningLayout.setOnWarningButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ShowDialogActivity.this, "clickkkkkkk", Toast.LENGTH_SHORT).show();
            }
        });

        warningLayout.setmWarningButtonBackgroundColor(R.color.colorRed);


        warningLayout.setmWarningButtonText("Click me");
        warningLayout.setmWarningButtonTextColor(R.color.colorAccent);
        warningLayout.setmWarningButtonTextSize(16);

        warningLayout.setmWarningTextColor(R.color.colorRed);
        warningLayout.setmWarningText("Changed warning text");

        warningLayout.setmWarningImageResource(R.drawable.ic_google);
    }

    private void setEvent() {
        showProgressbarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ShowDialogActivity.this);
                View view1 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.processing_dialog, null);

                builder.setView(view1);
                progressBar = view1.findViewById(R.id.progressbar);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                progressBar.setMax(100);
                setProgressValue(1);
            }
        });

        showSingleDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ShowDialogActivity.this);
                View view1 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.single_dialog, null);
                builder.setView(view1);
                Button button = view1.findViewById(R.id.btn_dissmiss_single_dialog);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
            }
        });


        showTwoOptionDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ShowDialogActivity.this);
                View view1 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.two_option_dialog, null);
                builder.setView(view1);

                final AlertDialog alertDialog = builder.create();
                alertDialog.show();

                Button cancelButton = view1.findViewById(R.id.btn_cancel_two_option_dialog);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(ShowDialogActivity.this, "cancel", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });

                Button discardButton = view1.findViewById(R.id.btn_discard_two_option_dialog);
                discardButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(ShowDialogActivity.this, "discard", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });
            }
        });

        showSlideUpFromBottomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(ShowDialogActivity.this, "ahihi", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(ShowDialogActivity.this);
                View view1 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.two_option_dialog, null);
                builder.setView(view1);

                final AlertDialog alertDialog = builder.create();
                alertDialog.getWindow().setWindowAnimations(R.style.DialogSlideAnim);
//                alertDialog.getWindow().setGravity(Gravity.BOTTOM);
                alertDialog.show();

                Button cancelButton = view1.findViewById(R.id.btn_cancel_two_option_dialog);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(ShowDialogActivity.this, "cancel", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });

                Button discardButton = view1.findViewById(R.id.btn_discard_two_option_dialog);
                discardButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(ShowDialogActivity.this, "discard", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });
            }
        });

    }

    private void setProgressValue(final int progress) {

        // set the progress
        progressBar.setProgress(progress);

        // thread is used to change the progress value
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setProgressValue(progress + 1);
            }
        });
        thread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
//        Toast.makeText(getApplicationContext(), "OnDown Gesture", Toast.LENGTH_LONG).show();
        return false;
    }
//
//    @Override
//    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//        Toast.makeText(getApplicationContext(), "Fling Gesture",  Toast.LENGTH_LONG).show();
//        return true;
//    }

    @Override
    public void onLongPress(MotionEvent e) {
//        Toast.makeText(getApplicationContext(), "Long Press Gesture",  Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//        Toast.makeText(getApplicationContext(), "Scroll Gesture",  Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
//        Toast.makeText(getApplicationContext(), "Show Press gesture",  Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
//        Toast.makeText(getApplicationContext(), "Single Tap Gesture",  Toast.LENGTH_LONG).show();
        return true;
    }



    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Toast.makeText(this, "Fling", Toast.LENGTH_SHORT).show();
        switch (getSlope(motionEvent.getX(), motionEvent.getY(), motionEvent1.getX(), motionEvent1.getY())) {
            case 1:
                Log.d("Luan", "top");
                return true;
            case 2:
                Log.d("Luan", "left");
                return true;
            case 3:
                Log.d("Luan", "down");
                return true;
            case 4:
                Log.d("Luan", "right");
                return true;
        }
        return false;
    }




    private int getSlope(float x1, float y1, float x2, float y2) {
        Double angle = Math.toDegrees(Math.atan2(y1 - y2, x2 - x1));
        if (angle > 45 && angle <= 135)
            // top
            return 1;
        if (angle >= 135 && angle < 180 || angle < -135 && angle > -180)
            // left
            return 2;
        if (angle < -45 && angle >= -135)
            // down
            return 3;
        if (angle > -45 && angle <= 45)
            // right
            return 4;
        return 0;
    }

}
