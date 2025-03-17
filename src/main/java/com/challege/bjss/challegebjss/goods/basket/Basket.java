package com.challege.bjss.challegebjss.goods.basket;

import com.challege.bjss.challegebjss.goods.rules.IPricingRule;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Basket.
 */
public class Basket {
    private final List<Item> items;
    private final List<IPricingRule> pricingRules;

    private final Object lock = new Object();

    /**
     * Instantiates a new Basket.
     *
     * @param items        the items
     * @param pricingRules the pricing rules
     */
    public Basket(final List<Item> items, final List<IPricingRule> pricingRules) {
        this.items = items == null ? new ArrayList<>() : items;
        this.pricingRules = pricingRules == null ? new ArrayList<>() : pricingRules;
    }

    /**
     * Gets subtotal.
     *
     * @return the subtotal
     */
    public BigDecimal getSubtotal() {
        synchronized (lock) {
            return items.stream().map(Item::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                    .setScale(2, RoundingMode.HALF_UP);
        }
    }

    /**
     * Gets total.
     *
     * @return the total
     */
    public BigDecimal getTotal() {
        synchronized (lock) {
            BigDecimal subtotal = getSubtotal();
            BigDecimal discount = pricingRules.stream()
                    .map(IPricingRule::calculateDiscount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            return subtotal.subtract(discount).setScale(2, RoundingMode.HALF_UP);
        }
    }

    /**
     * Gets items.
     *
     * @return the items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Gets pricing rules.
     *
     * @return the pricing rules
     */
    public List<IPricingRule> getPricingRules() {
        return pricingRules;
    }

    /**
     * Has discount boolean.
     *
     * @return the boolean
     */
    public boolean hasDiscount() {
        return items.stream().anyMatch(Item::hasDiscount);
    }
}
