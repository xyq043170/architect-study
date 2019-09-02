package com.test.socket.nio.base1;

import java.nio.IntBuffer;

public class Main {
	public static void main(String[] args) {
		/*IntBuffer buf = IntBuffer.allocate(10);
		buf.put(13);
		buf.put(21);
		buf.put(35);
		buf.flip();		//	位置复位为0
		System.out.println(buf);
		System.out.println(buf.capacity());
		System.out.println(buf.limit());
		
		System.out.println(buf.get(1));
		System.out.println(buf);
		buf.put(1, 4);
		System.out.println(buf.get(1));
		System.out.println(buf);
		
		for (int i = 0; i < buf.limit(); i++) {
			System.out.print(buf.get()+"\t");
		}
		
		System.out.println(buf);*/
		
		/*int[] arr = new int[]{1,2,5};
		IntBuffer buf = IntBuffer.wrap(arr);
		System.out.println(buf);
		
		IntBuffer buf2 = IntBuffer.wrap(arr,0,2);
		System.out.println(buf2);*/
		
		IntBuffer buf = IntBuffer.allocate(10);
		int[] arr = new int[]{1,2,5};
		buf.put(arr);
		System.out.println(buf);
		
		IntBuffer buf2 = buf.duplicate();
		System.out.println(buf2);
		
//		buf.position(0);
		buf.flip();
		System.out.println(buf);
		System.out.println(buf.remaining());
		
		int[] arr2 = new int[buf.remaining()];
		buf.get(arr2);
		for (int i : arr2) {
			System.out.print(Integer.toString(i)+",");
		}
		
	}
}
