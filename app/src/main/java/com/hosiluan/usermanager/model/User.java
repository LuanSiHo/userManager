package com.hosiluan.usermanager.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ho Si Luan on 10/29/2017.
 */

public class User implements Parcelable {

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

    protected User(Parcel in) {
        mId = in.readInt();
        mFirstName = in.readString();
        mLastName = in.readString();
        mEmail = in.readString();
        mBirthday = in.readString();
        mAddress = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeString(mFirstName);
        parcel.writeString(mLastName);
        parcel.writeString(mEmail);
        parcel.writeString(mBirthday);
        parcel.writeString(mAddress);
    }
}
