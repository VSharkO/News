package com.maradroid.dummyresponsegenerator.base.view_holders

import android.support.v7.widget.RecyclerView
import com.maradroid.dummyresponsegenerator.model.RecyclerWrapper
import android.view.View
import com.maradroid.dummyresponsegenerator.MainDelegate
import com.maradroid.dummyresponsegenerator.init_responses.InitFragmentView
import com.maradroid.dummyresponsegenerator.init_responses.presenter.InitPresenterImpl
import kotlinx.android.synthetic.main.cell_text_item.view.*

class ChooseItemHolder(view: View, val delegate: Any): RecyclerView.ViewHolder(view), View.OnClickListener {

    lateinit var response: String

    init {
        itemView.setOnClickListener(this)
    }

    fun bindData(data: RecyclerWrapper) {
        response = data.title
        itemView.tvItemText.text = data.title
        itemView.itemSwitch.isChecked = data.level == 1
    }

    fun itemChanged(data: RecyclerWrapper) {
        itemView.itemSwitch.isChecked = data.level == 1
    }

    override fun onClick(p0: View?) {
        if (delegate is InitPresenterImpl) {
            if (itemView.itemSwitch.isChecked)
                delegate.deleteResponse(response, adapterPosition)
            else
                delegate.generateResponse(response, adapterPosition)
        } else if (delegate is MainDelegate) {
            delegate.showResponse(itemView.tvItemText.text.toString())
        }
    }
}