package edu.neu.madcourse.numad21sp_xuankong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ServiceActivity extends AppCompatActivity {
    Button btService;
    TextView textViewNewCases;

    private Handler handler = new Handler();
    String requestUrl = "https://api.covidtracking.com/v1/us/current.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        btService = findViewById(R.id.LocatorButton);
        textViewNewCases = findViewById(R.id.textViewNewCase);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void runService(View view) {
        serviceRunnable serviceRunnable = new serviceRunnable();
        new Thread(serviceRunnable).start();
    }

    class serviceRunnable implements Runnable {
        @Override
        public void run() {
            handler.post(() -> {
                try {
                    HttpURLConnection httpClient =
                            (HttpURLConnection) new URL(requestUrl).openConnection();

                    // optional default is GET
                    httpClient.setRequestMethod("GET");
                    //add request header
                    httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
                    int responseCode = httpClient.getResponseCode();
                    try (BufferedReader in = new BufferedReader(
                            new InputStreamReader(httpClient.getInputStream()))) {

                        StringBuilder response = new StringBuilder();
                        String line;

                        while ((line = in.readLine()) != null) {
                            response.append(line);
                        }
                        String numberNewCases = getNumberNewCases(response.toString());
                        textViewNewCases.setText(numberNewCases);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            });
        }

        private String getNumberNewCases(String response) throws JSONException {
            JSONArray jsonArray = new JSONArray(response);
            JSONObject jsonObject = (JSONObject) jsonArray.get(0);
            String newCases = jsonObject.getString("positiveIncrease");
            return newCases;
        }
    }

}