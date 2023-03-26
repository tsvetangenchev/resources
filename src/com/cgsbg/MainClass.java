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
			System.out.println("Thread name: " + info.getThreadName() + "Thread State: " + info.getThreadState());
			System.out.println(String.format("CPU time: %s ns", threadMXBean.getThreadCpuTime(threadID)));
			System.out.println(String.format("CPU time: %s ms", threadMXBean.getThreadCpuTime(threadID) / (1000000)));
		}
	}

	static void memory() {
		
		
		OperatingSystemMXBean bean = ManagementFactory.getOperatingSystemMXBean();
		System.out.println("System load average: " + bean.getSystemLoadAverage());
		
		//getCommittedVirtualMemorySize());
		Runtime runtime = Runtime.getRuntime();
		
		long memory = runtime.totalMemory() - runtime.freeMemory();
		System.out.println("Used memory is bytes: " + memory);
		System.out.println("Used memory is megabytes: " + (memory/(1024*1024)));
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10000; i++) {
			MainClass.getCPU();
			MainClass.memory();
		}
	}
}
