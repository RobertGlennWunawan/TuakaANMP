package com.ubayadev.tuakaanmp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubayadev.tuakaanmp.databinding.ItemHabitBinding
import com.ubayadev.tuakaanmp.model.Habit
import com.ubayadev.tuakaanmp.viewmodel.HabitViewModel

class HabitListAdapter(val habitList: ArrayList<Habit>, val viewModel: HabitViewModel) :
    RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>(), HabitLayoutInterface {

    fun updateList(newList: List<Habit>) {
        habitList.clear()
        habitList.addAll(newList)
        notifyDataSetChanged()
    }
    class HabitViewHolder(var binding: ItemHabitBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHabitBinding.inflate(inflater, parent, false)
        return HabitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habitList[position]
        holder.binding.habit = habit
        holder.binding.listener = this

        holder.binding.executePendingBindings()

    }

    override fun onTitleClick(v: View) {
        val habitId = v.tag?.toString()?.toIntOrNull() ?: return

        val action = DashboardFragmentDirections.actionToEditHabit(habitId)
        Navigation.findNavController(v).navigate(action)
    }

    override fun getItemCount() = habitList.size
    override fun onButtonPlusClick(v: View, obj: Habit) {
        viewModel.updateProgress(obj.id, 1)
    }
    override fun onButtonMinusClick(v: View, obj: Habit) {
        if (obj.currentProgress > 0) {
            viewModel.updateProgress(obj.id, -1)
        }
    }
}