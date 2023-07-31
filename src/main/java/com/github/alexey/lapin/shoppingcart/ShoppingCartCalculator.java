package com.github.alexey.lapin.shoppingcart;

import java.math.BigDecimal;

/**
 * Calculates total price of the shopping cart.
 */
public interface ShoppingCartCalculator {

    /**
     * Calculates total price of the shopping cart.
     *
     * @param shoppingCart the shopping cart
     * @return the total price of the shopping cart
     */
    BigDecimal calculateTotalPrice(ShoppingCart shoppingCart);

}
