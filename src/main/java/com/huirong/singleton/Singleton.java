package com.huirong.singleton;

/**
 * Created by huirong on 17-5-21.
 */
public class Singleton {

    /**
     * 单线程解决方案
     */
//    private static Singleton instance = null;
//    public static Singleton getInstance(){
//        if (instance == null){
//            instance = new Singleton();
//        }
//        return instance;
//    }

    /**
     * 多线程下加锁解决方案，双重验证
     * 需要保证对象的可见性
     */
//    private static volatile Singleton instance;
//    public static Singleton getInstance(){
//        if (instance == null){
//            synchronized (Singleton.class){
//                if (instance == null){
//                    instance = new Singleton();
//                }
//            }
//        }
//        return instance;
//    }

    /**
     * 对象被过早创建
     */
//    private static Singleton instance = new Singleton();
//
//    private Singleton() {
//    }
//    public static Singleton getInstance(){
//        return instance;
//    }

    /**
     * 需要时创建对象
     */
    private static class SingletonHandler{
        private static Singleton instance = new Singleton();
    }
    private Singleton(){
    }
    public static Singleton getInstance(){
        return SingletonHandler.instance;
    }
}
