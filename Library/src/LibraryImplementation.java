/** Name: Nam Nguyen
    Date: 4/12/2024
   
   Description:
   
   The purpose of this main class is to give user various options and depend on the option chosen, it may ask user to determine if they want to add a Book or a Disc Media, and ask for user inputs to general information such as ISBN, title, totalCopies, availableCopies, and either author, edition and year or type and duration depend on their choice for Book or Disc Media respectively.
   It also have an option that let user search for an item or allow them to checkout Book.  In addition, the program will also inform user whenever it detect that the user entered invalid data.
   Lastly, depend on the user choices, it will be able to print out a summary of the library and all the items in it.
*/
import javax.swing.JOptionPane;

public class LibraryImplementation {
    public static void main(String[] args) {
        final int MAX_ITEMS = 6000;
        LibraryItems [] aItems = new LibraryItems [MAX_ITEMS];
        Library aLibrary = null;

        //Loop that will ask user to create a library instance, and unless the user provide it with valid information, the program won't let them move on.
        do{
            aLibrary = createLibrary();
        }while(aLibrary == null); 

        boolean quit = false;
        int menuChoice = getMenuOption();

        //Start of the switch statement that enable user to choose from various options
        while (!quit) {
            switch(menuChoice) {
                case 1:
                    addLibraryItem(aItems);
                    break;
                    
                case 2:
                    searchItem(aItems);
                    break;
                    
                case 3:
                    checkOutBook(aItems);
                    break;

                case 4:
                    printLibraryInfo(aLibrary, aItems);
                    break;

                case 5:
                    quit = true;
                    JOptionPane.showMessageDialog(null, "Thank you! Hope you come back soon!");
                    break;
                default:
                    throw new RuntimeException("Unknown error in menu choice");
            }
            menuChoice = getMenuOption();
        }
    }

    public static int getMenuOption() {
        int menuChoice;
        
        do {
            try {
                menuChoice = Integer.parseInt(JOptionPane.showInputDialog(
                    "**Welcome to the library\n" 
                    + "\n Please choose one of the following option: "
                    + "\n1. Add Library Item"
                    + "\n2. Search Library Item"
                    + "\n3. Checkout Book"
                    + "\n4. Print Library Report"
                    + "\n5. Exit"
                ));
            }
            catch (NumberFormatException e) {
                menuChoice = 0;
            }
            if (menuChoice < 1 || menuChoice > 5) {
                JOptionPane.showMessageDialog(null, "Invalid choice. Please enter a valid menu option.");
            }
        } while (menuChoice < 1 || menuChoice > 5);
        
        return menuChoice;
    }

    //Invoke by the main method to create a instance of a library. It will validate to see whether user enter valid information and informing the user if it detect any errors.
    public static Library createLibrary(){
        Library aLibrary = null;
        try {
            String name = JOptionPane.showInputDialog("Enter library name: ");
            String address = JOptionPane.showInputDialog("Enter library address: ");
            String librarian = JOptionPane.showInputDialog("Enter the librarian info: ");
            
            aLibrary = new Library(name, address, librarian);
        } catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(null, "Unable to create a library instance! Error detect: " + e.getMessage());;
        }

