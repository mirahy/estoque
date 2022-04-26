/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.edu.ifms.estoque.CoR;

/**
 *
 * @author santos
 */
public interface IMarcaCoR {
    public void executar(Object source);
    public void setProximo(IMarcaCoR cor);
}
