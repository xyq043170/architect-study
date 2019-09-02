package com.test.redis.cluster;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

public class TestClusterRedis {
	public static void main(String[] args) {
		Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
		jedisClusterNode.add(new HostAndPort("192.168.1.240",17001));
		jedisClusterNode.add(new HostAndPort("192.168.1.241",17002));
		jedisClusterNode.add(new HostAndPort("192.168.1.242",17003));
		jedisClusterNode.add(new HostAndPort("192.168.1.243",17004));
		jedisClusterNode.add(new HostAndPort("192.168.1.244",17005));
		jedisClusterNode.add(new HostAndPort("192.168.1.246",17006));
		
		JedisPoolConfig cfg = new JedisPoolConfig();
		cfg.setMaxTotal(100);
		cfg.setMaxIdle(20);
		cfg.setMaxWaitMillis(-1);
		cfg.setTestOnBorrow(true);
		JedisCluster jc = new JedisCluster(jedisClusterNode,6000,100,cfg);
		
		System.out.println(jc.set("age", 20+""));
		System.out.println(jc.set("address", "ShenZhen"));
		System.out.println(jc.get("name"));
		System.out.println(jc.get("name1"));
		System.out.println(jc.get("name2"));
		System.out.println(jc.get("age"));
		System.out.println(jc.get("address"));
	}
}
