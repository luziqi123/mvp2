package cn.longface.vrp;

/**
 * 在注册的时候会生成一个ViewEntry,并将其添加至View链表
 */
public class ViewEntry{

    Object view;

    public ViewEntry(Object view) {
        this.view = view;
    }
}
