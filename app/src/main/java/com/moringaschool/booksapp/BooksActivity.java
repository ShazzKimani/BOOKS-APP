package com.moringaschool.booksapp;

import android.app.Activity;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import android.widget.ArrayAdapter;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BooksActivity extends AppCompatActivity {
    @BindView(R.id.listView) ListView mListView;
    @BindView(R.id.locationTextView) TextView mLocationTextView;


    private String[] books = new String[] {"The Facebook Effect", "The Soul of a New Machine",
            "The New New Thing", "The World is Flat", " The Physics of the Future", "Only the Paranoid Survive",
            "The Chip", "Hackers",
            " Microcosm", " Telecosm",
            " Accidental Empires", " Inside Intel", "The Long Tail ", " The Innovator’s Dilemma ", "  Free"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        ButterKnife.bind(this);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, books);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String books = ((TextView)view).getText().toString();
                Toast.makeText(BooksActivity.this, books, Toast.LENGTH_LONG).show();
            }
        });

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("Here are all the available books in tech: " + location);
    }
}
