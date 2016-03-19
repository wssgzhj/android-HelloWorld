package helloworld.zhj.me.helloworld.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import helloworld.zhj.me.helloworld.R;
import helloworld.zhj.me.helloworld.activities.OrderDetailActivity;
import helloworld.zhj.me.helloworld.views.TipManager;
import helloworld.zhj.me.helloworld.views.adapters.OrderAdapter;
import helloworld.zhj.me.helloworld.views.customs.DividerItemDecoration;

/**
 * Created by zhj-plusplus on 3/12/16.
 */
public class OrderFragment extends BaseFragment {

    private static final int[] ORDERS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

    @Bind(R.id.srl_orders)
    SwipeRefreshLayout mSrlOrders;
    @Bind(R.id.rv_orders)
    RecyclerView mRvOrders;

    private OrderAdapter mOrderAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }


    private void initView() {
        mSrlOrders.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSrlOrders.setRefreshing(false);
                        new TipManager(getActivity()).showToast("刷新成功");
                    }
                }, 3000);
            }
        });

        mRvOrders.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mRvOrders.setLayoutManager(new LinearLayoutManager(getActivity()));
        mOrderAdapter = new OrderAdapter(getActivity(), ORDERS);
        mOrderAdapter.setOnItemClickListener(new OrderAdapter.OrderItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                OrderDetailActivity.launch(getActivity());
            }

            @Override
            public void onItemLongClick(View view, int position) {
            }
        });
        mRvOrders.setAdapter(mOrderAdapter);
        mRvOrders.setItemAnimator(new DefaultItemAnimator());
    }
}
