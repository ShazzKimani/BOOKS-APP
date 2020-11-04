package com.moringaschool.booksapp.ui;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.booksapp.R;
import com.moringaschool.booksapp.data.BooksAdapter;
import com.moringaschool.booksapp.models.Book;
import com.moringaschool.booksapp.network.NetworkUtils;
import com.moringaschool.booksapp.network.SharedP;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class BooksActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ProgressBar mProgress;
    private RecyclerView mBooksView;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_bar, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setOnQueryTextListener(this);
        overridePendingTransition(
                R.anim.zoom_in, R.anim.zoom_out);
        ArrayList<String> recentList = SharedP.getQueryList(getApplicationContext());
        int itemNum = recentList.size();

        MenuItem recentMenu;

        for (int i = 0; i < itemNum; i++) {
            recentMenu = menu.add(Menu.NONE, i, Menu.NONE, recentList.get(i));
        }

        return true;


    }






    @Override
    public boolean onQueryTextSubmit(String query) {

        try {
            URL bookUrl = NetworkUtils.buildUrl(query);
            new BooksQueryTask().execute(bookUrl);
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        mProgress = findViewById(R.id.progressBar);
        mBooksView = findViewById(R.id.mRecyclerView);

        LinearLayoutManager linearLayoutManager = new
                LinearLayoutManager(this);

        mBooksView.setLayoutManager(linearLayoutManager);

        Intent intent = getIntent();
        String url = intent.getStringExtra("QUERY");
        URL bookUrl;

        try {
            if (url == null || url.isEmpty()) {
                bookUrl = NetworkUtils.buildUrl("android");
            } else {
                bookUrl = new URL(url);
            }
            new BooksQueryTask().execute(bookUrl);
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }
    }



    public class BooksQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String result = null;
            try {
                result = NetworkUtils.getJson(searchUrl);
            } catch (IOException e) {
                Log.d("Error", e.getMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            TextView mErrorText = findViewById(R.id.tvError);
            mProgress.setVisibility(View.INVISIBLE);

            if (result == null) {
                mBooksView.setVisibility(View.INVISIBLE);
                mErrorText.setVisibility(View.VISIBLE);
            } else {
                mErrorText.setVisibility(View.INVISIBLE);
                mBooksView.setVisibility(View.VISIBLE);

                ArrayList<Book> mBooks = NetworkUtils.getBooksFromJson(result);
                BooksAdapter booksAdapter = new BooksAdapter(mBooks);
                mBooksView.setAdapter(booksAdapter);
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgress.setVisibility(View.VISIBLE);
            mBooksView.setVisibility(View.INVISIBLE);
        }

    }

}
