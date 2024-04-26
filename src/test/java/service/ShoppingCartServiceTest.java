package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import shoppingcart.domain.Product;
import shoppingcart.domain.Promotions;
import shoppingcart.infrainstructure.client.WiremockClient;
import shoppingcart.service.ShoppingCartService;
import shoppingcart.utils.calculator.BasketCalculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShoppingCartServiceTest {

    @InjectMocks
    ShoppingCartService service;

    @Mock
    WiremockClient client;

    @Mock
    Promotions promotionstListResponse;
    @Mock
    HashMap<Product, Integer> productHashmap;

    @Mock
    BasketCalculator basketCalculator;

    private final Product product = Product.builder()
            .productId("PWWe3w1SDU")
            .productName("Amazing Burger!")
            .productPrice(BigDecimal.valueOf(999.50))
            .build();

    @BeforeEach
    void setUp() {
        productHashmap = new HashMap<>();
        productHashmap.put(product, 2);
    }

    @Test
    void testGetProductList() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(client.getAllProducts()).thenReturn(productList);
        assertEquals(productList, service.getProductList());
    }

    @Test
    void testadditemsToBasket() {
        when(client.getProduct(any())).thenReturn(product);
        service.addItemsToBasket("PWWe3w1SDU", 2);
        verify(client,times(1)).getProduct("PWWe3w1SDU");
    }

    @Test
    void testGetTotalWithDiscounts() {
        Mockito.when(basketCalculator.getRawTotal(any())).thenReturn(BigDecimal.valueOf(1999));
        Mockito.when(basketCalculator.calculateTotalDiscounts(any())).thenReturn(BigDecimal.valueOf(999.50));

        List<Promotions> promotionList = new ArrayList<>();
        promotionList.add(promotionstListResponse);
        product.setPromotionsList(promotionList);

        when(client.getProduct(any())).thenReturn(product);

        service.addItemsToBasket("PWWe3w1SDU", 2);
        BigDecimal totalWithDiscounts = service.getTotalWithDiscounts();

        Assertions.assertEquals(BigDecimal.valueOf(999.50), totalWithDiscounts);
        }


}
