package DeleteAndCancel;

import ButtonPackage.ButtonCancel;
import Color.WindowColor;
import Components.BoxItem;
import Components.SubtotalPanel;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaMessage;
import Constant.JavaRoundDown;
import Constant.JavaRoundUpKhr;
import Constant.JavaRoute;
import Event.ButtonEvent;
import FormComponent.combobox.JavaComboBoxSelection;
import Model.PackageProduct.ProductIDModel;
import Products.ProductBox;
import java.awt.Component;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import main_validation.JavaValidation;
import okhttp3.Response;
import org.json.JSONObject;

public class DeleteDialog extends javax.swing.JDialog {

    // declar variable
    private JPanel panelProduct;
    private JPanel detailItem;
    private Component[] listCom;
    private int productId;
    private SubtotalPanel subtotalPanel;
    DecimalFormat dm = new DecimalFormat("$ #,##0.00");
    DecimalFormat kh = new DecimalFormat("#,##0");
    private String reasonId;
    private Button.Button btnPayment;
    private ButtonPackage.ButtonCancel btnCancel;
    private Button.Button buttonHoldOrder;
    private ProductBox productBox;
    private int qty;
    private String barcode;
    private Button.Button btnReturn;
    private JLabel titleOrder;

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public DeleteDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        panelDelete.setBackground(WindowColor.mediumGreen);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        cmdReason();
        comboBoxReason.requestFocus();
    }

    private void cmdReason() {
        JavaComboBoxSelection.addComboBox(comboBoxReason,
                JavaRoute.reason + "cancel",
                "reason",
                JavaComboBoxSelection.DESC);

        ButtonEvent event = new ButtonEvent() {
            @Override
            public void onSelected(String id) {
                reasonId = id;
            }
        };
        comboBoxReason.initEvent(event);
    }
     

    public void deleteItem() {
        double sumSubTotalUsd = 0;
        double sumDiscount = 0;

        for (int i = 0; i < listCom.length; i++) {
            var d = (BoxItem) listCom[i];
            if (productId == d.getProductId()) {
                detailItem.remove(i);
                detailItem.revalidate();
                detailItem.repaint();
            } else {
                var data = (BoxItem) listCom[i];
                String priceStr = data.getLabelPrice();
                priceStr = priceStr.replace("$", "");
                priceStr = priceStr.replace(",", "");
                double price = Double.valueOf(priceStr);
                int qty = data.getQty();
                double amount = price * qty;
                sumSubTotalUsd += amount;

                String discount = data.getDiscountAmount();
                discount = discount.replace("$", "");
                discount = discount.replace(",", "");
                double discountValue = JavaConstant.getReplace(d.getDiscountAmount());
                sumDiscount += discountValue;
            }
        }

        Component[] l = detailItem.getComponents();

        if (l.length == 0) {
            btnPayment.setBackground(WindowColor.lightGray);
            btnCancel.setBackground(WindowColor.lightGray);
            buttonHoldOrder.setBackground(WindowColor.lightGray);
            btnReturn.setBackground(WindowColor.brown);
            titleOrder.setVisible(false);
            subtotalPanel.setLabelSubTitleToZero();
            return;
        }

        subtotalPanel.setLabelSubtotalUsd(dm.format(sumSubTotalUsd));
        double _subTotalKh = JavaRoundDown.roundDown("" + sumSubTotalUsd * JavaConstant.exchangeRate);
        subtotalPanel.setLabelSubtotalKhr(JavaRoundUpKhr.setRoundNumber(_subTotalKh));

        subtotalPanel.setLableDiscountUsd(dm.format(sumDiscount));
        double _disKh = JavaRoundDown.roundDown("" + sumDiscount * JavaConstant.exchangeRate);
        if (_disKh > 0) {
            subtotalPanel.setLableDiscountKhr(JavaRoundUpKhr.setRoundNumber(_disKh));
        } else {
            subtotalPanel.setLableDiscountKhr(kh.format(0));
        }

        // total
        double total = sumSubTotalUsd - sumDiscount;
        subtotalPanel.setLableTotalUsd(dm.format(total));
        double _total = JavaRoundDown.roundDown("" + total * JavaConstant.exchangeRate);
        subtotalPanel.setLableTotalKhr(JavaRoundUpKhr.setRoundNumber(_total));

        this.dispose();
    }

    public JPanel getPanelProduct() {
        return panelProduct;
    }

    public void setPanelProduct(JPanel panelProduct) {
        this.panelProduct = panelProduct;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDelete = new javax.swing.JPanel();
        labelPopUpTitle2 = new Components.LabelPopUpTitle();
        buttonCancel1 = new ButtonPackage.ButtonCancel();
        buttonSave1 = new ButtonPackage.ButtonSave();
        comboBoxReason = new FormComponent.combobox.JavaCombobox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelDelete.setPreferredSize(new java.awt.Dimension(379, 155));

        labelPopUpTitle2.setLabelTitle("Delete");

        buttonCancel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCancel1MouseClicked(evt);
            }
        });

        buttonSave1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSave1MouseClicked(evt);
            }
        });

        comboBoxReason.setLabelName("Reason *");

        javax.swing.GroupLayout panelDeleteLayout = new javax.swing.GroupLayout(panelDelete);
        panelDelete.setLayout(panelDeleteLayout);
        panelDeleteLayout.setHorizontalGroup(
            panelDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelPopUpTitle2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelDeleteLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(comboBoxReason, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelDeleteLayout.createSequentialGroup()
                        .addComponent(buttonCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        panelDeleteLayout.setVerticalGroup(
            panelDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDeleteLayout.createSequentialGroup()
                .addComponent(labelPopUpTitle2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(comboBoxReason, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSave1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancel1MouseClicked
        this.dispose();
    }//GEN-LAST:event_buttonCancel1MouseClicked

    private void buttonSave1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSave1MouseClicked

        try {

            boolean isCheck = JavaValidation.checkValidation(panelDelete);

            if (isCheck) {
                JSONObject json = new JSONObject();
                json.put("createBy", JavaConstant.cashierId);
                json.put("reasonId", reasonId);
                ArrayList<ProductIDModel> listCancelDetail = new ArrayList<>();
                listCancelDetail.add(new ProductIDModel(productId));
                json.put("listCancelDetail", listCancelDetail);

                Response response = JavaConnection.post(JavaRoute.cancelAndDelete + "delete", json);

                if (response.isSuccessful()) {
                    Component[] listCom = detailItem.getComponents();
                    Component[] listCom1 = panelProduct.getComponents();

                    int saleQty = 0;

                    for (Component c : listCom) {
                        var data = ((BoxItem) c);
                        if (getBarcode().equals(data.getLabelBarcode())) {
                            saleQty = data.getQty();
                            break;
                        }
                    }

                    for (Component c : listCom1) {
                        var data = ((ProductBox) c);
                        if (getBarcode().equals(data.getBarcode())) {
                            int qty = Integer.parseInt(data.getQty());
                            qty = qty + saleQty;
                            data.setQty("" + qty);
                            data.setProductStatus(JavaMessage.inStock);
                        }
                    }

                    if (listCom.length == 1) {
                        detailItem.setBackground(WindowColor.slightGreen);
                        detailItem.setBorder(null);
                    }

                    dispose();
                    deleteItem();
                    // QtyUpdate.updateQty(productId, "add", productBox, quantity);
                    // productBox.setQty("" + sumQty);
                    JavaConstant.productId = 0;
                    JavaConstant.productQTyLeft = 0;
                    JavaConstant.discountAmount = 1;

                } else {
                    JOptionPane.showMessageDialog(this, "Save Failed!");
                    return;
                }
            }

        } catch (Exception e) {
            System.err.println("3333333333 e = " + e);
        }

    }//GEN-LAST:event_buttonSave1MouseClicked

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DeleteDialog dialog = new DeleteDialog(new javax.swing.JFrame(), true);
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

    public Button.Button getBtnPayment() {
        return btnPayment;
    }

    public void setBtnPayment(Button.Button btnPayment) {
        this.btnPayment = btnPayment;
    }

    public JPanel getDetailItem() {
        return detailItem;
    }

    public void setDetailItem(JPanel detailItem) {
        this.detailItem = detailItem;
    }

    public Component[] getListCom() {
        return listCom;
    }

    public void setListCom(Component[] listCom) {
        this.listCom = listCom;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public SubtotalPanel getSubtotalPanel() {
        return subtotalPanel;
    }

    public void setSubtotalPanel(SubtotalPanel subtotalPanel) {
        this.subtotalPanel = subtotalPanel;
    }

    public ButtonCancel getBtnCancel() {
        return btnCancel;
    }

    public void setBtnCancel(ButtonCancel btnCancel) {
        this.btnCancel = btnCancel;
    }

    public Button.Button getButtonHoldOrder() {
        return buttonHoldOrder;
    }

    public void setButtonHoldOrder(Button.Button buttonHoldOrder) {
        this.buttonHoldOrder = buttonHoldOrder;
    }

    public ProductBox getProductBox() {
        return productBox;
    }

    public void setProductBox(ProductBox productBox) {
        this.productBox = productBox;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Button.Button getBtnReturn() {
        return btnReturn;
    }

    public void setBtnReturn(Button.Button btnReturn) {
        this.btnReturn = btnReturn;
    }

    public JLabel getTitleOrder() {
        return titleOrder;
    }

    public void setTitleOrder(JLabel titleOrder) {
        this.titleOrder = titleOrder;
    }

     

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ButtonPackage.ButtonCancel buttonCancel1;
    private ButtonPackage.ButtonSave buttonSave1;
    private FormComponent.combobox.JavaCombobox comboBoxReason;
    private Components.LabelPopUpTitle labelPopUpTitle2;
    private javax.swing.JPanel panelDelete;
    // End of variables declaration//GEN-END:variables
}
