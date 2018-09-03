package com.maradroid.dummyresponsegenerator.choose_response

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maradroid.dummyresponsegenerator.MainDelegate
import com.maradroid.dummyresponsegenerator.R
import com.maradroid.dummyresponsegenerator.base.RecyclerAdapter
import com.maradroid.dummyresponsegenerator.model.RecyclerWrapper
import kotlinx.android.synthetic.main.fragment_choose.*

class ChooseFragment: Fragment(), ChooseFragmentView {

    lateinit var adapter: RecyclerAdapter
    var delegate: MainDelegate? = null

    companion object {
        fun newInstance(): ChooseFragment {
            val fragment = ChooseFragment()
            val bundle = Bundle()
            //TODO add bundle arguments
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        delegate = context as MainDelegate
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = RecyclerAdapter(delegate!!, Any())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_choose, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        btnReinit.setOnClickListener {
            delegate?.showInitScreen()
        }
    }

    override fun onResume() {
        super.onResume()
        getDummyResponses()
    }

    private fun getDummyResponses() {
        val recyclerList = context!!.getExternalFilesDir("").list().map {
            RecyclerWrapper(R.layout.cell_text_item, it)
        }
        adapter.setData(recyclerList)
    }
}

interface ChooseFragmentView {

}
