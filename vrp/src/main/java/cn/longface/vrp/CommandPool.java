package cn.longface.vrp;

import java.lang.ref.SoftReference;
import java.util.LinkedList;

public class CommandPool {

    LinkedList<SoftReference<Command>> mCommandPool;

    public CommandPool() {
        mCommandPool = new LinkedList<>();
    }

    public SoftReference<Command> get() {
        SoftReference<Command> softReference = mCommandPool.pop();
        if (softReference == null) {
            return new SoftReference<>(new Command());
        }
        return softReference;
    }

    public void putBack(SoftReference<Command> command) {
        mCommandPool.add(command);
    }
}
