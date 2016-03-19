package helloworld.zhj.me.helloworld.fragments;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import helloworld.zhj.me.helloworld.R;
import helloworld.zhj.me.helloworld.entities.Carousel;
import helloworld.zhj.me.helloworld.views.customs.CarouselView;

/**
 * Created by zhj-plusplus on 3/12/16.
 */
public class HomeFragment extends BaseFragment {

    @Bind(R.id.cv_advert)
    CarouselView mCvAdvert;

    private static final String[] IMAGE_URLS = {
//            "http://7xkj62.com1.z0.glb.clouddn.com/carousel/10001/10001_android_2.png",
//            "http://7xkj62.com1.z0.glb.clouddn.com/carousel/10008/10008_android.png",
//            "http://7xkj62.com1.z0.glb.clouddn.com/carousel/10009/10009_android.jpg",
//            "http://7xkj62.com1.z0.glb.clouddn.com/carousel/10010/10010_android.1.jpg",
//            "http://7xkj62.com1.z0.glb.clouddn.com/carousel/10011/10011_android.jpg",
//            "http://7xkj62.com1.z0.glb.clouddn.com/carousel/10012/10012_android.png",
//            "http://7xkj62.com1.z0.glb.clouddn.com/carousel/10013/10013_android.jpg"
    };

    private List<Carousel> mCarousels;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        mCarousels = new ArrayList<Carousel>();
        for (String url : IMAGE_URLS) {
            Carousel carousel = new Carousel();
            carousel.setImageUrl(url);
            mCarousels.add(carousel);
        }
        mCvAdvert.addData(mCarousels);
        return view;
    }
}
