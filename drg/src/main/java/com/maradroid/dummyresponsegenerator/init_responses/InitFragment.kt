package com.maradroid.dummyresponsegenerator.init_responses

import android.content.Context
import android.content.SharedPreferences
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
import com.maradroid.dummyresponsegenerator.init_responses.presenter.InitFragmentPresenter
import com.maradroid.dummyresponsegenerator.init_responses.presenter.InitPresenterImpl
import com.maradroid.dummyresponsegenerator.utils.DUMMY_KEY
import com.maradroid.dummyresponsegenerator.utils.DUMMY_SHARED_PERF
import com.maradroid.dummyresponsegenerator.utils.SharedPerfRepo
import kotlinx.android.synthetic.main.fragment_init.*

const val STRING_ALL = "All"

class InitFragment: Fragment(), InitFragmentView {

    lateinit var presenter: InitFragmentPresenter
    lateinit var adapter: RecyclerAdapter
    lateinit var interactor: Interactor
    lateinit var sharedPerf: SharedPerfRepo

    companion object {
        fun newInstance(): InitFragment {
            val fragment = InitFragment()
            val bundle = Bundle()
            //TODO add bundle arguments
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        interactor = InteractorImpl(context!!)
        presenter = InitPresenterImpl(this, interactor)
        adapter = RecyclerAdapter(presenter, Any())
        presenter.initAdapter(adapter)
        sharedPerf = SharedPerfRepo(context!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_init, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        presenter.prepareRecyclerData()

        if (sharedPerf.useDummyResponse()) {
            recyclerView.visibility = View.VISIBLE
            tvLabel.visibility = View.VISIBLE
            mainSwitch.isChecked = true
        }

        mainContainer.setOnClickListener {
            val checked = !mainSwitch.isChecked
            mainSwitch.isChecked = checked
            sharedPerf.setDummyResponse(checked)
            if (checked) {
                recyclerView.visibility = View.VISIBLE
                tvLabel.visibility = View.VISIBLE
                presenter.prepareRecyclerData()
            } else {
                recyclerView.visibility = View.GONE
                tvLabel.visibility = View.GONE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }

    override fun showPopup(title: String, message: String) {
        AlertDialog.Builder(context!!).setTitle(title).setMessage(message).setPositiveButton("ok", null).create().show()
    }
}

interface InitFragmentView {
    fun showPopup(title: String, message: String)

}