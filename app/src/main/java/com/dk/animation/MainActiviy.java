package com.dk.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.dk.animation.circle.CircleAnimationUtil;

/**
 * Created by DK on 2015/3/19.
 */
public class MainActiviy extends Activity {
    private View mTarget;
    private View mFab1;
    private View mFab2;
    private View mFab3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTarget = findViewById(R.id.target);
        findViewById(R.id.fab1).setOnClickListener(mOnClickListener);
        findViewById(R.id.fab2).setOnClickListener(mOnClickListener);
        findViewById(R.id.fab3).setOnClickListener(mOnClickListener);

    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CircleAnimationUtil util = new CircleAnimationUtil();
            util.attachActivity(MainActiviy.this);
            util.setOriginRect(mTarget.getWidth(), mTarget.getHeight());
            util.setDestRect(v.getWidth(), v.getWidth());
            util.setTargetView(mTarget);
            util.setDestView(v);
            util.startAnimation();
        }
    };
}
