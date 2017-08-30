package spring;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Administrator
 * Date: 2017/8/30
 * Time: 11:46
 */
public class SpringMain {

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/rabbitmq-context.xml");
        RabbitTemplate template = context.getBean(RabbitTemplate.class);

        template.convertAndSend("Hello, world!");
        Thread.sleep(1000);

        context.destroy();
    }
}
