package com.forms;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Created by forms on 2017/12/28.
 */

public class TypeEvalutor implements TypeEvaluator<PointF> {

    private int mType;   //1-4   分别代表四个球
    private float ballY;
    private float oldValue = 0;

    public TypeEvalutor(int type, float ballY) {
        this.mType = type;
        this.ballY = ballY;
    }

    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {

        PointF pointF = new PointF(0, 0);
        if (mType == 1) {
            float mBallY = ballY;
            if (fraction <= 0.40f) {
                if (fraction <= 0.2) {
                    pointF.y = mBallY - fraction * (startValue.y - endValue.y) / 0.2f;
                    pointF.x = 0;
                    this.oldValue = pointF.y;
                } else if (fraction > 0.2 && fraction <= 0.4) {
                    pointF.y = oldValue + (fraction - 0.2f) * (startValue.y - endValue.y) / 0.2f;
                    pointF.x = 0;
                }
            } else {
                pointF.x = 0;
                pointF.y = ballY;
            }
        } else if (mType == 2) {
            float mBallY = ballY;
            if (fraction >= 0.15f && fraction <= 0.55f) {
                if (fraction <= 0.35) {
                    pointF.y = mBallY - (fraction - 0.15f) * (startValue.y - endValue.y) / 0.2f;
                    pointF.x = 0;
                    this.oldValue = pointF.y;
                } else if (fraction > 0.35f && fraction <= 0.55f) {
                    pointF.y = oldValue + (fraction - 0.35f) * (startValue.y - endValue.y) / 0.2f;
                    pointF.x = 0;
                }
            } else {
                pointF.x = 0;
                pointF.y = ballY;
            }
        } else if (mType == 3) {
            float mBallY = ballY;
            if (fraction >= 0.30f && fraction <= 0.70f) {
                if (fraction <= 0.5f) {
                    pointF.y = mBallY - (fraction - 0.30f) * (startValue.y - endValue.y) / 0.2f;
                    pointF.x = 0;
                    this.oldValue = pointF.y;
                } else if (fraction > 0.5f && fraction <= 0.70f) {
                    pointF.y = oldValue + (fraction - 0.5f) * (startValue.y - endValue.y) / 0.2f;
                    pointF.x = 0;
                }
            } else {
                pointF.x = 0;
                pointF.y = ballY;
            }
        } else if (mType == 4) {

            float mBallY = ballY;
            if (fraction >= 0.45f && fraction <= 0.85f) {
                if (fraction <= 0.65f) {
                    pointF.y = mBallY - (fraction - 0.45f) * (startValue.y - endValue.y) / 0.2f;
                    pointF.x = 0;
                    this.oldValue = pointF.y;
                } else if (fraction > 0.65f && fraction <= 0.85f) {
                    pointF.y = oldValue + (fraction - 0.65f) * (startValue.y - endValue.y) / 0.2f;
                    pointF.x = 0;
                }
            } else {
                pointF.x = 0;
                pointF.y = ballY;
            }

        }
        return pointF;
    }
}
