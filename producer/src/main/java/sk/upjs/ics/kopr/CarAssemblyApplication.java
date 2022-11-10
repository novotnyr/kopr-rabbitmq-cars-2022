package sk.upjs.ics.kopr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class CarAssemblyApplication {
    private CarRandomizer carRandomizer = new CarRandomizer();

    @Scheduled(fixedRate = 2000)
    public void random() {
        Car car = this.carRandomizer.randomCar();
        System.out.println(car);
    }

    public static void main(String[] args) {
        SpringApplication.run(CarAssemblyApplication.class);
    }
}
