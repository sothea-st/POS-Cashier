package Reporting;

import Color.WindowColor;
import Components.NotFound;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import CustomeUI.CustomScrollBarUI;
import Event.ButtonEvent;
import Model.Userlogin.UserDataModel;
import Model.Userlogin.UserSuccessModel;
import Products.ListProduct;
import Reporting.ReportingItem.ReportOfReceive;
import Reporting.export.ExportReportReceiveToCSV;
import Reporting.export.ExportReportReceiveToExcel;
import Reporting.export.ExportReportReceiveToPDF;
import Reporting.model.ReportReceiveDetail;
import Reporting.model.ReportReceiveResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import pdf.PrintListPDF;
import pdf.PrintToCSV;
import pdf.PrintToExcel;

public class ReportingPurchaseReceive extends javax.swing.JDialog {

    private String dateFromValue;
    private String dateToValue;
    private String userId;
    private String pageNumber = "0";
    private int pageSize = 10;
    private boolean isCheckSearch = true;
    private String searchValue;
    public ArrayList<ReportReceiveDetail> listDetail = new ArrayList<>();

    public ReportingPurchaseReceive(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        searchField.setFocus();
        // custome scrollbar ui
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        jScrollPane1.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
        // custom scroll speed jscrollPane for vertical
        JScrollBar verticalScrollBar = jScrollPane1.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(30);
        verticalScrollBar.setBlockIncrement(35);
        JavaConstant.addTitleAndLogo(this, "Reporting Purchase Receive");

        // set background color
        listGetOrder.setBackground(WindowColor.mediumGreen);
        header.setBackground(WindowColor.darkGreen);

        addComboUser();
        // action get select brand
        ButtonEvent event = new ButtonEvent() {
            @Override
            public void onSelect(String key) {
                userId = key;
            }
        };
        userCombobox.initEvent(event);

        eventPagination();

        ButtonEvent btnevent = new ButtonEvent() {
            @Override
            public void onFocusGain() {

            }
        };
        dateFrom.initEvent(btnevent);
        dateTo.initEvent(btnevent);

        eventSearchPurchaseReceive();
        groupEvent();
        paginationPanel.setVisible(false);

    }

    private void groupEvent() {
        // event export to excel
        ButtonEvent excel = new ButtonEvent() {
            @Override
            public void onMouseClick() {
                export(1);
            }
        };

        groupButtonExport.excelEvent(excel);

        // event export to csv
        ButtonEvent csv = new ButtonEvent() {
            @Override
            public void onMouseClick() {
                export(2);
            }
        };

        groupButtonExport.csvEvent(csv);
        // event export to pdf
        ButtonEvent pdf = new ButtonEvent() {
            @Override
            public void onMouseClick() {
                export(3);
            }
        };

        groupButtonExport.pdfEvent(pdf);
    }

