package com.example.icecream;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Switch;

import com.example.icecream.ui.dashboard.DashboardFragment;
import com.example.icecream.ui.home.HomeFragment;
import com.example.icecream.ui.myinfo.MyinfoFragment;
import com.example.icecream.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.icecream.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private HomeFragment homefragment;
    private DashboardFragment dashboardFragment;
    private MyinfoFragment myinfoFragment;
    private NotificationsFragment notificationsFragment;
    private FragmentManager fragmentManager;
    private NavigationBarView navigationBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        /*
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_activity_main, homefragment).commit();
        navigationBarView = findViewById(R.id.nav_view);
        navigationBarView.setSelectedItemId(R.id.nav_host_fragment_activity_main);

        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        if (homefragment == null) {
                            homefragment = new HomeFragment();
                            fragmentManager.beginTransaction().add(R.id.nav_host_fragment_activity_main, homefragment).commit();
                        }
                        if (homefragment != null) {
                            fragmentManager.beginTransaction().show(homefragment).commit();
                        }
                        if (dashboardFragment != null) {
                            fragmentManager.beginTransaction().hide(dashboardFragment).commit();
                        }
                        if (myinfoFragment != null) {
                            fragmentManager.beginTransaction().hide(myinfoFragment).commit();
                        }
                        if (notificationsFragment != null) {
                            fragmentManager.beginTransaction().hide(notificationsFragment).commit();
                        }
                        return true;
                    case R.id.navigation_dashboard:
                        if (dashboardFragment == null) {
                            dashboardFragment = new DashboardFragment();
                            fragmentManager.beginTransaction().add(R.id.nav_host_fragment_activity_main, dashboardFragment).commit();
                        }
                        if (homefragment != null) {
                            fragmentManager.beginTransaction().hide(homefragment).commit();
                        }
                        if (dashboardFragment != null) {
                            fragmentManager.beginTransaction().show(dashboardFragment).commit();
                        }
                        if (myinfoFragment != null) {
                            fragmentManager.beginTransaction().hide(myinfoFragment).commit();
                        }
                        if (notificationsFragment != null) {
                            fragmentManager.beginTransaction().hide(notificationsFragment).commit();
                        }
                        return true;
                    case R.id.navigation_myinfo:
                        if (myinfoFragment == null) {
                            myinfoFragment = new MyinfoFragment();
                            fragmentManager.beginTransaction().add(R.id.nav_host_fragment_activity_main, myinfoFragment).commit();
                        }
                        if (homefragment != null) {
                            fragmentManager.beginTransaction().hide(homefragment).commit();
                        }
                        if (dashboardFragment != null) {
                            fragmentManager.beginTransaction().hide(dashboardFragment).commit();
                        }
                        if (myinfoFragment != null) {
                            fragmentManager.beginTransaction().show(myinfoFragment).commit();
                        }
                        if (notificationsFragment != null) {
                            fragmentManager.beginTransaction().hide(notificationsFragment).commit();
                        }
                        return true;
                    case R.id.navigation_notifications:
                        if (notificationsFragment == null) {
                            notificationsFragment = new NotificationsFragment();
                            fragmentManager.beginTransaction().add(R.id.nav_host_fragment_activity_main, notificationsFragment).commit();
                        }
                        if (homefragment != null) {
                            fragmentManager.beginTransaction().hide(homefragment).commit();
                        }
                        if (dashboardFragment != null) {
                            fragmentManager.beginTransaction().hide(dashboardFragment).commit();
                        }
                        if (myinfoFragment != null) {
                            fragmentManager.beginTransaction().hide(myinfoFragment).commit();
                        }
                        if (notificationsFragment != null) {
                            fragmentManager.beginTransaction().show(notificationsFragment).commit();
                        }
                        return true;
                }
                return false;
            }
        });
     */
    }
}