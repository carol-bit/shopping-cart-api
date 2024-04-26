package shoppingcart.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class Promotions {
    @JsonAlias("id")
    String promotionId;
    @JsonAlias("type")
    String promotionType;
    @JsonAlias("required_qty")
    Integer itemRequiredQty;
    @JsonAlias("free_qty")
    Integer itemFreeQty;
    @JsonAlias("price")
    BigDecimal discountPrice;
    @JsonAlias("amount")
    Integer percentageAmount;

}
