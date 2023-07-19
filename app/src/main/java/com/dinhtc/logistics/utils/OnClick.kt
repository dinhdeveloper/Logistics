package com.dinhtc.logistics.utils

import android.view.View

fun View.safeClickListener(safeClickListener: (view: View) -> Unit) {
    this.setOnClickListener {
        if (!SingleClick.isBlockingClick()) {
            safeClickListener(it)
        }
    }
}