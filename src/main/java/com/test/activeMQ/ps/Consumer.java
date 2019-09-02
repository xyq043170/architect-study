package com.test.activeMQ.ps;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.jms.listener.adapter.ListenerExecutionFailedException;


public class Consumer {
	public final String SELECTOR_0 = "age > 25";
	public final String SELECTOR_1 = "color = 'blue'";
	
	public final String SELECTOR_2 = "color = 'blue' AND sal > 2000";
	
	public final String SELECTOR_3 = "receiver = 'A'";
	
	public final String SELECTOR_4 = "sal > 1500";
	
	private ConnectionFactory connectionFactory;
	private Connection connection;
	private Session session;
	private MessageConsumer messageConsumer;
	
	private Destination destination;
	public Consumer()
	{
		try {
			this.connectionFactory = new ActiveMQConnectionFactory(
					"whf",
					"whf",
					"tcp://localhost:61616");
			this.connection = this.connectionFactory.createConnection();
			this.connection.start();
			this.session = this.connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			
			this.destination=this.session.createQueue("frist");
			this.messageConsumer = this.session.createConsumer(this.destination,SELECTOR_1);
					
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void receiver()
	{
		try {
			this.messageConsumer.setMessageListener(new Listener());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	class Listener implements MessageListener{

		@Override
		public void onMessage(Message message) {
			try {
				if(message instanceof TextMessage)
				{
					
				}
				if(message instanceof MapMessage)
				{
					MapMessage ret =(MapMessage) message;
					System.out.println(ret.toString());
					System.out.println(ret.getString("name"));
					System.out.println(ret.getString("age"));
					System.out.println(ret.getStringProperty("color"));
					System.out.println(ret.getStringProperty("sal"));
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
	
	public static void main(String[] args) {
		Consumer c =new Consumer();
		c.receiver();
	}
}
