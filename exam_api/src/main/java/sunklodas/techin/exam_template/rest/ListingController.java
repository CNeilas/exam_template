package sunklodas.techin.exam_template.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sunklodas.techin.exam_template.model.Listing;
import sunklodas.techin.exam_template.repository.ListingRepo;

import java.util.List; import java.util.Optional;

@RestController @RequestMapping("/api/v1/listings") public class ListingController {

    @Autowired
    private ListingRepo listingRepo;

    @GetMapping
    public ResponseEntity<List<Listing>> getAllListings() {
        List<Listing> listings = listingRepo.findAll();
        return listings.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(listings, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Listing> getListingById(@PathVariable Long id) {
        Optional<Listing> listing = listingRepo.findById(id);
        return listing.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Listing> addListing(@RequestBody Listing listing) {
        return new ResponseEntity<>(listingRepo.save(listing), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Listing> updateListing(@PathVariable Long id, @RequestBody Listing newListingData) {
        return listingRepo.findById(id).map(existingListing -> {
            existingListing.setName(newListingData.getName());
            existingListing.setDescription(newListingData.getDescription());
            existingListing.setPrice(newListingData.getPrice());
            existingListing.setCity(newListingData.getCity());
            return new ResponseEntity<>(listingRepo.save(existingListing), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteListingById(@PathVariable Long id) {
        listingRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}