package network;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import controller.MainController;
import model.dominioproblema.Evento;
import model.dominioproblema.EventoFimRodada;
import model.dominioproblema.EventoPartidaIniciada;

import javax.swing.*;
import java.util.List;

public class AtorNetGames implements OuvidorProxy {
  protected MainController mainController;
  protected Proxy proxy;

  public AtorNetGames(MainController interfaceGraf) {
    super();
    this.mainController = interfaceGraf;
    this.proxy = Proxy.getInstance();
    proxy.addOuvinte(this);
  }

  public boolean conectar(String servidor, String nome) {
    try {
      proxy.conectar(servidor, nome);
      return true;
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e.getMessage());
      e.printStackTrace();
      return false;
    }
  }

  public boolean desconectar() {
    try {
      proxy.desconectar();
      return true;
    } catch (NaoConectadoException e) {
      JOptionPane.showMessageDialog(null, e.getMessage());
      e.printStackTrace();
      return false;
    }
  }

  public void iniciarPartida() {
    try {
      proxy.iniciarPartida(new Integer(2));
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e.getMessage());
      e.printStackTrace();
    }
  }

  public void tratarPartidaNaoIniciada(String message) {
    JOptionPane.showMessageDialog(null, message);
  }

  public void enviarJogada(Evento evento) {
    try {
      proxy.enviaJogada(evento);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e.getMessage());
      e.printStackTrace();
    }
  }

  public void enviarEventoInicioPartida(EventoPartidaIniciada eventoPartidaIniciada){
    try {
      proxy.enviaJogada(eventoPartidaIniciada);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e.getMessage());
      e.printStackTrace();
    }
  }

  public String informarNomeAdversario(){
    try {
      List<String> adversarios = proxy.obterNomeAdversarios();
      return adversarios.stream().findFirst().orElse(null);
    }
    catch (Exception e){
      JOptionPane.showMessageDialog(null, "Erro ao procurar por adversários");
      return "";
    }
  }

  public void receberJogada(Jogada jogada) {
    if(jogada instanceof Evento){
      Evento evento = (Evento) jogada;
      mainController.receberJogada (evento);
    }
    else if(jogada instanceof EventoPartidaIniciada){
      EventoPartidaIniciada evento = (EventoPartidaIniciada) jogada;
      mainController.receberEventoInicioPartida(evento);
    }
    else if(jogada instanceof EventoFimRodada){
      //TODO
    }
  }

  public void finalizarPartidaComErro(String message) {
    mainController.partidaEncerradaPorErro(message);
  }

  @Override
  public void receberMensagem(String s) {
    //TODO: nenhuma ação necessária neste método ?
  }

  public void tratarConexaoPerdida() {
    mainController.partidaEncerradaPorErro("Conexão com o servidor perdida");
  }

  public void iniciarNovaPartida(Integer posicao) {
    mainController.partidaIniciadaPorOutroJogador(posicao);
  }
}
