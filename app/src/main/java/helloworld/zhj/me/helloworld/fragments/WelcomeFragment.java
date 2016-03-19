package helloworld.zhj.me.helloworld.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import helloworld.zhj.me.helloworld.R;
import helloworld.zhj.me.helloworld.activities.MainActivity;

/**
 * 欢迎页面
 * Created by zhj-plusplus on 3/12/16.
 */
public class WelcomeFragment extends BaseFragment {

    private static final int SPLASH_TIME = 1000;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        onSplash();
    }

    private void onSplash() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity.launch(getActivity());
                getActivity().finish();
                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        }, SPLASH_TIME);
    }
}
