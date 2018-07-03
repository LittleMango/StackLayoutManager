package com.littlemango.stacklayoutmanager

import android.view.View

abstract class StackLayout(scrollOrientation: StackLayoutManager.ScrollOrientation,
                           visibleCount: Int,
                           perItemOffset: Int) {

    protected val mScrollOrientation = scrollOrientation
    protected var mVisibleCount = visibleCount
    protected var mPerItemOffset = perItemOffset

    internal fun setItemOffset(offset: Int) {
        mPerItemOffset = offset
    }

    internal fun getItemOffset(): Int {
        return mPerItemOffset
    }

    /**
     * 外部回调，用来做布局.
     * @param firstMovePercent 第一个可视 item 移动的百分比，当即将完全移出屏幕的时候 firstMovePercent无限接近1.
     * @param itemView 当前的 itemView.
     * @param position 当前 itemView 对应的位置，position = 0 until visibleCount.
     */
    abstract fun doLayout(stackLayoutManager: StackLayoutManager,
                          scrollOffset: Int,
                          firstMovePercent: Float,
                          itemView: View,
                          position: Int)

    abstract fun requestLayout()
}