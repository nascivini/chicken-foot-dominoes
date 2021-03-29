/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import br.ufsc.inf.leobr.cliente.Jogada;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author vinicius
 */
public class Piece implements Jogada {
    private int sideOne;
    private int sideTwo;

    public Piece(int sideOne, int sideTwo) {
        this.sideOne = sideOne;
        this.sideTwo = sideTwo;
    }

    public int getSideOne() {
        return sideOne;
    }

    public void setSideOne(int sideOne) {
        this.sideOne = sideOne;
    }

    public int getSideTwo() {
        return sideTwo;
    }

    public void setSideTwo(int sideTwo) {
        this.sideTwo = sideTwo;
    }
    
    public ImageIcon initializeImage(){
        ImageIcon imageIcon = null;
        try{
            imageIcon = new ImageIcon(getClass().getResource("/resources/images/" + sideOne + "_" + sideTwo + ".png"));
            
        } catch(Exception e){
            try{
                imageIcon = new ImageIcon(getClass().getResource("/resources/images/" + sideTwo + "_" + sideOne + ".png"));
            }
            catch(Exception e2){
                
            }
        }
        return imageIcon;
    }

    public ImageIcon getImage() {
        return initializeImage();
    }
    
    @Override
    public boolean equals(Object object){
        if(object instanceof Piece){
            Piece p = (Piece) object;
            return (p.getSideOne() == sideOne && p.getSideTwo() == sideTwo) || (p.getSideOne() == sideTwo && p.getSideTwo() == sideOne);
        }
        return false;
    }
}
