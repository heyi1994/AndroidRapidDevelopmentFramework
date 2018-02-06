package com.rapid_development.framework.extends

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * @author Heyi
 * @since 1.0.0
 */
fun ViewGroup.inflater(@LayoutRes id:Int) = LayoutInflater.from(context).inflate(id,this)