/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.database;

import br.edu.ifms.estoque.model.Produto;
import br.edu.ifms.estoque.queries.ProdutoQueries;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 1513003
 */
public class ProdutoResultSetTableModel extends AbstractTableModel {

    private ProdutoQueries queries;
    private List<Produto> lista;
    private String[] colunas = {"Id", "Nome", "Preço", "Estoque", "Marca", "Unid. Medida", "Grupo de Produto"};

    public ProdutoResultSetTableModel() {
        queries = new ProdutoQueries();
        lista = queries.getAllProdutos();
    }

    public void atualizaTabela() {
        lista.clear();
        lista.addAll(queries.getAllProdutos());
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
        DecimalFormat df = new DecimalFormat(
                "#,##0.00",
                new DecimalFormatSymbols(new Locale("pt", "BR")));
        
        Produto obj = lista.get(row);
        switch (col) {
            case 0:
                return obj.getId();
            case 1:
                return obj.getNome();
            case 2:
                return df.format(obj.getPreco().doubleValue());
            case 3:
                return df.format(obj.getEstoque().doubleValue());
            case 4:
                return obj.getMarca().getNome();
            case 5:
                return obj.getUnidadeMedida().getNome();
            case 6:
                return obj.getGrupoProduto().getNome();
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
        switch (columnIndex) {
            case 0:
                return Integer.class;
            default:
                return String.class;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    public void disconnect() {
        queries.close();
    }

}
