package com.maradroid.dummyresponsegenerator.base

import android.content.Context
import com.maradroid.dummyresponsegenerator.BuildConfig
import com.maradroid.dummyresponsegenerator.base.interactor.Interactor
import com.maradroid.dummyresponsegenerator.base.interactor.InteractorImpl
import com.maradroid.dummyresponsegenerator.utils.*
import okhttp3.*

class DRGInterceptor(context: Context, val mediaType: String): Interceptor {

    private val interactor: Interactor = InteractorImpl(context)
    private val sharedPreferences = SharedPerfRepo(context)
    private val contentType: String

    init {
        if (mediaType == MEDIATYPE_JSON)
            contentType = CONTENT_TYPE_JSON
        else if (mediaType == MEDIATYPE_XML)
            contentType = CONTENT_TYPE_XML
        else
            contentType = "text/*"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        return if (sharedPreferences.useDummyResponse()) {
            val requestUrl = chain.request().url().toString().replace("/", "_")
            var dummyName = ""
            interactor.getAllDummyFileNames().forEach {
                if (requestUrl == it)
                    dummyName = it
            }
            if (!dummyName.isEmpty()) {
                val responseString = interactor.getResponseSync(dummyName)
                val offlineResponse = Response.Builder().body(ResponseBody.create( MediaType.parse(mediaType), responseString))
                        .code(200)
                        .message("OK")
                        .request(chain.request())
                        .protocol(Protocol.HTTP_1_1)
                        .addHeader("content-type", contentType)
                offlineResponse.build()
                /*val response = chain.proceed(chain.request())
                val body = ResponseBody.create(response.body()?.contentType(), responseString)
                response.newBuilder().body(body).build()*/
            } else {
                chain.proceed(chain.request())
            }
        } else {
            chain.proceed(chain.request())
        }
    }
}