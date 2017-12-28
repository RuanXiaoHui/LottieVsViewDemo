package com.forms;

import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class RotateActivity extends AppCompatActivity {

    private  ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate);

        imageView= (ImageView) findViewById(R.id.imageView);
        ((Animatable) imageView.getDrawable()).start();
    }

}
