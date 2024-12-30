 
package com.mycompany.kingmart;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

 
public class KhmerTextTest {
     public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Khmer Text Test");
            JLabel label = new JLabel("ទឹកគ្រាប់ជីរស់ជាតិក្រូច​ ៣០០មល");
            frame.add(label);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
