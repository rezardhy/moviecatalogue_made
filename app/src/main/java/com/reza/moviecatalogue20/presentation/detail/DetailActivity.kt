package com.reza.moviecatalogue20.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.reza.moviecatalogue20.R
import com.reza.moviecatalogue20.databinding.ActivityDetailBinding
import com.reza.moviecatalogue20.core.data.domain.Movie
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var moviesExtra: Movie
    private var menu: Menu? = null

    private val detailViewModel:DetailViewModel by viewModel()


    var state:Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        moviesExtra = intent.getParcelableExtra<Movie>(EXTRAMOVIES) as Movie
        binding.progressBar.visibility = View.VISIBLE

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        setMovie(moviesExtra)

    }



    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_fav,menu)
        this.menu = menu
        val checkFav = moviesExtra.favourite
        state = moviesExtra.favourite
        setFavMovie(checkFav)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_fav){
            state = !state
            detailViewModel.setFavourite(moviesExtra,state)
            setFavMovie(state)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setMovie(movie: Movie?){
        binding.progressBar.visibility = View.INVISIBLE

        binding.apply {
            movieTitle.text = movie?.title
            movieOverview.text=movie?.overview
            movieYears.text = movie?.year?.substring(0,4)
            movieScore.text = movie?.vote.toString()
        }

        Glide.with(this)
                .load("https://image.tmdb.org/t/p/original"+movie?.img)
                .into(binding.moviePoster)



    }

    private fun setFavMovie(state: Boolean) {

        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_fav)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_pink_24)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24)
        }
    }

    companion object{
        const val EXTRAMOVIES ="extra_movies"
    }
}