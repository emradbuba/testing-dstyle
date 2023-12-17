package com.git.emradbuba.learning.testing.dstyletesting.assertobject;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductAssertObject {
    private Product product;

    public ProductAssertObject(Product product) {
        this.product = product;
    }

    public void isPresent() {
        assertThat(product).isNotNull();
    }

    public void hasName() {
        assertThat(product.getProductName()).isNotBlank();
    }

    public void isOnlySeasonal() {
        assertThat(product.getProperties()).hasSize(1);
        assertThat(product.getProperties().get(0)).isEqualTo(ProductProperties.SEASONAL);
    }

    public void hasLowestPriceSinceThreeMonths() {
        PriceHistory priceHistory = product.getPriceHistory();
        assertThat(priceHistory).isNotNull();
        assertThat(priceHistory.getCurrentPrice()).isNotNull();
        assertThat(priceHistory.getPriceOneMonthAgo()).isNotNull();
        assertThat(priceHistory.getPriceTwoMonthsAgo()).isNotNull();
        assertThat(priceHistory.getCurrentPrice()).isLessThanOrEqualTo(priceHistory.getPriceOneMonthAgo());
        assertThat(priceHistory.getCurrentPrice()).isLessThanOrEqualTo(priceHistory.getPriceTwoMonthsAgo());
    }
}
