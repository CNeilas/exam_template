package vidunas.techin.exam_template.service;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import vidunas.techin.exam_template.exception.ProductNotFoundException;
import vidunas.techin.exam_template.model.Product;
import vidunas.techin.exam_template.repository.CategoryRepository;
import vidunas.techin.exam_template.repository.ProductRepository;

import java.util.List;

@AllArgsConstructor
@Getter @Setter
@Service
public class ProductService {

    public static final String PRODUCT_NOT_FOUND_WITH_ID = "Product not found with id = ";

    private final CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND_WITH_ID + id));
    }

    public List<Product> findAllProducts() { return productRepository.findAll(); }

    public Product createProduct(Product product) { return productRepository.save(product); }

    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

}
