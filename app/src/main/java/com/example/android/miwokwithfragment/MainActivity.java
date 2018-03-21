package com.example.android.miwokwithfragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = findViewById(R.id.view_pager);
        adjustedFragmentPagerAdapter fragments = new adjustedFragmentPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(fragments);

        TabLayout tabLayout = findViewById(R.id.slidings);
        tabLayout.setupWithViewPager(viewPager);

    }
}
