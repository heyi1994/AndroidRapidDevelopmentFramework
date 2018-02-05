package com.rapid_development.framework.data.repository

import android.os.Build
import android.util.Log
import com.rapid_development.framework.data.Consts
import com.rapid_development.framework.data.bean.BaseResponse
import com.rapid_development.framework.data.bean.Test
import com.rapid_development.framework.data.log.L
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Heyi
 * @since 1.0.0
 */
class RetrofitSource private constructor():ISource{
    companion object {
        private var mSource:ISource?=null

        fun getInstance():ISource{
            synchronized(this){
                if (mSource == null) mSource=RetrofitSource()
            }
            return  mSource!!
        }
    }
    private val TAG:String=this.javaClass.simpleName
    private var mApi:RetrofitApi
    init {
        var okHttpClient = OkHttpClient()
        val interceptor = Interceptor {
            chain ->
            val request = chain.request()
            val response = chain.proceed(request)
            L.d(TAG, "request url:" + request.url())
            response
        }
        val clientBuilder = okHttpClient.newBuilder()
                .addNetworkInterceptor { chain ->
                    var request = chain.request()
                    val builder = request.url().newBuilder()
                    request = request.newBuilder().url(builder.build()).build()
                    chain.proceed(request)
                }
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .readTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)

        okHttpClient = clientBuilder.build()

        val mRetrofit = Retrofit.Builder()
                .baseUrl(Consts.WEB_BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        mApi = mRetrofit.create(RetrofitApi::class.java)
    }

    override fun getUserInfo() = mApi.getUserInfo()
}