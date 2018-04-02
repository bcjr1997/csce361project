package com.csce.hamstersftw.hamsterhelp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class EmailResultPage extends AppCompatActivity {
    Databasehelper helper = new Databasehelper(this);
    private static final String TAG = "EmailResultPage";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_result_page);
        Bundle bundle = getIntent().getExtras();
        String Email = bundle.getString("Email");

        ArrayList<String> PersonalInformationByEmail = new ArrayList<String>();
        PersonalInformationByEmail = helper.EmailSearchPersonalInformation(Email);

        TextView PersonFirstName = (TextView) findViewById(R.id.Firstname1);
        TextView PersonLastName = (TextView) findViewById(R.id.LastName1);
        TextView PersonEmail = (TextView) findViewById(R.id.Email1);
        TextView PersonMobile = (TextView) findViewById(R.id.PhoneNumber1);
        PersonFirstName.setText("First Name: " + PersonalInformationByEmail.get(0));
        PersonLastName.setText("Last Name: " + PersonalInformationByEmail.get(1));
        PersonEmail.setText("Email: " + PersonalInformationByEmail.get(2));
        PersonMobile.setText("Mobile: " + PersonalInformationByEmail.get(3));
        String PersonalFirstName = PersonFirstName.getText().toString();
        String PersonalLastName =  PersonLastName.getText().toString();
        String PersonalEmail = PersonEmail.getText().toString();
        String PersonalMobile = PersonMobile.getText().toString();
        Log.d(TAG,"onCreate: " + PersonalFirstName);
        Log.d(TAG,"onCreate: " + PersonalLastName);
        Log.d(TAG,"onCreate: " + PersonalEmail);
        Log.d(TAG,"onCreate: " + PersonalMobile);
    }
}
