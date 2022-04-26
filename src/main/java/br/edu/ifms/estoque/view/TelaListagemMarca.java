/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifms.estoque.view;

import br.edu.ifms.estoque.CoR.IMarcaCoR;
import br.edu.ifms.estoque.CoR.MarcaEditarAction;
import br.edu.ifms.estoque.CoR.MarcaInserirAction;
import br.edu.ifms.estoque.database.MarcaHibernateTableModel;
import br.edu.ifms.estoque.facade.MarcaFacade;
import br.edu.ifms.estoque.mediator.MediatorMarcaAction;
import br.edu.ifms.estoque.mediator.MediatorMarcaButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author santos
 */
public class TelaListagemMarca extends JFrame {

    private JPanel painelFundo;
    private JPanel painelBotoes;
    private JTable tabela;
    private JScrollPane barraRolagem;
    private JButton btInserir;
    private JButton btExcluir;
    private JButton btEditar;
    private JButton btFechar;
    
    private MarcaHibernateTableModel model;
    
    private MediatorMarcaButton mediator;
    private MarcaFacade facade;

    public TelaListagemMarca() {
        super("Listagem de Marcas");
        
        model = new MarcaHibernateTableModel();
        facade = new MarcaFacade();

        criarBotoes();
        criarTabela();
        
        mediator = new MediatorMarcaButton();
        mediator.registerAlterar(btEditar);
        mediator.registerExcluir(btExcluir);
        mediator.registerModel(model);
        
        mediator.buscar();
    }

    private void criarBotoes() {
        btInserir = new JButton("Inserir");
        btEditar = new JButton("Editar");
        btExcluir = new JButton("Excluir");
        btFechar = new JButton("Fechar");
        
        ButtonHandler handler = new ButtonHandler();
        btInserir.addActionListener(handler);
        btEditar.addActionListener(handler);
        btExcluir.addActionListener(handler);
        btFechar.addActionListener(handler);

        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.CENTER);

        painelBotoes = new JPanel(layout);
        painelBotoes.add(btInserir);
        painelBotoes.add(btEditar);
        painelBotoes.add(btExcluir);
        painelBotoes.add(btFechar);
    }

    private void atualizarTabela() {
        mediator.buscar();
        tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(300);
    }

    private void criarTabela() {
        tabela = new JTable(model);
        tabela.setSize(640, 480);

        barraRolagem = new JScrollPane(tabela);

        Font font = new Font("Times", Font.PLAIN, 30);
        JLabel titulo = new JLabel("Listagem de Marcas");
        titulo.setFont(font);
        titulo.setHorizontalAlignment(JLabel.CENTER);

        BorderLayout layout = new BorderLayout(5, 5);
        painelFundo = new JPanel(layout);
        painelFundo.add(titulo, BorderLayout.NORTH);
        painelFundo.add(barraRolagem, BorderLayout.CENTER);
        painelFundo.add(painelBotoes, BorderLayout.SOUTH);

        add(painelFundo);
    }

    private boolean isRowSelected() {
        int index = tabela.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(TelaListagemMarca.this,
                    "Você deve selecionar um item para alterar/excluir uma Marca!",
                    "Alterar/Excluir Marca", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private class ButtonHandler implements ActionListener {

        private MarcaInserirAction marcaInserirAction;
        private MarcaEditarAction marcaEditarAction;
        private IMarcaCoR marcaExcluirAction;
        private IMarcaCoR marcaFecharAction;
        
        private MediatorMarcaAction medidatorAction;
        
        public ButtonHandler() {
            this.medidatorAction = new MediatorMarcaAction(facade, 
                    TelaListagemMarca.this);
            this.medidatorAction.registerButtonEditar(btEditar);
            this.medidatorAction.registerButtonInserir(btInserir);
            this.medidatorAction.registerModel(model);
            this.medidatorAction.registerTabela(tabela);
            
            marcaEditarAction = new MarcaEditarAction(medidatorAction);
            marcaInserirAction = new MarcaInserirAction(medidatorAction);
            marcaInserirAction.setProximo(marcaEditarAction);
        }
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            Object source = ae.getSource();
            marcaInserirAction.executar(source);
            
//            if (source == btFechar) {
//                dispose();
//            } else if (source == btInserir) {
//                TelaMarca form = facade.abrirFormulario(TelaListagemMarca.this, facade);
//                form.setVisible(true);
//            } else if (source == btExcluir && isRowSelected()) {
//                int index = tabela.getSelectedRow();
//                Long id = (Long) model.getValueAt(index, 0);
//                facade.excluir(TelaListagemMarca.this, id);
//            } else if (source == btEditar && isRowSelected()) {
//                int index = tabela.getSelectedRow();
//                Long id = (Long) model.getValueAt(index, 0);
//                TelaMarca form = facade.editarFormulario(TelaListagemMarca.this, facade, id);
//                form.setVisible(true);
//            }
            mediator.buscar();
        }

    }
    
    public static void main(String[] args) {
        TelaListagemMarca tela = new TelaListagemMarca();
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setSize(400, 500);
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
    }
}
