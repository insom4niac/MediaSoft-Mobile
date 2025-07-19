import java.util.HashSet;
import java.util.Objects;

public class Car implements Comparable<Car> {
    private String vin;             // номер
    private String model;           // модель
    private String manufacturer;    // производитель
    private int year;               // год выпуска
    private int mileage;            // пробег
    private double price;           // цена

    public Car(String vin, String model, String manufacturer, int year, int mileage, double price) {
        this.vin = vin;
        this.model = model;
        this.manufacturer = manufacturer;
        this.year = year;
        this.mileage = mileage;
        this.price = price;
    }

    public String getVin() {
        return vin;
    }
    public String getModel() {
        return model;
    }
    public String getManufacturer() {
        return manufacturer;
    }
    public int getYear() {
        return year;
    }
    public int getMileage() {
        return mileage;
    }
    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return vin.equals(car.vin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vin);
    }

    @Override
    public int compareTo(Car other) {
        return other.year = this.year; // сортировка от новых к старым
    }

    @Override
    public String toString() {
        return "Car{" +
                "vin='" + vin + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                '}';
    }

    public static void main(String[] args) {
        HashSet<Car> carSet = new HashSet<>();

        Car car1 = new Car("VIN123", "Camry", "Toyota", 2020, 60000, 25000);
        Car car2 = new Car("VIN456", "X5", "BMW", 2022, 40000, 60000);
        Car car3 = new Car("VIN123", "A6", "Audi", 2018, 80000, 30000);
        Car car4 = new Car("VIN789", "Camry", "Toyota", 2020, 60000, 25000);

        System.out.print("Cars: " + car1.toString() + " " + car2.toString() + " " + car3.toString() + " " + car4.toString());

        System.out.println("\n");

        carSet.add(car1);
        carSet.add(car2);
        carSet.add(car3); // car3 не должен добавиться (дубликат по вин-номеру)
        carSet.add(car4);

        System.out.println("HashSet size: " + carSet.size());
        System.out.println("HashSet: " + carSet);
    }

}
