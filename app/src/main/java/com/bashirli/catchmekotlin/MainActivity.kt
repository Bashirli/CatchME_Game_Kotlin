package com.bashirli.catchmekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
var handler:Handler=Handler()
        handler.postDelayed(object:Runnable{
            override fun run() {
        val intent=Intent(this@MainActivity,ScreenActivity::class.java);
                startActivity(intent)
                finish()
            }
        },1300)

    }
}