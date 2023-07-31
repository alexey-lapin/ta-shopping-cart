package com.github.alexey.lapin.shoppingcart.impl;

import com.github.alexey.lapin.shoppingcart.ShoppingCart;
import com.github.alexey.lapin.shoppingcart.ShoppingCartCalculator;
import com.github.alexey.lapin.shoppingcart.impl.DefaultShoppingCartCalculator;
import com.github.alexey.lapin.shoppingcart.impl.MapCalculationRuleRegistry;
import com.github.alexey.lapin.shoppingcart.impl.MapPriceRegistry;
import com.github.alexey.lapin.shoppingcart.rule.BulkDiscount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class DefaultShoppingCartCalculatorTest {

    private ShoppingCartCalculator shoppingCartCalculator;

    @BeforeEach
    void setUp() {
        var priceRegistry = new MapPriceRegistry(Map.of(
                "Apple", BigDecimal.valueOf(0.35),
                "Banana", BigDecimal.valueOf(0.20),
                "Melon", BigDecimal.valueOf(0.50),
                "Lime", BigDecimal.valueOf(0.15)
        ));

        var calculationRuleRegistry = new MapCalculationRuleRegistry(Map.of(
                "Melon", new BulkDiscount(2, 1),
                "Lime", new BulkDiscount(3, 2)
        ));

        shoppingCartCalculator = new DefaultShoppingCartCalculator(priceRegistry, calculationRuleRegistry);
    }

    @Test
    void should_returnZero_when_cartIsEmpty() {
        ShoppingCart shoppingCart = ShoppingCart.of();

        BigDecimal totalPrice = shoppingCartCalculator.calculateTotalPrice(shoppingCart);

        assertThat(totalPrice).isEqualByComparingTo(BigDecimal.ZERO);
    }

    @Test
    void should_returnCorrectAmount_when_cartHasMultipleItemsWithoutDiscount() {
        ShoppingCart shoppingCart = ShoppingCart.of("Apple", "Banana", "Apple");

        BigDecimal totalPrice = shoppingCartCalculator.calculateTotalPrice(shoppingCart);

        assertThat(totalPrice).isEqualByComparingTo(BigDecimal.valueOf(0.90));
    }

    @Test
    void should_returnCorrectAmount_when_cartHasMultipleItemsWithDiscount() {
        ShoppingCart shoppingCart = ShoppingCart.of("Melon", "Melon", "Lime", "Lime", "Lime");

        BigDecimal totalPrice = shoppingCartCalculator.calculateTotalPrice(shoppingCart);

        assertThat(totalPrice).isEqualByComparingTo(BigDecimal.valueOf(0.80));
    }

    @Test
    void should_returnCorrectAmount_when_cartHasMultipleItemsWithAndWithoutDiscount() {
        ShoppingCart shoppingCart = ShoppingCart.of("Melon", "Melon", "Melon", "Lime", "Lime", "Lime", "Lime");

        BigDecimal totalPrice = shoppingCartCalculator.calculateTotalPrice(shoppingCart);

        assertThat(totalPrice).isEqualByComparingTo(BigDecimal.valueOf(1.45));
    }

    @Test
    void should_throw_when_cartHasUnknownItems() {
        ShoppingCart shoppingCart = ShoppingCart.of("Pear");

        assertThatIllegalArgumentException().isThrownBy(() -> shoppingCartCalculator.calculateTotalPrice(shoppingCart));
    }

}