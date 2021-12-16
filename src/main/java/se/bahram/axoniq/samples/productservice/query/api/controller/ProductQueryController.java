package se.bahram.axoniq.samples.productservice.query.api.controller;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.bahram.axoniq.samples.productservice.query.api.model.ProductQueryRestModel;
import se.bahram.axoniq.samples.productservice.query.api.queries.GetProductsQuery;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    private final QueryGateway queryGateway;

    public ProductQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public List<ProductQueryRestModel> getAllProducts() {
        GetProductsQuery getProductsQuery =
                new GetProductsQuery();
        List<ProductQueryRestModel> productQueryRestModels =
                queryGateway
                        .query(
                                getProductsQuery,
                                ResponseTypes.multipleInstancesOf(ProductQueryRestModel.class)
                        )
                        .join();
        return productQueryRestModels;
    }
}
