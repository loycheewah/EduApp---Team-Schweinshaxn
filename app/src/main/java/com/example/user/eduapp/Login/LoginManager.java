package com.example.user.eduapp.Login;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.eduapp.Database.UserDbHelper;
import com.example.user.eduapp.JobDemand.JobDemandActivity;
import com.example.user.eduapp.R;
import com.example.user.eduapp.Rate.RateActivity;
import com.example.user.eduapp.Register.RegisterUI;
import com.example.user.eduapp.Search.SearchActivity;
import com.example.user.eduapp.WelcomePage;

public class LoginManager extends AppCompatActivity {
    SQLiteDatabase sqLiteDatabase;
    UserDbHelper userDbHelper;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Context context = this;

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);

        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final TextView textViewRegister = (TextView) findViewById(R.id.textViewRegister);
        final TextView textViewContinueGuest = (TextView) findViewById(R.id.textViewContinueGuest);

        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginManager.this, RegisterUI.class);
                LoginManager.this.startActivity(registerIntent);
            }
        });
    }


    public void onClick(View v) {

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final String username = etUsername.getText().toString();
        final String password = etPassword.getText().toString();

        userDbHelper = new UserDbHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getReadableDatabase();
        cursor = userDbHelper.getInformations(sqLiteDatabase);
        String databasePass,databaseUser;
        boolean success = false;

        if (username.length() == 0){
            Toast t1 = Toast.makeText(LoginManager.this, "Please enter Username", Toast.LENGTH_LONG);
            t1.show();
            return;
        }

        if (password.length() == 0){
            Toast t2 = Toast.makeText(LoginManager.this, "Please enter Password", Toast.LENGTH_LONG);
            t2.show();
            return;
        }

        if(cursor.moveToFirst()){
            do {
                databaseUser = cursor.getString(0);
                databasePass = cursor.getString(1);
                if (databasePass.equals(password) && databaseUser.equals(username) ){
                    success = true;
                    Toast t = Toast.makeText(LoginManager.this, "Login successful", Toast.LENGTH_LONG);
                    t.show();
                    Intent intent = new Intent(LoginManager.this, WelcomeActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                    break;
                }
            }while (cursor.moveToNext());
        }
        if(success == false){
            Toast t = Toast.makeText(LoginManager.this, "Invalid username/password", Toast.LENGTH_LONG);
            t.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        String username = getIntent().getExtras().getString("username");
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {
            Intent intent = new Intent(LoginManager.this, WelcomePage.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }

        if (id == R.id.action_search) {
            Intent intent = new Intent(LoginManager.this, SearchActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }

        if (id == R.id.action_jobdemand) {
            Intent intent = new Intent(LoginManager.this, JobDemandActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }

        if (id == R.id.action_rate) {
            Intent intent = new Intent(LoginManager.this, RateActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }

        if (id == R.id.action_logout) {
            Intent intent = new Intent(LoginManager.this, LoginManager.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}
