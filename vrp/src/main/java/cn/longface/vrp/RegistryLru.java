package cn.longface.vrp;


import android.support.v4.util.LruCache;

class RegistryLru {

    LruCache mLruCache;

    public RegistryLru() {
        mLruCache = new LruCache((int) (Runtime.getRuntime().maxMemory() / 1024 * 0.02f));
    }

    public <T> void run(InstanceCallback<T> callback) {

    }

    public <T> void runOnThread(InstanceCallback<T> callback) {

    }

}
