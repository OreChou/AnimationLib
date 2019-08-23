package org.orechou.animationlib.elements;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;

import org.orechou.animationlib.Element;

import java.util.ArrayList;
import java.util.Random;

public class CircleGridWaveElement extends Element {

    private static final String TAG = "CircleGridWaveElement";

    private static final float SCALE = 1.0f;

    private static final int ALPHA = 255;

    private static final int DEFAULT_LINE_CIRCLE_NUM = 3;

    private float mCircleSpacing = 5f;

    private int mLineCircleNum = DEFAULT_LINE_CIRCLE_NUM;

    private float[] mScales;

    private int[] mAlphas;

    public CircleGridWaveElement() {
        mScales = new float[mLineCircleNum * mLineCircleNum];
        mAlphas = new int[mLineCircleNum * mLineCircleNum];
        for (int i = 0; i < mLineCircleNum * mLineCircleNum; i++) {
            mScales[i] = SCALE;
            mAlphas[i] = ALPHA;
        }
    }

    @Override
    protected void draw(Canvas canvas, Paint paint) {
        float radius = (Math.min(getWidth(), getHeight()) - (mLineCircleNum - 1) * mCircleSpacing) / (2 * mLineCircleNum);
        float x, y;
        if (mLineCircleNum % 2 == 1) {
            x = getWidth() / 2 - mLineCircleNum / 2 * mCircleSpacing - (mLineCircleNum - 1) * radius;
            y = getHeight() / 2 - mLineCircleNum / 2 * mCircleSpacing - (mLineCircleNum - 1) * radius;
        } else {
            x = getWidth() / 2 - (mLineCircleNum / 2 - 0.5f) * mCircleSpacing - (mLineCircleNum - 1) * radius;
            y = getHeight() / 2 - (mLineCircleNum / 2 - 0.5f) * mCircleSpacing - (mLineCircleNum - 1) * radius;
        }

        for (int i = 0; i < mLineCircleNum; i++) {
            for (int j = 0; j < mLineCircleNum; j++) {
                float transformX = x + i * mCircleSpacing + 2 * i * radius;
                float transformY = y + j * mCircleSpacing + 2 * j * radius;

                canvas.save();
                canvas.translate(transformX, transformY);
                canvas.scale(mScales[i * 3 + j], mScales[i * 3 + j]);
                paint.setAlpha(mAlphas[3 * i + j]);
                canvas.drawCircle(0, 0, radius, paint);
                canvas.restore();
            }
        }

    }

    @Override
    protected void onCreateAnimators() {
        mAnimators = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < mLineCircleNum * mLineCircleNum; i++) {
            int duration = random.nextInt((1500 - 500) + 1) + 500;
            int delay = random.nextInt(800 - (-150) + 1) + (-150);

            ValueAnimator scaleAnimator = ValueAnimator.ofFloat(1f, 0.3f, 1f);
            scaleAnimator.setDuration(duration);
            scaleAnimator.setStartDelay(delay);
            scaleAnimator.setRepeatCount(ValueAnimator.INFINITE);
            final int index = i;
            addUpdateListener(scaleAnimator, animation -> {
                mScales[index] = (float) animation.getAnimatedValue();
                postInvalidate();
            });
            mAnimators.add(scaleAnimator);

            ValueAnimator alphaAnimator = ValueAnimator.ofInt(255, 210, 110, 255);
            alphaAnimator.setDuration(duration);
            alphaAnimator.setStartDelay(delay);
            alphaAnimator.setRepeatCount(ValueAnimator.INFINITE);
            addUpdateListener(alphaAnimator, animation -> {
                mAlphas[index] = (int) animation.getAnimatedValue();
                postInvalidate();
            });
            mAnimators.add(alphaAnimator);
        }




    }
}
