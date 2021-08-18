package com.example.projetointegrador.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projetointegrador.R
import com.example.projetointegrador.data.model.AllMoviesGenres
import com.google.android.material.chip.Chip

class GenresAdapter (var dataSetGenres: MutableList<AllMoviesGenres> = mutableListOf()) : RecyclerView.Adapter<GenresAdapter.RecyclerviewViewHolder>() {

    var genresChecked : (idGenres: List<Int>) -> Unit = {}
    private val selectGenres: MutableList<Int> = mutableListOf()

    class RecyclerviewViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var genres = view.findViewById<Chip>(R.id.cpGenres)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerviewViewHolder = RecyclerviewViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.activity_movies_type, parent, false)
    )

    override fun onBindViewHolder(holder: RecyclerviewViewHolder, position: Int) {
        holder.genres.text = dataSetGenres[position].name
        holder.genres?.setOnCheckedChangeListener { chip, isChecked ->
            if(isChecked){
                selectGenres.add(dataSetGenres[position].id)
            }else{
                selectGenres.remove(dataSetGenres[position].id)
            }
                genresChecked(selectGenres)
        }

    }

    override fun getItemCount() = dataSetGenres.size

}