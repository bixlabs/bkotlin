package com.bixlabs.bkotlinapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.bixlabs.bkotlin.TAG
import com.bixlabs.bkotlin.alert
import com.bixlabs.bkotlin.confirm

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        alert("Test", "This is a test", callback = {
            confirm("Another test", "This is another test", callback = {
                Log.e(TAG, "The user pressed -> $it")
            })
        })
    }
}

