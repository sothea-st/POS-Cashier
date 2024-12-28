package com.example.pos.system.feature.user_permission.reports.report_inventoory;

import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.domain.settings.Product;
import com.example.pos.system.domain.report.ReportInventory;
import com.example.pos.system.feature.attribute.product.ProductRepository;
import com.example.pos.system.feature.user_permission.reports.report_inventoory.dto.ReportInventoryRequest;
import com.example.pos.system.feature.user_permission.reports.report_inventoory.dto.ReportInventoryResponse;
import com.example.pos.system.layer.repository.ImportDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportInventoryService {
    private final ProductRepository productRepository;
    private final ImportDetailRepository importDetailRepository;
    private final ReportInventoryRepository reportInventoryRepository;

    public void create(List<ReportInventoryRequest> list) {

        for (ReportInventoryRequest data : list) {
            Product product = productRepository.findById(data.productId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Product id not found with : " + data.productId()));

            Integer qtyByProId = importDetailRepository.sumQtyByProId(product.getId());
            ReportInventory reportInventory = new ReportInventory();
            String inputDate = data.impDate();
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(inputDate, inputFormatter);
            String formattedDate = date.format(outputFormatter);

            reportInventory.setProduct(product);
            reportInventory.setDate(LocalDate.parse(formattedDate));

            // first time add product to report
            if (qtyByProId == null) {
                int beginningQty = 0;
                int availableQty = data.stockInQty() + beginningQty;
                reportInventory.setBeginningQty(beginningQty); // beginningQty = 0
                reportInventory.setStockInQty(availableQty);
                reportInventory.setAvailableQty(availableQty);
                reportInventory.setStockOutQty(0);
                reportInventory.setReturnInQty(0);
                reportInventory.setReturnOutQty(0);
                reportInventory.setEndingQty(0);
                reportInventoryRepository.save(reportInventory);
            } else { // qtyByProId != null => the product already add once
                Integer endingQty = reportInventoryRepository.findTopEndingQtyByProductOrderByDateDesc(product.getId());

                endingQty = endingQty == null ? 0 : endingQty;

                int beginningQty = endingQty;
                int availableQty = data.stockInQty() + beginningQty;
                int stockOutQty = data.stockOutQty() == null ? 0 : data.stockOutQty();
                int stockIntQty =  data.stockInQty() == null ? 0 : data.stockInQty();
                int returnInQty = data.returnInQty() == null ? 0 : data.returnInQty();
                int returnOutQty = data.returnOutQty() == null ? 0 : data.returnOutQty();
                int newEndingQty = availableQty - stockOutQty + returnInQty;

                reportInventory.setBeginningQty(endingQty); // beginning = ending
                reportInventory.setAvailableQty(availableQty);
                reportInventory.setStockInQty(stockIntQty);
                reportInventory.setStockOutQty(stockOutQty);
                reportInventory.setReturnInQty(returnInQty);
                reportInventory.setReturnOutQty(returnOutQty);
                reportInventory.setEndingQty(newEndingQty); // ending = availableQty - stockOutQty + returnInQty
                reportInventoryRepository.save(reportInventory);
            }
        }


    }


    public JavaCollectionResponse<?> read(String dateFrom,String dateTo, Integer pageSize, Integer pageNumber){
        List<ReportInventoryResponse> reportInventoryResponses = new ArrayList<>();
        long totalPageNumber = 0;
        if(pageNumber == null && pageSize == null){ // get all

            reportInventoryResponses  = reportInventoryRepository.findByDateBetween(LocalDate.parse(dateFrom),LocalDate.parse(dateTo)).stream()
                    .map(this::mapToReportInventoryResponse).toList();

            // assign total pages
            totalPageNumber = reportInventoryResponses.size();

        }else{ // get by pagination
            Sort sortById = Sort.by(Sort.Direction.DESC, "id");
            // page request
            // pageNumber start from 0
            PageRequest pageRequest = PageRequest.of(pageNumber,pageSize,sortById);
            Page<ReportInventory>  pages = reportInventoryRepository.findByDateBetween(LocalDate.parse(dateFrom),LocalDate.parse(dateTo),pageRequest);

            // assign total pages
            totalPageNumber = pages.getTotalElements();

            // map value to List
            reportInventoryResponses = pages.getContent().stream()
                    .map(this::mapToReportInventoryResponse)
                    .toList();

        }

        return JavaCollectionResponse.builder()
                .count(totalPageNumber)
                .data(reportInventoryResponses)
                .build();
    }

    public JavaCollectionResponse<?> search(String dateFrom,String dateTo, Integer pageSize, Integer pageNumber,String search){
        List<ReportInventoryResponse> reportInventoryResponses = new ArrayList<>();
        long totalPageNumber = 0;
        if(pageNumber == null && pageSize == null){ // get all

            reportInventoryResponses  = reportInventoryRepository.searchWithoutPagination(LocalDate.parse(dateFrom),LocalDate.parse(dateTo),search).stream()
                    .map(this::mapToReportInventoryResponse).toList();

            // assign total pages
            totalPageNumber = reportInventoryResponses.size();

        }else{ // get by pagination
            Sort sortById = Sort.by(Sort.Direction.DESC, "id");
            // page request
            // pageNumber start from 0
            PageRequest pageRequest = PageRequest.of(pageNumber,pageSize,sortById);
            Page<ReportInventory>  pages = reportInventoryRepository.searchWithPagination(LocalDate.parse(dateFrom),LocalDate.parse(dateTo),search,pageRequest);

            // assign total pages
            totalPageNumber = pages.getTotalElements();

            // map value to List
            reportInventoryResponses = pages.getContent().stream()
                    .map(this::mapToReportInventoryResponse)
                    .toList();

        }

        return JavaCollectionResponse.builder()
                .count(totalPageNumber)
                .data(reportInventoryResponses)
                .build();
    }


    private ReportInventoryResponse mapToReportInventoryResponse(ReportInventory reportInventory) {
        return ReportInventoryResponse.builder()
                .id(reportInventory.getId())
                .date(reportInventory.getDate().toString())
                .productName(reportInventory.getProduct().getProNameEn() + " " +reportInventory.getProduct().getChoices())
                .beginningQty(reportInventory.getBeginningQty())
                .stockInQty(reportInventory.getStockInQty())
                .availableQty(reportInventory.getAvailableQty())
                .returnInQty(reportInventory.getReturnInQty())
                .returnOutQty(reportInventory.getReturnOutQty())
                .stockOutQty(reportInventory.getStockOutQty())
                .endingQty(reportInventory.getEndingQty())
                .build();
    }
}
