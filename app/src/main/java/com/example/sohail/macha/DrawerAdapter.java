package com.example.sohail.macha;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

public class DrawerAdapter extends BaseExpandableListAdapter {

    private Context context;
    private int[] Images;
    private String[] Names;
    Map<String,List<String>> Children;

    public DrawerAdapter(Context context, int[] images, String[] names, Map<String, List<String>> children) {
        this.context = context;
        Images = images;
        Names = names;
        Children = children;
    }

    @Override
    public int getGroupCount() {
        return Names.length;
    }

    @Override
    public int getChildrenCount(int i) {
        return Children.size();
    }

    @Override
    public Object getGroup(int i) {
        return Names[i];
    }

    @Override
    public Object getChild(int i, int i1) {
        return Children.get(Names[i]).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.drawer_group_item,null);
        final ImageView catI = view.findViewById(R.id.catImage);
        TextView catT = view.findViewById(R.id.catText);
        LinearLayout linearLayout = view.findViewById(R.id.layout);
        final int k = i;
//        linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                catI.setBackgroundColor(context.getResources().getColor(R.color.myblue));
//                catI.setImageResource(Images[k+3]);
//                Toast.makeText(context,"clicked",Toast.LENGTH_SHORT).show();
//            }
//        });
        catI.setImageResource(Images[i]);
        catT.setText(getGroup(i).toString());
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.drawer_child_item,null);
        TextView child = view.findViewById(R.id.child);
        child.setText(Children.get(Names[i]).get(i1));
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        super.registerDataSetObserver(observer);
    }

}
