/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexander.fulleringer.flooring.view;

import alexander.fulleringer.flooring.exceptions.AuditorFileAccessException;
import alexander.fulleringer.flooring.model.Order;
import alexander.fulleringer.flooring.model.Product;
import alexander.fulleringer.flooring.model.TaxState;
import java.math.BigDecimal;
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
    
    public int printMenuGetSelection(){
        this.printMenu();
        return this.getMenuChoice(NUM_MAIN_MENU_OPTIONS);
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
        io.print("\nHere is what we have in our Vending Machine!");
        for(Order order : orders){
            displayOrder(order);
        }
        
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
        System.out.println("Which coin would you like to add?");
        System.out.println("Your options are as follows:");
        
        System.out.println("1. Penny");
        System.out.println("2. Nickel");
        System.out.println("3. Dime");
        System.out.println("4. Quarter");
        System.out.println("5. Exit without inserting a coin");
    }

    public void displayError(AuditorFileAccessException e) {
        System.out.println("---ERROR---");
        System.out.println(e.getMessage());
              
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
    
    public void displayFunds(BigDecimal funds){
        io.print("Funds available: " + funds.toString());
    }

    public void printPurchaseSuccess(String itemId, BigDecimal funds) {
        io.print("You have successfully purchased a " + itemId + ". You have " + funds.toString() +"$ remaining.");
    }
    
    public void displayStates(List<TaxState> states){
        System.out.println("We currently serve the following states:");
        for(TaxState state : states){
            System.out.println(state.toString());
        }
    }
    public void displayProductTypes(List<Product> products){
        System.out.println("We currently offer the following floors:");
        for(Product product : products){
            System.out.println(product.toString());
        }
        
    }
    
}
