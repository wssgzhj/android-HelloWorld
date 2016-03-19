package helloworld.zhj.me.helloworld.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import helloworld.zhj.me.helloworld.R;
import helloworld.zhj.me.helloworld.activities.MainActivity;
import helloworld.zhj.me.helloworld.utils.DisplayUtil;

/**
 * 引导页面
 * Created by zhj-plusplus on 3/12/16.
 */
public class GuideFragment extends BaseFragment {

    private static final int[] GUIDE_PICTURES = {
            R.mipmap.pic_guide1,
            R.mipmap.pic_guide2,
            R.mipmap.pic_guide3
    };

    @Bind(R.id.vp_guide)
    ViewPager mVpGuide;
    @Bind(R.id.rg_indicator)
    RadioGroup mRgIndicator;

    private List<View> mGuideViews;
    private PagerAdapter mAdapter;
    private boolean mIsScrolled;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        mGuideViews = new ArrayList();
        for (int i = 0; i < GUIDE_PICTURES.length; i++) {
            // 创建引导页
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_guide_page, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_guide);
            imageView.setImageResource(GUIDE_PICTURES[i]);
            Button btnEnter = (Button) view.findViewById(R.id.btn_enter);
            if (i == GUIDE_PICTURES.length - 1) {
                btnEnter.setVisibility(View.VISIBLE);
                btnEnter.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        startMainActivity();
                    }
                });
            } else {
                btnEnter.setVisibility(View.GONE);
            }
            mGuideViews.add(view);

            // 创建Indicator
            RadioButton rbIndicator = new RadioButton(getActivity());
            rbIndicator.setButtonDrawable(android.R.color.transparent);
            RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(DisplayUtil.dip2px(getActivity(), 10),
                    DisplayUtil.dip2px(getActivity(), 10));
            params.setMargins(DisplayUtil.dip2px(getActivity(), 10), 0, 0, 0);
            rbIndicator.setLayoutParams(new RadioGroup.LayoutParams(DisplayUtil.dip2px(getActivity(), 8), DisplayUtil.dip2px(
                    getActivity(), 8)));
            rbIndicator.setBackgroundResource(R.drawable.selector_guide_indicator);
            mRgIndicator.addView(rbIndicator, params);
            if (i == 0) {
                rbIndicator.setChecked(true);
            }
        }

        mAdapter = new GuidePagerAdapter(mGuideViews);
        mVpGuide.setAdapter(mAdapter);
        mVpGuide.setOnPageChangeListener(new GuideOnPageChangeListener());
    }

    private void startMainActivity() {
        mGuideViews.clear();
        MainActivity.launch(getActivity());
        getActivity().finish();
    }

    private class GuidePagerAdapter extends PagerAdapter {

        private List<View> data;

        public GuidePagerAdapter(List<View> data) {
            super();
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(data.get(position));
            return data.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(data.get(position));
        }

    }

    private class GuideOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int index) {
            RadioButton radioButton = (RadioButton) mRgIndicator.getChildAt(index);
            radioButton.setChecked(true);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // 最后页面继续滑动实现跳转
            switch (arg0) {
                case ViewPager.SCROLL_STATE_DRAGGING:
                    mIsScrolled = false;
                    break;
                case ViewPager.SCROLL_STATE_SETTLING:
                    mIsScrolled = true;
                    break;
                case ViewPager.SCROLL_STATE_IDLE:
                    if (mVpGuide.getCurrentItem() == mVpGuide.getAdapter().getCount() - 1 && !mIsScrolled) {
                        startMainActivity();
                    }
                    mIsScrolled = true;
                    break;
            }
        }
    }
}
