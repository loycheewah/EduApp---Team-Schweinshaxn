package com.example.user.eduapp.Register;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.eduapp.Database.UserDbHelper;
import com.example.user.eduapp.Login.LoginManager;
import com.example.user.eduapp.R;

import java.util.Objects;

public class VerificationUI extends AppCompatActivity {
    Context context;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_ui);
        Button button = (Button)findViewById(R.id.button3);
        button.setEnabled(false);


    }

    public void verify(View view) {               //called when button verify is clicked

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();

        //get variables from last UI (safe!)
        String verification_code = intent.getStringExtra(RegisterUI.CODE);
        String username = intent.getStringExtra(RegisterUI.USERNAME);
        String password = intent.getStringExtra(RegisterUI.PASSWORD);
        String schoolemail = intent.getStringExtra(RegisterUI.SCHOOLMAIL);
        String course = intent.getStringExtra(RegisterUI.COURSE);
        String institute = intent.getStringExtra(RegisterUI.INSTITUTE);

        //get variables from UI Verification
        TextView textView = findViewById(R.id.veri_success);
        EditText editText1 = findViewById(R.id.veriCode);       //get user verification code
        String userCode = editText1.getText().toString();

        //check if verification code correct
        if (Objects.equals(verification_code, userCode)) {

//            //write data into internal storage
//            String filename = "RegisteredUsers";
//            String separator_info = "|";
//            String separator_users = "||";
//            FileOutputStream outputStream;
//
//            try {
//                outputStream = openFileOutput(filename, Context.MODE_PRIVATE);  //accessible only to EduApp
//                outputStream.write(username.getBytes());
//                outputStream.write(separator_info.getBytes());
//                outputStream.write(password.getBytes());
//                outputStream.write(separator_info.getBytes());
//                outputStream.write(schoolemail.getBytes());
//                outputStream.write(separator_info.getBytes());
//                outputStream.write(course.getBytes());
//                outputStream.write(separator_info.getBytes());
//                outputStream.write(separator_users.getBytes());
//                outputStream.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

            //check if username is not available?
            String message = "Verification successful! Congratulations, you are now a registered user of EduApp!";
            textView.setText(message);

            //add to Database

            context = this;
            userDbHelper = new UserDbHelper(context);
            sqLiteDatabase = userDbHelper.getWritableDatabase();
            userDbHelper.addInformation(username,password,schoolemail,course,institute,"","","",sqLiteDatabase);
            Toast.makeText(getBaseContext(),"Data Saved",Toast.LENGTH_LONG).show();
            userDbHelper.close();
            Button button = (Button)findViewById(R.id.button3);
            button.setEnabled(true);


        } else {
            String message = "Verification failed! Please try again. You didn't get the verification code? Please wait some minutes or try registering again!";
            textView.setText(message);
        }
    }

    public void proceed(View view){
        Intent intent = new Intent(this, LoginManager.class);
        startActivity(intent);
    }
}

