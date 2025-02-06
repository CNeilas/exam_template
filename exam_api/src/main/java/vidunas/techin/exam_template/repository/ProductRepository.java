package vidunas.techin.exam_template.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vidunas.techin.exam_template.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameContainingIgnoreCase(String title);

    List<Product> findByCategoriesId(Long id);

    List<Product> findByCategoriesNameIgnoreCase(String name);
}
