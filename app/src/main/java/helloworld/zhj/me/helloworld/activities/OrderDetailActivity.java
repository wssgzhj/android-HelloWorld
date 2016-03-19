package helloworld.zhj.me.helloworld.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import helloworld.zhj.me.helloworld.R;

/**
 * Created by zhj-plusplus on 3/13/16.
 */
public class OrderDetailActivity extends BaseActivity {

    public static void launch(Context context){
        Intent intent = new Intent(context, OrderDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
    }
}
