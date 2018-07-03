package com.littlemango.stacklayoutmanager

import android.view.View
import com.littlemango.stacklayoutmanager.StackLayoutManager.ScrollOrientation

abstract class StackAnimation(scrollOrientation: ScrollOrientation, visibleCount: Int) {

    protected val mScrollOrientation = scrollOrientation
    protected var mVisibleCount = visibleCount

    internal fun setVisibleCount(visibleCount: Int) {
        mVisibleCount = visibleCount
    }

    /**
     * 外部回调，用来做动画.
     * @param firstMovePercent 第一个可视 item 移动的百分比，当即将完全移出屏幕的时候 firstMovePercent无限接近1.
     * @param itemView 当前的 itemView.
     * @param position 当前 itemView 对应的位置，position = 0 until visibleCount.
     */
    abstract fun doAnimation(firstMovePercent: Float, itemView: View, position: Int)
}