package org.orechou.animationlib;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class ElementView extends View {

    private Element mElement;

    int mMinWidth = 48;
    int mMaxWidth = 96;
    int mMinHeight = 48;
    int mMaxHeight = 96;

    public ElementView(Context context) {
        super(context);
    }

    public ElementView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ElementView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ElementView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setElement(Element element) {
        mElement = element;
        mElement.setCallback(this);
        postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = Math.max(mMinWidth, mElement.getIntrinsicWidth());
        int height = Math.max(mMinHeight, mElement.getIntrinsicHeight());

        width += getPaddingLeft() + getPaddingRight();
        height += getPaddingTop() + getPaddingBottom();

        final int measuredWidth = resolveSizeAndState(width, widthMeasureSpec, 0);
        final int measuredHeight = resolveSizeAndState(height, heightMeasureSpec, 0);
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int saveCount = canvas.save();
        canvas.translate(getPaddingLeft(), getPaddingTop());
        mElement.draw(canvas);
        canvas.restoreToCount(saveCount);
    }

}
