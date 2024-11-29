package menu;

import room.Room;

public class DisplayRoomStateCommand implements Command {
    private Room room;

    public DisplayRoomStateCommand(Room room) {
        this.room = room;
    }

    public DisplayRoomStateCommand() {
    }
    @Override
    public void execute() {
        System.out.println("Current room state:");
        room.getToys().forEach(toy -> System.out.println(toy.getDescription()));
        System.out.println("Total cost: " + room.getTotalCost());
    }
}

