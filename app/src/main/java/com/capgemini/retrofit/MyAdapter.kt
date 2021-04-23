package com.capgemini.retrofit

import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import java.net.URL
import javax.security.auth.callback.Callback

class MyAdapter(val data : List<Movie>) : RecyclerView.Adapter<MyAdapter.ViewHolder>()
{
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val title : TextView
        val description : TextView
        val rating : TextView
        val poster : ImageView

        init {

            title=view.findViewById(R.id.moviesname)
            description=view.findViewById(R.id.moviesdescription)
            poster=view.findViewById(R.id.moviesposter)
            rating=view.findViewById(R.id.moviesrating)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
       //inflate layout for item
       val inflater= LayoutInflater.from(parent.context)
       val v= inflater.inflate(R.layout.movie_items,parent,false)
        return ViewHolder(v)

    }

    override fun getItemCount() =data.size


    override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {
       val movie=data[position]
        holder.title.text= movie.title
        holder.description.text= movie.overview
        holder.rating.text= movie.vote_average.toString()
        val imageUrl = "http://image.tmdb.org/t/p/w185${movie.poster_path}"


      //  val imgurl= "https://openweathermap.org/img/wn/10d.png"
      /* val req= TMDInterface.getInstance().getImage(imageUrl)
        req.enqueue(object : retrofit2.Callback<ResponseBody>{
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("image","failed")
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.isSuccessful)
                {
                    val bytes= response.body()?.bytes()
                    val bmp = BitmapFactory.decodeByteArray(bytes,0,
                        bytes?.size?:0)
                    holder.poster.setImageBitmap(bmp)

                }
            }
        })*/
        Glide.with(holder.itemView.context)
            .load(Uri.parse(imageUrl)).disallowHardwareConfig()
            .into(holder.poster)








    }
}
