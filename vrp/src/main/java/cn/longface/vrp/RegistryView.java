package cn.longface.vrp;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;

/**
 * Context的管理
 */
class RegistryView {


    // View链表
    LinkedHashMap<Class, ViewEntry> mViewList;

    // 用于更新UI的Handler
    UiHandler mUiHandler;

    // 用于记录最后一个注册的Activity
    Activity activity;

    RegistryView() {
        mViewList = new LinkedHashMap<>(12);
        mUiHandler = new UiHandler();
    }

    /**
     * 需要在View确定显示的时候注册
     *
     * @param view
     */
    void registerView(Object view) {
        mViewList.put(view.getClass(), new ViewEntry(view));
        updateActivity(view);
    }

    /**
     * 当View不使用的时候取消注册
     *
     * @param view
     */
    void unregisterView(Object view) {
        mUiHandler.removeCallback(view.getClass());
        mViewList.remove(view.getClass());
        updateActivity(null);
    }

    /**
     * 获取指定的View实例
     *
     */
    <T> void getView(Class clazz , InstanceCallback<T> callback) {
        ViewEntry viewEntry = mViewList.get(clazz);
        if (viewEntry != null) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                callback.onHasInstance((T) viewEntry.view);
            } else {
                mUiHandler.sendCallback(viewEntry.view, callback);
            }
        } else {
            callback.onNotInstance(clazz.getName());
        }
    }

    /**
     * 应在使用后立即释放引用.
     *
     * @return 返回最后一个注册的Activity
     */
    Activity getActivity() {
        return activity;
    }

    /**
     * @return 返回AppContext
     */
    Context getContext() {
        if (activity == null) {
            return null;
        }
        return activity.getApplicationContext();
    }

    /**
     * 检查ViewEntry是否还存在
     *
     * @param clazz
     * @return
     */
    public boolean hasView(Class clazz) {
        return mViewList.get(clazz) != null;
    }

    /**
     * 更新activity为最后一个注册的Activity
     *
     * @param view 在解除注册的时候传null , activity会指向之前的一个Activity.
     */
    private void updateActivity(Object view) {
        if (view == null) {
            activity = null;
            ListIterator<Map.Entry<Class, ViewEntry>> i =
                    new ArrayList<>(mViewList.entrySet()).listIterator(mViewList.size());
            while (i.hasPrevious()) {
                Map.Entry<Class, ViewEntry> entry = i.previous();
                ViewEntry viewEntry = entry.getValue();
                if (viewEntry.view instanceof Activity) {
                    activity = (Activity) viewEntry.view;
                }
            }
        } else {
            if (view instanceof Activity) {
                activity = (Activity) view;
            }
        }

    }

}
