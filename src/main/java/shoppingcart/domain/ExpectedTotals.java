package shoppingcart.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class ExpectedTotals {
    List<TotalBasketContents> totalBasketContents;
    BigDecimal rawTotal;
    BigDecimal totalPromos;
    BigDecimal totalPayable;

}
