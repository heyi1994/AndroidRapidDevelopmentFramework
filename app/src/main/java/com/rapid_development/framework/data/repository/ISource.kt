package com.rapid_development.framework.data.repository

import com.rapid_development.framework.data.bean.BaseResponse
import com.rapid_development.framework.data.bean.Test
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * #### 定义方法 ;
 * @author Heyi
 * @since 1.0.0
 */
interface ISource {
    @GET("")
   fun getUserInfo():Observable<BaseResponse<Test>>?
}