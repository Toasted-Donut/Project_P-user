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
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ItemAnimator
import com.example.client.R
import com.example.client.databinding.FragmentChecksBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChecksFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChecksFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var binding: FragmentChecksBinding
    private lateinit var checksRecyclerAdapter: ChecksRecyclerAdapter
    private lateinit var list: ArrayList<ChecksRecyclerAdapter.CheckInfo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list = fillList()
        checksRecyclerAdapter = ChecksRecyclerAdapter(list)
        binding = FragmentChecksBinding.inflate(layoutInflater)
        binding.recViewChecks.layoutManager = LinearLayoutManager(activity)
        binding.recViewChecks.adapter = checksRecyclerAdapter
//        list = fillList2()
//        checksRecyclerAdapter.updateList(fillList2())
        Toast.makeText(activity,"gg", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }
    private fun fillList(): ArrayList<ChecksRecyclerAdapter.CheckInfo> {
        val data = mutableListOf<ChecksRecyclerAdapter.CheckInfo>()
        (0..0).forEach { i -> data.add(i, ChecksRecyclerAdapter.CheckInfo("$i num","$i date")) }
        return data as ArrayList<ChecksRecyclerAdapter.CheckInfo>
    }
    private fun fillList2(): ArrayList<ChecksRecyclerAdapter.CheckInfo> {
        val data = mutableListOf<ChecksRecyclerAdapter.CheckInfo>()
        (0..30).forEach { i -> data.add(i, ChecksRecyclerAdapter.CheckInfo("$i num","$i date")) }
        return data as ArrayList<ChecksRecyclerAdapter.CheckInfo>
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