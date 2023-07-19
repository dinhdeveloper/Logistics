package com.dinhtc.logistics.utils

object SingleClick {
    private const val MIN_CLICK_INTERVAL = 800
    private var sLastClickTime: Long = 0

    fun isBlockingClick(): Boolean = isBlockingClick(MIN_CLICK_INTERVAL.toLong())

    private fun isBlockingClick(minClickInterval: Long): Boolean {
        val isBlocking: Boolean
        val currentTime = System.currentTimeMillis()
        isBlocking = Math.abs(currentTime - sLastClickTime) < minClickInterval
        if (!isBlocking) {
            sLastClickTime = currentTime
        }
        return isBlocking
    }
}