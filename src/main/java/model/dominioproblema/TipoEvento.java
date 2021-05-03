package model.dominioproblema;

import br.ufsc.inf.leobr.cliente.Jogada;

import java.io.Serializable;

public enum TipoEvento implements Serializable, Jogada {
  PARTIDA_INICIADA("Partida Iniciada"),
  COMPRA_PECA("Compra de peça do galinheiro"),
  PASSAR_A_VEZ("Passou a vez, sem peças disponíveis"),
  JOGADA_NORMAL("Jogada normal");

  private String descricao;

  TipoEvento(String descricao){
    this.descricao = descricao;
  }

  public String getDescricao() {
    return descricao;
  }
}
