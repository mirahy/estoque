/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.queries;

import br.edu.ifms.estoque.database.Conexao;
import br.edu.ifms.estoque.model.UnidadeMedida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author santos
 */
public class UnidadeMedidaQueries {

    private Conexao conexao;
    private PreparedStatement selectAllUnidadeMedidas;
    private PreparedStatement selectUnidadeMedidaByNome;
    private PreparedStatement selectUnidadeMedidaById;
    private PreparedStatement insertNovoUnidadeMedida;
    private PreparedStatement updateUnidadeMedida;
    private PreparedStatement deleteSelectedUnidadeMedida;

    public UnidadeMedidaQueries() {
        try {
            conexao = Conexao.getInstance();
            Connection conn = conexao.getConn();
            String selectSql = "SELECT id, nome, fracionado "
                    + "  FROM unidade_medida ";
            /**
             * Cria a consulta que seleciona todos os dados do grupo de produtos
             */
            selectAllUnidadeMedidas = conn.prepareStatement(selectSql);
            /**
             * Cria consulta que seleciona os grupos de produtos com o nome
             * informado
             */
            selectUnidadeMedidaByNome = conn.prepareStatement(
                    selectSql
                    + " WHERE nome LIKE ?");

            /**
             * Cria consulta que seleciona um grupo de produto pelo ID informado
             */
            selectUnidadeMedidaById = conn.prepareStatement(
                    selectSql
                    + " WHERE id = ?");

            /**
             * Cria a inserção que adiciona uma nova entrada no banco de dados
             */
            insertNovoUnidadeMedida = conn.prepareStatement("INSERT INTO unidade_medida (nome, fracionado) VALUES (?, ?)");

            /**
             * Cria a alteração no banco de dados
             */
            updateUnidadeMedida = conn.prepareStatement("UPDATE unidade_medida SET nome = ?, fracionado = ? WHERE id = ?");

            /**
             * Criar a SQL para exclusão dos dados
             */
            deleteSelectedUnidadeMedida = conn.prepareStatement("DELETE FROM unidade_medida WHERE id = ?");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    private UnidadeMedida converter(ResultSet rs) throws SQLException {
        UnidadeMedida unidadeMedida = new UnidadeMedida(
                rs.getLong("id"),
                rs.getString("nome"),
                rs.getBoolean("fracionado")
        );
        return unidadeMedida;
    }

    public List<UnidadeMedida> getAllUnidadeMedidas() {
        List<UnidadeMedida> results = null;
        ResultSet resultSet = null;

        try {
            resultSet = selectAllUnidadeMedidas.executeQuery();
            results = new ArrayList<UnidadeMedida>();
            while (resultSet.next()) {
                results.add(converter(resultSet));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return results;
    }

    public List<UnidadeMedida> getUnidadeMedidasPorNome(String nome) {
        List<UnidadeMedida> results = null;
        ResultSet resultSet = null;

        try {
            selectUnidadeMedidaByNome.setString(1, nome);
            resultSet = selectUnidadeMedidaByNome.executeQuery();
            results = new ArrayList<UnidadeMedida>();
            while (resultSet.next()) {
                results.add(converter(resultSet));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return results;
    }

    public UnidadeMedida getUnidadeMedidaPorId(Long id) {
        ResultSet resultSet = null;
        UnidadeMedida unidade_medida = null;
        try {
            selectUnidadeMedidaById.setLong(1, id);
            resultSet = selectUnidadeMedidaById.executeQuery();
            if (resultSet.next()) {
                unidade_medida = converter(resultSet);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return unidade_medida;
    }

    public int addUnidadeMedida(String nome, Boolean fracionado) {
        int result = 0;

        try {
            insertNovoUnidadeMedida.setString(1, nome);
            insertNovoUnidadeMedida.setBoolean(2, fracionado);
            result = insertNovoUnidadeMedida.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
        }
        return result;
    }

    public int updateUnidadeMedida(Long id, String nome, Boolean fracionado) {
        int result = 0;

        try {
            updateUnidadeMedida.setString(1, nome);
            updateUnidadeMedida.setBoolean(2, fracionado);
            updateUnidadeMedida.setLong(3, id);
            result = updateUnidadeMedida.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        return result;
    }

    public int deleteUnidadeMedida(Long id) {
        int result = 0;

        try {
            deleteSelectedUnidadeMedida.setLong(1, id);
            result = deleteSelectedUnidadeMedida.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public void close() {
        try {
            conexao.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
