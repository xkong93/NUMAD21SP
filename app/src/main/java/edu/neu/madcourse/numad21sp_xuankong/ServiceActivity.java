package edu.neu.madcourse.numad21sp_xuankong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class ServiceActivity extends AppCompatActivity {
    Button btService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        btService = findViewById(R.id.LocatorButton);

    }


}