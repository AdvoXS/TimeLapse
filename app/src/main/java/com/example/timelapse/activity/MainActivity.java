package com.example.timelapse.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.timelapse.R;
import com.example.timelapse.activity.adapter.MainViewPagerAdapter;
import com.example.timelapse.activity.fragment.CalendarFragment;
import com.example.timelapse.activity.fragment.NotificationFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void setupViewPager(ViewPager viewPager) {
        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CalendarFragment(), "Календарь");
        adapter.addFragment(new NotificationFragment(), "Оповещения");
        viewPager.setAdapter(adapter);
    }

}
