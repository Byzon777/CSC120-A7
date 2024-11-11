import java.util.ArrayList;

/**
 * Represents a house, which is a specific type of building where residents live.
 */
public class House extends Building {
    private ArrayList<String> residents;
    private boolean hasDiningRoom;
    private boolean hasElevator;

    /**
     * Constructs a new House object with the given parameters.
     *
     * @param name          The name of the house.
     * @param address       The address of the house.
     * @param nFloors       The number of floors in the house.
     * @param hasDiningRoom Whether the house has a dining room or not.
     * @param hasElevator   Whether the house has an elevator.
     */
    public House(String name, String address, int nFloors, boolean hasDiningRoom, boolean hasElevator) {
        super(name, address, nFloors);
        this.residents = new ArrayList<>();
        this.hasDiningRoom = hasDiningRoom;
        this.hasElevator = hasElevator;
    }

    /**
     * Checks if the house has a dining room.
     *
     * @return true if the house has a dining room, false otherwise.
     */
    public boolean hasDiningRoom() {
        return this.hasDiningRoom;
    }

    /**
     * Checks if the house has an elevator.
     *
     * @return true if the house has an elevator, false otherwise.
     */
    public boolean hasElevator() {
        return this.hasElevator;
    }

    /**
     * Gets the number of residents living in the house.
     *
     * @return The number of residents.
     */
    public int nResidents() {
        return this.residents.size();
    }

    /**
     * Adds a resident to the house if they are not already living there.
     *
     * @param name The name of the resident to add.
     * @return true if the resident is successfully added, false if they already live there.
     */
    public boolean moveIn(String name) {
        if (this.residents.contains(name)) {
            System.out.println(name + " already lives here.");
            return false;
        }
        this.residents.add(name);
        return true;
    }

    /**
     * Overloaded method to add a resident along with their age.
     *
     * @param name The name of the resident to add.
     * @param age  The age of the resident.
     * @return true if the resident is successfully added, false if they already live there.
     */
    public boolean moveIn(String name, int age) {
        return moveIn(name + " (age " + age + ")");
    }

    /**
     * Removes a resident from the house.
     *
     * @param name The name of the resident to remove.
     * @return The name of the resident removed, or null if the resident was not found.
     */
    public String moveOut(String name) {
        if (this.residents.remove(name)) {
            return name;
        }
        return null;
    }

    /**
     * Overloaded method to remove a resident along with their age.
     *
     * @param name The name of the resident to remove.
     * @param age  The age of the resident.
     * @return The name of the resident removed, or null if the resident was not found.
     */
    public String moveOut(String name, int age) {
        return moveOut(name + " (age " + age + ")");
    }

    /**
     * Checks if a person is a resident of the house.
     *
     * @param person The name of the person to check.
     * @return true if the person is a resident, false otherwise.
     */
    public boolean isResident(String person) {
        return this.residents.contains(person);
    }

    /**
     * Displays the options available for interacting with the building, including house-specific options.
     * Overrides the showOptions() method in the Building class.
     */
    @Override
    public void showOptions() {
        super.showOptions(); // Display general building optionss
        System.out.println("House-specific options:");
        System.out.println("1. Move in a resident");
        System.out.println("2. Move out a resident");
    }

    /**
     * Navigates to the specified floor in the house, allowing movement between non-adjacent floors only if an elevator is present.
     * Throws an exception if not inside the house, if the specified floor is invalid, or if trying to navigate between non-adjacent floors without an elevator.
     *
     * @param floorNum The floor number to navigate to.
     */
    @Override
    public void goToFloor(int floorNum) {
        if (this.activeFloor == -1) {
            throw new RuntimeException("You are not inside this House. Must call enter() before navigating between floors.");
        }
        if (floorNum < 1 || floorNum > this.nFloors) {
            throw new RuntimeException("Invalid floor number. Valid range for this House is 1-" + this.nFloors + ".");
        }
        if (!hasElevator && Math.abs(floorNum - this.activeFloor) > 1) {
            throw new RuntimeException("Cannot navigate between non-adjacent floors without an elevator.");
        }
        System.out.println("You are now on floor #" + floorNum + " of " + this.name);
        this.activeFloor = floorNum;
    }

}
