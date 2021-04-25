package model.dominiointerface;

import model.dominioproblema.Peca;

public class PrimeiraPosicao extends Posicao {
  private Peca pecaCima;
  private Peca pecaBaixo;
  private Peca pecaEsquerda;
  private Peca pecaDireita;

  @Override
  public boolean temEspacosDisponiveis(){
    return pecaCima == null || pecaBaixo == null || pecaEsquerda == null || pecaDireita == null;
  }
}
