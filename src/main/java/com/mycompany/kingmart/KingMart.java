package com.mycompany.kingmart;

import Products.ImportFile;
import View.MainPage.MainPage;
import java.awt.Desktop;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author FRONT-END.06
 */
public class KingMart {

     public static void main(String[] args) throws FileNotFoundException {
//          MainPage mainPage = new MainPage();
//          mainPage.setVisible(true);

            ImportFile i = new ImportFile(new JFrame(), true);
            i.setVisible(true);
     }

}

