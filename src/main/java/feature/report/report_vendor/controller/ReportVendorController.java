package feature.report.report_vendor.controller;

import Constant.JavaRoute;
import feature.report.report_vendor.component.ReportVendorRowData;
import feature.report.report_vendor.export.EportReportVendorExcel;
import feature.report.report_vendor.export.EportReportVendorPDF;
import feature.report.report_vendor.model.ReportVendorResponse;
import feature.report.report_vendor.model.ReportVendorResponse.ReportVendorResponseDetail;
import feature.report.report_vendor.view.ReportVendorView;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import pagination.MainPaginationWithData;

public class ReportVendorController extends MainPaginationWithData<ReportVendorResponse> {

     private ReportVendorView view;
     private String dateFrom;
     private String dateTo;
     private String titleEn = "Report Supplier";
     private String titleKh = "រាយការណ៍ពីអ្នកផ្គត់ផ្គង់";
     private String[] columnHeader = {
          "No.",
          "Supplier Name",
          "Phone Number",
          "Email",
          "Website",
          "Address"
     };

     public ReportVendorController(ReportVendorView view) {
          super(ReportVendorResponse.class,
               view.getSearchField(),
               view.getPaginationPanel(),
               view.getPanelData(),
               view.getGroupButtonExport());

          this.view = view;

          getDateValue();
     }

     @Override
     protected String routeName(boolean isCheck) {
          String route = "";
          if (isCheck) { // get data
               route = JavaRoute.vendor + "/report?pageNumber=" + pageNumber + "&pageSize=" + pageSize + "&dateFrom=" + dateFrom + "&dateTo=" + dateTo;
          } else { // search
               route = JavaRoute.vendor + "/report/search?pageNumber=" + pageNumber + "&pageSize=" + pageSize + "&dateFrom=" + dateFrom + "&dateTo=" + dateTo + "&search=";
          }
          return route;
     }

     @Override
     protected void appendItem(GridBagConstraints gbc, int i) {

          ReportVendorResponseDetail detail = (ReportVendorResponseDetail) listData.get(i);

          ReportVendorRowData rowData = new ReportVendorRowData(detail, i + 1);

          rowData.setPreferredSize(new Dimension(1446, 45));

          panelData.add(rowData, gbc);

     }

     public void find() {

          getDateValue();

          init();

     }

     private void getDateValue() {
          this.dateFrom = view.getDateFrom().getSelectedDate();
          this.dateTo = view.getDateTo().getSelectedDate();
     }

     @Override
     protected void exportExcel() {
          new EportReportVendorExcel(columnHeader, titleEn, titleKh, dateFrom, dateTo).export();
     }

     @Override
     protected void exportPDF() {
          new EportReportVendorPDF(columnHeader, titleEn, titleKh, dateFrom, dateTo).export();
     }

}
