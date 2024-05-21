package com.example.client.ui.home

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.client.databinding.FragmentHomeBinding
import com.example.client.ui.MainActivity
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialDatePicker.Builder.dateRangePicker
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding
    private lateinit var chartDetailsRecyclerAdapter: ChartDetailsRecyclerAdapter
    private lateinit var list: ArrayList<ChartDetailsRecyclerAdapter.DetailInfo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list = ArrayList()
        chartDetailsRecyclerAdapter = ChartDetailsRecyclerAdapter(list)
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.recViewStats.layoutManager = LinearLayoutManager(activity)
        binding.recViewStats.adapter = chartDetailsRecyclerAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txt.setOnClickListener {
            datePickerdialog()
        }
    }
    private fun datePickerdialog(){
        val constraints = CalendarConstraints.Builder()
        //constraints.setValidator(DateValidatorPointBackward.now())
        val calendar = Calendar.getInstance()
        constraints.setEnd(calendar.timeInMillis)
        calendar.set(2022,1,1)
        constraints.setStart(calendar.timeInMillis)
        val builder = dateRangePicker()
        builder.setTitleText("Выберите период")
        builder.setCalendarConstraints(constraints.build())
        // Building the date picker dialog
        val datePicker = builder.build()
//        datePicker.allowEnterTransitionOverlap = true
//        datePicker.allowReturnTransitionOverlap = true
        datePicker.addOnPositiveButtonClickListener { selection ->
            // Retrieving the selected start and end dates
            val startDate = selection.first
            val endDate = selection.second

            // Formatting the selected dates as strings
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val startDateString = sdf.format(Date(startDate))
            val endDateString = sdf.format(Date(endDate))

            // Creating the date range string
            val selectedDateRange = "$startDateString - $endDateString"

            // Displaying the selected date range in the TextView
            binding.txt.text = selectedDateRange
        }

        // Showing the date picker dialog
        datePicker.showNow(parentFragmentManager, "Tag")
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}