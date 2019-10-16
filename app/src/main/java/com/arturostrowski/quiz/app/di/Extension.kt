package com.arturostrowski.quiz.app.di

import android.util.Log
import java.math.BigInteger
import java.security.MessageDigest

fun <T> T?.or(default: T): T = if (this == null) default else this
fun <T> T?.or(compute: () -> T): T = if (this == null) compute() else this

fun Any.showELog(log: String) = Log.e(this::class.java.simpleName, log)


data class Ternary<T>(val target: T, val result: Boolean)

infix fun <T> Boolean.then(target: T): Ternary<T> {
    return Ternary(target, this)
}

infix fun <T> Ternary<T>.or(target: T): T {
    return if (this.result) this.target else target
}

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}