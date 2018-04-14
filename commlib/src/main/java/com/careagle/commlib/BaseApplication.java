package com.careagle.commlib;

import android.app.Application;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by lida on 2018/4/14.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (com.careagle.commlib.BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            Log.e("tag", "开启路由");
        }
        ARouter.init(this);
    }
}
