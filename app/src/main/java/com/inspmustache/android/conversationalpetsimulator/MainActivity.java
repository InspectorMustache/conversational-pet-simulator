package com.inspmustache.android.conversationalpetsimulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            return;
        }

        CategoriesFragment categoriesFragment = new CategoriesFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_fragment_container, categoriesFragment)
                .commit();
    }
}
