package com.pjff.videogamesrf.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pjff.videogamesrf.data.remote.model.GameDto
import com.pjff.videogamesrf.databinding.GameElementBinding

//Paso 1.23, que herede de gameAdapter
class GamesAdapter(
    //Le pasamos la lista de juegos como parámetro
    private val games: List<GameDto>,
    //Le pasamos una lambda cuando el usuario de click en un juego y no regresa nada.
    private val onGameClicked: (GameDto) -> Unit
): RecyclerView.Adapter<GamesAdapter.ViewHolder>() {

    //Paso 1.25.la vista que vamos a inflar y nos regresa un recylverView
    class ViewHolder(private val binding: GameElementBinding): RecyclerView.ViewHolder(binding.root){

        //Paso 1.28
        val ivThumbnail = binding.ivThumbnail
        //paso 1.27
        fun bind(game: GameDto){
            //Vinculamos el title
            binding.tvTitle.text = game.name
        }
    }

    //--------------------------Implementamos los métodos -----------------------------------------

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //Paso 1.26
        val binding = GameElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = games.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Paso 1.29
        val game = games[position]
        //le pasamos el  game
        holder.bind(game)

        /*Con Picasso
        Picasso.get()
            .load(game.thumbnail)
            .into(holder.ivThumbnail)*/

        //Paso 1.31, para poner la imágen con la librería Con Glide
        Glide.with(holder.itemView.context)
            .load(game.thumbnail)
            .into(holder.ivThumbnail)

        //Paso 1.30,Procesamiento del clic al elemento
        holder.itemView.setOnClickListener {
            // le pasmaos el juego
            onGameClicked(game)
        }
    }
}