/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package discount_classes;

/**
 *
 * @author vanat
 */
public class DiscountCodeEntity {
    
    private String discountId;
    private float rate;
    
    public DiscountCodeEntity(String discCode, float arate) {
        this.discountId = discCode;
        this.rate = arate;
    }
    
    public String getDiscountId() {
        return this.discountId;
    }
    
    public float getRate() {
        return this.rate;
    }
}




