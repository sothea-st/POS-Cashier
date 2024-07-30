package com.example.pos.connection1.feature.imports;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.pos.connection1.constant.JavaConstant;
import com.example.pos.connection1.entity.Category;
import com.example.pos.connection1.entity.Employee;
import com.example.pos.connection1.entity.Import;
import com.example.pos.connection1.entity.ImportDetail;
import com.example.pos.connection1.entity.ImportDetailTemporary;
import com.example.pos.connection1.entity.Product;
import com.example.pos.connection1.entity.User;
import com.example.pos.connection1.entity.Vendor;
import com.example.pos.connection1.feature.employee.EmployeeRepository;
import com.example.pos.connection1.feature.imports.dto.CheckingRequest;
import com.example.pos.connection1.feature.imports.dto.ImportDetailResponse;
import com.example.pos.connection1.feature.imports.dto.ImportDetailsRequest;
import com.example.pos.connection1.feature.imports.dto.ImportRequest;
import com.example.pos.connection1.feature.imports.dto.ImportResponse;
import com.example.pos.connection1.feature.imports.dto.ImportResponseById;
import com.example.pos.connection1.feature.imports.dto.PurchaseOrderResponse;
import com.example.pos.connection1.feature.imports.dto.RejectPurchaseOrderRequest;
import com.example.pos.connection1.feature.product.ProductRepository;
import com.example.pos.connection1.feature.vendor.VendorRepository;
import com.example.pos.connection1.mapper.ImportMapper;
import com.example.pos.connection1.repository.CategoryRepository;
// import com.example.pos.connection1.repository.EmployeeRepository;
import com.example.pos.connection1.repository.ImportDetailRepository;
import com.example.pos.connection1.repository.UserRepository;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of the ImportService interface for managing import operations.
 */
@Service
@RequiredArgsConstructor
public class ImportServiceImp implements ImportService {

     // Dependencies injected via constructor
     private final ImportRepository importRepository;
     private final VendorRepository vendorRepository;
     private final EmployeeRepository employeeRepository;
     private final ProductRepository productRepository;
     private final ImportDetailRepository importDetailRepository;
     private final ImportMapper importMapper;
     private final CategoryRepository categoryRepository;
     private final ImportDetailTemporaryRepository importDetailTemporaryRepository;
     private final UserRepository userRepository;

     // Error messages for not found exceptions
     private final String vendorIdNotFound = "Vendor not found with Id : ";
     private final String empIdNotFound = "Employee not found with Id : ";
     private final String productIdNotFound = "Product not found with Id : ";
     private final String importIdNotFound = "Import not found with Id : ";
     private final String categoryIdNotFound = "Category not found with Id : ";
     private final String impNoNotFound = "Import not found with poId : ";

     @Override
     public JavaCollectionResponse<?> listRequestByRemark(Integer pageNumber, Integer pageSize, String type) {
          Sort sortById = Sort.by(Sort.Direction.DESC, "id");
          PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);
          Page<Import> pages = importRepository.findByStatusTrueAndIsDeletedFalse(pageRequest);

          List<ImportResponse> data = pages.getContent().stream()
                    .filter(p -> p.getRemark().toLowerCase().equals(type.toLowerCase()))
                    .map(importMapper::mapToImportResponse)
                    .toList();

