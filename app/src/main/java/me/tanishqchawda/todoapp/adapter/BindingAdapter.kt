package me.tanishqchawda.todoapp.adapter

import android.widget.Spinner
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import me.tanishqchawda.todoapp.R
import me.tanishqchawda.todoapp.utils.Priority

class BindingAdapter {

    companion object{
        @BindingAdapter("android:parsePriorityToInt")
        @JvmStatic
        fun parsePriorityToInt(spinner: Spinner, priority: Priority) {
            when (priority) {
                Priority.High -> spinner.setSelection(0)
                Priority.Medium -> spinner.setSelection(1)
                Priority.Low -> spinner.setSelection(2)
            }
        }
//
//        @BindingAdapter("android:parsePriorityColor")
//        @JvmStatic
//        fun parsePriorityColor(cardView: CardView, priority: Priority) {
//            when (priority) {
//                Priority.High -> cardView.setCardBackgroundColor(
//                    cardView.context.getColor(
//                        R.color.red
//                    )
//                )
//                Priority.Medium -> cardView.setCardBackgroundColor(
//                    cardView.context.getColor(
//                        R.color.yellow
//                    )
//                )
//                Priority.Low -> cardView.setCardBackgroundColor(
//                    cardView.context.getColor(
//                        R.color.green
//                    )
//                )
//            }
//        }
    }
}