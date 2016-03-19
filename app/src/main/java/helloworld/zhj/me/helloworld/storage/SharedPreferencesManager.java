package helloworld.zhj.me.helloworld.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import helloworld.zhj.me.helloworld.MyApplication;
import helloworld.zhj.me.helloworld.utils.PhoneInfoUtil;

/**
 * Created by zhj-plusplus on 3/12/16.
 */
public class SharedPreferencesManager {

    private static String FILE_NAME = "sp_hello_world";

    private static SharedPreferencesManager sInstance;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private SharedPreferencesManager() {
        Context context = MyApplication.getContext();
        if (Build.VERSION.SDK_INT > 10) {
            mSharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_MULTI_PROCESS);
        } else {
            mSharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        }
        mEditor = mSharedPreferences.edit();
    }

    public static SharedPreferencesManager getInstance() {
        if (sInstance == null) {
            synchronized (SharedPreferencesManager.class) {
                if (sInstance == null) {
                    sInstance = new SharedPreferencesManager();
                }
            }
        }
        return sInstance;
    }

    public void setNewVersion(boolean isNewVersion) {
        final String started = String.format("isFirstIn%s", PhoneInfoUtil.getVersionName());
        mEditor.putBoolean(started, isNewVersion);
        mEditor.commit();
    }

    public boolean isNewVersion() {
        final String started = String.format("isFirstIn%s", PhoneInfoUtil.getVersionName());
        return mSharedPreferences.getBoolean(started, true);
    }


}
