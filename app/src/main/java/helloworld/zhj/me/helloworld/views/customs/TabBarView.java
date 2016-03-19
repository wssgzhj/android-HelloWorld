package helloworld.zhj.me.helloworld.views.customs;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import helloworld.zhj.me.helloworld.R;

/**
 * 主界面底部导航栏
 */
public class TabBarView extends LinearLayout implements OnClickListener {

    public static final int INDEX_HOME = 0;
    public static final int INDEX_ORDER = 1;
    public static final int INDEX_ME = 2;

    private RelativeLayout mRlHome;
    private RelativeLayout mRlOrder;
    private RelativeLayout mRlMe;

    private OnTabSelectedListener mListener;

    public TabBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        LayoutInflater.from(context).inflate(R.layout.view_tab_bar, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    private void initView() {
        mRlHome = (RelativeLayout) findViewById(R.id.rl_home);
        mRlHome.setOnClickListener(this);
        mRlOrder = (RelativeLayout) findViewById(R.id.rl_order);
        mRlOrder.setOnClickListener(this);
        mRlMe = (RelativeLayout) findViewById(R.id.rl_me);
        mRlMe.setOnClickListener(this);
    }

    public interface OnTabSelectedListener {
        void onTabSelected(int index);
    }

    public void setOnTabSelectedListener(OnTabSelectedListener listener) {
        mListener = listener;
    }

    @Override
    public void onClick(View v) {
        resetViewSelected(v);
        int index = INDEX_HOME;
        switch (v.getId()) {
            case R.id.rl_home:
                index = INDEX_HOME;
                break;

            case R.id.rl_order:
                index = INDEX_ORDER;
                break;

            case R.id.rl_me:
                index = INDEX_ME;
                break;
        }
        if (mListener != null) {
            mListener.onTabSelected(index);
        }
    }

    private void resetViewSelected(View v) {
        mRlHome.setSelected(false);
        mRlOrder.setSelected(false);
        mRlMe.setSelected(false);
        v.setSelected(true);
    }

    public void setCurrentSelected(int index) {
        switch (index) {
            case INDEX_HOME:
                if (mRlHome.isEnabled()) {
                    mRlHome.performClick();
                }
                break;

            case INDEX_ORDER:
                if (mRlOrder.isEnabled()) {
                    mRlOrder.performClick();
                }
                break;

            case INDEX_ME:
                if (mRlMe.isEnabled()) {
                    mRlMe.performClick();
                }
                break;
        }
    }
}
