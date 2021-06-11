package com.krismanpratama.expertsystem.helper

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import com.krismanpratama.expertsystem.R

class LoadingDialog(private val activity: Activity) {

    private lateinit var dialog: Dialog

    fun loadingAlertDialog(){
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.loading_dialog,null))
        builder.setCancelable(false)

        dialog = builder.create()
        dialog.show()
    }

    fun dissmissDialog(){
        dialog.dismiss()
    }

}