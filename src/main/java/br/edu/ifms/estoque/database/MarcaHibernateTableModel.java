/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.database;

import br.edu.ifms.estoque.dao.MarcaDaoImpl;
import br.edu.ifms.estoque.dao.IMarcaDao;
import br.edu.ifms.estoque.model.Marca;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author professor
 */
public class MarcaHibernateTableModel extends AbstractTableModel {
    
    private IMarcaDao dao;
    private List<Marca> lista;
    private String[] colunas = {"Id", "Nome"};
    
    public MarcaHibernateTableModel() {
        dao = new MarcaDaoImpl();
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
        Marca obj = lista.get(row);
        switch (col) {
            case 0: return obj.getId();
            case 1: return obj.getNome();
            default:
                return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        
        if (columnIndex == 0) {
            return Long.class;
        }
        return String.class;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
    
    
}
