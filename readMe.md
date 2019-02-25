android 源码层 观察者被观察者的初级使用
    
    1. 被观察者Observable 添加观察者 Observer
    2. observable更新数据需要调用 setChange() 方法 使changed值为true确保notifyObserver生效
     /* 源码如下
     if (!hasChanged())
                     return;
                 arrLocal = obs.toArray();
                 clearChanged();
     */
     因此需要在传值之前调用setChangeed()方法
     3. observer 通过upgrade响应observable的变化
     
     
关于约束布局的用法 

    ==> https://www.jianshu.com/p/a74557359882
    
    
关于RxJava

    @DES 拓展的观察者订阅者模式 
    @RES https://gank.io/post/560e15be2dca930e00da1083
    
    @CONCEPT Observable Observer subscribe
        事件回调 除了 onEvent (upgrade()) 增加了onComplete() onError()
    
    @TIPS 由于订阅关系的存在 RxJava可能会引起内存泄漏
    
    @Content 一些关键字 just from 
        Scheduler 调度器 线程控制
        
        
    observable 被观察者需要调用onComplete()才能通知到观察者observer （类似notifObserver()）2.0 版本似乎不会影响onnext方法 
    但 onComplete() 会受到影响  同样地 Oncomplete() 方法后的onNext() 不会被调用   类比系统观察者模式中的 hasChange()方法
         