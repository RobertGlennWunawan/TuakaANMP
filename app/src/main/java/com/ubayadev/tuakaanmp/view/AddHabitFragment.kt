package com.ubayadev.tuakaanmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.ubayadev.tuakaanmp.R
import com.ubayadev.tuakaanmp.databinding.FragmentAddHabitBinding
import com.ubayadev.tuakaanmp.viewmodel.HabitViewModel

class AddHabitFragment : Fragment() {
    private lateinit var binding: FragmentAddHabitBinding
    private lateinit var viewModel: HabitViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(requireActivity()).get(HabitViewModel::class.java)

        binding.btnCreate.setOnClickListener {
            val name = binding.txtName.text.toString()
            val desc = binding.txtDesc.text.toString()
            val target = binding.txtTarget.text.toString().toIntOrNull()

            if (name.isEmpty() || desc.isEmpty() || target == null) {
                binding.txtError.text = "Semua field harus diisi!"
                binding.txtError.visibility = View.VISIBLE
            } else {
                viewModel.addHabit(name, desc, target)

                view.findNavController().navigateUp()
            }
        }
    }
}