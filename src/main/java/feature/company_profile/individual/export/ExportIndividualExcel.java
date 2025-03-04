package feature.company_profile.individual.export;

import main.main_export.MainExportExcel;

public class ExportIndividualExcel extends MainExportExcel {

     public ExportIndividualExcel(String[] columnHeader, String titleEn, String titleKh) {
          super(columnHeader, titleEn, titleKh);
     }

     @Override
     protected void setData() {
         ExportIndividual.read(dataList);
     }

}
