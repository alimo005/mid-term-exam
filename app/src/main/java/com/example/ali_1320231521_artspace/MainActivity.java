package com.example.ali_1320231521_artspace;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView ImageCounter, TitleImage, ImageArt;
    private ImageView Image;
    private Button BtnPrevious, BtnNext;

    private int CurrentIndex = 0;

    private final int[] Images = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6
    };

    private final String[] Titles = {
            "The Starry Night",
            "The Great Wave off Kanagawa",
            "Girl with a Pearl Earring",
            "The Persistence of Memory",
            "The Scream",
            "Mona Lisa"
    };


    private final String[] Artists = {
            "Vincent van Gogh",
            "Katsushika Hokusai",
            "Johannes Vermeer",
            "Salvador Dali",
            "Edvard Munch",
            "Leonardo da Vinci"
    };

    // Creation Years Array
    private final String[] mYears = {
            "1889",
            "1831",
            "1665",
            "1931",
            "1893",
            "1503-1506"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageCounter = findViewById(R.id.image_counter);
        Image = findViewById(R.id.image);
        TitleImage = findViewById(R.id.titel_image);
        ImageArt = findViewById(R.id.image_art);

        BtnPrevious = findViewById(R.id.btnPrevious);
        BtnNext = findViewById(R.id.btnNext);

        if (savedInstanceState != null) {
            CurrentIndex = savedInstanceState.getInt("art_index", 0);
        }

        updateArtwork();



        BtnNext.setOnClickListener(v -> {

            if (CurrentIndex < Images.length - 1) {
                CurrentIndex++;
                updateArtwork();
            }

        });



        BtnPrevious.setOnClickListener(v -> {

            if (CurrentIndex > 0) {
                CurrentIndex--;
                updateArtwork();
            }

        });


// Click on Artwork Image
        Image.setOnClickListener(v -> {

            // Create Explicit Intent
            Intent intent = new Intent(
                    MainActivity.this,
                    ArtworkDetailsActivity.class
            );

            // Send Artwork Information
            intent.putExtra("title", Titles[CurrentIndex]);

            intent.putExtra("artist", Artists[CurrentIndex]);

            intent.putExtra("year", mYears[CurrentIndex]);

            // Send Artwork Image
            intent.putExtra("imageResId", Images[CurrentIndex]);

            // Open Second Activity
            startActivity(intent);

        });


    }


    private void updateArtwork() {

        Image.setImageResource(Images[CurrentIndex]);

        TitleImage.setText(Titles[CurrentIndex]);

        ImageArt.setText(
                Artists[CurrentIndex] + " (" +
                        mYears[CurrentIndex] + ")"
        );

        ImageCounter.setText(
                (CurrentIndex + 1) + " / " + Images.length
        );

        // Disable and Enable Buttons
        BtnPrevious.setEnabled(CurrentIndex > 0);

        BtnNext.setEnabled(CurrentIndex < Images.length - 1);

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("art_index", CurrentIndex);
    }

}
