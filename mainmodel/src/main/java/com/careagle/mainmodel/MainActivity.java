package com.careagle.mainmodel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv).setOnClickListener(this);
        Log.e("tag", "MainActivity");

    }

    @Override
    public void onClick(View view) {
        ARouter.getInstance().build("/test/activity")
                .withString("text", "haha").navigation();
    }
}
