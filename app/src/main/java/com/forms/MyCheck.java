package com.forms;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by forms on 2017/12/28.
 * 功能分析：
 * 1.首先绘制圆弧
 * 2.圆弧结束后外圈的那个圆环做缩放从1-0.7-1  内部有个圆开始做半径放大的动画，这个时间与圆环的时间要一致
 * 使用属性动画进行完成
 * 3.上面绘制完毕以后，在做对号操作，对号是两条线
 */

public class MyCheck extends View {

    private Paint mPaint;       //绘制圆的画笔
    private Paint mNicePaint;       //绘制圆的画笔
    private float storkWidth = 12; //圆的外边框宽度
    private float storkNiceWidth = 15; //nice笔宽度
    private float mRadius = 60;   //圆的半径
    private int circleWidth;
    private static final int suggestWidth = 100;  //建议的最小宽度
    private RectF circleRectf;    //包裹圆的矩形
    private float angle = 0;          //外部圆环的角度
    private float internalCircle = 0;   //内部缩放圆半径
    private boolean isAnimationStart = false;
    private float mCircleStorkScaleWidth = 0;   //边框进行缩放的比例
    private RectF niceRectf;
    private float lineOneScale = 0;
    private float lineTwoScale = 0;
    private boolean isFirstLineEnd = false;
    private boolean isFirstLineStart = false;
    private ValueAnimator animator_extral_circle;     //绘制外部圆环属性动画
    private ValueAnimator animator_internal_cicle;   //绘制内部圆放大属性动画
    private ValueAnimator animator_extral_scale;    //绘制外部圆环缩放属性动画
    private ValueAnimator animator_line_one;        //对号左边线
    private ValueAnimator animator_line_two;        //对号右边线
    private AnimatorSet animatorSet;


    public MyCheck(Context context) {
        this(context, null);
    }

    public MyCheck(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public MyCheck(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(storkWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(0xFF59B546);


        mNicePaint = new Paint();
        mNicePaint.setAntiAlias(true);
        mNicePaint.setStrokeWidth(storkNiceWidth);
        mNicePaint.setStyle(Paint.Style.STROKE);
        mNicePaint.setStrokeCap(Paint.Cap.ROUND);
        mNicePaint.setColor(Color.WHITE);

        circleRectf = new RectF();
        niceRectf = new RectF();

        extralCircleAngle();
        scaleAnimation();
        scaleAnimationLineOne();
        scaleAnimationLineTwo();
        animatorSet = new AnimatorSet();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (internalCircle >= 0) {
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(circleWidth / 2.0f, circleWidth / 2.0f, internalCircle, mPaint);
        }

        mPaint.setStyle(Paint.Style.STROKE);
        circleRectf.set(storkWidth / 2.0f + mCircleStorkScaleWidth,
                storkWidth / 2.0f + mCircleStorkScaleWidth,
                circleWidth - storkWidth / 2.0f - mCircleStorkScaleWidth,
                circleWidth - storkWidth / 2.0f - mCircleStorkScaleWidth);
        canvas.drawArc(circleRectf, -90, angle, false, mPaint);

        float left = circleWidth / 6.0f;
        float top = circleWidth / 4.0f;
        float right = circleWidth / 2.0f + circleWidth / 3.0f;
        float bottom = circleWidth / 4.0f + circleWidth / 2.0f;

//        circleRectf.set(left, top,right, bottom); //bottom

        //width

//        canvas.drawRect(circleRectf,mNicePaint);

        //第一条线

        if (isFirstLineStart) {
            float startX = left;
            float startY = top + (bottom - top) / 2.0f;
            canvas.drawLine(startX, startY,
                    (startX + (right - left) * lineOneScale / 3.0f),
                    startY + (bottom - startY) * lineOneScale, mNicePaint);
        }

        //第二条线
        if (isFirstLineEnd) {
            float startX = left + (right - left) * 1 / 3.0f;
            float startY = bottom;
            canvas.drawLine(startX, startY,
                    startX + (right - startX) * lineTwoScale,
                    startY - (bottom - top) * lineTwoScale, mNicePaint);

        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                circleWidth = Math.min(widthSize, getSuggestedMinimumWidth());
                break;
            case MeasureSpec.EXACTLY:
                circleWidth = widthSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                circleWidth = getSuggestedMinimumWidth();
                break;
        }
        mRadius = (circleWidth - storkWidth / 2.0f) / 2.0f;
    }

    @Override
    protected int getSuggestedMinimumWidth() {
        return suggestWidth;
    }

    private void extralCircleAngle() {
        animator_extral_circle = ValueAnimator.ofFloat(0, 360.0f);
        animator_extral_circle.setDuration(800);
        animator_extral_circle.setInterpolator(new DecelerateInterpolator());
        animator_extral_circle.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                angle = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
    }

    private void scaleAnimation() {
        animator_internal_cicle = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator_internal_cicle.setDuration(200);
        animator_internal_cicle.setInterpolator(new AccelerateDecelerateInterpolator());
        animator_internal_cicle.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                internalCircle = (float) animation.getAnimatedValue() * mRadius;
                invalidate();
            }
        });
        animator_extral_scale = ValueAnimator.ofFloat(1.0f, .7f, 1.0f);
        animator_extral_scale.setDuration(150);
        animator_extral_scale.setInterpolator(new AccelerateDecelerateInterpolator());
        animator_extral_scale.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCircleStorkScaleWidth = (1.0f - (float) animation.getAnimatedValue()) * mRadius;
                invalidate();
            }
        });
        animator_extral_scale.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                //super.onAnimationEnd(animation);
                isFirstLineStart = true;
            }
        });

    }

    private void scaleAnimationLineOne() {
        animator_line_one = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator_line_one.setDuration(200);
        animator_line_one.setInterpolator(new AccelerateDecelerateInterpolator());
        animator_line_one.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                lineOneScale = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator_line_one.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
                isFirstLineEnd = true;
            }
        });
    }

    private void scaleAnimationLineTwo() {
        animator_line_two = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator_line_two.setDuration(150);
        animator_line_two.setInterpolator(new AccelerateDecelerateInterpolator());
        animator_line_two.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                lineTwoScale = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
    }

    public void playAnimation() {
        if (animatorSet.isRunning()) {
            animatorSet.end();
        } else {
            animatorSet = new AnimatorSet();
            releaseRes();
            animatorSet.play(animator_extral_scale).with(animator_internal_cicle);
            animatorSet.play(animator_line_two).with(animator_line_one);
            animatorSet.play(animator_line_one).after(animator_extral_scale);
            animatorSet.play(animator_extral_circle).before(animator_extral_scale);
            animatorSet.start();
        }
    }

    private void releaseRes() {
        lineOneScale = 0;
        lineTwoScale = 0;
        isFirstLineEnd = false;
        isFirstLineStart = false;
        angle = 0;
        internalCircle = 0;
        isAnimationStart = false;
    }

}
