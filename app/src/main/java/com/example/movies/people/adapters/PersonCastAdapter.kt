package com.example.movies.people.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.databinding.ItemCastMovieBinding
import com.example.movies.network.CastPerson
import com.example.movies.people.DetailPeopleActivity

class PersonCastAdapter(private val listener: DetailPeopleActivity) :
    ListAdapter<CastPerson, PersonCastAdapter.CastViewHolder>(DiffCallback) {

    inner class CastViewHolder(private val binding: ItemCastMovieBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(item: CastPerson) {
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
        fun onItemClick(position: Int, item: CastPerson?)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<CastPerson>() {
        override fun areItemsTheSame(oldItem: CastPerson, newItem: CastPerson): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CastPerson, newItem: CastPerson): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        return CastViewHolder(
            ItemCastMovieBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

}
