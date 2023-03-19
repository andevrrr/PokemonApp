package com.example.pokemon;


        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.view.View;

        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.toolbox.JsonObjectRequest;
        import com.android.volley.toolbox.Volley;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.List;

public class PokemonActivity extends AppCompatActivity {
    private List<Pokemon> pokemonList = new ArrayList<>(); // List with pokemons
    private RecyclerView recyclerView;
    private PokemonAdapter adapter;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        recyclerView = findViewById(R.id.recycler_view);
        adapter = new PokemonAdapter(pokemonList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // This means that the items in the RecyclerView will be arranged in a linear list

        requestQueue = Volley.newRequestQueue(this);
        String url = "https://pokeapi.co/api/v2/pokemon?limit=151"; // the pokemon data
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray results = response.getJSONArray("results"); // Pokemon's name and url are in the results attribute
                        for (int i = 0; i < results.length(); i++) {
                            JSONObject pokemonObject = results.getJSONObject(i);
                            String name = pokemonObject.getString("name");
                            String urlDetail = pokemonObject.getString("url"); // we get url, which will be used for retrieving a specific info about a chosen pokemon like its type and id

                            JsonObjectRequest requestDetail = new JsonObjectRequest(Request.Method.GET, urlDetail, null,
                                    detailResponse -> {
                                        try {
                                            int id = detailResponse.getInt("id");
                                            String imageUrl = detailResponse.getJSONObject("sprites").getJSONObject("other").getJSONObject("official-artwork").getString("front_default"); // getting the pictures
                                            List<String> types = new ArrayList<>();
                                            JSONArray typesArray = detailResponse.getJSONArray("types");
                                            for (int j = 0; j < typesArray.length(); j++) {
                                                types.add(typesArray.getJSONObject(j).getJSONObject("type").getString("name"));
                                            }
                                            Pokemon pokemon = new Pokemon(name, id, imageUrl, types);
                                            pokemonList.add(pokemon); // adding a pokemon to the arraylist
                                            adapter.notifyDataSetChanged();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    },
                                    error -> {
                                        error.printStackTrace();
                                    }
                            );
                            requestQueue.add(requestDetail);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    error.printStackTrace();
                }
        );
        requestQueue.add(request);
    }

    public void GoToPokeApi(View view){
        String url = "https://pokeapi.co"; // At the time of testing, this web site in the simulator's web browser does not work,
        //  if i change the url to whatever what it works. I guess, it is a problem not with the code, rather with the page and google phones.
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}

