package com.example.pos.connection1.routes;
import static org.springframework.util.MimeTypeUtils.IMAGE_PNG_VALUE;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.pos.connection1.service.shiftService.CloseShiftService;
import com.example.pos.connection1.service.shiftService.OpenShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.pos.connection1.components.JavaResponse;
import com.example.pos.connection1.constant.JavaConstant;
import com.example.pos.connection1.constant.JavaValidation;
import com.example.pos.connection1.entity.Category;
import com.example.pos.connection1.entity.CloseShift;
import com.example.pos.connection1.entity.Company;
import com.example.pos.connection1.entity.Employee;
import com.example.pos.connection1.entity.Import;
import com.example.pos.connection1.entity.OpenShift;
import com.example.pos.connection1.entity.Product;
import com.example.pos.connection1.entity.Sale;
import com.example.pos.connection1.entity.Supplier;
import com.example.pos.connection1.entity.models.ProductAddRemoveQty;
import com.example.pos.connection1.entity.models.ProductModel;
import com.example.pos.connection1.entity.models.UpdateProductDiscount;
import com.example.pos.connection1.entity.sourceData.CancelItem;
import com.example.pos.connection1.entity.sourceData.CurrencyValue;
import com.example.pos.connection1.entity.sourceData.CustomerType;
import com.example.pos.connection1.entity.sourceData.Reason;
import com.example.pos.connection1.entity.sourceData.ReturnProduct;
import com.example.pos.connection1.entity.sourceData.Source;
import com.example.pos.connection1.repository.ProductRepository;
import com.example.pos.connection1.repository.productProjection.ProductProjection;
import com.example.pos.connection1.repository.shiftRepository.CloseShiftRepository;
import com.example.pos.connection1.repository.shiftRepository.OpenShiftRepository;
import com.example.pos.connection1.service.CategoryService;
import com.example.pos.connection1.service.EmployeeService;
import com.example.pos.connection1.service.ImportService;
import com.example.pos.connection1.service.ProductService;
import com.example.pos.connection1.service.SaleService;
import com.example.pos.connection1.service.SupplierService;
import com.example.pos.connection1.service.cashierReport.CashierReportService;
import com.example.pos.connection1.service.companyService.CompanyService;
import com.example.pos.connection1.service.paymentService.ReprintService;
import com.example.pos.connection1.service.sourceDataService.CancelItemService;
import com.example.pos.connection1.service.sourceDataService.CurrencyValueService;
import com.example.pos.connection1.service.sourceDataService.CustomerTypeService;
import com.example.pos.connection1.service.sourceDataService.ReasonService;
import com.example.pos.connection1.service.sourceDataService.ReturnProductService;
import com.example.pos.connection1.service.sourceDataService.SourceService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
public class RouteController {

     @RequestMapping("/api/category")
     @RestController
     public static class RouteCategory {
          @Autowired
          private CategoryService service;

          @PostMapping
          public ResponseEntity<?> saveCategory(@Valid @ModelAttribute Category c) {
               Category data = service.saveCategory(c);
               return JavaResponse.success(data);
          }
          @GetMapping("/parentId/{parentId}")
          public ResponseEntity<?> getCategory(@PathVariable("parentId") int parentId) {
               ArrayList<Category> data = service.getCategory(parentId);
               return JavaResponse.success(data);
          }

          @PutMapping("/{id}")
          public ResponseEntity<?> updateCategory(@PathVariable("id") int id, @Valid @RequestBody Category category) {
               Category data = service.updateCategory(id, category);
               return JavaResponse.success(data);
          }

          @GetMapping("/{id}")
          public ResponseEntity<?> getCategoryById(@PathVariable("id") int id) {
               Category data = service.getCategoryById(id);
               return JavaResponse.success(data);
          }

          @DeleteMapping("/{id}")
          public ResponseEntity<?> deleteCategory(@PathVariable("id") int id, @RequestBody Category c) {
               service.deleteCategory(id, c);
               return JavaResponse.deleteSuccess(id);
          }
     }

     @RequestMapping("/api/product")
     @RestController
     public static class RouteProduct {
          @Autowired
          private ProductService service;
          @Autowired
          private ProductRepository repo;

          @GetMapping(value = "/getHead")
          public ResponseEntity<?> geth(){
               return ResponseEntity.ok().body(repo.getHead());
          }

