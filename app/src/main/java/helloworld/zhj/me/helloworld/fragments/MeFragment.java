package helloworld.zhj.me.helloworld.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import helloworld.zhj.me.helloworld.R;

/**
 * Created by zhj-plusplus on 3/12/16.
 */
public class MeFragment extends BaseFragment {

    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_me, container, false);
        return mView;
    }
}
