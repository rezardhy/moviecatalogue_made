package com.reza.moviecatalogue20.core.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.reza.moviecatalogue20.core.data.domain.Movie
import com.reza.moviecatalogue20.core.databinding.ListLayoutBinding


class MovieAdapter :RecyclerView.Adapter<MovieAdapter.MoviesViewHolder>() {
    var onItemClick: ((Movie) -> Unit)? = null
    private var mlist = ArrayList<Movie>()
    fun setMovie(list: List<Movie>?) {
        if (list == null) return
        this.mlist.clear()
        this.mlist.addAll(list)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding =
            ListLayoutBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = mlist[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = mlist.size

    inner class MoviesViewHolder(private val binding:ListLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movies: Movie){
            binding.apply {
                tvItemTitle.text = movies.title
                if(movies.year == "" || movies.year== null)
                    tvItemDate.text = "unknown"
                else
                    tvItemDate.text = movies.year?.substring(0,4)

                Glide.with(itemView.context)
                        .load("https://image.tmdb.org/t/p/original"+movies.img)
                        .into(imgPoster)

            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(mlist[absoluteAdapterPosition])
            }
        }
    }



}