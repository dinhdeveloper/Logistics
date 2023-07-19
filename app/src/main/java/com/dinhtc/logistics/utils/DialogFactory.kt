package com.dinhtc.logistics.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.TextView
import com.dinhtc.logistics.R

class DialogFactory {
    companion object {
        @JvmStatic
        fun showDialogDefaultNotCancel(context: Context?, message: String?) {
            if (context == null) return
            val dialog = TranslucentDialog(context).apply {
                window?.apply {
                    setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                }
                setContentView(R.layout.dialog_default_without_listener)
                setCancelable(false)
                setCanceledOnTouchOutside(false)
            }
            val tvMessage = dialog.findViewById<View>(R.id.txtContent) as TextView
            tvMessage.text = message
            val btnConfirm = dialog.findViewById<View>(R.id.btnConfirm) as TextView
            btnConfirm.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }
    }
}