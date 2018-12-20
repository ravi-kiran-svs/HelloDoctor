package com.example.ravikiran.hellodoctor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ActivityPersonalDetails extends AppCompatActivity {

    private static final String TAG = "ActivityPersonalDetails";
    Database myDB;
    Spinner sp_01;
    EditText et_01, et_02, et_03, et_04;
    Button b_01;
    String dept, hos, doc, hour, sex, Name, age, Phone, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        myDB = new Database(this);
        sp_01 = findViewById(R.id.sp_01);
        et_01 = findViewById(R.id.et_01);
        et_02 = findViewById(R.id.et_02);
        et_03 = findViewById(R.id.et_03);
        et_04 = findViewById(R.id.et_04);
        b_01 = findViewById(R.id.b_01);

        dept = getIntent().getStringExtra("DEPT");
        hos = getIntent().getStringExtra("HOSPITAL");
        doc = getIntent().getStringExtra("DOC");
        hour = getIntent().getStringExtra("HOUR");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, new String[]{"Mr.", "Ms.", "Mrs.", "Other"});
        sp_01.setAdapter(adapter);

        /*
        ArrayList<String> s01 = myDB.getList("HOSPITAL", dept);
        final ArrayAdapter<String> adapter01 = new ArrayAdapter<>(ActivityDetails.this,
                android.R.layout.simple_spinner_dropdown_item, s01);
        sp_01.setAdapter(adapter01);*/

        b_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if      (sp_01.getSelectedItemPosition() == 0)  sex = "M";
                else if (sp_01.getSelectedItemPosition() == 3)  sex = "O";
                else                                            sex = "F";

                Name = et_01.getText().toString();
                age = et_02.getText().toString();
                Phone = et_03.getText().toString();
                email = et_04.getText().toString();

                if (Name.equals(""))
                    Toast.makeText(ActivityPersonalDetails.this, "Name cannot be empty",
                            Toast.LENGTH_SHORT).show();

                else if (age.equals(""))
                    Toast.makeText(ActivityPersonalDetails.this, "Age cannot be left empty.",
                            Toast.LENGTH_SHORT).show();

                else if (Integer.valueOf(age) > 150)
                    Toast.makeText(ActivityPersonalDetails.this, "Age cannot be " + age + ".",
                            Toast.LENGTH_SHORT).show();

                else if (Phone.length() != 10 || Phone.equals(""))
                    Toast.makeText(ActivityPersonalDetails.this, "Phone number is in wrong format.",
                            Toast.LENGTH_SHORT).show();

                else if (!email.contains("@") || email.equals(""))
                    Toast.makeText(ActivityPersonalDetails.this, "Email ID is in wrong format.",
                            Toast.LENGTH_SHORT).show();

                else    {
                    String message = "Are you sure you want appointment with " + dept + " specialist, Dr."
                            + doc + " at " + hos + " during " + hour + ".\n"
                            + Name + " - " + sex + " - " + age + "\n"
                            + Phone + "\n"
                            + email;
                    AlertDialog.Builder build = new AlertDialog.Builder(ActivityPersonalDetails.this);
                    build.setTitle("Verify your Details")
                            .setMessage(message)
                            .setPositiveButton("Register", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int position) {
                                    Intent i = new Intent(ActivityPersonalDetails.this, ActivityToken.class);
                                    i.putExtra("DEPT", dept);
                                    i.putExtra("HOSPITAL", hos);
                                    i.putExtra("DOC", doc);
                                    i.putExtra("HOUR", hour);
                                    i.putExtra("NAME", Name);
                                    i.putExtra("SEX", sex);
                                    i.putExtra("AGE", age);
                                    i.putExtra("PHONE", Phone);
                                    i.putExtra("EMAIL", email);
                                    myDB.addPatient(Name, sex, age, Phone, email, hos, dept, doc, hour);
                                    startActivity(i);
                                    Log.d(TAG, "onItemClick: to ActivityToken.");
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //Do nothing bitch...
                                }
                            });
                    build.create().show();
                }
            }
        });
    }
}
