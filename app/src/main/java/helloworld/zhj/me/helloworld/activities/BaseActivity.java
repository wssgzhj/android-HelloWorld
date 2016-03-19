package helloworld.zhj.me.helloworld.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import helloworld.zhj.me.helloworld.R;

/**
 * Created by zhj-plusplus on 3/6/16.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    @Override
    public void startActivity(Intent intent, Bundle options) {
        super.startActivity(intent, options);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }
}
