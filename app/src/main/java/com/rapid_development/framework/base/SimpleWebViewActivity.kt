package com.rapid_development.framework.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.KeyEvent
import android.view.View
import android.webkit.*
import com.rapid_development.framework.R
import com.rapid_development.framework.data.log.L
import com.rapid_development.framework.extends.clickView
import com.rapid_development.framework.extends.lightStatusBar
import com.rapid_development.framework.extends.setStatusBarColor
import kotlinx.android.synthetic.main.activity_simple_webview.*

/**
 * @author Heyi
 * @since 1.0.0
 * @property
 */
class SimpleWebViewActivity :BaseActivity() {
    private val TAG:String=this.javaClass.simpleName
    companion object {
        private val WEB_URL="web_url"
        fun createIntent(context: Context,url:String):Intent{
         val intent=Intent(context, SimpleWebViewActivity::class.java)
            intent.putExtra(WEB_URL,url)
          return intent
        }
    }

    private var url:String?=null
    override fun getLayoutId(): Int= R.layout.activity_simple_webview

    @SuppressLint("SetJavaScriptEnabled")
    override fun init() {
        L.d(TAG,"the h5 url is :$url ;")
        setStatusBarColor()
        val settings = web_view.getSettings()
        settings.setUseWideViewPort(true)
        settings.setLoadWithOverviewMode(true)
        web_view.getSettings().setSupportZoom(true)
        web_view.getSettings().setBuiltInZoomControls(true)
        web_view.getSettings().setDisplayZoomControls(false)
        web_view.getSettings().setJavaScriptEnabled(true)
        web_view.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        web_view.setWebChromeClient(SimpleWebChromeClient())
        web_view.setWebViewClient(SimpleWebViewClient())
        web_view.getSettings().setJavaScriptEnabled(true)
        web_view.loadUrl(url)

         clickView(iv_back){
             iv_back.isClickable = false
             finishAndRightOut()
         }

    }

    inner class SimpleWebViewClient: WebViewClient(){
        override fun shouldOverrideUrlLoading(view: WebView?,  u:String?): Boolean {
            return if (Uri.parse(u).host == url) {
                false
            } else false
        }
    }

    override fun getRootView(): View =root_view



    override fun parseIntent(intent: Intent, isFromNewIntent: Boolean) {
            url=intent.getStringExtra(WEB_URL)
    }

    inner class SimpleWebChromeClient: WebChromeClient(){
        override fun onReceivedTitle(view: WebView?, title: String?) {
            super.onReceivedTitle(view, title)
            tv_title.text=title
        }
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && web_view.canGoBack()) {
            web_view.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}