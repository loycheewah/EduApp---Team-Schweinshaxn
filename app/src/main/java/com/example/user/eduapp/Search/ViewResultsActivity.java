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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.eduapp.Database.UserDbHelper;
import com.example.user.eduapp.JobDemand.JobDemandActivity;
import com.example.user.eduapp.Login.LoginManager;
import com.example.user.eduapp.R;
import com.example.user.eduapp.Rate.RateActivity;
import com.example.user.eduapp.WelcomePage;

public class ViewResultsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    SQLiteDatabase sqLiteDatabase;
    UserDbHelper userDbHelper;
    Cursor cursor;
    String Course,Institute,CourseDetails,CourseLearnings,FurtherStudies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_results);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView about = (ImageView) findViewById(R.id.imageView11);
        ImageView learning = (ImageView) findViewById(R.id.imageView12);
        ImageView furtherstudies = (ImageView) findViewById(R.id.imageView13);

        about.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewResultsActivity.this,ViewCourseDetailsActivity.class);
                intent.putExtra("coursedetails",CourseDetails);
                startActivity(intent);
            }
        });

        learning.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewResultsActivity.this,ViewCourseLearningActivity.class);
                intent.putExtra("courselearnings",CourseLearnings);
                startActivity(intent);

            }
        });

        furtherstudies.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewResultsActivity.this,ViewFurtherStudiesActivity.class);
                intent.putExtra("furtherstudies",FurtherStudies);
                startActivity(intent);

            }
        });

        TextView viewCourseResults = findViewById(R.id.ViewResults);
        viewCourseResults.setText(getIntent().getExtras().getString("COURSENAME") + ",");

        TextView viewInstituteResults = findViewById(R.id.ViewInstituteResults);
        viewInstituteResults.setText(getIntent().getExtras().getString("INSTITUTENAME"));

        userDbHelper = new UserDbHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getReadableDatabase();
        cursor = userDbHelper.getCourseInformations(sqLiteDatabase);

        if(cursor.moveToFirst()){
            do {
//                Toast k = Toast.makeText(ViewResultsActivity.this, "came here", Toast.LENGTH_LONG);
//                k.show();
                Course = cursor.getString(0);
                Institute = cursor.getString(5);
                if (Course.equals(getIntent().getExtras().getString("COURSENAME")) && Institute.equals(getIntent().getExtras().getString("INSTITUTENAME")) ){
                    Toast t = Toast.makeText(ViewResultsActivity.this, "Success", Toast.LENGTH_LONG);
                    t.show();
                    CourseDetails = cursor.getString(6);
                    CourseLearnings = cursor.getString(7);
                    FurtherStudies = cursor.getString(8);
                    break;
                }
            }while (cursor.moveToNext());
        }
    }

    //button viewCourseDetails
    public void viewCourseDetails(View view){
        //call result activity
        Intent intent = new Intent(this, ViewCourseDetailsActivity.class);
        intent.putExtra("coursedetails",CourseDetails);
        String username = getIntent().getExtras().getString("username");
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public void viewCourseLearnings(View view){
        //call result activity
        Intent intent = new Intent(this, ViewCourseLearningActivity.class);
        intent.putExtra("courselearnings",CourseLearnings);
        String username = getIntent().getExtras().getString("username");
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public void viewFurtherStudies(View view){
        Intent intent = new Intent(this, ViewFurtherStudiesActivity.class);
        intent.putExtra("furtherstudies",FurtherStudies);
        String username = getIntent().getExtras().getString("username");
        intent.putExtra("username", username);
        startActivity(intent);
    }

    //drop-down menu bar
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spin = (Spinner)parent;
        if (spin.getId() == R.id.spinner) {
            String sSelected = parent.getItemAtPosition(position).toString();
            String username = WelcomePage.userNameData;
            switch (position) {
                case 0:
                    break;
                case 1:
                    //show main page
                    Intent intent1 = new Intent(ViewResultsActivity.this, WelcomePage.class);
                    intent1.putExtra("username", username);
                    startActivity(intent1);
                    break;
                case 2:
                    //show job demand page
                    Intent intent2 = new Intent(ViewResultsActivity.this, JobDemandActivity.class);
                    intent2.putExtra("username", username);
                    startActivity(intent2);
                    break;
                case 3:
                    //show search page
                    Intent intent3 = new Intent(ViewResultsActivity.this, SearchActivity.class);
                    intent3.putExtra("username", username);
                    startActivity(intent3);
                    break;
                case 4:
                    //show rate page
                    Intent intent4 = new Intent(ViewResultsActivity.this, RateActivity.class);
                    intent4.putExtra("username", username);
                    startActivity(intent4);
                    break;
                case 5:
                    //show log out page
                    Intent intent5 = new Intent(ViewResultsActivity.this, LoginManager.class);
                    intent5.putExtra("username", username);
                    startActivity(intent5);
                    break;
            }
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
            Intent intent = new Intent(ViewResultsActivity.this, WelcomePage.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }

        if (id == R.id.action_search) {
            Intent intent = new Intent(ViewResultsActivity.this, SearchActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }

        if (id == R.id.action_jobdemand) {
            Intent intent = new Intent(ViewResultsActivity.this, JobDemandActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }

        if (id == R.id.action_rate) {
            Intent intent = new Intent(ViewResultsActivity.this, RateActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }

        if (id == R.id.action_logout) {
            Intent intent = new Intent(ViewResultsActivity.this, LoginManager.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
