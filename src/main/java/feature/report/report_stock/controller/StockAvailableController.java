package feature.report.report_stock.controller;

import Constant.JavaRoute;
import feature.report.report_stock.component.StockAvailableRowData;
import feature.report.report_stock.export.ExportStockAvailableExcel;
import feature.report.report_stock.export.ExportStockAvailablePDF;
import feature.report.report_stock.model.StockAvailableResponse;
import feature.report.report_stock.model.StockAvailableResponse.StockAvailableDetail;
import feature.report.report_stock.view.ReportStockAvailableView;
import java.awt.GridBagConstraints;
import pagination.MainPaginationWithData;

public class StockAvailableController extends MainPaginationWithData<StockAvailableResponse> {

     private ReportStockAvailableView view;
     private String titleEn2 = "Report Stock Available";
     private String titleKh2 = "របាយការណ៍ស្តុកមាន";
     
      private String titleEn1 = "Report Stock Unavailable";
     private String titleKh1 = "របាយការណ៍មិនមានស្ដុក";
     private Integer statusId;
     private String[] columnHeader = {
          "No.",
          "Product Name",
          "Category Name",
          "Unit Cost",
          "Unit Price",
          "Qty"
     };

     public StockAvailableController(ReportStockAvailableView view ,Integer statusId) {
          super(
               StockAvailableResponse.class,
               view.getSearchField(),
               view.getPaginationPanel(),
               view.getPanelData(),
               view.getGroupButtonExport());

          this.view = view;
          this.statusId = statusId;
     }

     @Override
     protected String routeName(boolean isCheck) {

          String search = isCheck ? "" : "/search";
          String searchField = isCheck ? "" : "&search=";

          String route = JavaRoute.reportStock + "/stockAvailable" + search + "?pageNumber="
               + pageNumber + "&pageSize=" + pageSize + "&statusId="+statusId + searchField;

          return route;
     }

     @Override
     protected void appendItem(GridBagConstraints gbc, int i) {

          StockAvailableDetail detail = (StockAvailableDetail) listData.get(i);

          StockAvailableRowData rowData = new StockAvailableRowData(detail, (i + 1));

          panelData.add(rowData, gbc);

     }

     @Override
     protected void exportExcel() {
          new ExportStockAvailableExcel(columnHeader, statusId == 2 ? titleEn2 : titleEn1,statusId == 2 ? titleKh2 : titleKh1,statusId).export();
     }

     @Override
     protected void exportPDF() {
          new ExportStockAvailablePDF(columnHeader, statusId == 2 ? titleEn2 : titleEn1, statusId == 2 ? titleKh2 : titleKh1,statusId).export();
     }

}
