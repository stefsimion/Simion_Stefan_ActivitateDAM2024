package com.example.seminar_4;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class interactiuneJson extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_interactiune_json);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection con = null;
                String keyOras = "";
                try {
                    URL url = new URL("http://dataservice.accuweather.com/locations/v1/cities/search?apikey=t32wDz2Y2GTqGmHtJ7A95kCO2d6N5sXK&q=Bucuresti");
                    con = (HttpURLConnection) url.openConnection();
                    InputStream is = con.getInputStream();

                    StringBuilder builder = new StringBuilder();
                    int ch;
                    while ((ch = is.read()) != -1) {
                        builder.append((char) ch);
                    }
                    String jsonResponse = builder.toString();

                    JSONArray vector = new JSONArray(jsonResponse);
                    JSONObject object = vector.getJSONObject(0);
                    keyOras = object.getString("Key");

                } catch (Exception e) {
                    Log.e("Error", "Eroare la procesarea API-ului: " + e.getMessage());
                } finally {
                    if (con != null) {
                        con.disconnect();
                    }
                }

                String finalKeyOras = keyOras;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
//                        TextView textView = findViewById(R.id.textViewOras);
                       // textView.setText("Cheie Oras: " + finalKeyOras);
                    }
                });
            }
        });
    }

}