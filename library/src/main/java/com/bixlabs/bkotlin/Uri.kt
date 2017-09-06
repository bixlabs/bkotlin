//package com.bixlabs.bkotlin
//
//import android.annotation.TargetApi
//import android.content.ContentUris
//import android.content.Context
//import android.database.Cursor
//import android.net.Uri
//import android.os.Build
//import android.os.Environment
//import android.provider.DocumentsContract
//import android.provider.MediaStore
//
///**
// * Get real sd-card path from DocumentsProvider, MediaStore Uris,
// * and other file-based ContentProviders.
// *
// * infix supported.
// *
// * @param[context] The context.
// */
//@TargetApi(Build.VERSION_CODES.KITKAT)
//infix fun Uri.getRealPath(context: Context): String {
//    if (DocumentsContract.isDocumentUri(context, this)) {
//        if ("com.android.externalstorage.documents" == this.authority) {
//            val docId = DocumentsContract.getDocumentId(this)
//            val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
//            val type = split[0]
//
//            if ("primary".equals(type, ignoreCase = true))
//                return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
//        } else if ("com.android.providers.downloads.documents" == this.authority) {
//            val id = DocumentsContract.getDocumentId(this)
//            val contentUri = ContentUris.withAppendedId(
//                    Uri.parse("content://downloads/public_downloads"), java.lang.Long.valueOf(id)!!)
//
//            return getDataColumn(context, contentUri, null, null)
//        } else if ("com.android.providers.media.documents" == this.authority) {
//            val docId = DocumentsContract.getDocumentId(this)
//            val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
//            val type = split[0]
//
//            var contentUri: Uri? = null
//
//            when (type) {
//                "image" -> contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//                "video" -> contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
//                "audio" -> contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
//            }
//
//            val selection = "_id=?"
//            val selectionArgs = arrayOf(split[1])
//
//            return getDataColumn(context, contentUri, selection, selectionArgs)
//        }
//    } else if ("content".equals(this.scheme, ignoreCase = true)) {
//        if ("com.google.android.apps.photos.content" == this.authority)
//            return this.lastPathSegment
//
//        return getDataColumn(context, this, null, null)
//    } else if ("file".equals(this.scheme, ignoreCase = true)) {
//        return this.path
//    }
//
//    return this.path
//}
//
//private fun getDataColumn(context: Context, uri: Uri?, selection: String?, selectionArgs: Array<String>?): String {
//    var cursor: Cursor? = null
//    val column = "_data"
//    val projection = arrayOf(column)
//
//    try {
//        cursor = context.contentResolver.query(uri, projection, selection, selectionArgs, null)
//        if (cursor != null && cursor.moveToFirst()) {
//            val column_index = cursor.getColumnIndexOrThrow(column)
//            return cursor.getString(column_index)
//        }
//    } finally {
//        if (cursor != null)
//            cursor.close()
//    }
//    return ""
//}