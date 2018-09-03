package com.maradroid.dummyresponsegenerator.base.view_holders

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.maradroid.dummyresponsegenerator.model.RecyclerWrapper
import com.maradroid.dummyresponsegenerator.response.presenter.ResponseFragmentPresenter
import com.maradroid.dummyresponsegenerator.utils.ELEMENT_ARRAY
import com.maradroid.dummyresponsegenerator.utils.ELEMENT_OBJECT
import kotlinx.android.synthetic.main.cell_response_button.view.*

class ResponseButtonViewHolder(view: View, delegate: Any, val presenter: Any): RecyclerView.ViewHolder(view), View.OnClickListener {

    lateinit var wrapper: RecyclerWrapper

    init {
        itemView.setOnClickListener(this)
    }

    fun bindData(data: RecyclerWrapper) {
        wrapper = data
        setTitle()
        if (data.level % 2 == 0) {
            itemView.vIndicator.setBackgroundColor(Color.BLUE)
        } else {
            itemView.vIndicator.setBackgroundColor(Color.RED)
        }
    }

    override fun onClick(p0: View?) {
        val position = adapterPosition
        if (position != RecyclerView.NO_POSITION) {
            if (presenter is ResponseFragmentPresenter) {
                val wrapperData = wrapper.data as MutableMap.MutableEntry<String, JsonElement>
                if (!wrapper.expanded) {
                    when (wrapper.jsonElementType) {
                        ELEMENT_ARRAY -> presenter.addJsonArrayData(position, wrapperData.value as JsonArray, wrapper.level.plus(1))
                        ELEMENT_OBJECT -> presenter.addJsonObjectData(position, wrapperData.value as JsonObject, wrapper.level.plus(1))
                    }
                    wrapper.expanded = true
                    setTitle()
                } else {
                    presenter.removeJsonData(position, wrapper.level)
                    wrapper.expanded = false
                    setTitle()
                }
            }
        }
    }

    private fun setTitle() {
        when {
            wrapper.jsonElementType == ELEMENT_ARRAY && !wrapper.expanded -> itemView.tvKey.text = "${wrapper.title} [..."
            wrapper.jsonElementType == ELEMENT_OBJECT && !wrapper.expanded-> itemView.tvKey.text = "${wrapper.title} {..."
            else -> when {
                wrapper.jsonElementType == ELEMENT_ARRAY -> itemView.tvKey.text = "${wrapper.title} ["
                wrapper.jsonElementType == ELEMENT_OBJECT -> itemView.tvKey.text = "${wrapper.title} {"
            }
        }
    }
}