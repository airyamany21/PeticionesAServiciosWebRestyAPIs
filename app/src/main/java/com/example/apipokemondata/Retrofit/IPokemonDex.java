package com.example.apipokemondata.Retrofit;

import com.example.apipokemondata.Model.Pokedex;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IPokemonDex {

    @GET("pokedex.json")
    Observable<Pokedex> getListPokemon();

}
