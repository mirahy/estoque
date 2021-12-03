/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifms.estoque.view;

import br.edu.ifms.estoque.dao.IMarcaDao;
import br.edu.ifms.estoque.database.MarcaResultSetTableModel;
import br.edu.ifms.estoque.factory.MarcaDaoFactory;
import br.edu.ifms.estoque.model.Marca;
import br.edu.ifms.estoque.queries.MarcaQueries;
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
    private MarcaResultSetTableModel modelo;
    private IMarcaDao dao;

    public TelaListagemMarca() {
        super("Listagem de Marcas");
        MarcaDaoFactory factory = new MarcaDaoFactory();
        dao = (IMarcaDao) factory.createObject();

        criarBotoes();
        criarTabela();
        atualizarTabela();
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
        modelo.atualizaTabela();
        tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(300);
    }

    private void criarTabela() {
        modelo = new MarcaResultSetTableModel();

        tabela = new JTable(modelo);
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

    private void showTelaCadastro(Marca marca) {
        TelaMarca tela = new TelaMarca(TelaListagemMarca.this);
        if (marca != null) {
            tela.setMarca(marca);
        }
        tela.setVisible(true);
        atualizarTabela();
    }

    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            Object source = ae.getSource();
            if (source == btFechar) {
                dispose();
            } else if (source == btInserir) {
                showTelaCadastro(null);
            } else if (source == btExcluir && isRowSelected()) {
                int index = tabela.getSelectedRow();
                Object obj = modelo.getValueAt(index, 0);
                Long id = (Long) obj;
                Marca marca = dao.buscarPorId(id);
                dao.excluir(marca);
                JOptionPane.showMessageDialog(TelaListagemMarca.this,
                        "Marca excluída com sucesso!",
                        "Excluir Marca",
                        JOptionPane.INFORMATION_MESSAGE);
                atualizarTabela();
            } else if (source == btEditar && isRowSelected()) {
                int index = tabela.getSelectedRow();
                Object obj = modelo.getValueAt(index, 0);
                Long id = (Long) obj;
                Marca marca = dao.buscarPorId(id);
                
                showTelaCadastro(marca);
            }
        }

    }
}
