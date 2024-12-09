package com.example.seminar_4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import androidx.room.Room;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ListaSerpi extends AppCompatActivity {

    private List<Sarpe> serpi = null;
    private int idModificat = 0;
    private AdapterSarpe adapter = null;
    private SerpiDataBase serpiDatabase = null;


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

       // Intent it = getIntent();
        //serpi = it.getParcelableArrayListExtra("serpi");


        ListView lv = findViewById(R.id.LVserpi);
        // ArrayAdapter<Sarpe> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,serpi);
        //lv.setAdapter(adapter);

//        adapter = new AdapterSarpe(serpi, getApplicationContext(),R.layout.layout_listview);
//        lv.setAdapter(adapter);


        serpiDatabase = Room.databaseBuilder(this, SerpiDataBase.class, "SerpiDB").build();

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try{
                    serpi = serpiDatabase.getSerpiDAO().getAllSerpi();
                }finally{
                    handler.post(() -> {
                        adapter = new AdapterSarpe(serpi, getApplicationContext(), R.layout.layout_listview);
                        lv.setAdapter(adapter);
                    });
                }
            }
        });


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

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {
                SharedPreferences sp = getSharedPreferences("serpiObiecte",MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(serpi.get(i).getKey(), serpi.get(i).toString());
                editor.commit();

                return false;
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