package com.challege.bjss.challegebjss.goods.basket;

import java.math.BigDecimal;


/**
 * The enum Item.
 */
public enum Item {

    /**
     * The Soup.
     */
    SOUP("Soup", new BigDecimal("0.65"), false,  BigDecimal.ZERO),
    /**
     * The Bread.
     */
    BREAD("Bread", new BigDecimal("0.80"), true, new BigDecimal("0.5") ),
    /**
     * The Milk.
     */
    MILK("Milk", new BigDecimal("1.30"), false, BigDecimal.ZERO),
    /**
     * The Apple.
     */
    APPLE("Apples", new BigDecimal("1.00"), true, new BigDecimal("0.1"));

    private final String name;
    private final BigDecimal discount;
    private final BigDecimal price;
    private  boolean hasDiscount;



    Item(String name, BigDecimal price, boolean hasDiscount, BigDecimal discount) {
        this.name = name;
        this.price = price;
        this.hasDiscount = hasDiscount;
        this.discount = discount;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Has discount boolean.
     *
     * @return the boolean
     */
    public boolean hasDiscount() {
        return hasDiscount;
    }

    /**
     * Gets discount.
     *
     * @return the discount
     */
    public BigDecimal getDiscount() {
        return discount;
    }

    /**
     * Gets instance.
     *
     * @param name the name
     * @return the instance
     */
    public static Item getInstance(String name) {
        for(Item item: values()) {
            if (item.name.equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }
}
