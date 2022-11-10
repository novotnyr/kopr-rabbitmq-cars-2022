package sk.upjs.ics.kopr.consumer;

public class Car {
    public enum Segment {
        A, C, J
    }

    private String make;

    private String model;

    private Segment segment;

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

    public Segment getSegment() {
        return segment;
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }

    @Override
    public String toString() {
        return this.make + " " + this.model;
    }
}
