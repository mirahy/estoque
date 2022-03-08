/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.database;

import br.edu.ifms.estoque.dao.ClienteDao;
import br.edu.ifms.estoque.dao.IClienteDao;
import br.edu.ifms.estoque.model.Cliente;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author professor
 */
public class ClienteHibernateTableModel extends AbstractTableModel {
    
    private IClienteDao dao;
    private List<Cliente> lista;
    private String[] colunas = {"Id", "Nome", "Telefone", "E-mail"};
    
    public ClienteHibernateTableModel() {
        dao = new ClienteDao();
        lista = dao.listar();
    }
    
    public void refresh(String nome) {
        lista.clear();
        lista.addAll(dao.buscarPorNome(nome));
        fireTableStructureChanged();
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Cliente obj = lista.get(row);
        switch (col) {
            case 0: return obj.getId();
            case 1: return obj.getNome();
            case 2: return obj.getTelefone();
            case 3: return obj.getEmail();
            default:
                return "";
        }
    }
    
}
