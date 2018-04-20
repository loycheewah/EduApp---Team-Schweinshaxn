package com.example.user.eduapp.Search;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.eduapp.Database.UserDbHelper;
import com.example.user.eduapp.JobDemand.JobDemandActivity;
import com.example.user.eduapp.Login.LoginManager;
import com.example.user.eduapp.R;
import com.example.user.eduapp.Rate.RateActivity;
import com.example.user.eduapp.WelcomePage;

public class ResultsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView inputL1R5,inputL1R4,inputLocation,inputInterest,inputIndustry,inputInstitution;
    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    UserDbHelper userDbHelper;
    Cursor cursor;
    ListDataAdapter listDataAdapter;
    boolean filterL1R4,filterL1R5,filterLocation,filterInterest,filterIndustry,filterInstitution = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent intent = getIntent();
        inputL1R5 = (TextView) findViewById(R.id.L1R5);
        inputL1R5.setText(getIntent().getExtras().getString("L1R5"));
        if (inputL1R5.getText().toString().length() > 0){
            filterL1R5 = true;
//            Toast t = Toast.makeText(ResultsActivity.this, "filterL1R5", Toast.LENGTH_LONG);
//            t.show();
        }

        inputIndustry = (TextView) findViewById(R.id.Industry);
        inputIndustry.setText(getIntent().getExtras().getString("Industry"));
        if (inputIndustry.getText().toString().length() > 0){
            filterIndustry = true;
//            Toast t = Toast.makeText(ResultsActivity.this, "filterIndustry", Toast.LENGTH_LONG);
//            t.show();
        }

        inputInstitution = (TextView) findViewById(R.id.Institution);
        inputInstitution.setText(getIntent().getExtras().getString("Institution"));
        if (inputInstitution.getText().toString().length() > 0){
            filterInstitution = true;
//            Toast t = Toast.makeText(ResultsActivity.this, inputInstitution.getText().toString(), Toast.LENGTH_LONG);
//            t.show();
        }

        inputL1R4 = (TextView) findViewById(R.id.L1R4);
        inputL1R4.setText(getIntent().getExtras().getString("L1R4"));
        if (inputL1R4.getText().toString().length() > 0){
            filterL1R4 = true;
//            Toast t = Toast.makeText(ResultsActivity.this, "filterL1R4", Toast.LENGTH_LONG);
//            t.show();
        }

        inputLocation = (TextView) findViewById(R.id.location);
        inputLocation.setText(getIntent().getExtras().getString("Location"));
        if (inputLocation.getText().toString().length() > 0){
            filterLocation = true;
//            Toast t = Toast.makeText(ResultsActivity.this, "filterLocation", Toast.LENGTH_LONG);
//            t.show();
        }

        inputInterest = (TextView) findViewById(R.id.Interest);
        inputInterest.setText(getIntent().getExtras().getString("Interest"));
        if (inputInterest.getText().toString().length() > 0){
            filterInterest = true;
//            Toast t = Toast.makeText(ResultsActivity.this, "filterInterest", Toast.LENGTH_LONG);
//            t.show();
        }

        listView = (ListView)findViewById(R.id.list_view);
        userDbHelper = new UserDbHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getReadableDatabase();
        cursor = userDbHelper.getCourseInformations(sqLiteDatabase);
        String username = getIntent().getExtras().getString("username");
        Toast t = Toast.makeText(ResultsActivity.this, username, Toast.LENGTH_LONG);
        t.show();
        listDataAdapter = new ListDataAdapter(getApplicationContext(), R.layout.row_layout,username);
        listView.setAdapter(listDataAdapter);

        if (cursor.moveToFirst()) {
            do {
                String coursename, L1R5, L1R4, Location, Rating, Institution,Interest,Industry;
                coursename = cursor.getString(0);
                L1R4 = cursor.getString(1);
                L1R5 = cursor.getString(2);
                Location = cursor.getString(3);
                Rating = cursor.getString(4);
                Institution = cursor.getString(5);
//                Toast t = Toast.makeText(ResultsActivity.this, Institution, Toast.LENGTH_LONG);
//                t.show();
                Interest = cursor.getString(9);
                Industry = cursor.getString(10);
                boolean match = true;

                if (filterL1R5 == true){
                    if (!(Integer.parseInt(L1R5) >= Integer.parseInt(inputL1R5.getText().toString()))) {
                        match = false;
                    }
                }

                if (filterL1R4 == true){
                    if (!(Integer.parseInt(L1R4) >= Integer.parseInt(inputL1R4.getText().toString()))){
                        match = false;
                    }
                }

                if (filterLocation == true){
                    if (!(inputLocation.getText().toString().equals(Location))){
                        match = false;
                    }
                }

                if (filterInterest == true){
                    if (!(inputInterest.getText().toString().equals(Interest))){
                        match = false;
                    }
                }

                if (filterInstitution == true){
                    if (!(inputInstitution.getText().toString().equals(Institution))){
                        match = false;
                    }
                }
                if (filterIndustry == true) {
                    if (!(inputIndustry.getText().toString().equals(Industry))) {
                        match = false;
                    }
                }


                if (match == true){
                    DataProvider dataProvider = new DataProvider(coursename, L1R4, L1R5, Institution, Rating);
                    listDataAdapter.add(dataProvider);
                }

            } while (cursor.moveToNext());

        }
    }

    //drop-down menu bar
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spin = (Spinner)parent;
        if (spin.getId() == R.id.spinner) {
            String sSelected = parent.getItemAtPosition(position).toString();
            Toast.makeText(this, sSelected, Toast.LENGTH_SHORT).show();
            String username = getIntent().getExtras().getString("username");
            switch (position) {
                case 0:
                    break;
                case 1:
                    //show main page
                    Intent intent1 = new Intent(ResultsActivity.this, WelcomePage.class);
                    intent1.putExtra("username", username);
                    startActivity(intent1);
                    break;
                case 2:
                    //show job demand page
                    Intent intent2 = new Intent(ResultsActivity.this, JobDemandActivity.class);
                    intent2.putExtra("username", username);
                    startActivity(intent2);
                    break;
                case 3:
                    //show search page
                    Intent intent3 = new Intent(ResultsActivity.this, SearchActivity.class);
                    intent3.putExtra("username", username);
                    startActivity(intent3);
                    break;
                case 4:
                    //show rate page
                    Intent intent4 = new Intent(ResultsActivity.this, RateActivity.class);
                    intent4.putExtra("username", username);
                    startActivity(intent4);
                    break;
                case 5:
                    //show log out page
                    Intent intent5 = new Intent(ResultsActivity.this, LoginManager.class);
                    intent5.putExtra("username", username);
                    startActivity(intent5);
                    break;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

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
            Intent intent = new Intent(ResultsActivity.this, WelcomePage.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }

        if (id == R.id.action_search) {
            Intent intent = new Intent(ResultsActivity.this, SearchActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }

        if (id == R.id.action_jobdemand) {
            Intent intent = new Intent(ResultsActivity.this, JobDemandActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }

        if (id == R.id.action_rate) {
            Intent intent = new Intent(ResultsActivity.this, RateActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }

        if (id == R.id.action_logout) {
            Intent intent = new Intent(ResultsActivity.this, LoginManager.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}

