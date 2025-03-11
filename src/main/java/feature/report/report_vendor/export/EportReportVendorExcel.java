 
package feature.report.report_vendor.export;

import main.main_export.MainExportExcel;

 
public class EportReportVendorExcel extends MainExportExcel {

     private String dateFrom;
     private String dateTo;
     
     public EportReportVendorExcel(String[] columnHeader, String titleEn, String titleKh,String dateFrom,String dateTo) {
          super(columnHeader, titleEn, titleKh);
          
          this.dateFrom =  dateFrom;
          this.dateTo = dateTo;
     }

 

     @Override
     protected void setData() {
          ExportReportVendor.read(dataList, dateFrom, dateTo);
     }
     
}
