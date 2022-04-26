/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.CoR;

import br.edu.ifms.estoque.mediator.MediatorMarcaAction;

/**
 *
 * @author aluno
 */
public class MarcaFecharAction implements IMarcaCoR{
    
     private IMarcaCoR proximo;
    private final MediatorMarcaAction mediator;

    public MarcaFecharAction( MediatorMarcaAction mediator) {
        this.mediator = mediator;
    } 

    @Override
    public void executar(Object source) {
        if (mediator.isButtonFechar(source) && mediator.isRowSelected()) {
            mediator.fechar();
        } 
    }

    @Override
    public void setProximo(IMarcaCoR cor) {
    }
    
}
