package com.hosiluan.usermanager;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by User on 11/1/2017.
 */

public class WarningLayout extends LinearLayout {

    private String mWarningText = "";
    private int mWarningTextColor;
    private float mWarningTextSize;

    private String mWarningButtonText = "";
    private int mWarningButtonTextColor;
    private int mWarningButtonBackgroundColor;
    private float mWarningButtonTextSize;

    private int mWarningImageResource;


    private TextView warningTextView;
    private Button warningButton;
    private ImageView warningImageView;

    public String onClickText = "nothing";


    public WarningLayout(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.layout_warning, this);

    }

    public WarningLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public WarningLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public WarningLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.WarningLayout, 0, 0);

        mWarningText = a.getString(R.styleable.WarningLayout_mWarningText);
        mWarningTextColor = a.getColor(R.styleable.WarningLayout_warningTextColor, Color.WHITE);
        mWarningTextSize = a.getDimension(R.styleable.WarningLayout_warningTextSize, 16);

        mWarningButtonText = a.getString(R.styleable.WarningLayout_warningButtonText);
        mWarningButtonTextSize = a.getDimension(R.styleable.WarningLayout_warningButtonTextSize, 16);
        mWarningButtonBackgroundColor = a.getColor(R.styleable.WarningLayout_warningButtonBackgroundColor, Color.WHITE);
        mWarningButtonTextColor = a.getColor(R.styleable.WarningLayout_warningButtonTextColor, Color.WHITE);

        mWarningImageResource = a.getResourceId(R.styleable.WarningLayout_warningImage, 0);

        LayoutInflater.from(context).inflate(R.layout.layout_warning, this);

        warningTextView = findViewById(R.id.tv_warning);
        warningButton = findViewById(R.id.btn_warning);
        warningImageView = findViewById(R.id.img_warning);


        warningTextView.setText(mWarningText);
        warningTextView.setTextColor(mWarningTextColor);
        warningTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mWarningTextSize);


        warningButton.setText(mWarningButtonText);
        warningButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, mWarningButtonTextSize);
        warningButton.setBackgroundColor(mWarningButtonBackgroundColor);
        warningButton.setTextColor(mWarningButtonTextColor);

        warningImageView.setImageResource(mWarningImageResource);
//        warningButton.setOnClickListener(this);
    }

    public void setmWarningText(String mWarningText) {
        this.mWarningText = mWarningText;
        if (warningTextView != null) {
            warningTextView.setText(mWarningText);
        }
    }

    public String getmWarningText() {
        return mWarningText;
    }

    public int getmWarningTextColor() {
        return mWarningTextColor;
    }

    public void setmWarningTextColor(int mWarningTextColor) {
        this.mWarningTextColor = mWarningTextColor;
        if (warningTextView != null) {
            if (mWarningTextColor > 0) {
                int color = ContextCompat.getColor(getContext(), mWarningTextColor);
                warningTextView.setTextColor(color);
            } else {
                warningTextView.setTextColor(mWarningTextColor);
            }
        }
    }

    public float getmWarningTextSize() {
        return mWarningTextSize;
    }

    public void setmWarningTextSize(float mWarningTextSize) {
        this.mWarningTextSize = mWarningTextSize;
        if (warningTextView != null) {
            warningTextView.setTextSize(mWarningTextSize);
        }
    }

    public String getmWarningButtonText() {
        return mWarningButtonText;
    }

    public void setmWarningButtonText(String mWarningButtonText) {
        this.mWarningButtonText = mWarningButtonText;
        if (warningButton != null) {
            warningButton.setText(mWarningButtonText);
        }
    }

    public int getmWarningButtonTextColor() {
        return mWarningButtonTextColor;
    }

    public void setmWarningButtonTextColor(int mWarningButtonTextColor) {
        this.mWarningButtonTextColor = mWarningButtonTextColor;
        if (warningButton != null) {
            if (mWarningButtonTextColor > 0) {
                int color = ContextCompat.getColor(getContext(), mWarningButtonTextColor);
                warningButton.setTextColor(color);
            } else {
                warningButton.setTextColor(mWarningButtonTextColor);

            }
        }
    }

    public int getmWarningButtonBackgroundColor() {
        return mWarningButtonBackgroundColor;
    }

    public void setmWarningButtonBackgroundColor(int mWarningButtonBackgroundColor) {
        this.mWarningButtonBackgroundColor = mWarningButtonBackgroundColor;
        if (warningButton != null) {

            if (mWarningButtonBackgroundColor > 0) {
                int color = ContextCompat.getColor(getContext(), mWarningButtonBackgroundColor);
                warningButton.setBackgroundColor(color);
            } else {
                warningButton.setBackgroundColor(mWarningButtonBackgroundColor);
            }
        }
    }

    public float getmWarningButtonTextSize() {
        return mWarningButtonTextSize;
    }

    public void setmWarningButtonTextSize(float mWarningButtonTextSize) {
        this.mWarningButtonTextSize = mWarningButtonTextSize;
        if (warningButton != null) {
            warningButton.setTextSize(mWarningButtonTextSize);
        }
    }

    public int getmWarningImageResource() {
        return mWarningImageResource;
    }

    public void setmWarningImageResource(int mWarningImageResource) {
        this.mWarningImageResource = mWarningImageResource;
        if (warningImageView != null) {
            warningImageView.setImageResource(mWarningImageResource);
        }
    }

    public void setOnWarningButtonClickListener(View.OnClickListener onClickListener) {
        warningButton.setOnClickListener(onClickListener);
    }
}
