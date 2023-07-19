package com.dinhtc.logistics.view.fragment

import com.dinhtc.logistics.R
import com.dinhtc.logistics.common.view.BaseFragment
import com.dinhtc.logistics.databinding.FragmentDetailBinding
import com.dinhtc.logistics.model.LogisticInfoModel
import com.dinhtc.logistics.view.fragment.HomeFragment.Companion.LOGISTIC_MODEL


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
        }
    }
}