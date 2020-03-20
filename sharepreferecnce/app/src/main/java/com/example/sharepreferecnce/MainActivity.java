package com.example.sharepreferecnce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn1,btn2;
    TextView txt1,txt2;
    EditText etxt1,etxt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findwidget();
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    private void findwidget() {
        btn1 = findViewById(R.id.samedata);
        btn2 = findViewById(R.id.loaddata);
        txt1 = findViewById(R.id.nametext);
        txt2 = findViewById(R.id.lnametext);
        etxt1 = findViewById(R.id.fname);
        etxt2 = findViewById(R.id.lname);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.samedata) {
            String fname = etxt1.getText().toString();
            String lname = etxt2.getText().toString();
            SharedPreferences preferences = getSharedPreferences("mydata", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("namekey",fname);
            editor.putString("lnamekey",lname);
            editor.commit();
            Toasty.success(MainActivity.this, "Success!", Toast.LENGTH_SHORT, true).show();

        }
        if (v.getId() == R.id.loaddata) {
            SharedPreferences preferences = getSharedPreferences("mydata", Context.MODE_PRIVATE);
            if (preferences.contains("namekey") && preferences.contains("lnamekey")) {
                String namevalue = preferences.getString("namekey","data not found");
                String lnamevalue = preferences.getString("lnamekey","data not found");
                txt1.setText(namevalue);
                txt2.setText(lnamevalue);
                Toasty.success(this, "Get Success!", Toast.LENGTH_SHORT, true).show();
            }
            else {
                Toasty.warning(this, "Beware of the dog.", Toast.LENGTH_SHORT, true).show();
            }
        }
    }
}
