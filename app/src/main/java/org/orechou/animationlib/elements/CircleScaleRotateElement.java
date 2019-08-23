package org.orechou.animationlib.elements;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;

import org.orechou.animationlib.Element;

import java.util.ArrayList;

public class CircleScaleRotateElement extends Element {

    private static final String TAG = "CircleScaleRotateElement";

    private float mScale = 1;

    private int mDegree = 0;

    private int mSpacing = 10;

    @Override
    protected void draw(Canvas canvas, Paint paint) {
        int x = getWidth() / 2;
        int y = getHeight() / 2;

        int radius = (Math.min(x, y) - 4 * mSpacing) / 3;
        canvas.save();
        canvas.translate(x, y);
        canvas.rotate(mDegree, 0, 0);
        canvas.scale(mScale, mScale);
        canvas.drawCircle(0 - 2 * radius - mSpacing, 0, radius, paint);
        canvas.drawCircle(0, 0, radius, paint);
        canvas.drawCircle(0+ 2 * radius + mSpacing, 0, radius, paint);
        canvas.restore();
    }

    @Override
    protected void onCreateAnimators() {
        mAnimators = new ArrayList<>();
        ValueAnimator scaleAnimator = ValueAnimator.ofFloat(1f, 0.7f, 1f);
        scaleAnimator.setDuration(1000);
        scaleAnimator.setRepeatCount(ValueAnimator.INFINITE);
        addUpdateListener(scaleAnimator, animation -> {
            mScale = (float) animation.getAnimatedValue();
            postInvalidate();
        });
        ValueAnimator rotateAnimator = ValueAnimator.ofInt(0, 360);
        rotateAnimator.setDuration(1000);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        addUpdateListener(rotateAnimator, animation -> {
            mDegree = (int) animation.getAnimatedValue();
            postInvalidate();
        });
        mAnimators.add(scaleAnimator);
        mAnimators.add(rotateAnimator);
    }
}
