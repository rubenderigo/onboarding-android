package com.example.movies.movies.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.databinding.ItemCastBinding
import com.example.movies.databinding.ItemCharacterBinding
import com.example.movies.databinding.ItemCrewBinding
import com.example.movies.movies.detail.DetailMovieFragment
import com.example.movies.network.CastMovie
import com.example.movies.network.CrewMovie

class CastCrewAdapter(private val listener: DetailMovieFragment) :
    ListAdapter<CastMovie, CastCrewAdapter.CastCrewMovieViewHolder>(DiffCallback) {

    inner class CastCrewMovieViewHolder(private val binding: ItemCastBinding) :
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastCrewMovieViewHolder {
        return CastCrewMovieViewHolder(
            ItemCastBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: CastCrewMovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

}

class CastAdapter :
    ListAdapter<CastMovie, CastAdapter.CastMovieViewHolder>(DiffCallback) {
    class CastMovieViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CastMovie) {
            binding.cast = item
            binding.executePendingBindings()
        }
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
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: CastMovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }
}

class CrewAdapter :
    ListAdapter<CrewMovie, CrewAdapter.CrewMovieViewHolder>(DiffCallback) {
    class CrewMovieViewHolder(private val binding: ItemCrewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CrewMovie) {
            binding.crew = item
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<CrewMovie>() {
        override fun areItemsTheSame(oldItem: CrewMovie, newItem: CrewMovie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CrewMovie, newItem: CrewMovie): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewMovieViewHolder {
        return CrewMovieViewHolder(
            ItemCrewBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: CrewMovieViewHolder, position: Int) {
        val person = getItem(position)
        holder.bind(person)
    }
}
