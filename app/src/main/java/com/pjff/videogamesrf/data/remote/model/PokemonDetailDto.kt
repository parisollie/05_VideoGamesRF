package com.pjff.videogamesrf.data.remote.model

import com.google.gson.annotations.SerializedName

data class PokemonDetailDto (
    //Las cosas que quiero de la pokeapi
    @SerializedName("sprites")
    var sprites: Sprites? = null
)

data class Sprites (
    @SerializedName("other")
    var other: Other? = null

)

data class Other(
    @SerializedName("official-artwork")
    var officialArtwork: OfficialArtwork? = null
)

data class OfficialArtwork(
    @SerializedName("front_default")
    var frontDefault: String? = null
)