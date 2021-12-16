package se.bahram.axoniq.samples.productservice.command.api.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductCommandRestModel {

    private String name;
    private BigDecimal price;
    private Integer quantity;
}
