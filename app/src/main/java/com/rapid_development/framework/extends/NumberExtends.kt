package com.rapid_development.framework.extends

import java.math.BigDecimal

/**
 * #### 一些常见数字相互转换的扩展属性 ;
 * @author Heyi
 * @since 1.0.0
 */

val String.i get() = if (this.toIntOrNull() == null)   0   else this.toInt()
val String.f get() = if (this.toFloatOrNull() == null)   0f   else this.toFloat()
val String.d get() = if (this.toDoubleOrNull() == null)   0.0   else this.toDouble()
val String.l get() = if (this.toLongOrNull() == null)   0L   else this.toLong()
val String.bd get() = if (this.toBigDecimalOrNull() == null) BigDecimal.ZERO else this.toBigDecimal()

val Int.s get() = this.toString()
val Int.f get() =  this.toFloat()
val Int.d get() =  this.toDouble()
val Int.l get() =  this.toLong()
val Int.bd get() = this.toBigDecimal()

val Float.s get() = this.toString()
val Float.i get() = this.toInt()
val Float.d get() =  this.toDouble()
val Float.db get() = this.toBigDecimal()
val Float.l get() =  this.toLong()

val Double.s get() =  this.toString()
val Double.i get() =  this.toInt()
val Double.f get() = this.toFloat()
val Double.l get() =  this.toLong()
val Double.db get() = this.toBigDecimal()

val Long.s get() = this.toString()
val Long.i get() = this.toInt()
val Long.d get() = this.toDouble()
val Long.bd get() = this.toBigDecimal()
val Long.f get() = this.toFloat()


