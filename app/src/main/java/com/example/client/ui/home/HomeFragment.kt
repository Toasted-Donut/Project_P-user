package com.example.client.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.client.data.getCHartColor
import com.example.client.databinding.FragmentHomeBinding
import com.example.client.ui.CommonViewModeLProviderFactory
import com.example.client.ui.MyApp
import com.example.client.ui.CommonViewModel
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker.Builder.dateRangePicker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.eazegraph.lib.models.PieModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.CoroutineContext


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), CoroutineScope {
    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var binding: FragmentHomeBinding
    //private lateinit var mainActivity: MainActivity

    private val viewModel: CommonViewModel by lazy {
        val app = activity!!.application as MyApp
        val viewModeLProviderFactory = CommonViewModeLProviderFactory(app)
        ViewModelProvider(
            this,
            viewModeLProviderFactory
        )[CommonViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // mainActivity = activity as MainActivity
        binding.txt.setOnClickListener {
            datePickerdialog()
        }
        binding.recViewStats.layoutManager = LinearLayoutManager(activity)
        binding.recViewStats.adapter = ChartDetailsRecyclerAdapter()
        viewModel.categorySumLvData.observe(this) { list ->
            list?.let {
                (binding.recViewStats.adapter as ChartDetailsRecyclerAdapter).updateList(it)
                for(catSum in it){
                    binding.piechart.addPieSlice(PieModel(
                        catSum.name,
                        catSum.sum,
                        getCHartColor(catSum.categoryID)
                    ))
                }
                binding.piechart.startAnimation()
            }
        }

    }

    override fun onStart() {
        super.onStart()

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
            viewModel.setChartData(startDate,endDate)
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