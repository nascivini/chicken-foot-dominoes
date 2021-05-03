package model.dominiointerface;

import model.dominioproblema.Jogador;
import model.dominioproblema.Peca;

import java.util.List;

public class Tabuleiro {
  private Jogador destaInstancia;
  private Jogador daOutraInstancia;
  private List<Peca> pecasGalinheiro;

  private PrimeiraPosicao primeiraPosicao;

  public List<Peca> getPecasGalinheiro() {
    return pecasGalinheiro;
  }

  public void setPecasGalinheiro(List<Peca> pecasGalinheiro) {
    this.pecasGalinheiro = pecasGalinheiro;
  }

  public void adicionarJogador(Jogador jogador){
    if(jogador.isEstaNestaInstancia()){
      destaInstancia = jogador;
    }
    else{
      daOutraInstancia = jogador;
    }
  }

  public Jogador getJogadorDestaInstancia(){
    return destaInstancia;
  }

  public Jogador getJogadorDaOutraInstancia(){
    return daOutraInstancia;
  }

  public boolean isPrimeiraPeca(){
    return primeiraPosicao == null;
  }
}
