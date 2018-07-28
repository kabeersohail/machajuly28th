package com.example.sohail.macha;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Cart extends AppCompatActivity {
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ConstraintLayout layout = findViewById(R.id.constraintcart);
        View child = getLayoutInflater().inflate(R.layout.cartitem,linearLayout,false);
        layout.addView(child);

    }
}
