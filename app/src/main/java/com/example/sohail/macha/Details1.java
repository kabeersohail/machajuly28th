package com.example.sohail.macha;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sohail.macha.Adapter.CustomViewPagerAdapter;
import com.example.sohail.macha.Model.Model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hold1.pagertabsindicator.PagerTabsIndicator;

import java.util.ArrayList;
import java.util.List;

public class Details1 extends AppCompatActivity {

    public static String Url,name,price;

    ViewPager viewPager;
    PagerTabsIndicator pagerTabsIndicator;
    List<Model> models = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details1);
        getExtras();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Products");
        databaseReference.child(name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Model model;
                Iterable<DataSnapshot> children =  dataSnapshot.child("Images").getChildren();
                for (DataSnapshot child: children) {
                    String url = child.getValue().toString();
                    model = new Model("Page",R.drawable.background,url);
                    models.add(model);



                }
                viewPager = findViewById(R.id.ViewPager);
                pagerTabsIndicator = findViewById(R.id.tabsIndicator);
                viewPager.setAdapter(new CustomViewPagerAdapter(models,Details1.this));
                pagerTabsIndicator.setViewPager(viewPager);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        initModel();
//        viewPager = findViewById(R.id.ViewPager);
//        pagerTabsIndicator = findViewById(R.id.tabsIndicator);
//        viewPager.setAdapter(new CustomViewPagerAdapter(models,this));
//        pagerTabsIndicator.setViewPager(viewPager);

    }

//    private void initModel() {
////        for(int i=0;i<4;i++){
////            models.add(new Model("Page "+(i+1),0));
////        }
//        Model model;
//        model = new Model("Page 1",R.drawable.sandle1);
//        models.add(model);
//        model = new Model("Page 2",R.drawable.sandle2);
//        models.add(model);
//        model = new Model("Page 3",R.drawable.sandle3);
//        models.add(model);
//        model = new Model("Page 4",R.drawable.sandle4);
//        models.add(model);
//    }

    public void getExtras(){

        if(getIntent().hasExtra("imageUrl") && getIntent().hasExtra("productName") && getIntent().hasExtra("productPrice")){
            Url = getIntent().getStringExtra("imageUrl");
            name = getIntent().getStringExtra("productName");
            price = getIntent().getStringExtra("productPrice");
        }
    }

}
