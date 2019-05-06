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
import android.widget.Toast;

import com.example.bismillahbrandindex.Model.AvailableProducts;
import com.example.bismillahbrandindex.Model.Images;
import com.example.bismillahbrandindex.Model.UpcomingProducts;
import com.example.bismillahbrandindex.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class AllUpcomingProducts extends AppCompatActivity {

    private DatabaseReference upcomingProductsref;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_products);
        setTitle("Upcoming Products");



            upcomingProductsref = FirebaseDatabase.getInstance().getReference().child("Upcoming Products");

            recyclerView = findViewById(R.id.recycler_menu1);
            recyclerView.setHasFixedSize(true);
            int numberOfColumns = 2;
            layoutManager = new LinearLayoutManager(this);


            layoutManager = new GridLayoutManager(getApplicationContext(),numberOfColumns, LinearLayoutManager.VERTICAL,false);
//        ((LinearLayoutManager) layoutManager).setOrientation(((LinearLayoutManager) layoutManager).HORIZONTAL);

//        layoutManager = new LinearLayoutManager(getApplicationContext(), numberOfColumns);
            recyclerView.setLayoutManager(layoutManager);


        }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<UpcomingProducts> options=
                new FirebaseRecyclerOptions.Builder<UpcomingProducts>()
                .setQuery(upcomingProductsref, UpcomingProducts.class)
                .build();

        FirebaseRecyclerAdapter <UpcomingProducts, ProductViewHolder> adapter =
                new FirebaseRecyclerAdapter<UpcomingProducts, ProductViewHolder>(options){
                    @Override
                    protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull final UpcomingProducts model) {

                        holder.txtProductName.setText(model.getProductName());
                        holder.txtProductPrice.setText(model.getPrice() + " Tk.");
                        Picasso.get().load(model.getImageUris().getImages().get(0)).into(holder.imageView);
//                        Picasso.get().load(getThumbnailImage(model)).into(holder.imageView);

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (AppStatus.getInstance(AllUpcomingProducts.this).isOnline()){
                                    Intent intent = new Intent(AllUpcomingProducts.this, UpcomingProductDetails.class);
                                intent.putExtra("pid", model.getPid());
                                startActivity(intent);
                            }
                            else {
                                    Toast.makeText(AllUpcomingProducts.this, "No Internet Connection!", Toast.LENGTH_SHORT).show();
                                }
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

    private String getThumbnailImage(AvailableProducts model) {
            Images imageUris = model.getImageUris();
            String id = imageUris == null ? model.getImages() : imageUris.getImages().get(0);
            return id;
        }
    }
