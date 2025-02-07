package sunklodas.techin.exam_template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sunklodas.techin.exam_template.model.Listing;

public interface ListingRepo extends JpaRepository<Listing, Long> {
}
