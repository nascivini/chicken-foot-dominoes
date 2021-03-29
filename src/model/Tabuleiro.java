/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import view.MainScreen;

/**
 *
 * @author vinicius
 */
public class Tabuleiro {
    
    private Piece primeiraPeca = new Piece(9,9);
    private List<Piece> todasPecas = new ArrayList<>();
    private List<Position> posicoes = new ArrayList<>();
    private Jogador jogador1;
    private Jogador jogador2;
    private MainScreen mainScreen;
    
    public Tabuleiro(MainScreen mainScreen, Jogador jogador1, Jogador jogador2, boolean distribuirPecas){
        this.mainScreen = mainScreen;
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        inicializarPecas();
        distribuirPecas();
    }
    
    private void inicializarPecas(){
        todasPecas.add(primeiraPeca);
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                int sideOne = i;
                int sideTwo = j;
                Piece p = new Piece(sideOne, sideTwo);
                if(!todasPecas.stream().anyMatch(e -> e.equals(p))){
                    todasPecas.add(p);
                }
            }
            
        }
    }
    
    public void distribuirPecas(){
        Random rand = new Random();
        List<Piece> pecasJog1 = new ArrayList<>();
        List<Piece> pecasJog2 = new ArrayList<>();
        for(int i = 0; i < 9; i++){
            pecasJog1.add(todasPecas.get(rand.nextInt(todasPecas.size())));
            pecasJog2.add(todasPecas.get(rand.nextInt(todasPecas.size())));
        }
        jogador1.setPecas(pecasJog1);
        jogador2.setPecas(pecasJog2);
    }

    public Piece getPrimeiraPeca() {
        return primeiraPeca;
    }

    public void setPrimeiraPeca(Piece primeiraPeca) {
        this.primeiraPeca = primeiraPeca;
    }

    public List<Piece> getTodasPecas() {
        return todasPecas;
    }

    public void setTodasPecas(List<Piece> todasPecas) {
        this.todasPecas = todasPecas;
    }

    public List<Position> getPosicoes() {
        return posicoes;
    }

    public void setPosicoes(List<Position> posicoes) {
        this.posicoes = posicoes;
    }

    public Jogador getJogador1() {
        return jogador1;
    }

    public void setJogador1(Jogador jogador1) {
        this.jogador1 = jogador1;
    }

    public Jogador getJogador2() {
        return jogador2;
    }

    public void setJogador2(Jogador jogador2) {
        this.jogador2 = jogador2;
    }

    public MainScreen getMainScreen() {
        return mainScreen;
    }

    public void setMainScreen(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
    }
    
    
}
