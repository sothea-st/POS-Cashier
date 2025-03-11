 
package feature.report.report_stock.export;
 
import main.main_export.MainExportPDF;

public class ExportStockAvailablePDF extends MainExportPDF{

     private Integer statusId;
     public ExportStockAvailablePDF(String[] columnHeader, String titleEn, String titleKh,Integer statusId) {
          super(columnHeader, titleEn, titleKh);
          this.statusId = statusId;
     }

     @Override
     protected void setData() {
          ExportStockAvailable.read(dataList,statusId);
     }
     
}
