
package com.example.ali_1320231521_artspace;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ArtworkDetailsActivity extends AppCompatActivity {

    private ImageView mArtworkImage;
    private TextView mTitle, mArtist, mYear;
    private Button mBtnVisit, mBtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artwork_details);

        mArtworkImage = findViewById(R.id.imageView);
        mTitle = findViewById(R.id.name_textView);
        mArtist = findViewById(R.id.artist_name_textView);
        mYear = findViewById(R.id.Year_textView);

        mBtnVisit = findViewById(R.id.website_button);
        mBtnBack = findViewById(R.id.back_button);

        Intent intent = getIntent();

        String title = intent.getStringExtra("title");
        String artist = intent.getStringExtra("artist");
        String year = intent.getStringExtra("year");

        int imageResId = intent.getIntExtra(
                "imageResId",
                R.drawable.image1
        );

        mTitle.setText(title);
        mArtist.setText("Artist: " + artist);
        mYear.setText("Year: " + year);

        mArtworkImage.setImageResource(imageResId);

        mBtnBack.setOnClickListener(v -> {
            finish();
        });

        mBtnVisit.setOnClickListener(v -> {

            String url = "https://en.wikipedia.org/w/index.php?search="
                    + Uri.encode(title);

            Intent browserIntent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(url)
            );

            startActivity(browserIntent);
        });

    }
}
