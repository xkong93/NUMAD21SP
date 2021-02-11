package edu.neu.madcourse.numad21sp_xuankong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class LinkCollectorActivity extends AppCompatActivity implements LinkDialog.DialogListener {
    FloatingActionButton btnAddLink;
    ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);
        btnAddLink = findViewById(R.id.addLinkButton);
        constraintLayout = findViewById(R.id.linkLayout);
        btnAddLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openAddLinkDialog();
            }
        });
    }


    private void openAddLinkDialog() {
            LinkDialog linkDialog = new LinkDialog();
            linkDialog.show(getSupportFragmentManager(),"Add Link Dialog");
    }

    @Override
    public void applyTexts(String link) {
        Snackbar snackbar;
        if (validateUrl(link)) {
            snackbar = Snackbar.make(constraintLayout,"Valid Link", Snackbar.LENGTH_LONG);
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