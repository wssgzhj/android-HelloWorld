package helloworld.zhj.me.helloworld.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import helloworld.zhj.me.helloworld.R;
import helloworld.zhj.me.helloworld.fragments.HomeFragment;
import helloworld.zhj.me.helloworld.fragments.MeFragment;
import helloworld.zhj.me.helloworld.fragments.OrderFragment;
import helloworld.zhj.me.helloworld.views.TipManager;
import helloworld.zhj.me.helloworld.views.customs.TabBarView;

/**
 * Created by zhj-plusplus on 3/6/16.
 */
public class MainActivity extends BaseActivity implements TabBarView.OnTabSelectedListener {

    @Bind(R.id.vp_main)
    ViewPager mVpMain;
    @Bind(R.id.tbv_tab_bar)
    TabBarView mTbvTabBar;

    private HomeFragment mHomeFragment;
    private OrderFragment mOrderFragment;
    private MeFragment mMeFragment;

    private int mCurrentTabIndex;
    private long mLastKeyDownTime;

    public static void launch(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mCurrentTabIndex = getIntent().getIntExtra("tabIndex", TabBarView.INDEX_HOME);
        initView();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        int tabIndex = getIntent().getIntExtra("tabIndex", -1);
        if (tabIndex != -1) {
            mTbvTabBar.setCurrentSelected(tabIndex);
        }
    }

    private void initView() {
        mTbvTabBar.setCurrentSelected(mCurrentTabIndex);
        mTbvTabBar.setOnTabSelectedListener(this);

        ArrayList<Fragment> fragments = new ArrayList();
        mHomeFragment = new HomeFragment();
        fragments.add(mHomeFragment);
        mOrderFragment = new OrderFragment();
        fragments.add(mOrderFragment);
        mMeFragment = new MeFragment();
        fragments.add(mMeFragment);
        mVpMain.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), fragments));
        mVpMain.setOnPageChangeListener(new MainPagerListener());
        mVpMain.setOffscreenPageLimit(fragments.size());
    }

    @Override
    public void onTabSelected(int index) {
        processPageChange(index);
    }

    private void processPageChange(int index) {
        if (mCurrentTabIndex == index) {
            return;
        }
        if (index != mVpMain.getCurrentItem()) {
            mVpMain.setCurrentItem(index, true);
        }
        mCurrentTabIndex = index;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mLastKeyDownTime) > 2000) {
                new TipManager(this).showToast("再按一次退出程序");
                mLastKeyDownTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class MainPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> mFragments;

        public MainPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
            super(fm);
            this.mFragments = fragments;
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public Fragment getItem(int arg0) {
            return mFragments.get(arg0);
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

    }

    public class MainPagerListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int index) {
            mTbvTabBar.setCurrentSelected(index);
        }

    }

}
