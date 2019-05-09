package cn.longface.vrp;

import android.app.Activity;
import android.content.Context;

/**
 * 注册表
 * 管理一个ViewEntry链表 , 管理一个Presenter
 *
 */
public class Registry {

    RegistryView mViewRegistry;
    RegistryLru mLruRegistry;

    protected Registry() {
        mViewRegistry = new RegistryView();
        mLruRegistry = new RegistryLru();
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

    /* ---------------------------------- RegistryView ----------------------------------*/

    public void registerView(Object view) {
        mViewRegistry.registerView(view);
    }

    public void unregisterView(Object view) {
        mViewRegistry.unregisterView(view);
    }

    /**
     * 一般情况下使用这个方法就可以了
     * 但在需要频繁操作的地方应该直接使用getView(Class , InstanceCallback) , 他会减少更多的内存分配.
     * @param callback
     * @param <T>
     */
    public <T> void getView(InstanceCallback<T> callback) {
        getView(callback.getClazz() , callback);
    }
    public <T> void getView(Class clazz , InstanceCallback<T> callback) {
        mViewRegistry.getView(clazz , callback);
    }

    public Activity getActivity() {
        return mViewRegistry.getActivity();
    }

    public Context getContext() {
        return mViewRegistry.getContext();
    }


    /* ---------------------------------- RegistryLru ----------------------------------*/


}
