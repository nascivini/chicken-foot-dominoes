/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import br.ufsc.inf.leobr.cliente.Jogada;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author vinicius
 */
public class Jogador implements Serializable, Jogada {
    private boolean suaVez;
    private String nome;
    private Integer idUsuario;
    private List<Piece> pecas = new ArrayList<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Piece> getPecas() {
        return pecas;
    }

    public void setPecas(List<Piece> pecas) {
        this.pecas = pecas;
    }

    public boolean isSuaVez() {
        return suaVez;
    }

    public void setSuaVez(boolean suaVez) {
        this.suaVez = suaVez;
    }
    
    public void removerPeca(Piece p){
        List<Piece> pecaEncontrada = pecas.stream().filter(e -> e.equals(p)).collect(Collectors.toList());
        if(pecaEncontrada != null && !pecaEncontrada.isEmpty()){
            pecas.remove(p);
        }
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Jogador){
            Jogador outro = (Jogador) obj;
            return nome.equals(outro.getNome());
        }
        return false;
    }
    
}
