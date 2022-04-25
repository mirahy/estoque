/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.facade;

import br.edu.ifms.estoque.dao.IMarcaDao;
import br.edu.ifms.estoque.dao.MarcaDaoImpl;
import br.edu.ifms.estoque.model.Marca;
import br.edu.ifms.estoque.view.TelaMarca;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author professor
 */
public class MarcaFacade {

    private IMarcaDao dao;

    public MarcaFacade(IMarcaDao dao) {
        this.dao = dao;
    }

    public MarcaFacade() {
        this(new MarcaDaoImpl());
    }

    public TelaMarca abrirFormulario(JFrame frame, MarcaFacade facade) {
        TelaMarca dialog = new TelaMarca(frame, true, facade);
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

        });
        return dialog;
    }

    public TelaMarca editarFormulario(
            JFrame frame,
            MarcaFacade facade,
            Long id) {
        TelaMarca dialog = abrirFormulario(frame, facade);
        dialog.setId(id);
        return dialog;
    }

    public void carregarDados(
            Long id,
            JTextField txtId,
            JTextField txtNome) {
        Marca c = dao.buscarPorId(id);
        txtId.setText(c.getId().toString());
        txtNome.setText(c.getNome());
    }

    public Boolean salvar(
            JTextField txtId,
            JTextField txtNome
    ) {
        boolean isId = txtId.getText().matches("\\d+");
        Long id = isId ? Long.parseLong(txtId.getText()) : null;

        Marca cliente = new Marca();
        cliente.setId(id);
        cliente.setNome(txtNome.getText());

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
            
            JOptionPane.showMessageDialog(frame, "Registro excluído com sucesso!",
                    "Informação", JOptionPane.INFORMATION_MESSAGE);
        }
        return true;
    }

}
