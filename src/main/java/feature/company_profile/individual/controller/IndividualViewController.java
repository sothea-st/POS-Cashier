package feature.company_profile.individual.controller;

import Components.Event.ButtonEvent;
import Constant.JavaRoute;
import feature.company_profile.individual.view.IndividualCreate;
import feature.company_profile.individual.view.IndividualDetail;
import feature.company_profile.individual.view.IndividualView;
import feature.company_profile.individual.component.IndividualRowData;
import feature.company_profile.individual.export.ExportIndividualExcel;
import feature.company_profile.individual.export.ExportIndividualPDF;
import feature.company_profile.individual.model.IndividualResponseModel;
import feature.company_profile.individual.model.IndividualResponseModel.IndividualResponseDetail;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import javax.swing.JFrame;
import lombok.Getter;
import lombok.Setter;
import pagination.MainPaginationWithData;

@Setter
@Getter
public class IndividualViewController extends MainPaginationWithData<IndividualResponseModel> {

     // variable
     private IndividualView individualView;
     private String titleKh = "បញ្ជីបុគ្គល";
     private String titleEn = "Individual List";
     // column header
     private String[] columnHeader = {
          "Customer ID",
          "Gender",
          "Nationality",
          "English Name",
          "Khmer Name",
          "Phone Number",
          "Email",
          "Total Amount Spend",
          "Created Date"
     };

     // constructor
     public IndividualViewController(IndividualView view) {
          super(
               IndividualResponseModel.class,
               view.getSearchField(),
               view.getPaginationPanel(),
               view.getPanelData(),
               view.getGroupButtonExport()
          );

          this.individualView = view;
     }

     @Override
     protected String routeName(boolean isCheck) {
          String route;
          if (isCheck) { // get data
               route = JavaRoute.companyProfile + "?pageNumber=" + pageNumber + "&pageSize=" + pageSize + "&code=Individual";
          } else { // search
               route = JavaRoute.companyProfile + "/search?pageNumber=" + pageNumber + "&pageSize=" + pageSize + "&code=Individual&search=";
          }
          return route;
     }

     @Override
     protected void appendItem(GridBagConstraints gbc, int i) {

          IndividualResponseDetail detail = (IndividualResponseDetail) listData.get(i);

          Integer id = detail.getId();

          IndividualRowData rowData = new IndividualRowData(detail);

          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onDelete() {
                    delete(JavaRoute.companyProfile + "/" + id + "?code=Individual");
               }

               @Override
               public void onView() {
                    IndividualDetail individualDetail = new IndividualDetail(new JFrame(), true);
                    individualDetail.setDetail(detail);
                    individualDetail.setVisible(true);
               }

               @Override
               public void onEdit() {
                    IndividualCreate individualCreate = new IndividualCreate(new JFrame(), true);
                    individualCreate.update(detail, individualView);
                    individualCreate.setVisible(true);
               }

          };
          rowData.initEvent(event);
          rowData.setPreferredSize(new Dimension(1489, 45));
          panelData.add(rowData, gbc);
     }

     @Override
     protected void exportExcel() {
          new ExportIndividualExcel(columnHeader, titleEn, titleKh).export();
     }

     @Override
     protected void exportPDF() {
          new ExportIndividualPDF(columnHeader, titleEn, titleKh).export();
     }

}
