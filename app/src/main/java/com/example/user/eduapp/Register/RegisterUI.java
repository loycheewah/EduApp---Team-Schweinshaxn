package com.example.user.eduapp.Register;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.user.eduapp.R;

import java.util.Random;

public class RegisterUI extends AppCompatActivity {
        public static final String CODE = "sg.edu.ntu.n1703174he.eduapp.CODE";
        public static final String USERNAME = "sg.edu.ntu.n1703174he.eduapp.USERNAME";
        public static final String PASSWORD = "sg.edu.ntu.n1703174he.eduapp.PASSWORD";
        public static final String SCHOOLMAIL = "sg.edu.ntu.n1703174he.eduapp.SCHOOLMAIL";
        public static final String COURSE = "sg.edu.ntu.n1703174he.eduapp.COURSE";
        public static final String INSTITUTE = "sg.edu.ntu.n1703174he.eduapp.INSTITUTE";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);
        }

        public void register(View view) {               //called when button registered is clicked
            Intent intent = new Intent(this, VerificationUI.class);

            EditText editText1 = findViewById(R.id.username);       //get username
            String username = editText1.getText().toString();

            EditText editText2 = findViewById(R.id.password);       //get password
            String password = editText2.getText().toString();

            EditText editText3 = findViewById(R.id.school_email);   //get school email
            String schoolemail = editText3.getText().toString();

            EditText editText4 = findViewById(R.id.course);         //get course
            String course = editText4.getText().toString();

            EditText editText5 = findViewById(R.id.institute);
            String institute = editText5.getText().toString();

            //send email with verification code
            Random rand = new Random();
            int n = rand.nextInt(89999); // Gives n such that 0 <= n < 89999
            int code_number = n + 10000;
            String code = Integer.toString(code_number);

            //execute Async Task to send email
            new SendMailTask().execute(code);

            //give code to next UI
            intent.putExtra(CODE, code);
            intent.putExtra(USERNAME, username);
            intent.putExtra(PASSWORD, password);
            intent.putExtra(SCHOOLMAIL, schoolemail);
            intent.putExtra(COURSE, course);
            intent.putExtra(INSTITUTE,institute);

            //start verification screen
            startActivity(intent);
        }


        private class SendMailTask extends AsyncTask<String, Void, Void> {
            protected Void doInBackground(String... code)
            {
                EditText editText3 = findViewById(R.id.school_email);   //get school email
                String schoolemail = editText3.getText().toString();
                try {
                    GMailSender sender = new GMailSender("eduappntu@gmail.com", "eduapp00");
                    sender.sendMail("Your verification code for EduApp",         //subject
                            code[0],                                                    //body
                            "eduappntu@gmail.com",                               //sender gmail
                            schoolemail);                                              //user mail
                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }
                return null;
            }
        }
    }



