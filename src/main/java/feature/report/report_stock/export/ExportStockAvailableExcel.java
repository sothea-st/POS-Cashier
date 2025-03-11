 
package feature.report.report_stock.export;

import main.main_export.MainExportExcel;

 
public class ExportStockAvailableExcel extends MainExportExcel{
     private Integer statusId;
     public ExportStockAvailableExcel(String[] columnHeader, String titleEn, String titleKh,Integer statusId) {
          super(columnHeader, titleEn, titleKh);
          this.statusId = statusId;
     }

     @Override
     protected void setData() {
          ExportStockAvailable.read(dataList,statusId);
     }
     
}
