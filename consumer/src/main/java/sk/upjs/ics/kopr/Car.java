package sk.upjs.ics.kopr;

import java.io.Serializable;

public class Car implements Serializable {
    private String make;

    private String model;

    private CarSegment segment;

    public static Car car(String specification, CarSegment segment) {
        String[] components = specification.split(" ");
        if (components.length != 2) {
            throw new IllegalArgumentException("Cannot parse car specification. Expecting 2 arguments in: '" + specification + "'");
        }
        Car car = new Car();
        car.setMake(components[0]);
        car.setModel(components[1]);
        car.setSegment(segment);
        return car;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public CarSegment getSegment() {
        return segment;
    }

    public void setSegment(CarSegment segment) {
        this.segment = segment;
    }

    @Override
    public String toString() {
        return this.make + " " + this.model;
    }
}
