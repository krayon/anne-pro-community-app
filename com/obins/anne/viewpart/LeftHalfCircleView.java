package com.obins.anne.viewpart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.obins.anne.C0182R;

public class LeftHalfCircleView extends ImageView {
    private static final int DEFAULT_DIRECTION = 0;
    private static final int DEFAULT_FILL_COLOR = -7829368;
    private final Paint mBorderPaint;
    private float mBorderRadius;
    private final RectF mBorderRect;
    private int mDirection;
    private int mFillColor;
    private final Paint mFillPaint;
    private float mFillRadius;

    public LeftHalfCircleView(Context context) {
        super(context);
        this.mFillPaint = new Paint();
        this.mBorderPaint = new Paint();
        this.mFillColor = DEFAULT_FILL_COLOR;
        this.mDirection = 0;
        this.mBorderRect = new RectF();
    }

    public LeftHalfCircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LeftHalfCircleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mFillPaint = new Paint();
        this.mBorderPaint = new Paint();
        this.mFillColor = DEFAULT_FILL_COLOR;
        this.mDirection = 0;
        this.mBorderRect = new RectF();
        this.mFillColor = context.obtainStyledAttributes(attrs, C0182R.styleable.CircleButton, defStyle, 0).getColor(0, DEFAULT_FILL_COLOR);
    }

    public void setColor(int color) {
        this.mFillColor = color;
        postInvalidate();
    }

    public void setProgress(int color) {
        this.mFillColor = color;
        postInvalidate();
    }

    protected void onDraw(Canvas canvas) {
        this.mFillPaint.setColor(this.mFillColor);
        RectF oval = new RectF();
        oval.set(0.0f, 0.0f, (float) getHeight(), (float) getHeight());
        canvas.drawArc(oval, 90.0f, 180.0f, true, this.mFillPaint);
    }
}
