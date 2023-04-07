package com.example.pokemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

// This activity is shown when a certain pokemon is clicked to provide its detail information.
// Actually there is no detailed info provided,
// because as i said earlier in the "pokemon" file that the detailed info is in the different url,
// that has to be fetched too to see the description of the pokemon.
public class PokemonDetailActivity extends AppCompatActivity {

    private ImageView mImageView;
    private TextView mNameTextView;
    private ProgressBar mdeterminateBar1, mdeterminateBar2, mdeterminateBar3, mdeterminateBar4, mdeterminateBar5, mdeterminateBar6;
    private TextView mWeightTextnumber;
    private TextView mHeightTextnumber;
    private TextView mTypeTextView;
    private TextView mDescriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        mImageView = findViewById(R.id.imageView);
        mNameTextView = findViewById(R.id.nameTextView);
        mWeightTextnumber = findViewById(R.id.weightTextnumber);
        mHeightTextnumber = findViewById(R.id.heightTextnumber);
        ChipGroup mTypeChipGroup = findViewById(R.id.typeChipGroup);
        mdeterminateBar1  = findViewById(R.id.determinateBar1);
        mdeterminateBar2  = findViewById(R.id.determinateBar2);
        mdeterminateBar3  = findViewById(R.id.determinateBar3);
        mdeterminateBar4  = findViewById(R.id.determinateBar4);
        mdeterminateBar5  = findViewById(R.id.determinateBar5);
        mdeterminateBar6  = findViewById(R.id.determinateBar6);
        // mDescriptionTextView = findViewById(R.id.descriptionTextView);

        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name"); // gets the info from the intent in the Pokemon class
            int id = intent.getIntExtra("id", 0);
            String imageUrl = intent.getStringExtra("imageUrl");
            List<String> types = intent.getStringArrayListExtra("types");
            //List<String> stats = intent.getStringArrayListExtra("stats");
            String weight = intent.getStringExtra("weight");
            String height = intent.getStringExtra("height");
            String stat0 = intent.getStringExtra("Stat0");
            String stat1 = intent.getStringExtra("Stat1");
            String stat2 = intent.getStringExtra("Stat2");
            String stat3 = intent.getStringExtra("Stat3");
            String stat4 = intent.getStringExtra("Stat4");
            String stat5 = intent.getStringExtra("Stat5");

            // Create a comma-separated string of types
            //String typeString2 = TextUtils.join(", ", stats);

            Pokemon pokemon = new Pokemon(name, id, imageUrl, types, weight, height, stat0, stat1, stat2, stat3, stat4, stat5);
            if (pokemon != null) {
                Glide.with(this)
                        .load(pokemon.getImageUrl())
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(mImageView);
                mNameTextView.setText(name);

                for (String type : types) {
                    Chip chip = new Chip(this);
                    chip.setText(type);
                    mTypeChipGroup.addView(chip);
                }

                mWeightTextnumber.setText(weight);
                mHeightTextnumber.setText(height);
                mdeterminateBar1.setProgress(Integer.parseInt(stat0));
                mdeterminateBar2.setProgress(Integer.parseInt(stat1));
                mdeterminateBar3.setProgress(Integer.parseInt(stat2));
                mdeterminateBar4.setProgress(Integer.parseInt(stat3));
                mdeterminateBar5.setProgress(Integer.parseInt(stat4));
                mdeterminateBar6.setProgress(Integer.parseInt(stat5));


               // mTypeTextView.setText(typeString2);
                //mDescriptionTextView.setText(pokemon.getDescription());
            }
        }
    }

}


