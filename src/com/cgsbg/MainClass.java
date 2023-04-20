package com.cgsbg;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class MainClass {

	static void getCPU() {

		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

		for (Long threadID : threadMXBean.getAllThreadIds()) {
			
			ThreadInfo info = threadMXBean.getThreadInfo(threadID);
			if (info != null)
				System.out.print("Thread name: " + info.getThreadName() + "  State: " + info.getThreadState());
			
			if (threadMXBean != null) {
				System.out.print(String.format(" CPU time: %s ns", threadMXBean.getThreadCpuTime(threadID)));
				System.out.print(String.format(" CPU time: %s ms", threadMXBean.getThreadCpuTime(threadID) / (1000000)));
				System.out.println(" ");
			}
		}
	}

	static void memory() {
		
		
		OperatingSystemMXBean bean = ManagementFactory.getOperatingSystemMXBean();
		System.out.println("System load average: " + bean.getSystemLoadAverage());
		
		Runtime runtime = Runtime.getRuntime();
		
		long memory = runtime.totalMemory() - runtime.freeMemory();
		System.out.println("Used memory is bytes: " + memory);
		System.out.println("Used memory is megabytes: " + (memory/(1024*1024)));
	}

	public static void main(String[] args) {
		
		for ( int i = 0; i < 10000; i++) {
			
			new Thread(new Runnable() {
				
				@Override
				public	void run() {
					System.out.println("test thread ");
				}
			},"Counter-Thread").start();
			
			MainClass.getCPU();
			MainClass.memory();
		}
		
	}
}
