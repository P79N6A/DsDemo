package com.dryseed.module_widget.viewPager.pageTransformer;

import android.support.v4.view.ViewPager;
import android.view.View;
import com.easy.moduler.lib.utils.LogUtils;

/**
 * position变化规律：
 *
 * 假设现在ViewPager在A页现在滑出B页，则:
 * A页的position变化就是( 0, -1]
 * B页的position变化就是[ 1 , 0 ]
 *
 * 可以这样理解：上一屏的起始点为-1，当前屏的起始点为0，下一屏的起始点为1
 *
 * @author caiminming
 */
public class DepthPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.75f;

    public void transformPage(View view, float position) {
        LogUtils.d("view : %s | position : %f", view.getTag(), position);

        int pageWidth = view.getWidth();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0);

        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when moving to the left page
            view.setAlpha(1);
            view.setTranslationX(0);
            view.setScaleX(1);
            view.setScaleY(1);

        } else if (position <= 1) { // (0,1]
            // Fade the page out.
            view.setAlpha(1 - position);

            // Counteract the default slide transition
            view.setTranslationX(pageWidth * -position);

            // Scale the page down (between MIN_SCALE and 1)
            float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);
        }

    }

}