package com.example.aff02.retrofitrecycle.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aff02.retrofitrecycle.R;

public class ViewDetails extends AppCompatActivity {

    public ImageView img;
    public TextView txtname,txtdesc;
    private static final String PREFS = "Prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        getSupportActionBar().setTitle("View Details");

        img = (ImageView)findViewById(R.id.proimage);
        txtname = (TextView)findViewById(R.id.proname);
        txtdesc = (TextView)findViewById(R.id.prodesc);

        Intent intent = getIntent();

        String name = intent.getExtras().getString("Proname");
        txtname.setText(name);

        String desc = intent.getExtras().getString("Prodesc");
        txtdesc.setText(desc);
    }
}
