package com.reza.moviecatalogue20.favourite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.reza.moviecatalogue20.core.data.adapter.MovieAdapter
import com.reza.moviecatalogue20.favourite.databinding.ActivityFavourite2Binding
import com.reza.moviecatalogue20.presentation.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class Favourite2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityFavourite2Binding
    private val movieAdapter = MovieAdapter()
    private val favouriteViewModel: Favourite2ViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavourite2Binding.inflate(layoutInflater)
        loadKoinModules(favouriteModule)

        title = "Favourite Movie"
        setContentView(binding.root)

        movieAdapter.onItemClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRAMOVIES, it)
            startActivity(intent)
        }

        favouriteViewModel.getDetailMovie.observe(this, {
            if (it.isNotEmpty()) {
                binding.progress.visibility = View.GONE
                binding.tvFail.visibility = View.GONE
                movieAdapter.setMovie(it)
                movieAdapter.notifyDataSetChanged()
                Log.d("Cekisi", it.toString())
                showList()
            } else {
                Log.d("cekfav", "Else")
                movieAdapter.notifyDataSetChanged()
                binding.rvUser.visibility = View.GONE
                binding.progress.visibility = View.GONE
                binding.tvFail.visibility = View.VISIBLE
            }
        })

    }

    private fun showList() {
        with(binding.rvUser) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }
}