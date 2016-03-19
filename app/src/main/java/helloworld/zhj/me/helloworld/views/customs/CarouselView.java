package helloworld.zhj.me.helloworld.views.customs;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import helloworld.zhj.me.helloworld.R;
import helloworld.zhj.me.helloworld.entities.Carousel;
import helloworld.zhj.me.helloworld.utils.DisplayUtil;

/**
 * Created by zhj-plusplus on 3/13/16.
 */
public class CarouselView extends LinearLayout {

    private Context mContext;
    private ViewPager mVpCarousel;
    private RadioGroup mRgIndicator;

    private List<Carousel> mCarousels;
    private boolean mIsAutoScroll = true;
    private int mWhat;
    private int mLength;

    public CarouselView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.view_carousel, this);
    }

    public void addData(List<Carousel> carousels) {
        if (carousels == null || carousels.size() == 0) {
            return;
        }
        mCarousels = carousels;
        mLength = mCarousels.size();
        initView();
    }

    private void initView() {
        initIndicator();
        initCarousel();
    }

    private void initIndicator() {
        mRgIndicator = (RadioGroup) findViewById(R.id.rg_indicator);
        if (mLength < 2) {
            mRgIndicator.setVisibility(View.GONE);
        } else {
            mRgIndicator.setVisibility(View.VISIBLE);
        }
        for (int i = 0; i < mLength; i++) {
            RadioButton rbIndicator = new RadioButton(mContext);
            rbIndicator.setButtonDrawable(android.R.color.transparent);
            RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(DisplayUtil.dip2px(mContext, 10),
                    DisplayUtil.dip2px(mContext, 10));
            params.setMargins(DisplayUtil.dip2px(mContext, 10), 0, 0, 0);
            rbIndicator.setLayoutParams(new RadioGroup.LayoutParams(DisplayUtil.dip2px(mContext, 8), DisplayUtil.dip2px(
                    mContext, 8)));
            rbIndicator.setBackgroundResource(R.drawable.selector_guide_indicator);
            mRgIndicator.addView(rbIndicator, params);
            if (i == 0) {
                rbIndicator.setChecked(true);
            }
        }
    }

    private void initCarousel() {
        mVpCarousel = (ViewPager) findViewById(R.id.vp_carousel);
        List<View> carouselPics = new ArrayList<View>();
        Carousel carousel = null;
        if(mLength == 1){
            for (int i = 0; i < mLength; i++) {
                carousel = mCarousels.get(i);
                // 显示图片设置
                ImageView imageView = intCarouselView(i, carousel);
                carouselPics.add(imageView);
            }
        }else if(mLength > 1){
            int length = mLength + 2;
            for (int i = 0; i < length; i++) {
                /**
                 * 实现无限循环：2'-0-1-2-0'
                 * 滑动的顺序：
                 * 进入页面显示0图片，向右滑动到0时，将0页设置为0'，则可以继续向右滑动；
                 * 同理当向左滑动到2时，将2页设置为2'，则可以继续向左滑动。
                 */
                if (i == 0) {
                    carousel = mCarousels.get(mLength - 1);
                } else if (i == (length - 1)) {
                    carousel = mCarousels.get(0);
                } else {
                    carousel = mCarousels.get(i - 1);
                }

                // 显示图片设置
                ImageView imageView = intCarouselView(i, carousel);
                carouselPics.add(imageView);
            }
        }

        mVpCarousel.setAdapter(new CarouselPagerAdapter(carouselPics));
        mVpCarousel.setOnPageChangeListener(new CarouselOnPageChangeListener());
        mVpCarousel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        mIsAutoScroll = false;
                        break;
                    case MotionEvent.ACTION_UP:
                        mIsAutoScroll = true;
                        break;
                    default:
                        mIsAutoScroll = true;
                        break;
                }
                return false;
            }
        });
        // 自动滚动播放设置，两个或两个以上数据才设置
        if (mLength > 1) {
            mVpCarousel.setCurrentItem(1);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if (mIsAutoScroll) {
                            changeWhat();
                            viewHandler.sendEmptyMessage(mWhat);
                        }
                    }
                }
            }).start();
        }

    }

    private final Handler viewHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mVpCarousel.setCurrentItem(msg.what);
            super.handleMessage(msg);
        }
    };

    private void changeWhat() {
        if (mLength == 1) {
            mWhat = (mWhat + 1) % mLength;
        } else if (mLength > 1) {
            mWhat = (mWhat + 1) % (mLength + 2);
        }
        try {
            Thread.sleep(3000);// 3秒跳转一次
        } catch (InterruptedException e) {

        }
    }

    private ImageView intCarouselView(int index, final Carousel carousel) {
        ImageView carouselView = new ImageView(getContext());
        carouselView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        carouselView.setTag(index);
        Glide.with(mContext).load(carousel.getImageUrl()).into(carouselView);
        return carouselView;
    }

    private class CarouselPagerAdapter extends PagerAdapter {

        private List<View> data;

        public CarouselPagerAdapter(List<View> data) {
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

    private class CarouselOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int index) {
            int pageIndex = index;
            if(mLength > 1){
                if (index == 0) {
                    // 当视图在第一个时，将页面号设置为图片的最后一张。
                    pageIndex = mLength;
                } else if (index == mLength + 1) {
                    // 当视图在最后一个时，将页面号设置为图片的第一张。
                    pageIndex = 1;
                }
                if (index != pageIndex) {
                    mVpCarousel.setCurrentItem(pageIndex, false);
                }
                // 切换游标，两个或两个以上爆款产品才显示游标
                RadioButton radioButton = (RadioButton) mRgIndicator.getChildAt(pageIndex - 1);
                radioButton.setChecked(true);
            }
            mWhat = pageIndex;
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }


}
