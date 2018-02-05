package com.rapid_development.framework.data.repository

import com.rapid_development.framework.data.bean.BaseResponse
import com.rapid_development.framework.data.bean.Test
import io.reactivex.Observable

/**
 * @author Heyi
 * @since 1.0.0
 */
interface RetrofitApi {
    /**
     * #### 获取个人信息
     */
    fun getUserInfo(): Observable<BaseResponse<Test>>?
}