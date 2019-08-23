package org.orechou.animationlib.elements;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import org.orechou.animationlib.Element;

import java.util.ArrayList;

public class PacmanElement extends Element {

    public static final String TAG = "PacmanElement";

    private int mAlpha = 255;

    private int mClockwiseDegree = 0;

    private int mAnticlockwiseDegree = 0;

    private int mCircleX = 0;

    private int mRadius = 0;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void draw(Canvas canvas, Paint paint) {
        // Pacman
        paint.setAlpha(255);
        int x = getWidth() / 2;
        int y = getHeight() / 2;
        float spacing = Math.min(getWidth(), getHeight()) / 2 / 5;
        canvas.save();
        canvas.translate(x, y);
        canvas.rotate(mClockwiseDegree);
        RectF rectF = new RectF(-x + spacing, -y + spacing, x - spacing, y - spacing);
        canvas.drawArc(rectF, 30, 270, true, paint);
        canvas.restore();
        canvas.save();
        canvas.translate(x, y);
        canvas.rotate(mAnticlockwiseDegree);
        canvas.drawArc(-x + spacing, -y + spacing, x - spacing, y - spacing, -30, -270, true, paint);
        canvas.restore();

        // Circle
        paint.setAlpha(mAlpha);
        mRadius = (int) (spacing);
        canvas.save();
        canvas.translate(mCircleX, getHeight() / 2);
        canvas.drawCircle(0, 0, mRadius, paint);
        canvas.restore();
    }

    @Override
    protected void onCreateAnimators() {
        mAnimators = new ArrayList<>();

        ValueAnimator rotateAnimator = ValueAnimator.ofInt(0, 30, 0);
        rotateAnimator.setDuration(750);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        addUpdateListener(rotateAnimator, animator -> {
            mAnticlockwiseDegree = (int) animator.getAnimatedValue();
            postInvalidate();
        });
        ValueAnimator antiRotateAnimator = ValueAnimator.ofInt(0, -30, 0);
        antiRotateAnimator.setDuration(750);
        antiRotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        addUpdateListener(antiRotateAnimator, animator -> {
            mClockwiseDegree = (int) animator.getAnimatedValue();
            postInvalidate();
        });

        ValueAnimator alphaAnimator = ValueAnimator.ofInt(255, 0);
        alphaAnimator.setDuration(750);
        alphaAnimator.setRepeatCount(ValueAnimator.INFINITE);
        addUpdateListener(alphaAnimator, animator -> {
            mAlpha = (int) animator.getAnimatedValue();
            postInvalidate();
        });
        ValueAnimator transformAnimator = ValueAnimator.ofInt(getWidth() - mRadius, getWidth() / 2);
        transformAnimator.setDuration(750);
        transformAnimator.setRepeatCount(ValueAnimator.INFINITE);
        addUpdateListener(transformAnimator, animator -> {
            mCircleX = (int) animator.getAnimatedValue();
            postInvalidate();
        });
        mAnimators.add(rotateAnimator);
        mAnimators.add(antiRotateAnimator);
        mAnimators.add(alphaAnimator);
        mAnimators.add(transformAnimator);
    }
}
