package feature.report.report_product_stock_in.controller;

import Constant.JavaRoute;
import feature.report.report_product_stock_in.component.ReportProductStockInRowData;
import feature.report.report_product_stock_in.export.ExportProductStockInExcel;
import feature.report.report_product_stock_in.export.ExportProductStockInPDF;
import feature.report.report_product_stock_in.model.ReportProductStockInResponse;
import feature.report.report_product_stock_in.model.ReportProductStockInResponse.ReportProductStockInDetailResponse;
import feature.report.report_product_stock_in.view.ReportProductStockInView;
import java.awt.GridBagConstraints;
import pagination.MainPaginationWithData;

public class ReportProductStockInController extends MainPaginationWithData<ReportProductStockInResponse> {

     private ReportProductStockInView view;

     private String dateFrom;
     private String dateTo;
     private String titleEn = "Report Product Stock In";
     private String titleKh = "រាយការណ៍ពីស្តុកផលិតផល";
     private String[] columnHeader = {
          "No.",
          "Product Name",
          "Category Name",
          "Supplier Name",
          "Price",
          "Qty",
          "Date",
          "Description"
     };

     public ReportProductStockInController(ReportProductStockInView view) {
          super(ReportProductStockInResponse.class,
               view.getSearchField(),
               view.getPaginationPanel(),
               view.getPanelData(),
               view.getGroupButtonExport());

          this.view = view;

          initDate();
     }

     private void initDate() {
          this.dateFrom = view.getDateFrom().getSelectedDate();
          this.dateTo = view.getDateTo().getSelectedDate();
     }

     @Override
     protected String routeName(boolean isCheck) {
          String route = "";
          if (isCheck) { // get data
               route = JavaRoute.reportProductStockIn + "?pageNumber=" + pageNumber + "&pageSize=" + pageSize + "&dateFrom=" + dateFrom + "&dateTo=" + dateTo;
          } else { // search
               route = JavaRoute.reportProductStockIn + "/search?pageNumber=" + pageNumber + "&pageSize=" + pageSize + "&dateFrom=" + dateFrom + "&dateTo=" + dateTo + "&search=";
          }
          //System.err.println("rorute = " + route);
          return route;
     }

     @Override
     protected void appendItem(GridBagConstraints gbc, int i) {

          ReportProductStockInDetailResponse detail = (ReportProductStockInDetailResponse) listData.get(i);

          ReportProductStockInRowData rowData = new ReportProductStockInRowData(detail, i + 1);

          panelData.add(rowData, gbc);
     }

     public void find() {
          initDate();
          init();
     }

     @Override
     protected void exportExcel() {
          new ExportProductStockInExcel(columnHeader, titleEn, titleKh, dateFrom, dateTo).export();
     }

     @Override
     protected void exportPDF() {
          new ExportProductStockInPDF(columnHeader, titleEn, titleKh, dateFrom, dateTo).export();
     }
     

}
