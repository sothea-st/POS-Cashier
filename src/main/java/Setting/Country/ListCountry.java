package Setting.Country;

import BlogCode.JavaBlogImage;
import Color.WindowColor;
import Constant.JavaBaseUrl;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import CustomeUI.CustomScrollBarUI;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import Model.Country.CountryModel;
import Model.Country.DataCountryModel;
import Model.Country.ListCountryModel;
import Setting.Category.NoDataAvaibalePanel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import okhttp3.Response;
import org.json.JSONObject;

public class ListCountry extends javax.swing.JDialog {

    String searchValue;
    private String pageNumber = "0";
    private int pageSize = 10;
    private boolean isCheckSearch = true;
    
    public ListCountry(java.awt.Frame parent, boolean modal) {
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
        JavaConstant.addTitleAndLogo(this, "Country");
        
        getListCountry(listGetCountry, true);
        eventSearchCountry();
        eventPagination();
        
    }
    
    private void eventPagination() {
        ButtonEvent event = new ButtonEvent() {
             @Override
             public void onMouseClick(String value) {
                  if (isCheckSearch) {
                       int _value = Integer.parseInt(value) - 1; // value pageNumber star from 0 
                       pageNumber = String.valueOf(_value);
                       getListCountry(listGetCountry, true);
                  }
             }
        };
        paginationPanel.initEvent(event);
    }
    
