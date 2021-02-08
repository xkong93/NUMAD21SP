package edu.neu.madcourse.numad21sp_xuankong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
    }

    public void onPress(View view) {
        Button b = (Button)view;
        String buttonText = b.getText().toString();
        TextView textView = findViewById(R.id.textView2);
        textView.setText("Pressed: " + buttonText);
    }


}