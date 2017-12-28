package com.forms;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    /*****
     * 这个Lottie这个库加载的JSON文件必须在assets文件夹下
     * Lottie的一些动画JSON文件可以去https://www.lottiefiles.com/网站下去进行查找
     * 使用心得：
     * 在使用Lottie这个库的时候在一定程度上减少了我们的工作量，如果和UI进行配合好，在一定程度上
     * 可以咸鱼一下，并且IOS RN IOS都是可以共用一套JSON动画文件，如果没有UI情况下我们还是乖乖的
     * 去自定义一些效果，结合属性动画+vectorDrawable+自定义View也是可以做出很多高大上的动画的
     *
     *
     * 该Demo中我就自定义View与使用该库做了一个对比，对于开发人员还是希望UI能够提供这样的东西，如果
     * 你的项目中就是两个小动画，而且自定义实现起来也不是那么复杂，我还是建议自己去写，毕竟引入一个库还是
     * 比较大的，况且还需要UI的配合，该Demo中我实现一个简单的View，大概需要30min，但是使用库基本3min
     * 就搞定了。
     */

    private LottieAnimationView lav;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lav = (LottieAnimationView) findViewById(R.id.lav);
        /****
         * 第一种直接在java文件中调用
         */
//        lav.setAnimation("success.json");
//        lav.loop(true);
//        lav.playAnimation();
        /***
         * Lottie的监听事件
         */
        lav.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.d("animation", "动画开始");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d("animation", "动画结束");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.d("animation", "动画取消");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.d("animation", "动画重复");
            }
        });

    }

    public void startXml(View view) {

        /****
         * 改变图层颜色的方式有下面两张，第一张是进行使用xx过滤，第二种直接是暴力过滤，不管有没有alpha通道
         * 全部给你变个色
         */
//        PorterDuffColorFilter filter=new PorterDuffColorFilter(Color.GRAY, PorterDuff.Mode.LIGHTEN);
//        lav.addColorFilterToLayer("Desk-Girl",filter);
//
//        lav.addColorFilterToLayer("Desk-Boy",new SimpleColorFilter(Color.GREEN));

        if (lav.isAnimating()) {
            lav.cancelAnimation();
        } else {
            lav.playAnimation();
        }

    }

    /****
     * 自定义时常和速度
     * @param v
     */
    public void customTimeAndSpeed(View v) {
        if (lav.isAnimating()) {
            lav.cancelAnimation();
        }
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimator.setDuration(5000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();


                //设置Lottie播放的进度
                lav.setProgress(value);

                /****
                 * 让其在各个进度区间变一个颜色
                 */
                if (value < 0.4) {
                    lav.addColorFilter(new PorterDuffColorFilter(Color.GREEN, PorterDuff.Mode.LIGHTEN));
                } else if (value >= 0.2 && value < 0.6) {
                    lav.addColorFilter(new PorterDuffColorFilter(Color.BLUE, PorterDuff.Mode.LIGHTEN));

                } else if (value >= 0.6 && value < 0.8) {
                    lav.addColorFilter(new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.LIGHTEN));

                } else if (value >= 0.8) {
                    lav.clearColorFilters();
                }
            }
        });

        valueAnimator.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lav.cancelAnimation();
    }

    public void startOne(View view) {

        Intent intent=new Intent(MainActivity.this,MyCheckActivity.class);
        startActivity(intent);
    }

    public void startTwo(View view) {

        Intent intent=new Intent(MainActivity.this,JumpActivity.class);
        startActivity(intent);
    }

    public void startThree(View view) {

        Intent intent=new Intent(MainActivity.this,RotateActivity.class);
        startActivity(intent);
    }

}
