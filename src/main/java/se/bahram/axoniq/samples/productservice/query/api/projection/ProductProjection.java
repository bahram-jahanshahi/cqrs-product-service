package se.bahram.axoniq.samples.productservice.query.api.projection;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import se.bahram.axoniq.samples.productservice.command.api.data.Product;
import se.bahram.axoniq.samples.productservice.command.api.data.ProductRepository;
import se.bahram.axoniq.samples.productservice.query.api.model.ProductQueryRestModel;
import se.bahram.axoniq.samples.productservice.query.api.queries.GetProductsQuery;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection {

    private final ProductRepository productRepository;

    public ProductProjection(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<ProductQueryRestModel> handle(GetProductsQuery getProductsQuery) {
        List<Product> products = productRepository.findAll();
        List<ProductQueryRestModel> productQueryRestModels =
                products.stream()
                        .map(product -> ProductQueryRestModel.builder()
                                .quantity(product.getQuantity())
                                .price(product.getPrice())
                                .name(product.getName())
                                .build()
                        )
                        .collect(Collectors.toList());
        return productQueryRestModels;
    }
}
