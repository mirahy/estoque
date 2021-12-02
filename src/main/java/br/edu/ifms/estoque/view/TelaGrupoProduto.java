/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifms.estoque.view;

import br.edu.ifms.estoque.model.GrupoProduto;
import br.edu.ifms.estoque.queries.GrupoProdutoQueries;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author santos
 */
public class TelaGrupoProduto extends JDialog {

    private JTextField campoId;
    private JTextField campoNome;
    private JButton botaoSalvar;
    private JButton botaoFechar;
    private GrupoProduto grupoProduto;
    private GrupoProdutoQueries queries;

    public TelaGrupoProduto(Frame telaPai) {
        super(telaPai);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setModal(true);
        this.grupoProduto = new GrupoProduto();

        JLabel lblId = new JLabel("Código: ");
        add(lblId);
        campoId = new JTextField(20);
        campoId.setEditable(false);
        add(campoId);

        add(new JLabel("Nome da Grupo de Produto: "));
        campoNome = new JTextField(20);
        campoNome.setFocusable(true);
        campoNome.requestFocusInWindow();
        add(campoNome);

        ActionHandler handler = new ActionHandler();

        botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(handler);
        add(botaoSalvar);

        botaoFechar = new JButton("Fechar");
        botaoFechar.addActionListener(handler);
        add(botaoFechar);
        
        queries = new GrupoProdutoQueries();
    }

    private void limpar() {
        campoId.setText("");
        campoNome.setText("");
    }

    private boolean validarCampos() {
        if ("".equals(campoNome.getText())) {
            JOptionPane.showMessageDialog(TelaGrupoProduto.this, "Você deve"
                    + " informar o nome do grupo do produto",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public GrupoProduto getGrupoProduto() {
        return this.grupoProduto;
    }

    private class ActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == botaoSalvar) {
                if (validarCampos()) {

                    String sId = campoId.getText();
                    Boolean contemNumero = sId.matches("\\d+");
                    
                    grupoProduto.setId(contemNumero ? Long.parseLong(sId) : null);
                    grupoProduto.setNome(campoNome.getText());
                    
                    if (grupoProduto.getId() == null) {
                        queries.addGrupoProduto(grupoProduto.getNome(), 
                                grupoProduto.getSubgrupo());
                    }

                    limpar();

                    JOptionPane.showMessageDialog(TelaGrupoProduto.this, "Dados salvos com sucesso!",
                            "Informação", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
            } else if (ae.getSource() == botaoFechar) {
                dispose();
            }
        }

    }

}
