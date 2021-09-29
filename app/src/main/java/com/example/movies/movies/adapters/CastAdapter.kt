package com.example.movies.movies.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.databinding.ItemCastBinding
import com.example.movies.movies.detail.DetailMovieFragment
import com.example.movies.network.CastMovie

class CastAdapter(private val listener: DetailMovieFragment) :
    ListAdapter<CastMovie, CastAdapter.CastMovieViewHolder>(DiffCallback) {

    inner class CastMovieViewHolder(private val binding: ItemCastBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(item: CastMovie) {
            binding.cast = item
            binding.executePendingBindings()
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position, binding.cast)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, item: CastMovie?)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<CastMovie>() {
        override fun areItemsTheSame(oldItem: CastMovie, newItem: CastMovie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CastMovie, newItem: CastMovie): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastMovieViewHolder {
        return CastMovieViewHolder(
            ItemCastBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: CastMovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

}
