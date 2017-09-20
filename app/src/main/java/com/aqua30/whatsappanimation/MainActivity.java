package com.aqua30.whatsappanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aqua30.whatsappanimation.base.BaseActivity;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Saurabh(aqua) in 2017.
 */

public class MainActivity extends BaseActivity {

    /* view binding */
    @BindView(R.id.attachment_container)RelativeLayout attachmentContainer;
    @BindView(R.id.item_a)TextView itemA;
    @BindView(R.id.item_b)TextView itemB;
    @BindView(R.id.item_c)TextView itemC;
    @BindView(R.id.item_d)TextView itemD;
    @BindView(R.id.item_e)TextView itemE;
    @BindView(R.id.item_f)TextView itemF;

    private boolean reveal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewHelper.setViewVisibility(attachmentContainer, false);
        ViewHelper.setViewVisibility(itemA, false);
        ViewHelper.setViewVisibility(itemB, false);
        ViewHelper.setViewVisibility(itemC, false);
        ViewHelper.setViewVisibility(itemD, false);
        ViewHelper.setViewVisibility(itemE, false);
        ViewHelper.setViewVisibility(itemF, false);
        ViewHelper.setViewVisibility(attachmentContainer, false);
    }

    @OnClick(R.id.attachment_button)
    void onAttachmentClick(){
        revealAttachments();
    }

    private void revealAttachments() {
        int cx = attachmentContainer.getWidth();
        int cy = attachmentContainer.getHeight();
        float finalRadius = (float) Math.hypot(cx, cy);
        if (!reveal) {
            reveal = true;
            Animator anim = ViewAnimationUtils.createCircularReveal(attachmentContainer, cx, cy, 0, finalRadius);
            attachmentContainer.setVisibility(View.VISIBLE);
            anim.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    ViewHelper.setViewVisibility(itemF, true);
                    ViewHelper.setViewVisibility(itemE, true);
                    ViewHelper.setViewVisibility(itemD, true);
                    ViewHelper.setViewVisibility(itemC, true);
                    ViewHelper.setViewVisibility(itemB, true);
                    ViewHelper.setViewVisibility(itemA, true);
                    animateView(itemF, 100);
                    animateView(itemE, 150);
                    animateView(itemD, 200);
                    animateView(itemC, 250);
                    animateView(itemB, 300);
                    animateView(itemA, 350);
                }

            });
            anim.start();
        } else {
            reveal = false;
            Animator anim = ViewAnimationUtils.createCircularReveal(attachmentContainer, cx, cy, finalRadius, 0);
            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    ViewHelper.setViewVisibility(attachmentContainer, false);
                    ViewHelper.setViewVisibility(attachmentContainer, false);
                    ViewHelper.setViewVisibility(itemA, false);
                    ViewHelper.setViewVisibility(itemB, false);
                    ViewHelper.setViewVisibility(itemC, false);
                    ViewHelper.setViewVisibility(itemD, false);
                    ViewHelper.setViewVisibility(itemE, false);
                    ViewHelper.setViewVisibility(itemF, false);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            anim.start();
        }
    }

    void animateView(View view, int delay){
        view.setScaleY(0f);
        view.setScaleX(0f);
        view.animate()
                .scaleX(1f)
                .scaleY(1f)
                .setInterpolator(new OvershootInterpolator(1.f))
                .setDuration(delay)
                .setStartDelay(delay);
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.ac_main;
    }
}
