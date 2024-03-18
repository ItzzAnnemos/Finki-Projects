package mk.ukim.finki.NP.ZadaciZaVezbanje2.Car;

public class Car {
    private String manufacturer;
    private String model;
    private int price;
    private float power;

    public Car(String manufacturer, String model, int price, float power) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.price = price;
        this.power = power;
    }

    public int getPrice() {
        return price;
    }

    public float getPower() {
        return power;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return String.format("%s %s (%dKW) %d", manufacturer, model,(int)power, price);
    }
}
