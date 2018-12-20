package com.example.ravikiran.hellodoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class ActivityDetails extends AppCompatActivity {

    private static final String TAG = "ActivityDetails";
    Database myDB;
    Spinner sp_01, sp_02, sp_03;
    Button b_01;
    String dept, hos, doc, hour;

    //TODO - problem with back button in personalDetails activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        myDB = new Database(this);
        sp_01 = findViewById(R.id.sp_01);
        sp_02 = findViewById(R.id.sp_02);
        sp_03 = findViewById(R.id.sp_03);
        b_01 = findViewById(R.id.b_01);

        dept = getIntent().getStringExtra("DEPT");
        setSpinnerContent();

        b_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hour = sp_03.getSelectedItem().toString();
                Intent i = new Intent(ActivityDetails.this, ActivityPersonalDetails.class);
                i.putExtra("DEPT", dept);
                i.putExtra("HOSPITAL", hos);
                i.putExtra("DOC", doc);
                i.putExtra("HOUR", hour);
                startActivity(i);
                Log.d(TAG, "onItemClick: to ActivityPersonalDetails.");
            }
        });
    }

    private void setSpinnerContent() {
        ArrayList<String> s01 = myDB.getList("HOSPITAL", dept);
        final ArrayAdapter<String> adapter01 = new ArrayAdapter<>(ActivityDetails.this,
                android.R.layout.simple_spinner_dropdown_item, s01);
        sp_01.setAdapter(adapter01);

        sp_01.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                hos = adapterView.getItemAtPosition(i).toString();

                ArrayList<String> s02 = myDB.getList("NAME", dept, hos);
                final ArrayAdapter<String> adapter02 = new ArrayAdapter<>(ActivityDetails.this,
                        android.R.layout.simple_spinner_dropdown_item, s02);
                sp_02.setAdapter(adapter02);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //lol nothing
            }
        });

        sp_02.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                doc = adapterView.getItemAtPosition(i).toString();

                ArrayList<String> s03 = myDB.getList(ActivityDetails.this, "HOURS", dept, hos, doc);
                final ArrayAdapter<String> adapter03 = new ArrayAdapter<>(ActivityDetails.this,
                        android.R.layout.simple_spinner_dropdown_item, s03);
                sp_03.setAdapter(adapter03);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
