/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.MainPage;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author MOBILE-APP.02
 */
public class CircleLoadingCursorExample {
         public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Circle Loading Cursor Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            JButton button = new JButton("Click Me");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setCircleLoadingCursor(frame);
                    // Simulate a time-consuming task
                    new Thread(() -> {
                        try {
                            Thread.sleep(3000); // Simulating a task that takes 3 seconds
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        // After the task is done, restore the default cursor
                        restoreDefaultCursor(frame);
                    }).start();
                }
            });
            frame.add(button, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }

    private static void setCircleLoadingCursor(Component component) {
        component.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    }

    private static void restoreDefaultCursor(Component component) {
        component.setCursor(Cursor.getDefaultCursor());
    }
}
