package xin.mafh.jms.queue;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class AppComsumer {
    public static final String url = "failover:(tcp://www.mafh.xin:61617,tcp://www.mafh.xin:61618,tcp://www.mafh.xin:61616)?randomize=true";
    public static final String queueName = "queue-test";
    public static void main(String[] args) throws JMSException {
//        1.创建connectionFactory
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(url);
//        2.创建connection
        Connection connection = activeMQConnectionFactory.createConnection();
//        3.启动连接
        connection.start();
//        4.创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        5.创建一个目标
        Destination destination = session.createQueue(queueName);
//        6.创建一个消费者
        MessageConsumer messageConsumer = session.createConsumer(destination);
//        7.创建一个监听器
        messageConsumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage)message;
//                8.接受消息
                try {
                    System.out.println("接收消息："+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
//        9.关闭连接
//        connection.close();
    }
}
