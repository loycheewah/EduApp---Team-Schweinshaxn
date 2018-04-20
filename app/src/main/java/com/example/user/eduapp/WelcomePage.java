package com.example.user.eduapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.eduapp.JobDemand.JobDemandActivity;
import com.example.user.eduapp.Login.LoginManager;
import com.example.user.eduapp.Rate.RateActivity;
import com.example.user.eduapp.Search.SearchActivity;


public class WelcomePage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView username;
    ImageView Search;
    ImageView Rate;
    ImageView JobDemand;
    ImageView LogOut;
    public static String userNameData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String username = getIntent().getExtras().getString("username");
        userNameData = getIntent().getExtras().getString("username");
        Toast t2 = Toast.makeText(WelcomePage.this, username, Toast.LENGTH_LONG);
        t2.show();

        ImageView search = (ImageView) findViewById(R.id.imageView);
        ImageView Rate = (ImageView) findViewById(R.id.imageView3);
        ImageView JobDemand = (ImageView) findViewById(R.id.imageView2);
        ImageView LogOut = (ImageView) findViewById(R.id.imageView5);

        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String username = getIntent().getExtras().getString("username");
                Intent intent = new Intent(WelcomePage.this,SearchActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);

            }
        });

        Rate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String username = getIntent().getExtras().getString("username");
                Intent intent2 = new Intent(WelcomePage.this, RateActivity.class);
                intent2.putExtra("username", username);
                startActivity(intent2);
            }
        });

        JobDemand.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String username = getIntent().getExtras().getString("username");
                Intent intent1 = new Intent (WelcomePage.this,JobDemandActivity.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });

        LogOut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent (WelcomePage.this,LoginManager.class);
                startActivity(intent3);
            }
        });

    }


    @Override
   public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String sSelected = parent.getItemAtPosition(position).toString();
//        Toast.makeText(this,sSelected, Toast.LENGTH_SHORT).show();
//        //view.setSelected(true);
//        switch(position){
//            case 0:
//                break;
//            case 1:
//                //show about page
//
//                break;
//            case 2:
//                //show job demand page
//                Intent intent1 = new Intent (this,JobDemandActivity.class);
//                startActivity(intent1);
//                break;
//            case 3:
//                //show search page
//                Intent intent = new Intent(this, SearchActivity.class);
//                startActivity(intent);
//                break;
//            case 4:
//                //show rate
//                String username = getIntent().getExtras().getString("username");
//                Intent intent2 = new Intent(this, RateActivity.class);
//                intent2.putExtra("username", username);
//                startActivity(intent2);
//                break;
//            case 5:
//                //log out
//                break;
//        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
