package com.bixlabs.bkotlin.utils

import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.app.Fragment
import android.app.FragmentManager
import android.content.ContentValues
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.support.v4.content.ContextCompat
import com.bixlabs.bkotlin.EMPTY
import java.text.SimpleDateFormat
import java.util.*


class BixMediaPicker {
    companion object {
        val PICK_FROM_CAMERA = 0
        val PICK_FROM_GALLERY = 1
        val PICK_FROM_GALLERY_VIDEO = 2
        val PICK_FROM_CAMERA_VIDEO = 3

        val PICK_SUCCESS = 1
        val PICK_FAILED = 0

        private val DATE_FORMAT = "yyyyMMdd_HHmmss"
    }

    private var currentPhotoPath: String? = null
    private var currentVideoPath: String? = null

    /**
     * pick image from Camera
     *
     * @param[callback] callback, should make class PickMediaCallback : PickMediaCallback
     */
    fun pickFromCamera(context: Context, callback: (Int, String) -> Unit) = requestPhotoPick(context, PICK_FROM_CAMERA, callback)

    /**
     * pick image from Gallery
     *
     * @param[callback] callback, should make class PickMediaCallback : PickMediaCallback
     */
    fun pickPhotoFromGallery(context: Context, callback: (Int, String) -> Unit) = requestPhotoPick(context, PICK_FROM_GALLERY, callback)

    /**
     * pick image from Video
     *
     * @param[callback] callback, should make class PickMediaCallback : PickMediaCallback
     */
    fun pickVideoFromGallery(context: Context, callback: (Int, String) -> Unit) = requestPhotoPick(context, PICK_FROM_GALLERY_VIDEO, callback)

    /**
     * pick image from Camera (Video Mode)
     *
     * @param[callback] callback, should make class PickMediaCallback : PickMediaCallback
     */
    fun pickFromVideoCamera(context: Context, callback: (Int, String) -> Unit) = requestPhotoPick(context, PICK_FROM_CAMERA_VIDEO, callback)


    /* ********************************************
     *               Private methods              *
     ******************************************** */

    private fun getActivity(context: Context): Activity? {
        var c = context
        while (c is ContextWrapper) {
            if (c is Activity) {
                return c
            }
            c = c.baseContext
        }

        return null
    }

