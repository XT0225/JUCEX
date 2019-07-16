package com.atguigu.locktest;
/**
 * 
 * @author Administrator
 * 	1、有顺序通知，需要有标识位
 *	2、有一个锁Lock，3把钥匙Condition
 *	3、判断标志位
 *	4、输出线程名+第几轮+第几次
 *	5、修改标志位，通知下一个
 *	多线程之间按顺序调用，实现A->B->C
 * 	三个线程启动，要求如下：
 * 
 * 	AA打印5次，BB打印10次，CC打印15次
 * 	接着
 * 	AA打印5次，BB打印10次，CC打印15次
 * 	......来10轮 
 *
 */
//创建线程资源类
class PrintSource{
	
}


public class CustomizedNW {
	public static void main(String[] args) {
		
	}
}
