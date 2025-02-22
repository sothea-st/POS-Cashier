package feature.promotion.controller;

import Components.Color.WindowColor;
import Components.Event.ButtonEvent;
import Components.Fonts.WindowFonts;
import Constant.JavaConnection;
import Constant.JavaRoute;
import com.fasterxml.jackson.databind.ObjectMapper;
import feature.promotion.model.PromotionModel;
import feature.promotion.model.PromotionModel.PromotionDetail;
import feature.promotion.view.PromotionView;
import feature.promotion.view.component.PromotionRowData;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import okhttp3.Response;

public class PromotionController {

     private String pageNumber = "1";
     private int pageSize = 10;
     private boolean isCheckSearch = true;
     private String searchValue;
     private int dataCount = 0;
     private String pageType;

     private PromotionView view;
     private PromotionDetail[] listData;

     public PromotionController(PromotionView view) {

          this.view = view;

          // call eventSearch
          eventSearch();

          // call eventPagination
          eventPagination();

          // call read
          read(true);
     }

     private void eventSearch() {
          // this event was called when user type on searchTextField 
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onKeyType() {
                    TimerTask task = new TimerTask() {
                         @Override
                         public void run() {
                              searchValue = view.getSearchField().getValueTextSearch();
                              System.err.println("searchValue === " + searchValue);
                              view.getPaginationPanel().resetPage();
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
          view.getSearchField().initEvent(event);
     }

     private void read(boolean isCheck) {

          Response response = null;

          String searchTxt = view.getSearchField().getValueTextSearch();

          if (!isCheck) {
               isCheckSearch = false;
               response = JavaConnection.get(JavaRoute.promotion + "/search" + "?pageNumber=" + pageNumber + "&pageSize=" + pageSize + "&search=" + searchTxt);
          } else {
               response = JavaConnection.get(JavaRoute.promotion + "?pageNumber=" + pageNumber + "&pageSize=" + pageSize);
          }

         // System.err.println(" ============ log view response : " + response);

          try {

               String responseData = response.body().string();

               // create object mapper
               ObjectMapper objectMapper = new ObjectMapper();

               // convert responseData to model
               PromotionModel model = objectMapper.readValue(responseData, PromotionModel.class);

               // pagination code
               dataCount = (int) model.getCount();
               if (isCheck) { // true get
                    view.getPaginationPanel().setTotalPage(model.getCount(), pageSize); // set totalPage and pageSize to pagination
               } else { // false search
                    view.getPaginationPanel().resetPage(dataCount);
               }

               if (listData != null) {
                    Arrays.fill(listData, null);
               }
               listData = model.getData(); // Assign new data

               appendData();

          } catch (Exception e) {
               System.err.println("error read promotion : " + e);
          }
     }

     private void appendData() {

          JPanel panelData = view.getPanelData();

          panelData.removeAll();

          GridBagLayout gridBagLayout = new GridBagLayout();
          gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
          gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

          panelData.setLayout(gridBagLayout);

          int x = 0;
          int y = 0;

          for (PromotionDetail detail : listData) {

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

               Integer promotionId = detail.getId();

               PromotionRowData rowData = new PromotionRowData(detail);

               ButtonEvent event = new ButtonEvent() {
                    @Override
                    public void onDelete() {
                         delete(promotionId);
                    }
               };
               rowData.initEvent(event);

               view.getPaginationPanel().setVisible(true);
               panelData.add(rowData, gbc);
          }

          // Refresh UI
          panelData.revalidate();
          panelData.repaint();
     }

     private void eventPagination() {
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
          view.getPaginationPanel().initEvent(event);
     }

     private void delete(Integer promotionId) {

          UIManager UI = new UIManager();
          UI.put("OptionPane.background", WindowColor.mediumGreen);
          UI.put("Panel.background", WindowColor.mediumGreen);
          UI.put("OptionPane.messageFont", WindowFonts.timeNewRomanBold14);

          int resp = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete ?",
               "Delete", JOptionPane.YES_NO_OPTION);

          if (resp == JOptionPane.YES_OPTION) {
               Response response = JavaConnection.delete(JavaRoute.promotion + "/" + promotionId);

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
