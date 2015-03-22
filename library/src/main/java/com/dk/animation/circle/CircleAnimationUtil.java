package com.dk.animation.circle;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;

import java.lang.ref.WeakReference;

/**
 * Created by DK on 2015/3/19.
 */
public class CircleAnimationUtil {
    private static final int DURATION = 2000;
    private View mTarget;
    private View mDest;
    private int originX;
    private int originY;
    private int destX;
    private int destY;
    private int mDuration = DURATION;
    private WeakReference<Activity> mContextReference;

    //    private CircleLayout mCircleLayout;
    private Bitmap mBitmap;
    private CircleImageView mImageView;

    public CircleAnimationUtil() {
    }

    public CircleAnimationUtil attachActivity(Activity activity) {
        mContextReference = new WeakReference<Activity>(activity);
        return this;
    }

    public CircleAnimationUtil setTargetView(View view) {
        mTarget = view;
        return this;
    }

    public CircleAnimationUtil setOriginRect(int x, int y) {
        originX = x;
        originY = y;
        return this;
    }

    public CircleAnimationUtil setDestRect(int x, int y) {
        destX = x;
        destY = y;
        return this;
    }

    public CircleAnimationUtil setDestView(View view) {
        mDest = view;
        return this;
    }

    private boolean prepare() {
        if (mContextReference.get() != null) {
            ViewGroup decoreView = (ViewGroup) mContextReference.get().getWindow().getDecorView();

            mBitmap = drawViewToBitmap(mTarget, mTarget.getWidth(), mTarget.getHeight());
            if (mImageView == null)
                mImageView = new CircleImageView(mContextReference.get());
            mImageView.setImageBitmap(mBitmap);


            int[] src = new int[2];
            mTarget.getLocationOnScreen(src);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(mTarget.getWidth(), mTarget.getHeight());
            params.setMargins(src[0], src[1], 0, 0);
            if (mImageView.getParent() == null)
                decoreView.addView(mImageView, params);
        }
        return true;
    }

    public void startAnimation() {

        if (prepare()) {
            mTarget.setVisibility(View.INVISIBLE);
            getAvatarRevealAnimator().start();
        }
    }

    private AnimatorSet getAvatarRevealAnimator() {
        AnimatorSet animatorSet = new AnimatorSet();
        final int endRadius = Math.max(destX, destY) / 2;
        int startRadius = Math.max(originX, originY);

        Animator mRevealAnimator = ObjectAnimator.ofFloat(mImageView, "drawableRadius", startRadius, endRadius);
        mRevealAnimator.setInterpolator(new AccelerateInterpolator());
        mRevealAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
//                mTarget.setRevealClip(true, originX / 2, originY / 2, endRadius);
//                try {
//                    Method method = Class.forName("android.view.View").getDeclaredMethod("setRevealClip", boolean.class, float.class, float.class, float.class);
//                    method.setAccessible(true);
//                    method.invoke(mTarget, true, originX / 2, originY / 2, endRadius);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

                int[] src = new int[2];
                int[] dest = new int[2];
                mImageView.getLocationOnScreen(src);
                mDest.getLocationOnScreen(dest);

                Animator translatorX = ObjectAnimator.ofFloat(mImageView, View.X, 0, dest[0] - src[0] - 2 * endRadius);
                translatorX.setInterpolator(new TimeInterpolator() {
                    @Override
                    public float getInterpolation(float input) {
                        return (float) (Math.sin((0.5f * input) * Math.PI));
                    }
                });
                float y = mImageView.getY();
                Animator translatorY = ObjectAnimator.ofFloat(mImageView, View.Y, y, y / 2 + dest[1] - src[1]);
                translatorY.setInterpolator(new LinearInterpolator());

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(translatorX, translatorY);
                animatorSet.setDuration(mDuration);
                animatorSet.playSequentially();
                animatorSet.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        AnimatorSet animatorSet = new AnimatorSet();
                        Animator scaleAnimatorY = ObjectAnimator.ofFloat(mImageView, View.SCALE_Y, 0.5f, 0);
                        Animator scaleAnimatorX = ObjectAnimator.ofFloat(mImageView, View.SCALE_X, 0.5f, 0);
                        animatorSet.setDuration(mDuration);
                        animatorSet.playTogether(scaleAnimatorX, scaleAnimatorY);
                        animatorSet.start();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                animatorSet.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

//        float scaleFactor = Math.max(2f * destY / originY, 2f * destX / originX);
        float scaleFactor = 0.5f;
        Animator scaleAnimatorY = ObjectAnimator.ofFloat(mImageView, View.SCALE_Y, 1, 1, 1, 1, scaleFactor);
        Animator scaleAnimatorX = ObjectAnimator.ofFloat(mImageView, View.SCALE_X, 1, 1, 1, 1, scaleFactor);
        animatorSet.setDuration(500);
        animatorSet.playTogether(scaleAnimatorX, scaleAnimatorY, mRevealAnimator);

        return animatorSet;
    }

    private Bitmap drawViewToBitmap(View view, int width, int height) {
        Drawable drawable = new BitmapDrawable();
//        view.layout(0, 0, width, height);
        Bitmap dest = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(dest);
        drawable.setBounds(new Rect(0, 0, width, height));
        drawable.draw(c);
        view.draw(c);
//        view.layout(0, 0, width, height);
        return dest;
    }

    private void reset() {
        mBitmap.recycle();
        mBitmap = null;
        if (mImageView.getParent() != null)
            ((ViewGroup) mImageView.getParent()).removeView(mImageView);
        mImageView = null;
    }
}
