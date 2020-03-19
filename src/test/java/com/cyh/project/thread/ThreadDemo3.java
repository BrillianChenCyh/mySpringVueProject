package com.cyh.project.thread;

/**
 *  @author: chenyinghui
 *  @Date: 2020/1/27 16:13
 *  @Description: 多线程
 */
public class ThreadDemo3{

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int j = 0; j < 30; j++) {
					System.out.println("子线程,i:"+j);
				}
			}
		});
        thread.start();
//        try {
//        	//主线程让子线程先执行
//			thread.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
        for (int i = 0; i < 30; i++) {
        	System.out.println("主线程,i:"+i);
		}
    }
}
