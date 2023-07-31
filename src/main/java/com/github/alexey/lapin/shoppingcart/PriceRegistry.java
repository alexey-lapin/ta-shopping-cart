package com.github.alexey.lapin.shoppingcart;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Provides prices for items.
 */
public interface PriceRegistry {

    /**
     * Returns a price for the given item.
     *
     * @param name the name of the item
     * @return the price for the given item
     */
    Optional<BigDecimal> getPrice(String name);

}
