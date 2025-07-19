import java.util.*;

public class CarModels {
    public static void main(String[] args) {
        List<String> carModels = new ArrayList<>(Arrays.asList(
                "Toyota Camry", "BMW X5", "Tesla Model X",
                "Audi A6", "BMW X5", "Lada Vesta"));
        System.out.println("Original model list: " + carModels);

        // удаление дубликатов и сортировка в обратном алфавитном порядке
        Set<String> uniqueModels = new TreeSet<>(Comparator.reverseOrder());
        uniqueModels.addAll(carModels); // автоматически удаляем дубликаты
        System.out.println("Models after removing duplicates and sorting: " + uniqueModels);

        // проверка и замена "Tesla" на "ELECTRO_CAR"
        List<String> processedModels = new ArrayList<>(uniqueModels);
        for (int i = 0; i < processedModels.size(); i++) {
            if (processedModels.get(i).contains("Tesla"))
                processedModels.set(i, "ELECTRO_CAR");
        }
        System.out.println("Models after the replacement of \"Tesla\": " + processedModels);
    }
}
