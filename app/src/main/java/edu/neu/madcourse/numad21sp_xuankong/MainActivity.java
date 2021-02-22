package edu.neu.madcourse.numad21sp_xuankong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onDisplay(View view) {
        TextView textView = findViewById(R.id.textView);
        String name = "Xuan Kong";
        String email = "kong.xu@husky.neu.edu";
        String other = "Go Husky!";
        textView.setText(name + "\n" + email + "\n" + other);
    }

    public void OnGotoNewActivity(View view) {
        Intent intent = new Intent(this, SixButtonActivity.class);
        startActivity(intent);
    }

    public void OnGotoLinkCollectorActivity(View view) {
        Intent intent = new Intent(this, LinkCollectorActivity.class);
        startActivity(intent);
    }

    public void OnGotoLocatorActivity(View view) {
        Intent intent = new Intent(this, LocatorActivity.class);
        startActivity(intent);
    }
}