    public void getListCountry(JPanel jpanelData, boolean isCheck) {
        try {

            Response response = null;
            if (isCheck) { // isCheck true get items
                 response = JavaConnection.get(JavaRoute.country + "?pageNumber=" + pageNumber + "&pageSize=10");
            } else { // isCheck false search
                 isCheckSearch = false;
                 response = JavaConnection.get(JavaRoute.searchCountry + searchValue + "?pageNumber=" + pageNumber + "&pageSize=50");
            }
            
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                ObjectMapper objMap = new ObjectMapper();
                ListCountryModel data = objMap.readValue(responseData, ListCountryModel.class);
                DataCountryModel[] listData = data.getData();
                
                if (isCheck) {
                    paginationPanel.setTotalPage(data.getCount(), pageSize);
                } else {
                    paginationPanel.resetPage();
                }
                
                assignCountry(listData, jpanelData);
            } else {
                System.err.println("fail loading country");
            }
        } catch (Exception e) {
            System.err.println("error getting country " + e);
        }
    }
    
    
     public void assignCountry(DataCountryModel[] listData, JPanel listGetCountry) {
        ArrayList<CountryModel> country = new ArrayList<>();
          
        for (int i = 0; i < listData.length; i++) {
            var obj = listData[i];
            CountryModel countries = new CountryModel(
                    obj.getId(),
                    obj.getCountryName(),
                    obj.getUuid()
                    
            );
            country.add(countries);
        }

        appendCountry(country, listGetCountry);
    }
     
    private void reloadPanel() {
        listGetCountry.removeAll();
        listGetCountry.revalidate();
        listGetCountry.repaint();
    }
    
    void appendCountry(ArrayList<CountryModel> list, JPanel listGetCountry) {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // one row has 5 column
        gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        listGetCountry.setLayout(gridBagLayout);
        reloadPanel();

        int x = 0;
        int y = 0;

        if(list.size() > 0){
            for (int i = 0; i < list.size(); i++) {
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

                var listData = list.get(i);
                
                GetCountry b = new GetCountry();
                
                ButtonEvent events = new ButtonEvent() {
                    @Override
                    public void onSelect(String Key) {  // event edit
                        AddCountry edit = new AddCountry(new JFrame(), true);
                        try {
                            Response response = JavaConnection.get(JavaRoute.country + "/" + listData.getId());
                            String responseData = response.body().string();
                            ObjectMapper objMap = new ObjectMapper();
                            DataCountryModel data = objMap.readValue(responseData, DataCountryModel.class);

                            edit.setId(data.getId());
                            edit.setListGetCountry(listGetCountry);

                            edit.setValueEdit(
                                data.getCountryName(),
                                new JavaBaseUrl().getBaseUrl() + JavaRoute.bgImage + listData.getUuid(),
                                data.getUuid()
                            );

                            edit.setVisible(true);
                        } catch (Exception e) {
                             System.err.println("error getting country " + e);
                        }
                    }
                    
                    
                    @Override
                    public void onRemove(String Key) {  // event delete country
                        try {
                            UIManager UI = new UIManager();
                            UI.put("OptionPane.background", WindowColor.mediumGreen);
                            UI.put("Panel.background", WindowColor.mediumGreen);
                            UI.put("OptionPane.messageFont", WindowFonts.timeNewRomanBold14);

                            int resp = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this country?",
                                    "Delete Country?", JOptionPane.YES_NO_OPTION);

                            if (resp == JOptionPane.YES_OPTION) {
                                JSONObject json = new JSONObject();
                                Response response = JavaConnection.delete(JavaRoute.country + "/" + listData.getId(), json);

                                if (response.isSuccessful()) {
                                    ListCountry list = new ListCountry(new JFrame(), true);
                                    listGetCountry.removeAll();
                                    listGetCountry.revalidate();
                                    listGetCountry.repaint();
                                    list.getListCountry(listGetCountry,true);
                                    System.out.println("Successful deleted ");
                                }
                            } else {
                                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                            }

                        } catch (Exception e) {
                            System.err.println("error getting country " + e);
                        }
                    }
                };

                b.initEvent(events);
                b.setId(listData.getId());
                b.setCountry(listData.getCountryName());

                try {

                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            // Task to be executed
                            b.setIconEdit(new ImageIcon(JavaBlogImage.getImage(JavaRoute.bgImage + "Edit.png")));
                            b.setIconDelete(new ImageIcon(JavaBlogImage.getImage(JavaRoute.bgImage + "DeleteIcon.png")));
                            
                            try {
                                b.setFlag(new JavaBaseUrl().getBaseUrl() + JavaRoute.bgImage + listData.getUuid());
                            } catch (IOException ex) {
                                Logger.getLogger(ListCountry.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };

                    Timer timer = new Timer();
                    timer.schedule(task, 500); // Delays task execution by 1 second

                } catch (Exception e) {
                    System.err.println("error read image = " + e);
                }

                listGetCountry.add(b, gbc);
            }  
        }else{
            NoDataAvaibalePanel no = new NoDataAvaibalePanel();
            listGetCountry.add(no);
        }
        
        listGetCountry.revalidate();
        listGetCountry.repaint();
    }
    
    
    //Action Search
    private void eventSearchCountry() {
        // this event was called when user type on searchTextField 
        ButtonEvent events = new ButtonEvent() {
            @Override
            public void onKeyType() {
                searchValue = searchField.getValueTextSearch();
                
                if (searchValue.isEmpty()) {
                    isCheckSearch = true;
                    pageNumber = "0";
                    getListCountry(listGetCountry, true);
                    return;
                }
                getListCountry(listGetCountry, false);
            }
        };
        searchField.initEvent(events);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelListAttribute = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        searchField = new Components.SearchField();
        jScrollPane = new javax.swing.JScrollPane();
        listGetCountry = new javax.swing.JPanel();
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
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Image");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Country Name");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        searchField.setPlaceholder("Search");
        searchField.setValueTextSearch("");

        jScrollPane.setBackground(new java.awt.Color(176, 215, 181));
        jScrollPane.setBorder(null);

        listGetCountry.setBackground(new java.awt.Color(176, 215, 181));

        javax.swing.GroupLayout listGetCountryLayout = new javax.swing.GroupLayout(listGetCountry);
        listGetCountry.setLayout(listGetCountryLayout);
        listGetCountryLayout.setHorizontalGroup(
            listGetCountryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 664, Short.MAX_VALUE)
        );
        listGetCountryLayout.setVerticalGroup(
            listGetCountryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );

        jScrollPane.setViewportView(listGetCountry);

        buttonCancel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCancel1MouseClicked(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(47, 155, 70));
        btnAdd.setButtonName("+ Add Country");
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelListAttributeLayout = new javax.swing.GroupLayout(panelListAttribute);
        panelListAttribute.setLayout(panelListAttributeLayout);
        panelListAttributeLayout.setHorizontalGroup(
            panelListAttributeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelListAttributeLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelListAttributeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelListAttributeLayout.createSequentialGroup()
                        .addComponent(paginationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelListAttributeLayout.createSequentialGroup()
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane)
                    .addComponent(header, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        panelListAttributeLayout.setVerticalGroup(
            panelListAttributeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListAttributeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelListAttributeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelListAttributeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paginationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelListAttribute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelListAttribute, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancel1MouseClicked
        dispose();
    }//GEN-LAST:event_buttonCancel1MouseClicked

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        AddCountry add = new AddCountry(new JFrame(), true);
        add.setListGetCountry(listGetCountry);
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
            java.util.logging.Logger.getLogger(ListCountry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListCountry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListCountry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListCountry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ListCountry dialog = new ListCountry(new javax.swing.JFrame(), true);
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
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JPanel listGetCountry;
    private pagination.PaginationPanel paginationPanel;
    private javax.swing.JPanel panelListAttribute;
    private Components.SearchField searchField;
    // End of variables declaration//GEN-END:variables
}
