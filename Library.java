import java.util.Hashtable;

/**
 * Represents a library, which is a specific type of building for storing books.
 */
public class Library extends Building {
    private Hashtable<String, Boolean> collection;

    /**
     * Constructs a new Library object with the given parameters.
     *
     * @param name     The name of the library.
     * @param address  The address of the library.
     * @param nFloors  The number of floors in the library.
     */
    public Library(String name, String address, int nFloors) {
        super(name, address, nFloors);
        this.collection = new Hashtable<>();
    }

    /**
     * Adds a book title to the library's collection if it's not already present.
     *
     * @param title The title of the book to add.
     */
    public void addTitle(String title) {
        if (!collection.containsKey(title)) {
            collection.put(title, true);
        } else {
            System.out.println("The title is already in the collection.");
        }
    }

    /**
     * Removes a book title from the library's collection.
     *
     * @param title The title of the book to remove.
     * @return The title of the book removed, or null if the book was not found.
     */
    public String removeTitle(String title) {
        if (collection.remove(title) != null) {
            return title;
        }
        return null;
    }

    /**
     * Checks out a book from the library if it's available.
     *
     * @param title The title of the book to check out.
     */
    public void checkOut(String title) {
        if (collection.containsKey(title)) {
            if (collection.get(title)) {
                collection.put(title, false);
            } else {
                System.out.println("The book is already checked out.");
            }
        } else {
            System.out.println("The book is not in the collection.");
        }
    }

    /**
     * Checks out a book from the library if it's available, associating it with the specified person.
     *
     * @param title The title of the book to check out.
     * @param person The name of the person checking out the book.
     */
    public void checkOut(String title, String person) {
        if (collection.containsKey(title)) {
            if (collection.get(title)) {
                collection.put(title, false);
                System.out.println(title + " has been checked out by " + person);
            } else {
                System.out.println("The book is already checked out.");
            }
        } else {
            System.out.println("The book is not in the collection.");
        }
    }

    /**
     * Returns a book to the library if it was checked out.
     *
     * @param title The title of the book to return.
     */
    public void returnBook(String title) {
        if (collection.containsKey(title)) {
            if (!collection.get(title)) {
                collection.put(title, true);
            } else {
                System.out.println("The book is already returned.");
            }
        } else {
            System.out.println("The book is not in the collection.");
        }
    }

    /**
     * Returns a book to the library if it was checked out by the specified person.
     *
     * @param title  The title of the book to return.
     * @param person The name of the person returning the book.
     */
    public void returnBook(String title, String person) {
        boolean bookFound = false;
        for (String bookTitle : collection.keySet()) {
            if (bookTitle.equals(title) && !collection.get(bookTitle)) {
                collection.put(bookTitle, true);
                System.out.println(person + " returned " + title + " to the library.");
                bookFound = true;
                break;
            }
        }
        if (!bookFound) {
            System.out.println("Either the book was not checked out by " + person + " or it's not in the collection.");
        }
    }


    /**
     * Checks if the library contains a specific book title.
     *
     * @param title The title of the book to check.
     * @return true if the book is in the collection, false otherwise.
     */
    public boolean containsTitle(String title) {
        return collection.containsKey(title);
    }

    /**
     * Checks if a book is available for checkout.
     *
     * @param title The title of the book to check.
     * @return true if the book is available, false otherwise.
     */
    public boolean isAvailable(String title) {
        return collection.getOrDefault(title, false);
    }

    /**
     * Prints the library's collection with checkout status.
     */
    public void printCollection() {
        for (String title : collection.keySet()) {
            System.out.println(title + " - Available: " + collection.get(title));
        }
    }

    /**
     * Displays the options available for interacting with the building, including library-specific options.
     * Overrides the showOptions() method in the Building class.
     */
    @Override
    public void showOptions() {
        super.showOptions(); // Display general building options
        System.out.println("Library-specific options:");
        System.out.println("1. Check out a book");
        System.out.println("2. Return a book");
    }

    /**
    * Navigates to the specified floor in the library, allowing movement between non-adjacent floors.
    * Throws an exception if not inside the library or if the specified floor is invalid.
     *
     * @param floorNum The floor number to navigate to.
     */
    @Override
    public void goToFloor(int floorNum) {
        if (this.activeFloor == -1) {
            throw new RuntimeException("You are not inside this Library. Must call enter() before navigating between floors.");
        }
        if (floorNum < 1 || floorNum > this.nFloors) {
            throw new RuntimeException("Invalid floor number. Valid range for this Library is 1-" + this.nFloors + ".");
        }
        System.out.println("You are now on floor #" + floorNum + " of " + this.name);
        this.activeFloor = floorNum;
    }

}
