package me.tanishqchawda.todoapp.views


import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.*
import me.tanishqchawda.todoapp.R
import me.tanishqchawda.todoapp.adapter.TaskAdapter
import me.tanishqchawda.todoapp.databinding.BottomSheetItemBinding
import me.tanishqchawda.todoapp.databinding.FilterBottomSheetBinding
import me.tanishqchawda.todoapp.databinding.FragmentOneBinding
import me.tanishqchawda.todoapp.utils.observeOnce
import me.tanishqchawda.todoapp.viewModel.LoginViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class OneFragment : Fragment() {
    private  val TAG = "OneFragment"
    lateinit var binding: FragmentOneBinding
    private var searchJob: Job? = null
    private val listAdapter: TaskAdapter by lazy { TaskAdapter(listOf()) }
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_one, container, false)
        val sharedPreference =  context?.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
       val username= sharedPreference?.getString("username","")
        binding.apply {
            viewmodel = viewModel
            lifecycleOwner =  viewLifecycleOwner
            add.setOnClickListener {
                enterMobileNumberPopUp()
            }
            viewModel.getNotes.observe(viewLifecycleOwner){
                rv.adapter = TaskAdapter(it)

            }
            filter.setOnClickListener {
                filterPopUp()
            }
            val string = "Welcome back,${username}"
            val e = string.length
            string.split(",").toString()
            Log.e(TAG, "onCreateView:${string} hdhdhd ${string.split(",")} jzsxjsjss ${e}")
            val spannable = SpannableString(string)
            spannable.setSpan(
                ForegroundColorSpan(Color.WHITE),
                13, // start
                e, // end
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )
            welcomeBackTagline.text = spannable







        }




        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    companion object {
        const val RESULT_FROM_FRAGMENT_B = "RESULT_FROM_FRAGMENT_B"
    }
    private fun enterMobileNumberPopUp() {
        val binding: BottomSheetItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.bottom_sheet_item, null, false)
        val dialog = BottomSheetDialog(requireContext(),R.style.BottomSheetDialog)
        binding.viewModel = viewModel

        dialog.setContentView(binding.root)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.show()}


    private fun filterPopUp() {
        val binding: FilterBottomSheetBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.filter_bottom_sheet, null, false)
        val dialog = BottomSheetDialog(requireContext(),R.style.BottomSheetDialog)

        binding.apply {
            binding.color.setOnClickListener {
                it.isSelected = true
                binding.bottomSheet.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.white))
            }

            binding.color2.setOnClickListener {
               it.isSelected = !it.isSelected
               binding.bottomSheet.background = ResourcesCompat.getDrawable(resources,R.drawable.round_shape_pinkish,null)
            }
            binding.color3.setOnClickListener {
                binding.color.isSelected = !it.isSelected
                binding.color2.isSelected = !it.isSelected
                it.isSelected = true
                binding.bottomSheet.background = ResourcesCompat.getDrawable(resources,R.drawable.round_shape_greenish,null)
            }
            binding.color4.setOnClickListener {
                binding.color.isSelected = !it.isSelected
                binding.color2.isSelected = !it.isSelected
                binding.color3.isSelected = !it.isSelected
                it.isSelected = true
                binding.bottomSheet.background = ResourcesCompat.getDrawable(resources,R.drawable.round_shape_yellowish,null)
            }
            binding.color5.setOnClickListener {
                binding.color.isSelected = !it.isSelected
                binding.color2.isSelected = !it.isSelected
                binding.color3.isSelected = !it.isSelected
                binding.color4.isSelected = !it.isSelected
                it.isSelected = true
                binding.bottomSheet.background = ResourcesCompat.getDrawable(resources,R.drawable.round_shape_purpleish,null)
            }
        }
        dialog.setContentView(binding.root)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.show()}



    private fun getDDMMYYYY(date:Date): String {
        val dateFormat: DateFormat = SimpleDateFormat("dd-MMM-yyyy")


        return dateFormat.format(date)
    }
    private fun searchThroughDatabase(query: String) {
        val searchQuery = "%$query%"
        viewModel.searchDatabase(searchQuery).observeOnce(viewLifecycleOwner) {
                listAdapter.setData(it)
            }
    }


}