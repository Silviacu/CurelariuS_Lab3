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
public class LinearRateStrategy implements RateStrategy {

   @Override
    public double calculateTime(int paidSoFar) {
        return (paidSoFar/5)*2;
    }
    
}
