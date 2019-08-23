package org.orechou.animationlib.elements;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;

import androidx.annotation.RequiresApi;

import org.orechou.animationlib.Element;

import java.util.ArrayList;

public class CircleArcRotateElement extends Element {

    private static final String TAG = "CircleArcRotateElement";

    private int mDegree = 0;

    private float mScale = 1f;

    private float mSpacing = 10;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void draw(Canvas canvas, Paint paint) {

        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        int x = getWidth() / 2;
        int y = getHeight() / 2;
        int radius = (int) (Math.min(getWidth(), getHeight()) / 2 - 4 * mSpacing);
        canvas.save();
        canvas.translate(x, y);
        canvas.scale(mScale, mScale);
        canvas.drawCircle(0, 0, radius, paint);
        canvas.restore();

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        float length = (Math.min(getWidth(), getHeight()) / 2 - 2 * mSpacing);
        canvas.save();
        canvas.translate(x, y);
        canvas.rotate(mDegree);
        canvas.drawArc(-length, -length, length, length, -45, 90, false, paint);
        canvas.drawArc(-length, -length, length, length, 135, 90, false, paint);
        canvas.restore();
    }

    @Override
    protected void onCreateAnimators() {
        mAnimators = new ArrayList<>();
        ValueAnimator scaleAnimator = ValueAnimator.ofFloat(1,0.6f,0.5f,1);
        scaleAnimator.setDuration(1000);
        scaleAnimator.setRepeatCount(ValueAnimator.INFINITE);
        addUpdateListener(scaleAnimator, animator -> {
            mScale = (float) animator.getAnimatedValue();
            postInvalidate();
        });
        mAnimators.add(scaleAnimator);

        ValueAnimator rotateAnimator = ValueAnimator.ofInt(0, 180, 360);
        rotateAnimator.setDuration(1000);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        addUpdateListener(rotateAnimator, animator -> {
            mDegree = (int) animator.getAnimatedValue();
            postInvalidate();
        });
        mAnimators.add(rotateAnimator);
    }
}
