package feature.company_profile.individual.controller;

import Components.Color.WindowColor;
import Components.Event.ButtonEvent;
import Components.Fonts.WindowFonts;
import Components.NotFound;
import Constant.JavaConnection;
import Constant.JavaRoute;
import com.fasterxml.jackson.databind.ObjectMapper;
import feature.company_profile.business.model.BusinessModel;
import feature.company_profile.individual.view.IndividualCreate;
import feature.company_profile.individual.view.IndividualDetail;
import feature.company_profile.individual.view.IndividualView;
import feature.company_profile.individual.component.IndividualRowData;
import feature.company_profile.individual.export.ExportIndividualExcel;
import feature.company_profile.individual.export.ExportIndividualPDF;
import feature.company_profile.individual.model.IndividualResponseModel;
import feature.company_profile.individual.model.IndividualResponseModel.IndividualResponseDetail;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import lombok.Getter;
import lombok.Setter;
import okhttp3.Response;
import pagination.MainPagination;
import pagination.MainPaginationWithData;

@Setter
@Getter
public class IndividualViewController extends MainPaginationWithData {

     // variable
     private IndividualView individualView;
//     private List<IndividualResponseDetail> listData = new ArrayList<>();

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

     private String titleKh = "បញ្ជីបុគ្គល";
     private String titleEn = "Individual List";

     public IndividualViewController(IndividualView individualView) {

          this.individualView = individualView;
          this.panelData = individualView.getPanelData();
          this.paginationPanel = individualView.getPaginationPanel();
          this.groupButtonExport = individualView.getGroupButtonExport();
     }

     public void init() {
          read(true); // read data
          eventPagination(); // pagination action
          eventSearch(); // search action
          eventExport(); // export action
     }

     private void eventExport() {
          ButtonEvent eventExel = new ButtonEvent() {
               @Override
               public void onMouseClick() {
                    new ExportIndividualExcel(columnHeader, titleEn, titleKh).export();
               }
          };
          groupButtonExport.excelEvent(eventExel);

          ButtonEvent eventPdf = new ButtonEvent() {
               @Override
               public void onMouseClick() {
                    new ExportIndividualPDF(columnHeader, titleEn, titleKh).export();
               }
          };
          groupButtonExport.pdfEvent(eventPdf);
     }

//     public void read(boolean isCheck) {
//
//          Response response = null;
//
//          if (isCheck) { // get data
//               response = JavaConnection.get(JavaRoute.companyProfile + "?pageNumber=" + pageNumber + "&pageSize=" + pageSize + "&code=Individual");
//          } else { // search
//               response = JavaConnection.get(JavaRoute.companyProfile + "/search?pageNumber=" + pageNumber + "&pageSize=" + pageSize + "&code=Individual&search=" + searchValue);
//          }
//
//          System.err.println("log view response : " + response);
//          try {
//
//               if (response.isSuccessful()) {
//
//                    String responseData = response.body().string();
//
//                    ObjectMapper objMapper = new ObjectMapper();
//
//                    IndividualResponseModel data = objMapper.readValue(responseData, IndividualResponseModel.class);
//
//                    // pagination code
//                    dataCount = (int) data.getCount();
//                    if (isCheck) { // true get
//                         paginationPanel.setTotalPage(data.getCount(), pageSize); // set totalPage and pageSize to pagination
//                    } else { // false search
//                         paginationPanel.resetPage(dataCount);
//                    }
//
//                    listData.clear();
//
//                    listData = data.getData();
//
//                    appendData();
//
//               }
//
//          } catch (Exception e) {
//               System.err.println("error get individual : " + e);
//          }
//
//     }
     private void delete(Integer id) {
          UIManager UI = new UIManager();
          UI.put("OptionPane.background", WindowColor.mediumGreen);
          UI.put("Panel.background", WindowColor.mediumGreen);
          UI.put("OptionPane.messageFont", WindowFonts.timeNewRomanBold14);

          int resp = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete ?",
               "Delete", JOptionPane.YES_NO_OPTION);

          if (resp == JOptionPane.YES_OPTION) {
               Response response = JavaConnection.delete(JavaRoute.companyProfile + "/" + id + "?code=Individual");

               try {
                    if (response.isSuccessful()) {
                         read(true);
                    }
               } catch (Exception e) {
                    System.err.println("error delete promotion : " + e);
               }
          }
     }

     //Action pagination
     public void eventPagination() {
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onMouseClick(String value) {
                    if (isCheckSearch) {
                         int _value = Integer.parseInt(value); // value pageNumber star from 0 
                         pageNumber = String.valueOf(_value);
                         read(true);
                    }
               }

               // for pagination
               @Override
               public void onMouseClick(String value, String pType) {
                    pageType = pType;
               }
          };
          paginationPanel.initEvent(event);
     }

     //Action Search
     public void eventSearch() {
          // this event was called when user type on searchTextField 
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onKeyType() {
                    TimerTask task = new TimerTask() {
                         @Override
                         public void run() {
                              searchValue = individualView.getSearchField().getValueTextSearch();
                              paginationPanel.resetPage();
                              pageNumber = "1";

                              if (searchValue.isEmpty()) {
                                   isCheckSearch = true;
                                   pageNumber = "1";
                                   read(true);
                                   return;
                              }
                              read(false);
                         }
                    };

                    Timer timer = new Timer();
                    timer.schedule(task, 500);

               }
          };
          individualView.getSearchField().initEvent(event);
     }

     @Override
     protected void appendData() {

//          IndividualResponseModel data = objMapper.readValue(responseData, IndividualResponseModel.class);
//
//          // pagination code
//          dataCount = (int) data.getCount();
//          if (isCheck) { // true get
//               paginationPanel.setTotalPage(data.getCount(), pageSize); // set totalPage and pageSize to pagination
//          } else { // false search
//               paginationPanel.resetPage(dataCount);
//          }
//
//          listData.clear();
//
//          listData = data.getData();

     }

}
