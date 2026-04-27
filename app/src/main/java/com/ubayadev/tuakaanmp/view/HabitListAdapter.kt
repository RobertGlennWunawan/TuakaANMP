package com.ubayadev.tuakaanmp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubayadev.tuakaanmp.databinding.ItemHabitBinding
import com.ubayadev.tuakaanmp.model.Habit
import com.ubayadev.tuakaanmp.viewmodel.HabitViewModel

class HabitListAdapter(val habitList: ArrayList<Habit>,val viewModel: HabitViewModel) :
    RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>() {

    fun updateList(newList: List<Habit>) {
        habitList.clear()
        habitList.addAll(newList)
        notifyDataSetChanged()
    }

    class HabitViewHolder(var binding: ItemHabitBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val binding = ItemHabitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HabitViewHolder(binding)
    }


    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habitList[position]
        holder.binding.txtHabitName.text = habit.name
        holder.binding.txtDescription.text = habit.description
        holder.binding.txtProgressRatio.text = "${habit.currentProgress}/${habit.target}"
        holder.binding.progressHabit.max = habit.target
        holder.binding.progressHabit.progress = habit.currentProgress

        if (habit.currentProgress >= habit.target) {
            holder.binding.txtStatus.text = "Completed"
        } else {
            holder.binding.txtStatus.text = "In Progress"
        }

        holder.binding.btnPlus.setOnClickListener {
            viewModel.updateProgress(habit.id, 1) // Tambah 1 poin
        }

        holder.binding.btnMinus.setOnClickListener {
            if (habit.currentProgress > 0) {
                viewModel.updateProgress(habit.id, -1) // Kurang 1 poin
            }
        }

    }


    override fun getItemCount() = habitList.size
}