package com.example.sohail.macha;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> implements AdapterView.OnItemClickListener{
    private Context context;
    private String[] imagepath,productname;
    private int[] productprice;

    public RecyclerViewAdapter() {
    }

    public RecyclerViewAdapter(Context context, String[] imagepath, String[] productname, int[] productprice) {
        this.imagepath = imagepath;
        this.context = context;
        this.productname = productname;
        this.productprice = productprice;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.single_item,viewGroup,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, final int i) {

        Picasso.get().load(imagepath[i]).into(recyclerViewHolder.imageview);
        recyclerViewHolder.ProductPrice.setText(String.valueOf(productprice[i]));
        recyclerViewHolder.ProductName.setText(productname[i]);
        recyclerViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,""+i, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,Details1.class);
                intent.putExtra("imageUrl",imagepath[i]);
                intent.putExtra("productName", String.valueOf(productname[i]));
                intent.putExtra("productPrice", String.valueOf(productprice[i]));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productname.length;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(context,i, Toast.LENGTH_SHORT).show();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout linearLayout;
        private ImageView imageview;
        private TextView ProductName,ProductPrice;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.parent_layout);
            imageview = itemView.findViewById(R.id.image_view);
            imageview.setSelected(true);
            ProductName = itemView.findViewById(R.id.product_name);
            ProductPrice = itemView.findViewById(R.id.product_price);
        }
    }
}
