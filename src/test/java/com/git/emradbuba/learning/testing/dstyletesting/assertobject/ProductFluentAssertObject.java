package com.git.emradbuba.learning.testing.dstyletesting.assertobject;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductFluentAssertObject {
    private Product product;

    private ProductFluentAssertObject(Product product) {
        this.product = product;
    }

    public static ProductFluentAssertObject assertThatProduct(Product product) {
        return new ProductFluentAssertObject(product);
    }

    public ProductFluentAssertObject isPresent() {
        assertThat(product).isNotNull();
        return this;
    }

    public ProductFluentAssertObject hasName() {
        assertThat(product.getProductName()).isNotBlank();
        return this;
    }

    public ProductFluentAssertObject isOnlySeasonal() {
        assertThat(product.getProperties()).hasSize(1);
        assertThat(product.getProperties().get(0)).isEqualTo(ProductProperties.SEASONAL);
        return this;
    }

    public ProductFluentAssertObject hasLowestPriceSinceThreeMonths() {
        PriceHistory priceHistory = product.getPriceHistory();
        assertThat(priceHistory).isNotNull();
        assertThat(priceHistory.getCurrentPrice()).isNotNull();
        assertThat(priceHistory.getPriceOneMonthAgo()).isNotNull();
        assertThat(priceHistory.getPriceTwoMonthsAgo()).isNotNull();
        assertThat(priceHistory.getCurrentPrice()).isLessThanOrEqualTo(priceHistory.getPriceOneMonthAgo());
        assertThat(priceHistory.getCurrentPrice()).isLessThanOrEqualTo(priceHistory.getPriceTwoMonthsAgo());
        return this;
    }

    public ProductFluentAssertObject and() {
        return this;
    }
}
