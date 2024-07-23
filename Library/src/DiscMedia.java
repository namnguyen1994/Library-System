/** Name: Nam Nguyen
    Date: 4/12/2024
   
   Description:
   
   The purpose of this class is to create a Disc Media object that demonstrate the concept of inheritance by using the user's input values for ISBN, title, totalCopies, availableCopies, type and duration.
   Once it receive the user input, this class will validate to ensure that the data meet certain criteria, otherwise it will inform the user to try again.
   Furthermore, this class contain many special methods, such as a method to determine whether the entered title or ISBN matched with the information in the system, and informing the user of the outcome.
   Lastly, this class will provide a toString method that will override the superclass method when invoke, and return the summary of all the disc media entered.
*/
public class DiscMedia extends LibraryItems {
    private String type;
    private int duration;

    //Special constructor that accept 6 parameters and invoke the superclass constructor, as well as validate the user input for type and duration.
    public DiscMedia(int ISBN, String title, int totalCopies, int availableCopies, String type, int duration){
        super(ISBN, title, totalCopies, availableCopies);
        setType(type);
        setDuration(duration);
    }

    public String getType(){
        return this.type;
    }

    public int getDuration(){
        return this.duration;
    }

    //This section onward is for the mutators (SET) methods, which take the user input and assign it to a named variable. It also validate to make sure values enter match certain condition in order to be assign to a named variable.
    public void setType(String type){
        if(type.equalsIgnoreCase("CD") || type.equalsIgnoreCase("DVD") || type.equalsIgnoreCase("BLURAY")){
            this.type = type;
        } else {
            throw new IllegalArgumentException("The disc media type must be provided!");
        }
    }

    public void setDuration(int duration){
        if(duration <= 0){
            throw new IllegalArgumentException("The duration cant be negative or zero!");
        }
        this.duration = duration;
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

    //Override method that return String representation of a DiscMedia.
    public String toString(){
        return super.toString() + " | Media Type: " + type + " | Duration: " + duration;
    }

}