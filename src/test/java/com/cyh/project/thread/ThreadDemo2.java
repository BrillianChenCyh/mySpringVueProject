package com.cyh.project.thread;

class CreateThreadDemo2 implements Runnable{

	@Override
	public void run() {
		for (int i = 0; i < 30; i++) {
			System.out.println("子线程,i:"+i);
		}

	}

}

/**
 *  @author: chenyinghui
 *  @Date: 2020/1/27 16:13
 *  @Description: 多线程
 */
public class ThreadDemo2{

    /**
     * 接口方式比继承方式实现多线程好些，因为开发都是使用接口编程
     */
    public static void main(String[] args) {
        CreateThreadDemo2 demo2 = new CreateThreadDemo2();
        Thread thread = new Thread(demo2);
        thread.start();
        for (int i = 0; i < 30; i++) {
            System.out.println("主线程,i:"+i);
        }
    }
}
