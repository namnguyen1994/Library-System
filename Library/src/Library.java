/** Name: Nam Nguyen
    Date: 4/12/2024
   
   Description:
   
   The purpose of this class is to create a library object using the user's input values for name, address and librarian information.
   Once it receive the user inputs, this class will validate to ensure that the data meet certain criteria, otherwise it will inform the user to try again.
   Lastly, this class will provide a toString method that will return the summary of the library information.
*/

public class Library {
    private String name;
    private String address;
    private String librarian;
    private int studyRoom;
    public static final int MAX_ROOM = 70;

    //Special constructor that accept 3 parameters and invoke the set methods to validate the user input for name, address and librarian information.
    public Library(String name, String address, String librarian){
        setName(name);
        setAddress(address);
        setLibrarian(librarian);
        this.studyRoom = MAX_ROOM;
    }
    
    //This section onward is for the accessors (GET) methods and it return the value associated with the variable name.
    public String getName(){
        return this.name;
    }
    
    public String getAddress(){
        return this.address;
    }
    
    public String getLibrarian(){
        return this.librarian;
    }

    public int getStudyRoom(){
        return this.studyRoom;
    }

    //This section onward is for the mutators (SET) methods, which take the user input and assign it to a named variable. It also validate to make sure values enter match certain condition in order to be assign to a named variable.
    public void setName(String name){
        if(name == null || name.equals("")){
            throw new IllegalArgumentException("The name of the library must be provided!");
        }
        this.name = name;
    }
    
    public void setAddress(String address){
        if(address == null || address.equals("")){
            throw new IllegalArgumentException("The address of the library must be provided!");
        }
        this.address = address;
        }
    
    public void setLibrarian(String librarian){
        if(librarian == null || librarian.equals("")){
            throw new IllegalArgumentException("The name of the librarian must be provided!");
        }
        this.librarian = librarian;
    }

    //Special method that display all the information regarding a library object
    public String toString(){
        return "Library Name: " + this.getName()  
        + "\nAddress: " + this.getAddress() 
        + "\nLibrarian Info: " + this.getLibrarian()
        + "\nTotal Study Room: " + this.studyRoom;
    }
}