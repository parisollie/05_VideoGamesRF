package com.pjff.videogamesrf.data.remote

import com.pjff.videogamesrf.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Paso 1.12, para que me regres un objeto retofit
class RetrofitHelper {
    fun getRetrofit(): Retrofit {
        //me da información de como me da la conexión.
        //Paso 1.18, seteamos
        val interceptor = HttpLoggingInterceptor().apply {
            //Para que nos de el servidor en el cuerpo de la respuesta
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().apply {
            //le pasamos el interceptor
            addInterceptor(interceptor)
        }.build()

        //Me regresa un retrofit builder
        return Retrofit.Builder()
            /*para que se genere una url base y le mandamos la url base de
            nuestro archivo de contantes*/
            .baseUrl(Constants.BASE_URL)
            //Paso 1.19
            //.client(client)
            //El convertidor que usara para poder pasearla Gson
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
