/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifms.estoque.view;

import br.edu.ifms.estoque.facade.MarcaFacade;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author santos
 */
public class TelaMarca extends JDialog {

    private MarcaFacade facade;

    private JTextField campoId;
    private JTextField campoNomeMarca;
    private JButton botaoSalvar;
    private JButton botaoFechar;

    public TelaMarca(Frame telaPai, boolean modal, MarcaFacade facade) {
        super(telaPai, modal);
        this.facade = facade;

        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setModal(true);

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
        JLabel lblId = new JLabel("Id.: ");
        lblId.setAlignmentX(Component.LEFT_ALIGNMENT);
        campoId = new JTextField(5);
        campoId.setEditable(false);

        JLabel lblNome = new JLabel("Nome: ");
        lblNome.setAlignmentX(Component.LEFT_ALIGNMENT);
        campoNomeMarca = new JTextField(20);
        campoNomeMarca.setFocusable(true);
        campoNomeMarca.requestFocusInWindow();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(lblId);
        panel.add(campoId);
        panel.add(lblNome);
        panel.add(campoNomeMarca);

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
        campoNomeMarca.setText("");
    }

    private boolean validarCampos() {
        if ("".equals(campoNomeMarca.getText())) {
            JOptionPane.showMessageDialog(TelaMarca.this, "Você deve"
                    + " informar o nome da marca do produto",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public void setId(Long id) {
        facade.carregarDados(id, campoId, campoNomeMarca);
    }

    private class ActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == botaoSalvar) {
                if (validarCampos()) {
                    if (JOptionPane.showConfirmDialog(TelaMarca.this, "Deseja salvar esses dados?",
                            "Salvar", JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                        facade.salvar(campoId, campoNomeMarca);
                        JOptionPane.showMessageDialog(TelaMarca.this, "Dados salvos com sucesso!",
                                "Informação", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    }
                    dispose();
                }
            } else if (ae.getSource() == botaoFechar) {
                dispose();
            }
        }

    }

}
