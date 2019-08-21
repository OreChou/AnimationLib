package org.orechou.animationlib.elements;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;

import org.orechou.animationlib.Element;

import java.util.ArrayList;

public class CircleWaveElement extends Element {

    public static final String TAG = "CircleWaveElement";

    private static final float SCALE = 1.0f;

    private static final int DEFAULT_CIRCLE_NUM = 4;

    private float[] mScales;

    private float mCircleSpacing = 5f;

    private int mCircleNum = DEFAULT_CIRCLE_NUM;

    public CircleWaveElement() {
        mScales = new float[mCircleNum];
        for (int i = 0; i < mCircleNum; i++) {
            mScales[i] = SCALE;
        }
    }

    @Override
    protected void draw(Canvas canvas, Paint paint) {
        float radius = (Math.min(getWidth(), getHeight()) - (mCircleNum - 1) * mCircleSpacing) / (2 * mCircleNum);
        float x;
        if (mCircleNum % 2 == 1) {
            x = getWidth() / 2 - mCircleNum / 2 * mCircleSpacing - (mCircleNum - 1) * radius;
        } else {
            x = getWidth() / 2 - (mCircleNum / 2 - 0.5f) * mCircleSpacing - (mCircleNum - 1) * radius;
        }
        float y = getHeight() / 2;

        for (int i = 0; i < mCircleNum; i++) {
            float transformX = x + i * mCircleSpacing + 2 * i * radius;
            float transformY = y;
            canvas.save();
            canvas.translate(transformX, transformY);
            canvas.scale(mScales[i], mScales[i]);
            canvas.drawCircle(0, 0, radius, paint);
            canvas.restore();
        }
    }

    @Override
    protected void onCreateAnimators() {
        mAnimators = new ArrayList<>();
        int[] delays = new int[mCircleNum];
        for (int i = 0; i < mCircleNum; i++) {
            delays[i] = 120 + 120 * i;
        }
        for (int i = 0; i < mCircleNum; i++) {
            ValueAnimator animator = ValueAnimator.ofFloat(1f, 0.3f, 1f);
            animator.setDuration(750);
            animator.setStartDelay(delays[i]);
            animator.setRepeatCount(ValueAnimator.INFINITE);
            final int index = i;
            addUpdateListener(animator, (valueAnimator) -> {
                mScales[index] = (float) valueAnimator.getAnimatedValue();
                postInvalidate();
            });
            mAnimators.add(animator);
        }
    }
}
