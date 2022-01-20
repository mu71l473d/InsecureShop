package com.insecureshop

import android.app.Application
import android.content.Context
import android.util.Log
import com.insecureshop.util.Util


class InsecureShopApp : Application() {

    override fun onCreate() {
        super.onCreate()
//        invokePlugins()
        Util.saveProductList(this)
    }

    private fun invokePlugins() {

    }


}