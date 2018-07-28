package com.example.sohail.macha;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;

import java.util.ArrayList;
import java.util.List;

public class InfiniteCycleViewPager extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Button JavaFinish;
    List<Integer> lstImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infinite_cycle_view_pager);

        drawerLayout = findViewById(R.id.drawermaccha);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        JavaFinish = findViewById(R.id.XmlFinish);
        JavaFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InfiniteCycleViewPager.this,RecyclerActivity.class));
            }
        });
        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initData();

        HorizontalInfiniteCycleViewPager pager = (HorizontalInfiniteCycleViewPager)findViewById(R.id.horizontal_cycle);
        MyAdapter adapter = new MyAdapter(lstImages,getBaseContext());
        pager.setAdapter(adapter);

    }

    private void initData() {
        lstImages.add(R.drawable.fbg1);
        lstImages.add(R.drawable.fbg2);
        lstImages.add(R.drawable.fbg3);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.signoutdrawar){
                AuthUI.getInstance().signOut(this);
                startActivity(new Intent(this,MainActivity.class));
            }

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
