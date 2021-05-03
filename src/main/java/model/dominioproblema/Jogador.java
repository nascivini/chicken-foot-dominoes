package model.dominioproblema;

import br.ufsc.inf.leobr.cliente.Jogada;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Jogador implements Serializable, Jogada {
  private String nome;
  private List<Peca> pecas = new ArrayList<>();
  private boolean estaNestaInstancia;

  public Jogador() {}

  public Jogador(String nome, boolean estaNestaInstancia) {
    this.nome = nome;
    this.estaNestaInstancia = estaNestaInstancia;
  }

  public List<Peca> getPecas() {
    return pecas;
  }

  public void setPecas(List<Peca> pecas) {
    this.pecas = pecas;
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
      return nome.equals(toCompare.getNome());
    }
    return false;
  }
}
