/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.database;

import br.edu.ifms.estoque.model.Marca;
import br.edu.ifms.estoque.queries.MarcaQueries;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 1513003
 */
public class MarcaResultSetTableModel extends AbstractTableModel {
    
    private MarcaQueries queries;
    private List<Marca> lista;
    private String[] colunas = {"Id", "Nome"};

    public MarcaResultSetTableModel() {
        queries = new MarcaQueries();
        lista = queries.getAllMarcas();
    }

    public void atualizaTabela() {
        lista.clear();
        lista.addAll(queries.getAllMarcas());
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
        queries.close();
    }
    
}
