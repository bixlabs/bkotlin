package com.bixlabs.bkotlinapp

import android.content.DialogInterface
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.bixlabs.bkotlin.TAG
import com.bixlabs.bkotlin.toTimeAgo
import com.bixlabs.bkotlin.toast
import com.bixlabs.bkotlin.utils.BixMediaPicker
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var progressDialog: DialogInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        alert("Test", "This is a test", callback = {
//            confirm("Another test", "This is another test", callback = {
//                Log.e(TAG, "The user pressed -> $it")
//                progressDialog = progress("Testing the progress", "Hold on...")
//                runOnUiDelayed(1500) {
//                    progressDialog.dismiss()
//                }
//            })
//        })

        PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("Great", true)

        btn_test.setOnClickListener {
            val a = 1504622447790L - (60000L * 3888000)

            text_test.text = a.toTimeAgo()

            val mediaPicker = BixMediaPicker()
            mediaPicker.pickPhotoFromGallery(MainActivity@this) { a, b ->
                if (a == BixMediaPicker.PICK_SUCCESS) {
                    Log.e(TAG, b)
                    toast(b)
                } else {
                    Log.e(TAG, "Error picking images")
                }
            }
        }
    }
}

