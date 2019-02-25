package com.me.observerdemo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by ${cs} on 2019/2/22.
 */

class KtActivity : AppCompatActivity(){

    lateinit var resource:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        println(resource)
    }

    companion object {
        val From ="from"
        @JvmStatic
        fun startActivity(context: Context){
            var intent = Intent()
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }
}