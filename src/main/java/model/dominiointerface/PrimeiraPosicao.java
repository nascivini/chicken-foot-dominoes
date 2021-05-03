package model.dominiointerface;

public class PrimeiraPosicao extends Posicao {
  private Posicao pecaCima;
  private Posicao pecaBaixo;
  private Posicao pecaEsquerda;
  private Posicao pecaDireita;

  public PrimeiraPosicao(){
    this.setPositionX(3);
    this.setPositionY(2);

    this.pecaCima = new Posicao();
    pecaCima.setPositionX(3);
    pecaCima.setPositionX(1);

    this.pecaBaixo = new Posicao();
    pecaBaixo.setPositionX(3);
    pecaBaixo.setPositionX(3);

    this.pecaEsquerda = new Posicao();
    pecaEsquerda.setPositionX(2);
    pecaEsquerda.setPositionX(1);

    this.pecaDireita = new Posicao();
    pecaDireita.setPositionX(4);
    pecaDireita.setPositionX(1);
  }

  @Override
  public boolean disponivel(){
    return pecaCima.disponivel() || pecaBaixo.disponivel() || pecaEsquerda.disponivel() || pecaDireita.disponivel();
  }

}
