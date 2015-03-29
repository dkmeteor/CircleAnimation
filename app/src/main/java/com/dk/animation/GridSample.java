package com.dk.animation;

import android.animation.Animator;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dk.animation.circle.CircleAnimationUtil;
import com.melnykov.fab.FloatingActionButton;

import org.lucasr.twowayview.ItemClickSupport;
import org.lucasr.twowayview.widget.TwoWayView;

/**
 * Created by DK on 2015/3/30.
 */
public class GridSample extends Activity {
    private TwoWayView mRecyclerView;
    private FloatingActionButton mFab;
    private LayoutAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        mRecyclerView = (TwoWayView) findViewById(R.id.list);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLongClickable(true);
        mAdapter = new LayoutAdapter(this, mRecyclerView, R.layout.layout_grid);
        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        final ItemClickSupport itemClick = ItemClickSupport.addTo(mRecyclerView);

        itemClick.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View child, final int position, long id) {
                if (position != 0) {
                    CircleAnimationUtil util = new CircleAnimationUtil().attachActivity(GridSample.this).setTargetView(child).setDestView(mFab);
                    util.setAnimationListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mAdapter.removeItem(position);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                    util.startAnimation();
                }
            }
        });

    }

}
