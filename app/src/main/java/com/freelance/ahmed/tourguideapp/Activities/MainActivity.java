package com.freelance.ahmed.tourguideapp.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.freelance.ahmed.tourguideapp.Fragments.ChrisFragment;
import com.freelance.ahmed.tourguideapp.Fragments.IslamicFragment;
import com.freelance.ahmed.tourguideapp.Fragments.ModernFragment;
import com.freelance.ahmed.tourguideapp.Fragments.PharFragment;
import com.freelance.ahmed.tourguideapp.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String WIKI = "https://en.wikipedia.org/wiki/Egypt";
    private final static String FLAG = "Frag1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        PharFragment pharFragment = new PharFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.mainLayout, pharFragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (getSupportFragmentManager().findFragmentByTag(FLAG) != null) {
            super.onBackPressed();
        } else {
            PharFragment homeMainFragment = new PharFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.mainLayout, homeMainFragment).commit();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_pharaonic) {
            PharFragment pharFragment = new PharFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.mainLayout, pharFragment).addToBackStack(FLAG).commit();
        } else if (id == R.id.nav_islamic) {
            IslamicFragment islamicFragment = new IslamicFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.mainLayout, islamicFragment).addToBackStack(FLAG).commit();
        } else if (id == R.id.nav_christian) {
            ChrisFragment chrisFragment = new ChrisFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.mainLayout, chrisFragment).addToBackStack(FLAG).commit();
        } else if (id == R.id.nav_modern) {
            ModernFragment modernFragment = new ModernFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.mainLayout, modernFragment).addToBackStack(FLAG).commit();
        } else if (id == R.id.nav_wiki) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(WIKI));
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
