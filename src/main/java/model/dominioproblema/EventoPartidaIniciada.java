package model.dominioproblema;

import br.ufsc.inf.leobr.cliente.Jogada;

import java.io.Serializable;
import java.util.List;

public class EventoPartidaIniciada implements Serializable, Jogada {
  private final TipoEvento tipoEvento = TipoEvento.PARTIDA_INICIADA;
  private Jogador jogadorQueIniciou;
  private List<Peca> pecas;
  private List<Peca> pecasGalinheiro;

  public TipoEvento getTipoEvento() {
    return tipoEvento;
  }

  public List<Peca> getPecasGalinheiro() {
    return pecasGalinheiro;
  }

  public void setPecasGalinheiro(List<Peca> pecasGalinheiro) {
    this.pecasGalinheiro = pecasGalinheiro;
  }

  public List<Peca> getPecas() {
    return pecas;
  }

  public void setPecas(List<Peca> pecas) {
    this.pecas = pecas;
  }

  public Jogador getJogadorQueIniciou() {
    return jogadorQueIniciou;
  }

  public void setJogadorQueIniciou(Jogador jogadorQueIniciou) {
    this.jogadorQueIniciou = jogadorQueIniciou;
  }
}
