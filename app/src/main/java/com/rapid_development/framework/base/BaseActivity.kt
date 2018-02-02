package com.rapid_development.framework.base

import android.content.Intent
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.view.View
import com.rapid_development.framework.R
import com.rapid_development.framework.data.log.L
import me.imid.swipebacklayout.lib.app.SwipeBackActivity

/**
 * #### Activity的基类,封装一些基础功能;
 * @author Heyi
 * @since 1.0.0
 */
abstract class BaseActivity:SwipeBackActivity() {
    private val TAG:String=this.javaClass.simpleName

    lateinit private var mToast:Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        mToast = Snackbar.make(getRootView(),"",Snackbar.LENGTH_SHORT)
                .setActionTextColor(resources.getColor(R.color.md_white))
        mToast.view.setBackgroundColor(resources.getColor(R.color.md_cyan_A700))
        intent?.let {
            parseIntent(it)
        }
        init()
    }

    /**
     * #### 解析Intent中携带的数据,空实现 ;
     */
    protected open fun parseIntent(intent: Intent){
       L.d(TAG,"the intent msg is : ${intent.toString()}")
    }

    /**
     * #### 初始化数据 ;
     */
    protected abstract fun init()

    /**
     * #### 获取子类的root View
     */
    protected abstract fun getRootView():View


    @LayoutRes
    protected abstract fun getLayoutId():Int


    protected fun toast(@StringRes stringRes:Int) {
        mToast.setText(stringRes)
        mToast.show()
    }

    protected fun toast( msg:String) {
        mToast.setText(msg)
        mToast.show()
    }
}