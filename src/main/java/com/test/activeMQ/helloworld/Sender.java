package com.test.activeMQ.helloworld;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;


public class Sender {
	public static void main(String[] args) throws JMSException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"whf",	//ActiveMQConnectionFactory.DEFAULT_USER
				"whf",	//ActiveMQConnectionFactory.DEFAULT_PASSWORD
				"tcp://localhost:61616");
	
		Connection connection = connectionFactory.createConnection();
		connection.start();
		
//		Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
		//使用事务的方式进行消息的发送	模式为自动签收
//		Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		//使用client签收方式
		Session session = connection.createSession(Boolean.TRUE, Session.CLIENT_ACKNOWLEDGE);
		Destination destination =session.createQueue("queue1");
		
//		MessageProducer messageProducer = session.createProducer(destination);
//		messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		
		MessageProducer messageProducer = session.createProducer(null);
		for (int i = 0; i < 5; i++) {
			TextMessage msg = session.createTextMessage();
			msg.setText("heoll world,id="+i);
//			messageProducer.send(msg);
			//1参数：目的地
			//2参数：消息
			//3参数：是否持久化
			//4参数：优先级
			//5参数：消息在mq上的存放有效期
			messageProducer.send(destination, msg, DeliveryMode.NON_PERSISTENT, i, 1000*60*2);
			System.out.println("生产者："+msg.getText());
		}
		
		//使用事务方式时需要提交事务
		session.commit();
		if(connection != null)
		{
			connection.close();
		}
	}
}
