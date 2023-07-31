package com.github.alexey.lapin.shoppingcart;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Represents a shopping cart.
 */
public class ShoppingCart {

    private final Collection<String> items;

    public ShoppingCart(Collection<String> items) {
        this.items = Objects.requireNonNull(items, "items must not be null");
    }

    public static ShoppingCart of(String... items) {
        return new ShoppingCart(Arrays.asList(items));
    }

    public Map<String, Integer> getItems() {
        return items.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(e -> 1)));
    }

}
