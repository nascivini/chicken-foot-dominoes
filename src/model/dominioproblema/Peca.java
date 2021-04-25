package model.dominioproblema;

import br.ufsc.inf.leobr.cliente.Jogada;

import java.io.Serializable;

public class Peca implements Serializable, Jogada {
  private short ladoA;
  private short ladoB;
  public String imagePath;

  public Peca(short ladoA, short ladoB, String imagePath){
    this.ladoA = ladoA;
    this.ladoB = ladoB;
    this.imagePath = imagePath;
  }

  public boolean possuiSimilar(short paraComparar){
    return paraComparar == ladoA || paraComparar == ladoB;
  }

  public short getLadoA() {
    return ladoA;
  }

  public void setLadoA(short ladoA) {
    this.ladoA = ladoA;
  }

  public short getLadoB() {
    return ladoB;
  }

  public void setLadoB(short ladoB) {
    this.ladoB = ladoB;
  }

  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }
}
