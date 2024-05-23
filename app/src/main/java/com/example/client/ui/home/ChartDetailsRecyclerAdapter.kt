package com.example.client.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.client.databinding.StatItemBinding
import com.example.client.data.getCHartColor


class ChartDetailsRecyclerAdapter(private val details: ArrayList<DetailInfo>) : RecyclerView.Adapter<ChartDetailsRecyclerAdapter.DetailsViewHolder>() {
    data class DetailInfo(val _catNum: Int, val _sum: Float){
        val catNum = _catNum
        val sum = _sum
    }
    class DetailsViewHolder(private val itemBinding: StatItemBinding) : RecyclerView.ViewHolder(itemBinding.root){
        val categoryName: TextView = itemBinding.category
        val sum: TextView = itemBinding.sum
        val color: View = itemBinding.color
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val itemBinding = StatItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DetailsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        val detailInfo: DetailInfo = details[position]
        holder.categoryName.text = detailInfo.catNum.toString()
        holder.sum.text = detailInfo.sum.toString()
        holder.color.setBackgroundColor(getCHartColor(detailInfo.catNum))
    }

    override fun getItemCount(): Int {
        return details.size
    }
}