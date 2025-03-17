package com.challege.bjss.challegebjss.goods.basket;

import com.challege.bjss.challegebjss.goods.rules.ApplePricingRule;
import com.challege.bjss.challegebjss.goods.rules.BreadPricingRule;
import com.challege.bjss.challegebjss.goods.rules.IPricingRule;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Basket factory.
 */
public class BasketFactory {
    /**
     * Create basket basket.
     *
     * @param args the args
     * @return the basket
     */
    public static Basket createBasket(final String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Error: No input provided.");
        }
        List<Item> basketItems = new ArrayList<>();
        for (String arg : args) {
            arg = arg.replaceFirst("^(.)", "$1".toUpperCase());
            Item item = Item.getInstance(arg);
            if (item == null) {
                throw new IllegalArgumentException("Error: Invalid input provided. '" + arg + "' is not a valid item.");
            }
            basketItems.add(item);
        }
        List<IPricingRule> pricingRules = List.of(new ApplePricingRule(basketItems),
                new BreadPricingRule(basketItems));
        return new Basket(basketItems, pricingRules);
    }

}

