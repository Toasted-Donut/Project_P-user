package com.example.client.ui.checks

import android.animation.LayoutTransition
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.client.R
import com.example.client.databinding.CheckTemplateBinding

class ChecksRecyclerAdapter(private val checks: ArrayList<CheckInfo>) : RecyclerView.Adapter<ChecksRecyclerAdapter.ChecksViewHolder>() { //replace any

    class ChecksViewHolder(private val itemBinding: CheckTemplateBinding) : RecyclerView.ViewHolder(itemBinding.root){

        val num = itemBinding.checkNum
        val date = itemBinding.checkDate
        val btn = itemBinding.btnExpand
        val checkExtra = itemBinding.checkExtra

        fun bind(checkInfo: CheckInfo){
            num.text = checkInfo.num
            date.text = checkInfo.date
        }


    }

    data class CheckInfo(val _num: String, val _date: String) {
        val num: String = _num
        val date: String = _date
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChecksViewHolder {
        anim_rot_cw = AnimationUtils.loadAnimation(parent.context, R.anim.rotate_cw)
        anim_rot_ccw = AnimationUtils.loadAnimation(parent.context, R.anim.rotate_ccw)
        val itemBinding = CheckTemplateBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        itemBinding.btnExpand.setOnClickListener {
            if (itemBinding.checkExtra.isVisible){
                itemBinding.checkExtra.visibility = View.GONE
                itemBinding.btnExpand.startAnimation(ChecksRecyclerAdapter.anim_rot_ccw)
            }
            else{
                itemBinding.checkExtra.visibility = View.VISIBLE
                itemBinding.btnExpand.startAnimation(anim_rot_cw)
            }
        }
        return ChecksViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return checks.size
    }

    override fun onBindViewHolder(holder: ChecksViewHolder, position: Int) {
        val checkInfo: CheckInfo = checks[position]
        holder.num.text = checkInfo.num
        holder.date.text = checkInfo.date
    }

    fun updateList(newList: List<CheckInfo>){
        checks.clear()
        checks.addAll(newList)
        notifyDataSetChanged()
    }
    companion object {
        lateinit var anim_rot_ccw: Animation
        lateinit var anim_rot_cw: Animation
    }


}