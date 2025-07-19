public interface RoomService<T extends Room> {
    void clean(T room);
    boolean reserve(T room) throws RoomAlreadyBookedException;
    boolean free(T room);
}
