package com.dinhtc.logistics.view.fragment

import com.dinhtc.logistics.R
import com.dinhtc.logistics.common.view.BaseFragment
import com.dinhtc.logistics.databinding.FragmentAddTaskBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTaskFragment : BaseFragment<FragmentAddTaskBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_add_task

    override fun viewCreatedFragment() {
    }
}