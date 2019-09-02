package com.test.activeMQ.psmysql;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver {
	public static void main(String[] args) throws JMSException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"whf",	//ActiveMQConnectionFactory.DEFAULT_USER
				"whf",	//ActiveMQConnectionFactory.DEFAULT_PASSWORD
				"tcp://localhost:61616");
	
		Connection connection = connectionFactory.createConnection();
		connection.start();
		
//		Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
		//使用clent模式接收	接收消息后需要手工告诉发送端要acknowledge
		Session session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);
		Destination destination =session.createQueue("queue1");
		MessageConsumer messageConsumer = session.createConsumer(destination);
		while(true)
		{
			TextMessage msg =(TextMessage) messageConsumer.receive();
			//发送模式为client模式时，需要手动acknowledge签收消息，另起一个线程通知tcp
			msg.acknowledge();
			if(msg == null) break;
			System.out.println("收到内容："+msg.getText());
		}
		
		if(connection != null)
		{
			connection.close();
		}
	}
}
