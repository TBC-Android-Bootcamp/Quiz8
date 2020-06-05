package com.university.exam8.ui.tools

import android.content.Context
import android.graphics.Point
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import android.view.WindowManager
import com.university.exam8.App

object Tools {
    fun viewVisibility(view: View) {
        if (view.visibility == View.VISIBLE)
            view.visibility = View.INVISIBLE
        else
            view.visibility = View.VISIBLE
    }

    fun isInternetAvailable(): Boolean {
        var result = false
        val connectivityManager =
            App.instance.getContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
        return result
    }

    fun getScreenDimenss(): Point {
        val wm = App.instance.getContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val size = Point()
        wm.defaultDisplay.getSize(size)
        return size
    }
}