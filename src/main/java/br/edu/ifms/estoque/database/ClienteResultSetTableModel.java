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
 * @author santos
 */
public class ClienteResultSetTableModel extends AbstractTableModel {

    private IClienteDao queries;
    private List<Cliente> lista;
    private String[] colunas = {"Id", "Nome", "Telefone", "E-mail"};

    public ClienteResultSetTableModel() {
        queries = new ClienteDao();
        lista = queries.listar();
    }

    public void atualizaTabela(String nome) {
        lista.clear();
        if (nome != null && !nome.isBlank() && !nome.isEmpty()) {
            lista.addAll(queries.buscarPorNome(nome));
        } else {
            lista.addAll(queries.listar());
        }
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
        Cliente obj = lista.get(row);
        switch (col) {
            case 0:
                return obj.getId();
            case 1:
                return obj.getNome();
            case 2:
                return obj.getTelefone();
            case 3:
                return obj.getEmail();
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
                return Long.class;
            case 1:
            case 2:
            case 3:
                return String.class;
        }
        // se ocorrer falhar, retorna o padrão Object
        return Object.class;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

}
