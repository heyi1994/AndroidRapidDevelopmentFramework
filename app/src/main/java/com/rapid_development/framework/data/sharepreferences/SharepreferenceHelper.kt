package com.rapid_development.framework.data.sharepreferences

import android.annotation.SuppressLint
import android.app.Application
import com.google.gson.Gson

/**
 * @author Heyi
 * @since 1.0.0
 */
object SharepreferenceHelper {
    @SuppressLint("StaticFieldLeak")
    private var mSp:Sharepreference?=null

    private val mGson = Gson()
    fun init(application: Application){
       mSp = Sharepreference.init(application,SharepreferenceConsts.FILE_NAME)
    }

    fun setIsFirstRun(isFirstRun:Boolean) = mSp?.set(SharepreferenceConsts.KEY_IS_FIRST_RUN,isFirstRun)

    fun isFirstRun() = if (mSp==null)throw IllegalStateException("This value is not initialized!") else mSp!!.get(SharepreferenceConsts.KEY_IS_FIRST_RUN,true)

}