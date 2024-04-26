package shoppingcart.utils.calculator;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import shoppingcart.domain.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import static shoppingcart.service.ShoppingCartService.getPromotionDiscountItem;
@AllArgsConstructor
@Component
public class BasketCalculator {
    public BigDecimal getRawTotal(HashMap<Product, Integer> productHashmap) {
        BigDecimal total = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> item : productHashmap.entrySet()) {
            total = total.add(getSubTotalItems(item.getKey(), item.getValue()));
        }
        return total.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getSubTotalItems(Product product, Integer value){
        return product.getProductPrice().multiply(BigDecimal.valueOf(value));
    }

    public BigDecimal calculateTotalDiscounts(HashMap<Product, Integer> productHashmap ){
        BigDecimal totalPromoItem = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> item : productHashmap.entrySet()) {
            BigDecimal totalPromoItemTemp = getPromotionDiscountItem(item.getValue(), item.getKey());
            totalPromoItem = totalPromoItem.add(totalPromoItemTemp);
        }
        return totalPromoItem;
    }

}
