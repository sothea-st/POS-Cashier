package feature.order_online.export;

 
import main.main_export.MainExportExcel;

public class ExportOrderOnlineExcel extends MainExportExcel {

     private String dateFrom;
     private String dateTo;

     public ExportOrderOnlineExcel(String[] columnHeader, String titleEn, String titleKh, String dateFrom, String dateTo) {
          super(columnHeader, titleEn, titleKh);
          this.dateFrom = dateFrom;
          this.dateTo = dateTo;
     }

     @Override
     protected void setData() {
          ExportOrderOnline.read(dataList, dateFrom, dateTo);
     }

}
