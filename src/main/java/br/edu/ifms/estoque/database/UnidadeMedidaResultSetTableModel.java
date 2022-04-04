/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.database;

import br.edu.ifms.estoque.model.UnidadeMedida;
import br.edu.ifms.estoque.queries.UnidadeMedidaQueries;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 1513003
 */
public class UnidadeMedidaResultSetTableModel extends AbstractTableModel {
    
    private UnidadeMedidaQueries queries;
    private List<UnidadeMedida> lista;
    private String[] colunas = {"Id", "Nome", "Fracionado"};

    public UnidadeMedidaResultSetTableModel() {
        queries = new UnidadeMedidaQueries();
        lista = queries.getAllUnidadeMedidas();
    }

    public void atualizaTabela() {
        lista.clear();
        lista.addAll(queries.getAllUnidadeMedidas());
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
        UnidadeMedida obj = lista.get(row);
        switch(col) {
            case 0: return obj.getId();
            case 1: return obj.getNome();
            case 2: return obj.isFracionado() ? "Sim" : "Não";
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
            case 1: 
            case 2: return String.class;
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
