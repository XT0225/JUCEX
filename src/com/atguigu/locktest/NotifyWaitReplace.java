package com.atguigu.locktest;
/**
 * 
 * @author Administrator
 * 现在有两个线程，
 * 可以操作初始值为零的一个变量，
 * 实现一个线程对该变量加1，一个线程对该变量减1，
 * 交替，来10轮。 
 *
 * 笔记：Java里面如何进行工程级别的多线程编写
 * 1多线程编程模板（套路）-----上
 *    1.1 线程操作资源类 
 *    1.2 高内聚 低耦合
 * 2多线程编程模板（套路）-----下
 *    2.1 判断
 *    		防止出现虚假唤醒，使用while
 *    2.2 干活
 *    2.3 通知
 *
 */
//创建线程资源类

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData2{
	//初值
	int sharedata = 0;
	//获取锁
	Lock lock = new ReentrantLock();
	//创建condition对象
	Condition cd = lock.newCondition();
	
	public void inc() throws InterruptedException {
		//上锁
		lock.lock();
		//判断 		防止出现虚假唤醒，使用while
		while (sharedata != 0) {
			cd.await();
		}
		//干活
		sharedata++;
		System.out.println(Thread.currentThread().getName() + ":" + sharedata);
		//通知
		cd.signalAll();
		//解锁
		lock.unlock();
	}

	public void dec() throws InterruptedException {
		//上锁
		lock.lock();
		//判断
		while (sharedata != 1) {
			cd.await();
		}
		//干活
		sharedata--;
		System.out.println(Thread.currentThread().getName() + ":" + sharedata);
		//通知
		cd.signalAll();
		//解锁
		lock.unlock();
	}
	
}

public class NotifyWaitReplace {
	public static void main(String[] args) {
		//获取资源类
		ShareData2 shareData = new ShareData2();
		//创建线程，需要交替十轮
		new Thread(()->{for (int i = 0; i < 10; i++)
			try {
				shareData.inc();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}}, "AA").start();
		
		new Thread(()->{for (int i = 0; i < 10; i++)
			try {
				shareData.dec();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}}, "BB").start();
	}
}
