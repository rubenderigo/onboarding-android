package com.example.movies.people.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.databinding.ItemPersonBinding
import com.example.movies.network.Person

class PersonAdapter :
    ListAdapter<Person, PersonAdapter.PersonViewHolder>(DiffCallback) {

    class PersonViewHolder(private val binding: ItemPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(person: Person) {
            binding.person = person
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder(
            ItemPersonBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = getItem(position)
        holder.bind(person)
    }
}
