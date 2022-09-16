package com.github.rindh.pokeapi;

import com.github.rindh.pokeapi.models.Pokemon;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PokeApi {
    private static final Logger LOGGER = Logger.getLogger( PokeApi.class.getName() );

    private final String POKE_API_URL = "https://pokeapi.co/api/v2/pokemon/";

    private HttpURLConnection connection(String pokeUrl){
        try {
            URL url = new URL(pokeUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            return connection;
        } catch (IOException e) {
            LOGGER.log( Level.SEVERE, "Can't reach the pokeapi", e);
            e.printStackTrace();
        }
        return null;
    }

    public Pokemon getPokemon(String name){
        Pokemon pokemon = new Pokemon();
        Gson json = new Gson();
        try {
            HttpURLConnection connection = connection(POKE_API_URL + name.trim().toLowerCase(Locale.ROOT));
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(connection).getInputStream()));
            String res = br.readLine();
            pokemon = json.fromJson(res, Pokemon.class);
        } catch (IOException e) {
            LOGGER.log( Level.SEVERE, "Could not find pokemon with the name: " + name, e);
            e.printStackTrace();
        }
        return pokemon;
    }
}
