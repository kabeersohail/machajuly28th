package com.example.sohail.macha.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sohail.macha.Model.Model;
import com.example.sohail.macha.R;
import com.github.chrisbanes.photoview.PhotoView;
import com.hold1.pagertabsindicator.PagerTabsIndicator;
import com.hold1.pagertabsindicator.TabViewProvider;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomViewPagerAdapter extends PagerAdapter implements TabViewProvider.ImageProvider {

    List<Model> models;
    Context context;
    PagerTabsIndicator pagerTabsIndicator;
    public CustomViewPagerAdapter(List<Model> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page "+(position+1);
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewGroup)container).removeView((View)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View ItemView = inflater.inflate(R.layout.layout_item,container,false);
        TextView textView = ItemView.findViewById(R.id.text);
//        ImageView imageView = ItemView.findViewById(R.id.image);
//        imageView.setImageResource(models.get(position).getId());
        PhotoView photoView = ItemView.findViewById(R.id.image);
//        photoView.setImageResource(models.get(position).getId());
        Picasso.get().load(models.get(position).getUrl()).into(photoView);
        textView.setText(models.get(position).getTitle());
        container.addView(ItemView);
        return ItemView;
    }

    @Override
    public Uri getImageUri(int i) {
        Uri uri = Uri.parse(models.get(i).getUrl());
        return null;
    }

    @Override
    public int getImageResourceId(int i) {
        return models.get(i).getId();
    }
}
