package org.orechou.animationlib.elements;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.animation.LinearInterpolator;

import org.orechou.animationlib.Element;

import java.util.ArrayList;

public class CircleTranslateElement extends Element {

    public static final String TAG = "CircleTranslateElement";

    private int mSpacing = 40;

    private int mRadius = 10;

    private int[] translateX = new int[3], translateY = new int[3];

    @Override
    protected void draw(Canvas canvas, Paint paint) {
        paint.setStrokeWidth(5);
        for (int i = 0; i < 3; i++) {
            canvas.save();
            canvas.translate(translateX[i], translateY[i]);
            canvas.drawCircle(0, 0, mRadius, paint);
            canvas.restore();
        }

    }

    @Override
    protected void onCreateAnimators() {
        int start = mSpacing + mRadius;
        mAnimators = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ValueAnimator translateXAnimator, translateYAnimator;
            if (i == 0) {
                translateXAnimator = ValueAnimator.ofInt(start, getWidth() / 2, getWidth() - start, start);
                translateYAnimator = ValueAnimator.ofInt(getHeight() - start, start, getHeight() - start, getHeight() - start);
            } else if (i == 1) {
                translateXAnimator = ValueAnimator.ofInt(getWidth() / 2, getWidth() - start, start, getWidth() / 2);
                translateYAnimator = ValueAnimator.ofInt(start, getHeight() - start, getHeight() - start, start);
            } else {
                translateXAnimator = ValueAnimator.ofInt(getWidth() - start, start, getWidth() / 2, getWidth() - start);
                translateYAnimator = ValueAnimator.ofInt(getHeight() - start, getHeight() - start, start, getHeight() - start);
            }
            translateXAnimator.setDuration(1500);
            translateXAnimator.setRepeatCount(ValueAnimator.INFINITE);
            translateXAnimator.setInterpolator(new LinearInterpolator());
            translateYAnimator.setDuration(1500);
            translateYAnimator.setRepeatCount(ValueAnimator.INFINITE);
            translateYAnimator.setInterpolator(new LinearInterpolator());
            final int index = i;
            addUpdateListener(translateXAnimator, animation -> {
                translateX[index] = (int) animation.getAnimatedValue();
                postInvalidate();
            });
            addUpdateListener(translateYAnimator, animation -> {
                translateY[index] = (int) animation.getAnimatedValue();
                postInvalidate();
            });
            mAnimators.add(translateXAnimator);
            mAnimators.add(translateYAnimator);
        }
    }
}
