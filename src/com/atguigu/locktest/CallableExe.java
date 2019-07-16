/**
 * 当需要有返回值时用callable创建线程
 * 1.创建线程资源
 * 2.获取未来任务
 * 3.创建线程
 * 4.在主线程执行时，未来任务可执行线程中的耗时任务
 * 5.可在最后使用FutureTask的get（）方法获取耗时任务的结果值
 */
package com.atguigu.locktest;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

//创建资源类
class Source implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		Thread.sleep(400);
		System.out.println(Thread.currentThread().getName() + "---------come in call");
		return 200;
	}
	
}

public class CallableExe{
	public static void main(String[] args) {
		//获取线程资源
		Source source = new Source();
		//获取未来任务
		FutureTask<Integer> futureTask = new FutureTask<>(source);
		FutureTask<Integer> futureTask2 = new FutureTask<>(source);
		
		//创建线程
		new Thread(futureTask, "AA").start();
		new Thread(futureTask2, "BB").start();
		
		System.out.println(Thread.currentThread().getName() + "------------main");
		//获取任务返回值
		Integer integer;
		try {
			integer = futureTask.get();
			System.out.println("result:" + integer);
			Integer integer2 = futureTask2.get();
			System.out.println("result:" + integer2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}