package com.example.projetointegrador.presentation.adapters

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.projetointegrador.R
import com.example.projetointegrador.data.model.Infos
import com.bumptech.glide.Glide
import com.example.projetointegrador.presentation.moviesdetails.MoviesDetailsActivity

class MoviesAdapter(
    var dataSet: MutableList<Infos> = mutableListOf(),
    private var favoritechecked: (movie: Infos, isChecked: Boolean) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.RecyclerviewViewHolder>() {

    class RecyclerviewViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var imageMovie = view.findViewById<ImageView>(R.id.imgMovie)
        var movieTitle = view.findViewById<TextView>(R.id.txtMovieTitle)
        var rating = view.findViewById<TextView>(R.id.txtRating)
        var favoriteButton = view.findViewById<ToggleButton>(R.id.btnFavorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerviewViewHolder = RecyclerviewViewHolder (
       LayoutInflater.from(parent.context).inflate(R.layout.fragment_movies, parent, false)
    )

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: RecyclerviewViewHolder, position: Int) {

        if (dataSet[position].poster_path !== "") {
            holder.imageMovie?.let {
                Glide.with(holder.imageMovie.context)
                    .load("https://image.tmdb.org/t/p/w500" + dataSet[position].poster_path)
                    .into(it)
            }
        }
        holder.movieTitle.text = dataSet[position].title
        holder.rating.text = convertRating(dataSet[position].vote_average)
        holder.imageMovie?.setOnClickListener {
            val intent = Intent(it.context, MoviesDetailsActivity::class.java)
            intent.putExtra("movies", dataSet[position])
            it.context.startActivity(intent)
        }

        holder.favoriteButton?.isChecked = dataSet[position].favoriteCheck
        holder.favoriteButton?.setOnClickListener {
            favoritechecked(dataSet[position], !dataSet[position].favoriteCheck)
            notifyDataSetChanged()
        }

    }

    override fun getItemCount() = dataSet.size

    fun convertRating(userRating: Number): String {
        return "${"%.0f".format((userRating.toDouble() * 10.0))}%"
    }

}