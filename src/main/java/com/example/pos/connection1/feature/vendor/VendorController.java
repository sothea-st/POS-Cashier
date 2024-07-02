package com.example.pos.connection1.feature.vendor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.pos.connection1.feature.vendor.dto.VendorRequest;
import com.example.pos.connection1.feature.vendor.dto.VendorResponse;
import com.example.pos.connection1.feature.vendor.dto.VendorUpdateRequest;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/vendor")
@RequiredArgsConstructor
public class VendorController {

     private final VendorService vendorService;

     @PostMapping
     VendorResponse create(@Valid @RequestBody VendorRequest vendorRequest) {
          return vendorService.create(vendorRequest);
     }

     @GetMapping
     JavaCollectionResponse<?> read(
               @RequestParam(defaultValue = "10", required = false) int pageSize,
               @RequestParam(defaultValue = "0", required = false) int pageNumber) {
          return vendorService.read(pageSize, pageNumber);
     }

     @GetMapping("/{id}")
     VendorResponse readByUuid(@PathVariable("id") int id) {
          return vendorService.readByUuid(id);
     }

     @ResponseStatus(HttpStatus.NO_CONTENT)
     @DeleteMapping("/{id}")
     void delete(@PathVariable("id") int id) {
          vendorService.delete(id);
     }

     @PutMapping("/{id}")
     VendorResponse updateByUuid(@PathVariable("id") int id,
               @Valid @RequestBody VendorUpdateRequest vendorUpdateRequest) {
          return vendorService.updateByUuid(id, vendorUpdateRequest);
     }

}
