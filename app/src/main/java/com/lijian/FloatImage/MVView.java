package com.lijian.FloatImage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MVView extends View {
    private  Rect mTextBounds;
    private  Paint mPaint;
    private String ss="asda";
    private int mWidth;
    private int mHeight;

    public MVView(Context context) {
        this(context,null);
    }

    public MVView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MVView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint =new Paint();
        mTextBounds = new Rect();
        mPaint.setTextSize(150);
        mPaint.getTextBounds(ss,0,ss.length(),mTextBounds);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int with = MeasureSpec.getSize(widthMeasureSpec);
        int wmode = MeasureSpec.getMode( widthMeasureSpec);

        if(wmode == MeasureSpec.EXACTLY){
            mWidth = with;
        }else {
            mWidth=mTextBounds.width()+getPaddingLeft()+getPaddingRight();
        }



        int height = MeasureSpec.getSize(heightMeasureSpec);
        int hmode = MeasureSpec.getMode( heightMeasureSpec);
        if(hmode == MeasureSpec.EXACTLY){
            mHeight = height;
        }else {
            mHeight=mTextBounds.height()+getPaddingTop()+getPaddingBottom();
        }
        setMeasuredDimension(mWidth,mHeight);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        System.out.println("111getPaddingTop():"+getPaddingTop());
        System.out.println("111mTextBounds.height(),:"+mTextBounds.height());

        canvas.drawText(ss,getPaddingLeft()+0,getPaddingTop()+mTextBounds.height(),mPaint);

    }
}
