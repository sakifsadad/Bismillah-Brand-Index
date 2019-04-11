package com.example.bismillahbrandindex;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bismillahbrandindex.Model.AvailableProducts;
import com.example.bismillahbrandindex.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference availableProductsref;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_home);

        availableProductsref = FirebaseDatabase.getInstance().getReference().child("AvailableProducts");

        recyclerView = findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        int numberOfColumns = 3;
        layoutManager = new LinearLayoutManager(this);


        layoutManager = new GridLayoutManager(getApplicationContext(),numberOfColumns, LinearLayoutManager.VERTICAL,false);
//        ((LinearLayoutManager) layoutManager).setOrientation(((LinearLayoutManager) layoutManager).HORIZONTAL);

//        layoutManager = new LinearLayoutManager(getApplicationContext(), numberOfColumns);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<AvailableProducts> options =
                new FirebaseRecyclerOptions.Builder<AvailableProducts>()
                .setQuery(availableProductsref,AvailableProducts.class)
                .build();

        FirebaseRecyclerAdapter<AvailableProducts, ProductViewHolder> adapter =
                new FirebaseRecyclerAdapter<AvailableProducts, ProductViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull final AvailableProducts model) {

                        holder.txtProductName.setText(model.getProductName());
                        holder.txtProductPrice.setText(model.getPrice());
                        Picasso.get().load(model.getImages()).into(holder.imageView);

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(MainActivity.this, ProductDetails.class);
                                intent.putExtra("pid", model.getPid());
                                startActivity(intent);
                            }
                        });


                    }

                    @NonNull
                    @Override
                    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.products_items_layout, viewGroup, false);
                        ProductViewHolder holder = new ProductViewHolder(view);
                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();


    }
}
