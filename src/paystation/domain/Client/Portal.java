/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain.Client;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import paystation.domain.*;
import paystation.domain.RateStrategies.AlternatingRateStrategy;
import paystation.domain.RateStrategies.LinearRateStrategy;
import paystation.domain.RateStrategies.ProgressiveRateStrategy;
import paystation.domain.RateStrategies.RateStrategy;

/**
 *
 * @author silvia
 */
public class Portal {
   
    private static Scanner keyb = new Scanner(System.in);
    private static String userInput;
    private static PayStation clientUser = new PayStationImpl();
    
    private static RateStrategy currentRateStrategy = new LinearRateStrategy();
    
    public static void main(String[] args) {
        boolean on = true;
        while(on) {
            showMenu();
            boolean userChoiceValid = getUserMenuChoice();
            System.out.println("userchoice" + userChoiceValid);
            if(!userChoiceValid) {
               break;
            }
            System.out.println("not broken yet!"+ userChoiceValid);
        }
        System.out.println("Good bye!");
    }
    
    public static void showMenu() {
        System.out.println("PayStation Menu");
        System.out.println("Please selectfrom the menu below:");
        System.out.println("\t1.) Deposit Coins"
            + "\n\t2.) Display"
            + "\n\t3.) Buy Ticket"
            + "\n\t4.) Cancel"
            + "\n\t5.) Change Rate Strategy"
            + "\n\t6.) Shutdown");
    }
    
    public static boolean getUserMenuChoice() {
        userInput = keyb.nextLine();
        System.out.println(userInput);
        switch (userInput) {
            case "1":
                //System.out.println("1");
                startAddPayment();
                return true;
            case "2":
                //System.out.println("2");
                displayMinutes();
                return true;
            case "3":
                //System.out.println("3");
                buyTickets();
                return true;
            case "4":
//                System.out.println("4");
                cancelTransaction();
                return true;
             case "5":
//                System.out.println("5");
                changeRateStrategy();
                 System.out.println("changed");
                 return true;
            case "6":
//                System.out.println("Shut Down");
                return false;
            default:
//                System.out.println("Default"); 
                return true;
        }
    }
    
    public static void startAddPayment(){
        System.out.println("Insert the amount of coins you would like to deposit");
        boolean notFinishedInsertingCoins = true;
        
        while(notFinishedInsertingCoins) {
            System.out.println("Insert a valid coin amount (5, 10, or 25 cents) one a time. When done, type 'exit'");
            String userCoinInput = keyb.nextLine();
            try {
                
                switch (userCoinInput) {
                    case "5":
                        clientUser.addPayment(5);
                        break;
                    case "10":
                        clientUser.addPayment(10);
                        break;
                    case "25":
                        clientUser.addPayment(25);
                        break;
                    case "exit":
                        notFinishedInsertingCoins = false;
                        break;
                    
                    default:
                        System.out.println("Not a valid input");
                        break;
                }
            } catch (IllegalCoinException e) {
                System.out.println(e);
            }
           // System.out.println("Coins inserted thus far: " + clientUser.getInsertedCoins());
        }
    }
    
    public static void displayMinutes(){
        System.out.println("Displaying Minutes Bought: " + clientUser.readDisplay());
    }
    
    public static void buyTickets(){
        System.out.println("Buying Ticket");
        
        Date top = new Date();
        double time, total = 0;
     
        SimpleDateFormat fmt = new SimpleDateFormat("E MM/dd/yyyy hh:mm a");
        System.out.println("Here is your receipt:\n");
        System.out.println("Time of purchase: " + fmt.format(top));
        System.out.println("Time bought: " + (time = clientUser.buy().value()) + " minutes");
        System.out.println("");
       
    }
    
    public static void cancelTransaction(){
        System.out.println("Cancelling Transaction");
        clientUser.cancel();
    }
    
    public static void changeRateStrategy(){
        System.out.println("Changing Rate Strategy");
        
        //int userInput2;
        String userInput2 = "";
        boolean finished = false;
        while (!finished) {
            System.out.println("Select a rate strategy: ");
            System.out.println("\t1.) Linear Rate"
                    + "\n\t2.) Progressive Rate"
                    + "\n\t3.) Alternating Rate (Progressive rate on weekdays and"
                    + " linear rate on weekends)"
                    + "\n\t4.) Cancel");
//            userInput2 = keyb.nextInt();
            userInput2 = keyb.nextLine();
//            if (userInput2 == 1) {
            if (userInput2.equals("1")) {
                System.out.println("linrate");
                clientUser.setRate(new LinearRateStrategy());
                System.out.println("donezo");
                finished = true;
//            } else if (userInput2 == 2) {
            } else if (userInput2.equals("2")) {
                  System.out.println("prograte");
                    clientUser.setRate(new ProgressiveRateStrategy());
                    finished = true;
//            }  else if (userInput2 == 3) {
            } else if (userInput2.equals("3")) {
                   System.out.println("altrate");
                    clientUser.setRate(new AlternatingRateStrategy());
                    finished = true;
//            } else if (userInput2 == 4){
            } else if (userInput2.equals("4")) {
                 System.out.println("fin");
                    finished = true; 
            }else {
                 System.out.println("Invalid selection");
            }

//            switch (userInput2){
//                case 1:
////                        clientUser.setRate("linear");
//                    System.out.println("linrate");
//                    clientUser.setRate(new LinearRateStrategy());
//                    System.out.println("donezo");
//                    finished = true;
//                    break;
//                case 2:
////                        clientUser.setRate("progressive");
//                    System.out.println("prograte");
//                    clientUser.setRate(new ProgressiveRateStrategy());
//                    finished = true;
//                    break;
//                case 3: 
////                        clientUser.setRate("alternate");
//                    System.out.println("altrate");
//                    clientUser.setRate(new AlternatingRateStrategy());
//                    finished = true;
//                    break;
//                case 4:
//                    System.out.println("fin");
//                    finished = true;
//                    break;
//                default:
//                    System.out.println("Invalid selection");
//            }
        }
        System.out.println("");

}
    
}
