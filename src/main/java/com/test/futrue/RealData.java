package com.test.futrue;

public class RealData implements Data {
	private String result;
	public RealData(String str)
	{
		System.out.println("根据"+str+"进行查询，这是一个耗时操作");
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("操作完毕，获取结果");
		result = "查询结果";
	}
	@Override
	public String getRequest() {
		// TODO Auto-generated method stub
		return result;
	}
}
