package cn.fushui.xutils3_demo;

import android.app.Application;

import org.xutils.x;

/**
 * Created by jianfeng on 2016/6/8.
 */
public class XutilsApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
