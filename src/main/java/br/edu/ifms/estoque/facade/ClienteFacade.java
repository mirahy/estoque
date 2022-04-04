/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.facade;

<<<<<<< HEAD
import br.edu.ifms.estoque.dao.IClienteDao;
import br.edu.ifms.estoque.model.Cliente;
import br.edu.ifms.estoque.view.TelaFormularioCliente;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
=======
import br.edu.ifms.estoque.dao.ClienteDao;
import br.edu.ifms.estoque.dao.IClienteDao;
import br.edu.ifms.estoque.model.Cliente;
import br.edu.ifms.estoque.view.TelaFormCliente;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
>>>>>>> 245f21c7f8360abe5cb99d82c446b8f18786d626
import javax.swing.JTextField;

/**
 *
<<<<<<< HEAD
 * @author santos
 */
public class ClienteFacade {

    private Cliente cliente;
=======
 * @author professor
 */
public class ClienteFacade {

>>>>>>> 245f21c7f8360abe5cb99d82c446b8f18786d626
    private IClienteDao dao;

    public ClienteFacade(IClienteDao dao) {
        this.dao = dao;
    }

<<<<<<< HEAD
    private void criarCliente(
=======
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
>>>>>>> 245f21c7f8360abe5cb99d82c446b8f18786d626
            JTextField txtId,
            JTextField txtNome,
            JTextField txtTelefone,
            JTextField txtEmail,
<<<<<<< HEAD
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
=======
            JTextField txtCpf) {
        Cliente c = dao.buscarPorId(id);
        txtId.setText(c.getId().toString());
        txtNome.setText(c.getNome());
        txtTelefone.setText(c.getTelefone());
        txtEmail.setText(c.getEmail());
        txtCpf.setText(c.getCpf());
>>>>>>> 245f21c7f8360abe5cb99d82c446b8f18786d626
    }

    public Boolean salvar(
            JTextField txtId,
            JTextField txtNome,
            JTextField txtTelefone,
            JTextField txtEmail,
<<<<<<< HEAD
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
=======
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
>>>>>>> 245f21c7f8360abe5cb99d82c446b8f18786d626
    }

}
