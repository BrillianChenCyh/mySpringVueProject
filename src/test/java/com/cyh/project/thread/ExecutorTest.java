package com.cyh.project.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 使用jdk1.5出现的多线程框架:Executor
 * 模拟爬网页
 * @author 25656
 */
public class ExecutorTest {

	private static Integer pages = 1; // 网页数

	private static boolean exeFlag = true; // 执行标识

	public static void main(String[] args) {
		// 创建ExecutorService 连接池默认连接10个
		ExecutorService executorService = Executors.newFixedThreadPool(10);

		while (exeFlag) {
			if (pages <= 100) {
				executorService.execute(new Runnable() {

					@Override
					public void run() {
						System.out.println("爬取了第" + pages + "网页...");
						pages++;
					}
				});
			} else {
				if (((ThreadPoolExecutor) executorService).getActiveCount() == 0) { // 活动线程个数是0
					executorService.shutdown(); // 结束所有线程
					exeFlag = false;
					System.out.println("爬虫任务已经完成");
				}
			}

			try {
				Thread.sleep(100); // 线程休息0.1秒
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
