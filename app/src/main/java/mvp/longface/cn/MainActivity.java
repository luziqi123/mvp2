package mvp.longface.cn;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.view.View;
import android.widget.TextView;

import cn.longface.vrp.Registry;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Registry.getInstance().registerView(this);
        textView = findViewById(R.id.text);
    }

    public void aaa(View view) {
        Presenter p = new Presenter();
        p.getText(this);
    }


    public void bbb(View view) {
        Registry.getInstance().unregisterView(this);
    }


    TextView textView;
    int i;
    public void updateUI(Runtime r, ActivityManager.MemoryInfo info) {
//        double M = 1024 * 1024;
//        textView.setText(++i + "");
//        textView.setText(
//                ++i + "\n" +
//                "最大可用内存:" + r.maxMemory() / M + "M" + "\n" +
//                        "当前已使用内存:" + (r.totalMemory() - r.freeMemory()) / M + "M" + "\n" +
//                        "当前可用内存:" + r.totalMemory() / M + "M" + "\n" +
//                        "当前空闲内存:" + r.freeMemory() / M + "M" + "\n");
    }



    public static String getAvailMemory(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
// mi.availMem; 当前系统的可用内存
        return Formatter.formatFileSize(context, mi.availMem);// 将获取的内存大小规格化
    }

}
