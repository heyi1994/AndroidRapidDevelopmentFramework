package com.rapid_development.framework.ui.main

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.os.Build
import android.provider.Settings
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import com.rapid_development.framework.R
import com.rapid_development.framework.base.*
import com.rapid_development.framework.data.bean.AppConfig
import com.rapid_development.framework.data.bean.BaseResponse
import com.rapid_development.framework.data.log.L
import com.rapid_development.framework.data.repository.RetrofitSource
import com.rapid_development.framework.data.sharepreferences.SharepreferenceHelper
import com.rapid_development.framework.extends.clickButton
import com.rapid_development.framework.extends.lightStatusBar
import com.rapid_development.framework.extends.requstNotificationPolicy
import com.rapid_development.framework.extends.setStatusBarColor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Heyi
 * @since 1.0.0
 */
class MainActivity:BaseActivity(),MainUiInterface{
    private val TAG:String=this.javaClass.simpleName
    private var current = 0

    lateinit private var mPresenter:MainPresenter
    override fun getLayoutId() =  R.layout.activity_main

    override fun init() {
      setStatusBarColor(R.color.md_white)
      lightStatusBar()
        mPresenter= MainPresenter(this)
        mPresenter.getPhone()
      clickButton(btn,{
          if (current>10){
              toast("the current >10")
              false
          }
          else true
      }){
          current++
      }

        if (SharepreferenceHelper.isFirstRun()){
            toast("the application is first run ;")
            SharepreferenceHelper.setIsFirstRun(false)
        }

        btn2.setOnClickListener {
            SimpleDialog(this).show()
        }


    }
    override fun getRootView() = root

    override fun showPhone(phone: String) {
       toast("the phone is :$phone ;")
    }
}

class MainPresenter(private val ui:MainUiInterface):BasePresenter<BaseUiInterface>(ui){
    fun getPhone(){
        RetrofitSource.getInstance().getAppConfig()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :BaseObserver<BaseResponse<AppConfig>>(ui){
                    override fun onSuccess(date: BaseResponse<AppConfig>) {
                        date.data.phone?.let {
                            ui.showPhone(it)
                        }
                    }
                })
    }
}



interface MainUiInterface:BaseUiInterface{
    fun showPhone(phone:String)
}


class SimpleDialog(context: Context):BaseDialog(true,context) {
    override fun getLayoutId() = R.layout.dialog_simple

    override fun isWindowWidthFullScreen() = true

    override fun init() {
    }

    override fun setGravity() = Gravity.BOTTOM
}