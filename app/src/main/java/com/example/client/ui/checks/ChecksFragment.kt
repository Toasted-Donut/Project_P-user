package com.example.client.ui.checks

import android.animation.Animator
import android.animation.LayoutTransition

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ItemAnimator
import com.example.client.R
import com.example.client.data.models.Check
import com.example.client.databinding.FragmentChecksBinding
import com.example.client.ui.MainActivity
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.CoroutineContext

import kotlin.time.Duration.Companion.days

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChecksFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChecksFragment : Fragment(), CoroutineScope {
    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var binding: FragmentChecksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       // Toast.makeText(activity,"gg", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mainActivity = activity as MainActivity
        binding = FragmentChecksBinding.inflate(layoutInflater)
        binding.recViewChecks.layoutManager = LinearLayoutManager(activity)
        binding.recViewChecks.adapter = mainActivity.checksRecyclerAdapter
        binding.pullToRefresh.setOnRefreshListener {
            launch {
                addBlankTest(mainActivity)
            }
            binding.pullToRefresh.isRefreshing = false
        }
        return binding.root
    }
    suspend fun addBlankTest(mainActivity: MainActivity){
        val cal = Calendar.getInstance()
        mainActivity.checksViewModel.insertCheck(Check(
            id=cal.timeInMillis,
            date = String.format(
                "${cal.get(Calendar.DAY_OF_MONTH)}/${cal.get(Calendar.MONTH)}/${cal.get(Calendar.YEAR)}"),
            filepath = ""))
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ChecksFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            ChecksFragment().apply {

            }
    }
}