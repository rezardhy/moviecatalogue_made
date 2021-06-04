package com.reza.moviecatalogue20.presentation.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.reza.moviecatalogue20.R

import com.reza.moviecatalogue20.core.data.adapter.MovieAdapter
import com.reza.moviecatalogue20.core.data.source.Resource
import com.reza.moviecatalogue20.databinding.ActivityMainBinding
import com.reza.moviecatalogue20.presentation.detail.DetailActivity

import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val movieAdapter = MovieAdapter()
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvFail.visibility= View.GONE

        movieAdapter.onItemClick = {
            Log.d("cekitemclicl",it.toString())
            val i = Intent(this, DetailActivity::class.java)
            i.putExtra(DetailActivity.EXTRAMOVIES, it)
            startActivity(i)
        }

        mainViewModel.getPopularMovies.observe(this,{
            if (it!= null){

                when(it){
                    is Resource.Success<*>->{
                        binding.progress.visibility = View.GONE
                        //val list = it.data as List<Movie>
                        movieAdapter.setMovie(it.data)
                        movieAdapter.notifyDataSetChanged()
                        showList()
                    }

                    is Resource.Loading<*>->
                        binding.progress.visibility = View.VISIBLE

                    is Resource.Error<*>->{
                        binding.progress.visibility = View.GONE

                    }

                }
            }
            else{
                binding.progress.visibility = View.INVISIBLE
                binding.tvFail.visibility= View.VISIBLE
            }
        })


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.move_to_fav,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_move_fav){

            val uri = Uri.parse("moviefav://favourite")
            startActivity(Intent(Intent.ACTION_VIEW, uri))

        }
        return super.onOptionsItemSelected(item)
    }

    private fun showList(){
        with(binding.rvUser){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

}