package com.example.ravikiran.hellodoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ActivityDeptSelect extends AppCompatActivity {

    private static final String TAG = "ActivityDeptSelect";
    Database myDB;
    ListView lv_01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dept_select);

        myDB = new Database(this);
        lv_01 = findViewById(R.id.lv_01);
        setListView();

        lv_01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent i = new Intent(ActivityDeptSelect.this, ActivityDetails.class);
                String dept = lv_01.getItemAtPosition(position).toString();
                i.putExtra("DEPT", dept);
                startActivity(i);
                Log.d(TAG, "onItemClick: to ActivityDetails.");
            }
        });
    }

    private void setListView() {
        ArrayAdapter<String> adap = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                myDB.getList("DEPT"));
        lv_01.setAdapter(adap);
    }
}
