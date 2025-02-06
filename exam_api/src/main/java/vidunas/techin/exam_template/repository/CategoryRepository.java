package vidunas.techin.exam_template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vidunas.techin.exam_template.model.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByNameContainingIgnoreCase(String searchKeyword);

    Optional<Category> findByNameIgnoreCase(String name);
}
