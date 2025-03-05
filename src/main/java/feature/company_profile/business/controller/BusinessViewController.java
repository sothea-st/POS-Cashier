package feature.company_profile.business.controller;

import Components.Color.WindowColor;
import Components.Event.ButtonEvent;
import Components.Fonts.WindowFonts;
import Components.NotFound;
import Constant.JavaConnection;
import Constant.JavaRoute;
import com.fasterxml.jackson.databind.ObjectMapper;
import feature.company_profile.business.component.BusinessRowData;
import feature.company_profile.business.model.BusinessModel;
import feature.company_profile.business.model.BusinessModel.BusinessModelDetail;
import feature.company_profile.business.view.BusinessCreate;
import feature.company_profile.business.view.BusinessView;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import okhttp3.Response;
import pagination.MainPagination;

public class BusinessViewController extends MainPagination {
 
     
     private BusinessView businessView;
     private List<BusinessModelDetail> listData = new ArrayList<>();

     public BusinessViewController(BusinessView businessView) {
          this.businessView = businessView;
          this.panelData = businessView.getPanelData();
          this.paginationPanel = businessView.getPaginationPanel();
     }

     public void init() {
          read(true); // read data
          eventPagination(); // pagination action
          eventSearch(); // search action
     }

     public void read(boolean isCheck) {

          Response response = null;

          if (isCheck) { // get data
               response = JavaConnection.get(JavaRoute.companyProfile + "?pageNumber=" + pageNumber + "&pageSize=" + pageSize + "&code=Business");
          } else { // search
               response = JavaConnection.get(JavaRoute.companyProfile + "/search?pageNumber=" + pageNumber + "&pageSize=" + pageSize + "&code=Business&search=" + searchValue);
          }

//          System.err.println("log view response : " + response);
          try {

               if (response.isSuccessful()) {

                    String responseData = response.body().string();

                    ObjectMapper objMapper = new ObjectMapper();

                    BusinessModel data = objMapper.readValue(responseData, BusinessModel.class);

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

          appendData();
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

                    BusinessModelDetail detail = listData.get(i);

                    Integer id = detail.getId();

                    BusinessRowData rowData = new BusinessRowData(detail);

                    ButtonEvent event = new ButtonEvent() {

                         @Override
                         public void onDelete() {
                              delete(id);
                         }

                         @Override
                         public void onEdit() {
                              BusinessCreate businessCreate = new BusinessCreate(new JFrame(), true);
                              businessCreate.update(detail,businessView);
                              businessCreate.setVisible(true);
                              
                         }

                    };
                    rowData.initEvent(event);

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
                              searchValue = businessView.getSearchField().getValueTextSearch();
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
          businessView.getSearchField().initEvent(event);
     }

     private void delete(Integer id) {
          UIManager UI = new UIManager();
          UI.put("OptionPane.background", WindowColor.mediumGreen);
          UI.put("Panel.background", WindowColor.mediumGreen);
          UI.put("OptionPane.messageFont", WindowFonts.timeNewRomanBold14);

          int resp = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete ?",
               "Delete", JOptionPane.YES_NO_OPTION);

          if (resp == JOptionPane.YES_OPTION) {
               Response response = JavaConnection.delete(JavaRoute.companyProfile + "/" + id + "?code=Business");

               try {
                    if (response.isSuccessful()) {
                         read(true);
                    }
               } catch (Exception e) {
                    System.err.println("error delete promotion : " + e);
               }
          }
     }
}
