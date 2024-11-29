package Setting.Range;

import Color.WindowColor;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import CustomeUI.CustomScrollBarUI;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import Model.Range.Range;
import Model.Range.RangeDetailModel;
import Model.Range.RangeModel;
import Model.Range.RangeModel.RangeDetail;
import Setting.Category.NoDataAvaibalePanel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import okhttp3.Response;
import org.json.JSONObject;

public class ListRange extends javax.swing.JDialog {

    String searchValue;
    private String pageNumber = "0";
    private int pageSize = 10;
    private boolean isCheckSearch = true;
    private int dataCount = 0;
    private String pageType;
    
    public ListRange(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        jScrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
        // custom scroll speed jscrollPane for vertical
        JScrollBar verticalScrollBar = jScrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(30);
        verticalScrollBar.setBlockIncrement(35);
        
        header.setBackground(WindowColor.darkGreen);
        JavaConstant.addTitleAndLogo(this, "Range");
        
        getRange(listGetRange,true,pageNumber);
        eventSearchRange();
        eventPagination();
    }
    
    private void eventPagination() {
        ButtonEvent event = new ButtonEvent() {
            @Override
            public void onMouseClick(String value) {
                 if (isCheckSearch) {
                      int _value = Integer.parseInt(value) - 1; // value pageNumber star from 0 
                      pageNumber = String.valueOf(_value);
                      getRange(listGetRange,true,pageNumber);
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
    
    public void getRange(JPanel jpanelData, boolean isCheck, String pageNumber) {
        try {
            
            Response response = null;
            if (isCheck) { // isCheck true get items
                 response = JavaConnection.get(JavaRoute.range + "?pageNumber=" + pageNumber + "&pageSize=10");
            } else { // isCheck false search
                 isCheckSearch = false;
                 response = JavaConnection.get(JavaRoute.range + "/search?" + searchValue);
            }
            
            System.out.println("response : " + response);

            if (response.isSuccessful()) {
                String responseData = response.body().string();
                ObjectMapper objMap = new ObjectMapper();
                RangeModel data = objMap.readValue(responseData, RangeModel.class);
                RangeDetail[] listData = data.getData();
                
                if (isCheck) {
                    paginationPanel.setTotalPage(data.getCount(), pageSize);
                } else {
                    paginationPanel.resetPage();
                }
                              
                assignRange(listData, jpanelData);
            } else {
                System.err.println("fail loading range");
            }
        } catch (Exception e) {
            System.err.println("error getting range " + e);
        }
    }
     
    public void assignRange(RangeDetail[] listData, JPanel listGetRange) {
        ArrayList<Range> range = new ArrayList<>();
          
        for (int i = 0; i < listData.length; i++) {
            var obj = listData[i];
            Range getRange = new Range(
                    obj.getId(),
                    obj.getRangeNameEn(),
                    obj.getRangeNameEn(),
                    obj.getWarehouse().getWarehouseNameEn()
            );
            range.add(getRange);
        }

        appendRange(range, listGetRange);
    }
    
    private void reloadPanel() {
        listGetRange.removeAll();
        listGetRange.revalidate();
        listGetRange.repaint();
    }
    
    void appendRange(ArrayList<Range> listRange, JPanel listGetRange) {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // one row has 5 column
        gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        listGetRange.setLayout(gridBagLayout);
        reloadPanel();
        
        int x = 0;
        int y = 0;
             
        if(listRange.size() > 0){
            for (int i = 0; i < listRange.size(); i++) {
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

                var listData = listRange.get(i);
                
                GetRange b = new GetRange();

                ButtonEvent events = new ButtonEvent() {
                    @Override
                    public void onSelect(String Key) {  // event edit
                        AddRange edit = new AddRange(new JFrame(), true);
                        try {
                            Response response = JavaConnection.get(JavaRoute.range + "/" + listData.getId());
                            String responseData = response.body().string();
                            ObjectMapper objMap = new ObjectMapper();
                            RangeDetailModel data = objMap.readValue(responseData, RangeDetailModel.class);
                            
                            System.out.println("data : " + data);

                            edit.setId(data.getData().getId());
                            edit.setListGetRange(listGetRange);
                            edit.setPageNumber(pageNumber);
                            edit.setObj(ListRange.this);

                            edit.setValueEdit(
                                data.getData().getRangeNameEn(),
                                data.getData().getRangeNameKh(),
                                ""+data.getData().getWarehouse().getId()
                            );

                            edit.setVisible(true);
                        } catch (Exception e) {
                             System.err.println("error getting warehouse " + e);
                        }
                    }
                    
                    
                    @Override
                    public void onRemove(String Key) {  // event delete brand
                        try {
                            UIManager UI = new UIManager();
                            UI.put("OptionPane.background", WindowColor.mediumGreen);
                            UI.put("Panel.background", WindowColor.mediumGreen);
                            UI.put("OptionPane.messageFont", WindowFonts.timeNewRomanBold14);

                            int resp = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this range?",
                                    "Delete Range?", JOptionPane.YES_NO_OPTION);

                            if (resp == JOptionPane.YES_OPTION) {
                                JSONObject json = new JSONObject();
                                json.put("status", false);
                                json.put("isDeleted", true);
                                Response response = JavaConnection.delete(JavaRoute.range + "/" + listData.getId(), json);

                                if (response.isSuccessful()) {
                                    
                                    dataCount = dataCount - 1;
                                    int totalP = pageSize * Integer.valueOf(pageNumber);
                                    if (dataCount == totalP) {
                                         paginationPanel.resetPage(pageType, pageNumber);
                                         int _value = Integer.parseInt(pageNumber) - 1; // value pageNumber star from 0 
                                         pageNumber = String.valueOf(_value);
                                    }
                                    
                                    listGetRange.removeAll();
                                    listGetRange.revalidate();
                                    listGetRange.repaint();
                                    getRange(listGetRange,true,pageNumber);
                                    System.out.println("Successful deleted ");
                                }
                            } else {
                                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                            }

                        } catch (Exception e) {
                            System.err.println("error getting warehouse " + e);
                        }
                    }
                };

                b.initEvent(events);
                b.setId(listData.getId());
                
                b.setRangeNameEn(listData.getRangeNameEn());
                b.setRangeNameKh(listData.getRangeNameKh());
                b.setWarehouseName(listData.getWarehouse());
                paginationPanel.setVisible(true);
                listGetRange.add(b, gbc);
            }  
        }else{
            NoDataAvaibalePanel no = new NoDataAvaibalePanel();
            listGetRange.add(no);
            paginationPanel.setVisible(false);
        }
        
        listGetRange.revalidate();
        listGetRange.repaint();
    }
    
    //Action Search
    private void eventSearchRange() {        
        // this event was called when user type on searchTextField 
        ButtonEvent event = new ButtonEvent() {
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
                                getRange(listGetRange,true,pageNumber);
                                return;
                           }
                           getRange(listGetRange,false,pageNumber);
                      }
                 };

                 Timer timer = new Timer();
                 timer.schedule(task, 500);

            }
        };
        searchField.initEvent(event);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelListUom = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        searchField = new Components.SearchField();
        jScrollPane = new javax.swing.JScrollPane();
        listGetRange = new javax.swing.JPanel();
        buttonCancel1 = new ButtonPackage.ButtonCancel();
        btnAdd = new Button.Button();
        paginationPanel = new pagination.PaginationPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        header.setBackground(new java.awt.Color(0, 0, 0));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Actions");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Range Name (KH)");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Range Name");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Warehouse");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        searchField.setPlaceholder("Search");
        searchField.setValueTextSearch("");

        jScrollPane.setBackground(new java.awt.Color(176, 215, 181));
        jScrollPane.setBorder(null);

        listGetRange.setBackground(new java.awt.Color(176, 215, 181));

        javax.swing.GroupLayout listGetRangeLayout = new javax.swing.GroupLayout(listGetRange);
        listGetRange.setLayout(listGetRangeLayout);
        listGetRangeLayout.setHorizontalGroup(
            listGetRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 664, Short.MAX_VALUE)
        );
        listGetRangeLayout.setVerticalGroup(
            listGetRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );

        jScrollPane.setViewportView(listGetRange);

        buttonCancel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCancel1MouseClicked(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(47, 155, 70));
        btnAdd.setButtonName("+ Add Range");
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelListUomLayout = new javax.swing.GroupLayout(panelListUom);
        panelListUom.setLayout(panelListUomLayout);
        panelListUomLayout.setHorizontalGroup(
            panelListUomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelListUomLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelListUomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelListUomLayout.createSequentialGroup()
                        .addComponent(paginationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelListUomLayout.createSequentialGroup()
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane)
                    .addComponent(header, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        panelListUomLayout.setVerticalGroup(
            panelListUomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListUomLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelListUomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelListUomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paginationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelListUom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelListUom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancel1MouseClicked
        dispose();
    }//GEN-LAST:event_buttonCancel1MouseClicked

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        AddRange add = new AddRange(new JFrame(), true);
        add.setPageNumber(pageNumber);
        add.setListGetRange(listGetRange);
        add.setObj(this);
        add.setVisible(true);
    }//GEN-LAST:event_btnAddMouseClicked

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
            java.util.logging.Logger.getLogger(ListRange.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListRange.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListRange.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListRange.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ListRange dialog = new ListRange(new javax.swing.JFrame(), true);
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
    private Button.Button btnAdd;
    private ButtonPackage.ButtonCancel buttonCancel1;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JPanel listGetRange;
    private pagination.PaginationPanel paginationPanel;
    private javax.swing.JPanel panelListUom;
    private Components.SearchField searchField;
    // End of variables declaration//GEN-END:variables
}
