package com.example.user.eduapp.Rate;

import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.Toast;
import android.database.sqlite.*;
import android.content.*;

import com.example.user.eduapp.Database.CourseContract;
import com.example.user.eduapp.Database.UserContract;
import com.example.user.eduapp.Database.UserDbHelper;
import com.example.user.eduapp.JobDemand.JobDemandActivity;
import com.example.user.eduapp.Login.LoginManager;
import com.example.user.eduapp.R;
import com.example.user.eduapp.Search.SearchActivity;
import com.example.user.eduapp.WelcomePage;

public class RateActivity extends AppCompatActivity {
    SQLiteDatabase sqLiteDatabase;
    UserDbHelper userDbHelper;
    Cursor cursor, cursor2;
    String Username;

    private RatingBar ratingBar;
    private RatingBar ratingBar2;
    private RatingBar ratingBar3;
    private FloatingActionButton ratingButtonSubmit;

    private String ratingScore;
    private String ratingScore2;
    private String ratingScore3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userDbHelper = new UserDbHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getReadableDatabase();
        cursor = userDbHelper.getInformations(sqLiteDatabase);
        cursor2 = userDbHelper.getInformations(sqLiteDatabase);


        ratingBar = findViewById(R.id.ratingBar);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(0).setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(1).setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
        ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                Toast.makeText(RateActivity.this,
                        String.valueOf(rating),
                        Toast.LENGTH_SHORT).show();

                ratingScore = String.valueOf(rating);
            }
        });


        ratingBar2 = findViewById(R.id.ratingBar2);
        LayerDrawable stars2 = (LayerDrawable) ratingBar2.getProgressDrawable();
        stars2.getDrawable(2).setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        stars2.getDrawable(0).setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
        stars2.getDrawable(1).setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
        ratingBar2.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar2, float rating,
                                        boolean fromUser) {
                Toast.makeText(RateActivity.this,
                        String.valueOf(rating),
                        Toast.LENGTH_SHORT).show();

                ratingScore2 = String.valueOf(rating);
            }
        });

        ratingBar3 = findViewById(R.id.ratingBar3);
        LayerDrawable stars3 = (LayerDrawable) ratingBar3.getProgressDrawable();
        stars3.getDrawable(2).setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        stars3.getDrawable(0).setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
        stars3.getDrawable(1).setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
        ratingBar3.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar3, float rating,
                                        boolean fromUser) {
                Toast.makeText(RateActivity.this,
                        String.valueOf(rating),
                        Toast.LENGTH_SHORT).show();

                ratingScore3 = String.valueOf(rating);
            }

        });

        ratingButtonSubmit = findViewById(R.id.ratingButtonSubmit);
        //if click on me, then display the current rating value.
        ratingButtonSubmit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                String username = getIntent().getExtras().getString("username");
                String coursename;
                int columnindex = 0;
                if (cursor.moveToFirst()) {
                    do {
                        Username = cursor.getString(columnindex);
                        if (Username.equals(getIntent().getExtras().getString("username"))) {
                            coursename = cursor.getString(2);
                            updateRatingInformation(Username, ratingScore, ratingScore2, ratingScore3, sqLiteDatabase);
                            updateCourseAverageRating(coursename);



                            Intent intent = new Intent(RateActivity.this, RateResult.class);
                            intent.putExtra("username",username);

                            startActivity(intent);
                            return;
                        }
                        columnindex++;

                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        });

    }

    protected void updateRatingInformation (String username, String educationrating, String courserating, String
    schoolliferating, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContract.UserInfo.USER_EDUCATIONRATING, educationrating);
        contentValues.put(UserContract.UserInfo.USER_COURSERATING, courserating);
        contentValues.put(UserContract.UserInfo.USER_SCHOOLLIFERATING, schoolliferating);
        db.update(UserContract.UserInfo.TABLE_NAME, contentValues, "username = ?" , new String[] {username});
    }

    protected void updateCourseRatingInformation (String coursename, String courserating, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CourseContract.CourseInfo.Rating, courserating);
        db.update(CourseContract.CourseInfo.TABLE_NAME , contentValues, "coursename = ?", new String[] {coursename});
    }

    protected void updateCourseAverageRating (String coursename) {
        float average = 0;

        cursor2 = sqLiteDatabase.rawQuery("SELECT AVG(courserating) FROM userinfo", null);
        cursor2.moveToFirst();
        average = cursor2.getFloat(0);

        updateCourseRatingInformation (coursename, String.valueOf(average), sqLiteDatabase);

    }

    /*public boolean addRatingData() {
        mDbHelper androidOpenDbHelperObj = new mDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues ratingValues = new ContentValues();
        ratingValues.put(FeedEntry.COLUMN_NAME_TITLE, title);
        ratingValues.put(FeedEntry.COLUMN_NAME_SUBTITLE, subtitle);

        long newRowId = db.insert(FeedEntry.TABLE_NAME, null, ratingValues);

        if (newRowId == -1)
            return false;
        else
            return true;
    }*/

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
            Intent intent = new Intent(RateActivity.this, WelcomePage.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }

        if (id == R.id.action_search) {
            Intent intent = new Intent(RateActivity.this, SearchActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }

        if (id == R.id.action_jobdemand) {
            Intent intent = new Intent(RateActivity.this, JobDemandActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }

        if (id == R.id.action_rate) {
            Intent intent = new Intent(RateActivity.this, RateActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }

        if (id == R.id.action_logout) {
            Intent intent = new Intent(RateActivity.this, LoginManager.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

}
