package me.tanishqchawda.todoapp.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import me.tanishqchawda.todoapp.R
import me.tanishqchawda.todoapp.databinding.FragmentTwoBinding

class TwoFragment : Fragment() {
     lateinit var binding: FragmentTwoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_two, container, false)

        return binding.root
    }


}