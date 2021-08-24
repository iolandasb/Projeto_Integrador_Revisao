package com.example.projetointegrador.presentation.moviesdetails

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projetointegrador.R
import com.example.projetointegrador.data.model.Infos
import com.example.projetointegrador.presentation.error.ErrorActivity
import com.example.projetointegrador.presentation.error.ErrorListener
import com.example.projetointegrador.presentation.MoviesViewModel
import com.example.projetointegrador.presentation.adapters.CastAdapter
import com.example.projetointegrador.presentation.adapters.GenresAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.imageview.ShapeableImageView

class MoviesDetailsActivity : AppCompatActivity(), ErrorListener {

    private val viewModel = MoviesViewModel(this)
    private lateinit var castAdapter: CastAdapter
    private lateinit var containerCast: RecyclerView
    private lateinit var genresAdapter: GenresAdapter
    private lateinit var containerGenres: RecyclerView
    private lateinit var movieImage: ShapeableImageView
    private lateinit var titleMovie: TextView
    private lateinit var movieYear: TextView
    private lateinit var certification: TextView
    private lateinit var movieLength: TextView
    private lateinit var synopsis: TextView
    private lateinit var movieRating: TextView
    private lateinit var returnButton: FloatingActionButton
    private lateinit var favButton: ToggleButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_details)

        movieImage = findViewById(R.id.imgMovieBanner)
        titleMovie = findViewById(R.id.txtTitle)
        movieYear = findViewById(R.id.txtMovieYear)
        certification = findViewById(R.id.txtPage)
        movieLength = findViewById(R.id.txtMovieLength)
        synopsis = findViewById(R.id.txtSinopsys)
        movieRating = findViewById(R.id.txtMovieRating)
        returnButton = findViewById(R.id.btnReturn)
        favButton = findViewById(R.id.btnFavorite)

        containerCast = findViewById(R.id.rcvMovieCast)
        castAdapter = CastAdapter()
        containerCast.adapter = castAdapter
        containerCast.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        containerGenres = findViewById(R.id.rcvMovieGenres)
        genresAdapter = GenresAdapter()
        containerGenres.adapter = genresAdapter
        containerGenres.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val infos = intent.extras?.get("movies") as Infos?

        if (infos != null) {
            viewModel.getMoviesReleaseDate(movieId = infos.id)
            viewModel.getMoviesRuntime(movieId = infos.id)
            viewModel.getCastInfos(movieId = infos.id)
            viewModel.getGenresInfos(movieId = infos.id)
        }

        infos?.let {
            Glide.with(this).load("https://image.tmdb.org/t/p/w500" + it.backdrop_path)
                .into(movieImage)
            titleMovie.text = it.title
            movieRating.text = convertRating(it.vote_average)
            synopsis.text = it.overview
            movieYear.text = it.release_date.substring(0, 4)
            favButton.isChecked = it.favoriteCheck

            setupReleaseDateObserveList()
            setupRuntimeObserveList()
            setupCastObserveList()
            setupGenresTypesObserveList()

        }

        returnButton.setOnClickListener {
            finish()
        }
    }

    private fun setupReleaseDateObserveList() {
        viewModel.releaseDateLiveData.observe(this,
            {
                certification.text = it.toString()
            })
    }

    private fun convertRuntime(totalMinutes: Int): String {
        var minutes = (totalMinutes % 60).toString()
        minutes = if (minutes.length == 1) "0$minutes" else minutes
        return (totalMinutes / 60).toString() + "h" + minutes + "min"
    }

    private fun setupRuntimeObserveList() {
        viewModel.runtimeLiveData.observe(this,
            {
                movieLength.text = convertRuntime(it.runtime)
            })
    }

    private fun convertRating(userRating: Number): String {
        return "${"%.0f".format((userRating.toDouble() * 10.0))}%"
    }

    private fun setupCastObserveList() {
        viewModel.castLiveData.observe(this,
            { response ->
                response?.let {
                    castAdapter.dataSetCast.clear()
                    castAdapter.dataSetCast.addAll(it)
                    castAdapter.notifyDataSetChanged()
                }
            })
    }

    private fun setupGenresTypesObserveList() {
        viewModel.allGenresLiveData.observe(this,
            { response ->
                response?.let {
                    genresAdapter.dataSetGenres.clear()
                    genresAdapter.dataSetGenres.addAll(it)
                    genresAdapter.notifyDataSetChanged()
                }
            })
    }

    override fun pageError() {
        val intent = Intent(this, ErrorActivity::class.java)
        startActivity(intent)
    }

}