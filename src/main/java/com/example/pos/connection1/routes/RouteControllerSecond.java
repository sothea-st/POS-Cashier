package com.example.pos.connection1.routes;

import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.pos.connection1.repository.roleAndPermissionRepository.RoleRepository;
import com.example.pos.connection1.service.HoldService;
import com.example.pos.connection1.service.shiftService.DefaultPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.multipart.MultipartFile;

import com.example.pos.connection1.components.JavaResponse;
import com.example.pos.connection1.constant.JavaConstant;
import com.example.pos.connection1.constant.JavaMessage;
import com.example.pos.connection1.controller.generateBarcode.BarcodeGenerator;
import com.example.pos.connection1.entity.Hold;
import com.example.pos.connection1.entity.branch.Branch;
import com.example.pos.connection1.entity.models.PaymentModel;
import com.example.pos.connection1.entity.models.ProductModel;
import com.example.pos.connection1.entity.people.Customer;
import com.example.pos.connection1.entity.projection.PaymentProjection;
import com.example.pos.connection1.entity.role.Role;
import com.example.pos.connection1.entity.role.roleProjection.RoleProjection;
import com.example.pos.connection1.entity.sourceData.AssignRole;
import com.example.pos.connection1.entity.sourceData.Brand;
import com.example.pos.connection1.entity.sourceData.DefaultPrice;
import com.example.pos.connection1.entity.sourceData.TaxProduct;
import com.example.pos.connection1.feature.vendor.VendorService;
import com.example.pos.connection1.feature.vendor.dto.VendorRequest;
import com.example.pos.connection1.feature.vendor.dto.VendorResponse;
import com.example.pos.connection1.feature.vendor.dto.VendorUpdateRequest;
import com.example.pos.connection1.projections.CustomerPointProjection.CustomerPointProjection;
import com.example.pos.connection1.projections.TaxProductProjection.TaxProductProjection;
import com.example.pos.connection1.projections.defaultPriceProjection.DefaultPriceProjection;
import com.example.pos.connection1.repository.HoldRepository;
import com.example.pos.connection1.repository.UserRepository;
import com.example.pos.connection1.repository.paymentRepository.PaymentRepository;
import com.example.pos.connection1.repository.peopleRepository.CustomerRepository;
import com.example.pos.connection1.service.RoleAndPermissionService.RoleService;
import com.example.pos.connection1.service.addImageService.AddImageService;
import com.example.pos.connection1.service.branchService.BranchService;
import com.example.pos.connection1.service.searchByBarcodeOrNameService.SearchByBarcodeOrNameService;
import com.example.pos.connection1.service.sourceDataService.BrandService;
import com.example.pos.connection1.service.sourceDataService.CustomerService;
import com.example.pos.connection1.service.sourceDataService.TaxProductService;

import java.awt.image.BufferedImage;
import java.io.IOException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
public class RouteControllerSecond {
     @RestController
     @RequestMapping("/api/branch")
     public static class RouteBranch {
          @Autowired
          private BranchService service;

          @PostMapping
          public ResponseEntity<?> addBranch(@Valid @RequestBody Branch b) {
               Branch data = service.addBranch(b);
               return JavaResponse.success(data);
          }

          @GetMapping
          public ResponseEntity<?> getAllBranch() {
               List<Branch> data = service.getAllBranch();
               return JavaResponse.success(data);
          }

          @GetMapping("/{id}")
          public ResponseEntity<?> getBranchById(@PathVariable("id") int id) {
               Branch data = service.getBranchById(id);
               return JavaResponse.success(data);
          }

          @DeleteMapping("/{id}")
          public ResponseEntity<?> deleteBranch(@PathVariable("id") int id, @RequestBody Branch b) {
               service.deleteBranch(id, b);
               return JavaResponse.deleteSuccess(id);
          }

          @PutMapping("/{id}")
          public ResponseEntity<?> updateBranch(@PathVariable("id") int id, @RequestBody Branch b) {
               Branch data = service.updateBranch(id, b);
               return JavaResponse.success(data);
          }
     }

     @RestController
     @RequestMapping("/api/brand")
     public static class RouteBrand {
          @Autowired
          private BrandService service;

