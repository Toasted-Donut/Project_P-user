package com.example.client.ui

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.client.R
import com.example.client.data.Mail
import com.example.client.data.models.Category
import com.example.client.databinding.ActivityMainBinding
import com.example.client.ui.checks.ChecksRecyclerAdapter
import com.example.client.ui.checks.ChecksViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext


class MainActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var binding: ActivityMainBinding
    lateinit var checksViewModel: ChecksViewModel
    lateinit var checksRecyclerAdapter: ChecksRecyclerAdapter
    private lateinit var anim_rot_ccw: Animation
    private lateinit var anim_rot_cw: Animation

    private var job = Job()
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
        launch {
            checksViewModel.repository.save(Category(0,"Другое"))
            val props = Properties()
            withContext(Dispatchers.IO) {
                props.load((this@MainActivity).assets.open("mail.properties"))
            }
            Mail(props,this@MainActivity).getMessages()
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job
}