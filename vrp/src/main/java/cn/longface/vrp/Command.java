package cn.longface.vrp;

/**
 * 这是一个命令
 * 在获取到实例的时候会发送这个命令到handler
 */
class Command {
    Object instance;
    InstanceCallback callback;
}
