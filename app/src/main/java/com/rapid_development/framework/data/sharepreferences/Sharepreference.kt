package com.rapid_development.framework.data.sharepreferences

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.text.TextUtils
import java.lang.IllegalArgumentException

/**
 * @author Heyi
 * @since 1.0.0
 */
class Sharepreference private constructor(private val context: Application,private val fileName:String){
    companion object {
        @SuppressLint("StaticFieldLeak")
        private var mSharepreference:Sharepreference?=null
        fun init(application: Application,fileName: String):Sharepreference{
            if (TextUtils.isEmpty(fileName))throw IllegalArgumentException("the file's name is empty !")

            synchronized(this){
                if (mSharepreference == null){
                    mSharepreference = Sharepreference(application,fileName)
                }
            }
            return  mSharepreference!!
        }
    }


    private fun getSharepreference() =  context.getSharedPreferences(fileName,Context.MODE_PRIVATE)

    fun <T> set (key:String,msg:T) {
            if (msg is Int)getSharepreference().edit().putInt(key,msg as Int).apply()
            else if (msg is Float)getSharepreference().edit().putFloat(key,msg as Float).apply()
            else if (msg is Boolean)getSharepreference().edit().putBoolean(key,msg as Boolean).apply()
            else if (msg is String)getSharepreference().edit().putString(key,msg as String).apply()
            else if (msg is Long)getSharepreference().edit().putLong(key,msg as Long).apply()
            else throw IllegalArgumentException("Unsupported parameter types !")
    }


    fun <T> get(key: String,defaultValue:T):T{
        val   result:Any
            if (defaultValue is Int)result = getSharepreference().getInt(key,defaultValue as Int)
            else if (defaultValue is Float)result = getSharepreference().getFloat(key,defaultValue as Float)
            else if (defaultValue is Boolean)result = getSharepreference().getBoolean(key,defaultValue as Boolean)
            else if (defaultValue is String)result = getSharepreference().getString(key,defaultValue as String)
            else if (defaultValue is Long)result = getSharepreference().getLong(key,defaultValue as Long)
            else throw IllegalArgumentException("Unsupported parameter types !")

            return  result as T
    }

    fun getInt(key: String) = get(key,SharepreferenceConsts.DEFAULT_INT_VAULE)
    fun getFloat(key: String) = get(key,SharepreferenceConsts.DEFAULT_FLOAT_VAULE)
    fun getBoolean(key: String) =get(key,SharepreferenceConsts.DEFAULT_BOOLEAN_VAULE)
    fun getString(key: String) =get(key,SharepreferenceConsts.DEFAULT_STRING_VAULE)
    fun getLong(key: String) = get(key,SharepreferenceConsts.DEFAULT_LONG_VAULE)


  fun clear() = getSharepreference().edit().clear().apply()
}