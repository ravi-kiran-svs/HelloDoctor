package com.example.ravikiran.hellodoctor;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityDocAdd extends AppCompatActivity {

    //private static final String TAG = "ActivityDocAdd";
    Database myDB;
    Button b_add;
    ListView lv_01;
    char hours[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_add);

        myDB = new Database(this);
        b_add = findViewById(R.id.b_add);
        lv_01 = findViewById(R.id.lv_01);
        hours = new char[]{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'};

        setListView();

        b_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final LayoutInflater inf = LayoutInflater.from(ActivityDocAdd.this);
                @SuppressLint("InflateParams")
                View v1 = inf.inflate(R.layout.dialog_doc_add, null);

                Spinner sp_01 = v1.findViewById(R.id.sp_01);
                Spinner sp_02 = v1.findViewById(R.id.sp_02);
                final EditText et_01 = v1.findViewById(R.id.et_01);
                final EditText et_02 = v1.findViewById(R.id.et_02);
                final EditText et_03 = v1.findViewById(R.id.et_03);

                ArrayList<String> s01 = myDB.getList("HOSPITAL");
                s01.add(0, "other");
                ArrayAdapter<String> adapter01 = new ArrayAdapter<>(ActivityDocAdd.this,
                        android.R.layout.simple_spinner_dropdown_item, s01);
                sp_01.setAdapter(adapter01);

                ArrayList<String> s02 = myDB.getList("DEPT");
                s02.add(0, "other");
                ArrayAdapter<String> adapter02 = new ArrayAdapter<>(ActivityDocAdd.this,
                        android.R.layout.simple_spinner_dropdown_item, s02);
                sp_02.setAdapter(adapter02);

                sp_01.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String s_02 = adapterView.getItemAtPosition(i).toString();
                        if (!s_02.equals("other"))
                            et_02.setText(s_02);
                        else if (s_02.equals("other"))
                            et_02.setText("");
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                sp_02.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String s_03 = adapterView.getItemAtPosition(i).toString();
                        if (!s_03.equals("other"))
                            et_03.setText(s_03);
                        else if (s_03.equals("other"))
                            et_03.setText("");
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                AlertDialog.Builder build = new AlertDialog.Builder(ActivityDocAdd.this);
                build.setTitle("Add a Doctor")
                        .setView(v1)
                        .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String s_name = et_01.getText().toString();
                                String s_hosp = et_02.getText().toString();
                                String s_dept = et_03.getText().toString();
                                StringBuilder s_hour = new StringBuilder();
                                for (int j=0; j<12; j++)    {
                                    s_hour.append(hours[j]);
                                }

                                if  (!s_name.equals("") && !s_hosp.equals("") && !s_dept.equals("") &&
                                        !String.valueOf(s_hour).equals("000000000000"))
                                    myDB.addDoc(s_name, s_hosp, s_dept, s_hour);
                                else
                                    Toast.makeText(ActivityDocAdd.this, "Empty Values are not accepted.",
                                            Toast.LENGTH_SHORT).show();
                                hours = new char[]{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'};
                                setListView();
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Do nothing bitch...
                            }
                        });

                build.create().show();
            }
        });
    }

    void setListView()  {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                myDB.getDocs());
        lv_01.setAdapter(adapter);
    }

    public void checked(View view)  {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId())   {
            case R.id.cb_01:
                if (checked)    hours[0] = '1';
                else            hours[0] = '0';
                break;
            case R.id.cb_02:
                if (checked)    hours[1] = '1';
                else            hours[1] = '0';
                break;
            case R.id.cb_03:
                if (checked)    hours[2] = '1';
                else            hours[2] = '0';
                break;
            case R.id.cb_04:
                if (checked)    hours[3] = '1';
                else            hours[3] = '0';
                break;
            case R.id.cb_05:
                if (checked)    hours[4] = '1';
                else            hours[4] = '0';
                break;
            case R.id.cb_06:
                if (checked)    hours[5] = '1';
                else            hours[5] = '0';
                break;
            case R.id.cb_07:
                if (checked)    hours[6] = '1';
                else            hours[6] = '0';
                break;
            case R.id.cb_08:
                if (checked)    hours[7] = '1';
                else            hours[7] = '0';
                break;
            case R.id.cb_09:
                if (checked)    hours[8] = '1';
                else            hours[8] = '0';
                break;
            case R.id.cb_10:
                if (checked)    hours[9] = '1';
                else            hours[9] = '0';
                break;
            case R.id.cb_11:
                if (checked)    hours[10] = '1';
                else            hours[10] = '0';
                break;
            case R.id.cb_12:
                if (checked)    hours[11] = '1';
                else            hours[11] = '0';
                break;
        }
    }
}
