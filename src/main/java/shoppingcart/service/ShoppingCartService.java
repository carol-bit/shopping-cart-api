package shoppingcart.service;

import lombok.Data;
import org.springframework.web.server.ResponseStatusException;
import shoppingcart.domain.*;
import shoppingcart.enums.PromotionsEnum;
import lombok.AllArgsConstructor;
import shoppingcart.infrainstructure.client.WiremockClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import shoppingcart.utils.calculator.BasketCalculator;

import java.math.BigDecimal;
import java.util.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static shoppingcart.mapper.BasketMapper.*;

@Service
@Slf4j
@AllArgsConstructor
@Data
public class ShoppingCartService {
    private final WiremockClient client;
    public HashMap<Product, Integer> productHashmap;
    private final BasketCalculator basketCalculator;

    public List<Product> getProductList() {
        return client.getAllProducts();
    }

    public void clearBasket() {
        productHashmap.clear();
    }

    public void addItemsToBasket(AddItemRequest addItemRequest) {
        try{
            Product product = client.getProduct(addItemRequest.getProductId());
            addItem(product, addItemRequest.getQuantity());
        } catch (Exception e){
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource", e);
        }
    }

    public void addItem(Product product, Integer quantity){
        if (productHashmap.containsKey(product)){
            productHashmap.put(product, productHashmap.get(product) + quantity);
        } else {
            productHashmap.put(product, quantity);
        }
    }

    public BigDecimal getTotalWithDiscounts(){
        return basketCalculator.getRawTotal(productHashmap)
                .subtract(basketCalculator.calculateTotalDiscounts(productHashmap));
    }

    public ExpectedTotals getBasketPriceDetails(){
        return mapExpectedTotals(getBasketContents(),
                basketCalculator.calculateTotalDiscounts(productHashmap ),
                basketCalculator.getRawTotal(productHashmap),
                getTotalWithDiscounts());
    }

    public List<TotalBasketContents> getBasketContents(){
        List<TotalBasketContents> productsList = new ArrayList<>();
        for (Map.Entry<Product, Integer> item : productHashmap.entrySet()) {
            BigDecimal subtotal = basketCalculator.getSubTotalItems(item.getKey(), item.getValue());
            productsList.add(mapBasketContents(item.getKey(), item.getValue(), subtotal,
                    getPromotionDiscountItem(item.getValue(), item.getKey())));
        }
        return productsList;
    }


    public static BigDecimal getPromotionDiscountItem(Integer currentQuantity, Product product){
        BigDecimal totalDiscountItem = BigDecimal.ZERO;
        for (Promotions promotion : product.getPromotionsList()) {
            PromotionsEnum integrationPromotions = PromotionsEnum.getByCode(currentQuantity, product,
                    promotion.getPromotionId(), promotion.getItemRequiredQty(), promotion.getDiscountPrice());
            integrationPromotions.setCurrentQuantity(integrationPromotions, currentQuantity);
            totalDiscountItem = integrationPromotions.getPromotion();
        }
        return  totalDiscountItem;
    }

}
