package com.pjff.videogamesrf.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.pjff.videogamesrf.R
import com.pjff.videogamesrf.application.VideoGamesRFApp
import com.pjff.videogamesrf.data.GameRepository
import com.pjff.videogamesrf.data.remote.model.GameDto
import com.pjff.videogamesrf.databinding.FragmentGamesListBinding
import com.pjff.videogamesrf.ui.adapters.GamesAdapter
import com.pjff.videogamesrf.util.Constants
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GamesListFragment : Fragment() {

    //paso 1.32
    private var _binding: FragmentGamesListBinding? = null
    private val binding get() = _binding!!

    //paso 1.34
    private lateinit var repository: GameRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    //paso 1.35, cuando la vista ya se creo
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Paso 1.33, Inflate the layout for this fragment
        _binding = FragmentGamesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //paso 1.36, ya tengo mi repositorio.
        repository = (requireActivity().application as VideoGamesRFApp).repository

        //paso 1.37, lanzamos la corrutina.
        lifecycleScope.launch {
            //val call: Call<List<GameDto>> = repository.getGames("cm/games/games_list.php")
            val call: Call<List<GameDto>> = repository.getGamesApiary()

            //Paso 1.38
            call.enqueue(object: Callback<List<GameDto>>{
                override fun onResponse(
                    call: Call<List<GameDto>>,
                    response: Response<List<GameDto>>
                ) {
                    //Implementamos los miembros
                    binding.pbLoading.visibility = View.GONE
                    //Paso 1.40
                    Log.d(Constants.LOGTAG, "Respuesta del servidor: ${response.body()}")

                    //paso 1.41
                    response.body()?.let{ games ->
                        binding.rvGames.apply {
                            layoutManager = LinearLayoutManager(requireContext())
                            //Le pasamos el listado de juegos.
                            adapter = GamesAdapter(games){ game ->
                                game.id?.let { id ->
                                    //Aquí va el código para la operación para ver los detalles
                                    requireActivity().supportFragmentManager.beginTransaction()
                                        //Paso 1.52, funcion de reemplazo
                                        .replace(R.id.fragment_container, GameDetailFragment.newInstance(id))
                                        //Le pasamos null ,para que no nos de problemas,
                                        .addToBackStack(null)
                                        //commit para que se reemplace el fragment
                                        .commit()
                                }
                            }
                        }
                    }
                }

                //Paso 1.39
                override fun onFailure(call: Call<List<GameDto>>, t: Throwable) {
                    Log.d(Constants.LOGTAG, "Error: ${t.message}")

                    Toast.makeText(requireActivity(), "No hay conexión", Toast.LENGTH_SHORT).show()
                     //para que quite el loading
                    binding.pbLoading.visibility = View.GONE

                }

            })
        }

    }

    //Paso 1.34
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}