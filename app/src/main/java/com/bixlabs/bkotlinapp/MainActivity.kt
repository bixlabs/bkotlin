package com.bixlabs.bkotlinapp

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.bixlabs.bkotlin.*
import com.bixlabs.bkotlin.utils.BixMediaPicker
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var progressDialog: DialogInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        displayAlertDialog("Test", "This is a test", callback = {
//            displayConfirmDialog("Another test", "This is another test", callback = {
//                Log.e(TAG, "The user pressed -> $it")
//                progressDialog = displayProgressDialog("Testing the progress", "Hold on...")
//                runDelayedOnUiThread(1500) {
//                    progressDialog.dismiss()
//                }
//            })
//        })

        btn_test.setOnClickListener {
            val a = 1504622447790L - (60000L * 3888000)

            text_test.text = a.toTimeAgo()

            val mediaPicker = BixMediaPicker()
            mediaPicker.pickPhotoFromGallery(MainActivity@this) { a, b ->
                if (a == BixMediaPicker.PICK_SUCCESS) {
                    Log.e(TAG, b)
                    longToast(b)
                } else {
                    Log.e(TAG, "Error picking images")
                }
            }
        }

        val abc = 1
        var bbb: String? = null

        abc.isNullThen {
            Log.e(TAG, "ABC is null")
        }.isNotNullThen {
            Log.e(TAG, "Obviously, ABC is not null -> $abc")
        }

        bbb.isNullThen {
            Log.e(TAG, "BBB is null, assigning value and printing...")
            bbb = "Now I'm not null"
        }.isNotNullThen {
            Log.e(TAG, bbb)
        }
    }
}

