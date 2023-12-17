# Testing course - dstyle

## Wrapping legacy static methods
Let's assume we have a class with `static` methods which we cannot modify. Example

```java
public class LegacyBusinessService {
    public static boolean runLongBusinessLogic1() {
        try {
            Thread.sleep(5000L); // <-- long running logic, does not matter
            return true;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
```

In order to avoid using these static method which could lead to lower testability, we can wrap the legacy logic into a fully testable component: 

```java
@Service
public class LegacyBusinessServiceWrapper {

    public boolean runLongBusinessLogic1() {
        return LegacyBusinessService.runLongBusinessLogic1(); // using static method but wrapping it in a normal component
    }
}
```

Further on, we can use just use the wrapper. By doing so, 
* we don't need to invoke the static logic from legacy methods (or using frameworks for mocking statics)
* so we can mock the logic to create more separated unit test approach

```java
@Component
public class BusinessLogicExecutor {

    private LegacyBusinessServiceWrapper legacyBusinessServiceWrapper;

    public BusinessLogicExecutor(LegacyBusinessServiceWrapper legacyBusinessServiceWrapper) {
        this.legacyBusinessServiceWrapper = legacyBusinessServiceWrapper;
    }

    public boolean performLongRunningBusinessLogics() {
        //  return LegacyStaticBusinessMethods.runLongBusinessLogic1();
        return legacyBusinessServiceWrapper.runLongBusinessLogic1();
    }
}
```

## AssertObject

A nice design pattern for performing repetitive assertions in more readable way. Can be also use to create more business friendly assertion code. 

### Example problem: 

We have a product:
```java
public class Product {
    private String productName;
    private List<ProductProperties> properties;
    private PriceHistory priceHistory;
}
```

Let's assume we have a service producing products, some of which should fulfil following specific criteria: 
* product has a name
* product is seasonal (and only seasonal)
* product's price is lowest since last two months


the assertion checking this can be quite complicated:
```java
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
```

Using `AssertObject` we can write it in more readable way moving the specific assertions to special object: 

```java
@Test
void shouldServiceReturnSeasonalRecentlyCheapestProduct_WithAssertObject() {

    Product product = productsService.getSeasonalAndCheapestProduct();

    ProductAssertObject productAssert = new ProductAssertObject(product);
    productAssert.isPresent();
    productAssert.hasName();
    productAssert.isOnlySeasonal();
    productAssert.hasLowestPriceSinceThreeMonths();
}
```
or even in a more fluent way:

```java
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
```

