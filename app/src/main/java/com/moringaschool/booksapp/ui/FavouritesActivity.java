package com.moringaschool.booksapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.booksapp.R;

public class FavouritesActivity extends AppCompatActivity {

    EditText editText;
    Button submit;
    DatabaseReference rootRef, demoRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        editText = findViewById(R.id.etValue);
        submit = findViewById(R.id.btnSubmit);

        // Database reference pointing to root of database
        rootRef = FirebaseDatabase.getInstance().getReference();

        // Database reference pointing to demo node
        demoRef = rootRef.child("demo");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = editText.getText().toString();

                // Push creates a unique id in database
                demoRef.setValue(value);
                Toast.makeText(FavouritesActivity.this, "Successfully submitted favourites", Toast.LENGTH_SHORT).show();
            }
        });

        Button fetchButton = findViewById(R.id.btnFetch);
        final TextView fetchedText = findViewById(R.id.tvValue);

        fetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                demoRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        fetchedText.setText(value);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(FavouritesActivity.this, "Error fetching data", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}