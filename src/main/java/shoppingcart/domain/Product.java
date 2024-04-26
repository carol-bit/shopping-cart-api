package shoppingcart.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Data
@NoArgsConstructor
@SuperBuilder
public class Product {
    @JsonAlias("id")
    String productId;
    @JsonAlias("name")
    String productName;
    @JsonAlias("price")
    BigDecimal productPrice;
    @JsonAlias("promotions")
    List<Promotions> promotionsList;

}
