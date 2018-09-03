package com.maradroid.dummyresponsegenerator.base.interactor

import android.content.Context
import android.os.Environment
import com.maradroid.dummyresponsegenerator.utils.RESOURCES_PATH
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.io.File

class InteractorImpl(val context: Context): Interactor{

    override fun getResponse(fileName: String): Observable<String> {
        return Observable.just(fileName).map {
            File(context.getExternalFilesDir(null), fileName).readText()
        }
    }

    override fun getResponseSync(fileName: String): String {
        return File(context.getExternalFilesDir(null), fileName).readText()
    }

    override fun initAllDummyResponse(): Observable<Boolean> {
        return if (isExternalStorageWritable()) {
            val observablesList = context.assets.list(RESOURCES_PATH).map {
                initDummyResponse(it)
            }
            Observable.zip(observablesList) {
                val failed = 0
                it.forEach {
                    it as Boolean
                    if (!it)
                        failed.plus(1)
                }
                failed == 0
            }
        } else {
            Observable.just(false)
        }
    }

    override fun initDummyResponse(fileName: String): Observable<Boolean> {
        return if (isExternalStorageWritable()) {
            Observable.just(fileName).map {
                context.assets.open("$RESOURCES_PATH/$it").use { inputStream ->
                    File(context.getExternalFilesDir(null), it).outputStream().use { output ->
                        inputStream.copyTo(output)
                        output.close()
                    }
                    inputStream.close()
                    true
                }
            }
        } else {
            Observable.just(false)
        }
    }

    override fun getDummyAndGeneratedResponses(): List<Observable<Array<String>>> = listOf(Observable.just(context.assets.list(RESOURCES_PATH)), Observable.just(context.getExternalFilesDir(null).list()))

    override fun deleteGeneratedResponse(fileName: String): Observable<Boolean> {
        return Observable.just(fileName).map {
            val file = File(context.getExternalFilesDir(null), it)
            if (file.exists())
                file.delete()
            else
                false
        }

    }

    override fun getAllDummyFileNames(): Array<String> = context.getExternalFilesDir(null).list()

    private fun isExternalStorageWritable(): Boolean = Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()

    override fun generateResponses(execute: Boolean) {
        if (execute) {
            initAllDummyResponse().subscribeOn(Schedulers.io()).subscribeBy(
                    onNext = { print("news: responses generated!!")}
            )
        }
    }
}

interface Interactor {
    fun getResponse(fileName: String): Observable<String>
    fun getResponseSync(fileName: String): String
    fun initDummyResponse(fileName: String): Observable<Boolean>
    fun initAllDummyResponse(): Observable<Boolean>
    fun getAllDummyFileNames(): Array<String>
    fun getDummyAndGeneratedResponses(): List<Observable<Array<String>>>
    fun deleteGeneratedResponse(fileName: String): Observable<Boolean>
    fun generateResponses(execute: Boolean)
}