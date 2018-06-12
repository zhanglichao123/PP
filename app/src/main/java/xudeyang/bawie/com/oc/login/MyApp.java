package xudeyang.bawie.com.oc.login;

import android.app.Application;

import com.mob.MobSDK;

/**
 * author:Created by WangZhiQiang on 2018/6/12.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MobSDK.init(this);
    }
}
