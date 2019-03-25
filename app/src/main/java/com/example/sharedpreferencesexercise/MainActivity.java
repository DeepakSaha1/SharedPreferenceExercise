package com.example.sharedpreferencesexercise;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText mName;
    private EditText mPassword;
    private Button mSubmit;
    private Button mLogout;
    private SharedPreferences pref;

    public static final String MY_PREF = "mypref";
    public static final String NAME = "name";
    public static final String PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mName = findViewById(R.id.et_name);
        mPassword = findViewById(R.id.et_pwd);

        mSubmit = findViewById(R.id.btn_submit);
        mLogout = findViewById(R.id.btn_logout);

        pref = getApplicationContext().getSharedPreferences(MY_PREF, MODE_PRIVATE);
        //                an editor to edit and save the changes in shared preferences
        final SharedPreferences.Editor editor = pref.edit();

        if(pref.contains(NAME))
            mName.setText(pref.getString(NAME,""));
        if (pref.contains(PASSWORD))
            mPassword.setText(pref.getString(PASSWORD, ""));

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mName.getText().toString().trim();
                String password = mName.getText().toString().trim();

//                SharedPreferences.Editor editor = pref.edit();
                editor.putString(NAME, name); // put data into shared preferences
                editor.putString(PASSWORD, password);

                editor.commit(); // commit changes
                Toast.makeText(MainActivity.this, "Data Inserted Successfully..", Toast.LENGTH_SHORT).show();
            }
        });

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pref.contains(NAME)) {
                    editor.remove(NAME); // to remove all data
                    editor.remove(PASSWORD);
                    Toast.makeText(MainActivity.this, "data cleared..", Toast.LENGTH_SHORT).show();
                    mName.setText("");
                    mPassword.setText("");
                    editor.commit();
                }else
                    Toast.makeText(MainActivity.this, "No data to remove..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
