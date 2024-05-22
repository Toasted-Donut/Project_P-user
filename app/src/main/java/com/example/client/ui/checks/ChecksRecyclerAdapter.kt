package com.example.client.ui.checks

import android.animation.LayoutTransition
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.client.R
import com.example.client.data.models.Check
import com.example.client.databinding.CheckTemplateBinding

class ChecksRecyclerAdapter(private val anim_rot_cw: Animation, private val anim_rot_ccw: Animation) : RecyclerView.Adapter<ChecksRecyclerAdapter.ChecksViewHolder>() { //replace any

    private val allChecks = ArrayList<Check>()
    class ChecksViewHolder(val itemBinding: CheckTemplateBinding) : RecyclerView.ViewHolder(itemBinding.root){}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChecksViewHolder {
        val itemBinding = CheckTemplateBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        itemBinding.btnExpand.setOnClickListener {
            if (itemBinding.checkExtra.isVisible){
                itemBinding.checkExtra.visibility = View.GONE
                itemBinding.btnExpand.startAnimation(anim_rot_ccw)
            }
            else{
                itemBinding.checkExtra.visibility = View.VISIBLE
                itemBinding.btnExpand.startAnimation(anim_rot_cw)
            }
        }
        return ChecksViewHolder(itemBinding)
    }
    override fun onBindViewHolder(holder: ChecksViewHolder, position: Int) {
        val check: Check = allChecks[position]
        holder.itemBinding.checkNum.text = check.id.toString()
        holder.itemBinding.checkDate.text = check.date
        holder.itemBinding.checkExtra.loadUrl(check.filepath.toString())
    }
    override fun getItemCount(): Int {
        return allChecks.size
    }
    fun updateList(newList: List<Check>){
        allChecks.clear()
        allChecks.addAll(newList)
        notifyDataSetChanged()
    }
}