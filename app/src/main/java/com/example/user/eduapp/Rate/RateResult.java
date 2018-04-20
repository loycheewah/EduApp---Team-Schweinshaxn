package com.example.user.eduapp.Rate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user.eduapp.R;
import com.example.user.eduapp.WelcomePage;

public class RateResult extends AppCompatActivity {

    private Button buttonBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_result);

        buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(RateResult.this, WelcomePage.class);

                String username = getIntent().getExtras().getString("username");
                intent.putExtra("username",username);

                startActivity(intent);
            }
        });

    }
}
