package com.example.pos.connection1.feature.vendor;

import org.springframework.data.domain.Page;
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
     Page<VendorResponse> read(
               @RequestParam(defaultValue = "10", required = false) int pageSize,
               @RequestParam(defaultValue = "0", required = false) int pageNumber) {
          return vendorService.read(pageSize, pageNumber);
     }

     @GetMapping("/{uuid}")
     VendorResponse readByUuid(@PathVariable("uuid") String uuid) {
          return vendorService.readByUuid(uuid);
     }

     @ResponseStatus(HttpStatus.NO_CONTENT)
     @DeleteMapping("/{uuid}")
     void delete(@PathVariable("uuid") String uuid) {
          vendorService.delete(uuid);
     }

     @PutMapping("/{uuid}")
     VendorResponse updateByUuid(@PathVariable("uuid") String uuid,
               @RequestBody VendorUpdateRequest vendorUpdateRequest) {
          return vendorService.updateByUuid(uuid, vendorUpdateRequest);
     }
}
