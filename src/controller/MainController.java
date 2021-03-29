/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JOptionPane;
import model.Jogador;
import model.Move;
import model.Piece;
import network.AtorNetGames;
import view.MainScreen;

/**
 *
 * @author vinicius
 */
public class MainController {
    
    private AtorNetGames atorNetGames;
    private MainScreen mainScreen;
    private boolean connected;
    public Jogador outroJogador;
    public Jogador eu;
    private boolean iniciadoPorMim;
    
    public MainController(MainScreen screen){
        atorNetGames = new AtorNetGames(this);
        mainScreen = screen;
    }
    
    public boolean conectar(String ipServidor, String nomeJogador){
        setConnected(atorNetGames.conectar(ipServidor, nomeJogador), ipServidor, nomeJogador);
        return connected;
    }
    
    public void desconectar(){
        connected = !atorNetGames.desconectar();
        setConnected(connected, "", "");
    }
    
    private void setConnected(boolean newState, String server, String playerName){
        connected = newState;
        eu = criarJogador(playerName, 1);
        mainScreen.updateStateAndPlayer(connected, server, eu);
    }
    
    public void removerPecaJogador(Jogador j, Piece p){
        
    }
    
    public void receiveMove(Move move){
        if(!move.getJogador().getNome().equals(eu.getNome())){
            mainScreen.receberJogada(move);
        }
    }
    
    public void sendMove(Move move){
        atorNetGames.enviarJogada(move);
    }
    
    public void matchInitiated(Integer posicao){	
        String idJogador = atorNetGames.informarNomeAdversario(eu.getNome());
        outroJogador = criarJogador(idJogador, posicao);
        mainScreen.partidaIniciada(outroJogador, iniciadoPorMim);
    }
    
    public void enviarInicioPartida(){
        iniciadoPorMim = true;
        atorNetGames.iniciarPartida();
    }
    
    public Jogador criarJogador(String nome, Integer id){
        Jogador jogador = new Jogador();
        jogador.setNome(nome);
        jogador.setIdUsuario(id);
        return jogador;
    }
    
    public void encerrarPartidaErro(){
        this.eu = null;
        this.outroJogador = null;
        mainScreen.limparCampos();
        if(connected){
            JOptionPane.showMessageDialog(null, "Houve uma falha no servidor ou o outro jogador se desconectou.");
            connected = false;
        }
        
    }
    
}
