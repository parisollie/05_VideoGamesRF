package com.pjff.videogamesrf.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pjff.videogamesrf.data.remote.model.GameDto
import com.pjff.videogamesrf.databinding.GameElementBinding

class GamesAdapter(
    //Le pasamos el arreglo de juegos
    private val games: List<GameDto>,
    //Le pasamos una lmbda cuando el usuario le de click
    private val onGameClicked: (GameDto) -> Unit
): RecyclerView.Adapter<GamesAdapter.ViewHolder>() {

    //Creamos nuestro Viewholder
    class ViewHolder(private val binding: GameElementBinding): RecyclerView.ViewHolder(binding.root){

        val ivThumbnail = binding.ivThumbnail

        //Recibe como parametro
        fun bind(game: GameDto){
            //Rellenar practica 2 y lo vinculamos  *******************
            binding.tvTitle.text = game.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GameElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //Para poder hacer nuestras
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = games.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //La posicion de los elementos
        val game = games[position]

        holder.bind(game)

        //Para cargar las imagenes

        //Con Picasso
        /*Picasso.get()
            .load(game.thumbnail)
            .into(holder.ivThumbnail)*/

        //Con Glide
        Glide.with(holder.itemView.context)
            .load(game.thumbnail)
            .into(holder.ivThumbnail)

        //Procesamiento del clic al elemento
        holder.itemView.setOnClickListener {
            //Le pasamos a la lmbda
            onGameClicked(game)
        }
    }
}