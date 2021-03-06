package com.yjy.old.topic;

import com.rabbitmq.client.*;
import com.yjy.old.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Administrator
 * Date: 2017/8/28
 * Time: 16:40
 */
public class Recv2 {
    private final static String QUEUE_NAME = "test_queue_topic_2";
    private final static String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtil.getConnection();

        final Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "item.#");

        channel.basicQos(1);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body);
                System.out.println(" [x] Received '" + message + "'");
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume(QUEUE_NAME, false, consumer);

    }
}
