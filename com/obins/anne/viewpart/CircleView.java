package com.obins.anne.viewpart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.obins.anne.C0182R;

public class CircleView extends ImageView {
    private static final int DEFAULT_FILL_COLOR = -7829368;
    private final Paint mBorderPaint;
    private float mBorderRadius;
    private final RectF mBorderRect;
    private int mFillColor;
    private final Paint mFillPaint;
    private float mFillRadius;

    public CircleView(Context context) {
        super(context);
        this.mFillPaint = new Paint();
        this.mBorderPaint = new Paint();
        this.mFillColor = DEFAULT_FILL_COLOR;
        this.mBorderRect = new RectF();
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mFillPaint = new Paint();
        this.mBorderPaint = new Paint();
        this.mFillColor = DEFAULT_FILL_COLOR;
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
        this.mFillPaint.setStyle(Style.FILL);
        this.mFillPaint.setAntiAlias(true);
        this.mFillPaint.setColor(this.mFillColor);
        this.mFillRadius = (float) Math.min(getHeight() / 2, getWidth() / 2);
        canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), this.mFillRadius, this.mFillPaint);
    }
}
