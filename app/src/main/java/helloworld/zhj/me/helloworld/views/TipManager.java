package helloworld.zhj.me.helloworld.views;

import android.content.Context;
import android.widget.Toast;

import helloworld.zhj.me.helloworld.MyApplication;

/**
 * 提示管理类
 * Created by zhj-plusplus on 3/13/16.
 */
public class TipManager {

    private Context mContext;

    public TipManager() {
        mContext = MyApplication.getContext();
    }

    public TipManager(Context context) {
        mContext = context;
    }

    public void showToast(CharSequence message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }
}
