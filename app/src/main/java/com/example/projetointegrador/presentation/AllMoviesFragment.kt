package com.example.projetointegrador.presentation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetointegrador.R
import com.example.projetointegrador.data.model.Infos
import com.example.projetointegrador.presentation.adapters.GenresAdapter
import com.example.projetointegrador.presentation.adapters.MoviesAdapter

class AllMoviesFragment : Fragment() {

    private lateinit var viewModel: MoviesViewModel

    lateinit var listAdapter: MoviesAdapter
    lateinit var container: RecyclerView

    lateinit var genresAdapter: GenresAdapter
    lateinit var containerGenres: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_tab_allmovies, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(MoviesViewModel::class.java)

        container = view.findViewById(R.id.rcvContainer)
        listAdapter = MoviesAdapter(favoritechecked = ::onFavoriteIconClick)
        container.adapter = listAdapter
        container.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        containerGenres = view.findViewById(R.id.rcvAllMoviesTypes)
        genresAdapter = GenresAdapter(context = view.context)
        containerGenres.adapter = genresAdapter
        containerGenres.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        genresAdapter.genresChecked = { movieId4 ->
            if (movieId4.isEmpty())
                viewModel.getInfos()
            else
                viewModel.getGenresSelect(movieId4)
        }

        setupObserveList()
        setupGenresObserveList()
        viewModel.getAllGenresInfos()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onResume() {
        super.onResume()
        viewModel.getInfos()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun onFavoriteIconClick(movie: Infos, isChecked: Boolean) {
        if (isChecked) {
            movie.favoriteCheck = true
            viewModel.addFavorite(movie)
        }
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

}
