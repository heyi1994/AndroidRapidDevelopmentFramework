package com.rapid_development.framework.base

import android.text.TextUtils
import android.util.Log
import com.google.gson.JsonSyntaxException
import com.google.gson.stream.MalformedJsonException
import com.rapid_development.framework.R
import com.rapid_development.framework.data.bean.BaseResponse
import com.rapid_development.framework.data.bean.ResponseCode
import com.rapid_development.framework.data.log.L
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * @author Heyi
 * @since 1.0.0
 */
abstract class BaseObserver<T: BaseResponse<*>>(val uiInterface: BaseUiInterface):Observer<T>{

    val TAG:String=this.javaClass.simpleName
    private var disposable:Disposable?=null

    companion object {
         fun handleError(e: Throwable?,ui: BaseUiInterface){
            e?.let {
                if (it is SocketTimeoutException || it is ConnectException || it is UnknownHostException){
                    ui.showNetworkException()
                }else if (it is JsonSyntaxException || it is NumberFormatException || it is MalformedJsonException){
                    ui.showDataException(ui.getTargetContext()!!.resources.getString(R.string.base_error_date_format))
                }else if (it is HttpException){
                    ui.showDataException(ui.getTargetContext()!!.resources.getString(R.string.base_error_http_error,it.code()))
                }else if( it is NullPointerException){
                    ui.showDataException(ui.getTargetContext()!!.resources.getString(R.string.base_error_NullPointerException))
                }else{
                    ui.showUnknownException()
                }
            }
        }
    }

    override fun onComplete() {
     L.d(TAG,"onComplete!")
        disposable?.dispose()
    }


    override fun onError(e: Throwable?) {
       L.w(TAG,e)
        disposable?.dispose()
        handleError(e,uiInterface)
    }


    /**
     * #### 根据返回的状态码来决定不同的处理
     *    - 0 : 请求成功 ;
     *    - 其他 : 请求成功,但是数据不是预期 ;
     *    - 如果app中包含单点登录,可以根据code跳转到登录页 ;
     */
    override fun onNext(t: T) {
        L.d(TAG,"onNext")
      if (t.code== ResponseCode.RESULT_CODE_SUCCESS){
          onSuccess(t)
      }else{
          onDataFailure(t)
      }
    }


    protected open fun onDataFailure(t: T){
        val msg=t.msg
        L.d(TAG,"request data but get failure:$msg")
        if (!TextUtils.isEmpty(msg))uiInterface.showDataException(msg)
        else uiInterface.showUnknownException()
    }

    override fun onSubscribe(d: Disposable?) {
        L.d(TAG,"onSubscribe!")
        disposable=d
    }

    protected abstract fun onSuccess(date:T)
}