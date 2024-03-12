/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BlogCode;

import Color.WindowColor;
import Fonts.WindowFonts;
import View.MainPage.MainPage;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author MOBILE-APP.02
 */
public class JavaExistScreen {
     public static void existFun(JFrame mainPage) {
          UIManager UI = new UIManager();
          UI.put("OptionPane.background", WindowColor.mediumGreen);
          UI.put("Panel.background", WindowColor.mediumGreen);
          UI.put("OptionPane.messageFont", WindowFonts.timeNewRomanBold14);
        
          
          mainPage.addWindowListener(new WindowAdapter() {
               public void windowClosing(WindowEvent evt) {
                    int resp = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",
                         "Exit?", JOptionPane.YES_NO_OPTION);
                     
                    if (resp == JOptionPane.YES_OPTION) {
                         mainPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    } else {
                         mainPage.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    }
               }
          });
     }
}
