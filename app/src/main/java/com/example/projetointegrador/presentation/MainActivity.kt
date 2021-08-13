package com.example.projetointegrador.presentation

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import androidx.viewpager2.widget.ViewPager2
import com.example.projetointegrador.R
import com.example.projetointegrador.presentation.adapters.FragmentAdapter
import com.example.projetointegrador.presentation.adapters.GenresAdapter
import com.example.projetointegrador.presentation.adapters.MoviesAdapter

class MainActivity : AppCompatActivity(), ErrorListener {

    private lateinit var searchButton : ImageButton
    private var searchText : EditText? = null
    private lateinit var icon : ImageView
    private lateinit var searchMode : TextView
    private lateinit var tryAgain : TextView
    private lateinit var searchFragment : FrameLayout
    private lateinit var imageNotFound : ImageView
    private lateinit var textNotFound : TextView
    private lateinit var messageNotFound : TextView
    private lateinit var movieSearchText : String

    lateinit var listAdapter: MoviesAdapter
    lateinit var container: RecyclerView

    lateinit var genresAdapter: GenresAdapter
    lateinit var containerGenres: RecyclerView

    private val viewModel = MoviesViewModel(this)

    val pageAdapter by lazy { FragmentAdapter(this)

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        containerGenres = findViewById(R.id.rcvAllMoviesTypes)
        genresAdapter = GenresAdapter(context = this)
        containerGenres.adapter = genresAdapter
        containerGenres.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        searchButton = findViewById(R.id.btnSearch)
        searchText = findViewById(R.id.edtSearch)
        icon = findViewById(R.id.imgRectangle)
        searchMode = findViewById(R.id.txtSearchMode)
        tryAgain = findViewById(R.id.txtTryAgain)
        searchFragment = findViewById(R.id.searchFragment)
        imageNotFound = findViewById(R.id.imgNotFound)
        textNotFound = findViewById(R.id.txtNot)
        messageNotFound = findViewById(R.id.txtMessage)
        containerGenres = findViewById(R.id.rcvAllMoviesTypes)

        val viewpager = findViewById<ViewPager2>(R.id.viewpager)
        viewpager.adapter = FragmentAdapter(this)
        val tablayout = findViewById<TabLayout>(R.id.tablayout)
        TabLayoutMediator(tablayout, viewpager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
        viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
            }
        })

        searchButton?.setOnClickListener {
            if (searchText?.text!!.isNotEmpty()) {
                tablayout.visibility = View.GONE
                viewpager.visibility = View.GONE
                icon.visibility = View.VISIBLE
                searchMode.visibility = View.VISIBLE
                tryAgain.visibility = View.VISIBLE
                searchFragment.visibility = View.VISIBLE
                imageNotFound.visibility = View.GONE
                textNotFound.visibility = View.GONE
                messageNotFound.visibility = View.GONE
                containerGenres.visibility = View.GONE

                movieSearchText = searchText?.text.toString()
                val fragment = SearchFragment.searchString(movieSearchText)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.searchFragment, fragment)
                    .addToBackStack(null)
                    .commit()
            } else {
                tablayout.visibility = View.GONE
                viewpager.visibility = View.GONE
                icon.visibility = View.VISIBLE
                searchMode.visibility = View.VISIBLE
                tryAgain.visibility = View.VISIBLE
                imageNotFound.visibility = View.VISIBLE
                textNotFound.visibility = View.VISIBLE
                messageNotFound.visibility = View.VISIBLE
                containerGenres.visibility = View.VISIBLE
            }
        }

        genresAdapter.genresChecked = { movieId4 ->
            if (movieId4.isNotEmpty())
                viewModel.getGenresSelect(movieId4)
                tablayout.visibility = View.VISIBLE
                viewpager.visibility = View.VISIBLE
                icon.visibility = View.INVISIBLE
                searchMode.visibility = View.INVISIBLE
                tryAgain.visibility = View.INVISIBLE
                searchFragment.visibility = View.INVISIBLE
                imageNotFound.visibility = View.GONE
                textNotFound.visibility = View.GONE
                messageNotFound.visibility = View.GONE
                containerGenres.visibility = View.GONE
                searchText?.text?.clear()
        }

        setupGenresObserveList()
        viewModel.getAllGenresInfos()

        tryAgain.setOnClickListener {
            tablayout.visibility = View.VISIBLE
            viewpager.visibility = View.VISIBLE
            icon.visibility = View.INVISIBLE
            searchMode.visibility = View.INVISIBLE
            tryAgain.visibility = View.INVISIBLE
            searchFragment.visibility = View.INVISIBLE
            imageNotFound.visibility = View.GONE
            textNotFound.visibility = View.GONE
            messageNotFound.visibility = View.GONE
            containerGenres.visibility = View.GONE
            searchText?.text?.clear()
        }

    }

    fun setupGenresObserveList() {
        viewModel.allGenresLiveData.observe(this,
            { response ->
                response?.let {
                    genresAdapter.dataSetGenres.clear()
                    genresAdapter.dataSetGenres.addAll(it)
                    genresAdapter.notifyDataSetChanged()
                }
            })
    }


    private fun getTabTitle(position: Int): String {
        return when (position) {
            0 -> "Todos os filmes"
            1 -> "Favoritos"
            else -> ""
        }
    }

    override fun pageError() {
        val intent = Intent(this, ErrorActivity::class.java)
        this?.startActivity(intent)
    }

}

