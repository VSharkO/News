package com.maradroid.dummyresponsegenerator.init_responses.presenter

import com.maradroid.dummyresponsegenerator.R
import com.maradroid.dummyresponsegenerator.base.RecyclerAdapterInterface
import com.maradroid.dummyresponsegenerator.base.interactor.Interactor
import com.maradroid.dummyresponsegenerator.init_responses.InitFragmentView
import com.maradroid.dummyresponsegenerator.init_responses.STRING_ALL
import com.maradroid.dummyresponsegenerator.model.RecyclerWrapper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class InitPresenterImpl(val view: InitFragmentView, val interactor: Interactor): InitFragmentPresenter {

    var adapter: RecyclerAdapterInterface? = null
    var disposable: Disposable? = null

    override fun initAdapter(adapter: RecyclerAdapterInterface) {
        this.adapter = adapter
    }

    override fun generateResponse(responseName: String, position: Int) {
        // TODO loader
        disposable = if (responseName == STRING_ALL) {
            interactor.initAllDummyResponse().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                            onNext = {view.showPopup("Success", "Response successfully generated!")},
                            onError = {view.showPopup("Error", it.message!!)}
                    )
        } else {
            interactor.initDummyResponse(responseName).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                            onNext = {adapter?.getData()?.get(position)?.level = 1; adapter?.notifyChange(position)},
                            onError = {view.showPopup("Error", it.message!!)}
                    )
        }
    }

    override fun prepareRecyclerData() {
        //TODO loader
        disposable = Observable.zip(interactor.getDummyAndGeneratedResponses()) {
            val dummyResponses = it[0] as Array<String>
            val generatedResponses = it[1] as Array<String>
            dummyResponses.map { dummy ->
                val responseGenerated = generatedResponses.contains(dummy)
                RecyclerWrapper(R.layout.cell_text_item, dummy, if (responseGenerated) 1 else 0)
            }
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeBy(
                onNext = { adapter?.setData(it) },
                onError = {view.showPopup("Error", it.message!!)}
        )
    }

    override fun deleteResponse(fileName: String, position: Int) {
        //TODO loader
        disposable = interactor.deleteGeneratedResponse(fileName).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {adapter?.getData()?.get(position)?.level = 0; adapter?.notifyChange(position)},
                        onError = {view.showPopup("Error", it.message!!)}
                )
    }

    override fun dispose() {
        disposable?.dispose()
    }
}

interface InitFragmentPresenter {
    fun initAdapter(adapter: RecyclerAdapterInterface)
    fun generateResponse(responseName: String, position: Int)
    fun prepareRecyclerData()
    fun deleteResponse(fileName: String, position: Int)
    fun dispose()
}