package com.example.pokemon;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    // By clicking the button, PokemonActivity will open
    public void GoToPokemonActivity(View view){
        Intent openPokemonActivity = new Intent(this, PokemonActivity.class);
        startActivity(openPokemonActivity);
    }




}