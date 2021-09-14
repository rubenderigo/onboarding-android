package com.example.movies.settings

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.movies.LoginRegisterActivity
import com.example.movies.MoviesApplication
import com.example.movies.R
import com.example.movies.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    private val viewModel: DataViewModel by activityViewModels {
        DataViewModelFactory(
            (activity?.application as MoviesApplication).database.userDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentSettingsBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.data = viewModel

        binding?.buttonLogOut.setOnClickListener {
            Toast.makeText(activity, "Log out", Toast.LENGTH_LONG).show()
            activity?.let{
                val intent = Intent (it, LoginRegisterActivity::class.java)
                it.startActivity(intent)
                it.finish()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_settings, menu)
    }
}