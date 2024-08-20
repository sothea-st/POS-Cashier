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

     //create vendor
     @PostMapping
     VendorResponse create(@Valid @RequestBody VendorRequest vendorRequest) {
          return vendorService.create(vendorRequest);
     }

     //get list vendor
     @GetMapping
     JavaCollectionResponse<?> read(
               @RequestParam(name = "pageSize", required = false) Integer pageSize,
               @RequestParam(name = "pageNumber", required = false) Integer pageNumber) {
          return vendorService.read(pageSize, pageNumber);
     }

     //get vendor by id
     @GetMapping("/{id}")
     VendorResponse readByUuid(@PathVariable("id") Integer id) {
          return vendorService.readByUuid(id);
     }

     //delete vendor
     @ResponseStatus(HttpStatus.NO_CONTENT)
     @DeleteMapping("/{id}")
     void delete(@PathVariable("id") Integer id) {
          vendorService.delete(id);
     }

     //update vendor
     @PutMapping("/{id}")
     VendorResponse updateByUuid(@PathVariable("id") Integer id,
               @Valid @RequestBody VendorUpdateRequest vendorUpdateRequest) {
          return vendorService.updateByUuid(id, vendorUpdateRequest);
     }

     //search vendor by vendor name
     @GetMapping("/searchVendor/{vendorName}")
     JavaCollectionResponse<?> search (
          @RequestParam(name = "pageSize", required = false) Integer pageSize, 
          @RequestParam(name = "pageNumber", required = false) Integer pageNumber, 
          @PathVariable("vendorName") String searchValue){
               return vendorService.search(pageSize, pageNumber, searchValue);
     }

}
