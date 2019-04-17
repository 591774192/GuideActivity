package com.casic.guideactivity;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

/**
 * @author 郭宝
 * @project： MVP
 * @package： cn.twsd.mvp.view.base
 * @date： 2018/2/24/024 15:03
 * @brief: 项目框架搭建
 */

public class BaseApplication extends Application {

    private static BaseApplication mApp;
    @Override
    public void onCreate() {
        super.onCreate();
        mApp = (BaseApplication)getApplicationContext();
        //初始化 blankj:utilcode 依赖库
        Utils.init(this);
    }

    public static BaseApplication getInstance() {
        return mApp;
    }
}
