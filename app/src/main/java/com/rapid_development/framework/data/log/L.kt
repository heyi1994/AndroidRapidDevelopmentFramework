package com.rapid_development.framework.data.log

import android.util.Log

/**
 * #### Log的工具类 ;
 * @author Heyi
 * @since 1.0.0
 */
object L {
    private val isDebug = true

    fun d (tag:String,msg:String) { if (isDebug) Log.d(tag,msg) }

    fun i (tag: String,msg:String){ if (isDebug) Log.i(tag,msg)}

    fun w (tag: String,err:Throwable?){ if (isDebug) Log.w(tag,err)}

    fun e (tag: String,msg: String,err: Throwable?){if (isDebug) Log.e(tag,msg,err)}

}