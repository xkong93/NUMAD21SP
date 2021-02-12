package edu.neu.madcourse.numad21sp_xuankong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class LinkCollectorActivity extends AppCompatActivity implements LinkDialog.DialogListener {
    FloatingActionButton btnAddLink;
    ConstraintLayout constraintLayout;
    List<String> links;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);
        btnAddLink = findViewById(R.id.addLinkButton);
        constraintLayout = findViewById(R.id.linkLayout);
        links = new ArrayList<>();
        btnAddLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openAddLinkDialog();
            }
        });
        recyclerView = findViewById(R.id.recycleLayout);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecycleViewAdapter(links, LinkCollectorActivity.this);
        recyclerView.setAdapter(mAdapter);
    }

    private void openAddLinkDialog() {
            LinkDialog linkDialog = new LinkDialog();
            linkDialog.show(getSupportFragmentManager(),"Add Link Dialog");
    }
//    public void onGoUrl(View view) {
//        TextView textView = findViewById(R.id.linkcard_link);
//        Log.d("Success", textView.getText().toString(), null);
//    }
    @Override
    public void applyTexts(String link) {
        Snackbar snackbar;
        if (validateUrl(link)) {
            snackbar = Snackbar.make(constraintLayout,"Valid Link", Snackbar.LENGTH_LONG);
            links.add(link);

        }else {
            snackbar = Snackbar.make(constraintLayout,"Invalid Link. Try Again. ", Snackbar.LENGTH_LONG);
        }
        snackbar.show();
    }

    private boolean validateUrl(String url) {
        String reg = "^((ftp|http|https):\\/\\/)?(www.)?(?!.*(ftp|http|https|www.))[a-zA-Z0-9_-]+(\\.[a-zA-Z]+)+((\\/)[\\w#]+)*(\\/\\w+\\?[a-zA-Z0-9_]+=\\w+(&[a-zA-Z0-9_]+=\\w+)*)?$";
        if (url.matches(reg)) {
            return true;
        }
        return false;
    }
}