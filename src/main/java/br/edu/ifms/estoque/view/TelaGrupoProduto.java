/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifms.estoque.view;

import br.edu.ifms.estoque.model.GrupoProduto;
import br.edu.ifms.estoque.queries.GrupoProdutoQueries;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author santos
 */
public class TelaGrupoProduto extends JDialog {

    private JTextField campoId;
    private JTextField campoNome;
    private JComboBox comboSubgrupo;
    private JButton botaoSalvar;
    private JButton botaoFechar;
    private GrupoProduto grupoProduto;
    private GrupoProdutoQueries queries;

    public TelaGrupoProduto(Frame telaPai) {
        super(telaPai);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setModal(true);
        this.grupoProduto = new GrupoProduto();
        queries = new GrupoProdutoQueries();

        initComponents();
    }

    private void initComponents() {
        Font font = new Font("Times", Font.PLAIN, 20);
        JLabel titulo = new JLabel("Formulário de Cadastro/Alteração");
        titulo.setFont(font);
        titulo.setHorizontalAlignment(JLabel.CENTER);

        add(titulo, BorderLayout.PAGE_START);
        add(criaPainelCampos(), BorderLayout.CENTER);
        add(criaPainelBotoes(), BorderLayout.PAGE_END);
        add(Box.createHorizontalGlue(), BorderLayout.LINE_START);
        add(Box.createHorizontalGlue(), BorderLayout.LINE_END);
    }

    private JPanel criaPainelCampos() {
        JLabel lblId = new JLabel("Código: ");
        lblId.setAlignmentX(Component.LEFT_ALIGNMENT);
        campoId = new JTextField(5);
        campoId.setEditable(false);

        JLabel lblNome = new JLabel("Nome: ");
        lblNome.setAlignmentX(Component.LEFT_ALIGNMENT);
        campoNome = new JTextField(20);
        campoNome.setFocusable(true);
        campoNome.requestFocusInWindow();

        JLabel lblSubgrupo = new JLabel("Grupo Pai: ");
        lblSubgrupo.setAlignmentX(Component.LEFT_ALIGNMENT);
        // traz a lista de subgrupos cadastrados
        List<GrupoProduto> lista = queries.getAllGrupos();
        comboSubgrupo = new JComboBox(lista.toArray());

        limpar();
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(lblId);
        panel.add(campoId);
        panel.add(lblNome);
        panel.add(campoNome);
        panel.add(lblSubgrupo);
        panel.add(comboSubgrupo);

        return panel;
    }

    private JPanel criaPainelBotoes() {
        ActionHandler handler = new ActionHandler();
        botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(handler);

        botaoFechar = new JButton("Fechar");
        botaoFechar.addActionListener(handler);

        JPanel panel = new JPanel();
        panel.add(botaoSalvar);
        panel.add(botaoFechar);
        return panel;
    }

    private void limpar() {
        campoId.setText("");
        campoNome.setText("");
        comboSubgrupo.setSelectedItem(null);
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

    public void setGrupoProduto(GrupoProduto grupo) {
        this.grupoProduto = grupo;
        
        campoId.setText(grupo.getId().toString());
        campoNome.setText(grupo.getNome());
        comboSubgrupo.setSelectedItem(grupo.getSubgrupo());
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
                    if (comboSubgrupo.getSelectedIndex() > -1) {
                        grupoProduto.setSubgrupo((GrupoProduto) comboSubgrupo.getSelectedItem());
                    }

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
