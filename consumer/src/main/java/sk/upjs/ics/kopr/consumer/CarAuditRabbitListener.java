package sk.upjs.ics.kopr.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import sk.upjs.ics.kopr.Car;

@Component
public class CarAuditRabbitListener {
    public static final Logger logger = LoggerFactory.getLogger(CarAuditRabbitListener.class);

    @RabbitListener(
            bindings = {
                    @QueueBinding(
                            exchange = @Exchange(name = "car", type="topic"),
                            value = @Queue("any-car"),
                            key = "car.#"
                    )
            }
    )
    public void onAnyCar(Car car) {
        logger.info("Audit: {} {}", car.getMake(), car.getModel());
    }
}
