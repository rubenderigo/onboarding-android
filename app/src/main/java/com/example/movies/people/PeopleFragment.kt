package com.example.movies.people

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.movies.databinding.FragmentPeopleBinding
import com.example.movies.network.Person
import com.example.movies.people.adapters.PersonAdapter

class PeopleFragment : Fragment(), PersonAdapter.OnItemClickListener {

    private val viewModel: PeopleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentPeopleBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.peopleRv.adapter = PersonAdapter(this)

        return binding.root
    }

    override fun onItemClick(position: Int, item: Person?) {
        activity?.let {
            val intent = Intent(it, DetailPeopleActivity::class.java)
            intent.putExtra("id", item?.id.toString())
            it.startActivity(intent)
        }
    }
}
