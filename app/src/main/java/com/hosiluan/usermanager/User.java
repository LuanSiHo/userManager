package com.hosiluan.usermanager;

/**
 * Created by Ho Si Luan on 10/29/2017.
 */

public class User  {

    private int mId;
    private String mFirstName;
    private String mLastName;
    private String mEmail;
    private String mBirthday;
    private String mAddress;

    public User() {
    }

    public User(int mId, String mFirstName, String mLastName, String mEmail, String mBirthday, String mAddress) {
        this.mId = mId;
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mEmail = mEmail;
        this.mBirthday = mBirthday;
        this.mAddress = mAddress;
    }

    public User(String mFirstName, String mLastName, String mEmail, String mBirthday, String mAddress) {
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mEmail = mEmail;
        this.mBirthday = mBirthday;
        this.mAddress = mAddress;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmBirthday() {
        return mBirthday;
    }

    public void setmBirthday(String mBirthday) {
        this.mBirthday = mBirthday;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }
}
