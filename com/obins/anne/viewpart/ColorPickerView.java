package com.obins.anne.viewpart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.obins.anne.utils.AppConfig;

public class ColorPickerView extends View {
    private static final float BORDER_WIDTH_PX = 1.0f;
    private float HUE_PANEL_WIDTH;
    private float PALETTE_CIRCLE_TRACKER_RADIUS;
    private float PANEL_SPACING;
    private float RECTANGLE_TRACKER_OFFSET;
    private int mAlpha;
    private String mAlphaSliderText;
    private int mBorderColor;
    private Paint mBorderPaint;
    private float mDensity;
    private float mDrawingOffset;
    private RectF mDrawingRect;
    private float mHue;
    private Paint mHuePaint;
    private RectF mHueRect;
    private Shader mHueShader;
    private Paint mHueTrackerPaint;
    private OnColorChangedListener mListener;
    private float mSat;
    private int mSliderTrackerColor;
    private Point mStartTouchPoint;
    private float mVal;

    public interface OnColorChangedListener {
        void onColorChanged(int i);
    }

    public ColorPickerView(Context context) {
        this(context, null);
    }

    public ColorPickerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorPickerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.HUE_PANEL_WIDTH = 30.0f;
        this.PANEL_SPACING = 10.0f;
        this.PALETTE_CIRCLE_TRACKER_RADIUS = 5.0f;
        this.RECTANGLE_TRACKER_OFFSET = 2.0f;
        this.mDensity = BORDER_WIDTH_PX;
        this.mAlpha = MotionEventCompat.ACTION_MASK;
        this.mHue = 180.0f;
        this.mSat = BORDER_WIDTH_PX;
        this.mVal = BORDER_WIDTH_PX;
        this.mAlphaSliderText = AppConfig.SERVER_IP;
        this.mSliderTrackerColor = -14935012;
        this.mBorderColor = -9539986;
        this.mStartTouchPoint = null;
        init();
    }

    private void init() {
        this.mDensity = getContext().getResources().getDisplayMetrics().density;
        this.PALETTE_CIRCLE_TRACKER_RADIUS *= this.mDensity;
        this.RECTANGLE_TRACKER_OFFSET *= this.mDensity;
        this.HUE_PANEL_WIDTH *= this.mDensity;
        this.PANEL_SPACING *= this.mDensity;
        this.mDrawingOffset = 0.0f;
        initPaintTools();
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    private void initPaintTools() {
        this.mHuePaint = new Paint();
        this.mHueTrackerPaint = new Paint();
        this.mBorderPaint = new Paint();
        this.mHueTrackerPaint.setColor(this.mSliderTrackerColor);
        this.mHueTrackerPaint.setStyle(Style.STROKE);
        this.mHueTrackerPaint.setStrokeWidth(2.0f * this.mDensity);
        this.mHueTrackerPaint.setAntiAlias(true);
    }

    private float calculateRequiredOffset() {
        return 1.5f * Math.max(Math.max(this.PALETTE_CIRCLE_TRACKER_RADIUS, this.RECTANGLE_TRACKER_OFFSET), BORDER_WIDTH_PX * this.mDensity);
    }

    private int[] buildHueColorArray() {
        int[] hue = new int[361];
        int count = 0;
        int i = hue.length - 1;
        while (i >= 0) {
            hue[count] = Color.HSVToColor(new float[]{(float) i, BORDER_WIDTH_PX, BORDER_WIDTH_PX});
            i--;
            count++;
        }
        return hue;
    }

    protected void onDraw(Canvas canvas) {
        if (this.mDrawingRect.width() > 0.0f && this.mDrawingRect.height() > 0.0f) {
            drawHuePanel(canvas);
        }
    }

    private void drawHuePanel(Canvas canvas) {
        RectF rect = this.mHueRect;
        this.mBorderPaint.setColor(this.mBorderColor);
        canvas.drawRect(rect.left - BORDER_WIDTH_PX, rect.top - BORDER_WIDTH_PX, rect.right + BORDER_WIDTH_PX, BORDER_WIDTH_PX + rect.bottom, this.mBorderPaint);
        if (this.mHueShader == null) {
            this.mHueShader = new LinearGradient(rect.left, rect.top, rect.right, rect.top, buildHueColorArray(), null, TileMode.CLAMP);
            this.mHuePaint.setShader(this.mHueShader);
        }
        canvas.drawRect(rect, this.mHuePaint);
        float rectHeight = (4.0f * this.mDensity) / 2.0f;
        Point p = hueToPoint(this.mHue);
        RectF r = new RectF();
        r.left = ((float) p.x) - rectHeight;
        r.top = rect.top - this.RECTANGLE_TRACKER_OFFSET;
        r.right = ((float) p.x) + rectHeight;
        r.bottom = rect.bottom + this.RECTANGLE_TRACKER_OFFSET;
        canvas.drawRoundRect(r, 2.0f, 2.0f, this.mHueTrackerPaint);
    }

    private Point hueToPoint(float hue) {
        RectF rect = this.mHueRect;
        float width = rect.width();
        Point p = new Point();
        p.y = (int) rect.top;
        p.x = (int) ((width - ((hue * width) / 360.0f)) + rect.left);
        return p;
    }

    private float pointToHue(float x) {
        RectF rect = this.mHueRect;
        float width = rect.width();
        if (x < rect.left) {
            x = 0.0f;
        } else if (x > rect.right) {
            x = width;
        } else {
            x -= rect.top;
        }
        return 360.0f - ((x * 360.0f) / width);
    }

    public boolean onTrackballEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        boolean update = false;
        if (event.getAction() == 2) {
            float hue = this.mHue - (10.0f * y);
            if (hue < 0.0f) {
                hue = 0.0f;
            } else if (hue > 360.0f) {
                hue = 360.0f;
            }
            this.mHue = hue;
            update = true;
        }
        if (!update) {
            return super.onTrackballEvent(event);
        }
        if (this.mListener != null) {
            this.mListener.onColorChanged(Color.HSVToColor(this.mAlpha, new float[]{this.mHue, this.mSat, this.mVal}));
        }
        invalidate();
        return true;
    }

    public boolean onTouchEvent(MotionEvent event) {
        boolean update = false;
        switch (event.getAction()) {
            case 0:
                this.mStartTouchPoint = new Point((int) event.getX(), (int) event.getY());
                update = moveTrackersIfNeeded(event);
                break;
            case 1:
                this.mStartTouchPoint = null;
                update = moveTrackersIfNeeded(event);
                break;
            case 2:
                update = moveTrackersIfNeeded(event);
                break;
        }
        if (!update) {
            return super.onTouchEvent(event);
        }
        if (this.mListener != null) {
            this.mListener.onColorChanged(Color.HSVToColor(this.mAlpha, new float[]{this.mHue, this.mSat, this.mVal}));
        }
        invalidate();
        return true;
    }

    private boolean moveTrackersIfNeeded(MotionEvent event) {
        if (this.mStartTouchPoint == null) {
            return false;
        }
        if (!this.mHueRect.contains((float) this.mStartTouchPoint.x, (float) this.mStartTouchPoint.y)) {
            return false;
        }
        this.mHue = pointToHue(event.getX());
        return true;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private int chooseWidth(int mode, int size) {
        return (mode == ExploreByTouchHelper.INVALID_ID || mode == 1073741824) ? size : getPrefferedWidth();
    }

    private int chooseHeight(int mode, int size) {
        return (mode == ExploreByTouchHelper.INVALID_ID || mode == 1073741824) ? size : getPrefferedHeight();
    }

    private int getPrefferedWidth() {
        return (int) ((((float) getPrefferedHeight()) + this.HUE_PANEL_WIDTH) + this.PANEL_SPACING);
    }

    private int getPrefferedHeight() {
        return (int) (200.0f * this.mDensity);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mDrawingRect = new RectF();
        this.mDrawingRect.left = this.mDrawingOffset + ((float) getPaddingLeft());
        this.mDrawingRect.right = (((float) w) - this.mDrawingOffset) - ((float) getPaddingRight());
        this.mDrawingRect.top = this.mDrawingOffset + ((float) getPaddingTop());
        this.mDrawingRect.bottom = (((float) h) - this.mDrawingOffset) - ((float) getPaddingBottom());
        setUpHueRect();
    }

    private void setUpHueRect() {
        RectF dRect = this.mDrawingRect;
        float panelSideHeight = dRect.height();
        float panelSideWidth = dRect.width();
        float left = dRect.left;
        float top = dRect.top;
        this.mHueRect = new RectF(left, top, left + panelSideWidth, top + panelSideHeight);
    }

    public void setOnColorChangedListener(OnColorChangedListener listener) {
        this.mListener = listener;
    }

    public void setBorderColor(int color) {
        this.mBorderColor = color;
        invalidate();
    }

    public int getBorderColor() {
        return this.mBorderColor;
    }

    public int getColor() {
        return 1;
    }

    public void setColor(int color) {
        setColor(color, false);
    }

    public void setColor(int color, boolean callback) {
        int alpha = Color.alpha(color);
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        this.mHue = hsv[0];
        if (callback) {
            invalidate();
        } else {
            invalidate();
        }
    }

    public float getDrawingOffset() {
        return this.mDrawingOffset;
    }

    public void setSliderTrackerColor(int color) {
        this.mSliderTrackerColor = color;
        this.mHueTrackerPaint.setColor(this.mSliderTrackerColor);
        invalidate();
    }

    public int getSliderTrackerColor() {
        return this.mSliderTrackerColor;
    }

    public void setAlphaSliderText(int res) {
        setAlphaSliderText(getContext().getString(res));
    }

    public void setAlphaSliderText(String text) {
        this.mAlphaSliderText = text;
        invalidate();
    }

    public String getAlphaSliderText() {
        return this.mAlphaSliderText;
    }
}
