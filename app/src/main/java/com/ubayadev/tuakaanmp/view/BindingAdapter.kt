package com.ubayadev.tuakaanmp.view

import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("progressRatioText", "progressTarget")
fun setProgressRatio(view: TextView, currentProgress: Int, target: Int) {
    view.text = "$currentProgress/$target"
}

@BindingAdapter("habitStatusText", "habitTarget")
fun setHabitStatus(view: TextView, currentProgress: Int, target: Int) {
    if (currentProgress >= target) {
        view.text = "Completed"
    } else {
        view.text = "In Progress"
    }
}

@BindingAdapter("android:max")
fun setProgressBarMax(progressBar: ProgressBar, max: Int) {
    progressBar.max = max
}