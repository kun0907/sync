package com.service;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class TaskExecutorUtil {
	
	
	private ThreadPoolTaskExecutor taskExecutor;
    public ThreadPoolTaskExecutor getTaskExecutor() {
        return taskExecutor;
    }

    public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public void startThread(Runnable r) {
        taskExecutor.execute(r);
    }

	public int sysActiveCou() {
		System.out.println("over------------------------" + taskExecutor.getActiveCount());
		return taskExecutor.getActiveCount();
	}
}
