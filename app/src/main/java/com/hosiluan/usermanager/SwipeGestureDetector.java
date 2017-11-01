package com.hosiluan.usermanager;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by Ho Si Luan on 11/1/2017.
 */
class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener {

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        switch (getSlope(e1.getX(), e1.getY(), e2.getX(), e2.getY())) {
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