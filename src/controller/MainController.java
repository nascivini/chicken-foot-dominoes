package controller;

import model.dominiointerface.Tabuleiro;
import model.dominioproblema.Evento;
import model.dominioproblema.EventoPartidaIniciada;
import model.dominioproblema.Jogador;
import network.AtorNetGames;

import javax.swing.*;

public class MainController {
  private Tabuleiro tabuleiro;
  private AtorNetGames atorNetGames;

  public MainController(){
    this.tabuleiro = new Tabuleiro();
    this.atorNetGames = new AtorNetGames(this);
  }

  public void conectar(String servidor, String nomeJogador){
    boolean connectouSe = atorNetGames.conectar(servidor, nomeJogador);
    if(connectouSe){
      JOptionPane.showMessageDialog(null, "Conectado com sucesso a " + servidor);
      jogadorCriado(nomeJogador);
    }
    //TODO: enviar para a tela retorno da conexão
  }

  public void jogadorCriado(String nomeJogador){
    String idJogador = atorNetGames.informarNomeAdversario(nomeJogador);
    Jogador jogador = new Jogador(nomeJogador, idJogador);

    tabuleiro.adicionarJogador(jogador);
    //TODO: atualizar a tela com os dados do jogador
  }

  public void desconectar(){
    atorNetGames.desconectar();
    //TODO: enviar para a tela retorno da desconexão
  }

  public void partidaEncerradaPorErro(String message){
    JOptionPane.showMessageDialog(null, message);
  }

  public void partidaIniciadaPorOutroJogador(Integer idJogador){
   //Aguardando evento de início de partida
  }

  public void partidaIniciadaPorJogadorAtual(){
    Jogador jogador = tabuleiro.getJogadorDestaInstancia();
    atorNetGames.iniciarNovaPartida(Integer.parseInt(jogador.getIdUsuario()));

    //TODO: distribuir peças (chamar método distribuirPecas())

    EventoPartidaIniciada evento = new EventoPartidaIniciada();

    evento.setPecasGalinheiro(tabuleiro.getPecasGalinheiro());
    evento.setJogadorQueIniciou(tabuleiro.getJogadorDestaInstancia());
    evento.setPecas(tabuleiro.getJogadorDaOutraInstancia().getPecas());
    atorNetGames.enviarEventoInicioPartida(evento);
  }

  public void receberEventoInicioPartida(EventoPartidaIniciada evento){
    tabuleiro.setPecasGalinheiro(evento.getPecasGalinheiro());

    Jogador jogadorDaqui = tabuleiro.getJogadorDestaInstancia();
    jogadorDaqui.setPecas(evento.getPecas());

    tabuleiro.adicionarJogador(evento.getJogadorQueIniciou());

    //TODO: atualizar tela (Jogo iniciado, novo jogador e peças distrubuídas)
  }

  public void receberJogada(Evento evento){
    //TODO: enviar atualização para a tela e atualizar tela
  }

  public void distribuirPecas(){
    carregarPecasDoJogo();
    //TODO: distribuir aleatoriamente
  }

  private void carregarPecasDoJogo(){
    for(int a = 0; a < 9; a++){
      for(int b = 0; b <= a; a++){

      }
    }
  }

}
