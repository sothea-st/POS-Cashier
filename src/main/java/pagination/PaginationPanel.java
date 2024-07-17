package pagination;

import Constant.JavaConstant;
import Event.ButtonEvent;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class PaginationPanel extends javax.swing.JPanel {

     private int totalPage = 16;
     private String page = "pageOne";

     public PaginationPanel() {
          initComponents();
          JavaConstant.setPointer(previousPage);
          JavaConstant.setPointer(pageOne);
          JavaConstant.setPointer(pageTwo);
          JavaConstant.setPointer(pageThree);
          JavaConstant.setPointer(pageFour);
          JavaConstant.setPointer(pageFive);
          JavaConstant.setPointer(pageNext);

          setBorder(pageOne);
     }

     private void setBorder(JLabel label) {
          Border border = BorderFactory.createLineBorder(Color.BLACK);
          label.setBorder(border);
     }

     private void removeBorder(JLabel jlabel) {
          jlabel.setBorder(null);
     }

     private void setUIBorder(
          JLabel setBorder,
          JLabel lb1,
          JLabel lb2,
          JLabel lb3,
          JLabel lb4,
          JLabel lb5,
          JLabel lb6
     ) {
          removeBorder(lb1);
          removeBorder(lb2);
          removeBorder(lb3);
          removeBorder(lb4);
          removeBorder(lb5);
          removeBorder(lb6);
          setBorder(setBorder);
     }

     private void setValueText(int one, int two, int three, int four, int five) {
          pageOne.setText(String.valueOf(one));
          pageTwo.setText(String.valueOf(two));
          pageThree.setText(String.valueOf(three));
          pageFour.setText(String.valueOf(four));
          pageFive.setText(String.valueOf(five));
     }

     public void initEvent(ButtonEvent event) {
          previousPage.addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {
                    if (page.equals("pageFour")) {
                         setUIBorder(pageThree, previousPage, pageOne, pageNext, pageTwo, pageFour, pageFive);
                         page = "pageThree";
                    } else if (page.equals("pageThree")) {
                         setUIBorder(pageTwo, previousPage, pageOne, pageNext, pageThree, pageFour, pageFive);
                         page = "pageTwo";
                    } else if (page.equals("pageTwo")) {
                         setUIBorder(pageOne, previousPage, pageTwo, pageNext, pageThree, pageFour, pageFive);
                         page = "pageOne";
                    }

                    System.out.println("page value : " + page);

                    String value = previousPage.getText();
                    event.onMouseClick(value);
               }

               @Override
               public void mousePressed(MouseEvent e) {
               }

               @Override
               public void mouseReleased(MouseEvent e) {
               }

               @Override
               public void mouseEntered(MouseEvent e) {
               }

               @Override
               public void mouseExited(MouseEvent e) {
               }
          });

          pageOne.addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {
                    funcPageOne(event);
               }

               @Override
               public void mousePressed(MouseEvent e) {
               }

               @Override
               public void mouseReleased(MouseEvent e) {
               }

               @Override
               public void mouseEntered(MouseEvent e) {
               }

               @Override
               public void mouseExited(MouseEvent e) {
               }
          });

          pageTwo.addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {
                    page = "pageTwo";
                    setUIBorder(
                         pageTwo,
                         previousPage,
                         pageOne,
                         pageThree,
                         pageFour,
                         pageFive,
                         pageNext
                    );
                    String value = pageTwo.getText();
                    event.onMouseClick(value);
               }

               @Override
               public void mousePressed(MouseEvent e) {
               }

               @Override
               public void mouseReleased(MouseEvent e) {
               }

               @Override
               public void mouseEntered(MouseEvent e) {
               }

               @Override
               public void mouseExited(MouseEvent e) {
               }
          });

          pageThree.addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {
                    page = "pageThree";
                    setUIBorder(
                         pageThree,
                         previousPage,
                         pageOne,
                         pageTwo,
                         pageFour,
                         pageFive,
                         pageNext
                    );
                    String value = pageThree.getText();
                    event.onMouseClick(value);
               }

               @Override
               public void mousePressed(MouseEvent e) {
               }

               @Override
               public void mouseReleased(MouseEvent e) {
               }

               @Override
               public void mouseEntered(MouseEvent e) {
               }

               @Override
               public void mouseExited(MouseEvent e) {
               }
          });

          pageFour.addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {
                    page = "pageFour";
                    setUIBorder(
                         pageFour,
                         previousPage,
                         pageOne,
                         pageTwo,
                         pageThree,
                         pageFive,
                         pageNext
                    );
                    String value = pageFour.getText();
                    event.onMouseClick(value);
               }

               @Override
               public void mousePressed(MouseEvent e) {
               }

               @Override
               public void mouseReleased(MouseEvent e) {
               }

               @Override
               public void mouseEntered(MouseEvent e) {
               }

               @Override
               public void mouseExited(MouseEvent e) {
               }
          });

          pageFive.addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {
                    funcPageFive(event);
               }

               @Override
               public void mousePressed(MouseEvent e) {
               }

               @Override
               public void mouseReleased(MouseEvent e) {
               }

               @Override
               public void mouseEntered(MouseEvent e) {
               }

               @Override
               public void mouseExited(MouseEvent e) {
               }
          });

          pageNext.addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {

                    if (page.equals("pageOne")) {
                         setUIBorder(pageTwo, previousPage, pageOne, pageNext, pageThree, pageFour, pageFive);
                         page = "pageTwo";
                    } else if (page.equals("pageTwo")) {
                         setUIBorder(pageThree, previousPage, pageOne, pageNext, pageTwo, pageFour, pageFive);
                         page = "pageThree";
                    } else if (page.equals("pageThree")) {
                         setUIBorder(pageFour, previousPage, pageOne, pageNext, pageTwo, pageThree, pageFive);
                         page = "pageFour";
                    } else if (page.equals("pageFour")) {
                         funcPageFive(event);
                         page = "pageThree";
                    }

                    System.out.println("page value : " + page);
                    String value = pageNext.getText();
                    event.onMouseClick(value);
               }

               @Override
               public void mousePressed(MouseEvent e) {
               }

               @Override
               public void mouseReleased(MouseEvent e) {
               }

               @Override
               public void mouseEntered(MouseEvent e) {
               }

               @Override
               public void mouseExited(MouseEvent e) {
               }
          });
     }

     void funcPageFive(ButtonEvent event) {
          page = "pageFive";
          String value = pageFive.getText();
          Integer pageFiveNewValue = Integer.valueOf(value);

          if (pageFiveNewValue <= totalPage) {

               if (pageFiveNewValue == totalPage) {
                    setUIBorder(
                         pageFive,
                         previousPage,
                         pageOne,
                         pageTwo,
                         pageThree,
                         pageFour,
                         pageNext
                    );
               } else if ((pageFiveNewValue + 1) == totalPage) {
                    setUIBorder(
                         pageFive,
                         previousPage,
                         pageOne,
                         pageTwo,
                         pageThree,
                         pageFour,
                         pageNext
                    );
                    pageOne.setText(String.valueOf(totalPage - 4));
                    pageTwo.setText(String.valueOf(totalPage - 3));
                    pageThree.setText(String.valueOf(totalPage - 2));
                    pageFour.setText(String.valueOf(totalPage - 1));
                    pageFive.setText(String.valueOf(totalPage));
               } else {
                    setUIBorder(
                         pageThree,
                         previousPage,
                         pageOne,
                         pageTwo,
                         pageFive,
                         pageFour,
                         pageNext
                    );
                    pageOne.setText(String.valueOf(pageFiveNewValue - 2));
                    pageTwo.setText(String.valueOf(pageFiveNewValue - 1));
                    pageThree.setText(String.valueOf(pageFive.getText()));
                    pageFour.setText(String.valueOf(pageFiveNewValue + 1));
                    pageFive.setText(String.valueOf(pageFiveNewValue + 2));
               }
               event.onMouseClick(value);
          }
     }

     void funcPageOne(ButtonEvent event) {
          page = "pageOne";
          setUIBorder(
               pageOne,
               previousPage,
               pageTwo,
               pageThree,
               pageFour,
               pageFive,
               pageNext
          );
          String value = pageOne.getText();
          int pageOneValue = Integer.parseInt(value);
          if (pageOneValue != 1) {
               if (pageOneValue > 0) {
                    switch (pageOneValue) {
                         case 2 -> {
                              setUIBorder(
                                   pageTwo,
                                   previousPage,
                                   pageThree,
                                   pageOne,
                                   pageFour,
                                   pageFive,
                                   pageNext
                              );
                              setValueText(1, 2, 3, 4, 5);
                         }
                         case 3 -> {
                              setUIBorder(
                                   pageThree,
                                   previousPage,
                                   pageTwo,
                                   pageOne,
                                   pageFour,
                                   pageFive,
                                   pageNext
                              );
                              setValueText(1, 2, 3, 4, 5);
                         }
                         case 4 -> {
                              setUIBorder(
                                   pageFour,
                                   previousPage,
                                   pageTwo,
                                   pageOne,
                                   pageThree,
                                   pageFive,
                                   pageNext
                              );
                              setValueText(2, 3, 4, 5, 6);
                         }
                         default -> {
                              int minusOne = pageOneValue - 1;
                              int minusTwo = pageOneValue - 2;
                              int plusOne = pageOneValue + 1;
                              int plusTwo = pageOneValue + 2;
                              setUIBorder(
                                   pageThree,
                                   previousPage,
                                   pageTwo,
                                   pageOne,
                                   pageFour,
                                   pageFive,
                                   pageNext
                              );
                              setValueText(minusTwo, minusOne, pageOneValue, plusOne, plusTwo);
                         }
                    }
                    event.onMouseClick(value);
               }
          }
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          jPanel1 = new javax.swing.JPanel();
          pageFour = new javax.swing.JLabel();
          previousPage = new javax.swing.JLabel();
          pageOne = new javax.swing.JLabel();
          pageTwo = new javax.swing.JLabel();
          pageThree = new javax.swing.JLabel();
          pageNext = new javax.swing.JLabel();
          pageFive = new javax.swing.JLabel();

          jPanel1.setPreferredSize(new java.awt.Dimension(50, 50));

          pageFour.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          pageFour.setText("4");

          previousPage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          previousPage.setText("<<");

          pageOne.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          pageOne.setText("1");

          pageTwo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          pageTwo.setText("2");

          pageThree.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          pageThree.setText("3");

          pageNext.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          pageNext.setText(">>");

          pageFive.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          pageFive.setText("5");

          javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
          jPanel1.setLayout(jPanel1Layout);
          jPanel1Layout.setHorizontalGroup(
               jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(previousPage, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(pageOne, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(pageTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(pageThree, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(pageFour, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(pageFive, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(pageNext, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          );
          jPanel1Layout.setVerticalGroup(
               jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(pageNext, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(previousPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                              .addGap(0, 0, Short.MAX_VALUE)
                              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                   .addComponent(pageThree, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(pageFour, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                         .addComponent(pageFive, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(pageOne, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(pageTwo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
          );
     }// </editor-fold>//GEN-END:initComponents


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JPanel jPanel1;
     private javax.swing.JLabel pageFive;
     private javax.swing.JLabel pageFour;
     private javax.swing.JLabel pageNext;
     private javax.swing.JLabel pageOne;
     private javax.swing.JLabel pageThree;
     private javax.swing.JLabel pageTwo;
     private javax.swing.JLabel previousPage;
     // End of variables declaration//GEN-END:variables
}
