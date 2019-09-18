package com.sen.study_android.animators;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.graphics.Color;
import android.graphics.Path;
import android.os.Build;
import android.support.animation.DynamicAnimation;
import android.support.animation.FlingAnimation;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.transition.*;
import android.util.Log;
import android.view.*;
import android.view.animation.LayoutAnimationController;
import android.view.animation.PathInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import com.sen.study_android.R;
import com.sen.study_android.utils.Slog;

public class AnimatorsActivity extends AppCompatActivity {
    private View view;

    int maxTransitionX = 0;
    int maxTransitionY = 0;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_animators);

        view = findViewById(R.id.fling_animation);
        View main = findViewById(R.id.fling_animation_contain);
        main.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                maxTransitionX = main.getWidth() - view.getWidth();
                maxTransitionY = main.getHeight() - view.getHeight();
                Log.e("Sen", "--->onGlobalLayout-->" + maxTransitionX);
            }
        });


        gestureDetector = new GestureDetector(this, gestureListener);
//        view.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return gestureDetector.onTouchEvent(event);
//            }
//        });


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition slide = new Explode();
            slide.setDuration(2000);
            getWindow().setEnterTransition(slide);
            getWindow().setExitTransition(slide);
            getWindow().getSharedElementExitTransition().setDuration(2000);
            getWindow().getSharedElementEnterTransition().setDuration(2000);
            getWindow().setAllowEnterTransitionOverlap(false);


        }

        viewGroup = findViewById(R.id.tranlation_root);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Transition transition = new Fade(Fade.IN);
            transition.setDuration(5000);
            TransitionManager.beginDelayedTransition(viewGroup, transition);
            viewGroup.addView(LayoutInflater.from(this).inflate(R.layout.a_scene, null));

        }


        ViewPager pager = findViewById(R.id.viewpage_animate);
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 6;
            }

            @Override
            public Fragment getItem(int i) {
                PageFragment pageFragment = new PageFragment();
                pageFragment.bg = Color.BLUE << (i % 3 * 8) | 0xff000000;
                pageFragment.name = "name=" + i;

                return pageFragment;
            }


        });

        pager.setPageTransformer(false, new ViewPager.PageTransformer() {

            private static final float MIN_SCALE = 0.85f;
            private static final float MIN_ALPHA = 0.5f;
            @Override
            public void transformPage(@NonNull View view, float position) {
                Slog.e("sen", "transformPage-------> " + "\tposition=" + position + "\tview -->" + view.getTag().toString());
                int pageWidth = view.getWidth();
                int pageHeight = view.getHeight();

                if (position < -1) { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    view.setAlpha(0f);

                } else if (position <= 1) { // [-1,1]
                    // Modify the default slide transition to shrink the page as well
                    float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                    float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                    float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                    if (position < 0) {
                        view.setTranslationX(horzMargin - vertMargin / 2);
                    } else {
                        view.setTranslationX(-horzMargin + vertMargin / 2);
                    }

                    // Scale the page down (between MIN_SCALE and 1)
                    view.setScaleX(scaleFactor);
                    view.setScaleY(scaleFactor);

                    // Fade the page relative to its size.
                    view.setAlpha(MIN_ALPHA +
                            (scaleFactor - MIN_SCALE) /
                                    (1 - MIN_SCALE) * (1 - MIN_ALPHA));

                } else { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    view.setAlpha(0f);
                }

            }
        });


    }


    private int fadeTime = 3000;

    public void fadeIn(View view) {
        view.setAlpha(0);
        view.animate()
                .alpha(1)
                .setDuration(fadeTime)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {


                    }
                })
                .start();
    }

    public void fadeOut(View view) {
        view.animate()
                .alpha(0)
                .setDuration(fadeTime)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {


                    }
                })
                .start();
    }

    public void translationX(View view) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Path path = new Path();
            path.addRect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom() + 100, Path.Direction.CCW);

            ObjectAnimator.ofFloat(view, View.X, View.Y, path).setDuration(8000).start();
        }

    }

    private GestureDetector gestureDetector;

    private GestureDetector.SimpleOnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener() {
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            Log.e("Sen", "--->onFling-->" + velocityX);
            if (Math.abs(velocityX) > Math.abs(velocityY)) {
                FlingAnimation FlingAnimation = new FlingAnimation(view, DynamicAnimation.TRANSLATION_X);

                FlingAnimation.setStartVelocity(velocityX)
                        .setMinValue(0f)
                        .setMaxValue(maxTransitionX)
                        .setFriction(1.1f)
                        .start();

            } else {
                FlingAnimation FlingAnimation = new FlingAnimation(view, DynamicAnimation.TRANSLATION_Y);
                FlingAnimation.setStartVelocity(velocityY)
                        .setMinValue(0f)
                        .setMaxValue(maxTransitionY)
                        .setFriction(1.1f)
                        .start();
            }

            return true;
        }


        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
    };

    public void flingAnimation(View view) {
        FlingAnimation fling = new FlingAnimation(view, DynamicAnimation.TRANSLATION_X);
        fling.setStartVelocity(4000)
                .setMinValue(0f)
                .setMaxValue(84)
                .setFriction(1.1f)
                .start();
    }


    private void setViewTranslateAnimation(ViewGroup group) {
        LayoutTransition layoutTransition = new LayoutTransition();
        layoutTransition.setAnimator(LayoutTransition.APPEARING, ObjectAnimator.ofFloat(null, "translationX", group.getWidth(), 0));
        group.setLayoutTransition(layoutTransition);


    }


    public void layoutAnimate(View view) {
        ViewGroup viewGroup = findViewById(R.id.ll_layout_animate);
        setViewTranslateAnimation(viewGroup);
        TextView textView = new TextView(this);
        textView.setText("test"+      view.isHardwareAccelerated());
        viewGroup.addView(textView);

    }

    public void removeLayoutAnimate(View view) {
        ViewGroup viewGroup = findViewById(R.id.ll_layout_animate);
        setViewTranslateAnimation(viewGroup);
        viewGroup.removeViewAt(viewGroup.getChildCount() - 1);
    }


    Scene scene;
    private ViewGroup viewGroup;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void tranlationAnimation(View view) {
        if (scene == null) {
            viewGroup = findViewById(R.id.tranlation_root);
            scene = Scene.getSceneForLayout(viewGroup, R.layout.a_scene, this);

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Transition transition = new Fade(Fade.IN);
            TransitionManager.go(scene, transition);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void tranlationAnimationVisibility(View view) {
        TransitionManager.beginDelayedTransition(viewGroup, new Explode());
        if (viewGroup != null) {
            viewGroup.setVisibility(viewGroup.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
        }

    }
}
