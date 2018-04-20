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
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.eduapp.JobDemand.JobDemandActivity;
import com.example.user.eduapp.Login.LoginManager;
import com.example.user.eduapp.R;
import com.example.user.eduapp.Rate.RateActivity;
import com.example.user.eduapp.WelcomePage;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ArrayList<String> selection = new ArrayList<String>();
    TextView finalText;
    Spinner spinnerDropDownMenu,spinnerL1R5,spinnerLocation,spinnerL1R4,spinnerInterest,spinnerIndustry,spinnerInstitution;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initialize spinners
        Spinner spinnerL1R5 = (Spinner) findViewById(R.id.spinnerL1R5);
        Spinner spinnerLocation = (Spinner) findViewById(R.id.spinnerLocation);
        Spinner spinnerL1R4 = (Spinner) findViewById(R.id.spinnerL1R4);
        Spinner spinnerInterest = (Spinner) findViewById(R.id.spinnerInterest);
        Spinner spinnerInstitution = (Spinner) findViewById(R.id.spinnerInstitution);
        Spinner spinnerIndustry = (Spinner) findViewById(R.id.spinnerIndustry);

        ArrayAdapter<CharSequence> adapterL1R5 = ArrayAdapter.createFromResource(this,
                R.array.L1R5_array, android.R.layout.simple_spinner_item);
        adapterL1R5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerL1R5.setAdapter(adapterL1R5);
        spinnerL1R5.setOnItemSelectedListener(this);

        //Spinner spinnerL1R4 = (Spinner) findViewById(R.id.spinnerL1R4);
        ArrayAdapter<CharSequence> adapterL1R4 = ArrayAdapter.createFromResource(this,
                R.array.L1R4_array, android.R.layout.simple_spinner_item);
        adapterL1R4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerL1R4.setAdapter(adapterL1R4);
        spinnerL1R4.setOnItemSelectedListener(this);

        //Spinner spinnerLocation = (Spinner) findViewById(R.id.spinnerLocation);
        ArrayAdapter<CharSequence> adapterLocation = ArrayAdapter.createFromResource(this,
                R.array.location_array, android.R.layout.simple_spinner_item);
        adapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLocation.setAdapter(adapterLocation);
        spinnerLocation.setOnItemSelectedListener(this);

        //Spinner spinnerLocation = (Spinner) findViewById(R.id.spinnerLocation);
        ArrayAdapter<CharSequence> adapterInterest = ArrayAdapter.createFromResource(this,
                R.array.interest_array, android.R.layout.simple_spinner_item);
        adapterInterest.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInterest.setAdapter(adapterInterest);
        spinnerInterest.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapterIndustry = ArrayAdapter.createFromResource(this,
                R.array.industry_array, android.R.layout.simple_spinner_item);
        adapterIndustry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIndustry.setAdapter(adapterIndustry);
        spinnerIndustry.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapterInstitution = ArrayAdapter.createFromResource(this,
                R.array.institution_array, android.R.layout.simple_spinner_item);
        adapterInstitution.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInstitution.setAdapter(adapterInstitution);
        spinnerInstitution.setOnItemSelectedListener(this);

        spinnerL1R4.setSelection(0);
        spinnerL1R5.setSelection(0);
        spinnerLocation.setSelection(0);
        spinnerInterest.setSelection(0);
        spinnerIndustry.setSelection(0);
        spinnerInstitution.setSelection(0);

        String test = spinnerL1R4.getSelectedItem().toString();
        spinnerL1R5.setEnabled(false);
        spinnerL1R4.setEnabled(false);
        spinnerLocation.setEnabled(false);
        spinnerInterest.setEnabled(false);
        spinnerIndustry.setEnabled(false);
        spinnerInstitution.setEnabled(false);


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
                    Intent intent1 = new Intent(SearchActivity.this, WelcomePage.class);
                    intent1.putExtra("username", username);
                    startActivity(intent1);
                    break;
                case 2:
                    //show job demand page
                    Intent intent2 = new Intent(SearchActivity.this, JobDemandActivity.class);
                    intent2.putExtra("username", username);
                    startActivity(intent2);
                    break;
                case 3:
                    //show search page
                    Intent intent3 = new Intent(SearchActivity.this, SearchActivity.class);
                    intent3.putExtra("username", username);
                    startActivity(intent3);
                    break;
                case 4:
                    //show rate page
                    Intent intent4 = new Intent(SearchActivity.this, RateActivity.class);
                    intent4.putExtra("username", username);
                    startActivity(intent4);
                    break;
                case 5:
                    //show log out page
                    Intent intent5 = new Intent(SearchActivity.this, LoginManager.class);
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

    //checkbox
    public void selectItem(View view){
        boolean checked = ((CheckBox)view).isChecked();
        spinnerL1R5 = (Spinner) findViewById(R.id.spinnerL1R5);
        spinnerLocation = (Spinner) findViewById(R.id.spinnerLocation);
        spinnerL1R4 = (Spinner) findViewById(R.id.spinnerL1R4);
        spinnerInterest = (Spinner) findViewById(R.id.spinnerInterest);
        spinnerIndustry = (Spinner) findViewById(R.id.spinnerIndustry);
        spinnerInstitution = (Spinner) findViewById(R.id.spinnerInstitution);
        switch(view.getId()){
            case R.id.L1R4:
                if (checked){
                    spinnerL1R4.setEnabled(true);
                }
                else {
                    spinnerL1R4.setSelection(0);
                    spinnerL1R4.setEnabled(false);
                }
                break;
            case R.id.L1R5:
                if (checked){
                    spinnerL1R5.setEnabled(true);
                }
                else {
                    spinnerL1R5.setSelection(0);
                    spinnerL1R5.setEnabled(false);
                }
                break;
            case R.id.Location:
                if (checked){
                    spinnerLocation.setEnabled(true);
                }
                else {
                    spinnerLocation.setSelection(0);
                    spinnerLocation.setEnabled(false);
                }
                break;
            case R.id.Interest:
                if (checked){
                    spinnerInterest.setEnabled(true);
                }
                else {
                    spinnerInterest.setSelection(0);
                    spinnerInterest.setEnabled(false);
                }
                break;
            case R.id.Industry:
                if (checked){
                    spinnerIndustry.setEnabled(true);
                }
                else {
                    spinnerIndustry.setSelection(0);
                    spinnerIndustry.setEnabled(false);
                }
                break;
            case R.id.Institution:
                if (checked){
                    spinnerInstitution.setEnabled(true);
                }
                else {
                    spinnerInstitution.setSelection(0);
                    spinnerInstitution.setEnabled(false);
                }
                break;
        }
    }

    //button
    public void finalSelection(View view){
        spinnerL1R5 = (Spinner) findViewById(R.id.spinnerL1R5);
        spinnerLocation = (Spinner) findViewById(R.id.spinnerLocation);
        spinnerL1R4 = (Spinner) findViewById(R.id.spinnerL1R4);
        spinnerInterest = (Spinner) findViewById(R.id.spinnerInterest);
        spinnerIndustry = (Spinner) findViewById(R.id.spinnerIndustry);
        spinnerInstitution = (Spinner) findViewById(R.id.spinnerInstitution);

        String L1R4 = spinnerL1R4.getSelectedItem().toString();
        String L1R5 = spinnerL1R5.getSelectedItem().toString();
        String Location = spinnerLocation.getSelectedItem().toString();
        String Interest = spinnerInterest.getSelectedItem().toString();
        String Industry = spinnerIndustry.getSelectedItem().toString();
        String Institution = spinnerInstitution.getSelectedItem().toString();

        if (spinnerL1R4.isEnabled()){
            if (L1R4.length() == 0){
                Toast t2 = Toast.makeText(SearchActivity.this, "Please select your input for the checked box", Toast.LENGTH_LONG);
                t2.show();
                return;
            }
        }

        if (spinnerL1R5.isEnabled()){
            if (L1R5.length() == 0){
                Toast t2 = Toast.makeText(SearchActivity.this, "Please select your input for the checked box", Toast.LENGTH_LONG);
                t2.show();
                return;
            }
        }

        if (spinnerLocation.isEnabled()){
            if (Location.length() == 0){
                Toast t2 = Toast.makeText(SearchActivity.this, "Please select your input for the checked box", Toast.LENGTH_LONG);
                t2.show();
                return;
            }
        }

        if (spinnerInterest.isEnabled()){
            if (Interest.length() == 0){
                Toast t2 = Toast.makeText(SearchActivity.this, "Please select your input for the checked box", Toast.LENGTH_LONG);
                t2.show();
                return;
            }
        }

        if (spinnerIndustry.isEnabled()){
            if (Industry.length() == 0){
                Toast t2 = Toast.makeText(SearchActivity.this, "Please select your input for the checked box", Toast.LENGTH_LONG);
                t2.show();
                return;
            }
        }

        if (spinnerInstitution.isEnabled()){
            if (Institution.length() == 0){
                Toast t2 = Toast.makeText(SearchActivity.this, "Please select your input for the checked box", Toast.LENGTH_LONG);
                t2.show();
                return;
            }
        }
        //Toast.makeText(this,Location, Toast.LENGTH_SHORT).show();
        //call result activity
        String username = getIntent().getExtras().getString("username");
        Intent intent = new Intent(this, ResultsActivity.class);
        intent.putExtra("L1R4",L1R4);
        intent.putExtra("L1R5",L1R5);
        intent.putExtra("Location",Location);
        intent.putExtra("Interest",Interest);
        intent.putExtra("Industry",Industry);
        intent.putExtra("Institution", Institution);
        intent.putExtra("username", username);
        startActivity(intent);
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
            Intent intent = new Intent(SearchActivity.this, WelcomePage.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }

        if (id == R.id.action_search) {
            Intent intent = new Intent(SearchActivity.this, SearchActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }

        if (id == R.id.action_jobdemand) {
            Intent intent = new Intent(SearchActivity.this, JobDemandActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }

        if (id == R.id.action_rate) {
            Intent intent = new Intent(SearchActivity.this, RateActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }

        if (id == R.id.action_logout) {
            Intent intent = new Intent(SearchActivity.this, LoginManager.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

}



