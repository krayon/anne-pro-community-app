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

public class CircleButton extends ImageView {
    private static final int DEFAULT_BORDER_COLOR = -1;
    private static final int DEFAULT_BORDER_WIDTH = 0;
    private static final int DEFAULT_FILL_COLOR = -7829368;
    private int mBorderColor;
    private final Paint mBorderPaint;
    private float mBorderRadius;
    private final RectF mBorderRect;
    private int mBorderWidth;
    private int mFillColor;
    private final Paint mFillPaint;
    private float mFillRadius;

    public CircleButton(Context context) {
        super(context);
        this.mFillPaint = new Paint();
        this.mBorderPaint = new Paint();
        this.mBorderWidth = 0;
        this.mFillColor = DEFAULT_FILL_COLOR;
        this.mBorderColor = -1;
        this.mBorderRect = new RectF();
    }

    public CircleButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mFillPaint = new Paint();
        this.mBorderPaint = new Paint();
        this.mBorderWidth = 0;
        this.mFillColor = DEFAULT_FILL_COLOR;
        this.mBorderColor = -1;
        this.mBorderRect = new RectF();
        TypedArray a = context.obtainStyledAttributes(attrs, C0182R.styleable.CircleButton, defStyle, 0);
        this.mFillColor = a.getColor(0, DEFAULT_FILL_COLOR);
        this.mBorderColor = a.getColor(1, -1);
        this.mBorderWidth = a.getDimensionPixelSize(5, 0);
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
        this.mBorderPaint.setColor(this.mBorderColor);
        this.mBorderPaint.setStyle(Style.STROKE);
        this.mBorderPaint.setAntiAlias(true);
        this.mBorderPaint.setColor(this.mBorderColor);
        this.mBorderPaint.setStrokeWidth((float) this.mBorderWidth);
        this.mBorderRect.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        this.mBorderRadius = Math.min((this.mBorderRect.height() / 2.0f) - ((float) this.mBorderWidth), (this.mBorderRect.width() / 2.0f) - ((float) this.mBorderWidth));
        canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), this.mBorderRadius, this.mBorderPaint);
    }
}