          @PostMapping
          public ResponseEntity<?> addProduct(@Valid @ModelAttribute Product product,
                    @RequestParam(value = "flagFile", required = false) MultipartFile flagFile,
                    @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
               Product data = service.addProduct(product, file, flagFile);
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
               List<ProductModel> data = service.getProductByCatId(catId, limit ,page);
               int count = service.count(catId);
               return ResponseEntity.ok().body(Map.of("msg", JavaConstant.success, "data", data, "count", count));
          }

          @GetMapping
          public ResponseEntity<?> getProduct(@RequestParam("limit") int limit) {
               HashMap<String, Object> map = new HashMap<>();
               int count = repo.countRow();
               List<ProductModel> data = service.getProduct(limit);
               return ResponseEntity.ok().body(Map.of("msg", JavaConstant.success, "data", data, "count", count));
          }

          @PostMapping("/{id}")
          public ResponseEntity<?> editProduct(@PathVariable("id") int id, @ModelAttribute Product p,
                    @RequestParam(value = "file", required = false) MultipartFile file,
                    @RequestParam(value = "flagFile", required = false) MultipartFile flagFile) throws IOException {
               Product pro = service.editProduct(id, p, file, flagFile);
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
                    @RequestParam("limit") int limit ,
                    @RequestParam("page") int page
                    ) {
               List<ProductModel> data = service.getListProductByBrandId(brandId, limit ,page);
               int count = service.countProductByBrandId(brandId);
               return ResponseEntity.ok().body(Map.of("msg", JavaConstant.success, "data", data, "count", count));
          }

          @PostMapping("/discount")
          public ResponseEntity<?> updateDis(@RequestBody UpdateProductDiscount data) {
               Product datas = service.updateDiscount(data.getId(), data.getDiscount());
               return JavaResponse.success(datas);
          }

          @GetMapping("/getNewPrdduct")
          public ResponseEntity<?> getNewProduct(){
               return JavaResponse.success(service.getNewProduct());
          }


          @GetMapping("/getProductPromotion")
          public ResponseEntity<?> getProductPromotion(){
               return JavaResponse.success(service.getProductPromotion());
          }

     }

     @RequestMapping("/api/supplier")
     @RestController
     public static class RouteSupplier {
          @Autowired
          private SupplierService service;

          @PostMapping()
          public ResponseEntity<?> addSupplier(@Valid @ModelAttribute Supplier supplier) {
               HashMap<String, String> err = new HashMap<>();
               String key = "contact";
               String contact = JavaValidation.checkPhone(supplier.getContact());

               if (!contact.isEmpty()) {
                    err.put(key, contact);
                    return ResponseEntity.status(500).body(err);
               }

               Supplier s = service.addSupplier(supplier);
               return JavaResponse.success(s);
          }

          @GetMapping()
          public ResponseEntity<?> getSupplier() {
               ArrayList<Supplier> data = service.getSupplier();
               return JavaResponse.success(data);
          }

          @GetMapping("/{id}")
          public ResponseEntity<?> getSupplierById(@PathVariable("id") int id) {
               Supplier data = service.getSupplierById(id);
               return JavaResponse.success(data);
          }

          @PutMapping("/{id}")
          public ResponseEntity<?> updateSupplier(@PathVariable("id") int id, @Valid @RequestBody Supplier s) {
               HashMap<String, String> err = new HashMap<>();
               String key = "contact";
               String contact = JavaValidation.checkPhone(s.getContact());

               if (!contact.isEmpty()) {
                    err.put(key, contact);
                    return ResponseEntity.status(500).body(err);
               }
               Supplier data = service.updateSupplier(id, s);
               return JavaResponse.success(data);
          }

          @DeleteMapping("/{id}")
          public ResponseEntity<?> deleteSupplier(@PathVariable("id") int id, @RequestBody Supplier s) {
               service.deleteSupplier(id, s);
               return JavaResponse.success("delete success");
          }
     }

     @RequestMapping("/api/employee")
     @RestController
     public static class RouteEmployee {
          @Autowired
          private EmployeeService service;

          @PostMapping
          public ResponseEntity<?> addEmployee(@Valid @ModelAttribute Employee e,
                    @RequestParam(value = "image", required = false) MultipartFile file) throws IOException {
               HashMap<String, String> err = new HashMap<>();
               String key = "contact";
               String contact = JavaValidation.checkPhone(e.getContact());

               if (!contact.isEmpty()) {
                    err.put(key, contact);
                    return ResponseEntity.status(500).body(err);
               }
               Employee data = service.addEmployee(e, file);

               return JavaResponse.success(data);
          }

