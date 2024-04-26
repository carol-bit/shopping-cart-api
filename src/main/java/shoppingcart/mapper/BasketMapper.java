package shoppingcart.mapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import shoppingcart.domain.TotalBasketContents;
import shoppingcart.domain.ExpectedTotals;
import shoppingcart.domain.Product;

import java.math.BigDecimal;
import java.util.List;

@Builder
@AllArgsConstructor
public class BasketMapper {

    public static ExpectedTotals mapExpectedTotals(List<TotalBasketContents> totalBasketContents, BigDecimal totalPromotion, BigDecimal rawTotal, BigDecimal totalPayable){
        return ExpectedTotals.builder()
                .totalBasketContents(totalBasketContents)
                .totalPromos(totalPromotion)
                .rawTotal(rawTotal)
                .totalPayable(totalPayable)
                .build();
    }

    public static TotalBasketContents mapBasketContents(Product product, Integer quantity, BigDecimal subtotal, BigDecimal promotion){
        return TotalBasketContents.builder()
                .productName(product.getProductName())
                .quantity(quantity)
                .itemRawTotal(subtotal)
                .totalItemDiscount(promotion)
                .build();
    }
}
