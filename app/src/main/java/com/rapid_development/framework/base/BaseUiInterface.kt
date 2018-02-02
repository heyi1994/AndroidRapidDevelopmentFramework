package com.rapid_development.framework.base

import android.app.Dialog
import android.content.Context
import com.rapid_development.framework.R

/**
 * #### 定义UI基类需要实现的接口 ;
 * @author Heyi
 * @since 1.0.0
 */
interface BaseUiInterface {

    /**
     * 数据请求发生网络异常时调用。
     */
     fun showNetworkException()

    /**
     * 发生Error但又不是网络异常时调用。
     */
     fun showUnknownException()

    /**
     * 数据成功返回但不是预期值时调用。
     */
     fun showDataException(msg: String)


     fun getContext():Context

}