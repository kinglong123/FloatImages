package com.lijian.FloatImage;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by lanjl on 2018/4/10.
 */
public class SuperSlidingDrawer1 extends LinearLayout {
    private final ViewDragHelper mHelper;

    public enum SlidingState {
        EXPANDED,
        COLLAPSED
    }
    private SlidingState mSlideState = SlidingState.COLLAPSED;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public SuperSlidingDrawer1(Context context) {
        this(context, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public SuperSlidingDrawer1(Context context,
            @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public SuperSlidingDrawer1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // setBackgroundColor(Color.parseColor("#50FFFFFF"));
        mHelper = ViewDragHelper.create(this, 1f, new DragHelperCallback());
    }
    int mHiddenViewMeasuredHeight;


    View childView;
    private float mDragOriLeft = Float.MAX_VALUE;
    private float mDragOriTop = Float.MAX_VALUE;
    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();
        System.out.println("1111111111111111onFinishInflate:");

        childView = getChildAt(0);

        if(mDragOriLeft == Float.MAX_VALUE) {//第一次记录位置
            mDragOriLeft = childView.getLeft();
            mDragOriTop = childView.getTop();
            System.out.println("11111111111mDragOriTop:"+mDragOriTop);
        }

    }

    private class DragHelperCallback extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {


            return child == childView;
        }

        @Override
        public int getViewHorizontalDragRange(View child)
        {
            return 1;
        }

        @Override
        public int getViewVerticalDragRange(View child)
        {
            return 1;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx)
        {
            return 0;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy)
        {
            int newTop = Math.max(top, 0);
            System.out.println("111111111:top"+top);
            return newTop;
        }
        @Override
        public void onViewCaptured(View capturedChild, int activePointerId) {
            super.onViewCaptured(capturedChild, activePointerId);


        }
        //手指释放的时候回调
//        @Override
//        public void onViewReleased(View releasedChild, float xvel, float yvel)
//        {
//
//                mHelper.settleCapturedViewAt((int)mDragOriLeft,(int)mDragOriTop);
//                invalidate();

//
//        }


        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel)
        {


            final int childWidth = getHeight()/2;
            System.out.println("2222222222222222222childWidth:"+childWidth);
            System.out.println("2222222222222222222releasedChild.getTop():"+releasedChild.getTop());

            float offset = (releasedChild.getTop()) * 1.0f / childWidth;
            System.out.println("2222222222222222222offset);"+offset);
            System.out.println("2222222222222222222yvel)："+yvel);

            if(yvel>0 &&  offset >0){
                System.out.println("2222222222222222222bbbbbbbbbbbbbb"+yvel);
                mHelper.settleCapturedViewAt((int)mDragOriLeft, getHeight());
            }
            else if(yvel >= 0 && offset > 0.5f){
                System.out.println("2222222222222222222bbbbbbbbbbbbbb"+yvel);
                mHelper.settleCapturedViewAt((int)mDragOriLeft, getHeight());
            }
            else if(yvel<0 &&  offset <0){
                System.out.println("2222222222222222222kkkkkkkkkkkkkkkkkk"+yvel);
                mHelper.settleCapturedViewAt((int)mDragOriLeft, -getHeight());
            }
            else if(yvel<=0  && offset < -0.5f){
                System.out.println("2222222222222222222kkkkkkkkkkkkkkkkkk"+yvel);
                mHelper.settleCapturedViewAt((int)mDragOriLeft, -getHeight());
            }
            else{
                System.out.println("2222222222222222222kkkkkkkkkkkkkkkkkkbbbbb"+mDragOriTop);
                mHelper.settleCapturedViewAt((int)mDragOriLeft,(int)mDragOriTop);
            }

//            mHelper.settleCapturedViewAt(yvel > 0 || xvel == 0 && offset > 0.5f ? 0 : getWidth(), releasedChild.getTop());
//            mHelper.settleCapturedViewAt(xvel > 0 || xvel == 0 && offset > 0.5f ? 0 : -childWidth, releasedChild.getTop());
            invalidate();
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            if (mInitTop == Integer.MIN_VALUE) {

                mInitTop = changedView.getTop();

            }
            mTop = top;

            System.out.println("11111111111111111kkkkkkonViewPositionChanged+"+mTop);
            System.out.println("11111111111111111kkkkkkchangedView.getTop()+"+changedView.getTop());
            mDragRange = getHeight();
            mDragOffset = (float) top / mDragRange;

            System.out.println("222222mDragOffset+"+mDragOffset);
            if (mPositionListener != null) {
                mPositionListener.onPositionChange(mInitTop, changedView.getTop(), Math.abs(mInitTop - changedView.getTop()) * 1.0f / getHeight());
            }
            //  requestLayout();
        }
    }
    private int mInitTop = Integer.MIN_VALUE;
    int mTop;
    int  mDragRange;
    float  mDragOffset;


    private float mInitialMotionX;
    private float mInitialMotionY;
    private ScrollableViewHelper mScrollableViewHelper = new ScrollableViewHelper();

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);

