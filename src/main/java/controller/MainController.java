package controller;

import model.dominiointerface.PrimeiraPosicao;
import model.dominiointerface.Tabuleiro;
import model.dominioproblema.Evento;
import model.dominioproblema.EventoPartidaIniciada;
import model.dominioproblema.Jogador;
import model.dominioproblema.Peca;
import network.AtorNetGames;
import view.MainScreen;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.dominiointerface.StatusConexao;

public class MainController {
  private Tabuleiro tabuleiro;
  private AtorNetGames atorNetGames;
  private MainScreen mainScreen;
  private Peca pecaEscolhida;

  public MainController() {
    this.tabuleiro = new Tabuleiro();
    this.atorNetGames = new AtorNetGames(this);
    this.mainScreen = new MainScreen(this);
    this.mainScreen.setVisible(true);
  }

  public boolean conectar(String servidor, String nomeJogador) {
    boolean conectado = atorNetGames.conectar(servidor, nomeJogador);
    if (conectado) {
      mainScreen.alterarStatusConexao(StatusConexao.CONECTADO, servidor);

      Jogador jogador = new Jogador(nomeJogador, true);
      tabuleiro.adicionarJogador(jogador);

      mainScreen.atualizarJogadorDestaInstancia(nomeJogador);
    }

    return conectado;
  }

  public boolean desconectar() {
    return atorNetGames.desconectar();
  }

  public void partidaEncerradaPorErro(String message) {
    JOptionPane.showMessageDialog(null, message);
  }

  public void partidaIniciadaPorOutroJogador(Integer idJogador) {
    System.out.println("Recebida mensagem de partida a iniciar ... Jogador que iniciou: " + idJogador);
  }

  public void partidaIniciadaPorJogadorAtual() {
    Jogador jogador = tabuleiro.getJogadorDestaInstancia();
//    atorNetGames.iniciarPartida();

//    String nomeAdversario = atorNetGames.informarNomeAdversario();
//    if(nomeAdversario == null || nomeAdversario.isEmpty()) return;
//    Jogador jogadorAdversario = new Jogador(nomeAdversario, false);
//    tabuleiro.adicionarJogador(jogadorAdversario);
//    mainScreen.atualizarJogadorDaOutraInstancia(nomeAdversario);

    distribuirPecas();

//    EventoPartidaIniciada evento = new EventoPartidaIniciada();
//
//    evento.setPecasGalinheiro(tabuleiro.getPecasGalinheiro());
//    evento.setJogadorQueIniciou(tabuleiro.getJogadorDestaInstancia());
//    evento.setPecas(tabuleiro.getJogadorDaOutraInstancia().getPecas());
//    atorNetGames.enviarEventoInicioPartida(evento);
//
    mainScreen.carregarPecasJogadorAtual(jogador.getPecas());
    mainScreen.atualizarGalinheiro(tabuleiro.getPecasGalinheiro().size());
//    mainScreen.atualizarVez("Vez de " + jogadorAdversario.getNome());
    mainScreen.atualizarStatusDaPartida("PARTIDA EM ANDAMENTO");
  }

  private List<Peca> carregarPecasDoJogo() {
    List<Peca> pecas = new ArrayList<Peca>();
    for (int a = 0; a <= 9; a++) {
      for (int b = a; b <= 9; b++) {
        Peca peca = new Peca(a, b, "");
        pecas.add(peca);
      }
    }
    return pecas;
  }

  public void receberEventoInicioPartida(EventoPartidaIniciada evento) {
    tabuleiro.setPecasGalinheiro(evento.getPecasGalinheiro());
    if (!evento.getJogadorQueIniciou().equals(tabuleiro.getJogadorDestaInstancia())) {
      Jogador jogadorDaqui = tabuleiro.getJogadorDestaInstancia();
      jogadorDaqui.setPecas(evento.getPecas());

      Jogador jogadorQueIniciou = evento.getJogadorQueIniciou();
      jogadorQueIniciou.setEstaNestaInstancia(false);
      tabuleiro.adicionarJogador(jogadorQueIniciou);

      mainScreen.carregarPecasJogadorAtual(jogadorDaqui.getPecas());
      mainScreen.atualizarGalinheiro(tabuleiro.getPecasGalinheiro().size());
      mainScreen.atualizarVez("Vez de " + jogadorDaqui.getNome());
      mainScreen.atualizarStatusDaPartida("PARTIDA EM ANDAMENTO");

      JOptionPane.showMessageDialog(null, "Partida iniciada pelo jogador adversário. Sua vez!");
    }
  }

  public void distribuirPecas() {
    List<Peca> pecasParaDistribuir = carregarPecasDoJogo();
    List<Peca> pecasDistribuidas = new ArrayList<>();

    separarPecasParaOJogador(tabuleiro.getJogadorDestaInstancia(), pecasParaDistribuir, pecasDistribuidas);
    pecasParaDistribuir.removeAll(pecasDistribuidas);
//    separarPecasParaOJogador(tabuleiro.getJogadorDaOutraInstancia(), pecasParaDistribuir, pecasDistribuidas);
//    pecasParaDistribuir.removeAll(pecasDistribuidas);

    tabuleiro.setPecasGalinheiro(pecasParaDistribuir);
  }

  public void separarPecasParaOJogador(Jogador jogador, List<Peca> pecasParaDistribuir, List<Peca> pecasDistribuidas){
    Collections.shuffle(pecasParaDistribuir);
    for (int i = 0; i < 21; i++) {
      Peca peca = pecasParaDistribuir.get(i);
      jogador
          .getPecas()
          .add(peca);
      pecasDistribuidas.add(peca);
    }
  }

  public void receberJogada(Evento evento) {
    //TODO: enviar atualização para a tela e atualizar tela
  }

  public void pecaEscolhidaPeloJogador(JLabel label){
    String[] constraints = label.getName().split("_");
    int line = Integer.parseInt(constraints[0]);
    int column = Integer.parseInt(constraints[1]);

    Peca peca = tabuleiro.getJogadorDestaInstancia()
        .getPecas()
        .stream()
        .filter(e -> e.equals(new Peca(line, column, "")))
        .findFirst()
        .orElse(null);

    pecaEscolhida = peca;

    if(tabuleiro.isPrimeiraPeca()){
      PrimeiraPosicao posicao = new PrimeiraPosicao();
      posicao.setjLabel(label);
      mainScreen.adicionarPosicaoNaTela(posicao);
    }
  }

}
