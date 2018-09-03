package com.maradroid.dummyresponsegenerator.response.presenter

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.maradroid.dummyresponsegenerator.*
import com.maradroid.dummyresponsegenerator.base.RecyclerAdapterInterface
import com.maradroid.dummyresponsegenerator.model.RecyclerWrapper
import com.maradroid.dummyresponsegenerator.response.ResponseFragmentView
import com.maradroid.dummyresponsegenerator.base.interactor.Interactor
import com.maradroid.dummyresponsegenerator.utils.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function3
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class ResponseFragmentPresenterImpl(val view: ResponseFragmentView, val interactor: Interactor): ResponseFragmentPresenter {

    var adapter: RecyclerAdapterInterface? = null
    lateinit var responseMap: MutableMap<String, JsonElement>
    var disposable: Disposable? = null

    lateinit var testJsonObject: JsonObject

    override fun initAdapter(adapter: RecyclerAdapterInterface) {
        this.adapter = adapter
        val secondObject = JsonObject()
        secondObject.add("secondElement", JsonPrimitive("pocetniString"))
        testJsonObject = JsonObject()
        testJsonObject.add("firstElement", secondObject)
    }

    /*override fun getData(fileName: String) {

        val json = "{\"menu\": {\n" +
                "    \"header\": \"SVG Viewer\",\n" +
                "    \"items\": [\n" +
                "        {\"id\": \"Open\"},\n" +
                "        {\"id\": \"OpenNew\", \"label\": \"Open New\"},\n" +
                "        null,\n" +
                "        {\"id\": \"ZoomIn\", \"label\": \"Zoom In\"},\n" +
                "        {\"id\": \"ZoomOut\", \"label\": \"Zoom Out\"},\n" +
                "        {\"id\": \"OriginalView\", \"label\": \"Original View\"},\n" +
                "        null,\n" +
                "        {\"id\": \"Quality\"},\n" +
                "        {\"id\": \"Pause\"},\n" +
                "        {\"id\": \"Mute\"},\n" +
                "        null,\n" +
                "        {\"id\": \"Find\", \"label\": \"Find...\"},\n" +
                "        {\"id\": \"FindAgain\", \"label\": \"Find Again\"},\n" +
                "        {\"id\": \"Copy\"},\n" +
                "        {\"id\": \"CopyAgain\", \"label\": \"Copy Again\"},\n" +
                "        {\"id\": \"CopySVG\", \"label\": \"Copy SVG\"},\n" +
                "        {\"id\": \"ViewSVG\", \"label\": \"View SVG\"},\n" +
                "        {\"id\": \"ViewSource\", \"label\": \"View Source\"},\n" +
                "        {\"id\": \"SaveAs\", \"label\": \"Save As\"},\n" +
                "        null,\n" +
                "        {\"id\": \"Help\"},\n" +
                "        {\"id\": \"About\", \"label\": \"About Adobe CVG Viewer...\"}\n" +
                "    ],\n" +
                "\"window\": {\n" +
                "        \"title\": \"Sample Konfabulator Widget\",\n" +
                "        \"name\": \"main_window\",\n" +
                "        \"width\": 500,\n" +
                "        \"height\": 500\n" +
                "    },\n" +
                "    \"image\": { \n" +
                "        \"src\": \"Images/Sun.png\",\n" +
                "        \"name\": \"false\",\n" +
                "        \"hOffset\": 250,\n" +
                "        \"vOffset\": 250,\n" +
                "        \"alignment\": \"center\"\n" +
                "    },\n" +
                "    \"text\": {\n" +
                "        \"data\": \"Click Here\",\n" +
                "        \"size\": 36,\n" +
                "        \"style\": \"bold\",\n" +
                "        \"name\": \"text1\",\n" +
                "        \"hOffset\": 250,\n" +
                "        \"vOffset\": 100,\n" +
                "        \"alignment\": \"center\",\n" +
                "        \"onMouseUp\": \"sun1.opacity = (sun1.opacity / 100) * 90;\"\n" +
                "    }\n" +
                "}}"
        parseData()
    }*/

    override fun getData(fileName: String) {
        disposable = interactor.getResponse(fileName).map {
            val recyclerList = mutableListOf<RecyclerWrapper>()
            val parsedMap: MutableMap<String, JsonElement> = Gson().fromJson(it, object : TypeToken<MutableMap<String, JsonElement>>(){}.type)
            recyclerList.add(RecyclerWrapper(R.layout.cell_response_brackets, "{", 0))

            parsedMap.forEach {
                parseJsonElement(recyclerList, 0, it as MutableMap.MutableEntry<String, JsonElement>)
            }

            recyclerList.add(RecyclerWrapper(R.layout.cell_response_brackets, "}", 0))
            Pair<MutableMap<String, JsonElement>, List<RecyclerWrapper>>(parsedMap, recyclerList)
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeBy( onNext = {responseMap = it.first; adapter?.setData(it.second)}, onError = {view.showError(it.message)})
    }

    override fun addJsonObjectData(position: Int, jsonObject: JsonObject, levelToApply: Int) {
        disposable = Observable.zip(Observable.just(position), Observable.just(jsonObject), Observable.just(levelToApply), Function3<Int, JsonObject, Int, List<RecyclerWrapper>>{
            _, _, _ ->
            val recyclerList = mutableListOf<RecyclerWrapper>()
            jsonObject.entrySet().forEach {
                parseJsonElement(recyclerList, levelToApply, it)
            }
            recyclerList.add(RecyclerWrapper(R.layout.cell_response_brackets, "}", levelToApply))
            recyclerList
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeBy( onNext = {adapter?.addItems(it, position)}, onError = {view.showError(it.message)})
    }

    override fun addJsonArrayData(position: Int, jsonArray: JsonArray, levelToApply: Int) {
        disposable = Observable.zip(Observable.just(position), Observable.just(jsonArray), Observable.just(levelToApply), Function3<Int, JsonArray, Int, List<RecyclerWrapper>>{
            _, _, _ ->
            val recyclerList = mutableListOf<RecyclerWrapper>()
            jsonArray.forEach {
                if (it is JsonObject) {
                    recyclerList.add(RecyclerWrapper(R.layout.cell_response_brackets, "{", levelToApply))
                    it.entrySet().forEach {
                        parseJsonElement(recyclerList, levelToApply, it)
                    }
                    recyclerList.add(RecyclerWrapper(R.layout.cell_response_brackets, "}", levelToApply))
                }
            }
            recyclerList.add(RecyclerWrapper(R.layout.cell_response_brackets, "]", levelToApply))
            recyclerList
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeBy(onNext = {adapter?.addItems(it, position)}, onError = {view.showError(it.message)})
    }

    private fun parseJsonElement(recyclerList: MutableList<RecyclerWrapper>, level: Int, entry: MutableMap.MutableEntry<String, JsonElement>) {
        val element = entry.value
        when (element) {
            is JsonPrimitive -> {
                when {
                    element.isBoolean -> recyclerList.add(RecyclerWrapper(R.layout.cell_response_item, entry, ELEMENT_PRIMITIVE_BOOLEAN, entry.key, level))
                    element.isNumber -> recyclerList.add(RecyclerWrapper(R.layout.cell_response_item, entry, ELEMENT_PRIMITIVE_NUMBER, entry.key, level))
                    element.isString -> recyclerList.add(RecyclerWrapper(R.layout.cell_response_item, entry, ELEMENT_PRIMITIVE_STRING, entry.key, level))
                }
            }
            is JsonArray -> recyclerList.add(RecyclerWrapper(R.layout.cell_response_button, entry, ELEMENT_ARRAY, entry.key, level, false))
            is JsonObject -> recyclerList.add(RecyclerWrapper(R.layout.cell_response_button, entry, ELEMENT_OBJECT, entry.key, level, false))
            is JsonNull -> recyclerList.add(RecyclerWrapper(R.layout.cell_response_item, entry, ELEMENT_NULL, entry.key, level))
        }
    }

    override fun removeJsonData(position: Int, level: Int) {
        val recyclerData = adapter?.getData()
        val indexToRemove = mutableListOf<Int>()
        if (recyclerData != null) {
            for (i in position.plus(1)..recyclerData.size.minus(1)) {
                if (recyclerData[i].level > level)
                    indexToRemove.add(i)
                else if (recyclerData[i].level <= level)
                    break
            }

            adapter?.removeItems(indexToRemove)
        }
    }

    override fun dispose() {
        disposable?.dispose()
    }

    override fun saveResponse() {
        var vds = 4 + 6
    }
}

interface ResponseFragmentPresenter {
    fun initAdapter(adapter: RecyclerAdapterInterface)
    fun getData(fileName: String)
    fun addJsonObjectData(position: Int, jsonObject: JsonObject, levelToApply: Int)
    fun addJsonArrayData(position: Int, jsonArray: JsonArray, levelToApply: Int)
    fun removeJsonData(position: Int, level: Int)
    fun saveResponse()
    fun dispose()
}