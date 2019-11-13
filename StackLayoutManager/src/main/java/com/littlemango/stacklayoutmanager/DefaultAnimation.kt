package com.littlemango.stacklayoutmanager

import android.view.View
import com.littlemango.stacklayoutmanager.StackLayoutManager.ScrollOrientation
import kotlin.math.pow

class DefaultAnimation(scrollOrientation: ScrollOrientation, visibleCount: Int) : StackAnimation(scrollOrientation, visibleCount) {

    private var mScale = 0.95f
    private var mOutScale = 0.8f
    private var mOutRotation: Int

    init {
        mOutRotation = when(scrollOrientation) {
            ScrollOrientation.LEFT_TO_RIGHT, ScrollOrientation.RIGHT_TO_LEFT -> 10
            else -> 0
        }
    }

    /**
     * 设置 item 缩放比例.
     * @param scale 缩放比例，默认是0.95f.
     */
    fun setItemScaleRate(scale: Float) {
        mScale = scale
    }

    /**
     * 获取item缩放比例.
     * @return item缩放比例，默认是0.95f.
     */
    fun getItemScaleRate(): Float {
        return mScale
    }

    /**
     * 设置 itemView 离开屏幕时候的缩放比例.
     * @param scale 缩放比例，默认是0.8f.
     */
    fun setOutScale(scale: Float) {
        mOutScale = scale
    }

    /**
     * 获取 itemView 离开屏幕时候的缩放比例.
     * @return 缩放比例，默认是0.8f.
     */
    fun getOutScale(): Float {
        return mOutScale
    }

    /**
     * 设置 itemView 离开屏幕时候的旋转角度.
     * @param rotation 旋转角度，默认是30.
     */
    fun setOutRotation(rotation: Int) {
        mOutRotation = rotation
    }

    /**
     * 获取 itemView 离开屏幕时候的旋转角度
     * @return 旋转角度，默认是30
     */
    fun getOutRotation(): Int {
        return mOutRotation
    }

    override fun doAnimation(firstMovePercent: Float, itemView: View, position: Int) {
        val scale: Float
        var alpha = 1.0f
        val rotation: Float
        if (position == 0) {
            scale = 1 - ((1 - mOutScale) * firstMovePercent)
            rotation = mOutRotation * firstMovePercent
        } else {
            val minScale = (mScale.toDouble().pow(position.toDouble())).toFloat()
            val maxScale = (mScale.toDouble().pow((position - 1).toDouble())).toFloat()
            scale = minScale + (maxScale - minScale) * firstMovePercent
            //只对最后一个 item 做透明度变化
            if (position == mVisibleCount) {
                alpha = firstMovePercent
            }
            rotation = 0f
        }

        setItemPivotXY(mScrollOrientation, itemView)
        rotationFirstVisibleItem(mScrollOrientation, itemView, rotation)
        itemView.scaleX = scale
        itemView.scaleY = scale
        itemView.alpha = alpha
    }

    private fun setItemPivotXY(scrollOrientation: ScrollOrientation, view: View) {
        when(scrollOrientation) {
            ScrollOrientation.RIGHT_TO_LEFT -> {
                view.pivotX = view.measuredWidth.toFloat()
                view.pivotY = view.measuredHeight.toFloat() / 2
            }
            ScrollOrientation.LEFT_TO_RIGHT -> {
                view.pivotX = 0f
                view.pivotY = view.measuredHeight.toFloat() / 2
            }
            ScrollOrientation.BOTTOM_TO_TOP -> {
                view.pivotX = view.measuredWidth.toFloat() /2
                view.pivotY = view.measuredHeight.toFloat()
            }
            ScrollOrientation.TOP_TO_BOTTOM -> {
                view.pivotX = view.measuredWidth.toFloat() / 2
                view.pivotY = 0f
            }
        }
    }

    private fun rotationFirstVisibleItem(scrollOrientation: ScrollOrientation, view: View, rotation: Float) {
        when(scrollOrientation) {
            ScrollOrientation.RIGHT_TO_LEFT -> view.rotationY = rotation
            ScrollOrientation.LEFT_TO_RIGHT -> view.rotationY = -rotation
            ScrollOrientation.BOTTOM_TO_TOP -> view.rotationX = -rotation
            ScrollOrientation.TOP_TO_BOTTOM -> view.rotationX = rotation
        }
    }
}