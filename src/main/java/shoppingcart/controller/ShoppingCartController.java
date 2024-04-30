package shoppingcart.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import shoppingcart.domain.AddItemRequest;
import shoppingcart.domain.Product;
import shoppingcart.service.ShoppingCartService;
import shoppingcart.domain.ExpectedTotals;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@AllArgsConstructor
    @RequestMapping(value = "/api/shopping-cart/v1")
@OpenAPIDefinition(info = @io.swagger.v3.oas.annotations.info.Info(
        title = "Shopping Cart Api",
        version = "v1",
        description = "Manage cart lifecycle"
))
public class ShoppingCartController {
    private final ShoppingCartService service;

    @Operation(summary = "List all products", description = "List all available products")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 201, message = "Created")
    })
    @GetMapping (path = {"/products"})
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<List<Product>> getProductsList() {
        return ResponseEntity.ok(service.getProductList());
    }


    @Operation(summary = "Add items", description = "Add items to basket")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @PostMapping (path = {"/basket/item"})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<Void> addItemsToBasket(@RequestBody AddItemRequest addItemRequest) {
        service.addItemsToBasket(addItemRequest);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get price details", description = "Get price details from basket")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 201, message = "Created")
    })
    @PostMapping (path = {"/basket/details"})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<ExpectedTotals> getCart() {
        return ResponseEntity.ok(service.getBasketPriceDetails());
    }

    @Operation(summary = "Clear Basket", description = "Clear temporary storage of products")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
    })
    @PostMapping (path = {"/clear"})
    public ResponseEntity<Void> clearBasket() {
        service.clearBasket();
        return ResponseEntity.ok().build();
    }


}
