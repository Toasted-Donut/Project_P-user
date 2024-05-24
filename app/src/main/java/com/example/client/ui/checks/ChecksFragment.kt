package com.example.client.ui.checks

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.client.data.Mail
import com.example.client.databinding.FragmentChecksBinding
import com.example.client.ui.MainActivity
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext

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
    private lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // Toast.makeText(activity,"gg", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainActivity = activity as MainActivity
        binding = FragmentChecksBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recViewChecks.layoutManager = LinearLayoutManager(activity)
        binding.recViewChecks.adapter = mainActivity.checksRecyclerAdapter
        binding.pullToRefresh.setOnRefreshListener {
            launch {
                withContext(Dispatchers.IO) {
                    val props = Properties()
                    props.load((mainActivity).assets.open("mail.properties"))
                    Mail(props, mainActivity).getMessages()
                }
            }
            binding.pullToRefresh.isRefreshing = false
        }
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