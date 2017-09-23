package paystation.domain;

import java.util.Map;
import java.util.HashMap;
import paystation.domain.RateStrategies.*;

/**TESTING
 * Implementation of the pay station.
 *
 * Responsibilities:
 *
 * 1) Accept payment; 
 * 2) Calculate parking time based on payment; 
 * 3) Know earning, parking time bought; 
 * 4) Issue receipts; 
 * 5) Handle buy and cancel events.
 *
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
public class PayStationImpl implements PayStation {
    
    private int insertedSoFar;
    private double timeBought;
    private int totalAmountCollected;
    private Map<Integer, Integer> myCoinMap = new HashMap();
    
    private RateStrategy currentRateStrategy = new LinearRateStrategy();
     
    public int getInsertedCoins() {
        return insertedSoFar;
    }
    
    public int getTotalAmountCollected() {
        return totalAmountCollected;
    }

    public Map getMyCoinMap(){
        return myCoinMap;
    }
    
    @Override
    public void addPayment(int coinValue)
            throws IllegalCoinException {
        switch (coinValue) {
            case 5: break;
            case 10: break;
            case 25: break;
            default:
                throw new IllegalCoinException("Invalid coin: " + coinValue);
        }
        insertedSoFar += coinValue;
        //first says it is a nickle and then how many nickles
        if(myCoinMap.get(coinValue)== null){
           myCoinMap.put(coinValue, 0);
        }
        myCoinMap.put(coinValue, myCoinMap.get(coinValue)+1);
        //timeBought = insertedSoFar / 5 * 2;
        timeBought = currentRateStrategy.calculateTime(insertedSoFar);
    }

    @Override
    public double readDisplay() {
        return timeBought;
    }

    @Override
    public Receipt buy() {
        Receipt r = new ReceiptImpl(timeBought);
        totalAmountCollected = insertedSoFar;

        reset();
        return r;
    }

    @Override
    public Map <Integer, Integer> cancel() {
        Map<Integer, Integer> currentCoinMap = new HashMap(myCoinMap);
        reset();
        return currentCoinMap;
    }
    
    private void reset() {
        timeBought = insertedSoFar = 0;
        myCoinMap.clear();
    }
    
     @Override
    public int empty(){
        int totalAmountCollected_return = totalAmountCollected;
        totalAmountCollected = 0;
        return totalAmountCollected_return;
    }

    @Override
    public void setRate(RateStrategy rs) {
        currentRateStrategy = rs;
//            switch(s){
//                case "linear":
//                    currentRateStrategy = new LinearRateStrategy();
//                    break;
//                case "progressive":
//                    currentRateStrategy = new ProgressiveRateStrategy();
//                    break;
//                case "alternate":
//                    currentRateStrategy = new AlternatingRateStrategy();
//                    break;
//                default:
//                    currentRateStrategy = new LinearRateStrategy();
//            }
    }
    
    

}
