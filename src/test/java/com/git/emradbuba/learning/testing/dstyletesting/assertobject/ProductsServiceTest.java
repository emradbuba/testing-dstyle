package com.git.emradbuba.learning.testing.dstyletesting.assertobject;

import org.junit.jupiter.api.Test;

import static com.git.emradbuba.learning.testing.dstyletesting.assertobject.ProductFluentAssertObject.assertThatProduct;
import static org.assertj.core.api.Assertions.assertThat;

class ProductsServiceTest {

    private final ProductsService productsService = new ProductsService();


    @Test
    void shouldServiceReturnSeasonalRecentlyCheapestProduct_WithoutAssertObject() {

        Product product = productsService.getSeasonalAndCheapestProduct();

        assertThat(product).isNotNull();
        assertThat(product.getProductName()).isNotBlank();
        assertThat(product.getProperties()).hasSize(1);
        assertThat(product.getProperties().get(0)).isEqualTo(ProductProperties.SEASONAL);
        PriceHistory priceHistory = product.getPriceHistory();
        assertThat(priceHistory).isNotNull();
        assertThat(priceHistory.getCurrentPrice()).isNotNull();
        assertThat(priceHistory.getPriceOneMonthAgo()).isNotNull();
        assertThat(priceHistory.getPriceTwoMonthsAgo()).isNotNull();
        assertThat(priceHistory.getCurrentPrice()).isLessThanOrEqualTo(priceHistory.getPriceOneMonthAgo());
        assertThat(priceHistory.getCurrentPrice()).isLessThanOrEqualTo(priceHistory.getPriceTwoMonthsAgo());
    }

    @Test
    void shouldServiceReturnSeasonalRecentlyCheapestProduct_WithAssertObject() {

        Product product = productsService.getSeasonalAndCheapestProduct();

        ProductAssertObject productAssert = new ProductAssertObject(product);

        productAssert.isPresent();
        productAssert.hasName();
        productAssert.isOnlySeasonal();
        productAssert.hasLowestPriceSinceThreeMonths();
    }

    @Test
    void shouldServiceReturnSeasonalRecentlyCheapestProduct_WithFluentAssertObject() {

        Product product = productsService.getSeasonalAndCheapestProduct();

        assertThatProduct(product)
                .isPresent()
                .and()
                .hasName()
                .and()
                .isOnlySeasonal()
                .and()
                .hasLowestPriceSinceThreeMonths();
    }
}