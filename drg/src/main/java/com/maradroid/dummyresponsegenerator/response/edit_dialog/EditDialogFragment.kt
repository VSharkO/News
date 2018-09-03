package com.maradroid.dummyresponsegenerator.response.edit_dialog

import android.app.Dialog

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.View
import com.maradroid.dummyresponsegenerator.R
import com.maradroid.dummyresponsegenerator.utils.*
import kotlinx.android.synthetic.main.dialog_edit.*
import kotlinx.android.synthetic.main.dialog_edit.view.*

const val EXTRA_TITLE = "EXTRA_TITLE"
const val EXTRA_VALUE = "EXTRA_VALUE"
const val EXTRA_TYPE = "EXTRA_TYPE"

class EditDialogFragment: DialogFragment() {

    var listener: DialogFragmentInterface? = null
    var numberType: Int = -1

    companion object {
        fun newInstance(title: String, value: String, dataType: Int): EditDialogFragment {
            val fragment = EditDialogFragment()
            val bundle = Bundle()
            bundle.putString(EXTRA_TITLE, title)
            bundle.putString(EXTRA_VALUE, value)
            bundle.putInt(EXTRA_TYPE, dataType)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = activity!!.layoutInflater.inflate(R.layout.dialog_edit, null)
        val builder = AlertDialog.Builder(context!!).setView(view)
        view.tvKey.text = arguments!!.getString(EXTRA_TITLE)
        view.etValue.setText(arguments!!.getString(EXTRA_VALUE))
        setNumericType(arguments!!.getInt(EXTRA_TYPE))

        view.tvCancel.setOnClickListener {
            dismiss()
        }

        view.tvOk.setOnClickListener {
            formatAndSendInputData(view)
        }
        return builder.create()
    }

    private fun setNumericType(valueType: Int) {
        if (valueType == ELEMENT_PRIMITIVE_NUMBER) {
            numberType = if (arguments!!.getString(EXTRA_VALUE).contains(".")) {
                DATA_DOUBLE
            } else {
                DATA_LONG
            }
        }
    }

    private fun formatAndSendInputData(view: View) {
        val dataType = arguments!!.getInt(EXTRA_TYPE)
        when (dataType) {
            ELEMENT_PRIMITIVE_NUMBER -> {
                when (numberType) {
                    DATA_DOUBLE -> {
                        listener?.onDialogOk(view.etValue.text.toString().toDouble(), DATA_DOUBLE)
                        dismiss()
                    }
                    DATA_LONG -> {
                        listener?.onDialogOk(view.etValue.text.toString().toLong(), DATA_LONG)
                        dismiss()
                    }
                }
            }
            ELEMENT_PRIMITIVE_BOOLEAN -> {
                val inputString = view.etValue.text.toString()
                if (inputString == "false" || inputString == "true") {
                    listener?.onDialogOk(inputString.toBoolean(), DATA_BOOLEAN)
                    dismiss()
                }
            }
            ELEMENT_PRIMITIVE_STRING -> {
                listener?.onDialogOk(view.etValue.text.toString(), DATA_STRING)
                dismiss()
            }
        }
    }
}

interface DialogFragmentInterface {
    fun onDialogOk(newValue: Any, type: Int)
}