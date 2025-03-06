package feature.company_profile.business.controller;

import Components.Event.ButtonEvent;
import Constant.JavaRoute;
import feature.company_profile.business.component.BusinessRowData;
import feature.company_profile.business.model.BusinessModel;
import feature.company_profile.business.model.BusinessModel.BusinessModelDetail;
import feature.company_profile.business.view.BusinessCreate;
import feature.company_profile.business.view.BusinessView;
import java.awt.GridBagConstraints;
import javax.swing.JFrame;
import pagination.MainPaginationWithData;

public class BusinessViewController extends MainPaginationWithData<BusinessModel> {

     private BusinessView businessView;

     public BusinessViewController(BusinessView view) {
          super(
               BusinessModel.class,
               view.getSearchField(),
               view.getPaginationPanel(),
               view.getPanelData(),
               null
          );
          
          this.businessView = view;
     }

     @Override
     protected String routeName(boolean isCheck) {
          String route;
          if (isCheck) {
               route = JavaRoute.companyProfile + "?pageNumber=" + pageNumber + "&pageSize=" + pageSize + "&code=Business";
          } else {
               route = JavaRoute.companyProfile + "/search?pageNumber=" + pageNumber + "&pageSize=" + pageSize + "&code=Business&search=";
          }
          return route;
     }

     @Override
     protected void appendItem(GridBagConstraints gbc, int i) {
          BusinessModelDetail detail = (BusinessModelDetail) listData.get(i);

          Integer id = detail.getId();

          BusinessRowData rowData = new BusinessRowData(detail);

          ButtonEvent event = new ButtonEvent() {

               @Override
               public void onDelete() {
                    delete(JavaRoute.companyProfile + "/" + id + "?code=Business");
               }

               @Override
               public void onEdit() {
                    BusinessCreate businessCreate = new BusinessCreate(new JFrame(), true);
                    businessCreate.update(detail, businessView);
                    businessCreate.setVisible(true);
               }

          };
          rowData.initEvent(event);
          panelData.add(rowData, gbc);
     }

}
