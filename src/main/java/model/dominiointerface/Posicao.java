package model.dominiointerface;

import model.dominioproblema.Peca;

import javax.swing.*;

public class Posicao {
  private Peca peca;
  private JLabel jLabel;
  private int positionX;
  private int positionY;

  public Peca getPeca() {
    return peca;
  }

  public void setPeca(Peca peca) {
    this.peca = peca;
  }

  public JLabel getjLabel() {
    return jLabel;
  }

  public void setjLabel(JLabel jLabel) {
    this.jLabel = jLabel;
  }

  public int getPositionX() {
    return positionX;
  }

  public void setPositionX(int positionX) {
    this.positionX = positionX;
  }

  public int getPositionY() {
    return positionY;
  }

  public void setPositionY(int positionY) {
    this.positionY = positionY;
  }

  public boolean validarInsercao(Peca peca){
    boolean temEspacosDisponiveis = disponivel();
    boolean pontaConfere =
      peca != null && (peca.possuiSimilar(peca.getLadoA()) ||  peca.possuiSimilar(peca.getLadoB()));

    return pontaConfere && temEspacosDisponiveis;
  }

  public boolean disponivel(){
    return peca == null;
  }

}
