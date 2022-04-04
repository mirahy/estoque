/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.facade;

import br.edu.ifms.estoque.dao.IClienteDao;
import br.edu.ifms.estoque.model.Cliente;
import br.edu.ifms.estoque.view.TelaFormularioCliente;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author santos
 */
public class ClienteFacade {

    private Cliente cliente;
    private IClienteDao dao;

    public ClienteFacade(IClienteDao dao) {
        this.dao = dao;
    }

    private void criarCliente(
            JTextField txtId,
            JTextField txtNome,
            JTextField txtTelefone,
            JTextField txtEmail,
            JFormattedTextField txtCpf
    ) {
        if (txtId.getText().matches("\\d+")) {
            Long id = Long.parseLong(txtId.getText());
            cliente = dao.buscarPorId(id);
        } else {
            cliente = new Cliente();
        }
        cliente.setNome(txtNome.getText());
        cliente.setTelefone(txtTelefone.getText());
        cliente.setEmail(txtEmail.getText());
        cliente.setCpf(txtCpf.getText());
    }

    public Boolean salvar(
            JTextField txtId,
            JTextField txtNome,
            JTextField txtTelefone,
            JTextField txtEmail,
            JFormattedTextField txtCpf
    ) {
        criarCliente(txtId, txtNome, txtTelefone, txtEmail, txtCpf);
        if (cliente.isSetId()) {
            dao.alterar(cliente);
        } else {
            dao.inserir(cliente);
        }
        return Boolean.TRUE;
    }

    public void carregarCampos(
            Long id,
            JTextField txtId,
            JTextField txtNome,
            JTextField txtTelefone,
            JTextField txtEmail,
            JFormattedTextField txtCpf
    ) {
        cliente = dao.buscarPorId(id);
        txtId.setText(cliente.getId().toString());
        txtNome.setText(cliente.getNome());
        txtTelefone.setText(cliente.getTelefone());
        txtEmail.setText(cliente.getEmail());
        txtCpf.setValue(cliente.getCpf());
    }

    public TelaFormularioCliente abrirFormulario(JFrame frame, ClienteFacade facade) {
        TelaFormularioCliente dialog = new TelaFormularioCliente(frame, true, facade);
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });
        return dialog;
    }
    
    public void excluir(Long id) {
        dao.excluir(id);
    }

}
