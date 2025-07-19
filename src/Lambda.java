import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Lambda {
    public static void main(String[] args) {
        // 1. вывод в консоль сообщения с помощью интерфейса Printable
        Printable printer = () -> System.out.println("1. Printing from lambda expression!");
        printer.print();

        // 2. проверка пустой строки.
        Predicate<String> isNotNull = str -> str != null;           // возвращает true, если строка не null
        Predicate<String> isNotEmpty = str -> !str.isEmpty();       // проверяет, что строка не пуста
        Predicate<String> isNotNullAndEmpty = isNotNull.and(isNotEmpty);  // проверяем строку, используя описанные выше выражения

        System.out.println("\n2. Checking if strings aren't null or empty");
        System.out.println(" > String \"\": \t\t" + isNotNullAndEmpty.test(""));        // false
        System.out.println(" > String null: \t" + isNotNullAndEmpty.test(null));        // false
        System.out.println(" > String \"Java\": \t" + isNotNullAndEmpty.test("Java"));  // true

        // 3. проверка строки на удовлетворение условиям
        Predicate<String> startsWithJorN = str -> !str.isEmpty()                        // строка не пуста
                && (str.toLowerCase().startsWith("j") || str.toLowerCase().startsWith("n"));  // начало на J или N
        Predicate<String> endsWithA = str -> str.toLowerCase().endsWith("a");           // конец на A
        Predicate<String> checkString = startsWithJorN.and(endsWithA);

        System.out.println("\n3. Checking whether the strings have the format \"J___A\" or \"N___A\"");
        System.out.println(" > String \"Java\": \t" + checkString.test("Java"));        // true
        System.out.println(" > String \"Ninja\": \t" + checkString.test("Ninja"));      // true
        System.out.println(" > String \"Lambda\": " + checkString.test("Lambda"));      // false

        // 4. лямбда выражение для HeavyBox.
        System.out.println("\n4. Lambda expression for HeavyBox");
        HeavyBox box = new HeavyBox(25);
        Consumer<HeavyBox> shipment = b -> System.out.println(" > A " + b.weight + "kg box was loaded!");
        Consumer<HeavyBox> send = b -> System.out.println(" > Sending box. Weight: " + b.weight);
        shipment.andThen(send).accept(box);


        // 5. проверка на положительность/отрицательность числа с помощью Function
        Function<Integer, String> numberChecker = num -> {
            if (num > 0)
                return "Number is positive";
            else if (num < 0)
                return "Number is negative";
            else
                return "Number is zero";
        };

        System.out.println("\n5. Checking if numbers are greater or less than zero");
        System.out.println(" > Number:\t10\t - " + numberChecker.apply(10));    // pos
        System.out.println(" > Number:\t 0\t - " + numberChecker.apply(0));     // zero
        System.out.println(" > Number: -14\t - " + numberChecker.apply(-14));   // neg


        // 6. лямбда для Supplier.
        System.out.println("\n6. Random numbers in [0,10] with Supplier");
        Supplier<Integer> randNumSupplier = () -> new Random().nextInt(11);
        System.out.println(" > Number:\t" + randNumSupplier.get());
        System.out.println(" > Number:\t" + randNumSupplier.get());
        System.out.println(" > Number:\t" + randNumSupplier.get());
    }

    // ===================================
    // вспомогательные классы и интерфейсы
    // ===================================
    // интерфейс для задания 1 - вывод с помощью лямбда-выражения
    interface Printable {
        void print();
    }

    // класс для задания 4
    static class HeavyBox {
        int weight;
        public HeavyBox(int w) {
            weight = w;
        }
    }
}
