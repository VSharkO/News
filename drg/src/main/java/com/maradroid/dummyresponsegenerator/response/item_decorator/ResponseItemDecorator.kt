package com.maradroid.dummyresponsegenerator.response.item_decorator

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.maradroid.dummyresponsegenerator.R
import com.maradroid.dummyresponsegenerator.base.RecyclerAdapter

class ResponseItemDecorator(context: Context?): RecyclerView.ItemDecoration() {

    var dp6 = 0

    init {
        if (context != null) {
            dp6 = context.resources.getDimensionPixelSize(R.dimen.dp6)
        }
    }

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        //super.getItemOffsets(outRect, view, parent, state)
        if (parent != null && outRect != null) {
            val position: Int = parent.getChildAdapterPosition(view)
            if (position != RecyclerView.NO_POSITION) {
                val adapter = parent.adapter as RecyclerAdapter
                val level = adapter.recyclerData[position].level
                outRect.left = dp6 * level
            }
        }
    }
}