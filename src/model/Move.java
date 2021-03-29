/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import br.ufsc.inf.leobr.cliente.Jogada;
import java.util.List;

/**
 *
 * @author vinicius
 */
public class Move implements Jogada {
    private PosicaoSend position;
    private Jogador jogador;
    private Jogador outroJogador;

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Jogador getOutroJogador() {
        return outroJogador;
    }

    public void setOutroJogador(Jogador outroJogador) {
        this.outroJogador = outroJogador;
    }

    public PosicaoSend getPosition() {
        return position;
    }

    public void setPosition(PosicaoSend position) {
        this.position = position;
    }

    
    
}
