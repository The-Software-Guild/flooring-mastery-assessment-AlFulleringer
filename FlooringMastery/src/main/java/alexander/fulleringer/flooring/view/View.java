/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexander.fulleringer.flooring.view;

import alexander.fulleringer.flooring.exceptions.AuditorFileAccessException;
import alexander.fulleringer.flooring.exceptions.DateFormatException;
import alexander.fulleringer.flooring.model.Order;
import alexander.fulleringer.flooring.model.Product;
import alexander.fulleringer.flooring.model.TaxState;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 *
 * @author Alex
 */
public class View {
    private UserIO io;
    private final int NUM_MAIN_MENU_OPTIONS = 6;
    private final int NUM_EDIT_MENU_OPTIONS = 5;
    
    public View(UserIO io){
        this.io=io;
    }

    public View() {
        io = new UserIOConsoleImpl();
    }
    
    public int printMenuGetSelection(){
        this.printMenu();
        return this.getMenuChoice(NUM_MAIN_MENU_OPTIONS);
    }
    public int printEditMenuGetSelection(){
        this.printEditOrderMenu();
        return this.getMenuChoice(NUM_EDIT_MENU_OPTIONS);
    }
    /**
     * This function asks a user for input to fill out the required fields to make and return a new DVD
     *
     * @return
     */
//    public DVD getNewDVD(){
//        DVD newDVD = new DVD(io.readString("Please enter the DVD title"));
//
//        newDVD.setDirectorName(io.readString("Please enter the director's name"));
//        newDVD.setReleaseDate(io.readString("Please enter the release date"));
//        newDVD.setStudio(io.readString("Please enter the Studio name"));
//        newDVD.setMpaaRating(io.readString("Please enter the MPAA Rating"));
//        newDVD.setMiscInfo(io.readString("Please enter any additional information of note"));
//
//        return newDVD;
//    }
//    public int displayAddFundsMenuGetSelection(){
//        this.printEditOrderMenu();
//        int choice = this.getMenuChoice(NUM_COIN_MENU_OPTIONS);
//        return choice;
//    }
    
    public void printMenu(){
        System.out.println("\n**************************************");
        System.out.println("Welcome to your Flooring Application!");
        System.out.println("Your options are as follows:");
        
        System.out.println("1. Display Orders");
        System.out.println("2. Add an Order");
        System.out.println("3. Edit an Order");
        System.out.println("4. Remove an Order");
        System.out.println("5. Export All Data");
        System.out.println("6. Exit Program");
    }

//
    public void displayOrders(List<Order> orders){
        io.print("\nHere are the orders you asked for");
        for(Order order : orders){
            displayOrder(order);
        }
        io.readString("Press enter to continue");
    }
    public void displayOrder(Order order){
        if (order != null){
            io.print(order.toString());
        }
        else{
            io.print("That is not something we offer");
        }
    }
    
    public void displayDisplayAllBanner(){
        System.out.println("Here is our current stock!");
    }
    
    public int getMenuChoice(int numOptions){
        return io.readInt("Please select one of the above choices.",1,numOptions);
    }
    
//    public void displayFindDVDCompletion() {
//        io.readString("Press enter to continue\n");
//    }
//
//    public void displayDropDVDBanner() {
//        io.print("--- Drop a DVD ---");
//    }
    
    public void displayDropSuccessBanner() {
        io.readString("Please hit enter to continue.\n");
    }
    
    public void displayDisplayInventorySuccess() {
        io.readString("Inventory display complete.\nPlease hit enter to continue.\n");
    }
    
    public void displayDropResult(Order toDrop) {
        if (toDrop!=null){
            displayOrder(toDrop);
            io.print("Order has been removed.");
        }
        else{
            io.print("No such order exists");
        }
        
        io.readString("Please press enter to continue");
    }
    
    
    public void printErrorMessage(Exception e){
        io.print("----------ERROR----------");
        io.print(e.getMessage());
    }
    
    public void displayGoodBye() {
        io.print("Good Bye!");
    }
    
    public void displayUnkownCommandBanner() {
        io.print("UNKNOWN COMMAND");
    }

    private void printEditOrderMenu() {
        io.print("Which information would you like to edit?");
        io.print("Your options are as follows:");
        
        io.print("1. Customer Name");
        io.print("2. State");
        io.print("3. Product Type");
        io.print("4. Area");
        io.print("5. Return to main menu");
    }

