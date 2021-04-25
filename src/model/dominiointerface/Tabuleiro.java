package model.dominiointerface;

import model.dominioproblema.Peca;
import model.dominioproblema.Jogador;

import java.util.List;

public class Tabuleiro {
  private Jogador jogadorA;
  private Jogador jogadorB;
  private List<Peca> pecasGalinheiro;

  public Jogador getJogadorA() {
    return jogadorA;
  }

  public void setJogadorA(Jogador jogadorA) {
    this.jogadorA = jogadorA;
  }

  public Jogador getJogadorB() {
    return jogadorB;
  }

  public void setJogadorB(Jogador jogadorB) {
    this.jogadorB = jogadorB;
  }

  public List<Peca> getPecasGalinheiro() {
    return pecasGalinheiro;
  }

  public void setPecasGalinheiro(List<Peca> pecasGalinheiro) {
    this.pecasGalinheiro = pecasGalinheiro;
  }

  public void adicionarJogador(Jogador jogador){
    if(jogador.getIdUsuario().equals("1")){
      jogadorA = jogador;
    } else{
      jogadorB = jogador;
    }
  }

  public Jogador getJogadorDestaInstancia(){
    Jogador jogador = null;
    if(jogadorA != null && jogadorA.isEstaNestaInstancia()){
      jogador = jogadorA;
    }
    else if(jogadorB != null && jogadorB.isEstaNestaInstancia()){
      jogador = jogadorB;
    }
    return jogador;
  }

  public Jogador getJogadorDaOutraInstancia(){
    Jogador jogador = null;
    if(jogadorA != null && !jogadorA.isEstaNestaInstancia()){
      jogador = jogadorA;
    }
    else if(jogadorB != null && !jogadorB.isEstaNestaInstancia()){
      jogador = jogadorB;
    }
    return jogador;
  }
}
