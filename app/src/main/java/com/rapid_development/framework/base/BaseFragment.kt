package com.rapid_development.framework.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.AnimRes
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rapid_development.framework.R
import com.rapid_development.framework.extends.inflater

/**
 * @author Heyi
 * @since 1.0.0
 */
abstract class BaseFragment :Fragment(),BaseUiInterface{
    private val TAG:String=this.javaClass.simpleName

    lateinit private var mToast:Snackbar
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            container?.inflater(getLayoutId())

    @LayoutRes
    protected abstract fun getLayoutId():Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mToast = Snackbar.make(getRootView(),"",Snackbar.LENGTH_SHORT)
                .setActionTextColor(resources.getColor(R.color.md_white))
        mToast.view.setBackgroundColor(resources.getColor(R.color.md_cyan_A700))
        arguments?.let {
            parseData(it)
        }
        init()
    }
    protected abstract fun init()

    /**
     * #### 空实现
     */
    protected fun parseData(bundle: Bundle){}


    override fun showDataException(msg: String) {
       toast(msg)
    }

    override fun showNetworkException() {
        toast(R.string.base_error_network)
    }

    override fun showUnknownException() {
        toast(R.string.base_error_unknow)
    }

    override fun getTargetContext() = context

    protected fun toast( msg:String) {
        if (mToast.isShown)mToast.dismiss()

        mToast.setText(msg)
        mToast.show()
    }


    protected fun toast(@StringRes msg:Int) {
        if (mToast.isShown)mToast.dismiss()

        mToast.setText(msg)
        mToast.show()
    }


    /**
     * #### 获取子类的root View
     */
    protected abstract fun getRootView():View



    @AnimRes
    protected fun getTransitionEnterAnim() =R.anim.fragment_slide_left_in
    @AnimRes
    protected fun getTransitionOutAnim()= R.anim.fragment_slide_left_out
    @AnimRes
    protected fun getTransitionLeftInAnim()  = R.anim.anim_view_left_in
    @AnimRes
    protected fun getTransitionRightOutAnim() = R.anim.fragment_slide_right_out
    @AnimRes
    protected fun getViewTransitionInAnim() = R.anim.anim_view_in
    @AnimRes
    protected fun getViewTransitionRightOutAnim() = R.anim.anim_view_out
}