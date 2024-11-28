package com.mycompany.kingmart;

 
import Constant.JavaConstant;
import View.MainPage.MainPage;
import java.io.FileNotFoundException;

public class KingMart {

     public static void main(String[] args) throws FileNotFoundException {

          JavaConstant.setLookAndFeel();
          
          MainPage mainPage = new MainPage();
          mainPage.setVisible(true);

//            ImportFile i = new ImportFile(new JFrame(), true);
//            i.setVisible(true);
     }

}
