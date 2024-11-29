package com.example.pos.system.feature.reports.report_inventoory;

import com.example.pos.system.domain.Product;
import com.example.pos.system.domain.report.ReportInventory;
import com.example.pos.system.feature.product.ProductRepository;
import com.example.pos.system.feature.reports.report_inventoory.dto.ReportInventoryRequest;
import com.example.pos.system.layer.repository.ImportDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

                reportInventory.setBeginningQty(endingQty); // beginning = ending
                reportInventory.setAvailableQty(availableQty);
                reportInventory.setStockInQty(stockIntQty);
                reportInventory.setStockOutQty(stockOutQty);
                reportInventory.setReturnInQty(returnInQty);
                reportInventory.setReturnOutQty(returnOutQty);
                reportInventory.setEndingQty(availableQty - stockOutQty); // ending = availableQty - stockOutQty
                reportInventoryRepository.save(reportInventory);
            }
        }


    }
}
