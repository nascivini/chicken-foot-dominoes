/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author vinicius
 */
public class ImageUtils {
  
  private static final String BASE_LOCATION = "images/";
  private static final String EXTENSION = ".jpg";

  public ImageIcon getMiddleImage(int w, int h){
    ImageIcon icon = null;
    try{
      icon = new ImageIcon(getClass().getClassLoader().getResource(BASE_LOCATION + "blackline" + EXTENSION));
      icon.setImage(getScaledImage(icon.getImage(), w, h));
    }
    catch(Exception e){
      System.out.println("Erro ao carregar imagens");
      e.printStackTrace();
    }
    return icon;
  }

  public ImageIcon getImage(int pecaDesejada, int w, int h){
    ImageIcon icon = null;
    try{
      icon = new ImageIcon(getClass().getClassLoader().getResource(BASE_LOCATION + pecaDesejada + EXTENSION));
      icon.setImage(getScaledImage(icon.getImage(), w, h));
    }
    catch(Exception e){
      System.out.println("Erro ao carregar imagens");
      e.printStackTrace();
    }
    return icon;
  }

  private Image getScaledImage(Image srcImg, int w, int h){
    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2 = resizedImg.createGraphics();

    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g2.drawImage(srcImg, 0, 0, w, h, null);
    g2.dispose();

    return resizedImg;
  }
}
