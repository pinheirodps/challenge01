package com.challege.bjss.challegebjss.goods.fomatter;

import com.challege.bjss.challegebjss.goods.basket.Basket;

/**
 * The type Output formatter without discount.
 */
public class OutputFormatterWithoutDiscount implements IOutputFormatter {
    public void print(final Basket basket) {
        System.out.println("(no offers available)");
    }
}
