package com.hosiluan.usermanager;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Calendar;

public class NewUserActivity extends AppCompatActivity {


    private EditText firstNameEditText,lastNameEditText,
            emailEditText,addressEditText,birthdayEditText;
    private Button saveButton;
    private ImageButton backImageButton;
    private Calendar mSelectedTime = Calendar.getInstance();

    private DBManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        setView();
        setEvent();

        dbManager = new DBManager(getApplicationContext());
    }

    private void setView(){
        firstNameEditText = (EditText) findViewById(R.id.edt_first_name);
        lastNameEditText = (EditText) findViewById(R.id.edt_last_name);
        emailEditText = (EditText) findViewById(R.id.edt_email);
        addressEditText = (EditText) findViewById(R.id.edt_address);
        birthdayEditText = (EditText) findViewById(R.id.edt_birthday);
        saveButton = (Button) findViewById(R.id.btn_save);
        backImageButton = (ImageButton) findViewById(R.id.img_btn_add_user_back);
    }

    private void setEvent(){
        birthdayEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(NewUserActivity.this,
                        R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        mSelectedTime.set(Calendar.YEAR, i);
                        mSelectedTime.set(Calendar.MONTH, i1);
                        mSelectedTime.set(Calendar.DAY_OF_MONTH, i2);

                        birthdayEditText.setText(mSelectedTime.get(Calendar.DAY_OF_MONTH)
                                + " / " + (mSelectedTime.get(Calendar.MONTH) + 1)
                                + " / " + mSelectedTime.get(Calendar.YEAR));
                    }
                }, mSelectedTime.get(Calendar.YEAR), mSelectedTime.get(Calendar.MONTH),
                        mSelectedTime.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = firstNameEditText.getText().toString().trim();
                String lastName = lastNameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String birthday = birthdayEditText.getText().toString().trim();
                String address = addressEditText.getText().toString().trim();

                if (firstName.length() == 0 || lastName.length() == 0
                        ||email.length() == 0 || birthday.length() == 0
                        || address.length() == 0){
                    Toast.makeText(NewUserActivity.this, "please enter all fields", Toast.LENGTH_SHORT).show();
                }else {
                    User user = new User(firstName,lastName,email,birthday,address);
                    dbManager.addUser(user);
                    Toast.makeText(NewUserActivity.this, "successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        backImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}
