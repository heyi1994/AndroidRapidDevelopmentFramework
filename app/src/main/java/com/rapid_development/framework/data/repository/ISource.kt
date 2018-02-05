package com.rapid_development.framework.data.repository

import com.rapid_development.framework.data.bean.AppConfig
import com.rapid_development.framework.data.bean.BaseResponse
import io.reactivex.Observable
import retrofit2.http.*

/**
 * #### 定义方法 ;
 * @author Heyi
 * @since 1.0.0
 */
interface ISource {

    fun getAppConfig():Observable<BaseResponse<AppConfig>>
}