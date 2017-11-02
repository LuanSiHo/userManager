package com.hosiluan.usermanager.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hosiluan.usermanager.R;

/**
 * Created by User on 10/30/2017.
 */

public class UserInfoToolbarFragment extends Fragment {

    private TextView editUserInfoTextView;
    private ImageButton backImageButton;
    private UserInfoFragmentListener userInfoFragmentListener;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_info_toolbar,container,false);
        editUserInfoTextView = (TextView) view.findViewById(R.id.tv_edit_user_info);
        backImageButton = (ImageButton) view.findViewById(R.id.img_btn_user_info_back);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        userInfoFragmentListener = (UserInfoFragmentListener) getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        setEvent();
    }

    private void setEvent(){
        editUserInfoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getApplicationContext(), "edittt", Toast.LENGTH_SHORT).show();

                EditUserInfoFragment editUserInfoFragment = new EditUserInfoFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,editUserInfoFragment);
                fragmentTransaction.commit();
                userInfoFragmentListener.onEditUserInfoEvent();
            }
        });
        backImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getApplicationContext(), "back", Toast.LENGTH_SHORT).show();
                userInfoFragmentListener.onBackToUserListEvent();
            }
        });
    }


    public interface UserInfoFragmentListener {
        void onBackToUserListEvent();
        void onEditUserInfoEvent();
    }
}
