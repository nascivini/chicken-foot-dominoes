/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import controller.MainController;
import javax.swing.JOptionPane;
import model.Move;

/**
 *
 * @author vinicius
 */
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
        } catch (NaoConectadoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
    }

    public void enviarJogada(Move lance) {
        try {
            proxy.enviaJogada(lance);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
    }

    public String informarNomeAdversario(String idUsuario) {
        String aux1 = proxy.obterNomeAdversario(new Integer(1));
        String aux2 = proxy.obterNomeAdversario(new Integer(2));;
        if (aux1.equals(idUsuario)) {
            return aux2;
        } else {
            return aux1;
        }
    }

    public void receberJogada(Jogada jogada) {
        Move estado = (Move) jogada;
        mainController.receiveMove(estado);
    }

    public void finalizarPartidaComErro(String message) {
        mainController.encerrarPartidaErro();

    }

    public void receberMensagem(String msg) {
        // TODO Auto-generated method stub

    }

    public void tratarConexaoPerdida() {
        mainController.encerrarPartidaErro();

    }

    public void tratarPartidaNaoIniciada(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public void iniciarNovaPartida(Integer posicao) {
        mainController.matchInitiated(posicao);
    }

}
