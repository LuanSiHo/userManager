package com.hosiluan.usermanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hosiluan.usermanager.model.User;

import java.util.ArrayList;

/**
 * Created by Ho Si Luan on 10/29/2017.
 */

public class DBManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserDB";
    private static final String TABLE_NAME = "tbUser";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String EMAIL = "email";
    private static final String BIRTHDAY = "birthday";
    private static final String ADDRESS = "address";
    private static final String USER_ID = "id";

    private static final String SQL_CREATE_TABLE = "CREATE TABLE "
            + TABLE_NAME + " ( " + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + FIRST_NAME + " TEXT , "
            + LAST_NAME + " TEXT , "
            + EMAIL + " TEXT , "
            + BIRTHDAY + " TEXT , "
            + ADDRESS + " TEXT) ";


    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
//        onCreate(sqLiteDatabase);
//        Log.d("Luan","Drop successfylly");
    }

    public void addUser(User user) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIRST_NAME,user.getmFirstName());
        contentValues.put(LAST_NAME,user.getmLastName());
        contentValues.put(EMAIL,user.getmEmail());
        contentValues.put(BIRTHDAY,user.getmBirthday());
        contentValues.put(ADDRESS,user.getmAddress());

        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        sqLiteDatabase.close();
    }

    public void editUser(User user){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIRST_NAME,user.getmFirstName());
        contentValues.put(LAST_NAME,user.getmLastName());
        contentValues.put(EMAIL,user.getmEmail());
        contentValues.put(BIRTHDAY,user.getmBirthday());
        contentValues.put(ADDRESS,user.getmAddress());

        sqLiteDatabase.update(TABLE_NAME,contentValues,USER_ID +" = ?",
                new String[]{user.getmId() + ""});

        sqLiteDatabase.close();
    }

    public void deleteUser(User user){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME,USER_ID +" = ?",new String[]{user.getmId() + ""});
        sqLiteDatabase.close();
    }

    public ArrayList<User> searchUser(String clue){
        ArrayList<User> userList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " +
                FIRST_NAME + " LIKE ? OR " + LAST_NAME + " LIKE ?",new String[]{"%" + clue + "%"});

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i ++){
                User user = new User(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5));
                userList.add(user);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return userList;
    }

    public ArrayList<User> getUserList(){
        ArrayList<User> userList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase  = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME,new String[]{});
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i ++){
                User user = new User(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5));
                userList.add(user);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return userList;
    }
}
