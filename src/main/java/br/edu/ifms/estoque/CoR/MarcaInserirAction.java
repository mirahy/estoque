/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.CoR;

import br.edu.ifms.estoque.mediator.MediatorMarcaAction;

/**
 *
 * @author santos
 */
public class MarcaInserirAction implements
        IMarcaCoR {
    private IMarcaCoR proximo;
    
    private final MediatorMarcaAction mediator;
    
    public MarcaInserirAction(MediatorMarcaAction mediator) {
        this.mediator = mediator;
    }
    
    @Override
    public void executar(Object source) {
        if (mediator.isButtonInserir(source)) {
            mediator.inserir();
        } else {
            this.proximo.executar(source);
        }
    }

    @Override
    public void setProximo(IMarcaCoR cor) {
        this.proximo = cor;
    }

}
