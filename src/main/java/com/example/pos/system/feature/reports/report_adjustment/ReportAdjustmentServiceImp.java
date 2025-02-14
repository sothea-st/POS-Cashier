package com.example.pos.system.feature.reports.report_adjustment;

import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.domain.adjustment.Adjustment;
import com.example.pos.system.domain.adjustment.AdjustmentDetail;
import com.example.pos.system.domain.settings.Category;
import com.example.pos.system.feature.adjustment.AdjustmentRepository;
import com.example.pos.system.feature.reports.report_adjustment.dto.*;
import com.example.pos.system.feature.reports.report_adjustment.dto.projection.AdjustmentProjection;
import com.example.pos.system.layer.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportAdjustmentServiceImp implements ReportAdjustmentService {
    // inject bean repository
    private final AdjustmentRepository adjustmentRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public JavaCollectionResponse<?> getReport(String dateFrom, String dateTo) {
        LocalDate dateFromValue = LocalDate.parse(dateFrom);
        LocalDate dateToValue = LocalDate.parse(dateTo);

        List<Category> categories = categoryRepository.findByStatusTrueAndIsDeletedFalse("division", "department");

        List<ReportAdjustmentResponse> reportAdjustmentResponses = new ArrayList<>();

        for (Category category : categories) {
            if (category.getCode().equals("department")) {

                int departmentId = category.getId();
                int divisionId = category.getParentId();

                String divisionName = category.getCatNameEn();
                String departmentName = category.getCatNameEn();

                if(adjustmentRepository.isExist(divisionId,departmentId) ) { // true exist

                    ReportAdjustmentResponse obj = new ReportAdjustmentResponse();
                    obj.setDivisionId(divisionId);
                    obj.setDivisionName(divisionName);

                    ResultAdjustment resultAdjustment = new ResultAdjustment();

                    // department
                    AdjustmentData department = new AdjustmentData();
                    department.setId(departmentId);
                    department.setName(departmentName);
                    resultAdjustment.setDepartment(department);



                    List<AdjustmentProjection> adjustmentProjectionList = adjustmentRepository.listAdjustmentProjection(
                            divisionId,
                            departmentId,
                            dateFromValue,
                            dateToValue
                    );



                    for( AdjustmentProjection product : adjustmentProjectionList ) {
                        if(adjustmentRepository.isExistProduct(product.getProduct_id()) ) { // true do action
                            // product
                            AdjustmentData productData = new AdjustmentData();
                            productData.setId(product.getProduct_id());
                            productData.setName(product.getPro_name_en());
                            resultAdjustment.setProduct(productData);

                            // detail
                            List<AdjustmentDataDetail> details = new ArrayList<>();

                            details = adjustmentRepository.getDetail(product.getProduct_id(),dateFromValue,dateToValue).stream()
                                            .map(val -> AdjustmentDataDetail.builder()
                                                    .transactionDate(val.getTransaction_date().toString())
                                                    .transactionNo(val.getTransaction())
                                                    .reference(val.getReference())
                                                    .reason(val.getReason())
                                                    .description(val.getReturn_type())
                                                    .totalQty(val.getQty())
                                                    .totalCost(val.getTotal_cost())
                                                    .build()).toList();



                            resultAdjustment.setDetails(details);
//                            break;
                        }
                    }


                    obj.setResult(resultAdjustment);
                    reportAdjustmentResponses.add(obj);

                }


            }
        }
        return JavaCollectionResponse.builder()
                .count(0)
                .data(reportAdjustmentResponses)
                .build();
    }
}
