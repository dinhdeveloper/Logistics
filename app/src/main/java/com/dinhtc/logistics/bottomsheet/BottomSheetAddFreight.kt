package com.dinhtc.logistics.bottomsheet

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.dinhtc.logistics.R
import com.dinhtc.logistics.utils.DialogFactory
import com.dinhtc.logistics.utils.safeClickListener
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetAddFreight(
    private val mContext: Context
) : BottomSheetDialogFragment() {

    var bottomSheetBehavior: BottomSheetBehavior<*>? = null

    private lateinit var imgClose: ImageView
    private lateinit var btnChooseHangHoa: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_add_freight, container, false)
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetCommon
    }

    override fun onViewCreated(
        modalSheetView: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(modalSheetView, savedInstanceState)
        /*Show full dialog*/
        bottomSheetBehavior = BottomSheetBehavior.from(view?.parent as View)
        bottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
        bottomSheetBehavior?.isDraggable = false

        findViewByID(modalSheetView)
        actionView()
    }

    private fun actionView() {
        imgClose.setOnClickListener {
            dismiss()
        }

        btnChooseHangHoa.setOnClickListener {
            DialogFactory.showDialogDefaultNotCancel(mContext,"Hiển thị danh sách hàng hóa")
        }
    }

    private fun findViewByID(modalSheetView: View) {
        imgClose = modalSheetView.findViewById(R.id.imgClose)
        btnChooseHangHoa = modalSheetView.findViewById(R.id.btnChooseHangHoa)
    }

    companion object {
        const val TAG = "BottomSheetAddFreight"
    }
}