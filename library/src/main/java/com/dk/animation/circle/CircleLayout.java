package com.dk.animation.circle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;


public class CircleLayout extends FrameLayout {

    private Path mCirclelPath;
    private boolean isClipOutlines;
    private float mCenterX;
    private float mCenterY;
    private float mRadius;

    public CircleLayout(Context context) {
        this(context, null);
    }

    public CircleLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mCirclelPath = new Path();
    }


    public void setCenter(float centerX, float centerY) {
        mCenterX = centerX;
        mCenterY = centerY;
    }

    public void setClipOutlines(boolean clip) {
        isClipOutlines = clip;
    }

    public void setCirclelRadius(float radius) {
        mRadius = radius;
        invalidate();
    }

    public float getCircleRadius() {
        return mRadius;
    }


    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        if (!isClipOutlines)
            return super.drawChild(canvas, child, drawingTime);

        final int state = canvas.save();

        mCirclelPath.reset();
        mCirclelPath.addCircle(mCenterX, mCenterY, mRadius, Path.Direction.CW);

        canvas.clipPath(mCirclelPath);

        boolean isInvalided = super.drawChild(canvas, child, drawingTime);

        canvas.restoreToCount(state);

        return isInvalided;
    }

}