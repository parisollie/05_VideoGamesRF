package com.pjff.videogamesrf.data.remote

import com.pjff.videogamesrf.data.remote.model.GameDetailDto
import com.pjff.videogamesrf.data.remote.model.GameDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url


interface GamesApi {
    /*Paso 1.9, conexion para el listado
    getGames("cm/games/games_list.php")*/
    @GET
    fun getGames(
        //Le paso el endpoint, le pongo la anotacion URL y me mapea a una variable URL
        @Url url: String?
        //Call es de retrofit que es un List de GameDTO
    ): Call<List<GameDto>>

    /*Paso 1.10
    conexion para los detalles, le debemos pasar el párametro
    Este es el endpoint al que se conecta : "cm/games/game_detail.php"
    */
    @GET("cm/games/game_detail.php")
    fun getGameDetail(
        //Aquí le pasamos el id
        @Query("id") id: String?/*,
        @Query("name") name: String?*/
    ): Call<GameDetailDto>
    /*En caso de que estuviera así ,lo hariamos de esta forma:
    cm/games/game_detail.php?id=21347&name=amaury
    getGameDetail("21347","amaury")*/

    //-------------------Para Apiary--------------------------
    //Paso 1.11,Segunda forma
    @GET("xmen/xmen_list")
    //Call es de retrofit que es un List de GameDTO
    fun getGamesApiary(): Call<List<GameDto>>

    //games/game_detail/21357
    @GET("xmen/xmen_detail/{id}")
    fun getGameDetailApiary(
        @Path("id") id: String?
    ): Call<GameDetailDto>

    /*
    Cuando tengamos 2 datos a llamar
    getGameDetailApiary("21357","Amaury")
    //games/game_detail/21357/Amaury
    * */

    /*
    La liga del profe
    https://private-a649a-games28.apiary-mock.com/games/games_list

    Mi liga
    https://private-3dc8e0-xmen.apiary-mock.com/xmen/xmen_list
    https://private-3dc8e0-xmen.apiary-mock.com/xmen/xmen_detail/21357

    */

}