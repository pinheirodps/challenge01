package com.challege.bjss.challegebjss.goods.rules;

import com.challege.bjss.challegebjss.goods.basket.Item;

import java.math.BigDecimal;
import java.util.List;

/**
 * The type Bread pricing rule.
 */
public class BreadPricingRule implements IPricingRule {
    private final List<Item> items;

    /**
     * Instantiates a new Bread pricing rule.
     *
     * @param items the items
     */
    public BreadPricingRule(final List<Item> items) {
        this.items = items;
    }

    @Override
    public BigDecimal calculateDiscount() {
        if (items == null || items.isEmpty()) return BigDecimal.ZERO;
        long soupCount = items.stream()
                .filter(item -> item.equals(Item.SOUP))
                .count();
        return soupCount >= 2 ? Item.BREAD.getPrice().multiply(Item.BREAD.getDiscount()) : BigDecimal.ZERO;
    }


    @Override
    public boolean isApplicable(final Item item) {
        return item.equals(Item.BREAD);
    }

}

