package com.pjff.videogamesrf.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.pjff.videogamesrf.R
import com.pjff.videogamesrf.data.GameRepository
import com.pjff.videogamesrf.data.remote.RetrofitHelper
import com.pjff.videogamesrf.data.remote.model.GameDto
import com.pjff.videogamesrf.databinding.ActivityMainBinding
import com.pjff.videogamesrf.ui.fragments.GamesListFragment
import com.pjff.videogamesrf.util.Constants
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

//App xmen-checando, trabajremos con este
class MainActivity : AppCompatActivity() {

    //Paso 1.1
    private lateinit var binding: ActivityMainBinding
    /*
    //Paso 1.13
    private lateinit var repository: GameRepository
    private lateinit var retrofit: Retrofit*/

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.SplashTheme)
        super.onCreate(savedInstanceState)
        //Paso 1.2
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, GamesListFragment())
                .commit()
        }

        //Paso 1.14,Instanciamos el Retrofithelper
        //retrofit = RetrofitHelper().getRetrofit()
        //paso 1.15
        //repository = GameRepository(retrofit)

        //Paso 1.16,Mandamos una Corrutina
       /*lifecycleScope.launch {
           //Para el listado de juegos y le mandamos el párametro
           val call: Call<List<GameDto>> = repository.getGames("cm/games/games_list.php")

           //Paso 1.17,La metemos a la cola para llamarla y le pasamos una interfaz como un object
           call.enqueue(object: Callback<List<GameDto>> {
               //Le ponemos los 2 miembros: onResponse y OnFailure

               override fun onResponse(
                   call: Call<List<GameDto>>,
                   response: Response<List<GameDto>>
               ) {
                   Log.d(Constants.LOGTAG, "Respuesta del servidor ${response.body()}")
               }

               override fun onFailure(call: Call<List<GameDto>>, t: Throwable) {
                   //Manejo del error
                   Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
               }
           })
       }*/
    }
}