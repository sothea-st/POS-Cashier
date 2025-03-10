package feature.order_online.export;

import main.main_export.MainExportPDF;

public class ExportOrderOnlinePDF extends MainExportPDF {

     private String dateFrom;
     private String dateTo;

     public ExportOrderOnlinePDF(String[] columnHeader, String titleEn, String titleKh, String dateFrom, String dateTo) {
          super(columnHeader, titleEn, titleKh);
          this.dateFrom = dateFrom;
          this.dateTo = dateTo;
     }

     @Override
     protected void setData() {
          ExportOrderOnline.read(dataList, dateFrom, dateTo);
     }

}
