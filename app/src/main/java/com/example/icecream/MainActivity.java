package com.example.icecream;

import android.content.Context;
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
    private BottomNavigationView bottomNavigationView;

    public static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        context = getApplicationContext();

        //최초의 Fragment 생성 (HOME)
        bottomNavigationView = findViewById(R.id.nav_view);

        if(homefragment == null){
            homefragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment_activity_main, homefragment).commit();
        }
        //--------------------------------------------------------------------------------//
        //homefragment가 생성되어 있다면 homeframent는 show 나머지 fragment는 hide
        if(homefragment != null){
            getSupportFragmentManager().beginTransaction().show(homefragment).commit();
        }
        if (dashboardFragment != null) {
            getSupportFragmentManager().beginTransaction().hide(dashboardFragment).commit();
        }
        if (myinfoFragment != null) {
            getSupportFragmentManager().beginTransaction().hide(myinfoFragment).commit();
        }
        if (notificationsFragment != null) {
            getSupportFragmentManager().beginTransaction().hide(notificationsFragment).commit();
        }


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.navigation_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_activity_main, homefragment).commit();
                        //homefragment가 비어있으면 생성
                        if(homefragment == null){
                            homefragment = new HomeFragment();
                            getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment_activity_main, homefragment).commit();
                        }
                        //--------------------------------------------------------------------------------//
                        //homefragment가 생성되어 있다면 homeframent는 show 나머지 fragment는 hide
                        if(homefragment != null){
                            getSupportFragmentManager().beginTransaction().show(homefragment).commit();
                        }
                        if (dashboardFragment != null) {
                            getSupportFragmentManager().beginTransaction().hide(dashboardFragment).commit();
                        }
                        if (myinfoFragment != null) {
                            getSupportFragmentManager().beginTransaction().hide(myinfoFragment).commit();
                        }
                        if (notificationsFragment != null) {
                            getSupportFragmentManager().beginTransaction().hide(notificationsFragment).commit();
                        }
                        //--------------------------------------------------------------------------------//
                        break;

                    //이하 homefragment와 같은 구조
                    case R.id.navigation_dashboard:
                        if(dashboardFragment == null){
                            dashboardFragment = new DashboardFragment();
                            getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment_activity_main, dashboardFragment).commit();
                        }
                        //--------------------------------------------------------------------------------//
                        if(homefragment != null){
                            getSupportFragmentManager().beginTransaction().hide(homefragment).commit();
                        }
                        if (dashboardFragment != null) {
                            getSupportFragmentManager().beginTransaction().show(dashboardFragment).commit();
                        }
                        if (myinfoFragment != null) {
                            getSupportFragmentManager().beginTransaction().hide(myinfoFragment).commit();
                        }
                        if (notificationsFragment != null) {
                            getSupportFragmentManager().beginTransaction().hide(notificationsFragment).commit();
                        }
                        //--------------------------------------------------------------------------------//
                        break;

                    case R.id.navigation_notifications:
                        if(notificationsFragment == null){
                            notificationsFragment = new NotificationsFragment();
                            getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment_activity_main, notificationsFragment).commit();
                        }
                        //--------------------------------------------------------------------------------//
                        if(homefragment != null){
                            getSupportFragmentManager().beginTransaction().hide(homefragment).commit();
                        }
                        if (dashboardFragment != null) {
                            getSupportFragmentManager().beginTransaction().hide(dashboardFragment).commit();
                        }
                        if (myinfoFragment != null) {
                            getSupportFragmentManager().beginTransaction().hide(myinfoFragment).commit();
                        }
                        if (notificationsFragment != null) {
                            getSupportFragmentManager().beginTransaction().show(notificationsFragment).commit();
                        }
                        //--------------------------------------------------------------------------------//
                        break;

                    case R.id.navigation_myinfo:
                        if(myinfoFragment == null){
                            myinfoFragment = new MyinfoFragment();
                            getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment_activity_main, myinfoFragment).commit();
                        }
                        //--------------------------------------------------------------------------------//
                        if(homefragment != null){
                            getSupportFragmentManager().beginTransaction().hide(homefragment).commit();
                        }
                        if (dashboardFragment != null) {
                            getSupportFragmentManager().beginTransaction().hide(dashboardFragment).commit();
                        }
                        if (myinfoFragment != null) {
                            getSupportFragmentManager().beginTransaction().show(myinfoFragment).commit();
                        }
                        if (notificationsFragment != null) {
                            getSupportFragmentManager().beginTransaction().hide(notificationsFragment).commit();
                        }
                        //--------------------------------------------------------------------------------//
                        break;
                }
                return true;
            }
        });
    }
}