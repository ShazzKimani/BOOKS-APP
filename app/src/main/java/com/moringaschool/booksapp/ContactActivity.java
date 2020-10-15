package com.moringaschool.booksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ContactActivity extends AppCompatActivity {

@BindView(R.id.submit_areaButton) Button mSubmit_areaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        ButterKnife.bind(this);
        mSubmit_areaButton = (Button) findViewById(R.id. submit_areaButton);
        mSubmit_areaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ContactActivity.this, "Thank you for contacting us!", Toast.LENGTH_LONG).show();
            }
        });
    }
}