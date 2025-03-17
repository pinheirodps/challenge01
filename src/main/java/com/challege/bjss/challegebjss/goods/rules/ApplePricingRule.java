package com.challege.bjss.challegebjss.goods.rules;

import com.challege.bjss.challegebjss.goods.basket.Item;

import java.math.BigDecimal;
import java.util.List;

/**
 * The type Apple pricing rule.
 */
public class ApplePricingRule implements IPricingRule {

    private final List<Item> items;

    /**
     * Instantiates a new Apple pricing rule.
     *
     * @param items the items
     */
    public ApplePricingRule(final List<Item> items) {
        this.items = items;
    }

    @Override
    public BigDecimal calculateDiscount() {
        if (items == null || items.isEmpty()) return BigDecimal.ZERO;
        long appleCount = items.stream()
                .filter(item -> item.equals(Item.APPLE))
                .distinct()
                .count();
        return appleCount > 0 ? Item.APPLE.getPrice().multiply(Item.APPLE.getDiscount()) : BigDecimal.ZERO;
    }


    @Override
    public boolean isApplicable(final Item item) {
        return item.equals(Item.APPLE);
    }

}

