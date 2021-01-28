package com.test.automobile.utils

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.test.automobile.R

class Dialogs {
    companion object {

        fun serverErrorShowDialog(context: Context) {
            val dialogView: View = (context as AppCompatActivity).layoutInflater.inflate(
                    R.layout.dialog_notification_server_error,
                    null
            )
            val positiveButton = dialogView.findViewById<View>(R.id.positiveButton) as Button
            val dialogBuilder = MaterialAlertDialogBuilder(context)
                    .setView(dialogView)

            val dialog = dialogBuilder.create()
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()
            positiveButton.setOnClickListener { dialog.dismiss() }

        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun showAlertDialogNoInternet(context: Context, view: View?) {
            val builder = AlertDialog.Builder(context)
            val dialogView: View = (context as AppCompatActivity).layoutInflater.inflate(
                    R.layout.dialog_custom_no_internet,
                    null
            )
            val positiveButton = dialogView.findViewById<View>(R.id.positiveButton) as Button
            val closeButton = dialogView.findViewById<View>(R.id.closeButton) as Button
            builder.setView(dialogView)
            val alertDialog = builder.create()
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alertDialog.show()
            alertDialog.setCanceledOnTouchOutside(false)
            positiveButton.text = "Обновить страницу"
            positiveButton.setOnClickListener { v: View? ->
                Navigation.findNavController(view!!).navigate(R.id.autoMobileFragment)
                alertDialog.dismiss()
            }
            closeButton.setOnClickListener { v: View? -> context.finishAndRemoveTask() }
        }
    }
}