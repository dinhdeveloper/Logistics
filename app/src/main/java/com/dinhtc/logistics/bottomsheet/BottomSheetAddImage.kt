package com.dinhtc.logistics.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.dinhtc.logistics.R
import com.dinhtc.logistics.utils.safeClickListener
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetAddImage : BottomSheetDialogFragment() {

    var bottomSheetBehavior: BottomSheetBehavior<*>? = null

    private lateinit var imgClose: ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_add_image, container, false)
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetCommon
    }

    override fun onViewCreated(modalSheetView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(modalSheetView, savedInstanceState)
        /*Show full dialog*/
        bottomSheetBehavior = BottomSheetBehavior.from(view?.parent as View)
        bottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
        bottomSheetBehavior?.isDraggable = false

        findViewByID(modalSheetView)
        actionView()
    }

    private fun actionView() {
        imgClose.safeClickListener {
            dismiss()
        }
    }

    private fun findViewByID(modalSheetView: View) {
        imgClose = modalSheetView.findViewById(R.id.imgClose)
    }

    companion object {
        const val TAG = "BottomSheetAddImage"
    }

}