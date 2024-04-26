package shoppingcart.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import shoppingcart.domain.Product;

import java.math.BigDecimal;
import java.util.Arrays;

import static shoppingcart.utils.calculator.PromotionsCalculator.*;

@AllArgsConstructor
@Slf4j
@Getter
public enum PromotionsEnum {

        BUY_X_GET_Y_FREE("ZRAwbsO2qM", 0, BigDecimal.ZERO, null, 0) {
            @Override
            public BigDecimal getPromotion() {
                return calculateBuyGetFree(getCurrentQuantity(), getProduct().getProductPrice(), getRequiredQuantity());
            }
            @Override
            public void setCurrentQuantity(PromotionsEnum promotion, Integer quantity) {
                promotion.currentQuantity = quantity;
            }
        },
        QTY_BASED_PRICE_OVERRIDE("ibt3EEYczW", 0, BigDecimal.ZERO, null, 0) {
            @Override
            public BigDecimal getPromotion() {
                return calculateOverridePrice(getDiscountPrice(), getCurrentQuantity(), getRequiredQuantity());
            }
            @Override
            public void setCurrentQuantity(PromotionsEnum promotion, Integer quantity) {
                promotion.currentQuantity = quantity;
            }


        },
        FLAT_PERCENT("Gm1piPn7Fg", 0, BigDecimal.ZERO, null, 0) {
            @Override
            public BigDecimal getPromotion() {
                return calculateDiscountPercentage(getProduct().getProductPrice(), getCurrentQuantity());
            }

            @Override
            public void setCurrentQuantity(PromotionsEnum promotion, Integer quantity) {
                promotion.currentQuantity = quantity;
            }


        },
        UNKNOWN_PROMOTION("", 0, BigDecimal.ZERO, null, 0) {
            @Override
            public BigDecimal getPromotion() {
                return BigDecimal.ZERO;
            }

            @Override
            public void setCurrentQuantity(PromotionsEnum promotion, Integer currentQuantity) {
                promotion.currentQuantity = currentQuantity;
            }


        };

        private final String code;
        private Integer currentQuantity;
        private BigDecimal discountPrice;
        private Product product;
        private Integer requiredQuantity;

    private static PromotionsEnum setPromotionDescount(PromotionsEnum promotion, Integer currentQuantity, Product product, Integer requiredQuantity,  BigDecimal discountPrice) {
            promotion.currentQuantity = currentQuantity;
            promotion.discountPrice = discountPrice;
            promotion.product = product;
            promotion.requiredQuantity = requiredQuantity;
            return promotion;
        }

        public static PromotionsEnum getByCode(Integer currentQuantity, Product product, String idPromotion, Integer requiredQuantity, BigDecimal discountPrice) {
            return Arrays.stream(PromotionsEnum.values())
                    .filter(element -> element.getCode().equals(idPromotion))
                    .map(element -> setPromotionDescount(element, currentQuantity, product, requiredQuantity, discountPrice))
                    .findFirst()
                    .orElse(UNKNOWN_PROMOTION);
        }

        public String getCode() {
            return this.code;
        }
        public abstract BigDecimal getPromotion();
        public abstract void setCurrentQuantity(PromotionsEnum promotion, Integer quantity);
}
