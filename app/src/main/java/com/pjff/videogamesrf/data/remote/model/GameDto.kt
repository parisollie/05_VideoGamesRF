package com.pjff.videogamesrf.data.remote.model

import com.google.gson.annotations.SerializedName

//Paso 1.5,Para poder consumir nuestra respuesta

/*
    Mi liGa de mi api:  https://private-3dc8e0-xmen.apiary-mock.com/xmen/xmen_list
    Deben ser identicas a la API,porque sino no funciona.

    Estos datos son los que sacaremos de la api :

    "id": "21357",
    "thumbnail": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT_UcND_8CGXUE0skiE2V73HhmaXOdKw-us3A&usqp=CAU",
    "name": "Jean Gray"
*/
data class GameDto(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("thumbnail")
    var thumbnail: String? = null,
    @SerializedName("name")
    var name: String? = null
)


