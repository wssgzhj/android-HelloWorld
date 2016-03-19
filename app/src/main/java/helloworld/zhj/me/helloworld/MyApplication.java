package helloworld.zhj.me.helloworld;

import android.app.Application;
import android.content.Context;

/**
 * Created by zhj-plusplus on 3/6/16.
 */
public class MyApplication extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getBaseContext();
    }

    public static Context getContext() {
        return sContext;
    }
}
