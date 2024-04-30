package ShoppingCartControllerTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import shoppingcart.controller.ShoppingCartController;
import shoppingcart.domain.AddItemRequest;
import shoppingcart.domain.ExpectedTotals;
import shoppingcart.domain.Product;
import shoppingcart.service.ShoppingCartService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShoppingCartControllerTest {

    @InjectMocks
    private ShoppingCartController controller;

    @Mock
    private ShoppingCartService service;

    @Mock
    ExpectedTotals expectedTotals;

    private final AddItemRequest addItemRequest = AddItemRequest.builder().quantity(2).productId("PWWe3w1SDU").build();

    @Test
    void testGetProductsList() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("PWWe3w1SDU", "Amazing Burger!", BigDecimal.valueOf(999.50), null));
        productList.add(new Product("2", "Product 2", BigDecimal.valueOf(20), null));

        when(service.getProductList()).thenReturn(productList);
        ResponseEntity<List<Product>> response = controller.getProductsList();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productList, response.getBody());
    }

    @Test
    void testAddItemsToCar() {
        ResponseEntity<Void> response = controller.addItemsToBasket(addItemRequest);
        verify(service).addItemsToBasket(addItemRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetCart() {
        when(service.getBasketPriceDetails()).thenReturn(expectedTotals);
        ResponseEntity<ExpectedTotals> response = controller.getCart();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedTotals, response.getBody());
    }

}
