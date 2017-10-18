package xin.mafh.jms.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 *
 */
public class AppProducer {
    public static final String url = "tcp://www.mafh.xin:61616";
    public static final String topicName = "topic-test";
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
        Destination destination = session.createTopic(topicName);
//        6.创建一个生产者
        MessageProducer messageProducer = session.createProducer(destination);

        for (int i = 0; i < 100; i++) {
//            7.创建消息
            TextMessage textMessage = session.createTextMessage("text"+i);
//            8.发布消息
            messageProducer.send(textMessage);
            System.out.println("生产者发送消息："+textMessage.getText());
        }
//        9.关闭连接
        connection.close();
    }
}
