package com.moringaschool.booksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.searchBooksButton)
    Button mSearchBooksButton;
    @BindView(R.id.locationEditText)
    EditText mLocationEditText;
    @BindView(R.id.contactUsButton)
    Button mContactUsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mLocationEditText = (EditText) findViewById(R.id.locationEditText);
        mContactUsButton = (Button) findViewById(R.id.contactUsButton);
        mContactUsButton.setOnClickListener(new View.OnClickListener()


            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(intent);
            }
        });


        mSearchBooksButton = (Button) findViewById(R.id.searchBooksButton);
        mSearchBooksButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String location = mLocationEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, BooksActivity.class);
                intent.putExtra("location", location);
                startActivity(intent);
            }
        });
    }
}






