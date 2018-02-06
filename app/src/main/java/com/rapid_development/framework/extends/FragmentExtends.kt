package com.rapid_development.framework.extends

import android.app.Activity
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.util.TypedValue
import android.view.View
import android.widget.Button

/**
 * @author Heyi
 * @since 1.0.0
 */
/**
 *#### 点击Button,改变字体,并设置按钮为灰色且不可点击 ;
 *    - 和drawable中的 sel_btn_*.xml 配合使用 ;
 *    - 异步完成后需要将 isEnabled 设置为true 恢复为可点击 ;
 */
fun <T: Button> Fragment.clickButtonSetEnable(btn:T, @StringRes changeStringRes:Int, clickBody:()->Unit){
    btn.isEnabled  =  false
    btn.text = getString(changeStringRes)
    btn.setOnClickListener { clickBody() }
}
fun <T: Button> Fragment.clickButtonSetEnable(btn:T, @StringRes changeStringRes:Int, filter:()->Boolean, clickBody:()->Unit){
    btn.isEnabled = false
    btn.text = getString(changeStringRes)
    btn.setOnClickListener { if (filter())clickBody() }
}
fun <T: Button> Fragment.clickButtonSetEnable(btn:T, changeString:CharSequence, clickBody:()->Unit){
    btn.isEnabled  =  false
    btn.text = changeString
    btn.setOnClickListener { clickBody() }
}
fun <T: Button> Fragment.clickButtonSetEnable(btn:T, changeString:CharSequence, filter:()->Boolean, clickBody:()->Unit){
    btn.isEnabled = false
    btn.text = changeString
    btn.setOnClickListener { if (filter())clickBody() }
}
fun <T: Button> Fragment.clickButton(btn: T, clickBody: () -> Unit){
    btn.setOnClickListener { clickBody() }
}

fun <T: Button> Fragment.clickButton(btn: T, filter:()->Boolean, clickBody: () -> Unit){
    btn.setOnClickListener {
        if (filter())clickBody()
    }
}
fun <T: View> Fragment.clickView(view:T, clickBody: () -> Unit){
    view.isFocusable = true
    view.isClickable = true
    view.setOnClickListener { clickBody() }
}

fun <T: View> Fragment.clickView(view:T, filter:()->Boolean, clickBody: () -> Unit){
    view.isFocusable = true
    view.isClickable = true
    view.setOnClickListener { if (filter())clickBody() }
}



fun Fragment.dp2px(dp:Int) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp.f,resources.displayMetrics).i
fun Fragment.sp2px(sp:Int) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,sp.f,resources.displayMetrics).i


val Fragment.visible get() =  View.VISIBLE
val Fragment.gone  get() =  View.GONE
val Fragment.invisible get() = View.INVISIBLE

val Fragment.screenWidth get() = resources.displayMetrics.widthPixels
val Fragment.screenHeight get() = resources.displayMetrics.heightPixels