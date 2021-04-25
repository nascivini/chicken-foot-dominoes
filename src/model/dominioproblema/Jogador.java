package model.dominioproblema;

import br.ufsc.inf.leobr.cliente.Jogada;

import java.io.Serializable;
import java.util.List;

public class Jogador implements Serializable, Jogada {
  private String idUsuario;
  private String nome;
  private List<Peca> pecas;
  private boolean estaNestaInstancia;

  public Jogador() {}

  public Jogador(String idUsuario, String nome) {
    this.idUsuario = idUsuario;
    this.nome = nome;
  }

  public List<Peca> getPecas() {
    return pecas;
  }

  public void setPecas(List<Peca> pecas) {
    this.pecas = pecas;
  }

  public String getIdUsuario() {
    return idUsuario;
  }

  public String getNome() {
    return nome;
  }

  public boolean isEstaNestaInstancia() {
    return estaNestaInstancia;
  }

  public void setEstaNestaInstancia(boolean estaNestaInstancia) {
    this.estaNestaInstancia = estaNestaInstancia;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj instanceof Jogador){
      Jogador toCompare = (Jogador) obj;
      return idUsuario.equals(toCompare.getIdUsuario());
    }
    return false;
  }
}
