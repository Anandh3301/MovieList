package com.capgemini.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
//comment to test Github Push
class MainActivity : AppCompatActivity() {
    lateinit var movies : PopularMovies
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val key=resources.getString(R.string.API_KEY)
        var git = "hub"
      val request=  TMDInterface.getInstance().getMovies(key)
        request.enqueue(PopularMoviesCallback())
      //  Thread.sleep(2000)

    }

    inner class PopularMoviesCallback : Callback<PopularMovies>{
        override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
            Toast.makeText(applicationContext,"something went wrong",Toast.LENGTH_LONG).show()
            Log.wtf("callback",t.message)
        }

        override fun onResponse(call: Call<PopularMovies>, response: Response<PopularMovies>) {
            if(response.isSuccessful)
            {
                movies= response.body()!!
                Log.wtf("callback",movies.toString())
                rview.layoutManager=LinearLayoutManager(applicationContext)
                rview.adapter=MyAdapter(movies.results)



            }

        }

    }

}