          @PostMapping
          public ResponseEntity<?> add(@Valid @RequestBody Brand b) {
               Brand data = service.add(b);
               return JavaResponse.success(data);
          }

          @GetMapping
          public ResponseEntity<?> read() {
               List<Brand> data = service.read();
               return JavaResponse.success(data);
          }

          @GetMapping("/{id}")
          public ResponseEntity<?> getBrandById(@PathVariable("id") int id) {
               Brand data = service.getBrandById(id);
               return JavaResponse.success(data);
          }

          @DeleteMapping("/{id}")
          public ResponseEntity<?> deleteBrand(@PathVariable("id") int id, @RequestBody Brand b) {
               service.deleteBrand(id, b);
               return JavaResponse.deleteSuccess(id);
          }

          @PutMapping("/{id}")
          public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Brand b) {
               Brand data = service.updateBrand(id, b);
               return JavaResponse.success(data);
          }
     }

     @RestController
     @RequestMapping("/api/searchProductByBarcodeOrName")
     public static class RouteSearchProduct {
          @Autowired
          private SearchByBarcodeOrNameService service;

          @GetMapping
          public ResponseEntity<?> search(@RequestParam("code") String code,
                    @RequestParam("valueSearch") String valueSearch) {
               List<ProductModel> data = service.search(code, valueSearch);
               return JavaResponse.success(data);
          }

          @GetMapping("/searchWithInvoice")
          public ResponseEntity<?> searchInvoice(@RequestParam("invoiceNo") String invoiceNo ,
           @RequestParam(value = "barcode" , required = false)  String barcode) {
               Map<String, Object> data = service.searchWithInvoiceNo(invoiceNo,barcode); 
              
               if( data.get("msg").equals(JavaConstant.INVOICE_NUMBER_DOES_NOT_EXIST) ) {
                    return ResponseEntity.ok().body(data);
               }

               if( data.get("msg").equals(JavaConstant.PRODUCT_DOES_NOT_EXIST_IN_INVOICE_NUMBER) ) {
                    return ResponseEntity.ok().body(data);
               }

               return ResponseEntity.ok().body(data);
          }

          @GetMapping("/getInvoice/{paymentBarcode}")
          public ResponseEntity<?> getInvoice(@PathVariable("paymentBarcode") String paymentBarcode) {
               return JavaResponse.success(service.getIncoive(paymentBarcode));
          }


          @GetMapping("/getProductByBarcodeInInvoice")
          public ResponseEntity<?> getProductByBarcodeInInvoice(@RequestParam("barcode") String barcode , @RequestParam("invoiceNumber") String invoiceNumber) {
               return JavaResponse.success(service.getProductByBarcodeInInvoice(barcode,invoiceNumber));
          }
   
     }

     @RestController
     @RequestMapping("/api/role")
     public static class RouteRole {
          @Autowired
          private RoleService service;

          @PostMapping
          public ResponseEntity<?> add(@Valid @RequestBody Role r) {
               Role data = service.add(r);
               return JavaResponse.success(data);
          }

          @GetMapping
          public ResponseEntity<?> read() {
               List<RoleProjection> data = service.getRole();
               return JavaResponse.success(data);
          }

          @GetMapping("/{id}")
          public ResponseEntity<?> getRoleById(@PathVariable("id") int id) {
               Role data = service.getRoleById(id);
               return JavaResponse.success(data);
          }

          @DeleteMapping("/{id}")
          public ResponseEntity<?> deleteRole(@PathVariable("id") int id, @RequestBody Role role) {
               service.deleteRoleById(id, role);
               return JavaResponse.deleteSuccess(id);
          }

          @PutMapping("/{id}")
          public ResponseEntity<?> updateRole(@PathVariable("id") int id, @RequestBody Role role) {
               Role data = service.updateRoleById(id, role);
               return JavaResponse.success(data);
          }
     }

     @RestController
     @RequestMapping("/api/assignRole")
     public static class RouteAssignRole {
          @Autowired
          private RoleService service;

          @Autowired
          private RoleRepository repoRole;

          @Autowired
          private UserRepository repoUser;

          @PostMapping
          public ResponseEntity<?> assignRole(@RequestParam("assignerId") int assignerId, @RequestBody AssignRole a) {
               int roleId = repoUser.findById(assignerId).get().getRole();
               String roleName = repoRole.findById(roleId).get().getRoleName();
               if (!roleName.equals(JavaConstant.admin)) {
                    return JavaResponse.success("This account have no permission assign role!");
               }
               service.assignRole(a);
               return JavaResponse.success("Assign role success");
          }
     }

     @RestController
     @RequestMapping("/api/generateBarcode")
     public static class RouteGenerateBarcode {
          @Autowired
          BarcodeGenerator barcodeGenerator;

          @GetMapping(value = "/barcodes/{barcode}", produces = IMAGE_PNG_VALUE)
          public ResponseEntity<BufferedImage> generate(@PathVariable("barcode") final String barcodeText)
                    throws Exception {
               return ResponseEntity.ok().body(barcodeGenerator.generateEAN128BarCodeImage(barcodeText));
          }

          @GetMapping(value = "/receiptBarcode/{barcode}", produces = IMAGE_PNG_VALUE)
          public ResponseEntity<BufferedImage> generates(@PathVariable("receiptBarcode") final String receiptBarcode)
                    throws Exception {
               return ResponseEntity.ok().body(barcodeGenerator.generateEAN128BarCodeImage(receiptBarcode));
          }

     }

     @RestController
     @RequestMapping("/api/defaultPrice")
     public static class RouteDefaultPrice {
          @Autowired
          private DefaultPriceService service;

          @PostMapping
          public ResponseEntity<?> addDefaultPrice(@Valid @RequestBody DefaultPrice d) {

               HashMap<String, String> error = new HashMap<>();
               if (d.getCreateBy() == 0)
                    error.put("createBy", JavaMessage.required);
               if (d.getDefaultPriceKhr() == null)
                    error.put("defaultPriceKhr", JavaMessage.required);
               if (d.getDefaultPriceUsd() == null)
                    error.put("defaultPriceUsd", JavaMessage.required);
               if (!error.isEmpty())
                    return JavaResponse.error(error);

               DefaultPrice data = service.addDefaultPrice(d);
               return JavaResponse.success(data);
          }

          @GetMapping
          public ResponseEntity<?> getListDefaultPrice() {
               List<DefaultPrice> data = service.getListDefaultPrice();
               return JavaResponse.success(data);
          }

          @GetMapping("/{id}")
          public ResponseEntity<?> getDefaltPriceById(@PathVariable("id") int id) {
               DefaultPriceProjection data = service.getDefaultPriceById(id);
               return JavaResponse.success(data);
          }

          @PutMapping("/{id}")
          public ResponseEntity<?> updateDefaultPrice(@PathVariable("id") int id, @RequestBody DefaultPrice d) {

               DefaultPrice data = service.updateDefaultPrice(id, d);
               return JavaResponse.success(data);
          }

          @DeleteMapping("/{id}")
          public ResponseEntity<?> deleteDefaultPrice(@PathVariable("id") int id, @RequestBody DefaultPrice d) {
               service.deleteDefaultPriceById(id, d);
               return JavaResponse.deleteSuccess(id);
          }

     }

     @RestController
     @RequestMapping("/api/customer")
     public static class RouteCustomer {
          @Autowired
          private CustomerService service;
          @Autowired
          private CustomerRepository repo;

          @PostMapping
          public ResponseEntity<?> add(@RequestBody Customer c) {
               HashMap<String, Object> errMap = new HashMap<>();
               boolean isExist = repo.existsByContact(c.getContact());
               if (isExist) {
                    errMap.put("msg", "The phone number already uesd!");
                    errMap.put("status", 500);
                    return ResponseEntity.status(500).body(errMap);
               }
               if (c.checkPhone(c.getContact()) != null)
                    return c.checkPhone(c.getContact());
               Customer data = service.add(c);
               return ResponseEntity.ok().body(Map.of("msg", "success", "status", 200));
          }

          @GetMapping
          public ResponseEntity<?> read() {
               List<Customer> data = service.read();
               return JavaResponse.success(data);
          }

          @GetMapping("/{id}")
          public ResponseEntity<?> getById(@PathVariable("id") int id) {
               Customer data = service.readById(id);
               return JavaResponse.success(data);
          }

          @DeleteMapping("/{id}")
          public ResponseEntity<?> delete(@PathVariable("id") int id, @RequestBody Customer c) {
               service.deleteById(id, c);
               return JavaResponse.deleteSuccess(id);
          }

          @PutMapping("/{id}")
          public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Customer c) {
               Customer data = service.update(id, c);
               return JavaResponse.success(data);
          }

          @GetMapping("/getCustomerId")
          public ResponseEntity<?> getCusotmerId() {
               String data = service.getCustomerId();
               return JavaResponse.success(data);
          }

          @GetMapping("/getCustomerPoint/{phone}")
          public ResponseEntity<?> getCustomerPoint(@PathVariable("phone") String phone) {
               CustomerPointProjection data = service.getPoint(phone);
               return JavaResponse.success(data);
          }

     }

     @RestController
     @RequestMapping("/api/public/addImageForBackground")
     public static class RouteAddImage {
          @Autowired
          private AddImageService service;

          @PostMapping
          public ResponseEntity<?> addImage(@RequestParam("file") MultipartFile file) throws IOException {
              String fileName = service.addImage(file);
               return ResponseEntity.ok().body(Map.of("msg","success","fileName",fileName));
          }

 

          @GetMapping("/{id}")
          public ResponseEntity<byte[]> getFile(@PathVariable String id) throws IOException {
               byte[] imageData = service.getFile(id);
               return ResponseEntity.status(HttpStatus.OK)
                         .contentType(MediaType.valueOf(IMAGE_PNG_VALUE))
                         .body(imageData);
          }
     }

     @RestController
     @RequestMapping("/api/hold")
     public static class RouteHold {
          @Autowired
          private HoldService service;

          @Autowired
          private HoldRepository repo;

          @PostMapping
          public ResponseEntity<?> addHolde(@RequestBody Hold h) {
               Hold data = service.addHold(h);
               return JavaResponse.success(data);
          }

          @GetMapping
          public ResponseEntity<?> getHold(@RequestParam("userId") int userID ,
           @RequestParam(value = "id" , required = false) Integer id) {

               HashMap<String, Object> data = service.getHold(userID,id);
               long count = repo.countResult(userID);
               data.put("count", count);
               data.put("msg", "success");
               return ResponseEntity.ok().body(data);
          }

          @DeleteMapping
          public ResponseEntity<?> deleteHold(@RequestBody Hold h) {
               service.deleteHold(h);
               return JavaResponse.success("delete success");
          }

          @DeleteMapping("/deleteByHoldId")
          public ResponseEntity<?> deleteByHoldId(@RequestParam("holdId") int holdId) {
               service.deleteHoldById(holdId);
               return JavaResponse.success("delete success");
          }

          @GetMapping("/deleteByItem")
          public ResponseEntity<?> deleteByItem(@RequestParam("holdId") int holdId, @RequestParam("proId") int proId) {
               service.deleteHoldByItem(holdId, proId);
               return JavaResponse.success(JavaConstant.success);
          }
     }

     @RestController
     @RequestMapping("/api/taxProduct")
     public static class RouteTaxName {
          @Autowired
          private TaxProductService service;

          @PostMapping
          public ResponseEntity<?> add(@Valid @RequestBody TaxProduct t) {
               TaxProduct data = service.add(t);
               return JavaResponse.success(data);
          }

          @GetMapping
          public ResponseEntity<?> read() {
               List<TaxProductProjection> data = service.read();
               return JavaResponse.success(data);
          }

          @GetMapping("/{id}")
          public ResponseEntity<?> getById(@PathVariable("id") int id) {
               TaxProductProjection data = service.getById(id);
               return JavaResponse.success(data);
          }

          @PutMapping("/{id}")
          public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody TaxProduct t) {
               TaxProduct data = service.update(id, t);
               return JavaResponse.success(data);
          }

          @DeleteMapping("/{id}")
          public ResponseEntity<?> delete(@PathVariable("id") int id, @RequestBody TaxProduct t) {
               service.delete(id, t);
               return JavaResponse.deleteSuccess(id);
          }
     }

 

}
