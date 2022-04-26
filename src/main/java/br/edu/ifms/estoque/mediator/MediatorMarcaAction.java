/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.mediator;

import br.edu.ifms.estoque.database.MarcaHibernateTableModel;
import br.edu.ifms.estoque.facade.MarcaFacade;
import br.edu.ifms.estoque.view.TelaMarca;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author santos
 */
public class MediatorMarcaAction {

    private JButton buttonEditar;
    private JButton buttonInserir;
    private JButton buttonExcluir;
    private JButton buttonFechar;
    private JTable tabela;
    private MarcaHibernateTableModel model;
    private final MarcaFacade facade;
    private final JFrame framePai;

    public MediatorMarcaAction(MarcaFacade facade, JFrame framePai) {
        this.facade = facade;
        this.framePai = framePai;
    }
    
    public MediatorMarcaAction registerButtonExcluir(JButton button){
        this.buttonExcluir = button;
        return this;
    }
    
    public MediatorMarcaAction registerButtonEditar(JButton button) {
        this.buttonEditar = button;
        return this;
    }
    
    public MediatorMarcaAction registerButtonInserir(JButton button) {
        this.buttonInserir = button;
        return this;
    }
    
    public MediatorMarcaAction registerButtonFechar(JButton button) {
        this.buttonFechar = button;
        return this;
    }
    
    
    public MediatorMarcaAction registerTabela(JTable tabela) {
        this.tabela = tabela;
        return this;
    }

    public MediatorMarcaAction registerModel(MarcaHibernateTableModel model) {
        this.model = model;
        return this;
    }
    
    public Boolean isButtonEditar(Object source) {
        return source == buttonEditar;
    }
    
    public boolean isButtonInserir(Object source) {
        return source == buttonInserir;
    }
    
    public boolean isButtonExcluir(Object source){
        return source == buttonExcluir;
    }
    
    public boolean isButtonFechar(Object source){
        return source == buttonFechar;
    }

    public boolean isRowSelected() {
        int index = tabela.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(framePai,
                    "Você deve selecionar um item para alterar/excluir uma Marca!",
                    "Alterar/Excluir Marca", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public void editar() {
        int index = tabela.getSelectedRow();
        Long id = (Long) model.getValueAt(index, 0);
        TelaMarca form = facade.editarFormulario(framePai, facade, id);
        form.setVisible(true);
    }

    public void inserir() {
        TelaMarca form = facade.abrirFormulario(framePai, facade);
        form.setVisible(true);
    }
    
    public void excluir(){
        int index = tabela.getSelectedRow();
          Long id = (Long) model.getValueAt(index, 0);
          facade.excluir(framePai, id);
        
    }
    
    public void fechar(){
        framePai.dispose();
    }


}
