package com.forms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;

public class MyCheckActivity extends AppCompatActivity {

    private MyCheck myCheck;
    private LottieAnimationView lottieAnimationView2;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);
        initView();
    }

    private void initView() {
        myCheck = (MyCheck) findViewById(R.id.myCheck);
        lottieAnimationView2 = (LottieAnimationView) findViewById(R.id.lottieAnimationView2);

        btn = (Button) findViewById(R.id.btn);
        myCheck.playAnimation();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCheck.playAnimation();
                if (lottieAnimationView2.isAnimating()){
                    lottieAnimationView2.cancelAnimation();
                }else{
                    lottieAnimationView2.playAnimation();
                }
            }
        });
    }
}
