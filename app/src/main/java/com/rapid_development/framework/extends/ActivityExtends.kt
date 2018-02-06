package com.rapid_development.framework.extends

import android.annotation.TargetApi
import android.app.Activity
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.os.Build
import android.os.PowerManager
import android.provider.Settings
import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.util.TypedValue
import android.view.View
import android.view.WindowManager
import android.widget.Button
import com.rapid_development.framework.R
import com.rapid_development.framework.data.log.L
import com.rapid_development.framework.utils.Manufacturer
import com.rapid_development.framework.utils.StatusBarUtils
import java.util.concurrent.locks.Lock

/**
 * #### Activity的扩展方法及属性
 * @author Heyi
 * @since 1.0.0
 */

/**
 * ##### 给一个函数加锁 ;
 * @param lock  see [Lock] ;
 * @param body  要被锁的函数 ;
 */
inline  fun <T> lockFun(lock:Lock,body:()->T):T{
    lock.lock()
    try {
       return body()
    }finally {
        lock.unlock()
    }
}

/**
 * #### 给状态栏染色 ;
 * @param colorRes 颜色ResId ,默认为App默认给控件染色主题颜色 ;
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
fun Activity.setStatusBarColor(@ColorRes colorRes:Int = R.color.colorAccent){
    if (Build.VERSION.SDK_INT>=21) window.statusBarColor=resources.getColor(colorRes)
}

/**
 * #### 保持页面常亮 ; 更多实现方式参考[PowerManager] ;
 */
fun Activity.keepScreenOn(){
    window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
}

/**
 * #### 将状态栏设置为透明模式,并返回状态栏的高度 ;
 * @return 返回状态栏高度 ;
 */
@TargetApi(Build.VERSION_CODES.KITKAT)
fun Activity.transparentStatusBar():Int{
    if (Build.VERSION.SDK_INT>=19) {
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        return  resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height","dimen","android"))
    } else return  0
}

/**
 * #### 将状态栏字体颜色设置成DIM色 ;因为一些厂商把rom进行了更改,利用反射将某些厂商适配 ;
 */
@TargetApi(Build.VERSION_CODES.M)
fun Activity.lightStatusBar(){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        if (Build.MANUFACTURER == Manufacturer.XIAOMI.info) StatusBarUtils.MIUISetStatusBarLightMode(window,true)
        else if (Build.MANUFACTURER == Manufacturer.MEIZU.info) StatusBarUtils.FlymeSetStatusBarLightMode(window,true)
    }
}

/**
 *#### 点击Button,改变字体,并设置按钮为灰色且不可点击 ;
 *    - 和drawable中的 sel_btn_*.xml 配合使用 ;
 *    - 异步完成后需要将 isEnabled 设置为true 恢复为可点击 ;
 */
 fun <T:Button>Activity.clickButtonSetEnable(btn:T,@StringRes changeStringRes:Int,clickBody:()->Unit){
     btn.isEnabled  =  false
     btn.text = getString(changeStringRes)
     btn.setOnClickListener { clickBody() }
 }
fun <T:Button>Activity.clickButtonSetEnable(btn:T,@StringRes changeStringRes:Int,filter:()->Boolean,clickBody:()->Unit){
        btn.isEnabled = false
        btn.text = getString(changeStringRes)
        btn.setOnClickListener { if (filter())clickBody() }
}
 fun <T:Button>Activity.clickButtonSetEnable(btn:T, changeString:CharSequence,clickBody:()->Unit){
    btn.isEnabled  =  false
    btn.text = changeString
    btn.setOnClickListener { clickBody() }
}
fun <T:Button>Activity.clickButtonSetEnable(btn:T, changeString:CharSequence,filter:()->Boolean,clickBody:()->Unit){
        btn.isEnabled = false
        btn.text = changeString
        btn.setOnClickListener { if (filter())clickBody() }
}
 fun <T:Button>Activity.clickButton(btn: T,clickBody: () -> Unit){
     btn.setOnClickListener { clickBody() }
  }

 fun <T:Button>Activity.clickButton(btn: T,filter:()->Boolean,clickBody: () -> Unit){
         btn.setOnClickListener {
             if (filter())clickBody()
         }
}
 fun <T:View>Activity.clickView(view:T,clickBody: () -> Unit){
     view.isFocusable = true
     view.isClickable = true
     view.setOnClickListener { clickBody() }
 }

fun <T:View>Activity.clickView(view:T,filter:()->Boolean ,clickBody: () -> Unit){
        view.isFocusable = true
        view.isClickable = true
        view.setOnClickListener { if (filter())clickBody() }
}



fun Activity.dp2px(dp:Int) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp.f,resources.displayMetrics).i
fun Activity.sp2px(sp:Int) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,sp.f,resources.displayMetrics).i


val Activity.visible get() =  View.VISIBLE
val Activity.gone  get() =  View.GONE
val Activity.invisible get() = View.INVISIBLE

val Activity.screenWidth get() = resources.displayMetrics.widthPixels
val Activity.screenHeight get() = resources.displayMetrics.heightPixels


/**
 * #### 获取勿扰模式权限,低于[Build.VERSION_CODES.M]返回true ; 高于则需要请求权限 ;  app播放音频要检测响铃模式和请求音频焦点 ;
 *  - 应用 : 用[AudioManager.setRingerMode] 切换为勿扰模式,高于[Build.VERSION_CODES.M]需要该权限 ;
 *  - 用户取消或者授权的广播 see [NotificationManager.ACTION_NOTIFICATION_POLICY_ACCESS_GRANTED_CHANGED] ;
 */
 fun Activity.requstNotificationPolicy():Boolean{
    val manager =getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (!manager.isNotificationPolicyAccessGranted()) startActivity(Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS))
        return manager.isNotificationPolicyAccessGranted
    }else return true
}








