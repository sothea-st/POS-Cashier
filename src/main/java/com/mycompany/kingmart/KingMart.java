package com.mycompany.kingmart;

import Constant.JavaConstant;
import Fonts.WindowFonts;

import View.MainPage.MainPage;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import java.io.FileNotFoundException;

import javax.swing.UIManager;

public class KingMart {

     public static void main(String[] args) throws FileNotFoundException {

//          JavaConstant.setLookAndFeel();
//          System.out.println("Hello World!");
          // change view teplate
          FlatRobotoFont.install();
          FlatLaf.registerCustomDefaultsSource("theme");
          //UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
          UIManager.put("defaultFont", WindowFonts.timeNewRoman14);

          // Set custom outline color for JTextField and JComboBox
          // UIManager.put("TextField.focusedBorderColor", JavaColor.primary); // Focused border color for JTextField
//        UIManager.put("ComboBox.focusedBorderColor", Color.RED);  // Focused border color for JComboBox
          // Set outline color globally
          //UIManager.put("Component.focusedBorderColor", JavaColor.primary); // Global focus border color
          FlatMacDarkLaf.setup();
          MainPage mainPage = new MainPage();
          mainPage.setVisible(true);

//            ImportFile i = new ImportFile(new JFrame(), true);
//            i.setVisible(true);
     }

}