        return aLibrary;
    }

    //Invoke by the main method to allow user to choose whether they want to add a book or a disc media
    public static void addLibraryItem(LibraryItems [] aItems){
        String choice = JOptionPane.showInputDialog("Please pick one of these options to add (1 or 2): 1. Book, 2. Disc Media");

        switch (choice) {
            case "1":
                createBook(aItems);
                break;
            
            case "2":
                createDiscMedia(aItems);
                break;

            default:
                JOptionPane.showMessageDialog(null, "Please enter a valid choice!");
                break;
        }
    }

    //Invoke by the addLibraryItem method that enable user to enter a Book information while the library still have space, and prompt user if they want to keep adding more.
    public static void createBook(LibraryItems[] aItems){
        int currentSize = LibraryItems.getNumItems();

        do{
            try{;
                int ISBN = Integer.parseInt(JOptionPane.showInputDialog("Please enter the book ISBN: "));
                String title = JOptionPane.showInputDialog("Please enter the title of the book: ");
                int totalCopies = Integer.parseInt(JOptionPane.showInputDialog("Please enter the total copies of this book: "));
                int availableCopies = Integer.parseInt(JOptionPane.showInputDialog("Please enter the number of available copies of this book: "));
                String author = JOptionPane.showInputDialog("Please enter the author of this book: ");
                String edition = JOptionPane.showInputDialog("Please enter the edition of this book: ");
                int year = Integer.parseInt(JOptionPane.showInputDialog("Please enter the published year of this book: "));

                aItems[currentSize] = new Book(ISBN, title, totalCopies, availableCopies, author, edition, year);
                currentSize = LibraryItems.getNumItems();
            }catch (NullPointerException ez){
                JOptionPane.showMessageDialog(null, "Item wasn't successfully added! Error detecting: " + ez.getMessage());
            }catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Item wasn't successfully added! Error detecting: " + ex.getMessage());
            }catch(IllegalArgumentException e){
                JOptionPane.showMessageDialog(null, "Item wasn't successfully added! Error detecting: " + e.getMessage());
            }
        } while (currentSize < aItems.length && JOptionPane.showConfirmDialog(null, "Would you like to enter more item?", "New Inquiry", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
    }

    //Invoke by the addLibraryItem method that enable user to enter a Disc Media information while the library still have space, and prompt the user if they want to continue adding more disc media or not.
    public static void createDiscMedia(LibraryItems[] aItems){
        int currentSize = LibraryItems.getNumItems();

        do{
            try{
                int ISBN = Integer.parseInt(JOptionPane.showInputDialog("Please enter the disc media ISBN: "));
                String title = JOptionPane.showInputDialog("Please enter the title of the disc media: ");
                int totalCopies = Integer.parseInt(JOptionPane.showInputDialog("Please enter the total copies of this disc media: "));
                int availableCopies = Integer.parseInt(JOptionPane.showInputDialog("Please enter the number of available copies of this disc media: "));
                String types = JOptionPane.showInputDialog("Please enter the media types (CD/DVD/BLURAY): ");
                int duration = Integer.parseInt(JOptionPane.showInputDialog("Please enter the duration: "));

                aItems[currentSize] = new DiscMedia(ISBN, title, totalCopies, availableCopies, types, duration);
                currentSize = LibraryItems.getNumItems();
            }catch (NullPointerException ez){
                JOptionPane.showMessageDialog(null, "Item wasn't successfully added! Error detecting: " + ez.getMessage());
            }catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Item wasn't successfully added! Error detecting: " + ex.getMessage());
            }catch(IllegalArgumentException e){
                JOptionPane.showMessageDialog(null, "Item wasn't successfully added! Error detecting: " + e.getMessage());
            }
        } while (currentSize < aItems.length && JOptionPane.showConfirmDialog(null, "Would you like to enter more item?", "New Inquiry", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
    }

    //Invoke by the main method that enable user to search the library system by either using a item title or ISBN, and determine whether there is a library items that match the user search input for title and ISBN. If there is no match, the program will inform the user.
    public static void searchItem(LibraryItems[] aItems){
        String results = "Search History Report: \n" ;
        int currentSize = LibraryItems.getNumItems();

        try{
            int searchChoice = Integer.parseInt(JOptionPane.showInputDialog("**Please choose one of the following option: (1 or 2) **"
                + "\n1. Search by title"
                + "\n2. Search by ISBN"
            ));
            boolean match = false;

            switch(searchChoice){
                case 1:
                    String searchTitle = JOptionPane.showInputDialog("Please enter the title of the item to search: ");
                    for (int i = 0; i < currentSize; i++){
                        if(aItems[i].matchedTitle(searchTitle)){
                            results += "Matched Item: " + aItems[i].toString(); 
                            match = true;
                        }
                    }
                    if (match){
                        JOptionPane.showMessageDialog(null, results);
                    } else {
                        JOptionPane.showMessageDialog(null, "There is no item matching the search criteria");
                    }
                    break;
                
                case 2:
                    int searchISBN = Integer.parseInt(JOptionPane.showInputDialog("Please enter the ISBN to search: "));
                    for (int i = 0; i < currentSize; i++){
                        if(aItems[i].matchedISBN(searchISBN)){
                            results += "Matched Item: " + aItems[i].toString(); 
                            match = true;
                        }
                    }
                    if (match){
                        JOptionPane.showMessageDialog(null, results);
                    } else {
                        JOptionPane.showMessageDialog(null, "There is no item matching the search criteria");
                    }
                    break;
                
                default:
                    JOptionPane.showMessageDialog(null, "Please enter a valid choice!");
                    break;
            }
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Unable to search: " + e.getMessage());
        }
    
    }

    /*Invoke by the main method that allow user to checkout Book only (not Disc Media). This method will allow user to search a book using ISBN, and determine whether the ISBN use is a Book or a Disc Media. 
    If it a Book, it will search to see whether there are available copies and ask user if they want to check out and provide receipt otherwise it will inform the user that there is no book available.
    */
    public static void checkOutBook(LibraryItems[] aItems){
        String results = "Checkout Report: \n" ;
        int currentSize = LibraryItems.getNumItems();
        boolean foundBook = false;

        try{
            int searchISBN = Integer.parseInt(JOptionPane.showInputDialog("Please enter the ISBN to search: "));

            for (int i = 0; i < currentSize; i++){
                if (aItems[i].matchedISBN(searchISBN) && aItems[i] instanceof Book){
                    foundBook = true;
                    if(aItems[i].getAvailableCopies() > 0){
                        String available = JOptionPane.showInputDialog("There are copies of the book availalable, would you like to checkout (Y/N):");
                        if(available.equalsIgnoreCase("Y")){
                            String studentID = JOptionPane.showInputDialog("Please enter your student G-ID based on this format: G######## to begin checkout) ");
                            String dueDate = JOptionPane.showInputDialog("Please enter the due date based on this format (MMDDYYYY): ");
                            aItems[i].setCheckoutHolder(studentID);
                            aItems[i].setDueDate(dueDate);
                            results += "A copy of " + aItems[i].getTitle() + " checkout successfully by " + aItems[i].getCheckoutHolder() + " and will be due on " + aItems[i].getDueDate() + ". Remaining copies available: " + aItems[i].decreaseAvailableCopies(); 
                        } else {
                            JOptionPane.showMessageDialog(null, "No book was checkout");
                        }  
                    } else {
                        JOptionPane.showMessageDialog(null, "No more copies available to checkout. Please check back next time!");
                    }
                }
            }

            if(foundBook){
                JOptionPane.showMessageDialog(null, results);
            } else {
                JOptionPane.showMessageDialog(null, "Either the ISBN enter does not match or the search items is not a book media so can't be checkout!");
            }
        }catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Unable to search: " + ex.getMessage());
        }catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(null, "Student ID or due date is invalid! Error detecting: " + e.getMessage());
        } 
    }
    
    //Invoke by the main method to print out the library information and all the library items.
    public static void printLibraryInfo(Library aLibrary, LibraryItems[] aItems){
        String report = "**Thank you for using the Library system**" +"\n\n";
        int currentSize = LibraryItems.getNumItems();
        report += "Library Infos: " + "\n";
        report += aLibrary.toString() + "\nTotal Books: " + currentSize + "\n\n";
        report += "Book/Disc Media Information: " + "\n";

        for (int i = 0; i < currentSize; i++){
            report += aItems[i].toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, report);
    }

}