package com.lijian.FloatImage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class MVViewGroup extends ViewGroup {

    public MVViewGroup(Context context) {
        super(context);
        //ViewGroup 核心考虑两件事:1我自己多大 2我的那些子控件怎么放
    }

    public MVViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MVViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 判断是否有子view
        int with = 0;
        int height = 0;
        int childCount=getChildCount();
        if(childCount == 0){
           setMeasuredDimension(measureWithAndHeight(widthMeasureSpec),measureWithAndHeight(heightMeasureSpec));
        }else {
            int childViewWidth = 0;
            int childViewHerght = 0;
            int childViewMarginTop = 0;
            int childViewMarginBottom = 0;


            for(int i=0;i<childCount;i++){
                View childView = getChildAt(i);

                measureChild(childView,widthMeasureSpec,heightMeasureSpec);
                 MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();

                 childViewWidth = Math.max(childViewWidth,childView.getMeasuredWidth()+lp.leftMargin+lp.rightMargin);
                 childViewHerght += childView.getMeasuredHeight()+lp.topMargin+lp.bottomMargin;

            }
            with = childViewWidth+getPaddingLeft()+getPaddingRight();
            height = childViewHerght+getPaddingTop()+getPaddingBottom();
            setMeasuredDimension(measureWithAndHeight(widthMeasureSpec,with),measureWithAndHeight(heightMeasureSpec,height));
        }

    }
    private int measureWithAndHeight(int widthMeasureSpec,int size) {
        int result =0;
        int with = MeasureSpec.getSize(widthMeasureSpec);
        int wmode = MeasureSpec.getMode( widthMeasureSpec);

        if(wmode == MeasureSpec.EXACTLY){
            result = with;
        }else {
            result=size;
        }
        return result;
    }



    private int measureWithAndHeight(int widthMeasureSpec) {
        int result =0;
        int with = MeasureSpec.getSize(widthMeasureSpec);
        int wmode = MeasureSpec.getMode( widthMeasureSpec);

        if(wmode == MeasureSpec.EXACTLY){
            result = with;
        }else {
            result=0;
        }
        return result;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int left,top,right,bottom = 0;
        int nexttop =  0;

        int childCount=getChildCount();
        for(int i=0;i<childCount;i++){
            View view = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) view.getLayoutParams();
            if(i==0){
                top =nexttop + lp.topMargin+getPaddingTop();//getPaddingTop
            }else {
                top =nexttop + lp.topMargin;
            }
            left = lp.leftMargin+getPaddingLeft();//要加上父view的getPaddingLeft
            right = left+view.getMeasuredWidth();
            bottom = top + view.getMeasuredHeight();

            view.layout(left,top,right,bottom);

            nexttop = bottom +lp.bottomMargin;//下一个view的top

        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
//        return super.generateLayoutParams(p);
        return new MarginLayoutParams(getContext(),attrs);
    }
}
