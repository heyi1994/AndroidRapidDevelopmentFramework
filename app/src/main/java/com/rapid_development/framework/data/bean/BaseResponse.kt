package com.rapid_development.framework.data.bean

/**
 * @author Heyi
 * @since 1.0.0
 */
data class BaseResponse<T> (val code:Int,val msg:String,val data:T)

internal object ResponseCode{
    val RESULT_CODE_SUCCESS=0
    val RESULT_CODE_TOKEN_EXPIRED=401
}