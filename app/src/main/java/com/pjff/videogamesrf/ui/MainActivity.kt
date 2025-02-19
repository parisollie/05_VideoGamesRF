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

    private lateinit var binding: ActivityMainBinding
    /*private lateinit var repository: GameRepository
    private lateinit var retrofit: Retrofit*/

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.SplashTheme)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, GamesListFragment())
                .commit()
        }

        //Instanciamos el retrofithelper
        //retrofit = RetrofitHelper().getRetrofit()
        //repository = GameRepository(retrofit)

        //Mandamos una ecorutina
       /*lifecycleScope.launch {
           val call: Call<List<GameDto>> = repository.getGames("cm/games/games_list.php")

           //La metemos ala cola para llamarla
           call.enqueue(object: Callback<List<GameDto>> {
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