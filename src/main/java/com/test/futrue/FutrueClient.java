package com.test.futrue;

import java.util.Date;

public class FutrueClient {
	public Data request(final String str)
	{
		final FutrueData futrueData = new FutrueData();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				RealData realData=new RealData(str);
				futrueData.setRealData(realData);
			}
		}).start();
		
		return futrueData;
	}
}