//        if (( action != MotionEvent.ACTION_DOWN)) {
//            mHelper.cancel();
//            return super.onInterceptTouchEvent(ev);
//        }

        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mHelper.cancel();
            return false;
        }

        final float x = ev.getX();
        final float y = ev.getY();
        boolean interceptTap = false;

        System.out.println("11111111111111:"+action);
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                mInitialMotionX = x;
                mInitialMotionY = y;
                //  interceptTap = mDragHelper.isViewUnder(mHeaderView, (int) x, (int) y);
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                final float adx = Math.abs(x - mInitialMotionX);
                final float ady = Math.abs(y - mInitialMotionY);
                float dy = y - mInitialMotionY;
                final int slop = mHelper.getTouchSlop();
                /*useless*/
                if (adx > ady) {
                    mHelper.cancel();
                    return false;
                }
//这边需要向sling组件学习
//                if (dy> 0) { // Collapsing 向下
//
//                    if (mScrollableViewHelper.getScrollableViewScrollPosition(childView, true) > 0) {
//
//                        return true;
//                    }else{
//                        return false;
//                    }
//
//               }


            }
        }

        return mHelper.shouldInterceptTouchEvent(ev) ;
    }




    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        mHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll()
    {
        System.out.println("computeScroll");
        if (mHelper != null && mHelper.continueSettling(true)) {

            invalidate();
        }else {

            if(Math.abs(mDragOffset) > 0.8 ){
                mSlideState = SlidingState.COLLAPSED;
                if(mPositionListener!=null){
                    mPositionListener.onFlingOutFinish();
                }


            }
        }
    }

    public void setPositionListener(FloatView.OnPositionChangeListener positionListener) {
        mPositionListener = positionListener;
    }

    private FloatView.OnPositionChangeListener mPositionListener;
    public interface OnPositionChangeListener {

        void onPositionChange(int initTop, int nowTop, float ratio);

        void onFlingOutFinish();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec,  heightMeasureSpec);

        mHiddenViewMeasuredHeight= MeasureSpec.getSize(heightMeasureSpec);



    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mHiddenViewMeasuredHeight = childView.getHeight();

        if(mSlideState == SlidingState.COLLAPSED){
            //super.onLayout(changed, l, t-mHiddenViewMeasuredHeight, r, b-mHiddenViewMeasuredHeight);
            childView.layout(l, t-mHiddenViewMeasuredHeight, r, b-mHiddenViewMeasuredHeight);

        }









    }


    public void animateOpen() {
//        this.setVisibility(View.VISIBLE);
//        mHelper.settleCapturedViewAt((int)mDragOriLeft,(int)mDragOriTop);
        mSlideState = SlidingState.EXPANDED;
        if (mHelper != null ) {
            View child = getChildAt(0);

            mHelper.smoothSlideViewTo(child,
                    (int)mDragOriLeft,(int)mDragOriTop);

            invalidate();

        }


    }
    public SlidingState getSlideState() {
        return mSlideState;

    }

    public void animateClose() {
//        this.setVisibility(View.GONE);
        mSlideState = SlidingState.COLLAPSED;
        if (mHelper != null ) {
            View child = getChildAt(0);

            mHelper.smoothSlideViewTo(child,
                    getLeft(),getTop());
            mHelper.smoothSlideViewTo(child,(int)mDragOriLeft, getHeight());
            invalidate();

        }


    }



}
