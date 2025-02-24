package com.pjff.videogamesrf.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.pjff.videogamesrf.R
import com.pjff.videogamesrf.data.remote.PokemonApi
import com.pjff.videogamesrf.data.remote.model.PokemonDetailDto
import com.pjff.videogamesrf.databinding.ActivityTestPokemonBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*class TestPokemon : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_pokemon)
    }
}*/

//Paso 2.3
class TestPokemon : AppCompatActivity() {

    //paso 2.4
    private lateinit var binding: ActivityTestPokemonBinding

    //Paso 2.6,MI URL BASE
    private val BASE_URL = "https://pokeapi.co/"

    private val LOGTAG = "LOGS"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Paso 2.5
        binding = ActivityTestPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //paso 2.6
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //Paso 2.7,La instanciaos con el retofrit
        val pokemonApi: PokemonApi = retrofit.create(PokemonApi::class.java)

        //Paso 2.8
        val call: Call<PokemonDetailDto> = pokemonApi.getPokemonDetail("151")

        //Paso 2.9
        call.enqueue(object: Callback<PokemonDetailDto> {
            override fun onResponse(
                call: Call<PokemonDetailDto>,
                response: Response<PokemonDetailDto>
            ) {
                Log.d(LOGTAG, "${response.body()?.sprites?.other?.officialArtwork?.frontDefault}")

                Glide.with(this@TestPokemon)
                    .load(response.body()?.sprites?.other?.officialArtwork?.frontDefault)
                    .into(binding.ivPokemon)
            }
            override fun onFailure(call: Call<PokemonDetailDto>, t: Throwable) {
            }
        })
    }
}