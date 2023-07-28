package com.dinhtc.logistics.model

import com.dinhtc.logistics.common.widgets.tagview.interfaces.TagInterface
import java.util.Locale

data class CustomerModel(
    val avatar: String,
    val email: String,
    val id: Int,
    val name: String
)