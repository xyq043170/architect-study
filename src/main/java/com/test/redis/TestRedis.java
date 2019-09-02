package com.test.redis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class TestRedis {
	private static Jedis jedis;
	private static ShardedJedis shard;
	private static ShardedJedisPool pool;
	
	
	public static void main(String[] args) {
		jedis = new Jedis("192.168.1.240",6379);
//		jedis.auth("123");
		List<String> list = jedis.mget("name","age");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
//		
//		jedis.set("sex", "nan");
		
//		//需多种集合配合使用，目前使用hash和set配合使用
//		final String SYS_USER_SEL_AGE_21 = "SYS_USER_SEL_AGE_21";
//		final String SYS_USER_SEL_SEX_M = "SYS_USER_SEL_SEX_M";
//		final String SYS_USER_SEL_SEX_W = "SYS_USER_SEL_SEX_W";
//		final String SYS_USER_TABLE ="SYS_USER_TABLE";
//		
//		Set<String> user_ages= jedis.smembers(SYS_USER_SEL_AGE_21);
//		for (Iterator iterator = user_ages.iterator(); iterator.hasNext();) {
//			String string = (String) iterator.next();
//			String aa =jedis.hget(SYS_USER_TABLE, string);
//			System.out.println(aa);
//		}
//		System.out.println("=============================================================================");
//		Set<String> user_all=jedis.sinter(SYS_USER_SEL_AGE_21,SYS_USER_SEL_SEX_M);
//		User user =null;
//		for (Iterator iterator = user_all.iterator(); iterator.hasNext();) {
//			String string = (String) iterator.next();
//			String aa =jedis.hget(SYS_USER_TABLE, string);
//			System.out.println(aa.replace("[", "").replace("]", ""));
//			user =JsonUtilss.objectFromJson(aa.replace("[", "").replace("]", ""), User.class);
//		}
//		System.out.println(user.getAge()+","+user.getSex());
		/*
		System.out.println(jedis.get("sex"));
		Random dom = new Random();
		Map<String,String> map = new HashMap<String,String>();
		
		String uid1=UUID.randomUUID().toString();
		User u1 = new User(uid1,"z1",dom.nextInt(100),"m");
		map.put(uid1, JSONUtils.toJSONString(u1));
		jedis.sadd(SYS_USER_SEL_SEX_M, uid1);
		
		String uid2=UUID.randomUUID().toString();
		User u2 = new User(uid2,"z2",21,"w");
		map.put(uid2, JSONUtils.toJSONString(u2));
		jedis.sadd(SYS_USER_SEL_SEX_W, uid2);
		jedis.sadd(SYS_USER_SEL_AGE_21, uid2);
		
		String uid3=UUID.randomUUID().toString();
		User u3 = new User(uid3,"z3",21,"m");
		map.put(uid3, JSONUtils.toJSONString(u3));
		jedis.sadd(SYS_USER_SEL_SEX_M, uid3);
		jedis.sadd(SYS_USER_SEL_AGE_21, uid3);
		
		String uid4=UUID.randomUUID().toString();
		User u4 = new User(uid4,"z4",dom.nextInt(100),"w");
		map.put(uid4, JSONUtils.toJSONString(u4));
		jedis.sadd(SYS_USER_SEL_SEX_W, uid4);
		
		String uid5=UUID.randomUUID().toString();
		User u5 = new User(uid5,"z5",dom.nextInt(100),"m");
		map.put(uid5, JSONUtils.toJSONString(u5));
		jedis.sadd(SYS_USER_SEL_SEX_M, uid5);
		
		
		jedis.hmset("SYS_USER_TABLE", map);*/
		
	}
}
