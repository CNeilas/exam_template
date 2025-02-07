package sunklodas.techin.exam_template.service;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import sunklodas.techin.exam_template.exception.ProductNotFoundException;
import sunklodas.techin.exam_template.model.Listing;
import sunklodas.techin.exam_template.repository.ProductRepository;

import java.util.List;

@AllArgsConstructor
@Getter @Setter
@Service
public class ProductService {

    public static final String PRODUCT_NOT_FOUND_WITH_ID = "Product not found with id = ";


    private ProductRepository productRepository;

    public Listing findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND_WITH_ID + id));
    }

    public List<Listing> findAllProducts() { return productRepository.findAll(); }

    public Listing createProduct(Listing listing) { return productRepository.save(listing); }

    public void deleteProduct(Listing listing) {
        productRepository.delete(listing);
    }

}
