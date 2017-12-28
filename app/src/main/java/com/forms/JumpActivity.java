package com.forms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.airbnb.lottie.LottieAnimationView;

public class JumpActivity extends AppCompatActivity {

    private MyJumpView jump;
    private Button btn;
    private LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump);
        initView();
    }

    private void initView() {
        jump = (MyJumpView) findViewById(R.id.jump);
        lottieAnimationView = (LottieAnimationView) findViewById(R.id.lottieAnimationView);

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lottieAnimationView.isAnimating()){
                    lottieAnimationView.cancelAnimation();
                    lottieAnimationView.setProgress(0);
                }else{
                    lottieAnimationView.playAnimation();
                }
                    jump.playBall();
            }
        });
    }
}
