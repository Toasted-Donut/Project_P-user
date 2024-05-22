package com.example.client.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.client.R
import com.example.client.databinding.ActivityMainBinding
import com.example.client.ui.checks.ChecksRecyclerAdapter
import com.example.client.ui.checks.ChecksViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var checksViewModel: ChecksViewModel
    lateinit var checksRecyclerAdapter: ChecksRecyclerAdapter
    lateinit var anim_rot_ccw: Animation
    lateinit var anim_rot_cw: Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding  = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        anim_rot_cw = AnimationUtils.loadAnimation(this, R.anim.rotate_cw)
        anim_rot_ccw = AnimationUtils.loadAnimation(this, R.anim.rotate_ccw)

        checksViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[ChecksViewModel::class.java]
        checksRecyclerAdapter = ChecksRecyclerAdapter(anim_rot_cw,anim_rot_ccw)


        checksViewModel.allChecks.observe(this, Observer { list ->
            list?.let {
                checksRecyclerAdapter.updateList(it)
            }
        })
    }
}