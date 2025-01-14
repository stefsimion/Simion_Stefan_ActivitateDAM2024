package com.example.seminar_4;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FireBase extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private List<Sarpe> listaSerpi;
    private ArrayAdapter<Sarpe> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base);

        ListView listView = findViewById(R.id.firebaselv);
        listaSerpi = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaSerpi);
        listView.setAdapter(adapter);

        FirebaseApp.initializeApp(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("components");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaSerpi.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Sarpe s = dataSnapshot.getValue(Sarpe.class);
                    if (s != null) {
                        listaSerpi.add(s);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}