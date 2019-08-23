package org.orechou.animationlib.elements;

import android.animation.ValueAnimator;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.animation.LinearInterpolator;

import org.orechou.animationlib.Element;

import java.util.ArrayList;

public class SquareRotateElement extends Element {

    public static final String TAG = "SquareRotateElement";

    private float rotateX, rotateY;

    private Camera mCamera;

    private Matrix mMatrix;

    public SquareRotateElement() {
        mCamera = new Camera();
        mMatrix = new Matrix();
    }

    @Override
    protected void draw(Canvas canvas, Paint paint) {
        mMatrix.reset();
        mCamera.save();
        mCamera.rotateX(rotateX);
        mCamera.rotateY(rotateY);
        mCamera.getMatrix(mMatrix);
        mCamera.restore();

        mMatrix.preTranslate(-centerX(), -centerY());
        mMatrix.postTranslate(centerX(), centerY());
        canvas.concat(mMatrix);

        canvas.drawRect(new RectF(getWidth() / 6, getHeight() / 6, getWidth() / 6 * 5, getHeight() / 6 * 5), paint);
    }

    @Override
    protected void onCreateAnimators() {
        mAnimators = new ArrayList<>();
        ValueAnimator animator=ValueAnimator.ofFloat(0,180,180,0,0);
        addUpdateListener(animator, animation -> {
            rotateX= (float) animation.getAnimatedValue();
            postInvalidate();
        });
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(2500);

        ValueAnimator animator1=ValueAnimator.ofFloat(0,0,180,180,0);
        addUpdateListener(animator1, animation -> {
            rotateY= (float) animation.getAnimatedValue();
            postInvalidate();
        });
        animator1.setInterpolator(new LinearInterpolator());
        animator1.setRepeatCount(ValueAnimator.INFINITE);
        animator1.setDuration(2500);

        mAnimators.add(animator);
        mAnimators.add(animator1);
    }
}
