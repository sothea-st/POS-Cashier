package feature.company_profile.individual.controller;

import Components.Color.WindowColor;
import Components.Event.ButtonEvent;
import Components.Fonts.WindowFonts;
import Components.NotFound;
import Constant.JavaConnection;
import Constant.JavaRoute;
import Reporting.GroupButtonExport;
import com.fasterxml.jackson.databind.ObjectMapper;
import feature.company_profile.individual.view.IndividualCreate;
import feature.company_profile.individual.view.IndividualDetail;
import feature.company_profile.individual.view.IndividualView;
import feature.company_profile.individual.component.IndividualRowData;
import feature.company_profile.individual.export.ExportIndividualExcel;
import feature.company_profile.individual.export.ExportIndividualPDF;
import feature.company_profile.individual.model.IndividualModel;
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
import javax.swing.JPanel;
import javax.swing.UIManager;
import lombok.Getter;
import lombok.Setter;
import okhttp3.Response;
import pagination.PaginationPanel;

@Setter
@Getter
public class IndividualController {

     // variable pagination
     private String pageNumber = "1";
     private int pageSize = 10;
     private boolean isCheckSearch = true;
     private String searchValue;
     private int dataCount = 0;
     private String pageType;
     // end variable pagination

     // variable
     private IndividualView individualView;
     private PaginationPanel paginationPanel;
     private JPanel panelData;
     private List<IndividualModel.IndividualDetail> listData = new ArrayList<>();
     private GroupButtonExport groupButtonExport;

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

     public IndividualController(IndividualView individualView) {
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

     public void read(boolean isCheck) {

          Response response = null;

          if (isCheck) { // get data
               response = JavaConnection.get(JavaRoute.companyProfile + "?pageNumber=" + pageNumber + "&pageSize=" + pageSize + "&code=Individual");
          } else { // search
               response = JavaConnection.get(JavaRoute.companyProfile + "/search?pageNumber=" + pageNumber + "&pageSize=" + pageSize + "&code=Individual&search=" + searchValue);
          }

          System.err.println("log view response : " + response);
          try {

               if (response.isSuccessful()) {

                    String responseData = response.body().string();

                    ObjectMapper objMapper = new ObjectMapper();

                    IndividualModel data = objMapper.readValue(responseData, IndividualModel.class);

                    // pagination code
                    dataCount = (int) data.getCount();
                    if (isCheck) { // true get
                         paginationPanel.setTotalPage(data.getCount(), pageSize); // set totalPage and pageSize to pagination
                    } else { // false search
                         paginationPanel.resetPage(dataCount);
                    }

                    listData.clear();

                    listData = data.getData();

                    appendData();

               }

          } catch (Exception e) {
               System.err.println("error get individual : " + e);
          }

     }

     private void appendData() {

          panelData.removeAll();
          GridBagLayout gridBagLayout = new GridBagLayout();
          gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
          gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

          panelData.setLayout(gridBagLayout);

          int x = 0;
          int y = 0;

          if (!listData.isEmpty()) {
               for (int i = 0; i < listData.size(); i++) {
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridx = x;
                    gbc.gridy = y;
                    gbc.gridwidth = 1;
                    gbc.anchor = gbc.NORTH;
                    x++;
                    if (x == 1) {
                         x = 0;
                         y++;
                    }

                    IndividualModel.IndividualDetail detail = listData.get(i);

                    Integer id = detail.getId();

                    IndividualRowData rowData = new IndividualRowData(detail);

                    ButtonEvent event = new ButtonEvent() {
                         @Override
                         public void onMouseClick() {
                              delete(id);
                         }

                         @Override
                         public void onClick() {
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
                    paginationPanel.setVisible(true);
                    panelData.add(rowData, gbc);
               }
          } else {
               panelData.setLayout(new BorderLayout());
               NotFound nofound = new NotFound();
               panelData.add(nofound, BorderLayout.CENTER);
               panelData.add(nofound);
               panelData.revalidate();
               panelData.repaint();
               paginationPanel.setVisible(false);
          }

          panelData.revalidate();
          panelData.repaint();

     }

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

}
