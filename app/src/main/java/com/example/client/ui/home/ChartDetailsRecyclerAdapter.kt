package com.example.client.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.client.databinding.StatItemBinding
import com.example.client.data.getCHartColor
import com.example.client.data.models.CategorySum


class ChartDetailsRecyclerAdapter : RecyclerView.Adapter<ChartDetailsRecyclerAdapter.DetailsViewHolder>() {
    private val details = ArrayList<CategorySum>()
    class DetailsViewHolder(val itemBinding: StatItemBinding) : RecyclerView.ViewHolder(itemBinding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val itemBinding = StatItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DetailsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        val detailInfo = details[position]
        holder.itemBinding.category.text = detailInfo.name
        holder.itemBinding.sum.text = detailInfo.sum.toString()
        holder.itemBinding.color.setBackgroundColor(getCHartColor(detailInfo.categoryID))
    }

    override fun getItemCount(): Int {
        return details.size
    }

    fun updateList(newList: List<CategorySum>){
        details.clear()
        details.addAll(newList)
        notifyDataSetChanged()
    }

}