/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain.RateStrategies;

import java.util.Calendar;

/**
 *
 * @author silvia
 */
public class AlternatingRateStrategy implements RateStrategy {
       RateStrategy rate;
    
    @Override
    public double calculateTime(int insertedSoFar) {
        int day = Calendar.getInstance().DAY_OF_WEEK;
        if(day < 6){
            rate = new ProgressiveRateStrategy();
            return rate.calculateTime(insertedSoFar);
        } else {
            rate = new LinearRateStrategy();
            return rate.calculateTime(insertedSoFar);
        }
    }
}
