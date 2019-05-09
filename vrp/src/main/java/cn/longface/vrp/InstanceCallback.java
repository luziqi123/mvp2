package cn.longface.vrp;

import java.lang.reflect.ParameterizedType;

/**
 * 获取实例的回调
 * @param <I>
 */
public abstract class InstanceCallback<I> {

    /**
     * 在获取到实例的时候会调用该方法.
     * instance不会为空.
     * 如果是获取ViewEntry这个回调在UI线程中执行.
     * @param instance
     */
    public abstract void onHasInstance(I instance);

    /**
     * 当没有对应实例的时候回调用该方法.
     * 只有在获取ViewEntry的时候才会调用该方法, 否则都会重新创建一个对应的对象.
     * 该方法不一定在主线程执行
     * @param className
     */
    public void onNotInstance(String className) {}

    /**
     * 获取泛型的class
     * @return
     */
    public Class<I> getClazz() {
        return (Class<I>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
