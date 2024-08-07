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
import Reporting.ReportingItem.ReportOfPurchaseOrder;
import Reporting.export.ExportReportPurchaseOrderToCSV;
import Reporting.export.ExportReportPurchaseOrderToExcel;
import Reporting.export.ExportReportPurchaseOrderToPDF;
import Reporting.model.ReportingDetailResponse;
import Reporting.model.ReportingRespone;
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

public class ReportingPurchaseOrderV2 extends javax.swing.JDialog {

    private String requestById;
    private String checkedById;
    private String approvedById;
    private String rejectedById;
    private String statusValue;

    public ArrayList<ReportingDetailResponse> listDetail = new ArrayList<>();
    private String pageNumber = "0";
    private int pageSize = 15;
    private boolean isCheckSearch = true;
    private String searchValue;
    private String dateFromValue;
    private String dateToValue;

    public ReportingPurchaseOrderV2(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        initComponents();
        searchField.setFocus();
        // custome scrollbar ui
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        jScrollPane1.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
        // custom scroll speed jscrollPane for vertical
        JScrollBar verticalScrollBar = jScrollPane1.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(30);
        verticalScrollBar.setBlockIncrement(35);

        // set background color
        listGetPurchaseOrder.setBackground(WindowColor.mediumGreen);
        header.setBackground(WindowColor.darkGreen);
        JavaConstant.addTitleAndLogo(this, "");

        addComboRequestBy();
        // action get select brand
        ButtonEvent event = new ButtonEvent() {
            @Override
            public void onSelect(String key) {
                requestById = key;
            }
        };
        requestBy.initEvent(event);

        addComboCheckedBy();
        // action get select brand
        ButtonEvent eventOne = new ButtonEvent() {
            @Override
            public void onSelect(String key) {
                checkedById = key;
            }
        };
        checkedBy.initEvent(eventOne);

        addComboApprovedBy();
        // action get select brand
        ButtonEvent eventTwo = new ButtonEvent() {
            @Override
            public void onSelect(String key) {
                approvedById = key;
            }
        };
        approvedBy.initEvent(eventTwo);

        addComboRejectedBy();
        // action get select brand
        ButtonEvent eventThree = new ButtonEvent() {
            @Override
            public void onSelect(String key) {
                rejectedById = key;
            }
        };
        rejectedBy.initEvent(eventThree);

        addComboStatus();
        ButtonEvent events = new ButtonEvent() {
            @Override
            public void onSelect(String key) {
                statusValue = key;
            }
        };
        status.initEvent(events);

        ButtonEvent btnevent = new ButtonEvent() {
            @Override
            public void onFocusGain() {

            }
        };
        dateFrom.initEvent(btnevent);
        dateTo.initEvent(btnevent);

        eventPagination();
        paginationPanel.setVisible(false);
        eventSearchPurchaseOrder();
        groupEvent();
    }

