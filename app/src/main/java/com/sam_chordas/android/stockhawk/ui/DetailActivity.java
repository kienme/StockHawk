package com.sam_chordas.android.stockhawk.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sam_chordas.android.stockhawk.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_detail, new DetailFragment())
                    .commit();
        }
    }
}
