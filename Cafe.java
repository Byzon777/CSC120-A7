/**
 * Represents a cafe - type of building where coffee is being sold.
 */
public class Cafe extends Building {

    private int nCoffeeOunces; // The number of ounces of coffee remaining in inventory
    private int nSugarPackets; // The number of sugar packets remaining in inventory
    private int nCreams; // The number of "splashes" of cream remaining in inventory
    private int nCups; // The number of cups remaining in inventory

    // defined specifically for the overloaded restock() method
    private int initialCoffeeOunces; // The initial number of ounces of coffee in inventory
    private int initialSugarPackets; // The initial number of sugar packets in inventory
    private int initialCreams; //The initial number of "splashes" of cream in inventory
    private int initialCups; // The initial number of cups in inventory

    /**
     * Constructs a new Cafe object with the given parameters.
     *
     * @param name               The name of the cafe.
     * @param address            The address of the cafe.
     * @param nFloors            The number of floors in the cafe.
     * @param initialCoffeeOunces The initial amount of coffee in ounces.
     * @param initialSugarPackets The initial number of sugar packets.
     * @param initialCreams       The initial number of cream "splashes".
     * @param initialCups         The initial number of cups.
     */
    public Cafe(String name, String address, int nFloors, int initialCoffeeOunces, int initialSugarPackets,
                int initialCreams, int initialCups) {
        super(name, address, nFloors);
        this.nCoffeeOunces = initialCoffeeOunces;
        this.nSugarPackets = initialSugarPackets;
        this.nCreams = initialCreams;
        this.nCups = initialCups;
        this.initialCoffeeOunces = initialCoffeeOunces;
        this.initialSugarPackets = initialSugarPackets;
        this.initialCreams = initialCreams;
        this.initialCups = initialCups;
    }

    /**
     * Sells a cup of coffee with the specified size and ingredients.
     *
     * @param size          The size of the coffee in ounces.
     * @param nSugarPackets The number of sugar packets.
     * @param nCreams       The number of cream "splashes".
     */
    public void sellCoffee(int size, int nSugarPackets, int nCreams) {
        if (nCups > 0) { // Check if there are available cups before selling coffee
            if (size <= nCoffeeOunces && nSugarPackets <= this.nSugarPackets && nCreams <= this.nCreams) {
                this.nCoffeeOunces -= size;
                this.nSugarPackets -= nSugarPackets;
                this.nCreams -= nCreams;
                this.nCups--;
                System.out.println("Coffee sold successfully!");
            } else {
                System.out.println("Insufficient inventory to sell coffee.");
                restock(size, nSugarPackets, nCreams, 1); // Restock with one cup
            }
        } else {
            System.out.println("No cups available to sell coffee.");
        }
    }

    /**
     * Sells a cup of coffee with the specified size.
     *
     * @param size          The size of the coffee in ounces.
     */
    public void sellCoffee(int size) {
        if (nCups > 0) { // Check if there are available cups before selling coffee
            if (size <= nCoffeeOunces && nSugarPackets <= 2 && nCreams <= 2) {
                this.nCoffeeOunces -= size;
                this.nSugarPackets -= 2;
                this.nCreams -= 2;
                this.nCups--;
                System.out.println("Coffee sold successfully!");
            } else {
                System.out.println("Insufficient inventory to sell coffee.");
                restock(size, nSugarPackets, nCreams, 1); // Restock with one cup
            }
        } else {
            System.out.println("No cups available to sell coffee.");
        }
    }

    /**
     * Restocks ingredients for coffee with the specified number of items.
     *
     * @param nCoffeeOunces         The number of the coffee ounces brough to cafe.
     * @param nSugarPackets         The number of the sugar packets brough to cafe.
     * @param nCreams               The number of the cream packets brough to cafe.
     * @param nCups                 The number of the cups brough to cafe.
     */
    private void restock(int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups) {
        this.nCoffeeOunces += nCoffeeOunces;
        this.nSugarPackets += nSugarPackets;
        this.nCreams += nCreams;
        this.nCups += nCups;
    }
    /**
     *
     * Restocks ingredients for coffee with the inital number of items.
     */
    private void restock() {
        this.nCoffeeOunces = initialCoffeeOunces;
        this.nSugarPackets = initialSugarPackets;
        this.nCreams = initialCreams;
        this.nCups = initialCups;
    }

    /**
     * Displays the options available for interacting with the building, including cafe-specific options.
     * Overrides the showOptions() method in the Building class.
     */
    @Override
    public void showOptions() {
        super.showOptions(); // Display general building options
        System.out.println("Cafe-specific options:");
        System.out.println("1. Buy coffe");
    }

    /**
    * Navigates to the specified floor in the cafe.
    * Throws an exception if not inside the library or if the specified floor is more than 1.
     *
     * @param floorNum The floor number to navigate to.
     */
    @Override
    public void goToFloor(int floorNum) {
        if (this.activeFloor == -1) {
            throw new RuntimeException("You are not inside this Cafe. Must call enter() before navigating between floors.");
        }
        if (floorNum > 1) {
            throw new RuntimeException("Invalid floor number. You are only allowed to be on the first floor");
        }
        System.out.println("You are now on floor #" + floorNum + " of " + this.name);
        this.activeFloor = floorNum;
    }
}
