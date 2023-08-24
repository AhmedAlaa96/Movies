package com.ahmed.movies.ui.movieslist.adapter

import com.ahmed.movies.R
import com.ahmed.movies.data.models.dto.Movie
import com.ahmed.movies.databinding.ItemMovieBinding
import com.ahmed.movies.ui.base.BaseViewHolder
import com.ahmed.movies.ui.base.ListItemClickListener
import com.ahmed.movies.utils.DateTimeHelper
import com.ahmed.movies.utils.Utils.roundTheNumber
import com.ahmed.movies.utils.setNetworkImage
import com.ahmed.movies.utils.alternate

class MovieItemHolder(
    private val binding: ItemMovieBinding,
    private val mMatchItemClickListener: ListItemClickListener<Movie>? = null
) : BaseViewHolder<Movie>(binding, mMatchItemClickListener) {
    override fun bind(item: Movie) {
        bindMovieTitle(item.title)
        bindMovieDate(item.releaseDate)
        bindVoteText(item.voteAverage)
        bindMovieIcon(item.posterPath)
        bindItemClick(item)
    }

    private fun bindItemClick(item: Movie) {
        itemView.setOnClickListener { mMatchItemClickListener?.onItemClick(item, adapterPosition) }
    }

    private fun bindMovieTitle(title: String?) {
        binding.txtTitle.text = title.alternate()
    }

    private fun bindMovieDate(releaseDate: String?) {
        binding.txtDate.text = binding.txtDate.context.getString(R.string.release, DateTimeHelper.convertDateStringToAnotherFormat(releaseDate))
    }

    private fun bindVoteText(voteAverage: Double?) {
        binding.txtVote.text =
            binding.txtVote.context.getString(R.string.rate, roundTheNumber(voteAverage))
    }

    private fun bindMovieIcon(posterPath: String?) {
        binding.imgMovie.setNetworkImage(posterPath)
    }
}