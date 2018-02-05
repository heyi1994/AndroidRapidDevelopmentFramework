package com.rapid_development.framework.data.repository

import com.rapid_development.framework.data.bean.AppConfig
import com.rapid_development.framework.data.bean.BaseResponse
import io.reactivex.Observable
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * @author Heyi
 * @since 1.0.0
 */
interface RetrofitApi {

    @GET("app/config")
    fun getAppConfig():Observable<BaseResponse<AppConfig>>
}