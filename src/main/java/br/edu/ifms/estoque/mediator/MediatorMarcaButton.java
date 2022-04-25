/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.mediator;

import br.edu.ifms.estoque.database.ClienteHibernateTableModel;
import br.edu.ifms.estoque.database.MarcaHibernateTableModel;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 *
 * @author santos
 */
public class MediatorMarcaButton {
    
    private JButton btAlterar;
    private JButton btExcluir;
    private MarcaHibernateTableModel model;
    
    public void registerAlterar(JButton buttonAlterar) {
        this.btAlterar = buttonAlterar;
    }
    
    public void registerExcluir(JButton buttonExcluir) {
        this.btExcluir = buttonExcluir;
    }
    
    public void registerModel(MarcaHibernateTableModel model) {
        this.model = model;
    }
    
    public void buscar() {
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        model.refresh(null);
    }
    
    public void cadastrar() {
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
    }
    
    public void selecionar() {
        btAlterar.setVisible(true);
        btExcluir.setVisible(true);
    }
    
}
