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
public class MarcaExcluirAction implements IMarcaCoR{
    
    private IMarcaCoR proximo;
    private final MediatorMarcaAction mediator;

    public MarcaExcluirAction( MediatorMarcaAction mediator) {
        this.mediator = mediator;
    } 
    
    @Override
    public void executar(Object source) {
        if (mediator.isButtonExcluir(source) && mediator.isRowSelected()) {
            mediator.excluir();
        } else {
            this.proximo.executar(source);
        }
    }

    @Override
    public void setProximo(IMarcaCoR cor) {
        this.proximo = cor;
    }
    
}
