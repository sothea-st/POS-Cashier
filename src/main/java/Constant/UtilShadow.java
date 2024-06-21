/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Constant;

import Components.Shadow.ShadowRenderer;
import Components.Shadow.ShadowType;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import lombok.Setter;

 
@Setter
public class UtilShadow {
   
     private static ShadowType shadowType;

     public static void createShadow(Graphics grphcs, int getWidth, int getHeight, Color getBackground) {

          final int SHADOW_SIZE = 1;
          final float SHADOW_OPACITY = 0.1f;
          final Color SHADOW_COLOR = Color.GRAY;

          Graphics2D g2 = (Graphics2D) grphcs;

          int size = SHADOW_SIZE * 2;
          int x = 0;
          int y = 0;
          int width = getWidth - size;
          int height = getHeight - size;
          if (null == shadowType) {
               //  Center
               x = SHADOW_SIZE;
               y = SHADOW_SIZE;
          } else {
               switch (shadowType) {
                    case TOP -> {
                         x = SHADOW_SIZE;
                         y = size;
                    }
                    case BOT -> {
                         x = SHADOW_SIZE;
                         y = 0;
                    }
                    case TOP_LEFT -> {
                         x = size;
                         y = size;
                    }
                    case TOP_RIGHT -> {
                         x = 0;
                         y = size;
                    }
                    case BOT_LEFT -> {
                         x = size;
                         y = 0;
                    }
                    case BOT_RIGHT -> {
                         x = 0;
                         y = 0;
                    }
                    default -> {
                         //  Center
                         x = SHADOW_SIZE;
                         y = SHADOW_SIZE;
                    }
               }
          }

          BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
          Graphics2D g = img.createGraphics();

          g.setColor(getBackground);

          g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

          g.fillRoundRect(
               0, 0, width, height, 10, 10);

          //  Create Shadow
          ShadowRenderer render = new ShadowRenderer(SHADOW_SIZE, SHADOW_OPACITY, SHADOW_COLOR);

          g2.drawImage(render.createShadow(img), 0, 0, null);
          g2.drawImage(img, x, y,
               null);
     }

     public static void createShadowSearch(Graphics grphcs, int getWidth, int getHeight, Color getBackground) {
          int SHADOW_SIZE = 3;
          float SHADOW_OPACITY = 0.8f;
          Color SHADOW_COLOR = Color.GRAY;

          Graphics2D g2 = (Graphics2D) grphcs;
          int size = SHADOW_SIZE * 2;
          int x = 0;
          int y = 0;
          int width = getWidth - size;
          int height = getHeight - size;
          if (null == shadowType) {
               //  Center
               x = SHADOW_SIZE;
               y = SHADOW_SIZE;
          } else {
               switch (shadowType) {
                    case TOP -> {
                         x = SHADOW_SIZE;
                         y = size;
                    }
                    case BOT -> {
                         x = SHADOW_SIZE;
                         y = 0;
                    }
                    case TOP_LEFT -> {
                         x = size;
                         y = size;
                    }
                    case TOP_RIGHT -> {
                         x = 0;
                         y = size;
                    }
                    case BOT_LEFT -> {
                         x = size;
                         y = 0;
                    }
                    case BOT_RIGHT -> {
                         x = 0;
                         y = 0;
                    }
                    default -> {
                         //  Center
                         x = SHADOW_SIZE;
                         y = SHADOW_SIZE;
                    }
               }
          }
          BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
          Graphics2D g = img.createGraphics();

          g.setColor(getBackground);

          g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

          g.fillRoundRect(
               0, 0, width, height, 40, 40);

          //  Create Shadow
          ShadowRenderer render = new ShadowRenderer(SHADOW_SIZE, SHADOW_OPACITY, SHADOW_COLOR);

          g2.drawImage(render.createShadow(img), 0, 0, null);
          g2.drawImage(img, x, y,
               null);
     }
}
