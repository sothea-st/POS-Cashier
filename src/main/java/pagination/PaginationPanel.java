package pagination;

import Color.WindowColor;
import Constant.JavaConstant;
import Event.ButtonEvent;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class PaginationPanel extends javax.swing.JPanel {

     private int totalPage = 0;
     private String page = "pageOne";
     private int pageNumber = 1; // alway start from 1
//     private int pageSize = 0;

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
          checkPageNumber();
     }

     public int getTotalPage() {
          return totalPage;
     }

     public void setTotalPage(long totalPage, int pageSize) {
          double result = (double) totalPage / pageSize;
          double roundedResult = Math.ceil(result);
          this.totalPage = (int) roundedResult;

          if (pageNumber != 1) {
               int _calPageSize = pageNumber * pageSize;
               showPageNumber.setText(String.valueOf(((pageNumber - 1) * pageSize + 1)));
               if (_calPageSize > totalPage) {
                    showPageSize.setText(String.valueOf(totalPage));
               } else {
                    showPageSize.setText(String.valueOf(_calPageSize));
               }
               showTotal.setText(String.valueOf(totalPage));
          } else {
               showPageNumber.setText(String.valueOf(1));
               showTotal.setText(String.valueOf(totalPage));
               if (pageSize > totalPage) {
                    showPageSize.setText(String.valueOf(totalPage));
               } else {
                    showPageSize.setText(String.valueOf(pageSize));
               }
          }

 
          checkPageNumber();
     }

     private void checkPageNumber() {
          switch (totalPage) {
               case 1 -> {
                    setVisiblePage(false, false, false, false);
                    break;
               }
               case 2 -> {
                    setVisiblePage(true, false, false, false);
                    break;
               }

               case 3 -> {
                    setVisiblePage(true, true, false, false);
                    break;
               }

               case 4 -> {
                    setVisiblePage(true, true, true, false);
                    break;
               }

               case 5 -> {
                    setVisiblePage(true, true, true, true);
                    break;
               }
               default -> {
                    setVisiblePage(true, true, true, true);
               }
          }
     }

