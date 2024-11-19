package com.example.seminar_4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ListaImagini extends AppCompatActivity {

    List<Bitmap> imagini = new ArrayList<>();
    List<ImaginiSarpe> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_imagini);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        List<String> linkuriImagini = new ArrayList<>();
        linkuriImagini.add("http://www.romaniasalbatica.ro/medias/2017/09/15/Sarpele%20de%20casa%2002.jpg");
        linkuriImagini.add("http://www.romaniasalbatica.ro/medias/2017/09/15/Sarpele%20de%20casa%2001.jpg");
        linkuriImagini.add("http://www.romaniasalbatica.ro/medias/2017/09/15/Sarpele%20lui%20Esculap%2001.jpg");
        linkuriImagini.add("http://www.romaniasalbatica.ro/medias/2017/09/15/Sarpele%20de%20apa%2001.jpg");
        linkuriImagini.add("http://www.romaniasalbatica.ro/medias/2017/09/15/Sarpele%20de%20alun%2001.jpg");

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            try {
                for (String link : linkuriImagini) {
                    HttpURLConnection con = (HttpURLConnection) new URL(link).openConnection();
                    InputStream is = con.getInputStream();
                    imagini.add(BitmapFactory.decodeStream(is));
                    con.disconnect();
                }

                handler.post(() -> {
                    for (int i = 0; i < imagini.size(); i++) {
                        lista.add(new ImaginiSarpe("Sarpe " + (i + 1), imagini.get(i), linkuriImagini.get(i)));
                    }

                    ListView lv = findViewById(R.id.imagini);
                    ImagineAdapter adapter = new ImagineAdapter(getApplicationContext(), R.layout.layout_img, lista);
                    lv.setAdapter(adapter);
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
