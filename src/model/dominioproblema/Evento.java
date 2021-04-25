package model.dominioproblema;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Evento implements Jogada {
  private TipoEvento tipoEvento;
  private Jogador jogador;

  public TipoEvento getTipoEvento() {
    return tipoEvento;
  }

  public void setTipoEvento(TipoEvento tipoEvento) {
    this.tipoEvento = tipoEvento;
  }

  public Jogador getJogador() {
    return jogador;
  }

  public void setJogador(Jogador jogador) {
    this.jogador = jogador;
  }
}
