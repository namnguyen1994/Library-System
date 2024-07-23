/** Name: Nam Nguyen
    Date: 4/12/2024
   
   Description:
   
    The purpose of this abstract class is to force a class to be extended due to it representing an object that is too broad. Thus this class will depend on it subclass to instantiate an object.
    This abstract class contain a constructor that would be invoke by the subclass to validate user input for ISBN, title, totalCopies and available Copies.
    Furthermore, this abstract class also contain abstract methods that will have to be override by it subclass.
*/
public abstract class LibraryItems {
    private int ISBN;
    private String title;
    private int totalCopies;
    private int availableCopies;
    private String checkoutHolder;
    private String dueDate;
    private static int numItems;

    //Special constructor that accept 4 parameters and invoke the set methods to validate the user input for ISBN, title, totalCopies and availableCopies.
    public LibraryItems(int ISBN, String title, int totalCopies, int availableCopies){
        setISBN(ISBN);
        setTitle(title);
        setTotalCopies(totalCopies);
        setAvailableCopies(availableCopies);
        dueDate = null;
        checkoutHolder = null;
        ++numItems;
    }
        
    //This section onward is for the accessors (GET) methods and it return the value associated with the variable name.
    public int getISBN(){
        return this.ISBN;
    }

    public String getTitle(){
        return this.title;
    }

    public int getTotalCopies(){
        return this.totalCopies;
    }

    public int getAvailableCopies(){
        return this.availableCopies;
    }

    public String getCheckoutHolder(){
        return this.checkoutHolder;
    }

    public String getDueDate(){
        return this.dueDate;
    }

    public static int getNumItems(){
        return numItems;
    }

    //This section onward is for the mutators (SET) methods, which take the user input and assign it to a named variable. It also validate to make sure values enter match certain condition in order to be assign to a named variable.
    public void setISBN(int ISBN){
        if(ISBN < 0){
            throw new IllegalArgumentException("The item ISBN must be provided and can't be negative or 0!");
        }
        this.ISBN = ISBN;
    }

    public void setTitle(String title){
        if(title == null || title.equals("")){
            throw new IllegalArgumentException("The title of the item must be provided!");
        }
        this.title = title;
    }

    public void setTotalCopies(int totalCopies){
        if(totalCopies < 0){
            throw new IllegalArgumentException("The total copies can't be negative!");
        }
        this.totalCopies = totalCopies;
    }

    public void setAvailableCopies(int availableCopies){
        if (availableCopies < 0 || availableCopies > totalCopies){
            throw new IllegalArgumentException("The available copies can't be negative or exceed the total copies!");
        }
        this.availableCopies = availableCopies;
    }

    public void setCheckoutHolder(String studentID){
        if (studentID.length() == 9 && studentID.indexOf('G') == 0){
            this.checkoutHolder = studentID;
        } else {
            throw new IllegalArgumentException("The Student G-ID must be provided at checkout!");
        }    
    }

    public void setDueDate (String dueDate){
        if(dueDate == null || dueDate.equals("") || dueDate.length() != 8){
            throw new IllegalArgumentException("The due date must be provided based on this format (MMDDYYYY)!");
        }
        this.dueDate = dueDate;
    }

    //special method that allow the user to decrease the number of availableCopies by one each time it get invoked, and update the availableCopies value.
    public int decreaseAvailableCopies(){
        int results = availableCopies - 1;
        return this.availableCopies = results;
    }

    //This section is for the abstract methods where it method body is not specified with the intention of having these abstract methods being override by the subclass.
    public abstract boolean matchedISBN(int ISBN);

    public abstract boolean matchedTitle(String title);

    //Special method that display all the information regarding a library items object
    public String toString(){
        return "ISBN: " + this.ISBN
        + " | Title: "+ this.title 
        + " | Total Copies: " + this.totalCopies
        + " | Available Copies: " + this.availableCopies
        + " | Checkout Holder: " + (this.checkoutHolder != null ? this.checkoutHolder : "N/A")
        + " | Due Date: " + (this.dueDate != null ? this.dueDate : "N/A");
    }
}