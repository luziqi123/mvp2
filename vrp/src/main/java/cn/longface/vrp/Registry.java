package cn.longface.vrp;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Registry {


    // View链表
    LinkedHashMap<Class, ViewEntry> mViewList;
    // Presenter集合

    // 用于更新UI的Handler
    Handler mUiHandler;

    // 命令实例的对象池 , 在需要回调实例的地方会用到
    LinkedList<Command> mCommands;



    protected Registry() {
        mViewList = new LinkedHashMap<>(12);
        mUiHandler = new UiHandler(Looper.getMainLooper());
        mCommands = new LinkedList<>();
    }
    private volatile static Registry instance;
    public static Registry getInstance() {
        if (instance != null) {
            return instance;
        }
        if (instance == null) {
            synchronized (Registry.class) {
                if (instance == null) {
                    instance = new Registry();
                }
            }
        }
        return instance;
    }

    /**
     * 需要在View确定显示的时候注册
     * @param view
     */
    public void registerView(Object view) {
        mViewList.put(view.getClass(), createViewEntry(view));
    }

    /**
     * 当View不再使用的时候取消注册
     * @param view
     */
    public void unregisterView(Object view) {
        mViewList.remove(view.getClass());
    }

    public void getView(Class viewClass , InstanceCallback callback) {
        ViewEntry viewEntry = mViewList.get(viewClass);
        if (viewEntry != null) {
            Message message = mUiHandler.obtainMessage();
            Command command = new Command();
            command.instance = viewEntry.view;
            command.callback = callback;
            message.obj = command;
            mUiHandler.sendMessage(message);
        }
    }

    public void getPresenter(InstanceCallback callback) {
        // TODO 获取实例
    }

    private ViewEntry createViewEntry(Object view) {
        return new ViewEntry(view);
    }

    class UiHandler extends Handler {

        public UiHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Command command = (Command) msg.obj;
            command.callback.get(command.instance);
        }
    }


}
