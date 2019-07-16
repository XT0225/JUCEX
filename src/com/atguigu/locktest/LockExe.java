package com.atguigu.locktest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @Description:卖票程序复习线程知识 , 三个售票员    卖出    30张票
 * 
 * 笔记：Java里面如何进行工程级别的多线程编写
 * 1多线程编程模板（套路）-----上
 *    1.1 线程  操作(资源类里的实例方法)资源类 
 *    1.2 高内聚 低耦合
 */
class Sale{
	//声明票号
	int tk = 30;
	//获取lock锁
	Lock lock  = new ReentrantLock();
	public void work() {
		lock.lock();
		try {
			//判断
			if (tk > 0) {
				System.out.println(Thread.currentThread().getName() + "卖出" + (tk--) + "号票，还剩" + tk + "张票");
			}
		} finally {
			// 解锁
			lock.unlock();
		}
	}
	
}

public class LockExe {
	
	public static void main(String[] args) {
		//创建资源对象
		Sale sale = new Sale();
		//创建三个买票线程
		new Thread(()->{for (int i = 0; i < 40; i++)sale.work();}, "AA").start();
		
		new Thread(()->{for (int i = 0; i < 40; i++)sale.work();}, "BB").start(); 
		
		new Thread(()->{for (int i = 0; i < 40; i++)sale.work();}, "CC").start();
	}

}
