/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain.RateStrategies;

/**
 *
 * @author silvia
 */
public class ProgressiveRateStrategy implements RateStrategy {
    
    @Override
    public double calculateTime(int paidSoFar) {
        if (paidSoFar <= 150) {
            return (paidSoFar / 5) * 2;
        } else if (paidSoFar <= 350) {
            double x = ((paidSoFar - 150) / 5)*1.5;
            return ((150/5)*2) + x;
        } else {
            double x = (paidSoFar-350)/5;
            double y = ((200/5))*1.5;
            return ((150/5)*2) +x+y;
        }
    }
}
