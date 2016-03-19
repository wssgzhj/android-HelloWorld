package helloworld.zhj.me.helloworld.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import helloworld.zhj.me.helloworld.MyApplication;

/**
 * Created by zhj-plusplus on 3/12/16.
 */
public class PhoneInfoUtil {

    /**
     * 获取VersionName
     *
     * @return VersionName
     */
    public static String getVersionName() {
        String version = null;
        try {
            PackageManager packageManager = MyApplication.getContext()
                    .getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    MyApplication.getContext().getPackageName(), 0);
            version = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {

        }
        return version;
    }
}
