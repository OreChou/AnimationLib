package org.orechou.animationlib.elements;

import android.graphics.Canvas;
import android.graphics.Paint;

import org.orechou.animationlib.Element;

public class CircleWaveElement extends Element {

    private static final float SCALE = 1.0f;

    private float[] mScales = new float[] {SCALE, SCALE};

    private float mCircleSpacing = 5f;

    @Override
    protected void draw(Canvas canvas, Paint paint) {


    }

    @Override
    protected void onCreateAnimators() {

    }
}
