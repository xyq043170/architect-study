package com.test.futrue;

public class FutrueData implements Data {
	private RealData realData;
	private boolean isReady = false;
	
	public RealData getRealData() {
		return realData;
	}
	public synchronized void setRealData(RealData realData) {
		if(isReady)
		{
			return;
		}
		
		this.realData = realData;
		isReady = true;
		notify();
	}
	
	public synchronized String getRequest()
	{
		while(!isReady)
		{
			try {
				wait();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return this.realData.getRequest();
	}
}
