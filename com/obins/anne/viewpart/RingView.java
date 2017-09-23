package com.obins.anne.viewpart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.obins.anne.C0182R;

public class RingView extends ImageView {
    private static final int DEFAULT_FILL_COLOR = -7829368;
    private final Paint mBorderPaint;
    private float mBorderRadius;
    private final RectF mBorderRect;
    private int mColorWheelRadius;
    private int mColorWheelThickness;
    private int mFillColor;
    private final Paint mFillPaint;
    private float mFillRadius;

    public RingView(Context context) {
        super(context);
        this.mColorWheelThickness = 2;
        this.mColorWheelRadius = 160;
        this.mFillPaint = new Paint();
        this.mBorderPaint = new Paint();
        this.mFillColor = DEFAULT_FILL_COLOR;
        this.mBorderRect = new RectF();
    }

    public RingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mColorWheelThickness = 2;
        this.mColorWheelRadius = 160;
        this.mFillPaint = new Paint();
        this.mBorderPaint = new Paint();
        this.mFillColor = DEFAULT_FILL_COLOR;
        this.mBorderRect = new RectF();
        TypedArray a = context.obtainStyledAttributes(attrs, C0182R.styleable.CircleButton, defStyle, 0);
        this.mColorWheelRadius = a.getDimensionPixelSize(6, this.mColorWheelRadius);
        this.mColorWheelThickness = a.getDimensionPixelSize(7, this.mColorWheelThickness);
        this.mFillColor = a.getColor(0, DEFAULT_FILL_COLOR);
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
        this.mFillPaint.setStyle(Style.STROKE);
        this.mFillPaint.setAntiAlias(true);
        this.mFillPaint.setColor(this.mFillColor);
        this.mFillPaint.setStrokeWidth((float) this.mColorWheelThickness);
        this.mFillRadius = (float) (this.mColorWheelRadius - (this.mColorWheelThickness / 2));
        int mXCenter = getWidth() / 2;
        int mYCenter = getHeight() / 2;
        RectF oval = new RectF();
        oval.left = ((float) mXCenter) - this.mFillRadius;
        oval.top = ((float) mYCenter) - this.mFillRadius;
        oval.right = (this.mFillRadius * 2.0f) + (((float) mXCenter) - this.mFillRadius);
        oval.bottom = (this.mFillRadius * 2.0f) + (((float) mYCenter) - this.mFillRadius);
        canvas.drawArc(oval, -90.0f, 360.0f, false, this.mFillPaint);
    }
}
