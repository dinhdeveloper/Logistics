package com.dinhtc.logistics.view.fragment

import android.view.WindowManager
import com.dinhtc.logistics.R
import com.dinhtc.logistics.bottomsheet.BottomSheetAddFreight
import com.dinhtc.logistics.bottomsheet.BottomSheetAddImage
import com.dinhtc.logistics.common.view.BaseFragment
import com.dinhtc.logistics.databinding.FragmentDetailBinding
import com.dinhtc.logistics.model.LogisticInfoModel
import com.dinhtc.logistics.utils.safeClickListener
import com.dinhtc.logistics.view.fragment.HomeFragment.Companion.LOGISTIC_MODEL
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(){

    private lateinit var logisticsModel : LogisticInfoModel

    override val layoutResourceId: Int
        get() = R.layout.fragment_detail

    override fun viewCreatedFragment() {
        logisticsModel = arguments?.getSerializable(LOGISTIC_MODEL) as LogisticInfoModel
        viewBinding.apply {
            logisticsModel.let {
                viewBinding.logisticModelView = logisticsModel
            }

            btnHangHoa.setOnClickListener {
                showDialogAddHangHoa()
            }
            btnAnh.setOnClickListener {
                showDialogAddImage()
            }
        }
    }

    private fun showDialogAddImage() {
        val bottomSheetAddImage = BottomSheetAddImage()
        bottomSheetAddImage.isCancelable = false
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        activity?.supportFragmentManager?.let { bottomSheetAddImage.show(it, bottomSheetAddImage.tag) }
    }

    private fun showDialogAddHangHoa() {
        val bottomSheetAdd = context?.let { BottomSheetAddFreight(it) }
        bottomSheetAdd?.isCancelable = false
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        activity?.supportFragmentManager?.let { bottomSheetAdd?.show(it, bottomSheetAdd.tag) }
    }
}