package sk.upjs.ics.kopr;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static sk.upjs.ics.kopr.Car.car;

public class CarRandomizer {
    private static Random random = new Random();

    private List<Car> SEGMENT_A = List.of(
            car("Chevrolet Spark", CarSegment.A),
            car("Fiat 500", CarSegment.A),
            car("Kia Picanto", CarSegment.A),
            car("Renault Twingo", CarSegment.A)
    );

    private List<Car> SEGMENT_C = List.of(
            car("VW Golf", CarSegment.C),
            car("Honda Civic", CarSegment.C),
            car("Toyota Corolla", CarSegment.C),
            car("Mazda 3", CarSegment.C)
    );

    private List<Car> SEGMENT_J = List.of(
            car("Toyota RAV4", CarSegment.J),
            car("Honda CR-V", CarSegment.J),
            car("Kia Sportage", CarSegment.J),
            car("VW Tiguan", CarSegment.J)
    );

    private Map<CarSegment, List<Car>> inventory = new LinkedHashMap<>();

    public CarRandomizer() {
        inventory.put(CarSegment.A, SEGMENT_A);
        inventory.put(CarSegment.C, SEGMENT_C);
        inventory.put(CarSegment.J, SEGMENT_J);
    }

    private <T> T randomValue(T[] array, Random random) {
        int randomIndex = random.nextInt(array.length);
        return array[randomIndex];
    }

    private <T> T randomValue(List<T> list, Random random) {
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }

    public Car randomCar() {
        CarSegment randomSegment = randomValue(CarSegment.values(), CarRandomizer.random);
        List<Car> cars = this.inventory.get(randomSegment);
        return randomValue(cars, CarRandomizer.random);
    }
}
