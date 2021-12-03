/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.database;

import br.edu.ifms.estoque.dao.IMarcaDao;
import br.edu.ifms.estoque.factory.MarcaDaoFactory;
import br.edu.ifms.estoque.model.Marca;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 1513003
 */
public class MarcaResultSetTableModel extends AbstractTableModel {
    
    private IMarcaDao dao;
    private List<Marca> lista = new ArrayList();
    private String[] colunas = {"Id", "Nome"};

    public MarcaResultSetTableModel() {
        MarcaDaoFactory factory = new MarcaDaoFactory();
        dao = (IMarcaDao) factory.createObject();
        atualizaTabela();
    }

    public void atualizaTabela() {
        lista.clear();
        lista.addAll(dao.listar());
        // informa que uma nova consulta foi gerada, portanto deve atualizar os dados
        fireTableStructureChanged();
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        // retorna 0 em caso de falha
        return colunas.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Marca obj = lista.get(row);
        switch(col) {
            case 0: return obj.getId();
            case 1: return obj.getNome();
        }
        return "";
    }

    /**
     * Retorna a classe que representa a coluna informada
     * 
     * @param columnIndex
     * @return 
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex) {
            case 0: return Integer.class;
            case 1: return String.class;
        }
        // se ocorrer falhar, retorna o padrão Object
        return Object.class;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
    public void disconnect() {
        
    }
    
}
