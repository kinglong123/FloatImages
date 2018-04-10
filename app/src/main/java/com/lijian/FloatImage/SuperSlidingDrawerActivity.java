package com.lijian.FloatImage;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by lanjl on 2018/4/10.
 */
public class SuperSlidingDrawerActivity extends FragmentActivity {

   LinearLayout mLinearLayout;
    SuperSlidingDrawer mSuperSlidingDrawer;
    int mHiddenViewMeasuredHeight;
    FrameLayout mFrameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sliding);

        mFrameLayout = findViewById(R.id.ff);

        mLinearLayout = findViewById(R.id.ll);
        mSuperSlidingDrawer = findViewById(R.id.sl);


        mHiddenViewMeasuredHeight =  mSuperSlidingDrawer.getHeight();
        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSuperSlidingDrawer.getSlideState() == SuperSlidingDrawer.SlidingState.COLLAPSED) {
                    mSuperSlidingDrawer.animateOpen();

                } else {
                    mSuperSlidingDrawer.animateClose();

                }
            }
        });

        RootFragment fragment;
        if(savedInstanceState == null)
            fragment = (RootFragment) FragmentsUtils.swapFragments(getSupportFragmentManager(), R.id.ff, RootFragment.newInstance());
        else
            fragment = (RootFragment) FragmentsUtils.findFragment(getSupportFragmentManager(), RootFragment.newInstance(), null);


    }

//    private void animateOpen(View v) {
//        v.setVisibility(View.VISIBLE);
//        ValueAnimator animator = createDropAnimator(v, 0,
//                mHiddenViewMeasuredHeight);
//        animator.start();
//
//    }
//
//    private void animateClose(final View view) {
//        int origHeight = view.getHeight();
//        ValueAnimator animator = createDropAnimator(view, origHeight, 0);
//        animator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(android.animation.Animator animation) {
//                view.setVisibility(View.GONE);
//            }
//
//        });
//        animator.start();
//    }
//
//    private ValueAnimator createDropAnimator(final View v, int start, int end) {
//        ValueAnimator animator = ValueAnimator.ofInt(start, end);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//
//            @Override
//            public void onAnimationUpdate(ValueAnimator arg0) {
//                int value = (int) arg0.getAnimatedValue();
//                ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
//                layoutParams.height = value;
//                v.setLayoutParams(layoutParams);
//
//            }
//        });
//        return animator;
//    }


}
