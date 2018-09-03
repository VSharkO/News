package com.maradroid.dummyresponsegenerator.response

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maradroid.dummyresponsegenerator.R
import com.maradroid.dummyresponsegenerator.base.RecyclerAdapter
import com.maradroid.dummyresponsegenerator.base.interactor.Interactor
import com.maradroid.dummyresponsegenerator.base.interactor.InteractorImpl
import com.maradroid.dummyresponsegenerator.response.item_decorator.ResponseItemDecorator
import com.maradroid.dummyresponsegenerator.response.presenter.ResponseFragmentPresenter
import com.maradroid.dummyresponsegenerator.response.presenter.ResponseFragmentPresenterImpl
import com.maradroid.dummyresponsegenerator.utils.RESOURCES_PATH
import kotlinx.android.synthetic.main.fragment_response.*

const val EXTRA_FILENAME = "EXTRA_FILENAME"

class ResponseFragment: Fragment(), ResponseFragmentView {

    lateinit var interactor: InteractorImpl
    lateinit var presenter: ResponseFragmentPresenter
    lateinit var adapter: RecyclerAdapter

    companion object {
        fun newInstance(fileName: String): ResponseFragment {
            val fragment = ResponseFragment()
            val bundle = Bundle()
            bundle.putString(EXTRA_FILENAME, fileName)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO get arguments
        interactor = InteractorImpl(context!!)
        presenter = ResponseFragmentPresenterImpl(this, interactor as Interactor)
        adapter = RecyclerAdapter(this as ResponseFragmentView, presenter)
        presenter.initAdapter(adapter)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_response, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.fm = childFragmentManager
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(ResponseItemDecorator(context))
        recyclerView.adapter = adapter
        presenter.getData(arguments!!.getString(EXTRA_FILENAME))
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }

    override fun showError(message: String?) {
        AlertDialog.Builder(context!!).setTitle("Error").setMessage(message).setPositiveButton("ok", null).create().show()
    }

    fun saveResponse() {
        presenter.saveResponse()
    }
}

interface ResponseFragmentView {
    fun showError(message: String?)
}
