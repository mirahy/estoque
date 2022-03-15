/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.facade;

import br.edu.ifms.estoque.dao.ClienteDao;
import br.edu.ifms.estoque.dao.IClienteDao;
import br.edu.ifms.estoque.model.Cliente;
import br.edu.ifms.estoque.view.TelaFormCliente;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author professor
 */
public class ClienteFacade {

    private IClienteDao dao;

    public ClienteFacade(IClienteDao dao) {
        this.dao = dao;
    }

    public ClienteFacade() {
        this(new ClienteDao());
    }

    public TelaFormCliente abrirFormulario(JFrame frame, ClienteFacade facade) {
        TelaFormCliente dialog = new TelaFormCliente(frame, true, facade);
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

        });
        return dialog;
    }

    public TelaFormCliente editarFormulario(
            JFrame frame,
            ClienteFacade facade,
            Long id) {
        TelaFormCliente dialog = abrirFormulario(frame, facade);
        dialog.setId(id);
        return dialog;
    }

    public void carregarDados(
            Long id,
            JTextField txtId,
            JTextField txtNome,
            JTextField txtTelefone,
            JTextField txtEmail,
            JTextField txtCpf) {
        Cliente c = dao.buscarPorId(id);
        txtId.setText(c.getId().toString());
        txtNome.setText(c.getNome());
        txtTelefone.setText(c.getTelefone());
        txtEmail.setText(c.getEmail());
        txtCpf.setText(c.getCpf());
    }

    public Boolean salvar(
            JTextField txtId,
            JTextField txtNome,
            JTextField txtTelefone,
            JTextField txtEmail,
            JTextField txtCpf
    ) {
        boolean isId = txtId.getText().matches("\\d+");
        Long id = isId ? Long.parseLong(txtId.getText()) : null;

        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNome(txtNome.getText());
        cliente.setTelefone(txtTelefone.getText());
        cliente.setEmail(txtEmail.getText());
        cliente.setCpf(txtCpf.getText());

        if (!isId) {
            dao.inserir(cliente);
        } else {
            dao.alterar(cliente);
        }

        return Boolean.TRUE;
    }
    
    public Boolean excluir(JFrame frame, Long id) {
        if (JOptionPane.showConfirmDialog(frame, "Deseja excluir esse registro?",
                "Excluir", JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            
            dao.excluir(id);
            
            JOptionPane.showMessageDialog(frame, "Registro excluídocom sucesso!",
                    "Informação", JOptionPane.INFORMATION_MESSAGE);
        }
        return true;
    }

}
