package com.hari.aboutplaceproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    public static final String DATA_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json";
    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        //Setting toolbar for Screen title
        setSupportActionBar(mToolBar);
    }
}
