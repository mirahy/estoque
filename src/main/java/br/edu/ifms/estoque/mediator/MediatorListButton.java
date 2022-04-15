/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.mediator;

import br.edu.ifms.estoque.database.ClienteHibernateTableModel;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 *
 * @author santos
 */
public class MediatorListButton {
    
    private JButton btAlterar;
    private JButton btExcluir;
    private ClienteHibernateTableModel model;
    private JTextField txtNome;
    
    public void registerAlterar(JButton buttonAlterar) {
        this.btAlterar = buttonAlterar;
    }
    
    public void registerExcluir(JButton buttonExcluir) {
        this.btExcluir = buttonExcluir;
    }
    
    public void registerModel(ClienteHibernateTableModel model) {
        this.model = model;
    }
    
    public void registerField(JTextField txtNome) {
        this.txtNome = txtNome;
    }
    
    public void buscar() {
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        model.refresh(txtNome.getText());
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
