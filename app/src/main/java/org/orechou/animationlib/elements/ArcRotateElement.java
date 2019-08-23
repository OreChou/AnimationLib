package org.orechou.animationlib.elements;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;

import androidx.annotation.RequiresApi;

import org.orechou.animationlib.Element;

import java.util.ArrayList;

public class ArcRotateElement extends Element {

    private static final String TAG = "ArcRotateElement";

    private float mScale = 1f;

    private int mDegree = 0;

    private int mSpacing = 10;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        int x = getWidth() / 2;
        int y = getHeight() / 2;
        int radius = (Math.min(getHeight(), getWidth()) - 2 * mSpacing) / 2;
        canvas.save();
        canvas.translate(x, y);
        canvas.scale(mScale, mScale);
        canvas.rotate(mDegree);
        canvas.drawArc(-radius, -radius, radius, radius, -45, 270, false, paint);
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
