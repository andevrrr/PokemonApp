package com.example.pokemon;


import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {
    private String name;
    private int id;
    private String imageUrl;
    private List<String> types;
    private String description;

    // constrains
    public Pokemon(String name, int id, String imageUrl, List<String> types) {
        this.name = name;
        this.id = id;
        this.imageUrl = imageUrl;
        this.types = types;
        this.description = description;
    }

    // getters

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<String> getTypes() {
        return types;
    }

    public String getDescription() {
        return description;
    } // Description is not going to be showed, because apparently the url i use does not have it,
    // but this "https://pokeapi.co/api/v2/pokemon-species/<pokemon_number>/ url contains some cool info about each pokemon and their description in different languages
    // Unfortunately, i did not use it, as i am running out of time, but i am planning on doing that anyway, even after submitting the project in its current state."


    // This explicit intent is needed to open the "activity_pokemon_detail" activity,
    // and it also transmits information about a Pokemon that was clicked.
    public Intent getIntent(Context context) {
        Intent intent = new Intent(context, PokemonDetailActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("id", id);
        intent.putExtra("imageUrl", imageUrl);
        intent.putStringArrayListExtra("types", new ArrayList<String>(types));
        intent.putExtra("description", description);
        return intent;
    }
}
