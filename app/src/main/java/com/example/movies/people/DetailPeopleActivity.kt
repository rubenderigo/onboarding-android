package com.example.movies.people

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.movies.databinding.ActivityDetailPeopleBinding
import com.example.movies.network.Cast
import com.example.movies.people.adapters.PersonCastAdapter

class DetailPeopleActivity : AppCompatActivity(), PersonCastAdapter.OnItemClickListener {

    private val viewModel: DetailPeopleViewModel by viewModels {
        DetailPeopleViewModelFactory(
            getIdIntent()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDetailPeopleBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.castPerson.adapter = PersonCastAdapter(this)

        val view = binding.root
        setContentView(view)

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)
    }

    private fun getIdIntent(): Int? {
        val intent = getIntent()
        val id = intent.getStringExtra("id")
        return id?.toInt()
    }

    override fun onItemClick(position: Int, cast: Cast?) {
        Toast.makeText(this, cast?.title.toString(), Toast.LENGTH_SHORT ).show()
    }
}
