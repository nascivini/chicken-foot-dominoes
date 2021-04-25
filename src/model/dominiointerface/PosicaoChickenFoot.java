package model.dominiointerface;

import model.dominioproblema.Peca;

public class PosicaoChickenFoot extends Posicao{

  private Peca pecaDiagonalCima;
  private Peca pecaCentral;
  private Peca pecaDiagonalBaixo;

  @Override
  public boolean temEspacosDisponiveis() {
    return pecaDiagonalCima == null || pecaDiagonalBaixo == null || pecaCentral == null;
  }
}
