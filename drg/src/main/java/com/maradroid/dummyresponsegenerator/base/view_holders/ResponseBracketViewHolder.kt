package com.maradroid.dummyresponsegenerator.base.view_holders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.maradroid.dummyresponsegenerator.model.RecyclerWrapper
import kotlinx.android.synthetic.main.cell_response_brackets.view.*

class ResponseBracketViewHolder(view: View, delegate: Any, presenter: Any): RecyclerView.ViewHolder(view) {

    fun bindData(data: RecyclerWrapper) {
        itemView.tvBracket.text = data.title
    }
}