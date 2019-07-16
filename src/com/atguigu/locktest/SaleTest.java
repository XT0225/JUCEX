package com.atguigu.locktest;

class MySale{
	private int number = 30;
	
	//1.同步方法：
	public synchronized void sale() {
		if (number > 0) {
			System.out.println(Thread.currentThread().getName() + "卖出第：\t" + (number--) + "\t 还剩下：" + number);
		}
	}
}

public class SaleTest {
	public static void main(String[] args) {
		
		new Thread();
	}
}
