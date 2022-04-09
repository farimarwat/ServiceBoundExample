package com.marwatsoft.servicebindingexample

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.marwatsoft.servicebindingexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var mContext:Context
    var isServiceBinded = false
    var mService:MyService? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Intent(mContext,MyService::class.java).also {
            bindService(it,myConnectiion, BIND_AUTO_CREATE)
        }
        binding.btnShowtime.setOnClickListener {
            Log.e("MyService","${isServiceBinded}")
            if(isServiceBinded){

               mService?.let {
                   binding.txtTime.text = it.getCurrentTime()
               }
            }
        }
    }

    val myConnectiion = object:ServiceConnection{
        override fun onServiceConnected(className: ComponentName?, service: IBinder?) {
            val binder = service as MyService.MyBinder
            mService = binder.getService()
            isServiceBinded = true
            Log.e("MyService","Service: ${isServiceBinded}")
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
           isServiceBinded = false
            Log.e("MyService","Service: ${isServiceBinded}")
        }

    }
}