package com.pjff.videogamesrf.data.remote.model

import com.google.gson.annotations.SerializedName

//Para poder consumir nuestra respuesta
data class GameDto(

    //Escribimos tal cual esta en nuestra api
    //El seriallizaed , tiene la funcion en donde le especifico
    //el nombre tal cual quiero leer
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("thumbnail")
    var thumbnail: String? = null,
    @SerializedName("title")
    var title: String? = null

//Cosas para mi practica
)
