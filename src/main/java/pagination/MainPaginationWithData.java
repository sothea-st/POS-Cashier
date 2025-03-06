package pagination;

import Components.Color.WindowColor;
import Components.Event.ButtonEvent;
import Components.Fonts.WindowFonts;
import Components.NotFound;
import Components.SearchField;
import Constant.JavaConnection;
import Reporting.GroupButtonExport;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import okhttp3.Response;

public abstract class MainPaginationWithData<T extends PaginationData> {

     // variable pagination
     protected String pageNumber = "1";
     protected int pageSize = 10;
     protected boolean isCheckSearch = true;
     protected String searchValue;
     protected int dataCount = 0;
     protected String pageType;

     // variable component
     protected PaginationPanel paginationPanel;
     protected JPanel panelData;
     protected GroupButtonExport groupButtonExport;
     protected SearchField searchField;

     // variable response 
     protected Response response = null;
     protected ObjectMapper objMapper = new ObjectMapper();
     protected String responseData;
     protected List<?> listData = new ArrayList<>();

     private Class<T> typeClass;

     public MainPaginationWithData(
          Class<T> typeClass,
          SearchField searchField,
          PaginationPanel paginationPanel,
          JPanel panelData,
          GroupButtonExport groupButtonExport
     ) {
          this.typeClass = typeClass;
          this.searchField = searchField;
          this.paginationPanel = paginationPanel;
          this.panelData = panelData;
          this.groupButtonExport = groupButtonExport;
          eventSearch();
          eventPagination();

          if (groupButtonExport != null) {
               eventExport();
          }

     }

     protected abstract String routeName(boolean isCheck);

     public void init() {
          read(true);
     }

     private void read(boolean isCheck) {

          if (isCheck) { // get data
               response = JavaConnection.get(routeName(isCheck));
          } else { // search
               response = JavaConnection.get(routeName(isCheck) + searchValue);
          }

          //System.err.println("log view response : " + response);
          try {

               if (response.isSuccessful()) {

                    responseData = response.body().string();

                    objMapper = new ObjectMapper();

                    T dataInstance = objMapper.readValue(responseData, typeClass);

                    loopData(isCheck, dataInstance);

               }

          } catch (Exception e) {
               System.err.println("error get individual : " + e);
          }

     }

     protected void loopData(boolean isCheck, T data) {
          // pagination code
          dataCount = (int) data.getCount();

          if (isCheck) { // true get
               paginationPanel.setTotalPage(data.getCount(), pageSize); // set totalPage and pageSize to pagination
          } else { // false search
               paginationPanel.resetPage(dataCount);
          }

          listData.clear();
          listData.addAll(data.getData());

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

                    appendItem(gbc, i);

               }
               paginationPanel.setVisible(true);
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

     protected abstract void appendItem(GridBagConstraints gbc, int i);

     protected void delete(String route) {
          UIManager UI = new UIManager();
          UI.put("OptionPane.background", WindowColor.mediumGreen);
          UI.put("Panel.background", WindowColor.mediumGreen);
          UI.put("OptionPane.messageFont", WindowFonts.timeNewRomanBold14);

          int resp = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete ?",
               "Delete", JOptionPane.YES_NO_OPTION);

          if (resp == JOptionPane.YES_OPTION) {
               Response response = JavaConnection.delete(route);

               try {
                    if (response.isSuccessful()) {
                         read(true);
                    }
               } catch (Exception e) {
                    System.err.println("error delete promotion : " + e);
               }
          }
     }

     // action search
     protected void eventSearch() {
          // this event was called when user type on searchTextField 
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onKeyType() {
                    TimerTask task = new TimerTask() {
                         @Override
                         public void run() {
                              searchValue = searchField.getValueTextSearch();

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
          searchField.initEvent(event);
     }

     // action pagination
     protected void eventPagination() {
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onMouseClick(String value) {
                    if (isCheckSearch) {
                         int _value = Integer.parseInt(value);
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

     //Action Export
     protected void eventExport() {

          ButtonEvent eventExel = new ButtonEvent() {
               @Override
               public void onMouseClick() {
                    //new ExportIndividualExcel(columnHeader, titleEn, titleKh).export();
                    exportExcel();
               }
          };
          groupButtonExport.excelEvent(eventExel);

          ButtonEvent eventPdf = new ButtonEvent() {
               @Override
               public void onMouseClick() {
                    //new ExportIndividualPDF(columnHeader, titleEn, titleKh).export();
                    exportPDF();
               }
          };
          groupButtonExport.pdfEvent(eventPdf);

          ButtonEvent eventCsv = new ButtonEvent() {
               @Override
               public void onMouseClick() {
                    //new ExportIndividualPDF(columnHeader, titleEn, titleKh).export();
                    exportCSV();
               }
          };
          groupButtonExport.csvEvent(eventCsv);
     }

     protected void exportExcel(){};

     protected void exportPDF(){};

     protected void exportCSV(){};

}
