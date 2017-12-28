package com.forms;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by forms on 2017/12/28.
 */

public class MyJumpView extends View {

    private int mWidth = 0;   //画布的宽度
    private int mHeight = 0; //画布的高度
    private Paint mPaint;  //初始化画笔
    private float mRadius;//绘制球的半径
    private ValueAnimator oneBallAnimation;  //第一个球动画
    private ValueAnimator twoBallAnimation;  //第二个球动画
    private ValueAnimator threeBallAnimation; //第三个球动画
    private ValueAnimator fourBallAnimation;  //第四个球动画
    private float oneBallY = 0;
    private float twoBallY = 0;
    private float threeBallY = 0;
    private float fourBallY = 0;
    private AnimatorSet animatorSet;


    public MyJumpView(Context context) {
        this(context, null);
    }

    public MyJumpView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public MyJumpView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5);
        animatorSet = new AnimatorSet();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(0xFFE53935);
        canvas.drawCircle(mRadius, oneBallY, mRadius, mPaint);

        mPaint.setColor(0xFF1E88E5);
        canvas.drawCircle(mRadius * 4, twoBallY, mRadius, mPaint);

        mPaint.setColor(0xFF43A047);
        canvas.drawCircle(mRadius * 7, threeBallY, mRadius, mPaint);

        mPaint.setColor(0xFFFDD835);
        canvas.drawCircle(mRadius * 10, fourBallY, mRadius, mPaint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        mWidth = measureWidth(widthMode, widthSize);
        mHeight = measureWidth(heightMode, heightSize);
        mRadius = mWidth / 11.0f;
        oneBallY = twoBallY = threeBallY = fourBallY = (mHeight - mRadius);
        oneBallAnimation();
        twoBallAnimation();
        threeBallAnimation();
        fourBallAnimation();
        setMeasuredDimension(mWidth, mHeight);

    }

    @Override
    protected int getSuggestedMinimumWidth() {
        return 200;
    }

    @Override
    protected int getSuggestedMinimumHeight() {
        return 100;
    }

    private int measureWidth(int mode, int width) {
        int result = 0;
        switch (mode) {
            case MeasureSpec.AT_MOST:
                result = Math.min(width, getSuggestedMinimumWidth());
                break;
            case MeasureSpec.EXACTLY:
                result = width;
                break;
            case MeasureSpec.UNSPECIFIED:
                result = getSuggestedMinimumWidth();
                break;
        }

        return result;
    }

    private void oneBallAnimation() {
        TypeEvalutor type = new TypeEvalutor(1, oneBallY);
        oneBallAnimation = ValueAnimator.ofObject(type, new PointF(mRadius, mHeight - mRadius),
                new PointF(mRadius, mHeight - mRadius * 4));
        oneBallAnimation.setDuration(1800);
        oneBallAnimation.setRepeatCount(ValueAnimator.INFINITE);
        oneBallAnimation.setRepeatMode(ValueAnimator.RESTART);
        oneBallAnimation.setInterpolator(new DecelerateInterpolator());
        oneBallAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                oneBallY = pointF.y;
                invalidate();
            }
        });
    }

    private void twoBallAnimation() {
        TypeEvalutor type = new TypeEvalutor(2, twoBallY);
        twoBallAnimation = ValueAnimator.ofObject(type, new PointF(mRadius * 4, mHeight - mRadius),
                new PointF(mRadius * 4, mHeight - mRadius * 4));
        twoBallAnimation.setDuration(1800);
        twoBallAnimation.setRepeatCount(ValueAnimator.INFINITE);
        twoBallAnimation.setRepeatMode(ValueAnimator.RESTART);
        twoBallAnimation.setInterpolator(new DecelerateInterpolator());
        twoBallAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                twoBallY = pointF.y;
                invalidate();
            }
        });
    }

    private void threeBallAnimation() {
        TypeEvalutor type = new TypeEvalutor(3, threeBallY);
        threeBallAnimation = ValueAnimator.ofObject(type, new PointF(mRadius * 7, mHeight - mRadius),
                new PointF(mRadius * 7, mHeight - mRadius * 4));
        threeBallAnimation.setDuration(1800);
        threeBallAnimation.setRepeatCount(ValueAnimator.INFINITE);
        threeBallAnimation.setRepeatMode(ValueAnimator.RESTART);
        threeBallAnimation.setInterpolator(new DecelerateInterpolator());
        threeBallAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                threeBallY = pointF.y;
                invalidate();
            }
        });
    }

    private void fourBallAnimation() {
        TypeEvalutor type = new TypeEvalutor(4, fourBallY);
        fourBallAnimation = ValueAnimator.ofObject(type, new PointF(mRadius * 10, mHeight - mRadius),
                new PointF(mRadius * 10, mHeight - mRadius * 4));
        fourBallAnimation.setDuration(1800);
        fourBallAnimation.setRepeatCount(ValueAnimator.INFINITE);
        fourBallAnimation.setRepeatMode(ValueAnimator.RESTART);
        fourBallAnimation.setInterpolator(new DecelerateInterpolator());
        fourBallAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                fourBallY = pointF.y;
                invalidate();
            }
        });
    }

    public void playBall() {
        if (animatorSet.isStarted()) {
            animatorSet.end();
            oneBallY = twoBallY = threeBallY = fourBallY = (mHeight - mRadius);
        } else {
            animatorSet = new AnimatorSet();
            animatorSet.playTogether(oneBallAnimation, twoBallAnimation, threeBallAnimation,
                    fourBallAnimation);
            animatorSet.start();
        }
    }
}
