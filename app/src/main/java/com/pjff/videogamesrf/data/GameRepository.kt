package com.pjff.videogamesrf.data

import com.pjff.videogamesrf.data.remote.GamesApi
import com.pjff.videogamesrf.data.remote.model.GameDetailDto
import com.pjff.videogamesrf.data.remote.model.GameDto
import retrofit2.Call
import retrofit2.Retrofit

//Paso 1.13,Necesita una dependencia de retrofit de tipo retrofit
class GameRepository(private val retrofit: Retrofit) {
    //Propiedad de gamesApi y lo instanciamos y le pasamos como parametro la clase GamesApi
    private val gamesApi: GamesApi = retrofit.create(GamesApi::class.java)

    //Las funciones de mi GameApi
    fun getGames(url: String): Call<List<GameDto>> =
        gamesApi.getGames(url)

    fun getGameDetail(id: String?): Call<GameDetailDto> =
        gamesApi.getGameDetail(id)

    fun getGamesApiary(): Call<List<GameDto>> =
        gamesApi.getGamesApiary()

    fun getGameDetailApiary(id: String?): Call<GameDetailDto> =
        gamesApi.getGameDetailApiary(id)


}