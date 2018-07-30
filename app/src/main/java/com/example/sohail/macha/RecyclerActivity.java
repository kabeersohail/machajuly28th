package com.example.sohail.macha;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecyclerActivity extends AppCompatActivity {

    String[] productname;
    int[] productprice;
    String[] imagepath;

    int[] Images = {
            R.drawable.manblack,
            R.drawable.womenblack,
            R.drawable.childrenblack,
            R.drawable.manafter,
            R.drawable.womenafter,
            R.drawable.boyafter
    };

    String[] Names = {
            "Men",
            "Women",
            "Kids"
    };

    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView recyclerView;
    List<String> images;

    Map<String,List<String>> Children;
    List<String> Men = new ArrayList<>();
    List<String> Women = new ArrayList<>();
    List<String> Kids = new ArrayList<>();
    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        recyclerView = findViewById(R.id.recycler_view);

        Men.add("Shirts");
        Men.add("Trousers");
        Men.add("FootWear");
        Men.add("Summer");

        Women.add("Tops");
        Women.add("Jeans");
        Women.add("Footwear");

        Kids.add("Shirts");
        Kids.add("FootWear");
        Kids.add("Jeans");
        Kids.add("Accessories");
        Kids.add("Toys");

        Children = new HashMap<>();

        Children.put(Names[0],Men);
        Children.put(Names[1],Women);
        Children.put(Names[2],Kids);

        expandableListView = findViewById(R.id.ExpandableListView);
        expandableListAdapter = new DrawerAdapter(this,Images,Names,Children);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                Images[0] = R.drawable.manblack;
                Images[1] = R.drawable.womenblack;
                Images[2] = R.drawable.childrenblack;
                Images[3] = R.drawable.manafter;
                Images[4] = R.drawable.womenafter;
                Images[5] = R.drawable.boyafter;
                Images[i] = Images[i+3];
                view.setBackgroundColor(RecyclerActivity.this.getResources().getColor(R.color.myblue));
                final BaseExpandableListAdapter adapter = (BaseExpandableListAdapter) expandableListAdapter;
                adapter.notifyDataSetChanged();
                Toast.makeText(RecyclerActivity.this,"Group "+i,Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if(groupPosition != previousGroup)
                    expandableListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });



        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Products");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                ArrayList<String> images;
//                images = new ArrayList<String>();
                int NumberOfChildren = (int) dataSnapshot.getChildrenCount();
                int i =0;
                int size = (int) dataSnapshot.getChildrenCount();
                productprice = new int[size];
                productname = new String[size];
                imagepath = new String[size];
                Iterable<DataSnapshot> children =  dataSnapshot.getChildren();
                for (DataSnapshot child: children) {

                    productname[i] = child.getKey();
                    String p = child.child("Price").getValue().toString();
                    productprice[i] = Integer.valueOf(p);
                    imagepath[i] = child.child("Images").child(String.valueOf(0)).getValue().toString();
                    i++;
//                    images = (ArrayList<String>) child.child("Images").getValue();
//                    int size = images.size();
//                    String[] img;
//                    img = new String[size];
//                    for(i =0;i<size;i++){
//                        img[i] = images.get(i);
//                    }





//                    img[0] = images.get(0);
                    recyclerViewAdapter = new RecyclerViewAdapter(RecyclerActivity.this,imagepath,productname,productprice);
                    layoutManager = new GridLayoutManager(RecyclerActivity.this,2);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(recyclerViewAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                final int k =i;
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Products");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int o=0;
                        int size = (int) dataSnapshot.getChildrenCount();
                        Iterable<DataSnapshot> children =  dataSnapshot.getChildren();
                        for (DataSnapshot child: children) {
                            String Tags = child.child("Tags").getValue().toString();
                            if(Tags.equals(Names[k])){
                                productname[o] = child.getKey();
                                String p = child.child("Price").getValue().toString();
                                productprice[o] = Integer.valueOf(p);
                                imagepath[o] = child.child("Images").child(String.valueOf(0)).getValue().toString();
                                o++;
//                                recyclerViewAdapter.notifyDataSetChanged();
                            }
                        }

                        String[] PN;
                        int[] PP;
                        String[] IP;
                        PP = new int[o];
                        PN = new String[o];
                        IP = new String[o];

                        for(int i=0;i<o;i++){
                            PP[i] = productprice[i];
                            PN[i] = productname[i];
                            IP[i] = imagepath[i];
                        }
                        recyclerView.setAdapter(null);
                        recyclerViewAdapter = new RecyclerViewAdapter(RecyclerActivity.this,IP,PN,PP);
                        layoutManager = new GridLayoutManager(RecyclerActivity.this,2);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(recyclerViewAdapter);
//                        recyclerViewAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Toast.makeText(RecyclerActivity.this,Names[i]+","+Children.get(Names[i]).get(i1),Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }



}
