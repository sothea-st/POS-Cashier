package NewDiscounts;

import Color.WindowColor;
import Components.BoxItem;
import Components.SubtotalPanel;
import Constant.JavaConstant;
import Event.ButtonEvent;
import HoldOrder.HoldeModel;
import SwitchButton.EventSwitchSelected;
import java.awt.Component;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Discounting extends javax.swing.JDialog {

    private JPanel detailItem;
    private BoxItem box;
    private SubtotalPanel totalPanel;
    
    DecimalFormat dm = new DecimalFormat("$ #,##0.00");
    DecimalFormat kh = new DecimalFormat("#,##0");
    
    public Discounting(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setBackGroud();
        switchButton();
        inputDis.setLabelTextCenter("0%");
        inputDis.requestFocus();
        type = "percent";
        event();
    }

    String type;
    String percentType;
    
    private void switchButton(){
        switchButton1.addEventSelected(new EventSwitchSelected() {
            @Override
            public void onSelected(boolean selected) {
                if(selected == true){
                    inputDis.setValueTextFieldCenter("");
                    inputDis.setLabelTextCenter("$ 0.00");
                    inputDis.requestFocus();
                    type = "dollar";
                }
                else
                {
                    inputDis.setValueTextFieldCenter("");
                    inputDis.setLabelTextCenter("0%");
                    inputDis.requestFocus();
                    type = "percent";
                }
            }
        });
    }
    
    void event() {
         ButtonEvent btnevent = new ButtonEvent() {
              @Override
              public void onFocusGain() {

              }
         };
         inputDis.initEvent(btnevent);
     }
    
    private void setBackGroud(){
        discountPanel.setBackground(WindowColor.mediumGreen);
        calculatePanel.setBackground(WindowColor.green);
    }

    private void inputDiscount(String value) {
        String discountValue = inputDis.getValueTextFieldCenter();
        if(discountValue == null){
            discountValue = "" ;
        }
            
        discountValue += value;
        
        if(type == "dollar"){
//            if(inputDis.getValueTextFieldCenter().contains("$")){
//                inputDis.setValueTextFieldCenter("$" +discountValue.replace("$", ""));
//            }else{
//                inputDis.setValueTextFieldCenter("$" +discountValue);
//            }
//            
            inputDis.setValueTextFieldCenter(discountValue);
        }
        else{
            
            if(percentType == "hasPercent"){
                inputDis.setValueTextFieldCenter(value);
            }else{
                
                if(inputDis.getValueTextFieldCenter()!= null && inputDis.getValueTextFieldCenter().contains("%")){
                    inputDis.setValueTextFieldCenter(value);
                }
                else{
                    inputDis.setValueTextFieldCenter(discountValue);
                }
            } 
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        discountPanel = new javax.swing.JPanel();
        labelPopUpTitle1 = new Components.LabelPopUpTitle();
        label1 = new Components.Label();
        label2 = new Components.Label();
        calculatePanel = new javax.swing.JPanel();
        tenPercents = new Components.labelDis();
        twentyPercent = new Components.labelDis();
        thirtyPercent = new Components.labelDis();
        fiftyPercent = new Components.labelDis();
        one = new Components.labelDis();
        two = new Components.labelDis();
        three = new Components.labelDis();
        del = new Components.labelDis();
        four = new Components.labelDis();
        five = new Components.labelDis();
        six = new Components.labelDis();
        dot = new Components.labelDis();
        seven = new Components.labelDis();
        eight = new Components.labelDis();
        nine = new Components.labelDis();
        zero = new Components.labelDis();
        btnDone = new Button.Button();
        switchButton1 = new SwitchButton.SwitchButton();
        inputDis = new Components.TextFieldCenter();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelPopUpTitle1.setLabelTitle("Discount");

        label1.setLabelName("%");

        label2.setLabelName("$");

        tenPercents.setBackground(new java.awt.Color(153, 204, 255));
        tenPercents.setForeground(new java.awt.Color(0, 0, 0));
        tenPercents.setLabelDiscount("10%");
        tenPercents.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tenPercentsMouseClicked(evt);
            }
        });

        twentyPercent.setBackground(new java.awt.Color(153, 204, 255));
        twentyPercent.setLabelDiscount("20%");
        twentyPercent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                twentyPercentMouseClicked(evt);
            }
        });

        thirtyPercent.setBackground(new java.awt.Color(153, 204, 255));
        thirtyPercent.setLabelDiscount("30%");
        thirtyPercent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                thirtyPercentMouseClicked(evt);
            }
        });

        fiftyPercent.setBackground(new java.awt.Color(153, 204, 255));
        fiftyPercent.setLabelDiscount("50%");
        fiftyPercent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fiftyPercentMouseClicked(evt);
            }
        });

        one.setLabelDiscount("1");
        one.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                oneMouseClicked(evt);
            }
        });

        two.setLabelDiscount("2");
        two.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                twoMouseClicked(evt);
            }
        });

        three.setLabelDiscount("3");
        three.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                threeMouseClicked(evt);
            }
        });

        del.setLabelDiscount("Del");
        del.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                delMouseClicked(evt);
            }
        });

        four.setLabelDiscount("4");
        four.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fourMouseClicked(evt);
            }
        });

        five.setLabelDiscount("5");
        five.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fiveMouseClicked(evt);
            }
        });

        six.setLabelDiscount("6");
        six.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sixMouseClicked(evt);
            }
        });

        dot.setLabelDiscount(".");
        dot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dotMouseClicked(evt);
            }
        });

        seven.setLabelDiscount("7");
        seven.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sevenMouseClicked(evt);
            }
        });

        eight.setLabelDiscount("8");
        eight.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eightMouseClicked(evt);
            }
        });

        nine.setLabelDiscount("9");
        nine.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nineMouseClicked(evt);
            }
        });

        zero.setLabelDiscount("0");
        zero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                zeroMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout calculatePanelLayout = new javax.swing.GroupLayout(calculatePanel);
        calculatePanel.setLayout(calculatePanelLayout);
        calculatePanelLayout.setHorizontalGroup(
            calculatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(calculatePanelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(calculatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tenPercents, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(one, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(four, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seven, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(calculatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(twentyPercent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(two, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(five, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(calculatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(thirtyPercent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(three, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(six, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(calculatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(zero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(del, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fiftyPercent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 2, Short.MAX_VALUE))
        );
        calculatePanelLayout.setVerticalGroup(
            calculatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(calculatePanelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(calculatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fiftyPercent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(thirtyPercent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(twentyPercent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tenPercents, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(calculatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(one, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(two, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(three, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(del, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(calculatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(four, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(five, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(six, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(calculatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(seven, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 2, Short.MAX_VALUE))
        );

        btnDone.setBackground(new java.awt.Color(51, 153, 255));
        btnDone.setButtonName("Done");
        btnDone.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDoneMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout discountPanelLayout = new javax.swing.GroupLayout(discountPanel);
        discountPanel.setLayout(discountPanelLayout);
        discountPanelLayout.setHorizontalGroup(
            discountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(discountPanelLayout.createSequentialGroup()
                .addGroup(discountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(discountPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(discountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(discountPanelLayout.createSequentialGroup()
                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(switchButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(206, 206, 206)
                                .addComponent(inputDis, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(calculatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(discountPanelLayout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(btnDone, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        discountPanelLayout.setVerticalGroup(
            discountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(discountPanelLayout.createSequentialGroup()
                .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(discountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(discountPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(discountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, discountPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(discountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, discountPanelLayout.createSequentialGroup()
                                .addComponent(switchButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, discountPanelLayout.createSequentialGroup()
                                .addComponent(inputDis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))))
                .addComponent(calculatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(discountPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(discountPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tenPercentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tenPercentsMouseClicked
        
        if(type == "percent"){
            String tenPercent = tenPercents.getLabelDiscount();
            percentType = "hasPercent";
            inputDiscount(tenPercent);
        }
        
    }//GEN-LAST:event_tenPercentsMouseClicked

    private void twentyPercentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_twentyPercentMouseClicked
        
        if(type == "percent"){
            String twentyPercents = twentyPercent.getLabelDiscount();
            percentType = "hasPercent";
            inputDiscount(twentyPercents);
        }
        
    }//GEN-LAST:event_twentyPercentMouseClicked

    private void thirtyPercentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thirtyPercentMouseClicked
        
        if(type == "percent"){
            String thirtyPercents = thirtyPercent.getLabelDiscount();
            percentType = "hasPercent";
            inputDiscount(thirtyPercents);
        }
        
    }//GEN-LAST:event_thirtyPercentMouseClicked

    private void fiftyPercentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fiftyPercentMouseClicked
       
        if(type == "percent"){
            String fiftyPercents = fiftyPercent.getLabelDiscount();
            percentType = "hasPercent";
            inputDiscount(fiftyPercents);
        }
        
    }//GEN-LAST:event_fiftyPercentMouseClicked

    private void oneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_oneMouseClicked
        String lbOne = one.getLabelDiscount();
        percentType = "noPercent";
        inputDiscount(lbOne);
    }//GEN-LAST:event_oneMouseClicked

    private void twoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_twoMouseClicked
        String lbTwo = two.getLabelDiscount();
        percentType = "noPercent";
        inputDiscount(lbTwo);
    }//GEN-LAST:event_twoMouseClicked

    private void threeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_threeMouseClicked
        String lbThree = three.getLabelDiscount();
        percentType = "noPercent";
        inputDiscount(lbThree);
    }//GEN-LAST:event_threeMouseClicked

    private void fourMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fourMouseClicked
        String lbFour = four.getLabelDiscount();
        percentType = "noPercent";
        inputDiscount(lbFour);
    }//GEN-LAST:event_fourMouseClicked

    private void fiveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fiveMouseClicked
        String lbFive = five.getLabelDiscount();
        percentType = "noPercent";
        inputDiscount(lbFive);
    }//GEN-LAST:event_fiveMouseClicked

    private void sixMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sixMouseClicked
        String lbSix = six.getLabelDiscount();
        percentType = "noPercent";
        inputDiscount(lbSix);
    }//GEN-LAST:event_sixMouseClicked

    private void sevenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sevenMouseClicked
        String lbSeven = seven.getLabelDiscount();
        percentType = "noPercent";
        inputDiscount(lbSeven);
    }//GEN-LAST:event_sevenMouseClicked

    private void eightMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eightMouseClicked
        String lbEight = eight.getLabelDiscount();
        percentType = "noPercent";
        inputDiscount(lbEight);
    }//GEN-LAST:event_eightMouseClicked

    private void nineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nineMouseClicked
        String lbNine = nine.getLabelDiscount();
        percentType = "noPercent";
        inputDiscount(lbNine);
    }//GEN-LAST:event_nineMouseClicked

    private void zeroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_zeroMouseClicked
        String lbZero = zero.getLabelDiscount();
        percentType = "noPercent";
        inputDiscount(lbZero);
    }//GEN-LAST:event_zeroMouseClicked

    private void dotMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dotMouseClicked
        String lbDot = dot.getLabelDiscount();
        percentType = "noPercent";
        inputDiscount(lbDot);
    }//GEN-LAST:event_dotMouseClicked

    private void delMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delMouseClicked
        if (!inputDis.getValueTextFieldCenter().isEmpty()) {
              String valueReceive = inputDis.getValueTextFieldCenter();
//              if(type == "percent"){
//                  valueReceive = valueReceive.substring(0, valueReceive.length() - 1);
//              }
//              else{
//                  valueReceive = valueReceive.substring(1, valueReceive.length() - 1);
//              }
              
              valueReceive = valueReceive.substring(0, valueReceive.length() - 1);
                  
              inputDis.setValueTextFieldCenter("");
              inputDiscount(valueReceive);
         }
    }//GEN-LAST:event_delMouseClicked

    private void btnDoneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDoneMouseClicked

        String discountValue = inputDis.getValueTextFieldCenter();
        
        if(discountValue != null){
            if(discountValue.contains("$")){
            discountValue = discountValue.replace("$", "");
            }

            if(discountValue.contains("%")){
                discountValue = discountValue.replace("%", "");
            }
        }
        
        
        if(discountValue == null){
            JOptionPane.showMessageDialog(null,"Please input discount value!");
            return;
        }
         
        Component[] listHold = detailItem.getComponents();
        double sumDiscount = 0;
        double sumTotalUsd = 0;
        
        for (int i = 0; i < listHold.length; i++) {
            var box = ((BoxItem) listHold[i]);
            
            if(type == "dollar"){
                if(JavaConstant.productId != 0 && box.getProductId() == JavaConstant.productId){
                    box.setDiscountAmount("$ " + discountValue);
                    box.setDiscountValue(Double.valueOf(discountValue));
                    box.setDiscountType(type);
                }
                
                if(JavaConstant.productId == 0){
                    box.setDiscountAmount("$ " + discountValue);
                    box.setDiscountValue(Double.valueOf(discountValue));
                    box.setDiscountType(type);
                }
                
            }else{
                double amount = Double.valueOf( box.getLabelAmountUsd().substring(1));
                double disvalue = Double.valueOf(discountValue);
                double discountAmount = (amount * disvalue)/100;
                
                if(JavaConstant.productId != 0 && box.getProductId() == JavaConstant.productId){
                    box.setDiscountAmount( dm.format(discountAmount));
                    box.setDiscountValue(Double.valueOf(discountValue));
                    box.setDiscountType(type);
                }
                
                if(JavaConstant.productId == 0){
                    box.setDiscountAmount( dm.format(discountAmount));
                    box.setDiscountValue(Double.valueOf(discountValue));
                    box.setDiscountType(type);
                }
                
            }
            
            sumDiscount += Double.valueOf(box.getDiscountAmount().substring(1));
            sumTotalUsd += Double.valueOf(box.getLabelAmountUsd().substring(1)) - sumDiscount;
            totalPanel.setLableDiscountUsd(dm.format(sumDiscount));
            totalPanel.setLableDiscountKhr(kh.format(sumDiscount * JavaConstant.exchangeRate));
            
            totalPanel.setLableTotalUsd(dm.format(sumTotalUsd));
            totalPanel.setLableTotalKhr(kh.format(sumTotalUsd * JavaConstant.exchangeRate));
        }
        
        detailItem.revalidate();
        detailItem.repaint();
        dispose();       
    }//GEN-LAST:event_btnDoneMouseClicked

    public JPanel getDetailItem() {
        return detailItem;
    }

    public void setDetailItem(JPanel detailItem) {
        this.detailItem = detailItem;
    }

    public BoxItem getBox() {
        return box;
    }

    public void setBox(BoxItem box) {
        this.box = box;
    }

    public SubtotalPanel getTotalPanel() {
        return totalPanel;
    }

    public void setTotalPanel(SubtotalPanel totalPanel) {
        this.totalPanel = totalPanel;
    }
    
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
            java.util.logging.Logger.getLogger(Discounting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Discounting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Discounting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Discounting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Discounting dialog = new Discounting(new javax.swing.JFrame(), true);
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
    private Button.Button btnDone;
    private javax.swing.JPanel calculatePanel;
    private Components.labelDis del;
    private javax.swing.JPanel discountPanel;
    private Components.labelDis dot;
    private Components.labelDis eight;
    private Components.labelDis fiftyPercent;
    private Components.labelDis five;
    private Components.labelDis four;
    private Components.TextFieldCenter inputDis;
    private Components.Label label1;
    private Components.Label label2;
    private Components.LabelPopUpTitle labelPopUpTitle1;
    private Components.labelDis nine;
    private Components.labelDis one;
    private Components.labelDis seven;
    private Components.labelDis six;
    private SwitchButton.SwitchButton switchButton1;
    private Components.labelDis tenPercents;
    private Components.labelDis thirtyPercent;
    private Components.labelDis three;
    private Components.labelDis twentyPercent;
    private Components.labelDis two;
    private Components.labelDis zero;
    // End of variables declaration//GEN-END:variables
}
