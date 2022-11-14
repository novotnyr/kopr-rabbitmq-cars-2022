package sk.upjs.ics.kopr;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CarRabbitProducer {
    private CarRandomizer carRandomizer = new CarRandomizer();

    @Autowired
    private RabbitTemplate rabbit;

    @Scheduled(fixedRate = 2000)
    public void random() {
        Car car = this.carRandomizer.randomCar();
        System.out.println(car);

        var routingKey = "car." + car.getSegment();

        System.out.println(car + " " + routingKey);

        rabbit.convertAndSend("car", routingKey, car);
    }
}
