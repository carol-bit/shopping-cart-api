package shoppingcart.utils.calculator;

import java.math.BigDecimal;
public class PromotionsCalculator {
    public static BigDecimal calculateBuyGetFree(Integer currentQuantity, BigDecimal priceProduct, Integer requiredQuantity){
        if (verifyQuantityRequired(currentQuantity, requiredQuantity))
            return priceProduct;
        return BigDecimal.ZERO;
    }
    public static BigDecimal calculateOverridePrice(BigDecimal discountPrice, Integer actualQuantity, Integer requiredQuantity){
        if (verifyQuantityRequired(actualQuantity, requiredQuantity)) {
            return discountPrice;
        } else {
            return  BigDecimal.ZERO;
        }
    }

    public static BigDecimal calculateDiscountPercentage(BigDecimal price, Integer actualQuantity){
        BigDecimal valorDescontoPorItem = price.multiply(BigDecimal.valueOf(0.10));
        return valorDescontoPorItem.multiply(BigDecimal.valueOf(actualQuantity));
    }

    public static boolean verifyQuantityRequired(Integer currentQuantity, Integer requiredQuantity){
        if (currentQuantity >= requiredQuantity)
            return true;
        return false;
    }


}
