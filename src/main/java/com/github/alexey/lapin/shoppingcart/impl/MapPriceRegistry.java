package com.github.alexey.lapin.shoppingcart.impl;

import com.github.alexey.lapin.shoppingcart.PriceRegistry;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class MapPriceRegistry implements PriceRegistry {

    private final Map<String, BigDecimal> prices;

    public MapPriceRegistry(Map<String, BigDecimal> prices) {
        this.prices = Objects.requireNonNull(prices, "prices must not be null");
    }

    @Override
    public Optional<BigDecimal> getPrice(String name) {
        return Optional.ofNullable(prices.get(name));
    }

}