          return JavaCollectionResponse.builder()
                    .count(data.size())
                    .data(data)
                    .build();

     }

     @Override
     public void rejectPurchaseOrder(RejectPurchaseOrderRequest rejectPurchaseOrder, Integer id) {
          Import data = importRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                              "Import not found with id : " + id));

          data.setRemark("rejected");
          data.setMsg(rejectPurchaseOrder.msg());
          data.setRejectBy(rejectPurchaseOrder.rejectBy());
          importRepository.save(data);
     }

     @Override
     public JavaCollectionResponse<?> purchaseOrderResponse() {
          List<Import> list = importRepository.findByStatusTrueAndIsDeletedFalseAndRemark("approved");
          List<PurchaseOrderResponse> data = list.stream()
                    .map(p -> PurchaseOrderResponse.builder()
                              .id(p.getId())
                              .poId(p.getImpNo())
                              .build())
                    .toList();
          return JavaCollectionResponse.builder()
                    .count(data.size())
                    .data(data)
                    .build();
     }

     @Override
     public void checkingRequest(CheckingRequest checkingRequest, String poId) {
          if (checkingRequest.role().toLowerCase().equals(JavaConstant.admin.toLowerCase())) {
               Import importData = importRepository.findByImpNo(poId)
                         .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, impNoNotFound + poId));

               importData.setRemark(checkingRequest.remark());
               if (checkingRequest.remark().toLowerCase().equals("checked")) {
                    importData.setCheckBy(checkingRequest.createBy());
                    importData.setCheckDate(checkingRequest.checkDate());
               } else if (checkingRequest.remark().toLowerCase().equals(JavaConstant.approved.toLowerCase())) {
                    importData.setApproveBy(checkingRequest.createBy());
                    importData.setApproveDate(checkingRequest.checkDate());
               }
               importRepository.save(importData);
          } else {
               throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not permission ");
          }
     }

     /**
      * filter Import
      *
      * @param value value client want to filter
      */
     @Override
     public JavaCollectionResponse<?> filter(int pageNumber, int pageSize, String value, String remark) {
          Sort sortById = Sort.by(Sort.Direction.DESC, "id");
          PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);
          Page<Import> pages = importRepository.findByStatusTrueAndIsDeletedFalse(pageRequest);
          List<ImportResponse> data = new ArrayList<>();
          switch (remark) {
               case "requested" -> data = pages.getContent().stream()
                       .filter(p -> p.getVendor().getVendorName().toLowerCase().contains(value.toLowerCase()))
                       .filter(p -> p.getRemark().toLowerCase().equals(remark.toLowerCase()))
                       .map(importMapper::mapToImportResponse)
                       .toList();
               case "checked" -> data = pages.getContent().stream()
                       .filter(p -> p.getVendor().getVendorName().toLowerCase().contains(value.toLowerCase()))
                       .filter(p -> p.getRemark().toLowerCase().equals(remark.toLowerCase()))
                       .map(importMapper::mapToImportResponse)
                       .toList();
               case "approved" -> data = pages.getContent().stream()
                       .filter(p -> p.getVendor().getVendorName().toLowerCase().contains(value.toLowerCase()))
                       .filter(p -> p.getRemark().toLowerCase().equals(remark.toLowerCase()))
                       .map(importMapper::mapToImportResponse)
                       .toList();
               default -> {
                    data = pages.getContent().stream()
                            .filter(p -> p.getVendor().getVendorName().toLowerCase().contains(value.toLowerCase()))
                            .map(importMapper::mapToImportResponse)
                            .toList();
               }
          }

          return JavaCollectionResponse.builder()
                    .count(data.size())
                    .data(data)
                    .build();
     }

     /**
      * update Import
      *
      * @param importReques is a source from client
      * @param id           to specify itme update
      */
     @Override
     public void update(ImportRequest importRequest, int id) {
          createAndUpdateImport(importRequest, id);
     }

     /**
      * Retrieves a Category entity by categoryId and code.
      *
      * @param categoryId The ID of the category.
      * @param code       The code to search for within the category.
      * @return The Category entity if found.
      * @throws ResponseStatusException If the category is not found.
      */
     private Category category(int categoryId, String code) {
          return categoryRepository
                    .findByIdAndStatusTrueAndIsDeletedFalseAndCode(categoryId, code)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                              categoryIdNotFound + categoryId));
     }

     /**
      * Retrieves details of an import by its ID.
      *
      * @param id The ID of the import.
      * @return An ImportResponseById object containing import details.
      * @throws ResponseStatusException If the import is not found.
      */
     @Override
     public ImportResponseById retrieveDetail(int id) {
          // Retrieve the Import entity by id where status is true and isDeleted is false
          Import imports = importRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, importIdNotFound + id));

          // Retrieve ImportDetail entities associated with the Import entity
          List<ImportDetailTemporary> importDetails = importDetailTemporaryRepository.getResultByImpId(id);
          List<ImportDetailResponse> details = new ArrayList<>();
          String requestBy = userRepository.getNameEmp(imports.getCreateBy());
          String checkBy = null;
          String approvedBy = null;
          String rejectBy = null;

          if (imports.getCheckBy() != null) {
               checkBy = userRepository.getNameEmp(imports.getCheckBy());
          }
          if (imports.getApproveBy() != null) {
               approvedBy = userRepository.getNameEmp(imports.getApproveBy());
          }

          if (imports.getRejectBy() != null) {
               rejectBy = userRepository.getNameEmp(imports.getRejectBy());
          }

          // Iterate through each ImportDetail entity and construct ImportDetailResponse
          // objects
          for (ImportDetailTemporary value : importDetails) {
               // Calculate total cost
               double totalCost = value.getProduct().getCost().doubleValue() * value.getQtyNew();
               String _totalCost = String.format("%.2f", totalCost);

               // Retrieve category information based on productId
               int categoryId = value.getProduct().getSubCategory().getParentId();
               Category category = category(categoryId, "category");
               Category deparment = category(category.getParentId(), "department");
               Category division = category(deparment.getParentId(), "division");

               // Create ImportDetailResponse object and add to details list
               ImportDetailResponse importDetailResponse = ImportDetailResponse.builder()
                         .id(value.getId())
                         .productId(value.getProduct().getId())
                         .barcode(value.getProduct().getBarcode())
                         .proNameEn(value.getProduct().getProNameEn())
                         .proNameKh(value.getProduct().getProNameKh())
                         .division(division.getCatNameEn())
                         .department(deparment.getCatNameEn())
                         .category(category.getCatNameEn())
                         .subCategoryId(value.getProduct().getSubCategory().getId())
                         .subCategory(value.getProduct().getSubCategory().getCatNameEn())
                         .availableQty(value.getProduct().getImportDetail().getQtyOld())
                         .orderQty(value.getQtyNew())
                         .cost(value.getProduct().getCost())
                         .totalCost(BigDecimal.valueOf(Double.parseDouble(_totalCost)))
                         .receivedQty(value.getReceiveQty())
                         .build();
               details.add(importDetailResponse);
          }
          Map<String, String> _requestBy = new HashedMap<>();
          _requestBy.put("name", requestBy);
          _requestBy.put("date", imports.getImpDate());

          Map<String, String> _checkBy = new HashedMap<>();
          _checkBy.put("name", checkBy);
          _checkBy.put("date", imports.getCheckDate());

          Map<String, String> _approveBy = new HashedMap<>();
          _approveBy.put("name", approvedBy);
          _approveBy.put("date", imports.getApproveDate());

          // Build and return ImportResponseById object
          return ImportResponseById.builder()
                    .transactionNo(imports.getId())
                    .purchaseOrderNo(imports.getImpNo())
                    .referenceNo(imports.getReferenceNo())
                    .totalCost(imports.getTotal())
                    .totalQty(imports.getTotalQty())
                    .orderDate(imports.getImpDate())
                    .vendorId(imports.getVendor().getId())
                    .vendorName(imports.getVendor().getVendorName())
                    .details(details)
                    .requestBy(_requestBy)
                    .checkedBy(_checkBy)
                    .approvedBy(_approveBy)
                    .rejectBy(rejectBy)
                    .feedBackReject(imports.getMsg())
                    .remark(imports.getRemark())
                    .transactionDate(imports.getTransactionDate())
                    .build();
     }

     /**
      * Deletes an import by its ID.
      *
      * @param id The ID of the import to delete.
      * @throws ResponseStatusException If the import is not found.
      */
     @Override
     public void deleteById(int id) {
          // Find the Import entity by id where status is true and isDeleted is false
          Import imports = importRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, importIdNotFound + id));

          // Set status to false and mark as deleted for the Import entity
          imports.setStatus(false);
          imports.setDeleted(true);

          // Save the updated Import entity
          importRepository.save(imports);

          // Get all ImportDetail entities associated with the Import entity
          List<ImportDetail> importDetails = importDetailRepository.getResultByImpId(id);

          // Iterate through each ImportDetail entity and mark as deleted
          for (ImportDetail detail : importDetails) {
               detail.setStatus(false);
               detail.setDeleted(true);
          }

          // Save all updated ImportDetail entities
          importDetailRepository.saveAll(importDetails);
     }

     /**
      * Retrieves a paginated list of imports.
      *
      * @param pageNumber The page number to retrieve (zero-based).
      * @param pageSize   The size of each page.
      * @return A JavaCollectionResponse containing ImportResponse objects.
      */
     @Override
     public JavaCollectionResponse<?> retrieve(int pageNumber, int pageSize) {
          // Define sorting by 'id' in descending order
          Sort sortById = Sort.by(Sort.Direction.DESC, "id");
          // Create PageRequest for pagination
          PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);
          // Retrieve page of Import entities from repository
          Page<Import> pages = importRepository.findByStatusTrueAndIsDeletedFalse(pageRequest);

          // Map Import entities to ImportResponse DTOs using ImportMapper
          List<ImportResponse> data = pages.getContent().stream()
                    .map(importMapper::mapToImportResponse)
                    .toList();

          // Build and return JavaCollectionResponse containing paginated data
          return JavaCollectionResponse.builder()
                    .count(pages.getTotalElements())
                    .data(data)
                    .build();
     }

     /**
      * Creates a new import record based on the provided ImportRequest.
      *
      * @param importRequest The ImportRequest containing import details.
      */
     @Override
     public void createImport(ImportRequest importRequest) {
          if (importRequest.impId() == 0) {
               createAndUpdateImport(importRequest, null);
          } else {
               if (importRequest.remark().toLowerCase().equals("stocked")) {
                    Import imp = importRepository.findById(importRequest.impId())
                              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                        "Import not found with id : " + importRequest.impId()));

                    imp.setRemark(importRequest.remark());
                    importRepository.save(imp);
                    requestData(importRequest, importRequest.impId());
               } else if (importRequest.remark().equalsIgnoreCase("received")) {
                    
                    for (ImportDetailsRequest data : importRequest.details()) {
                         Product product = productRepository.findById(data.productId())
                                   .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                             "Product id not found with : " + data.productId()));
                         ImportDetailTemporary detail = importDetailTemporaryRepository
                                   .findByImpIdAndProduct(importRequest.impId(), product)
                                   .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                             "Import Detail not found"));

