package com.ubayadev.tuakaanmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.ubayadev.tuakaanmp.databinding.FragmentAddHabitBinding
import com.ubayadev.tuakaanmp.model.Habit
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
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(HabitViewModel::class.java)

        val newHabit = Habit(title = "", description = "", target = 0, currentProgress = 0, id = 0)
        binding.habit = newHabit
        binding.submitListener = this
        binding.executePendingBindings()

        binding.btnCreate.setOnClickListener {
            val name = newHabit.title.trim()
            val desc = newHabit.description.trim()
            val target = binding.txtTarget.text.toString().toIntOrNull()

            if (name.isEmpty() || desc.isEmpty() || target == null) {
                binding.txtError.text = "Semua field harus diisi dengan benar!"
                binding.txtError.visibility = View.VISIBLE
            } else {
                binding.txtError.visibility = View.GONE


                viewModel.addHabit(name, desc, target)

                view.findNavController().navigateUp()
            }
        }
    }
}