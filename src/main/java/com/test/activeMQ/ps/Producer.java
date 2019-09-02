package com.test.activeMQ.ps;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {
	private ConnectionFactory connectionFactory;
	private Connection connection;
	private Session session;
	
	private MessageProducer messageProducer;
	
	public Producer()
	{
		try {
			this.connectionFactory = new ActiveMQConnectionFactory(
					"whf",
					"whf",
					"tcp://localhost:61616");
			this.connection = this.connectionFactory.createConnection();
			this.connection.start();
			this.session = this.connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			this.messageProducer = this.session.createProducer(null);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public Session getSession()
	{
		return this.session;
	}
	
	public void send1()
	{
		try {
			Destination destination=this.session.createQueue("frist");
			MapMessage msg1 = this.session.createMapMessage();
			msg1.setString("name", "张三");
			msg1.setString("age", "23");
			msg1.setStringProperty("color", "blue");
			msg1.setIntProperty("sal", 2200);
			
			MapMessage msg2 = this.session.createMapMessage();
			msg2.setString("name", "李四");
			msg2.setString("age", "26");
			msg2.setStringProperty("color", "red");
			msg2.setIntProperty("sal", 1300);
			
			MapMessage msg3 = this.session.createMapMessage();
			msg3.setString("name", "王五");
			msg3.setString("age", "28");
			msg3.setStringProperty("color", "green");
			msg3.setIntProperty("sal", 1500);
			
			MapMessage msg4 = this.session.createMapMessage();
			msg4.setString("name", "赵六");
			msg4.setString("age", "27");
			msg4.setStringProperty("color", "block");
			msg4.setIntProperty("sal", 2500);
			
//			msg1.setString("name", "张三");
//			msg1.setString("age", "23");
//			msg1.setStringProperty("color", "blue");
//			msg1.setIntProperty("sal", 2200);
//			int id = 1;
//			msg1.setInt("id", id);
//			String receiver = id%2 == 0 ? "A" : "B";
//			msg1.setStringProperty("receiver", receiver);
			
			this.messageProducer.send(destination, msg1, DeliveryMode.NON_PERSISTENT, 2, 1000*60*10L);
			this.messageProducer.send(destination, msg2, DeliveryMode.NON_PERSISTENT, 3, 1000*60*10L);
			this.messageProducer.send(destination, msg3, DeliveryMode.NON_PERSISTENT, 6, 1000*60*10L);
			this.messageProducer.send(destination, msg4, DeliveryMode.NON_PERSISTENT, 9, 1000*60*10L);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void send2()
	{
		try {
			Destination destination =this.session.createQueue("frist");
			TextMessage msg = this.session.createTextMessage("我是一个字符串");
			this.messageProducer.send(destination, msg, DeliveryMode.NON_PERSISTENT, 9, 1000*60*10L);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args) {
		Producer p = new Producer();
		p.send1();
	}
}
