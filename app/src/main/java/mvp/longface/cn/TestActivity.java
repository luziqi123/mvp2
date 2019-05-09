package mvp.longface.cn;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import cn.longface.vrp.Registry;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Registry.getInstance().registerView(this);
        Log.i("RRRRRRR", "TextCreate");
        new Thread(new Runnable() {
            @Override
            public void run() {
                TestActivity testActivity = TestActivity.this;
                SystemClock.sleep(10000);
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Registry.getInstance().unregisterView(this);
        Log.i("RRRRRRR", "TextDestroy");
    }
}