//                         detail.setQtyNew(data.qtyNew()); // qtyNew is orderQty
                         detail.setReceiveQty(data.receivedQty());
                         importDetailTemporaryRepository.save(detail);
                    }
               }
          }
     }

     /**
      * Generates a formatted import number based on the current count.
      *
      * @param count The current count of imports.
      * @return A formatted import number.
      */
     private String impCount(long count) {
          return String.format("%05d", count + 1);
     }

     private void createAndUpdateImport(ImportRequest importRequest, Integer id) {

          // Get current date
          LocalDate localDate = LocalDate.now();

          // Retrieve vendor entity or throw exception if not found
          Vendor vendor = vendorRepository.findByIdAndStatusTrueAndIsDeletedFalse(importRequest.vendorId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                              vendorIdNotFound + importRequest.vendorId()));

          // Retrieve employee entity or throw exception if not found
          Employee employee = employeeRepository.findByIdAndStatusTrueAndIsDeletedFalse(importRequest.empId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                              empIdNotFound + importRequest.empId()));
          Import data = null;
          String impNo = "";
          if (id == null) {
               // Create new Import entity
               data = new Import();
               // Generate import number
               impNo = impCount(importRepository.count());
               data.setImpNo(impNo);
               data.setDateLocal(localDate);
          } else {
               data = importRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                         .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, importIdNotFound + id));
          }

          data.setEmployee(employee);
          data.setVendor(vendor);
          data.setImpDate(importRequest.impDate());
          data.setDiscount(importRequest.discount());
          data.setTotal(importRequest.total());
          data.setCreateBy(importRequest.createBy());
          data.setTotalQty(importRequest.totalQty());
          data.setTransactionDate(importRequest.transactionDate());
          data.setReferenceNo(importRequest.referenceNo());
          data.setRemark(importRequest.remark());
          // Save Import entity to repository
          importRepository.save(data);

          if (importRequest.remark().toLowerCase().equals("stocked")) {
               // Process each detail in ImportRequest
               requestData(importRequest, data.getId());
          } else {
               requestDataTmp(importRequest, data.getId());
          }
     }

     private void requestData(ImportRequest importRequest, int impoId) {
          List<ImportDetailsRequest> listDetail = importRequest.details();
          for (int i = 0; i < listDetail.size(); i++) {
               var value = listDetail.get(i);
               int productId = value.productId();

               // Retrieve product entity or throw exception if not found
               Product product = productRepository.findByIdAndStatusTrueAndIsDeletedFalse(productId)
                         .orElseThrow(() -> new ResponseStatusException(
                                   HttpStatus.NOT_FOUND, productIdNotFound + productId));

               int qtyNew = value.qtyNew();

               ImportDetail details = null;
               if (value.id() == null) {
                    details = new ImportDetail();
                    ImportDetail getImpDetails = importDetailRepository.getDataImportDetail(productId);

                    // Determine quantity to set based on existing or new detail
                    if (getImpDetails == null) {
                         details.setQtyOld(qtyNew);
                    } else {
                         int qtyOld = getImpDetails.getQtyOld();
                         int qty = qtyOld + qtyNew;
                         details.setQtyOld(qty);
                    }
               } else {
                    Optional<ImportDetail> checkId = importDetailRepository.findById(value.id());
                    if (checkId == null) {
                         details = new ImportDetail();
                    } else {
                         details = checkId.get();
                         ImportDetail getImpDetails = importDetailRepository.getDataImportDetail(productId);

                         // Determine quantity to set based on existing or new detail
                         if (getImpDetails == null) {
                              details.setQtyOld(qtyNew);
                         } else {
                              int qtyOld = getImpDetails.getQtyOld() - getImpDetails.getQtyNew();
                              int qty = qtyOld + qtyNew;
                              details.setQtyOld(qty);
                         }
                    }
               }

               details.setImpId(impoId);
               details.setProduct(product);
               details.setQtyNew(qtyNew);
               details.setCost(value.cost());
               details.setAmount(value.amount());
               details.setExpireDate(value.expireDate());
               details.setCreateBy(importRequest.createBy());
               // Save ImportDetail entity to repository
               importDetailRepository.save(details);

               // Update product status and import detail reference
               product.setProductStatus("In Stock");
               product.setImportDetail(details);
               productRepository.save(product);
          }
     }

     private void requestDataTmp(ImportRequest importRequest, int impoId) {
          List<ImportDetailsRequest> listDetail = importRequest.details();
          for (int i = 0; i < listDetail.size(); i++) {
               var value = listDetail.get(i);
               int productId = value.productId();

               // Retrieve product entity or throw exception if not found
               Product product = productRepository.findByIdAndStatusTrueAndIsDeletedFalse(productId)
                         .orElseThrow(() -> new ResponseStatusException(
                                   HttpStatus.NOT_FOUND, productIdNotFound + productId));

               Import imp = importRepository.findById(impoId).orElseThrow(
                         () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                   "Import id has not been found  with id : " + impoId));

               ImportDetailTemporary importDetailTemporary = new ImportDetailTemporary();
               importDetailTemporary.setProduct(product);
               importDetailTemporary.setImpId(impoId);
               importDetailTemporary.setImport1(imp);
               importDetailTemporary.setProduct(product);
               importDetailTemporary.setQtyNew(value.qtyNew());
               importDetailTemporary.setCost(value.cost());
               importDetailTemporary.setAmount(value.amount());
               importDetailTemporary.setExpireDate(value.expireDate());
               importDetailTemporary.setCreateBy(importRequest.createBy());
               // Save ImportDetail entity to repository
               importDetailTemporaryRepository.save(importDetailTemporary);
               // Update product status and import detail reference
               product.setProductStatus("In Stock");
               productRepository.save(product);
          }
     }

}
