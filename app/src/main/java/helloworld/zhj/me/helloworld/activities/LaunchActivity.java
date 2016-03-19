package helloworld.zhj.me.helloworld.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import helloworld.zhj.me.helloworld.R;
import helloworld.zhj.me.helloworld.fragments.GuideFragment;
import helloworld.zhj.me.helloworld.fragments.WelcomeFragment;
import helloworld.zhj.me.helloworld.storage.SharedPreferencesManager;

/**
 * 启动页面
 */
public class LaunchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_fragment_container);
        initFragment();
    }

    private void initFragment() {
        boolean isNewVersion = SharedPreferencesManager.getInstance().isNewVersion();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment;
        if (isNewVersion) {
            fragment = new GuideFragment();
            SharedPreferencesManager.getInstance().setNewVersion(false);
        } else {
            fragment = new WelcomeFragment();
        }
        ft.replace(R.id.layout_fragment_container, fragment, null);
        ft.commit();
    }

}
