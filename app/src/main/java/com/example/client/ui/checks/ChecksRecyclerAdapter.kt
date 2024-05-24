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


class ChecksRecyclerAdapter(private val anim_rot_cw: Animation, private val anim_rot_ccw: Animation) : RecyclerView.Adapter<ChecksRecyclerAdapter.ChecksViewHolder>() {

    private var expandedHolderPosition = -1
    private val allChecks = ArrayList<Check>()
    private var getViewHolder: (position: Int) -> ChecksViewHolder? = { null }
    inner class ChecksViewHolder(private val itemBinding: CheckTemplateBinding) : RecyclerView.ViewHolder(itemBinding.root){
        private val isExpanded get() = adapterPosition == expandedHolderPosition
        fun bindItem(check: Check) {
            itemBinding.checkNum.text = check.id.toString()
            itemBinding.checkDate.text = check.date
            itemBinding.checkExtra.loadUrl(check.filepath!!)
            if (isExpanded){
                itemBinding.checkExtra.visibility = View.VISIBLE
            }
            else{
                itemBinding.checkExtra.visibility = View.GONE
            }
            itemBinding.btnExpand.setOnClickListener {
                val  position = adapterPosition
                notifyItemChanged(position)
                if (isExpanded){
                    itemBinding.btnExpand.startAnimation(anim_rot_ccw)
                    expandedHolderPosition = -1
                }
                else{
                    itemBinding.btnExpand.startAnimation(anim_rot_cw)
                    expandedHolderPosition = position
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChecksViewHolder {
        val itemBinding = CheckTemplateBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        itemBinding.checkExtra.settings.allowFileAccess = true
        itemBinding.checkExtra.settings.loadWithOverviewMode = true
        itemBinding.checkExtra.settings.useWideViewPort = true
        itemBinding.checkExtra.settings.builtInZoomControls = true
        itemBinding.checkExtra.settings.displayZoomControls = false
        itemBinding.checkExtra.setInitialScale(100)
        return ChecksViewHolder(itemBinding)
    }
    override fun onBindViewHolder(holder: ChecksViewHolder, position: Int) {
        val check: Check = allChecks[position]
        holder.bindItem(check)
    }
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        getViewHolder = { position ->
            recyclerView.findViewHolderForAdapterPosition(position) as? ChecksViewHolder
        }
    }
    override fun getItemCount(): Int {
        return allChecks.size
    }
    fun updateList(newList: List<Check>){
        this.expandedHolderPosition = -1
        this.allChecks.clear()
        this.allChecks.addAll(newList)
        this.notifyDataSetChanged()
    }
}