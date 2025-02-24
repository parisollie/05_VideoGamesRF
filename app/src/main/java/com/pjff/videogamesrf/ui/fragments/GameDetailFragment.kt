package com.pjff.videogamesrf.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.pjff.videogamesrf.R
import com.pjff.videogamesrf.application.VideoGamesRFApp
import com.pjff.videogamesrf.data.GameRepository
import com.pjff.videogamesrf.data.remote.model.GameDetailDto
import com.pjff.videogamesrf.databinding.FragmentGameDetailBinding
import com.pjff.videogamesrf.util.Constants
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//Paso 1.45
private const val GAME_ID = "game_id"


class GameDetailFragment : Fragment() {

    //Paso 1.46
    private var gameId: String? = null

    //Paso 1.48
    private var _binding: FragmentGameDetailBinding? = null
    private val binding get() = _binding!!

    //Paso 1.51
    private lateinit var repository: GameRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { args ->
            //Paso 1.47
            gameId = args.getString(GAME_ID)
            Log.d(Constants.LOGTAG, "Id recibido: $gameId")

            //Paso 1.53, para que me pase la instancia del repositorio
            repository = (requireActivity().application as VideoGamesRFApp).repository

            //Paso 1.54,ponemos la corrutina.
            lifecycleScope.launch {

                gameId?.let { id ->
                    //val call: Call<GameDetailDto> = repository.getGameDetail(id)
                    //paso 1.55
                    val call: Call<GameDetailDto> = repository.getGameDetailApiary(id)

                    //Paso 1.56
                    call.enqueue(object: Callback<GameDetailDto>{
                        override fun onResponse(
                            call: Call<GameDetailDto>,
                            response: Response<GameDetailDto>
                        ) {
                            binding.apply {
                                //paso 1.58

                                pbLoading.visibility = View.GONE

                                tvTitle.text = response.body()?.name

                                tvLongDesc.text = response.body()?.longDesc

                                tvLevel.text = response.body()?.levelX

                                tvFirstTime.text = response.body()?.first_Time

                                tvPower.text = response.body()?.power

                                Glide.with(requireContext())
                                    .load(response.body()?.image)
                                    .into(ivImage)
                            }
                        }

                        override fun onFailure(call: Call<GameDetailDto>, t: Throwable) {
                            //Paso 1.57
                            binding.pbLoading.visibility = View.GONE
                            Toast.makeText(requireActivity(), "No hay conexión", Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Paso 1.49,inflamos la vista
        _binding = FragmentGameDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    //Paso 1.50
    override fun onDestroy() {
        super.onDestroy()
        //para evitar fugas de memoria.
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(gameId: String) =
            GameDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(GAME_ID, gameId)
                }
            }
    }
}