package com.pjff.videogamesrf.application

import android.app.Application
import com.pjff.videogamesrf.data.GameRepository
import com.pjff.videogamesrf.data.remote.RetrofitHelper

//Paso 1.3,Instancia global de la aplicación y que herede de application.
//Paso 1.4, esta en el manifest

class VideoGamesRFApp: Application() {
    //Paso 1.20, creamos una instancia de retrofit
    private val retrofit by lazy{
        RetrofitHelper().getRetrofit()
    }

    val repository by lazy{
        GameRepository(retrofit)
    }

}