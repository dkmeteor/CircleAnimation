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
    private View mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTarget = findViewById(R.id.target);
        mFab = findViewById(R.id.fab);
        mTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CircleAnimationUtil util = new CircleAnimationUtil();
                util.attachActivity(MainActiviy.this);
                util.setOriginRect(mTarget.getWidth(), mTarget.getHeight());
                util.setDestRect(mFab.getWidth(), mFab.getWidth());
                util.setTargetView(mTarget);
                util.setDestView(mFab);
                util.startAnimation();
            }
        });
    }
}
