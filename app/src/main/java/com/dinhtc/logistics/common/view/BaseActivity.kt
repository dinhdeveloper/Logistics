package com.dinhtc.logistics.common.view

import android.os.Bundle
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.dinhtc.logistics.R

abstract class BaseActivity<_viewDataBinding : ViewDataBinding>() : AppCompatActivity() {

    abstract val layoutResourceId: Int
    protected var viewDataBinding: ViewDataBinding? = null
    abstract fun onCreateActivity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResourceId)
        onCreateActivity()
    }

    override fun setContentView(@LayoutRes layoutResID: Int) {
        val baseLayout = layoutInflater.inflate(R.layout.activity_base, null) as FrameLayout
        val layoutMain: FrameLayout = baseLayout.findViewById(R.id.layout_main)
        viewDataBinding = DataBindingUtil.inflate(layoutInflater, layoutResID, layoutMain, true)
        super.setContentView(baseLayout)
    }
}