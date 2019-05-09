# mvp2
一个基于MVP 的架构工具.

好处:
不用关心线程问题.
不用关心生命周期问题.
多个Presenter交集问题.
接口繁多问题.

我们简称为VRP (View-Registry-Presenter) , 是在View和Presenter层中间新增了一个Registry注册表.

# 备忘
两个相同Class的实例注册 , 场景: 咸鱼打开一个商品详情A , 再在详情推荐里打开另一个商品详情B ,
这时候就需要两个相同类型的Activity入栈了 , 并且逻辑都一样.

Command需要一个对象池

RegistryView需要对Context进行管理


# 工作原理

我们在使用View调用presenter方法的时候 , 可以直接通过一个注册表来获取对应的实例 , 然后调用其具体的方法.


