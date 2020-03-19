package com.cyh.project.thread;

class CreateThreadDemo1 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            System.out.println("run,i:"+i);
        }
    }
}

/**
 *  @author: chenyinghui
 *  @Date: 2020/1/27 16:13
 *  @Description: 多线程
 */
public class ThreadDemo1{

    /**
     * 创建线程的方式：
     * 1.继承Thread类重写run方法
     * 2.实现runable接口
     * 3.匿名内部内方式
     * 4.callable
     * 5.使用线程池创建线程
     */
    public static void main(String[] args) {
        CreateThreadDemo1 createThreadDemo1 = new CreateThreadDemo1();
        createThreadDemo1.start();
        for (int i = 0; i < 30; i++) {
            System.out.println("main,i:"+i);
        }
    }
}
