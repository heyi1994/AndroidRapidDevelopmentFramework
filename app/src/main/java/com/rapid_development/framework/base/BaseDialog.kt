package com.rapid_development.framework.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.WindowManager
import com.rapid_development.framework.R

/**
 * #### 第一次show的时候执行onCreate方法
 * @author Heyi
 * @since 1.0.0
 */
abstract class BaseDialog(val hasFrame:Boolean,context:Context): Dialog(context,if(hasFrame) R.style.DialogStyle else R.style.DialogStyle_No_Frame){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setWindowAnimations(R.style.window_anim_style)
        if (isWindowWidthFullScreen()){
            window.decorView.setPadding(0,0,0,0)
            val param= window.attributes
            param.width=WindowManager.LayoutParams.MATCH_PARENT
            param.height=WindowManager.LayoutParams.WRAP_CONTENT
            window.attributes=param
        }
        window.setGravity(setGravity())
        setContentView(getLayoutId())
        init()
    }


    @LayoutRes
    protected abstract fun getLayoutId():Int


    protected abstract fun isWindowWidthFullScreen():Boolean

    protected abstract fun init()

    protected abstract fun setGravity():Int

}