          @GetMapping
          public ResponseEntity<?> getEmployee() {
               List<Employee> data = service.getEmployee();
               return JavaResponse.success(data);
          }

          @GetMapping("/{id}")
          public ResponseEntity<?> getEmployeeById(@PathVariable("id") int id) {
               Employee data = service.getEmployeeById(id);
               return JavaResponse.success(data);
          }

          @DeleteMapping("/{id}")
          public ResponseEntity<?> deleteEmployeeById(@PathVariable("id") int id, @RequestBody Employee e) {
               service.deleteEmployeeById(id, e);
               return JavaResponse.deleteSuccess(id);
          }

          @PostMapping("/{id}")
          public ResponseEntity<?> updateEmployee(@Valid @PathVariable("id") int id, @ModelAttribute Employee e,
                    @RequestParam("image") MultipartFile file) throws IOException {
               HashMap<String, String> err = new HashMap<>();
               String key = "contact";
               String contact = JavaValidation.checkPhone(e.getContact());

               if (!contact.isEmpty()) {
                    err.put(key, contact);
                    return ResponseEntity.status(500).body(err);
               }
               Employee data = service.updateEmployee(id, e, file);
               return JavaResponse.success(data);
          }

          @GetMapping("/readFileById/{id}")
          public ResponseEntity<byte[]> getImage(@PathVariable("id") String id) throws IOException {
               byte[] data = service.getImageEmployee(id);
               return ResponseEntity.status(HttpStatus.OK)
                         .contentType(MediaType.valueOf(IMAGE_PNG_VALUE))
                         .body(data);
          }

     }

     @RestController
     @RequestMapping("/api/import")
     public static class RouteImport {
          @Autowired
          private ImportService service;

          @PostMapping
          public ResponseEntity<?> addImport(@Valid @RequestBody Import i) {
               service.addImport(i);
               return JavaResponse.success("success insert");
          }

          @PostMapping("/updateQty")
          public ResponseEntity<?> updateQty(@RequestBody ProductAddRemoveQty p){
               int _qty = service.updateQty(p);
               return ResponseEntity.ok().body(Map.of("qtyUpdate",_qty,"msg","success"));
          }

     }

     @RestController
     @RequestMapping("/api/sale")
     public static class RouteSale {
          @Autowired
          private SaleService service;
          @Autowired
          private HttpSession session;
          @Autowired
          private OpenShiftRepository repoOpen;

          @PostMapping
          public ResponseEntity<?> saleProduct(@Valid @RequestBody Sale s) throws Exception {
               var userCode = session.getAttribute(JavaConstant.userCode);

               OpenShift countOpenShift = repoOpen.countOpenShift(s.getUserCode(), JavaConstant.currentDate);
               HashMap<String, Object> map = new HashMap<>();
               // protect when user try to processing sale but user does not open shift first
               if (countOpenShift == null || countOpenShift.getNumberOpenShift() == 0) {
                    map.put(JavaConstant.message, JavaConstant.openShift);
                    return JavaResponse.error(map);
               }
               var data = service.saleProduct(s);
               return JavaResponse.success(data);
          }
     }

     @RestController
     @RequestMapping("/api/reprint")
     public static class RouteReprint {
          @Autowired
          private ReprintService service;

          @GetMapping("/{paymentNo}")
          public ResponseEntity<?> getData(@PathVariable("paymentNo") String paymentNo) {
               var data = service.readData(paymentNo);
               return JavaResponse.success(data);
          }

          @GetMapping 
          public ResponseEntity<?> getData() {
               var data = service.readData("");
               return JavaResponse.success(data);
          }
     }

     @RestController
     @RequestMapping("/api/source")
     public static class RouteSource {
          @Autowired
          private SourceService service;

          @PostMapping
          public ResponseEntity<?> addSource(@Valid @ModelAttribute Source s) {
               Source data = service.addSource(s);
               return JavaResponse.success(data);
          }

          @GetMapping
          public ResponseEntity<?> getSource() {
               List<Source> data = service.getSource();
               return JavaResponse.success(data);
          }

          @GetMapping("/{id}")
          public ResponseEntity<?> getSourceById(@PathVariable("id") int id) {
               Source data = service.getSourceById(id);
               return JavaResponse.success(data);
          }

