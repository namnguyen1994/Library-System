/** Name: Nam Nguyen
    Date: 4/12/2024
   
   Description:
   
   The purpose of this class is to create a Book object that demonstrate the concept of inheritance by using the user's input values for ISBN, title, totalCopies, availableCopies, author, edition and year.
   Once it receive the user input, this class will validate to ensure that the data meet certain criteria, otherwise it will inform the user to try again.
   Furthermore, this class contain many special methods, such as a method to determine whether the entered title or ISBN matched with the information in the system, and informing the user of the outcome.
   Lastly, this class will provide a toString method that will override the superclass method when invoke, and return the summary of all the book entered.
*/
public class Book extends LibraryItems {
    private String author;
    private String edition;
    private int year;

    //Special constructor that accept 7 parameters and invoke the superclass constructor, as well as validate the user input for author, edition and year.
    public Book(int ISBN, String title, int totalCopies, int availableCopies, String author, String edition, int year){
        super(ISBN, title, totalCopies, availableCopies);
        setAuthor(author);
        setEdition(edition);
        setYear(year);
    }

    public String getAuthor(){
        return this.author;
    }

    public String getEdition(){
        return this.edition;
    }

    public int getYear(){
        return this.year;
    }

    //This section onward is for the mutators (SET) methods, which take the user input and assign it to a named variable. It also validate to make sure values enter match certain condition in order to be assign to a named variable.
    public void setAuthor(String author){
        if(author == null || author.equals("")){
            throw new IllegalArgumentException("The author name must be provided!");
        }
        this.author = author;
    }

    public void setEdition(String edition){
        if(edition == null || edition.equals("")){
            throw new IllegalArgumentException("The book edition must be provided!");
        }
        this.edition = edition;
    }

    public void setYear(int year){
        if(year < 0 || year > 2024){
            throw new IllegalArgumentException("The published year can't be zero or greater than 2024!");
        }
        this.year = year;
    }
    
    //This special method will override the abstract method found in the LibraryItems superclass, and will determine whether the value being pass into it matched the set criteria.
    public boolean matchedISBN(int ISBN){
        if(this.getISBN() == ISBN){
            return true;
        }

        return false;
    }

    //This special method will override the abstract method found in the LibraryItems superclass, and will determine whether the value being pass into it matched the set criteria.
    public boolean matchedTitle(String title){
        if (this.getTitle().equalsIgnoreCase(title)){
            return true;
        }
        
        return false;
    }
    
    //Override method that return String representation of a Book.
    public String toString(){
        return super.toString() + " | Author: " + author + " | Edition: " + edition + " | Publishing Year: " + year;
    }

}
