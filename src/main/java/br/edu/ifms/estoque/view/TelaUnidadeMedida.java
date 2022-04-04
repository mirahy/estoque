/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifms.estoque.view;

import br.edu.ifms.estoque.model.UnidadeMedida;
import br.edu.ifms.estoque.queries.UnidadeMedidaQueries;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author santos
 */
public class TelaUnidadeMedida extends JDialog {
    
    private JTextField campoId;
    private JTextField campoNome;
    
    private JRadioButton opcaoFrancionado;
    private JRadioButton opcaoNaoFracionado;
    
    private JButton botaoSalvar;
    private JButton botaoFechar;
    private UnidadeMedida unidadeMedida;
    private UnidadeMedidaQueries queries;
    
    public TelaUnidadeMedida(Frame telaPai) {
        super(telaPai);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setModal(true);
        this.unidadeMedida = new UnidadeMedida();
        this.queries = new UnidadeMedidaQueries();
        
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
        campoNome = new JTextField(20);
        campoNome.setFocusable(true);
        campoNome.requestFocusInWindow();
        
        opcaoFrancionado = new JRadioButton("Fracionado");
        opcaoFrancionado.setSelected(true);
        opcaoNaoFracionado = new JRadioButton("Não fracionado");
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(opcaoFrancionado);
        grupo.add(opcaoNaoFracionado);
        
        JPanel radioPanel = new JPanel();
        radioPanel.add(opcaoFrancionado);
        radioPanel.add(opcaoNaoFracionado);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(lblId);
        panel.add(campoId);
        panel.add(lblNome);
        panel.add(campoNome);
        panel.add(radioPanel);
        
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
        opcaoFrancionado.setSelected(true);
    }
    
    private boolean validarCampos() {
        if ("".equals(campoNome.getText())) {
            JOptionPane.showMessageDialog(TelaUnidadeMedida.this, "Você deve"
                    + " informar o nome da marca do produto",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
    
    public UnidadeMedida getUnidadeMedida() {
        return this.unidadeMedida;
    }
    
    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
        campoId.setText(unidadeMedida.getId().toString());
        campoNome.setText(unidadeMedida.getNome());
        
        opcaoFrancionado.setSelected(unidadeMedida.isFracionado());
        opcaoNaoFracionado.setSelected(!unidadeMedida.isFracionado());
    }
    
    private class ActionHandler implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == botaoSalvar) {
                if (validarCampos()) {
                    String sId = campoId.getText();
                    Boolean contemNumero = sId.matches("\\d+");
                    unidadeMedida.setId(contemNumero ? Long.parseLong(sId) : null);
                    unidadeMedida.setNome(campoNome.getText());
                    unidadeMedida.setFracionado(opcaoFrancionado.isSelected());
                    if (contemNumero) {
                        queries.updateUnidadeMedida(
                                unidadeMedida.getId(), 
                                unidadeMedida.getNome(),
                                unidadeMedida.getFracionado());
                    } else {
                        queries.addUnidadeMedida(
                                unidadeMedida.getNome(),
                                unidadeMedida.getFracionado());
                    }
                    limpar();
                    
                    JOptionPane.showMessageDialog(TelaUnidadeMedida.this, "Dados salvos com sucesso!",
                            "Informação", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
            } else if (ae.getSource() == botaoFechar) {
                dispose();
            }
        }
        
    }
    
}
