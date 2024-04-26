package shoppingcart.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Data
@NoArgsConstructor
public class TotalBasketContents extends Product {
    Integer quantity;
    BigDecimal itemRawTotal;
    BigDecimal totalItemDiscount;
    BigDecimal totalItemPayable;

}
