class RoomServiceImpl<T extends Room> implements RoomService<T> {

    @Override
    public void clean(T room) {
        System.out.println("> Cleaning room " + room.getRoomNumber());
    }

    @Override
    public boolean reserve(T room) throws RoomAlreadyBookedException {
        if (room.isBooked()) {
            throw new RoomAlreadyBookedException("> Room " + room.getRoomNumber() + " is already booked.");
        }
        room.setBooked(true);
        System.out.println("> Room " + room.getRoomNumber() + " reserved.");
        return true;
    }

    @Override
    public boolean free(T room) {
        if (room.isBooked()) {
            room.setBooked(false);
            System.out.println("> Room " + room.getRoomNumber() + " freed.");
            return true;
        } else {
            System.out.println("> Room " + room.getRoomNumber() + " is not booked.");
            return false;
        }
    }
}