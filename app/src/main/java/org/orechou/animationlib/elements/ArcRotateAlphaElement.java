package org.orechou.animationlib.elements;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import org.orechou.animationlib.Element;

import java.util.ArrayList;

public class ArcRotateAlphaElement extends Element {

    private static final String TAG = "ArcRotateAlphaElement";

    private int mAlpha = 255;

    private float mSweepAngle = 110;

    private float mRotate = 0;

    private int mSpacing = 10;

    @Override
    protected void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);

        int x = getWidth() / 2;
        int y = getHeight() / 2;
        int radius = Math.min(x, y) - 5 - 2 * mSpacing;
        RectF rectF = new RectF(-radius, -radius, radius, radius);
        paint.setAlpha(mAlpha);
        canvas.save();
        canvas.translate(x, y);
        canvas.rotate(mRotate);
        canvas.drawArc(rectF, 335f, mSweepAngle, false, paint);
        canvas.drawArc(rectF, 95f, mSweepAngle, false, paint);
        canvas.drawArc(rectF, 215f, mSweepAngle, false, paint);
        canvas.restore();
    }

    @Override
    protected void onCreateAnimators() {
        mAnimators = new ArrayList<>();
        ValueAnimator alphaAnimator = ValueAnimator.ofInt(120, 255, 255, 255, 120);
        alphaAnimator.setDuration(4000);
        alphaAnimator.setRepeatCount(ValueAnimator.INFINITE);
        alphaAnimator.addUpdateListener(animation -> {
            mAlpha = (int) animation.getAnimatedValue();
            postInvalidate();
        });
        ValueAnimator sweepAnimator = ValueAnimator.ofFloat(0, 110, 0);
        sweepAnimator.setDuration(4000);
        sweepAnimator.setRepeatCount(ValueAnimator.INFINITE);
        sweepAnimator.addUpdateListener(animation -> {
            mSweepAngle = (float) animation.getAnimatedValue();
            postInvalidate();
        });
        ValueAnimator rotateAnimator = ValueAnimator.ofFloat(0, 360, 720);
        rotateAnimator.setDuration(4000);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimator.addUpdateListener(animation -> {
            mRotate = (float) animation.getAnimatedValue();
            postInvalidate();
        });
        mAnimators.add(alphaAnimator);
        mAnimators.add(sweepAnimator);
        mAnimators.add(rotateAnimator);
    }
}
