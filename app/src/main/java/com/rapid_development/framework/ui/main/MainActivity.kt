package com.rapid_development.framework.ui.main

import android.view.View
import com.rapid_development.framework.R
import com.rapid_development.framework.base.BaseActivity
import com.rapid_development.framework.base.BaseUiInterface
import com.rapid_development.framework.data.log.L
import com.rapid_development.framework.data.sharepreferences.SharepreferenceHelper
import com.rapid_development.framework.extends.clickButton
import com.rapid_development.framework.extends.lightStatusBar
import com.rapid_development.framework.extends.setStatusBarColor
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Heyi
 * @since 1.0.0
 */
class MainActivity:BaseActivity() {
    private val TAG:String=this.javaClass.simpleName
    private var current = 0
    override fun getLayoutId() =  R.layout.activity_main

    override fun init() {
      setStatusBarColor(R.color.md_white)
      lightStatusBar()
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
    }
    override fun getRootView() = root
}