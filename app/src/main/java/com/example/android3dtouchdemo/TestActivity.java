package com.example.android3dtouchdemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author :Sea
 * Date :2020-4-7 13:10
 * PackageName:com.example.android3dtouchdemo
 * Desc:
 */
public class TestActivity extends AppCompatActivity {
    public static final String ACTION_OPEN_DYNAMIC = "com.example.android3dtouchdemo.action.DYNAMIC_OPEN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
}
