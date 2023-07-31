# Shopping Cart

This is a simple Java-based Shopping Cart Price Calculator that allows you to calculate the total price of items in a
shopping cart. It can handle different pricing rules for each item, including discounts for buying in bulk.

## Usage

Create a `PriceRegistry`:

```java
Map<String, BigDecimal> prices = new HashMap<>();
prices.put("Apple",new BigDecimal("0.35"));
prices.put("Banana",new BigDecimal("0.20"));
prices.put("Melon",new BigDecimal("0.50"));
prices.put("Lime",new BigDecimal("0.15"));
PriceRegistry priceRegistry=new MapPriceRegistry(prices);
```

Create a `CalculationRuleRegistry`:

```java
Map<String, CalculationRule> rules = new HashMap<>();
rules.put("Melon",new BulkDiscount(2,1)); // buy 2 pay for 1
rules.put("Lime",new BulkDiscount(3,2)); // buy 3 pay for 2
CalculationRuleRegistry calculationRuleRegistry=new MapCalculationRuleRegistry(rules);
```

Create a `ShoppingCartCalculator`:

```java
ShoppingCartCalculator calculator=new DefaultShoppingCartCalculator(priceRegistry,ruleRegistry);

ShoppingCart cart = ShoppingCart.of("Apple","Banana","Apple","Melon","Melon","Lime","Lime","Lime");
BigDecimal totalPrice=calculator.calculateTotalPrice(cart);
```
