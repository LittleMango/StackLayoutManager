package com.littlemango.stacklayoutmanagermaster;

import android.view.View;

import com.littlemango.stacklayoutmanager.StackAnimation;
import com.littlemango.stacklayoutmanager.StackLayoutManager.ScrollOrientation;

import org.jetbrains.annotations.NotNull;

public class FadeInFadeOutAnimation extends StackAnimation {

    private int mVisibleCount;

    FadeInFadeOutAnimation(@NotNull ScrollOrientation scrollOrientation, int visibleCount) {
        super(scrollOrientation, visibleCount);
        mVisibleCount = visibleCount;
    }

    @Override
    public void doAnimation(float firstMovePercent, @NotNull View itemView, int position) {
        if (position == 0) {
            itemView.setAlpha(1 - firstMovePercent + 0.5f);
        } else if (position == mVisibleCount) {
            itemView.setAlpha(firstMovePercent);
        }
        itemView.setScaleX(1);
        itemView.setScaleY(1);
    }
}
