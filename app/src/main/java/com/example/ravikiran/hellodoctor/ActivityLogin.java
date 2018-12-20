package com.example.ravikiran.hellodoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ActivityLogin extends AppCompatActivity {

    private static final String TAG = "ActivityLogin";
    Button b01, b02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        b01 = findViewById(R.id.b01);
        b02 = findViewById(R.id.b02);

        b01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivityLogin.this, ActivityDeptSelect.class);
                startActivity(i);
                Log.d(TAG, "onClick: to ActivityDeptSelect.");
            }
        });

        b02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivityLogin.this, ActivityDocAdd.class);
                startActivity(i);
                Log.d(TAG, "onClick: to ActivityDocAdd.");
            }
        });
    }
}