package com.sigaritus.swu.zhihudailym.activity;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.sigaritus.swu.zhihudailym.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.main_container)
    FrameLayout mainContainer;
    ActionBarDrawerToggle mActionBarDrawerToggle;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
        if (savedInstanceState==null)
            showHome();

    }

    private void initViews(){

        setSupportActionBar(toolbar);
        ActionBar actionBar = this.getSupportActionBar();

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout, toolbar, R.string.open, R.string.close);

        mActionBarDrawerToggle.syncState();

        drawerLayout.addDrawerListener(mActionBarDrawerToggle);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });


    }
    private void showHome() {
        selectDrawerItem(navigationView.getMenu().getItem(0));
        drawerLayout.openDrawer(GravityCompat.START);
    }

    private void selectDrawerItem(MenuItem menuItem) {
        boolean specialToolbarBehaviour = false;
        Class fragmentClass;

        switch (menuItem.getItemId()) {
            case R.id.nav_home:

                break;
            case R.id.nav_theme_post:

                specialToolbarBehaviour = true;
                break;
            case R.id.nav_hot_post:

                break;
            case R.id.nav_history:

                break;
            case R.id.nav_offline:

                break;

            case R.id.nav_change_theme:

                break;
            case R.id.nav_about:

                break;
            default:

                break;
        }

        try {
            Fragment fragment = null;
            fragmentManager.beginTransaction().replace(R.id.main_container, fragment).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setToolbarElevation(specialToolbarBehaviour);
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setToolbarElevation(boolean specialToolbarBehaviour) {
        if (specialToolbarBehaviour) {
            toolbar.setElevation(0.0f);
            mainContainer.setElevation(getResources().getDimension(R.dimen.elevation_toolbar));
        } else {
            toolbar.setElevation(getResources().getDimension(R.dimen.elevation_toolbar));
            mainContainer.setElevation(0.0f);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mActionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        mActionBarDrawerToggle.syncState();
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }




}
