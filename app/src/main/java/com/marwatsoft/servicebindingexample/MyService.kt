package com.marwatsoft.servicebindingexample

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.text.SimpleDateFormat
import java.util.*

class MyService: Service() {
    val mBinder = MyBinder()
    override fun onBind(p0: Intent?): IBinder? {
        return mBinder
    }
    fun getCurrentTime(): String {
        val dateformat = SimpleDateFormat("HH:mm:ss MM/dd/yyyy",
            Locale.US)
        return dateformat.format(Date())
    }
    inner class MyBinder: Binder(){
        fun getService():MyService{
            return this@MyService
        }
    }
}