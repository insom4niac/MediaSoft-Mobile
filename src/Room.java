import java.util.Random;

abstract class Room {
    private final int room_number;    // номер комнаты
    private final int max_capacity;   // максимальное количество человек в комнате
    private final int price_per_night; // цена за ночь
    private boolean is_booked;  // забронирована ли комната

    //конструкторы
    public Room(int room_number, int max_capacity, int price_per_night) {
        this.room_number = room_number;
        this.max_capacity = max_capacity;
        this.price_per_night = price_per_night;
        this.is_booked = false; // по умолчанию комната не забронирована
    }

    public Room(int room_number) {
        // генерируем остальные значения случайно и вызываем верхний конструктор
        this(room_number, new Random().nextInt(5)+1, new Random().nextInt(100)+50);
    }

    // геттеры
    public int getRoomNumber() { return room_number; }
    public int getMaxCapacity() { return max_capacity; }
    public int getPricePerNight() { return price_per_night; }
    public boolean isBooked() { return is_booked; }

    // сеттеры
    public void setBooked(boolean is_booked) { this.is_booked = is_booked; }

    @Override
    public String toString() {
        return "  Room number: \t\t" + room_number +
                "\n  Max capacity:\t\t" + max_capacity +
                "\n  Price per night:\t" + price_per_night +
                "\n  Is booked:\t\t" + is_booked;
    }
}