/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dominiointerface;

import java.awt.Color;

/**
 *
 * @author vinicius
 */
public enum StatusConexao {  
  NAO_CONECTADO("NÃO CONECTADO", Color.RED),
  CONECTADO("CONECTADO a ", Color.GREEN);
  
  private final Color color;
  private final String message;
  
  private StatusConexao(String message, Color color){
    this.message = message;
    this.color = color;
  }
  
  public Color getColor(){
    return color;
  }
  
  public String getMessage(){
    return message;
  }
}
