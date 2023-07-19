package com.dinhtc.logistics.utils

import android.os.Build
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import org.apache.commons.lang3.StringEscapeUtils

object AndroidUtils {
    @JvmStatic
    fun fromHtml(str: String?): Spanned {
        return when {
            str == null -> {
                SpannableString("")
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> {
                Html.fromHtml(StringEscapeUtils.unescapeHtml4(str), Html.FROM_HTML_MODE_LEGACY)
            }
            else -> {
                Html.fromHtml(StringEscapeUtils.unescapeHtml4(str))
            }
        }
    }
}