          @DeleteMapping("/{id}")
          public ResponseEntity<?> deleteSource(@PathVariable("id") int id, @RequestBody Source s) {
               service.deleteSource(id, s);
               return JavaResponse.deleteSuccess(id);
          }

          @PutMapping("/{id}")
          public ResponseEntity<?> updateSource(@PathVariable("id") int id, @RequestBody Source s) {
               Source data = service.updateSource(id, s);
               return JavaResponse.success(data);
          }
     }

     @RestController
     @RequestMapping("/api/customerType")
     public static class RouteCustomerType {
          @Autowired
          private CustomerTypeService service;

          @PostMapping
          public ResponseEntity<?> add(@Valid @ModelAttribute CustomerType c) {
               CustomerType data = service.add(c);
               return JavaResponse.success(data);
          }

          @GetMapping
          public ResponseEntity<?> read() {
               List<CustomerType> data = service.read();
               return JavaResponse.success(data);
          }

          @GetMapping("/{id}")
          public ResponseEntity<?> readById(@PathVariable("id") int id) {
               CustomerType data = service.readById(id);
               return JavaResponse.success(data);
          }

          @DeleteMapping("/{id}")
          public ResponseEntity<?> delete(@PathVariable("id") int id, @RequestBody CustomerType c) {
               service.delete(id, c);
               return JavaResponse.deleteSuccess(id);
          }

