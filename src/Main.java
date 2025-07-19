public class Main {
    public static void main(String[] args) {
        StandardRoom stdRoom = new StandardRoom(101, 2, 1000);
        ProRoom proRoom = new ProRoom(412, 4, 5000);

        RoomServiceImpl<StandardRoom> standartRoomService = new RoomServiceImpl<>();
        RoomServiceImpl<ProRoom> proRoomService = new RoomServiceImpl<>();

        // тестирование стандартной комнаты
        System.out.println("Testing standard room:\n" + stdRoom);
        standartRoomService.clean(stdRoom);                             // уборка номера

        try {                                                           // бронирование
            standartRoomService.reserve(stdRoom);
            System.out.println("> Standard room booked successfully!");
        } catch (RoomAlreadyBookedException e) {
            System.err.println("> ERROR: " + e.getMessage());
        }

        standartRoomService.free(stdRoom);                              // освобождаем номер
        System.out.println("> Standard room freed");

        // тестирование проф. комнаты
        System.out.println("\nTesting ProRoom:\n" + proRoom);
        proRoomService.clean(proRoom);                                  // уборка

        try {
            proRoomService.reserve(proRoom);                            // бронирование
            System.out.println("> ProRoom booked successfully.");
        } catch (RoomAlreadyBookedException e) {
            System.err.println("> Error: " + e.getMessage());
        }

        proRoomService.free(proRoom);                                   // освобождение
        System.out.println("> ProRoom freed.");
    }
}