//     public int getPageSize() {
//          return pageSize;
//     }
//
//     public void setPageSize(int pageSize) {
//          this.pageSize = pageSize;
//     }

     private void setVisiblePage(boolean two, boolean three, boolean four, boolean five) {
          pageTwo.setForeground(two ? WindowColor.black : WindowColor.white);
          pageThree.setForeground(three ? WindowColor.black : WindowColor.white);
          pageFour.setForeground(four ? WindowColor.black : WindowColor.white);
          pageFive.setForeground(five ? WindowColor.black : WindowColor.white);
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

     public void resetPage() {
          page = "pageOne";
          pageNumber = 1;
          totalPage = 1;
          setUIBorder(pageOne, pageFour, previousPage, pageNext, pageTwo, pageThree, pageFive);
          setValueText(1, 2, 3, 4, 5);
          checkPageNumber();
     }

     ;
     
       

     public void initEvent(ButtonEvent event) {

          previousPage.addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {

                    switch (page) {
                         case "pageFive" -> {
                              setUIBorder(pageFour, previousPage, pageOne, pageNext, pageTwo, pageFour, pageFive);
                              page = "pageFour";
                         }
                         case "pageFour" -> {
                              setUIBorder(pageThree, previousPage, pageOne, pageNext, pageTwo, pageFour, pageFive);
                              page = "pageThree";
                         }
                         case "pageThree" -> {
                              setUIBorder(pageTwo, previousPage, pageOne, pageNext, pageThree, pageFour, pageFive);
                              page = "pageTwo";
                         }
                         case "pageTwo" -> {
                              setUIBorder(pageOne, previousPage, pageTwo, pageNext, pageThree, pageFour, pageFive);
                              funcPageOne(event);
                         }
                         default -> {
                         }
                    }

                    if (pageNumber == 1) {
                         event.onMouseClick(String.valueOf(pageNumber));
                         return;
                    }
                    pageNumber--;
                    event.onMouseClick(String.valueOf(pageNumber));
               }

               @Override
               public void mousePressed(MouseEvent e) {
                    previousPage.setForeground(WindowColor.gray);
               }

               @Override
               public void mouseReleased(MouseEvent e) {
                    previousPage.setForeground(WindowColor.black);
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
                    if (totalPage == 1) {
                         return;
                    }
                    switch (page) {
                         case "pageOne" -> {
                              setUIBorder(pageTwo, previousPage, pageOne, pageNext, pageThree, pageFour, pageFive);
                              page = "pageTwo";
                         }
                         case "pageTwo" -> {
                              setUIBorder(pageThree, previousPage, pageOne, pageNext, pageTwo, pageFour, pageFive);
                              page = "pageThree";
                              if (totalPage == 2) {
                                   setUIBorder(pageTwo, previousPage, pageOne, pageNext, pageThree, pageFour, pageFive);
                                   page = "pageTwo";
                              }
                         }
                         case "pageThree" -> {
                              setUIBorder(pageFour, previousPage, pageOne, pageNext, pageTwo, pageThree, pageFive);
                              page = "pageFour";
                              if (totalPage == 3) {
                                   setUIBorder(pageThree, previousPage, pageOne, pageNext, pageFour, pageTwo, pageFive);
                                   page = "pageThree";
                              }
                         }
                         case "pageFour" -> {
                              funcPageFive(event);
                              if (totalPage == 4) {
                                   setUIBorder(pageFour, previousPage, pageOne, pageNext, pageThree, pageTwo, pageFive);
                                   page = "pageFour";
                              }
                         }

                         default -> {
                         }
                    }

                    if (pageNumber == totalPage) {
                         return;
                    }
                    pageNumber++;
                    event.onMouseClick(String.valueOf(pageNumber));
               }

               @Override
               public void mousePressed(MouseEvent e) {
                    pageNext.setForeground(WindowColor.gray);
               }

               @Override
               public void mouseReleased(MouseEvent e) {
                    pageNext.setForeground(WindowColor.black);
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
                    pageNumber = Integer.parseInt(pageOne.getText());
                    funcPageOne(event);
               }

               @Override
               public void mousePressed(MouseEvent e) {
                    pageOne.setForeground(WindowColor.gray);
               }

               @Override
               public void mouseReleased(MouseEvent e) {
                    pageOne.setForeground(WindowColor.black);
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
                    if (totalPage < 2) {
                         return;
                    }
                    pageNumber = Integer.parseInt(pageTwo.getText());
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
                    pageTwo.setForeground(WindowColor.gray);
               }

               @Override
               public void mouseReleased(MouseEvent e) {
                    pageTwo.setForeground(WindowColor.black);
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
                    if (totalPage < 3) {
                         return;
                    }
                    pageNumber = Integer.parseInt(pageThree.getText());
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
                    pageThree.setForeground(WindowColor.gray);
               }

               @Override
               public void mouseReleased(MouseEvent e) {
                    pageThree.setForeground(WindowColor.black);
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
                    if (totalPage < 4) {
                         return;
                    }
                    pageNumber = Integer.parseInt(pageFour.getText());
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
                    pageFour.setForeground(WindowColor.gray);
               }

               @Override
               public void mouseReleased(MouseEvent e) {
                    pageFour.setForeground(WindowColor.black);
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
                    if (totalPage < 5) {
                         return;
                    }
                    pageNumber = Integer.parseInt(pageFive.getText());
                    funcPageFive(event);
               }

               @Override
               public void mousePressed(MouseEvent e) {
                    pageFive.setForeground(WindowColor.gray);
               }

               @Override
               public void mouseReleased(MouseEvent e) {
                    pageFive.setForeground(WindowColor.black);
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
                    page = "pageFive";
                    pageNumber = Integer.parseInt(pageFive.getText());
               } else if (pageFiveNewValue == totalPage) {
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
                    page = "pageFive";
                    pageNumber = Integer.parseInt(pageFive.getText());
               } else if ((pageFiveNewValue + 1) == totalPage) {
                    setUIBorder(
                         pageFour,
                         previousPage,
                         pageOne,
                         pageTwo,
                         pageThree,
                         pageFive,
                         pageNext
                    );
                    pageOne.setText(String.valueOf(totalPage - 4));
                    pageTwo.setText(String.valueOf(totalPage - 3));
                    pageThree.setText(String.valueOf(totalPage - 2));
                    pageFour.setText(String.valueOf(totalPage - 1));
                    pageFive.setText(String.valueOf(totalPage));
                    page = "pageFour";
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
                    page = "pageThree";
               }
               event.onMouseClick(value);
          }
     }

     void funcPageOne(ButtonEvent event) {
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
          if (pageOneValue == 1) {
               pageNumber = 1;
               page = "pageOne";
               event.onMouseClick(value);
          }
          if (pageOneValue != 1) {
               if (pageOneValue > 0) {
                    switch (pageOneValue) {
                         case 2 -> {
                              page = "pageTwo";
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
                              page = "pageThree";
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
                         default -> {
                              page = "pageThree";
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

          panel = new javax.swing.JPanel();
          pageFour = new javax.swing.JLabel();
          previousPage = new javax.swing.JLabel();
          pageOne = new javax.swing.JLabel();
          pageTwo = new javax.swing.JLabel();
          pageThree = new javax.swing.JLabel();
          pageNext = new javax.swing.JLabel();
          pageFive = new javax.swing.JLabel();
          jLabel1 = new javax.swing.JLabel();
          showPageNumber = new javax.swing.JLabel();
          jLabel3 = new javax.swing.JLabel();
          showPageSize = new javax.swing.JLabel();
          jLabel5 = new javax.swing.JLabel();
          showTotal = new javax.swing.JLabel();

          panel.setPreferredSize(new java.awt.Dimension(50, 50));

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

          jLabel1.setText("Showing ");

          showPageNumber.setText("1");

          jLabel3.setText("to");

          showPageSize.setText("10");

          jLabel5.setText("of");

          showTotal.setText("100");

          javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
          panel.setLayout(panelLayout);
          panelLayout.setHorizontalGroup(
               panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelLayout.createSequentialGroup()
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
                    .addGap(18, 18, 18)
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(showPageNumber)
                    .addGap(6, 6, 6)
                    .addComponent(jLabel3)
                    .addGap(6, 6, 6)
                    .addComponent(showPageSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(6, 6, 6)
                    .addComponent(jLabel5)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(showTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
          );
          panelLayout.setVerticalGroup(
               panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelLayout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(previousPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(pageOne, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(pageTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                              .addGap(0, 0, Short.MAX_VALUE)
                              .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(pageThree, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(pageFour, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(pageFive, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(pageNext, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1)
                                        .addComponent(showPageNumber)
                                        .addComponent(jLabel3)
                                        .addComponent(showPageSize)
                                        .addComponent(jLabel5)
                                        .addComponent(showTotal))))))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                    .addGap(0, 0, 0))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
          );
     }// </editor-fold>//GEN-END:initComponents


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JLabel jLabel1;
     private javax.swing.JLabel jLabel3;
     private javax.swing.JLabel jLabel5;
     private javax.swing.JLabel pageFive;
     private javax.swing.JLabel pageFour;
     private javax.swing.JLabel pageNext;
     private javax.swing.JLabel pageOne;
     private javax.swing.JLabel pageThree;
     private javax.swing.JLabel pageTwo;
     private javax.swing.JPanel panel;
     private javax.swing.JLabel previousPage;
     private javax.swing.JLabel showPageNumber;
     private javax.swing.JLabel showPageSize;
     private javax.swing.JLabel showTotal;
     // End of variables declaration//GEN-END:variables
}
