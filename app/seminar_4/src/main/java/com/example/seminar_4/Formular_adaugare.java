package com.example.seminar_4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Formular_adaugare extends AppCompatActivity {

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

                Intent it = new Intent();
                it.putExtra("sarpe",s);
                setResult(RESULT_OK, it);
                finish();
            }
        });
    }
}
