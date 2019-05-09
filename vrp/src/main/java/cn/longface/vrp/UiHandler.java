package cn.longface.vrp;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.SoftReference;

class UiHandler extends Handler {

    CommandPool mCommandPool = new CommandPool();

    UiHandler() {
        super(Looper.getMainLooper());
    }

    void sendCallback(Object view , InstanceCallback callback) {
        SoftReference<Command> sf = mCommandPool.get();
        sf.get().instance = view;
        sf.get().callback = callback;
        Message message = obtainMessage();
        message.obj = sf;
        message.what = sf.get().getClass().hashCode();
        sendMessage(message);
    }

    void removeCallback(Class clazz) {
        removeMessages(clazz.hashCode());
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        SoftReference<Command> sf = (SoftReference<Command>) msg.obj;
        if (Registry.getInstance().mViewRegistry.hasView(sf.get().callback.getClazz())) {
            sf.get().callback.onHasInstance(sf.get().instance);
        } else {
            sf.get().callback.onNotInstance(sf.get().callback.getClazz().getName());
        }
        mCommandPool.putBack(sf);
    }
}
