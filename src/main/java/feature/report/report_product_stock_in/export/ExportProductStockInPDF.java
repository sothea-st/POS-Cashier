package feature.report.report_product_stock_in.export;

import main.main_export.MainExportPDF;

public class ExportProductStockInPDF extends MainExportPDF {

     private String dateFrom;
     private String dateTo;

     public ExportProductStockInPDF(String[] columnHeader, String titleEn, String titleKh, String dateFrom, String dateTo) {
          super(columnHeader, titleEn, titleKh);
          this.dateFrom = dateFrom;
          this.dateTo = dateTo;
     }

     @Override
     protected void setData() {
          ExportProductStockIn.read(dataList, dateFrom, dateTo);
     }

}
