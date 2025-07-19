import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamApiExample {
    public static void main(String[] args) {
        List<Car> cars = Arrays.asList(
                new Car("VIN1", "Model S", "Tesla", 2023, 20000, 80000),
                new Car("VIN2", "X5", "BMW", 2022, 40000, 60000),
                new Car("VIN3", "Camry", "Toyota", 2020, 60000, 25000),
                new Car("VIN4", "A6", "Audi", 2018, 80000, 30000),
                new Car("VIN5", "E-Class", "Mercedes", 2021, 30000, 55000),
                new Car("VIN6", "Model 3", "Tesla", 2024, 10000, 75000)
        );

        // фильтр, сортировка и топ-3
        System.out.println("Top 3 most expensive cars with a mileage of less than 50_000 km: ");
        cars.stream().filter(car -> car.getMileage() < 50000)
                .sorted((c1,c2) -> Double.compare(c2.getPrice(), c1.getPrice()))
                .limit(3)
                .forEach(car -> System.out.println(car.getManufacturer() + " " + car.getModel() + " - " + car.getPrice()));

        // средний пробег
        double avgMileage = cars.stream()
                .mapToInt(Car::getMileage)
                .average()
                .orElse(0);
        System.out.println("\nAverage mileage: " + String.format("%.2f", avgMileage));

        // группировка по производителю
        Map<String, List<Car>> carsByManufacturer = cars.stream()
                .collect(Collectors.groupingBy(Car::getManufacturer));
        System.out.println("\nGrouping by manufacturer:");
        carsByManufacturer.forEach((manufacturer, carList) -> {
            System.out.println(manufacturer + ":");
            carList.forEach(car -> System.out.println(" - " + car.getModel()));
        });
    }
}
