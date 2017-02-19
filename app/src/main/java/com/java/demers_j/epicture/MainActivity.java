package com.java.demers_j.epicture;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Menu menu;
    private boolean isShown = false;

    public Menu getMenu() {
        return menu;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (!isShown) {
                    try {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (getCurrentFocus() != null)
                            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                isShown = !isShown;
            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        launchSearch(R.integer.Flickr);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        if (this.menu == null)
            this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.sort_by_date:
                Toast.makeText(MainActivity.this, "Sort By Date", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.sort_by_name:
                Toast.makeText(MainActivity.this, "Sort By Name", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_flickr) {
            launchSearch(R.integer.Flickr);
        } else if (id == R.id.nav_imgur) {
            launchSearch(R.integer.Imgur);

        } else if (id == R.id.nav_favorite) {

        } else if (id == R.id.nav_settings) {
            SettingsFragment settingsFragment = new SettingsFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relativeLayout_for_fragment, settingsFragment, settingsFragment.getTag()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void launchSearch(int searchEngine) {
        SearchFragment searchFragment = SearchFragment.newInstance(searchEngine);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.relativeLayout_for_fragment, searchFragment, searchFragment.getTag()).commit();
    }
}
