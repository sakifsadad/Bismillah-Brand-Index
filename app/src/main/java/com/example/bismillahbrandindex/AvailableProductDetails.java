package com.example.bismillahbrandindex;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bismillahbrandindex.Model.AvailableProducts;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AvailableProductDetails extends YouTubeBaseActivity {

    private TextView pname, pprice, pdisplay, pcolor, psim, pram, prom, pcamera, pbattery, pprocessor, pnetwork, pfingerprint, pothers;
    private String productID = "";
    YouTubePlayerView youTubePlayerView;
    private String youtubeVideoLink;
    private ImageView call, messenger;

    private ViewPager productImagesViewPager;
    private TabLayout viewpagerIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_product_details);
        //show loader
        productID = getIntent().getStringExtra("pid");


        pname = (TextView) findViewById(R.id.product_name);
        pprice = (TextView) findViewById(R.id.price);
        pdisplay = (TextView) findViewById(R.id.display);
        pcolor = (TextView) findViewById(R.id.color);
        pram = (TextView) findViewById(R.id.ram);
        prom = (TextView) findViewById(R.id.rom);
        pcamera = (TextView) findViewById(R.id.camera);
        pbattery = (TextView) findViewById(R.id.battery);
        pprocessor = (TextView) findViewById(R.id.processor);
        pnetwork = (TextView) findViewById(R.id.network);
        pfingerprint = (TextView) findViewById(R.id.fingerprint);
        pothers = (TextView) findViewById(R.id.others);
        psim = (TextView) findViewById(R.id.sim);

        call = (ImageView) findViewById(R.id.call_button);
        messenger = (ImageView) findViewById(R.id.messenger_button);

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.player_view);
        getProductDetails(productID);
//        initiateYoutube();

        productImagesViewPager = findViewById(R.id.product_images);
        viewpagerIndicator = findViewById(R.id.viewpager_indicator);


        List<Integer> productImages = new ArrayList<>();
        productImages.add(R.drawable.dil);
        productImages.add(R.drawable.dill);
        productImages.add(R.drawable.dill);

        ProductImagesAdapter productImagesAdapter = new ProductImagesAdapter(productImages);
        productImagesViewPager.setAdapter(productImagesAdapter);

        viewpagerIndicator.setupWithViewPager(productImagesViewPager, true);


        // calling activity///////////////////////////////////////////

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01754941577")));
            }
        });
        ///////////////////////////////////////////////////////////////

        // messenger activity//////////////////////////////////////////

        messenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.messenger.com/t/mddurotto")));

            }
        });
        ////////////////////////////////////////////////////////////////

    }

    private void initiateYoutube() {
        youTubePlayerView.initialize(YouTubeAPI.API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                //hide loader
                youTubePlayer.loadVideo(youtubeVideoLink);

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }

    private void getProductDetails(String productID) {

        final DatabaseReference availableProductsRef = FirebaseDatabase.getInstance().getReference().child("Available Products");

        availableProductsRef.child(productID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    AvailableProducts availableProducts = dataSnapshot.getValue(AvailableProducts.class);

                    pname.setText(availableProducts.getProductName());
                    pprice.setText(availableProducts.getPrice() + " Tk.");
                    pdisplay.setText(availableProducts.getDisplay());
                    pcolor.setText(availableProducts.getColor());
                    pram.setText(availableProducts.getRAM());
                    prom.setText(availableProducts.getROM());
                    pcamera.setText(availableProducts.getCamera());
                    pbattery.setText(availableProducts.getBattery());
                    pprocessor.setText(availableProducts.getProcessor());
                    pnetwork.setText(availableProducts.getNetwork());
                    pfingerprint.setText(availableProducts.getFingerprint());
                    pothers.setText(availableProducts.getOthers());
                    psim.setText(availableProducts.getSim());

                    youtubeVideoLink = availableProducts.getYoutubeVideoLink();
                    initiateYoutube();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });

    }
}
