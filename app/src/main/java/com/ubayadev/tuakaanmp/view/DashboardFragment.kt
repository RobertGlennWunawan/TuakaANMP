package com.ubayadev.tuakaanmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubayadev.tuakaanmp.R
import com.ubayadev.tuakaanmp.databinding.FragmentDashboardBinding
import com.ubayadev.tuakaanmp.viewmodel.HabitViewModel

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var viewModel: HabitViewModel
    private lateinit var habitAdapter: HabitListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(HabitViewModel::class.java)
        viewModel.refresh()

        habitAdapter = HabitListAdapter(arrayListOf(), viewModel)

        binding.recViewDashboard.layoutManager = LinearLayoutManager(context)
        binding.recViewDashboard.adapter = habitAdapter

        observeViewModel()
    }
    private fun observeViewModel() {
        viewModel.habitsLD.observe(viewLifecycleOwner) { habits ->
            habitAdapter.updateList(habits)
        }
    }
}