    public void displayError(Exception e) {
        io.print("---ERROR---");
        io.print(e.getMessage());
              
    }
//
//    public String getItemSelection(List<Item> inventory) {
//        
//        String itemId;
//        int option=1;
//        for (Item item : inventory){
//            io.print("option " + option + " is: " + item.getName());
//            option++;
//        }
//        int choice = io.readInt("Which # would you like?", 1, inventory.size());
//        
//        return inventory.get(choice-1).getName();
//    }
    

    
    public void displayStates(List<TaxState> states){
        io.print("We currently serve the following states:");
        for(TaxState state : states){
            io.print(state.toString());
        }
    }
    public void displayAllProducts(List<Product> products){
        io.print("We currently offer the following products:");
        for(Product product : products){
            io.print(product.toString());
        }
        
    }

    public LocalDate getDate() {
        io.print("Please input the date whose orders you would like to review");
        String s;
        LocalDate date;
        s = io.readString("The date should be of format: YYYY-MM-DD");
        try{
            date = LocalDate.parse(s, DateTimeFormatter.ISO_DATE);
            return date;
        }
        catch(DateTimeParseException e){
            DateFormatException  exception = new DateFormatException("That is not a valid date format, please try again."); 
            displayError(exception);
            return getDate();
        }
        
    }

    public String parseString(String prompt) {
        return io.readString(prompt);
    }

    public void displayStateSelectionBanner() {
        io.print("Please choose one of the following states using their abbreviation!");
    }

    public void displayBadStateBanner() {
        io.print("That is not a state we provide service in.");
    }

    public void displaySuccess(String prompt) {
        io.readString(prompt + "\nPress Enter to continue");
    }

    public BigDecimal parseBigDecimal(){
        BigDecimal toReturn;
        String toUse = this.parseString("Please enter a decimal value.");
        try{
            toReturn = new BigDecimal(toUse);
        }
        catch(NumberFormatException e){
            io.print("That's not a valid decimal :(");
            toReturn = parseBigDecimal();
        }
        return toReturn;
    }
    public LocalDate getNewOrderDate() {
        io.print("Please input the date you would like, it must be in the future!");
        String s;
        LocalDate date;
        s = io.readString("The date should be of format: YYYY-MM-DD");
        try{
            date = LocalDate.parse(s, DateTimeFormatter.ISO_DATE);
            return date;
        }
        catch(DateTimeParseException e){
            DateFormatException  exception = new DateFormatException("That is not a valid date format, please try again."); 
            displayError(exception);
            return getDate();
        }
            }

    public void displayProductSelectionBanner() {
        io.print("Choose one of our products by entering its product type!");
    }

    public void displayBadProductBanner() {
        io.print("That is not a product we provide :(");
    }

    public void displayRequestAreaPrompt() {
        io.print("Please enter a positive decimal value for the area!");
    }

    public int parseOrderNumber() {
        return io.readInt("Please input your order number");

    }

    public String getEditCustomerName(Order o) {

        io.print("Your customer's name is currently: " + o.getCustomerName());
        
        return io.readString("Enter a new name or simply press enter to not change the name");
    }

    public void displayInvalidInputBanner() {
        io.readString("That is not a valid input in this scenario");
    }

    public String getEditState(Order theOrder, List<TaxState> states) {
        io.print("Your current state is: " + theOrder.getStateAbbr());
        this.displayStates(states);
        return io.readString("Please choose one of the above states by abbreviation\n"
                + "Or press enter to not make a change");
    }

    public String getEditProduct(Order theOrder, List<Product> allProducts) {
        io.print("Your current product is: " + theOrder.getProductType());
        this.displayAllProducts(allProducts);
        return io.readString("Please choose one of the above products\n"
                + "Or press enter to not make a change");    }

    public String getEditArea(Order theOrder) {
        io.print("Your current area is: " + theOrder.getArea().toString());
        return io.readString("Please enter the new area\n"
                + "Or press enter to not make a change");    
    }

    public boolean verifyOrderEdit(Order theOrder) {
        io.print("Your order: " + theOrder.toString());
        String s = io.readString("Would you like to persist your edits? Please enter yes or no");
        if(s.equals("yes")){
            return true;
        }
        if (s.equals("no")){
            return false;
        }
        else{
            return verifyOrderEdit(theOrder);
        }
        
    }

    public boolean confirmDrop(Order theOrder) {
        io.print("Your order: " + theOrder.toString());
        String s = io.readString("Would you like to remove this order? Please enter yes or no");
        if(s.equals("yes")){
            return true;
        }
        if (s.equals("no")){
            return false;
        }
        else{
            return confirmDrop(theOrder);
        }
    }
    
    
}