    @SuppressLint("ValidFragment")
    private fun requestPhotoPick(context: Context, pickType: Int, callback: (Int, String) -> Unit) {

        val fm = getActivity(context)?.fragmentManager
        val fragment = ResultFragment(fm as FragmentManager, callback)
        val fragmentTag = "FRAGMENT_TAG"

        fm.beginTransaction().add(fragment, fragmentTag).commitAllowingStateLoss()
        fm.executePendingTransactions()

        when (pickType) {
            PICK_FROM_GALLERY, PICK_FROM_GALLERY_VIDEO -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                        ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    fragment.requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), pickType)
                    return
                }
            }

            PICK_FROM_CAMERA, PICK_FROM_CAMERA_VIDEO -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                        (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                                ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                                ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)) {

                    fragment.requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA), pickType)
                    return
                }
            }
        }

        val intent = Intent("", MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        when (pickType) {
            PICK_FROM_CAMERA -> {
                val captureUri = createImageUri(context)
                currentPhotoPath = captureUri.toString()
                intent.action = MediaStore.ACTION_IMAGE_CAPTURE
                intent.putExtra(MediaStore.EXTRA_OUTPUT, captureUri)
            }

            PICK_FROM_GALLERY -> {
                intent.action = Intent.ACTION_PICK
                intent.type = "image/*"
            }

            PICK_FROM_GALLERY_VIDEO -> {
                intent.action = Intent.ACTION_PICK
                intent.type = "video/*"
            }

            PICK_FROM_CAMERA_VIDEO -> {
                val captureUri = createVideoUri(context)
                currentVideoPath = captureUri.toString()
                intent.action = MediaStore.ACTION_VIDEO_CAPTURE
                intent.putExtra(MediaStore.EXTRA_OUTPUT, captureUri)
            }
        }

        fragment.startActivityForResult(intent, pickType)
    }

    private fun createImageUri(context: Context): Uri {
        val contentResolver = context.contentResolver
        val cv = ContentValues()
        val timeStamp = SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(Date())
        cv.put(MediaStore.Images.Media.TITLE, timeStamp)
        return contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, cv)
    }

    private fun createVideoUri(context: Context): Uri {
        val contentResolver = context.contentResolver
        val cv = ContentValues()
        val timeStamp = SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(Date())
        cv.put(MediaStore.Images.Media.TITLE, timeStamp)
        return contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, cv)
    }

    @SuppressLint("ValidFragment")
    inner class ResultFragment() : Fragment() {
        private var fm: FragmentManager? = null
        private var callback: ((Int, String) -> Unit)? = null

        constructor(fm: FragmentManager, callback: (Int, String) -> Unit) : this() {
            this.fm = fm
            this.callback = callback
        }

        override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)

            if (verifyPermissions(grantResults)) {
                requestPhotoPick(activity, requestCode, callback as ((Int, String) -> Unit))
            } else {
                callback?.invoke(PICK_FAILED, "")
            }

            fm?.beginTransaction()?.remove(this)?.commitAllowingStateLoss()
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            when (requestCode) {
                PICK_FROM_CAMERA ->
                    if (resultCode == Activity.RESULT_OK) {
                        currentPhotoPath?.let {
                            callback?.invoke(PICK_SUCCESS, getRealPath(activity, requestCode, Uri.parse(it)))
                        }
                    }

                PICK_FROM_GALLERY ->
                    if (resultCode == Activity.RESULT_OK) {
                        data?.data?.let {
                            callback?.invoke(PICK_SUCCESS, getRealPath(activity, requestCode, it))
                        }
                    }

                PICK_FROM_GALLERY_VIDEO ->
                    if (resultCode == Activity.RESULT_OK) {
                        data?.data?.let {
                            callback?.invoke(PICK_SUCCESS, getRealPath(activity, requestCode, it))
                        }
                    }

                PICK_FROM_CAMERA_VIDEO ->
                    if (resultCode == Activity.RESULT_OK) {
                        var path = data?.data?.let {
                            getRealPath(activity, requestCode, it)
                        }

                        if (path.isNullOrEmpty()) {
                            path = currentVideoPath as String
                        }

                        if (path != null) {
                            callback?.invoke(PICK_SUCCESS, path)
                        } else {
                            callback?.invoke(PICK_SUCCESS, String.EMPTY())
                        }
                    }
            }

            fm?.beginTransaction()?.remove(this)?.commit()
        }
    }

    private fun verifyPermissions(grantResults: IntArray): Boolean =
            if (grantResults.isEmpty()) false else grantResults.none { it != PackageManager.PERMISSION_GRANTED }

    private fun getRealPath(context: Context, pickType: Int, uri: Uri): String {
        var filePath: String? = null

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            filePath = getRealPathPostKitKat(context, pickType, uri)
        }

        if (filePath != null) {
            return filePath
        }

        val column = arrayOf(MediaStore.MediaColumns.DATA)
        val cursor = context.applicationContext.contentResolver.query(uri, column, null, null, null)

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
                filePath = cursor.getString(columnIndex)
            }
            cursor.close()
        }

        return if (filePath == null) uri.path else filePath
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private fun getRealPathPostKitKat(context: Context, pickType: Int, uri: Uri): String? {
        var filePath: String? = null

        if (DocumentsContract.isDocumentUri(context.applicationContext, uri)) {
            val wholeID = DocumentsContract.getDocumentId(uri)
            val id = wholeID.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
            val column = arrayOf(MediaStore.Video.Media.DATA)
            val sel = MediaStore.Video.Media._ID + "=?"

            val cursor: Cursor? = when (pickType) {
                PICK_FROM_CAMERA, PICK_FROM_GALLERY -> {
                    context.applicationContext.contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            column, sel, arrayOf(id), null)
                }

                PICK_FROM_CAMERA_VIDEO, PICK_FROM_GALLERY_VIDEO -> {
                    context.applicationContext.contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                            column, sel, arrayOf(id), null)
                }

                else -> null
            }

            if (cursor != null) {
                val columnIndex = cursor.getColumnIndex(column[0])

                if (cursor.moveToFirst()) {
                    filePath = cursor.getString(columnIndex)
                }

                cursor.close()
            }
        }

        return filePath
    }
}