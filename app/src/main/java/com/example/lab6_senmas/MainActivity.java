package com.example.lab6_senmas;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainActivity extends AppCompatActivity {

    Drawer drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
//        toolbar.setBackground(getResources().getDrawable(R.color.colorPrimary));

        setSupportActionBar(toolbar);

        FirstFragment firstFragment = new FirstFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main_frame, firstFragment)
                .commit();

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.test)
                .build();

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName("Main");
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withName("Android Studio 3.5 Features");
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withName("Android Development Patterns");
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withName("Android Developer Stories");
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withName("Info");
//        PrimaryDrawerItem item6 = new PrimaryDrawerItem().withName("Sixth");

        drawer = new DrawerBuilder()
                .withAccountHeader(headerResult)
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,
                        new DividerDrawerItem(),
                        item2,
                        new DividerDrawerItem(),
                        item3,
                        new DividerDrawerItem(),
                        item4,
                        new DividerDrawerItem(),
                        item5
//                        new DividerDrawerItem(),
//                        item6
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position) {
                            case 1:
                                FirstFragment firstFragment = new FirstFragment();
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.activity_main_frame, firstFragment)
                                        .commit();
                                return false;
                            case 3:
                                SecondFragment secondFragment = new SecondFragment();
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.activity_main_frame, secondFragment)
                                        .commit();
                                return false;
                            case 5:
                                ThirdFragment thirdFragment = new ThirdFragment();
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.activity_main_frame, thirdFragment)
                                        .commit();
                                return false;
                            case 7:
                                ForthFragment forthFragment = new ForthFragment();
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.activity_main_frame, forthFragment)
                                        .commit();
                                return false;
                            case 9:
                                FifthFragment fifthFragment = new FifthFragment();
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.activity_main_frame, fifthFragment)
                                        .commit();
                                return false;
//                            case 11:
//                                SixthFragment sixthFragment = new SixthFragment();
//                                getSupportFragmentManager()
//                                        .beginTransaction()
//                                        .replace(R.id.activity_main_frame, sixthFragment)
//                                        .commit();
//                                return false;
                            default:
                                return true;
                        }
                    }
                })
                .build();

//        result.addStickyFooterItem(new PrimaryDrawerItem().withName("Test"));
    }

    @Override
    public void onBackPressed() {
        if (drawer.getCurrentSelectedPosition() == 1) {
            this.finish();
        }
        FirstFragment firstFragment = new FirstFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main_frame, firstFragment)
                .commit();
        drawer.setSelectionAtPosition(1);
    }


}