    private void addComboRequestBy() {
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
                requestBy.setMap(map);
            } else {
                System.err.println("fail loading user");
            }

        } catch (Exception e) {
            System.err.println("error = " + e);
        }
    }

    private void addComboCheckedBy() {
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
                checkedBy.setMap(map);
            } else {
                System.err.println("fail loading user");
            }

        } catch (Exception e) {
            System.err.println("error = " + e);
        }
    }

    private void addComboApprovedBy() {
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
                approvedBy.setMap(map);
            } else {
                System.err.println("fail loading user");
            }

        } catch (Exception e) {
            System.err.println("error = " + e);
        }
    }

    private void addComboRejectedBy() {
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
                rejectedBy.setMap(map);
            } else {
                System.err.println("fail loading user");
            }

        } catch (Exception e) {
            System.err.println("error = " + e);
        }
    }

    private void addComboStatus() {
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put("Requested", "requested");
            map.put("Checked", "checked");
            map.put("Approved", "approved");
            map.put("Rejected", "rejected");
            status.setMap(map);

        } catch (Exception e) {
            System.err.println("error = " + e);
        }
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

    private void export(int type) {
        if (listDetail.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Can not export .");
            return;
        }

        Response response = null;

        String route = JavaRoute.getReportPurchaseOrderByStatus + "?dateFrom=" + dateFromValue + "&dateTo=" + dateToValue;

        if (requestById != null) {
            route = route + "&requestId=" + requestById;
        }

        if (checkedById != null) {
            route = route + "&checkId=" + checkedById;
        }

        if (approvedById != null) {
            route = route + "&approvedId=" + approvedById;
        }

        if (rejectedById != null) {
            route = route + "&rejectId=" + rejectedById;
        }

        if (statusValue != null) {
            route = route + "&remark=" + statusValue;
        }

        response = JavaConnection.get(route);

        try {
            String responeData = response.body().string();
            ObjectMapper objectMapper = new ObjectMapper();
            ReportingRespone data = objectMapper.readValue(responeData, ReportingRespone.class);
            ReportingDetailResponse[] lists = data.getData();
            listDetail.clear();
            listDetail.addAll(Arrays.asList(lists));

            switch (type) {
                case 1 -> {
                    ListProduct.msgPrint(PrintToExcel.folderPath);
                    ExportReportPurchaseOrderToExcel.toExcel(listDetail);
                    break;
                }
                case 2 -> {
                    ListProduct.msgPrint(PrintToCSV.folderPath);
                    ExportReportPurchaseOrderToCSV.toCSV(listDetail);
                    break;
                }

                case 3 -> {
                    ListProduct.msgPrint(PrintListPDF.folderPath);
                    try {
                        ExportReportPurchaseOrderToPDF.printListPdf(listDetail);
                    } catch (IOException ex) {
                        Logger.getLogger(ReportingImportDetail.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

        } catch (Exception e) {
            System.err.println("error export : " + e);
        }
    }

    private void eventPagination() {

        ButtonEvent paginationEvent = new ButtonEvent() {
            @Override
            public void onMouseClick(String value) {
                if (isCheckSearch) {
                    int _value = Integer.parseInt(value) - 1; // value pageNumber star from 0 
                    pageNumber = String.valueOf(_value);
                    setData(true);
                }
            }
        };
        paginationPanel.initEvent(paginationEvent);
    }

    //Action Search
    private void eventSearchPurchaseOrder() {
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
                            setData(true);
                            return;
                        }
                        setData(false);
                    }
                };
                Timer timer = new Timer();
                timer.schedule(task, 500);
            }
        };
        searchField.initEvent(events);
    }

    public void reloadPanel() {
        listGetPurchaseOrder.removeAll();
        listGetPurchaseOrder.repaint();
        listGetPurchaseOrder.revalidate();
    }

    public void appendPurchaeOrder(ReportingDetailResponse[] list) {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // one row has 5 column
        gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        reloadPanel();
        listGetPurchaseOrder.setLayout(gridBagLayout);

        int x = 0;
        int y = 0;
        int index = 0;
        for (ReportingDetailResponse data : list) {
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

            index++;
            ReportOfPurchaseOrder b = new ReportOfPurchaseOrder();

            b.setData(
                    String.valueOf(index),
                    String.valueOf(data.getVendorName()),
                    String.valueOf(data.getTransactionNo()),
                    String.valueOf(data.getReferenceNo()),
                    String.valueOf(data.getTransactionDate()),
                    String.valueOf(data.getRequestBy()),
                    String.valueOf(data.getCheckBy()),
                    String.valueOf(data.getApprovedBy()),
                    String.valueOf(data.getRejectBy()),
                    String.valueOf(data.getTotalQty()),
                    "$ ".concat(String.valueOf(data.getTotalCost())),
                    String.valueOf(String.valueOf(StringUtils.capitalize(data.getRemark())))
            );

            paginationPanel.setVisible(true);
            listGetPurchaseOrder.add(b, gbc);
        }

        if (list.length == 0) {
            listGetPurchaseOrder.setLayout(new BorderLayout());
            NotFound nofound = new NotFound();
            listGetPurchaseOrder.add(nofound, BorderLayout.CENTER);
            listGetPurchaseOrder.add(nofound);
            listGetPurchaseOrder.revalidate();
            listGetPurchaseOrder.repaint();
            paginationPanel.setVisible(false);
        }

        listGetPurchaseOrder.revalidate();
        listGetPurchaseOrder.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        searchField = new Components.SearchField();
        groupButtonExport = new Reporting.GroupButtonExport();
        buttonApply = new ButtonPackage.ButtonSave();
        dateFrom = new DatePicker.DatePicker();
        dateTo = new DatePicker.DatePicker();
        requestBy = new Components.ComboBox();
        status = new Components.ComboBox();
        checkedBy = new Components.ComboBox();
        approvedBy = new Components.ComboBox();
        rejectedBy = new Components.ComboBox();
        label1 = new Components.Label();
        label2 = new Components.Label();
        label3 = new Components.Label();
        label4 = new Components.Label();
        label5 = new Components.Label();
        label6 = new Components.Label();
        label7 = new Components.Label();
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
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listGetPurchaseOrder = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        searchField.setPlaceholder("Search");
        searchField.setValueTextSearch("");

        buttonApply.setTitleButton("Apply");
        buttonApply.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonApplyMouseClicked(evt);
            }
        });

        label1.setLabelName("Date From");

        label2.setLabelName("Date To");

        label3.setLabelName("Requested By");

        label4.setLabelName("Checked By");

        label5.setLabelName("Approved By");

        label6.setLabelName("Rejected By");

        label7.setLabelName("Status");

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
                        .addComponent(groupButtonExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(requestBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkedBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(approvedBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rejectedBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonApply, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(7, 7, 7))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(requestBy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateFrom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateTo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonApply, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(checkedBy, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(approvedBy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rejectedBy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        jLabel9.setText("Requested By");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Approved By");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Checked By");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Rejected By");

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
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel5)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jScrollPane1.setBackground(new java.awt.Color(176, 215, 181));
        jScrollPane1.setBorder(null);

        listGetPurchaseOrder.setBackground(new java.awt.Color(176, 215, 181));

        javax.swing.GroupLayout listGetPurchaseOrderLayout = new javax.swing.GroupLayout(listGetPurchaseOrder);
        listGetPurchaseOrder.setLayout(listGetPurchaseOrderLayout);
        listGetPurchaseOrderLayout.setHorizontalGroup(
            listGetPurchaseOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        listGetPurchaseOrderLayout.setVerticalGroup(
            listGetPurchaseOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 645, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(listGetPurchaseOrder);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void buttonApplyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonApplyMouseClicked
         setData(true);
    }//GEN-LAST:event_buttonApplyMouseClicked

    void setData(boolean isCheck) {
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

        try {

            Response response = null;
            if (isCheck) { // isCheck true is get items

                String route = JavaRoute.getReportPurchaseOrderByStatus + "?pageNumber=" + pageNumber + "&pageSize=" + pageSize
                        + "&dateFrom=" + dateFromValue + "&dateTo=" + dateToValue;

                if (requestById != null) {
                    route = route + "&requestId=" + requestById;
                }

                if (checkedById != null) {
                    route = route + "&checkId=" + checkedById;
                }

                if (approvedById != null) {
                    route = route + "&approvedId=" + approvedById;
                }

                if (rejectedById != null) {
                    route = route + "&rejectId=" + rejectedById;
                }

                if (statusValue != null) {
                    route = route + "&remark=" + statusValue;
                }

                response = JavaConnection.get(route);

            } else { // isCheck false is search
                isCheckSearch = false;
                
                String route = JavaRoute.searchReportPurchaseOrder + searchValue + "?pageNumber=" + pageNumber + "&pageSize=50"
                        + "&dateFrom=" + dateFromValue + "&dateTo=" + dateToValue;

                if (requestById != null) {
                    route = route + "&requestId=" + requestById;
                }

                if (checkedById != null) {
                    route = route + "&checkId=" + checkedById;
                }

                if (approvedById != null) {
                    route = route + "&approvedId=" + approvedById;
                }

                if (rejectedById != null) {
                    route = route + "&rejectId=" + rejectedById;
                }

                if (statusValue != null) {
                    route = route + "&remark=" + statusValue;
                }

                response = JavaConnection.get(route);
            }
            
            System.out.println("response : " + response);

            String dataResponse = response.body().string();
            JSONObject jsonResponse = new JSONObject(dataResponse);
            if (jsonResponse.has("error")) {
                JSONObject error = jsonResponse.getJSONObject("error");
                String reason = error.getString("reason");
                JOptionPane.showMessageDialog(null, reason);
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                ReportingRespone data = objectMapper.readValue(dataResponse, ReportingRespone.class);
                ReportingDetailResponse[] lists = data.getData();

                if (isCheck) {
                    paginationPanel.setTotalPage(data.getCount(), pageSize);
                } else {
                    paginationPanel.resetPage();
                }

                listDetail.clear();
                listDetail.addAll(Arrays.asList(lists));
                appendPurchaeOrder(lists);
                if (lists.length != 0) {
                    paginationPanel.setVisible(true);
                }
            }
        } catch (Exception e) {
            System.out.println("error : " + e);
        }
    }

    private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked
         this.dispose();
    }//GEN-LAST:event_btnCancelMouseClicked

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
            java.util.logging.Logger.getLogger(ReportingPurchaseOrderV2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReportingPurchaseOrderV2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReportingPurchaseOrderV2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReportingPurchaseOrderV2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ReportingPurchaseOrderV2 dialog = new ReportingPurchaseOrderV2(new javax.swing.JFrame(), true);
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
    private Components.ComboBox approvedBy;
    private Button.Button btnCancel;
    private ButtonPackage.ButtonSave buttonApply;
    private Components.ComboBox checkedBy;
    private DatePicker.DatePicker dateFrom;
    private DatePicker.DatePicker dateTo;
    private Reporting.GroupButtonExport groupButtonExport;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private Components.Label label4;
    private Components.Label label5;
    private Components.Label label6;
    private Components.Label label7;
    private javax.swing.JPanel listGetPurchaseOrder;
    private pagination.PaginationPanel paginationPanel;
    private Components.ComboBox rejectedBy;
    private Components.ComboBox requestBy;
    private Components.SearchField searchField;
    private Components.ComboBox status;
    // End of variables declaration//GEN-END:variables
}
