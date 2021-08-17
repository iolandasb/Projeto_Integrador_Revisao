package com.example.projetointegrador.presentation

import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetointegrador.R
import com.example.projetointegrador.data.model.Infos
import com.example.projetointegrador.presentation.adapters.GenresAdapter
import com.example.projetointegrador.presentation.adapters.MoviesAdapter

class SearchFragment : Fragment() {

    private lateinit var viewModel: MoviesViewModel

    lateinit var listAdapter: MoviesAdapter
    lateinit var container: RecyclerView

    lateinit var genresAdapter: GenresAdapter
    lateinit var containerGenres: RecyclerView

    private lateinit var searchNotFound : View

    private var movieSearch: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieSearch = it.getString(search)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_tab_allmovies, container, false)
    }

    companion object {
        var search = "search"
        @JvmStatic
        fun searchString(movieSearch: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(search, movieSearch)
                }
            }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchNotFound = view.findViewById(R.id.vMovieNotFound)

        viewModel = ViewModelProvider(requireActivity()).get(MoviesViewModel::class.java)

        container = view.findViewById(R.id.rcvContainer)
        listAdapter = MoviesAdapter(favoritechecked = ::onFavoriteIconClick)
        container.adapter = listAdapter
        container.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        containerGenres = view.findViewById(R.id.rcvAllMoviesTypes)
        genresAdapter = GenresAdapter(context = view.context)
        containerGenres.adapter = genresAdapter
        containerGenres.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        
        val querySearch = movieSearch?.toUri()
        if (querySearch != null) {
            update(querySearch)
        }

        setupSearchObserveList()
        setupGenresObserveList()
        setupObserveList()
        searchNotFound.visibility = View.GONE

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun update(querySearch: Uri) {
        viewModel.getSearch(querySearch)
        genresAdapter.genresChecked = { genre_ids ->
            if (genre_ids.isEmpty())
                viewModel.getSearch(querySearch)
            else
                viewModel.getGenresSearch(genre_ids)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun onFavoriteIconClick(movie: Infos, isChecked: Boolean) {
        if (isChecked) {
            movie.favoriteCheck = true
            viewModel.addFavorite(movie)
        } else{
            movie.favoriteCheck = false
            viewModel.removeFavorite(movie)
        }
    }

    private fun setupSearchObserveList(){
        viewModel.searchLiveData.observe(viewLifecycleOwner,
            { response ->
                response?.let{
                    if (it.isNullOrEmpty()) {
                        searchNotFound.visibility = View.VISIBLE
                        listAdapter.dataSet.clear()
                        listAdapter.notifyDataSetChanged()
                    }else{
                        searchNotFound.visibility = View.GONE
                        listAdapter.dataSet.clear()
                        listAdapter.dataSet.addAll(it)
                        listAdapter.notifyDataSetChanged()
                    }
                }
            })
    }

    fun setupGenresObserveList() {
        viewModel.allGenresLiveData.observe(viewLifecycleOwner,
            { response ->
                response?.let {
                    genresAdapter.dataSetGenres.clear()
                    genresAdapter.dataSetGenres.addAll(it)
                    genresAdapter.notifyDataSetChanged()
                }
            })
    }

    fun setupObserveList() {
        viewModel.moviesLiveData.observe(viewLifecycleOwner,
            { response ->
                response?.let {
                    listAdapter.dataSet.clear()
                    listAdapter.dataSet.addAll(it)
                    listAdapter.notifyDataSetChanged()
                }
            }
        )
    }

}