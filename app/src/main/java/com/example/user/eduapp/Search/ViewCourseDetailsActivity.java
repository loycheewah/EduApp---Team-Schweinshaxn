package com.example.user.eduapp.Search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.eduapp.JobDemand.JobDemandActivity;
import com.example.user.eduapp.Login.LoginManager;
import com.example.user.eduapp.R;
import com.example.user.eduapp.Rate.RateActivity;
import com.example.user.eduapp.WelcomePage;

public class ViewCourseDetailsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_course_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String username = getIntent().getExtras().getString("username");
        TextView furtherStudies = (TextView) findViewById(R.id.CourseDetails);
        furtherStudies.setText(getIntent().getExtras().getString("coursedetails"));
    }
    //drop-down menu bar
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spin = (Spinner)parent;
        if (spin.getId() == R.id.spinner) {
            String sSelected = parent.getItemAtPosition(position).toString();
            Toast.makeText(this, sSelected, Toast.LENGTH_SHORT).show();
            String username = WelcomePage.userNameData;
            switch (position) {
                case 0:
                    break;
                case 1:
                    //show main page
                    Intent intent1 = new Intent(ViewCourseDetailsActivity.this, WelcomePage.class);
                    intent1.putExtra("username", username);
                    startActivity(intent1);
                    break;
                case 2:
                    //show job demand page
                    Intent intent2 = new Intent(ViewCourseDetailsActivity.this, JobDemandActivity.class);
                    intent2.putExtra("username", username);
                    startActivity(intent2);
                    break;
                case 3:
                    //show search page
                    Intent intent3 = new Intent(ViewCourseDetailsActivity.this, SearchActivity.class);
                    intent3.putExtra("username", username);
                    startActivity(intent3);
                    break;
                case 4:
                    //show rate page
                    Intent intent4 = new Intent(ViewCourseDetailsActivity.this, RateActivity.class);
                    intent4.putExtra("username", username);
                    startActivity(intent4);
                    break;
                case 5:
                    //show log out page
                    Intent intent5 = new Intent(ViewCourseDetailsActivity.this, LoginManager.class);
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
            Intent intent = new Intent(ViewCourseDetailsActivity.this, WelcomePage.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }

        if (id == R.id.action_search) {
            Intent intent = new Intent(ViewCourseDetailsActivity.this, SearchActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }

        if (id == R.id.action_jobdemand) {
            Intent intent = new Intent(ViewCourseDetailsActivity.this, JobDemandActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }

        if (id == R.id.action_rate) {
            Intent intent = new Intent(ViewCourseDetailsActivity.this, RateActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }

        if (id == R.id.action_logout) {
            Intent intent = new Intent(ViewCourseDetailsActivity.this, LoginManager.class);
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

