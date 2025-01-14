package com.example.seminar_4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Formular_adaugare extends AppCompatActivity {

    private SerpiDataBase database = null;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_formular_adaugare);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FirebaseApp.initializeApp(this);
        database  = Room.databaseBuilder(this, SerpiDataBase.class, "SerpiDB").build();
        databaseReference = FirebaseDatabase.getInstance().getReference("components");

        Intent it = getIntent();
        if(it.hasExtra("sarpe")){
            Sarpe s = it.getParcelableExtra("sarpe");
            EditText specieEt=findViewById(R.id.specie);
            EditText lungimeEt = findViewById(R.id.lungime);
            EditText culoareEt = findViewById(R.id.culoare);
            CheckBox veninosCb = findViewById(R.id.checkBox);

            specieEt.setText(s.getSpecie());
            lungimeEt.setText(s.getLungime_medie());
            culoareEt.setText(s.getCuloare());
            veninosCb.setChecked(s.isVeninos());

        }

        Button btnAdaugare = findViewById(R.id.button2);
        btnAdaugare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etSpecie = findViewById(R.id.specie);
                String specie = etSpecie.getText().toString();

                EditText etLungime = findViewById(R.id.lungime);
                String lungime = etLungime.getText().toString();

                EditText etCuloare = findViewById(R.id.culoare);
                String culoare = etCuloare.getText().toString();

                CheckBox cbVenin = findViewById(R.id.checkBox);
                boolean veninos = cbVenin.isChecked();

                Sarpe s = new Sarpe(specie, lungime, culoare, veninos);

                Executor executor = Executors.newSingleThreadExecutor();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            FileOutputStream file;
                            file = openFileOutput("obiecteFavorite.txt", MODE_PRIVATE);
                            OutputStreamWriter output = new OutputStreamWriter(file);
                            BufferedWriter writer = new BufferedWriter(output);
                            writer.write(s.toString());
                            writer.close();
                            output.close();
                            file.close();

                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        database.getSerpiDAO().insertSarpe(s);
                    }
                });

                CheckBox cbOnline = findViewById(R.id.checkBoxFire);
                if (cbOnline.isChecked()) {
                    databaseReference.push().setValue(s)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Formular_adaugare.this, "Componenta salvată cu succes în Firebase", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(Formular_adaugare.this, "Eroare la salvarea în Firebase: " + task.getException().getMessage(),
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }

                Intent it = new Intent();
                it.putExtra("sarpe",s);
                setResult(RESULT_OK, it);
                finish();
            }
        });
    }
}
