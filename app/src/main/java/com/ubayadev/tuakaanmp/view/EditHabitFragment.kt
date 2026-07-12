package com.ubayadev.tuakaanmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.ubayadev.tuakaanmp.databinding.FragmentAddHabitBinding
import com.ubayadev.tuakaanmp.model.Habit
import com.ubayadev.tuakaanmp.viewmodel.HabitViewModel

class EditHabitFragment : Fragment() {

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

        val habitId = EditHabitFragmentArgs.fromBundle(requireArguments()).habitId
        viewModel.fetchHabit(habitId)

        viewModel.habitLD.observe(viewLifecycleOwner, Observer { habit ->
            if (habit != null) {
                binding.habit = habit
                binding.submitListener = this
                binding.executePendingBindings()

                binding.txtTarget.setText(habit.target.toString())
                binding.btnCreate.text = "Submit"

                binding.btnCreate.setOnClickListener { v ->
                    onSubmitClick(v, habit)
                }
            }
        })
    }

    fun onSubmitClick(view: View, habit: Habit) {
        val targetText = binding.txtTarget.text.toString().trim()

        if (habit.title.isEmpty() || habit.description.isEmpty() || targetText.isEmpty()) {
            Toast.makeText(requireContext(), "Semua field harus diisi dengan benar!", Toast.LENGTH_SHORT).show()
        } else {
            habit.target = targetText.toInt()

            viewModel.updateHabit(habit)
            Toast.makeText(requireContext(), "Habit berhasil diperbarui!", Toast.LENGTH_SHORT).show()

            view.findNavController().navigateUp()
        }
    }
}