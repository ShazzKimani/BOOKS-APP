package com.moringaschool.booksapp;



import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.moringaschool.booksapp.databinding.ActivityBookDetailBinding;
import com.moringaschool.booksapp.models.Book;


public class BookDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        Book book = getIntent().getParcelableExtra("Book");
        ActivityBookDetailBinding binding = DataBindingUtil
                .setContentView(this,R.layout.activity_book_detail);
        binding.setBook(book);
    }
}
