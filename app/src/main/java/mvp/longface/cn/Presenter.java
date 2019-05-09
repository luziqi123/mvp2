package mvp.longface.cn;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import cn.longface.vrp.InstanceCallback;
import cn.longface.vrp.Registry;

public class Presenter {

    boolean isRun = true;
    public void getText(Context context) {
        final Runtime r = Runtime.getRuntime();

        ActivityManager activityManager = (ActivityManager) context.getSystemService(Activity.ACTIVITY_SERVICE);
        final ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(info);

        final InstanceCallback<MainActivity> callback = new InstanceCallback<MainActivity>() {
            @Override
            public void onHasInstance(MainActivity instance) {
                 instance.updateUI(r , info);
            }

            @Override
            public void onNotInstance(String className) {
                super.onNotInstance(className);
                Log.i("RRRRRRR", className + "获取失败");
                isRun = false;
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRun) {
                    Registry.getInstance().getView(MainActivity.class , callback);
                    SystemClock.sleep(10);
                }
            }
        }).start();
    }
}
