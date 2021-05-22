package com.example.timelapse.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.timelapse.R;
import com.example.timelapse.activity.adapter.MainViewPagerAdapter;
import com.example.timelapse.activity.fragment.CalendarFragment;
import com.example.timelapse.activity.fragment.NotificationFragment;
import com.example.timelapse.activity.fragment.NotificationTimerDialogFragment;
import com.google.android.material.tabs.TabLayout;
import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainActivity extends AppCompatActivity {
    private Drawer.Result drawerResult = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        createMenu(toolbar);

        ViewPager viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void createMenu(Toolbar toolbar) {
        drawerResult = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withHeader(R.layout.header_navigation_menu)
                .addDrawerItems(
                        new SectionDrawerItem().withName(R.string.drawer_item_settings),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_notification).withIcon(FontAwesome.Icon.faw_bell),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_calendar).withIcon(FontAwesome.Icon.faw_calendar)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    // Обработка клика
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        if (position == 2) {
                            NotificationTimerDialogFragment dialogNotificationSettings = new NotificationTimerDialogFragment();
                            dialogNotificationSettings.show(getSupportFragmentManager(), "notification_settings");
                        }
                    }
                })
                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
                    @Override
                    // Обработка длинного клика, например, только для SecondaryDrawerItem
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        if (drawerItem instanceof SecondaryDrawerItem) {
                        }
                        return false;
                    }
                })
                .build();
    }


    private void setupViewPager(ViewPager viewPager) {
        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CalendarFragment(), "Календарь");
        adapter.addFragment(new NotificationFragment(), "Оповещения");
        viewPager.setAdapter(adapter);
    }

}