    //Export
    private void export(int type) {
        if (listDetail.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Can not export .");
            return;
        }

        Response response = null;
        String endpoint = "";

        if (userId == null) {
            response = JavaConnection.get(JavaRoute.reportReceive + "?pageNumber=" + pageNumber + "&pageSize=" + pageSize + "&dateFrom=" + dateFromValue + "&dateTo=" + dateToValue);
        } else {
            endpoint = "?pageNumber=" + pageNumber + "&pageSize=" + pageSize + "&dateFrom=" + dateFromValue + "&dateTo=" + dateToValue + "&receiveId=" + userId;
            response = JavaConnection.get(JavaRoute.reportReceive + endpoint);
        }

        try {
            String responseData = response.body().string();
            ObjectMapper objectMapper = new ObjectMapper();
            ReportReceiveResponse data = objectMapper.readValue(responseData, ReportReceiveResponse.class);
            ReportReceiveDetail[] lists = data.getData();
            listDetail.clear();
            listDetail.addAll(Arrays.asList(lists));

            switch (type) {
                case 1 -> {
                    ListProduct.msgPrint(PrintToExcel.folderPath);
                    ExportReportReceiveToExcel.toExcel(listDetail);
                    break;
                }
                case 2 -> {
                    ListProduct.msgPrint(PrintToCSV.folderPath);
                    ExportReportReceiveToCSV.toCSV(listDetail);
                    break;
                }

                case 3 -> {
                    ListProduct.msgPrint(PrintListPDF.folderPath);
                    try {
                        ExportReportReceiveToPDF.printListPdf(listDetail);
                    } catch (IOException ex) {
                        Logger.getLogger(ReportingImportDetail.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

        } catch (Exception e) {
            System.err.println("error export : " + e);
        }
    }

    //Combobox
    private void addComboUser() {
        try {
            HashMap<String, String> map = new HashMap<>();
            Response response = JavaConnection.get(JavaRoute.userAccount);
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                ObjectMapper objMap = new ObjectMapper();
                UserSuccessModel data = objMap.readValue(responseData, UserSuccessModel.class);
                UserDataModel[] listData = data.getData();
                for (UserDataModel user : listData) {
                    int userId = user.getId();
                    String userName = user.getFullName();
                    map.put(userName, "" + userId);
                }
                userCombobox.setMap(map);
            } else {
                System.err.println("fail loading user");
            }

        } catch (Exception e) {
            System.err.println("error = " + e);
        }
    }
    
    //Pagination
    private void eventPagination() {
        ButtonEvent paginationEvent = new ButtonEvent() {
            @Override
            public void onMouseClick(String value) {
                if (isCheckSearch) {
                    int _value = Integer.parseInt(value) - 1; // value pageNumber star from 0 
                    pageNumber = String.valueOf(_value);
                    getReport(true);
                } 
            }
        };
        paginationPanel.initEvent(paginationEvent);
    }

    //Action Search
    private void eventSearchPurchaseReceive() {
        // this event was called when user type on searchTextField 
        ButtonEvent events = new ButtonEvent() {
            @Override
            public void onKeyType() {
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        searchValue = searchField.getValueTextSearch();
                        paginationPanel.resetPage();
                        pageNumber = "0";
                        
                        if (searchValue.isEmpty()) {
                            isCheckSearch = true;
                            pageNumber = "0";
                            getReport(true);
                            return;
                        }
                        getReport(false);
                    }
                };
                Timer time = new Timer();
                time.schedule(task, 500);
            }
        };
        searchField.initEvent(events);
    }

    void reloadPanel() {
        listGetOrder.removeAll();
        listGetOrder.revalidate();
        listGetOrder.repaint();
    }

    void appendPurchaseReceive(ReportReceiveDetail[] lists) {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // one row has 5 column
        gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        reloadPanel();
        listGetOrder.setLayout(gridBagLayout);

        int x = 0;
        int y = 0;

        if (lists.length == 0) {
            listGetOrder.setLayout(new BorderLayout());
            NotFound nofound = new NotFound();
            listGetOrder.add(nofound, BorderLayout.CENTER);
            listGetOrder.add(nofound);
            listGetOrder.revalidate();
            listGetOrder.repaint();
            paginationPanel.setVisible(false);
            return;
        }

        for (int i = 0; i < lists.length; i++) {
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
            var data = lists[i];

            ReportOfReceive b = new ReportOfReceive();
            b.setData(
                    String.valueOf(i + 1),
                    String.valueOf(data.getVendorName()),
                    String.valueOf(data.getTransactionNo()),
                    String.valueOf(data.getReferenceNo()),
                    String.valueOf(data.getTransactionDate()),
                    String.valueOf(data.getReceiveBy()),
                    String.valueOf(data.getTotalQty()),
                    "$ ".concat(String.valueOf(data.getTotalCost())),
                    String.valueOf(StringUtils.capitalize(data.getRemark())));
            
            paginationPanel.setVisible(true);
            listGetOrder.add(b, gbc);
        }

        listGetOrder.revalidate();
        listGetOrder.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        searchField = new Components.SearchField();
        groupButtonExport = new Reporting.GroupButtonExport();
        buttonSave = new ButtonPackage.ButtonSave();
        dateFrom = new DatePicker.DatePicker();
        dateTo = new DatePicker.DatePicker();
        userCombobox = new Components.ComboBox();
        label1 = new Components.Label();
        label2 = new Components.Label();
        label3 = new Components.Label();
        btnCancel = new Button.Button();
        paginationPanel = new pagination.PaginationPanel();
        header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listGetOrder = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        searchField.setPlaceholder("Search");
        searchField.setValueTextSearch("");

        buttonSave.setTitleButton("Apply");
        buttonSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSaveMouseClicked(evt);
            }
        });

        label1.setLabelName("Date From");

        label2.setLabelName("Date To");

        label3.setLabelName("Received By");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(groupButtonExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(userCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userCombobox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateFrom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateTo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(groupButtonExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        btnCancel.setButtonName("Close");
        btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelMouseClicked(evt);
            }
        });

        header.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("#");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Reference №");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Transaction Date");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Vendor Name");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Total Qty");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Total Cost");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Transaction №");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Status");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Received By");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jScrollPane1.setBackground(new java.awt.Color(176, 215, 181));
        jScrollPane1.setBorder(null);

        listGetOrder.setBackground(new java.awt.Color(176, 215, 181));

        javax.swing.GroupLayout listGetOrderLayout = new javax.swing.GroupLayout(listGetOrder);
        listGetOrder.setLayout(listGetOrderLayout);
        listGetOrderLayout.setHorizontalGroup(
            listGetOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        listGetOrderLayout.setVerticalGroup(
            listGetOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 431, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(listGetOrder);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(paginationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addGap(20, 20, 20))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paginationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked
         getReport(true);

    }//GEN-LAST:event_buttonSaveMouseClicked

    private void getReport(boolean isCheck) {

        dateFromValue = dateFrom.getValueTextField();
        dateToValue = dateTo.getValueTextField();

        if (dateFromValue == null || dateFromValue.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Date From can not be empty!");
            return;
        }

        if (dateToValue == null || dateToValue.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Date To can not be empty!");
            return;
        }

        dateFromValue = JavaConstant.formateDateYYYYMMDD(dateFromValue);
        dateToValue = JavaConstant.formateDateYYYYMMDD(dateToValue);

        Response response = null;
        String endpoint = "";

        if (isCheck) {
            if (userId == null) {
                response = JavaConnection.get(JavaRoute.reportReceive + "?pageNumber=" + pageNumber + "&pageSize=" + pageSize + "&dateFrom=" + dateFromValue + "&dateTo=" + dateToValue);
            } else {
                endpoint = "?pageNumber=" + pageNumber + "&pageSize=" + pageSize + "&dateFrom=" + dateFromValue + "&dateTo=" + dateToValue + "&receiveId=" + userId;
                response = JavaConnection.get(JavaRoute.reportReceive + endpoint);
            }
        } else {
            isCheckSearch = false;
            if (userId == null) {
                response = JavaConnection.get(JavaRoute.searchReportPurchaseReceive + searchValue + "?pageNumber=" + pageNumber + "&pageSize=50" + "&dateFrom=" + dateFromValue + "&dateTo=" + dateToValue);
            } else {
                endpoint = "?pageNumber=" + pageNumber + "&pageSize=50" + "&dateFrom=" + dateFromValue + "&dateTo=" + dateToValue + "&receiveId=" + userId;
                response = JavaConnection.get(JavaRoute.searchReportPurchaseReceive + searchValue + endpoint);
            }
        }

        try {

            String responseData = response.body().string();
            JSONObject jsonResponse = new JSONObject(responseData);
            if (jsonResponse.has("error")) {
                JSONObject error = jsonResponse.getJSONObject("error");
                String reason = error.getString("reason");
                JOptionPane.showMessageDialog(null, reason);
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                ReportReceiveResponse data = objectMapper.readValue(responseData, ReportReceiveResponse.class);
                ReportReceiveDetail[] lists = data.getData();

                if (isCheck) {
                    paginationPanel.setTotalPage(data.getCount(), pageSize);
                } else {
                    paginationPanel.resetPage();
                }

                listDetail.clear();
                listDetail.addAll(Arrays.asList(lists));
                appendPurchaseReceive(lists);
                if (lists.length != 0) {
                    paginationPanel.setVisible(true);
                }
            }

        } catch (Exception e) {
            System.err.println("error : " + e);
        }

    }

    private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked
         this.dispose();
    }//GEN-LAST:event_btnCancelMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReportingPurchaseReceive.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReportingPurchaseReceive.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReportingPurchaseReceive.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReportingPurchaseReceive.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ReportingPurchaseReceive dialog = new ReportingPurchaseReceive(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Button.Button btnCancel;
    private ButtonPackage.ButtonSave buttonSave;
    private DatePicker.DatePicker dateFrom;
    private DatePicker.DatePicker dateTo;
    private Reporting.GroupButtonExport groupButtonExport;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private Components.Label label1;
    private Components.Label label2;
    private Components.Label label3;
    private javax.swing.JPanel listGetOrder;
    private pagination.PaginationPanel paginationPanel;
    private Components.SearchField searchField;
    private Components.ComboBox userCombobox;
    // End of variables declaration//GEN-END:variables
}
