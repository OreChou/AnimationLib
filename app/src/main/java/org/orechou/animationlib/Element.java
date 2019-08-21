package org.orechou.animationlib;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class Element extends Drawable implements Animatable {

    /**
     * 绘图相关
     */
    private Paint mPaint = new Paint();

    private static final Rect ZERO_BOUNDS_RECT = new Rect();

    private Rect mDrawBounds = ZERO_BOUNDS_RECT;

    private int mAlpha;

    private boolean mHasReadyAnimators = false;

    /**
     * 动画相关，使用 ValueAnimator
     */
    protected List<ValueAnimator> mAnimators;

    private Map<ValueAnimator, ValueAnimator.AnimatorUpdateListener> mUpdateListenerMap = new HashMap<>();

    public Element() {
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setAntiAlias(true);
    }

    protected abstract void draw(Canvas canvas, Paint paint);

    protected abstract void onCreateAnimators();

    @Override
    public void draw(@NonNull Canvas canvas) {
        draw(canvas, mPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        this.mAlpha = alpha;
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        // ? 不透明度
        return PixelFormat.OPAQUE;
    }

    @Override
    public void start() {
        readyAnimators();
        if (mAnimators == null) {
            return;
        }
        if (isStarted()) {
            return;
        }
        startAnimators();
        invalidateSelf();
    }

    @Override
    public void stop() {
        stopAnimators();
    }

    private void readyAnimators() {
        if (mAnimators == null) {
            onCreateAnimators();
            mHasReadyAnimators = true;
        }
    }

    private void startAnimators() {
        for (ValueAnimator animator : mAnimators) {
            ValueAnimator.AnimatorUpdateListener updateListener = mUpdateListenerMap.get(animator);
            if (updateListener != null) {
                animator.addUpdateListener(updateListener);
            }
            animator.start();
        }
    }

    private void stopAnimators() {
        for (ValueAnimator animator : mAnimators) {
            if (animator != null && animator.isStarted()) {
                animator.end();
            }
            animator.removeAllUpdateListeners();
        }
    }

    public void addUpdateListener(ValueAnimator animator, ValueAnimator.AnimatorUpdateListener updateListener){
        mUpdateListenerMap.put(animator,updateListener);
    }

    public void postInvalidate() {
        invalidateSelf();
    }

    @Override
    public boolean isRunning() {
        boolean res = true;
        for (ValueAnimator animator : mAnimators) {
            res = (res && animator.isRunning());
        }
        return res;
    }

    public boolean isStarted() {
        boolean res = true;
        // All animator not start is not start
        for (ValueAnimator animator : mAnimators) {
            res = (res && animator.isStarted());
        }
        return res;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.setDrawBounds(bounds);
    }

    public Rect getDrawBounds() {
        return mDrawBounds;
    }

    public void setDrawBounds(Rect drawBounds) {
        mDrawBounds = new Rect(drawBounds);
    }

    public int getWidth() {
        return mDrawBounds.width();
    }

    public int getHeight() {
        return mDrawBounds.height();
    }

    public int centerX(){
        return mDrawBounds.centerX();
    }

    public int centerY(){
        return mDrawBounds.centerY();
    }
}
