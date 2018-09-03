package com.maradroid.dummyresponsegenerator.base.view_holders

import android.graphics.Color
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.google.gson.*
import com.maradroid.dummyresponsegenerator.R
import com.maradroid.dummyresponsegenerator.model.RecyclerWrapper
import com.maradroid.dummyresponsegenerator.response.edit_dialog.DialogFragmentInterface
import com.maradroid.dummyresponsegenerator.response.edit_dialog.EditDialogFragment
import com.maradroid.dummyresponsegenerator.utils.*
import kotlinx.android.synthetic.main.cell_response_item.view.*
import java.text.NumberFormat

class ResponseViewHolder(view: View, delegate: Any, presenter: Any, val fm: FragmentManager): RecyclerView.ViewHolder(view), View.OnClickListener, DialogFragmentInterface {

    lateinit var wrapper: RecyclerWrapper

    init {
        itemView.setOnClickListener(this)
    }

    fun bindData(data: RecyclerWrapper) {
        wrapper = data
        val wrapperData = wrapper.data as MutableMap.MutableEntry<String, JsonElement>
        itemView.tvKey.text = data.title
        when (data.jsonElementType) {
            ELEMENT_PRIMITIVE_BOOLEAN,
            ELEMENT_PRIMITIVE_NUMBER,
            ELEMENT_PRIMITIVE_STRING -> itemView.tvValue.text = wrapperData.value.asString
            ELEMENT_NULL -> itemView.tvValue.text = "null"
        }
        if (data.level % 2 == 0) {
            itemView.vIndicator.setBackgroundColor(Color.BLUE)
        } else {
            itemView.vIndicator.setBackgroundColor(Color.RED)
        }
    }

    override fun onDialogOk(newValue: Any, type: Int) {
        //TODO handle ELEMENT_NULL
        val entry = wrapper.data as MutableMap.MutableEntry<String, JsonElement>
        val jsonElement = entry.value
        when (jsonElement) {
            is JsonPrimitive -> {
                when {
                    jsonElement.isBoolean -> entry.setValue(JsonPrimitive(newValue as Boolean))
                    jsonElement.isNumber -> {
                        when (type) {
                            DATA_DOUBLE -> entry.setValue(JsonPrimitive(newValue as Double))
                            DATA_LONG -> entry.setValue(JsonPrimitive(newValue as Long))
                        }
                    }
                    jsonElement.isString -> entry.setValue(JsonPrimitive(newValue as String))
                }
            }
            //is JsonNull -> recyclerList.add(RecyclerWrapper(R.layout.cell_response_item, element, ELEMENT_NULL, key, level))
        }
        itemView.tvValue.text = newValue.toString()
    }

    override fun onClick(p0: View?) {
        val dialog = EditDialogFragment.newInstance(wrapper.title, itemView.tvValue.text.toString(), wrapper.jsonElementType)
        dialog.listener = this
        dialog.show(fm, "")
    }
}