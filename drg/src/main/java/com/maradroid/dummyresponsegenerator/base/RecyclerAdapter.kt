package com.maradroid.dummyresponsegenerator.base

import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maradroid.dummyresponsegenerator.R
import com.maradroid.dummyresponsegenerator.base.view_holders.ChooseItemHolder
import com.maradroid.dummyresponsegenerator.base.view_holders.ResponseBracketViewHolder
import com.maradroid.dummyresponsegenerator.base.view_holders.ResponseButtonViewHolder
import com.maradroid.dummyresponsegenerator.base.view_holders.ResponseViewHolder
import com.maradroid.dummyresponsegenerator.model.RecyclerWrapper

class RecyclerAdapter(val delegate: Any, val presenter: Any): RecyclerView.Adapter<RecyclerView.ViewHolder>(), RecyclerAdapterInterface {
    var fm: FragmentManager? = null
    var recyclerData = mutableListOf<RecyclerWrapper>()

    override fun getItemViewType(position: Int): Int = recyclerData[position].type

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.cell_response_item -> ResponseViewHolder(view, delegate, presenter, fm!!)
            R.layout.cell_response_button -> ResponseButtonViewHolder(view, delegate, presenter)
            R.layout.cell_response_brackets -> ResponseBracketViewHolder(view, delegate, presenter)
            R.layout.cell_text_item -> ChooseItemHolder(view, delegate)
            else -> {
                DummyViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int = recyclerData.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            when (getItemViewType(position)) {
                R.layout.cell_text_item -> {
                    holder as ChooseItemHolder
                    holder.itemChanged(recyclerData[position])
                }
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.cell_response_item -> {
                holder as ResponseViewHolder
                holder.bindData(recyclerData[position])
            }
            R.layout.cell_response_button -> {
                holder as ResponseButtonViewHolder
                holder.bindData(recyclerData[position])
            }
            R.layout.cell_response_brackets -> {
                holder as ResponseBracketViewHolder
                holder.bindData(recyclerData[position])
            }
            R.layout.cell_text_item -> {
                holder as ChooseItemHolder
                holder.bindData(recyclerData[position])
            }
        }
    }

    override fun setData(data: List<RecyclerWrapper>) {
        recyclerData.clear()
        recyclerData.addAll(data)
        notifyDataSetChanged()
    }

    override fun addItems(data: List<RecyclerWrapper>, position: Int) {
        recyclerData.addAll(position.plus(1), data)
        notifyItemRangeInserted(position + 1, data.size)
    }

    override fun getData(): List<RecyclerWrapper> = recyclerData

    override fun removeItems(indexToRemove: List<Int>) {
        indexToRemove.reversed().forEach {
            recyclerData.removeAt(it)
            notifyItemRemoved(it)
        }
    }

    override fun notifyChange(position: Int) {
        notifyItemChanged(position, true)
    }
}

class DummyViewHolder(view: View): RecyclerView.ViewHolder(view)

interface RecyclerAdapterInterface {
    fun setData(data: List<RecyclerWrapper>)
    fun addItems(data: List<RecyclerWrapper>, position: Int)
    fun getData(): List<RecyclerWrapper>
    fun removeItems(indexToRemove: List<Int>)
    fun notifyChange(position: Int)
}