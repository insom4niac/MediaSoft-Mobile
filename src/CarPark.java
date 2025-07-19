import java.util.Arrays;
import java.util.Random;

public class CarPark {
    public static void main(String[] args) {
        int[] productionYears = new int[50];
        Random random = new Random();

        // заполнение массива случайными годами выпуска (2000-2025)
        for (int i = 0; i < productionYears.length; i++) {
            productionYears[i] = 2000 + random.nextInt(26); // 2000+[0,25]
        }

        System.out.println("All years of car production: " + Arrays.toString(productionYears));

        // вывод машин, выпущенных после 2015 года
        System.out.print("Cars produced after 2015: ");
        Arrays.stream(productionYears)
                .filter(year-> year > 2015)
                .forEach(year -> System.out.print(year + " "));
        System.out.println();

        // расчет среднего возраста авто
        double avgAge = Arrays.stream(productionYears)
                .map(year -> 2025 - year)
                .average()
                .orElse(0); // возвращаем 0 если массив пустой
        System.out.println("Average car age: " + String.format("%.2f", avgAge));
    }
}
