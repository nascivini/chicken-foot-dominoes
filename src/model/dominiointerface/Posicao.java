package model.dominiointerface;

import model.dominioproblema.Peca;

import javax.swing.*;

public abstract class Posicao {
  private Peca peca;
  private JLabel jLabel;

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

  public boolean validarInsercao(Peca peca){
    boolean temEspacosDisponiveis = temEspacosDisponiveis();
    boolean pontaConfere =
      peca != null && (peca.possuiSimilar(peca.getLadoA()) ||  peca.possuiSimilar(peca.getLadoB()));

    return pontaConfere && temEspacosDisponiveis;
  }

  public abstract boolean temEspacosDisponiveis();

}
