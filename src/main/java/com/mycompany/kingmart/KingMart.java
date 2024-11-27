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

          //JavaConstant.setLookAndFeel();
          MainPage mainPage = new MainPage();
          mainPage.setVisible(true);

//            ImportFile i = new ImportFile(new JFrame(), true);
//            i.setVisible(true);
     }

}
