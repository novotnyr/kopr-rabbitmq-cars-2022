package sk.upjs.ics.kopr.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sk.upjs.ics.kopr.Car;

@SpringBootApplication
public class ConsumerApplication {
    public static final Logger logger = LoggerFactory.getLogger(ConsumerApplication.class);

    @RabbitListener(queues = "large-car")
    public void consumeCar(Car car) {
        logger.info("{} {}: segment: {}", car.getMake(), car.getModel(), car.getSegment());
    }

    @RabbitListener(queues = "mini-car")
    public void consumeMiniCar(Car car) {
        if (car.getMake().equalsIgnoreCase("chevrolet")) {
            logger.error("Cannot consume Chevrolet {}", car.getModel());
            throw new AmqpRejectAndDontRequeueException("Cannot consume Chevrolet");
        }

        logger.info("Consuming mini cars: {} {}", car.getMake(), car.getModel());
    }

    // chceme front pre A-ckovy segment
    // 1. nadeklarovat exchange: 'car'
    // 2. nadeklarovat front pre auta A-ckoveho segmentu
    // 3. binding: medzi exchange 'car' a frontom

    @Bean
    Exchange carExchange() {
        return ExchangeBuilder.topicExchange("car").build();
    }

    @Bean
    Queue aSegmentQueue() {
        return new Queue("mini-car");
    }

    @Bean
    Binding aSegmentCarBinding() {
        return BindingBuilder.bind(aSegmentQueue())
                             .to(carExchange())
                             .with("car.A")
                             .noargs();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

}
