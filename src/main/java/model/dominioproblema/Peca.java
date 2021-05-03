package model.dominioproblema;

import br.ufsc.inf.leobr.cliente.Jogada;

import javax.swing.*;
import java.io.Serializable;

public class Peca implements Serializable, Jogada {
  private int ladoA;
  private int ladoB;
  private JLabel jLabel;
  public String imagePath;

  public Peca(int ladoA, int ladoB, String imagePath){
    this.ladoA = ladoA;
    this.ladoB = ladoB;
    this.imagePath = imagePath;
  }

  public boolean possuiSimilar(int paraComparar){
    return paraComparar == ladoA || paraComparar == ladoB;
  }

  public int getLadoA() {
    return ladoA;
  }

  public void setLadoA(int ladoA) {
    this.ladoA = ladoA;
  }

  public int getLadoB() {
    return ladoB;
  }

  public void setLadoB(int ladoB) {
    this.ladoB = ladoB;
  }

  public JLabel getjLabel() {
    return jLabel;
  }

  public void setjLabel(JLabel jLabel) {
    this.jLabel = jLabel;
  }

  @Override
  public boolean equals(Object obj){
    if(obj instanceof Peca){
      return ((Peca) obj).ladoA == ladoA && ((Peca) obj).ladoB == ladoB;
    }
    return false;
  }
}
