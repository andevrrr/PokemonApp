package com.example.pokemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
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
    private TextView mTypeTextView;
    private TextView mDescriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        mImageView = findViewById(R.id.imageView);
        mNameTextView = findViewById(R.id.nameTextView);
        mTypeTextView = findViewById(R.id.typeTextView);
        // mDescriptionTextView = findViewById(R.id.descriptionTextView);

        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name"); // gets the info from the intent in the Pokemon class
            int id = intent.getIntExtra("id", 0);
            String imageUrl = intent.getStringExtra("imageUrl");
            List<String> types = intent.getStringArrayListExtra("types");

            // Create a comma-separated string of types
            String typeString = TextUtils.join(", ", types);

            Pokemon pokemon = new Pokemon(name, id, imageUrl, types);
            if (pokemon != null) {
                Glide.with(this)
                        .load(pokemon.getImageUrl())
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(mImageView);
                mNameTextView.setText(pokemon.getName());
                mTypeTextView.setText(typeString);
                //mDescriptionTextView.setText(pokemon.getDescription());
            }
        }
    }

}


