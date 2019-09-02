package com.test.executors.customThreadPool;

public class MyTask implements Runnable {
	private int taskId;
	private String taskName;
	
	public MyTask(int taskId,String taskName)
	{
		this.taskId =taskId;
		this.taskName =taskName;
	}
	
	
	public int getTaskId() {
		return taskId;
	}


	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}


	public String getTaskName() {
		return taskName;
	}


	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}


	@Override
	public void run() {
		try {
			System.out.println("run taskId="+this.taskId);
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.taskId+"";
	}
	
	
}
