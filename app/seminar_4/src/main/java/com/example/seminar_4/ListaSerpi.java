package com.example.seminar_4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class ListaSerpi extends AppCompatActivity {

    private List<Sarpe> serpi = null;
    private int idModificat = 0;
    private AdapterSarpe adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_serpi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent it = getIntent();
        List<Sarpe> serpi = it.getParcelableArrayListExtra("serpi");

        ListView lv = findViewById(R.id.LVserpi);
        // ArrayAdapter<Sarpe> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,serpi);
        //lv.setAdapter(adapter);

        adapter = new AdapterSarpe(serpi, getApplicationContext(),R.layout.layout_listview);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentModifica = new Intent(getApplicationContext(), Formular_adaugare.class);
                intentModifica.putExtra("sarpe",serpi.get(i));
                idModificat=i;
                startActivityForResult(intentModifica,209);
                Toast.makeText(getApplicationContext(),serpi.get(i).toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(resultCode==209){
                serpi.set(idModificat,data.getParcelableExtra("sarpe"));
                adapter.notifyDataSetChanged();
            }
        }
    }


}