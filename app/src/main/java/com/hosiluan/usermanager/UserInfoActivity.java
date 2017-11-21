package com.hosiluan.usermanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.hosiluan.usermanager.fragment.EditUserInfoFragment;
import com.hosiluan.usermanager.fragment.UserInfoToolbarFragment;
import com.hosiluan.usermanager.model.User;

import static com.hosiluan.usermanager.ListUserActivity.BUNDLE_TO_USER_INFO;
import static com.hosiluan.usermanager.ListUserActivity.OBJECT_TO_USER_INFO;

public class UserInfoActivity extends AppCompatActivity implements
        UserInfoToolbarFragment.UserInfoFragmentListener,
        EditUserInfoFragment.EditUserInfoFragmentListener {

    private User user;
    private EditText firstnameEditText, lastnameEditText, emailEditText,
            birthdayEditText, addressEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initToolbar();
        setView();
        recieveBundleFromUserList();
    }

    private void setView() {
        firstnameEditText = (EditText) findViewById(R.id.edt_user_info_firstname);
        lastnameEditText = (EditText) findViewById(R.id.edt_user_info_lastname);
        emailEditText = (EditText) findViewById(R.id.edt_user_info_email);
        birthdayEditText = (EditText) findViewById(R.id.edt_user_info_birthday);
        addressEditText = (EditText) findViewById(R.id.edt_user_info_address);

    }

    private void recieveBundleFromUserList() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(BUNDLE_TO_USER_INFO);
        user = bundle.getParcelable(OBJECT_TO_USER_INFO);
        firstnameEditText.setText(user.getmFirstName());
        lastnameEditText.setText(user.getmLastName());
        emailEditText.setText(user.getmEmail());
        birthdayEditText.setText(user.getmBirthday());
        addressEditText.setText(user.getmAddress());

        disableEditText();

    }

    private void initToolbar() {
        UserInfoToolbarFragment userInfoToolbarFragment = new UserInfoToolbarFragment();
        android.app.FragmentManager fragmentManager = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, userInfoToolbarFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackToUserListEvent() {
        finish();
    }

    @Override
    public void onEditUserInfoEvent() {

        EditUserInfoFragment editUserInfoFragment = new EditUserInfoFragment();
        android.app.FragmentManager fragmentManager = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, editUserInfoFragment);
        fragmentTransaction.commit();

        enableEditText();

    }

    private void disableEditText(){
        firstnameEditText.setEnabled(false);
        lastnameEditText.setEnabled(false);
        emailEditText.setEnabled(false);
        birthdayEditText.setEnabled(false);
        addressEditText.setEnabled(false);
    }
    private void enableEditText(){
        firstnameEditText.setEnabled(true);
        lastnameEditText.setEnabled(true);
        emailEditText.setEnabled(true);
        birthdayEditText.setEnabled(true);
        addressEditText.setEnabled(true);
    }

    @Override
    public void onBackToUserInfoEvent() {
        UserInfoToolbarFragment userInfoToolbarFragment = new UserInfoToolbarFragment();
        android.app.FragmentManager fragmentManager = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, userInfoToolbarFragment);
        fragmentTransaction.commit();

        disableEditText();
    }

    @Override
    public void onSaveUserInfoEvent() {

        DBManager dbManager = new DBManager(getApplicationContext());

        User updatedUser = new User();
        updatedUser.setmId(user.getmId());
        updatedUser.setmFirstName(firstnameEditText.getText().toString().trim());
        updatedUser.setmLastName(lastnameEditText.getText().toString().trim());
        updatedUser.setmEmail(emailEditText.getText().toString().trim());
        updatedUser.setmBirthday(birthdayEditText.getText().toString().trim());
        updatedUser.setmAddress(addressEditText.getText().toString().trim());

        dbManager.editUser(updatedUser);

        Log.d("Luan",updatedUser.getmId() + " / " + updatedUser.getmFirstName() + " / "
                + updatedUser.getmLastName() + " / " + updatedUser.getmEmail() + " / "
                + updatedUser.getmBirthday() + " / " + updatedUser.getmAddress());

        finish();

    }
}
