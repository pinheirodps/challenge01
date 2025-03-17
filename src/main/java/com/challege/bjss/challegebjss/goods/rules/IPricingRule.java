package com.challege.bjss.challegebjss.goods.rules;

import com.challege.bjss.challegebjss.goods.basket.Item;

import java.math.BigDecimal;

/**
 * The interface Pricing rule.
 */
public interface IPricingRule {
    /**
     * Calculate discount big decimal.
     *
     * @return the big decimal
     */
    BigDecimal calculateDiscount();

    /**
     * Is applicable boolean.
     *
     * @param item the item
     * @return the boolean
     */
    boolean isApplicable(Item item);


}
