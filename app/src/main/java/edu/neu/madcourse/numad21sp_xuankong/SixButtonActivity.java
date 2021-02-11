package edu.neu.madcourse.numad21sp_xuankong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class SixButtonActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
    }

    public void onPress(View view) {
        Button b = (Button)view;
        String buttonText = b.getText().toString();
        TextView textView = findViewById(R.id.textView2);
        textView.setText("Pressed: " + buttonText);
    }


}