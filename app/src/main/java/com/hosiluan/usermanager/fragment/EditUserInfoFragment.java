package com.hosiluan.usermanager.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hosiluan.usermanager.R;

/**
 * Created by User on 10/30/2017.
 */

public class EditUserInfoFragment extends Fragment {

    private TextView saveUserInfoTextView;
    private ImageButton backImageButton;

    private EditUserInfoFragmentListener editUserInfoFragmentListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_user_info_toolbar, container, false);
        saveUserInfoTextView = view.findViewById(R.id.tv_save_user_info);
        backImageButton = view.findViewById(R.id.img_btn_edit_user_info_back);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        editUserInfoFragmentListener = (EditUserInfoFragmentListener) activity;
    }

    @Override
    public void onResume() {
        super.onResume();
        setEvent();

    }

    private void setEvent() {
        backImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editUserInfoFragmentListener.onBackToUserInfoEvent();
            }
        });

        saveUserInfoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editUserInfoFragmentListener.onSaveUserInfoEvent();
            }
        });
    }

    public interface EditUserInfoFragmentListener {
        void onBackToUserInfoEvent();

        void onSaveUserInfoEvent();
    }
}
