package com.example.pos.system.feature.product;

import com.example.pos.system.constant.JavaResponse;
import com.example.pos.system.constant.JavaConstant;
import com.example.pos.system.domain.Product;
import com.example.pos.system.domain.models.ProductModel;
import com.example.pos.system.domain.models.UpdateProductDiscount;
import com.example.pos.system.feature.product.dto.ProductDataRequest;
import com.example.pos.system.feature.product.productService.ProductExcelServic;
import com.example.pos.system.feature.product.productService.ProductService;
import com.example.pos.system.feature.product.dto.ProductMultiple;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.springframework.util.MimeTypeUtils.IMAGE_PNG_VALUE;
@RequestMapping("/api/product")
@RestController
@RequiredArgsConstructor
public class ProductControllerV0 {
        private final ProductService service;
        private final ProductRepository repo;
        private final ProductExcelServic productExcelServic;
 

        @GetMapping(value = "/getHead")
        public ResponseEntity<?> geth() {
            return ResponseEntity.ok().body(repo.getHead());
        }

        @PostMapping("/importMultiple")
        public void addMultipleProduct(@RequestBody ProductMultiple lists) {

            // Map<String, Object> response = productMultipleService.addMultipleProduct(lists);

            // int code = (int) response.get("code"); // Assuming 'code' is returned as an integer

            // if (code == 409) {
            //     // Conflict: Barcode already exists
            //     return ResponseEntity.ok().body(Map.of("msg", "conflict", "data", "Barcode : "
            //             + response.get("barcode") + " already exists for one or more products in the list."));
            // } else if (code == 200) {
            //     // Success: All products imported successfully
            //     return JavaResponse.success("Import Success");
            // } else {
            //     // Handle other status codes as needed
            //     throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
            //             "Unexpected status returned from service.");
            // }
        }

        @PostMapping("/excel")
        public ResponseEntity<?> importFileExcel(@RequestParam("file") MultipartFile multipartFile)
                throws IOException {
            productExcelServic.importFileExcel(multipartFile);
            return JavaResponse.success("Import Success");
        }

        @PostMapping
        public ResponseEntity<?> addProduct(@Valid @ModelAttribute ProductDataRequest productDataRequest,
                                            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {

            Product data = service.addProduct(productDataRequest, file);
            return JavaResponse.success(data);
        }

        // get image from path assets\\product\\imageName
        @GetMapping(value = "/image/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
        public byte[] getImage(@PathVariable("imageName") String imageName) throws IOException {
            return service.getImage(imageName);
        }

        @GetMapping("/getProductByCatId")
        public ResponseEntity<?> getProductByCatId(@RequestParam("catId") int catId,
                                                   @RequestParam("limit") int limit, @RequestParam("page") int page) {
            List<ProductModel> data = service.getProductByCatId(catId, limit, page);
            int count = service.count(catId);
            return ResponseEntity.ok().body(Map.of("msg", JavaConstant.success, "data", data, "count", count));
        }

        @GetMapping
        public ResponseEntity<?> getProduct(
                @RequestParam("limit") int limit,
                @RequestParam int perPage,
                @RequestParam int page) {
            int count = repo.countRow();
            List<ProductModel> data = service.getProduct(limit, perPage, page);
            return ResponseEntity.ok().body(Map.of("msg", JavaConstant.success, "data", data, "count", count));
        }

        @PostMapping("/{id}")
        public ResponseEntity<?> editProduct(@PathVariable("id") int id, @ModelAttribute Product p,
                                             @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
            Product pro = service.editProduct(id, p, file);
            return JavaResponse.success(pro);
        }

        @GetMapping("/{id}")
        public ResponseEntity<?> getDataById(@PathVariable("id") int id) {
            Product data = service.readData(id);
            return JavaResponse.success(data);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteProduct(@PathVariable("id") int id, @RequestBody Product p) {
            service.deleteProduct(id, p);
            return JavaResponse.deleteSuccess(id);
        }

        @GetMapping("/readFileById/{id}")
        public ResponseEntity<byte[]> getFile(@PathVariable String id) throws IOException {
            byte[] imageData = service.getFile(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.valueOf(IMAGE_PNG_VALUE))
                    .body(imageData);
        }

        @GetMapping("/getProductByBrandId")
        public ResponseEntity<?> getProductByBrandId(@RequestParam("brandId") int brandId,
                                                     @RequestParam("limit") int limit,
                                                     @RequestParam("page") int page) {
            List<ProductModel> data = service.getListProductByBrandId(brandId, limit, page);
            int count = service.countProductByBrandId(brandId);
            return ResponseEntity.ok().body(Map.of("msg", JavaConstant.success, "data", data, "count", count));
        }

        @PostMapping("/discount")
        public ResponseEntity<?> updateDis(@RequestBody UpdateProductDiscount data) {
            Product datas = service.updateDiscount(data.getId(), data.getDiscount());
            return JavaResponse.success(datas);
        }

        @GetMapping("/getNewProduct")
        public ResponseEntity<?> getNewProduct(@RequestParam("limit") int limit, @RequestParam int page) {
            // return JavaResponse.success(service.getNewProduct(limit,perPage,page));
            int countRow = repo.countRow();
            int number = 21;

//            if( countRow == 0 ) {
//                number = 21;
//            } else {
//                number = (countRow * 30) / 100;
//            }

            return ResponseEntity.ok().body(
                    Map.of("count", number, "msg", "success", "data", service.getNewProduct(limit, page, number)));
        }

        @GetMapping("/getProductPromotion")
        public ResponseEntity<?> getProductPromotion() {
            return JavaResponse.success(service.getProductPromotion());
        }


}
