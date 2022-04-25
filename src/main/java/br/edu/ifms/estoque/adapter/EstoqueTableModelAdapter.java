/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.adapter;

import br.edu.ifms.estoque.dao.IBaseDao;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author santos
 */
public abstract class EstoqueTableModelAdapter<T> extends AbstractTableModel {
    
    private IBaseDao dao;
    private List<T> lista;
    private final String[] colunas;
    
    public EstoqueTableModelAdapter(String[] colunas, IBaseDao dao) {
        this.colunas = colunas;
        this.dao = dao;
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
        T obj = lista.get(row);
        return obj;
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
