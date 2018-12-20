package com.example.ravikiran.hellodoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ActivityToken extends AppCompatActivity {

    Database myDB;
    TextView tv_main;
    ListView lv_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token);
        myDB = new Database(this);
        tv_main = findViewById(R.id.tv_main);
        lv_main = findViewById(R.id.lv_main);

        String Name = getIntent().getStringExtra("NAME");
        String sex = getIntent().getStringExtra("SEX");
        String age = getIntent().getStringExtra("AGE");
        String Phone = getIntent().getStringExtra("PHONE");
        String email = getIntent().getStringExtra("EMAIL");
        String hospital = getIntent().getStringExtra("HOSPITAL");
        String dept = getIntent().getStringExtra("DEPT");
        String doc = getIntent().getStringExtra("DOC");
        String hour = getIntent().getStringExtra("HOUR");

        String message = "Your appointment:\n"
                + "Name : " + Name + "\n"
                + "Sex : " + sex + "\n"
                + "Age : " + age + "\n"
                + "Phone : " + Phone + "\n"
                + "Email : " + email + "\n"
                + "with Dr." + doc + "\n"
                + "Dept : " + dept + "\n"
                + "Hospital : " + hospital + "\n"
                + "Timings :  " + hour + "\n";
        tv_main.setText(message);

        setListView();
        lv_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent i = new Intent(ActivityToken.this, ActivityLogin.class);
                startActivity(i);
            }
        });
    }

    private void setListView() {
        ArrayAdapter<String> adap = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                myDB.getPatients());
        lv_main.setAdapter(adap);
    }
}