          @PutMapping("/{id}")
          public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody CustomerType c) {
               CustomerType data = service.update(id, c);
               return JavaResponse.success(data);
          }
     }

     @RestController
     @RequestMapping("/api/company")
     public static class RouteCompany {
          @Autowired
          private CompanyService service;

          @PostMapping
          public ResponseEntity<?> addCompany(@Valid @ModelAttribute Company c,
                    @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
               Company data = service.addCompany(c, file);
               return JavaResponse.success(data);
          }

          @GetMapping
          public ResponseEntity<?> getCompnay() {
               List<Company> data = service.getCompany();
               return JavaResponse.success(data);
          }

          @GetMapping("/{id}")
          public ResponseEntity<?> getCompanyById(@PathVariable("id") int id) {
               Company data = service.getCompanyById(id);
               return JavaResponse.success(data);
          }

          @DeleteMapping("/{id}")
          public ResponseEntity<?> deleteCompany(@PathVariable("id") int id, @RequestBody Company c) {
               service.deleteCompany(id, c);
               return JavaResponse.deleteSuccess(id);
          }

          @PostMapping("/{id}")
          public ResponseEntity<?> updateCompany(@PathVariable("id") int id, @ModelAttribute Company c,
                    @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
               Company data = service.updateCompany(id, c, file);
               return JavaResponse.success(data);
          }
     }

     @RestController
     @RequestMapping("/api/cancelItem")
     public static class RouteCancel {
          @Autowired
          private CancelItemService service;

          @PostMapping("/{type}")
          public ResponseEntity<?> cancelItem(@PathVariable("type") String type, @RequestBody CancelItem c) {
               service.cancelAndDeleteItem(c, type);
               return JavaResponse.success("succes delelte item");
          }
     }

     @RestController
     @RequestMapping("/api/openShiftTime")
     public static class RouteOpenShift {
          @Autowired
          private OpenShiftService service;

          @PostMapping
          public ResponseEntity<?> openShift(@Valid @RequestBody OpenShift o) {
               OpenShift data = service.openShift(o);
               return JavaResponse.success(data);
          }

          @GetMapping("/{userCode}")
          public ResponseEntity<?> getOpenShift(@PathVariable("userCode") String userCode) {
               OpenShift data = service.getOpenShift(userCode);
               return JavaResponse.success(data);
          }
     }

     @RestController
     @RequestMapping("/api/closeShiftTime")
     public static class RouteCloseShift {
          @Autowired
          private CloseShiftService service;

          @Autowired
          private HttpSession session;

          @Autowired
          private CloseShiftRepository repoClose;

          @Autowired
          private OpenShiftRepository repoOpen;

          @PostMapping
          public ResponseEntity<?> closeShift(@RequestBody CloseShift c) {
               HashMap<String, Object> map = new HashMap<>();

               OpenShift countOpenShift = repoOpen.countOpenShift(c.getUserCode(), JavaConstant.currentDate);
               // protect when user try to processing sale but user does not open shift first
               if (countOpenShift == null || countOpenShift.getNumberOpenShift() == 0) {
                    map.put(JavaConstant.message, JavaConstant.closeOpenShfitFirst);
                    return JavaResponse.error(map);
               }

               CloseShift data = service.closeShift(c);
               return JavaResponse.success(data);
          }
     }

     @RestController
     @RequestMapping("/api/cashierReport")
     public static class RouteCashierRepot {
          @Autowired
          private CashierReportService service;

          @Autowired
          private CloseShiftRepository repoClose;

          @GetMapping
          public ResponseEntity<?> getCashierReport(@RequestParam("userCode") String userCode,
                    @RequestParam("userId") int userId, @RequestParam("posId") String posId) {
               HashMap<String, Object> map = new HashMap<>();
               CloseShift closeShift = repoClose.getCloseShift(userCode, JavaConstant.currentDate, posId);
               // protect when user try to processing sale but user does not open shift first
               if (closeShift == null) {
                    map.put(JavaConstant.message, JavaConstant.msgCloseShift);
                    return JavaResponse.error(map);
               }
               return JavaResponse.success(service.cashierReport(userCode, userId, posId));
          }
     }

     @RestController
     @RequestMapping("/api/reason")
     public static class RouteReason {
          @Autowired
          private ReasonService service;

          @PostMapping
          public ResponseEntity<?> addReason(@Valid @RequestBody Reason reason) {
               Reason data = service.addReason(reason);
               return JavaResponse.success(data);
          }

          @GetMapping
          public ResponseEntity<?> getReason() {
               List<Reason> data = service.getReason();
               return JavaResponse.success(data);
          }

          @GetMapping("/{id}")
          public ResponseEntity<?> getReasonById(@PathVariable("id") int id) {
               Reason data = service.getReasonById(id);
               return JavaResponse.success(data);
          }

          @DeleteMapping("/{id}")
          public ResponseEntity<?> delete(@PathVariable("id") int id, @RequestBody Reason r) {
               service.delete(id, r);
               return JavaResponse.deleteSuccess(id);
          }

          @PutMapping("/{id}")
          public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Reason r) {
               Reason data = service.update(id, r);
               return JavaResponse.success(data);
          }

          @GetMapping("/getReasonByCode/{code}")
          public ResponseEntity<?> getReasonByCode(@PathVariable("code") String code) {
               List<Reason> data = service.getReasonByCode(code);
               return JavaResponse.success(data);
          }
     }

     @RestController
     @RequestMapping("/api/returnProduct")
     public static class RouteReturnProduct {
          @Autowired
          private ReturnProductService service;

          @PostMapping
          public ResponseEntity<?> returnProduct(@Valid @RequestBody ReturnProduct r) {
              Map<String,Object>  map = service.returnProduct(r);
               return JavaResponse.success(map);
          }

          @GetMapping("/{barcode}")
          public ResponseEntity<?> getProductByBarcode(@PathVariable("barcode") String barcode) {
               ProductProjection data = service.searchProdcutByBarcode(barcode);
               return JavaResponse.success(data);
          }
     }

     @RestController
     @RequestMapping("/api/currencyValue")
     public static class RouteCurrenValue {
          @Autowired
          private CurrencyValueService service;

          @PostMapping
          public ResponseEntity<?> addCurrency(@Valid @RequestBody CurrencyValue c) {
               CurrencyValue data = service.addCurrency(c);
               return JavaResponse.success(data);
          }

          @GetMapping
          public ResponseEntity<?> getAllCurrency() {
               List<CurrencyValue> data = service.getAllCurrencyValue();
               return JavaResponse.success(data);
          }

          @GetMapping("/{id}")
          public ResponseEntity<?> getCurrencyById(@PathVariable("id") int id) {
               CurrencyValue data = service.getCurrencyById(id);
               return JavaResponse.success(data);
          }

          @DeleteMapping("/{id}")
          public ResponseEntity<?> deleteCurrency(@PathVariable("id") int id, @RequestBody CurrencyValue c) {
               service.deleteCurrency(id, c);
               return JavaResponse.deleteSuccess(id);
          }

          @PutMapping("/{id}")
          public ResponseEntity<?> updateCurrency(@PathVariable("id") int id, @RequestBody CurrencyValue c) {
               CurrencyValue data = service.updateCurrency(id, c);
               return JavaResponse.success(data);
          }
     }

}
