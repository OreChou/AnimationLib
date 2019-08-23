package org.orechou.animationlib.elements;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;

import org.orechou.animationlib.Element;

import java.util.ArrayList;

public class CircleScaleAlphaMultipleElement extends Element {

    private static final String TAG = "CircleScaleAlphaMultipleElement";

    private int mSpacing = 20;

    private float[] mScales = new float[] {1, 1, 1, 1};

    private float mScale = 1;

    private int mAlpha = 255;

    @Override
    protected void draw(Canvas canvas, Paint paint) {
        int x = getWidth() / 2;
        int y = getHeight() / 2;
        mSpacing = Math.min(x, y) / 5;

        int[] radius = new int[4];
        for (int i = 0; i < 4; i++) {
            radius[i] = Math.min(x, y) - i * mSpacing - mSpacing;
            canvas.save();
            canvas.translate(x, y);
            canvas.scale(mScale, mScale);
            paint.setAlpha(mAlpha);
            canvas.drawCircle(0, 0, radius[i], paint);
            canvas.restore();
        }
    }

    @Override
    protected void onCreateAnimators() {
        mAnimators = new ArrayList<>();
        ValueAnimator scaleAnimator = ValueAnimator.ofFloat(0, 1);
        scaleAnimator.setDuration(1200);
        scaleAnimator.setRepeatCount(ValueAnimator.INFINITE);
        addUpdateListener(scaleAnimator, animation -> {
            mScale = (float) animation.getAnimatedValue();
            postInvalidate();
        });
        ValueAnimator alphaAnimator = ValueAnimator.ofInt(255, 0);
        alphaAnimator.setDuration(1200);
        alphaAnimator.setRepeatCount(ValueAnimator.INFINITE);
        addUpdateListener(alphaAnimator, animation -> {
            mAlpha = (int) animation.getAnimatedValue();
            postInvalidate();
        });
        mAnimators.add(scaleAnimator);
        mAnimators.add(alphaAnimator);
    }
}
