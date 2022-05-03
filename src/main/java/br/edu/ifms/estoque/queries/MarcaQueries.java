/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.queries;

import br.edu.ifms.estoque.database.Conexao;
import br.edu.ifms.estoque.model.Marca;
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
public class MarcaQueries {

    private Conexao conexao;
    private PreparedStatement selectAllMarcas;
    private PreparedStatement selectMarcaByNome;
    private PreparedStatement selectMarcaById;
    private PreparedStatement insertNovoMarca;
    private PreparedStatement updateMarca;
    private PreparedStatement deleteSelectedMarca;

    public MarcaQueries() {
        try {
            conexao = Conexao.getInstance();
            Connection conn = conexao.getConn();
            /**
             * Cria a consulta que seleciona todos os dados do grupo de produtos
             */
            selectAllMarcas = conn.prepareStatement(
                    "SELECT id, nome "
                    + "  FROM marca ");
            /**
             * Cria consulta que seleciona os grupos de produtos com o nome
             * informado
             */
            selectMarcaByNome = conn.prepareStatement(
                    "SELECT id, nome "
                    + "  FROM marca "
                    + " WHERE nome LIKE ?");

            /**
             * Cria consulta que seleciona um grupo de produto pelo ID informado
             */
            selectMarcaById = conn.prepareStatement(
                    "SELECT id, nome "
                    + "  FROM marca "
                    + " WHERE id = ?");

            /**
             * Cria a inserção que adiciona uma nova entrada no banco de dados
             */
            insertNovoMarca = conn.prepareStatement("INSERT INTO marca (nome) VALUES (?)");

            /**
             * Cria a alteração no banco de dados
             */
            updateMarca = conn.prepareStatement("UPDATE marca SET nome = ? WHERE id = ?");

            /**
             * Criar a SQL para exclusão dos dados
             */
            deleteSelectedMarca = conn.prepareStatement("DELETE FROM marca WHERE id = ?");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    private Marca converter(ResultSet rs) throws SQLException {
        Marca marca = new Marca.MarcaDiretor()
                .wihtId(rs.getLong("id"))
                .wihtNome(rs.getString("nome"))
                .construir();
        return marca;
    }

    public List<Marca> getAllMarcas() {
        List<Marca> results = null;
        ResultSet resultSet = null;

        try {
            resultSet = selectAllMarcas.executeQuery();
            results = new ArrayList<Marca>();
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

    public List<Marca> getMarcasPorNome(String nome) {
        List<Marca> results = null;
        ResultSet resultSet = null;

        try {
            selectMarcaByNome.setString(1, nome);
            resultSet = selectMarcaByNome.executeQuery();
            results = new ArrayList<Marca>();
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

    public Marca getMarcaPorId(Long id) {
        ResultSet resultSet = null;
        Marca marca = null;
        try {
            selectMarcaById.setLong(1, id);
            resultSet = selectMarcaById.executeQuery();
            if (resultSet.next()) {
                marca = converter(resultSet);
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
        return marca;
    }

    public int addMarca(String nome) {
        int result = 0;

        try {
            insertNovoMarca.setString(1, nome);
            result = insertNovoMarca.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
        }
        return result;
    }

    public int updateMarca(Long id, String nome) {
        int result = 0;

        try {
            updateMarca.setString(1, nome);
            updateMarca.setLong(2, id);
            result = updateMarca.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        return result;
    }

    public int deleteMarca(Long id) {
        int result = 0;

        try {
            deleteSelectedMarca.setLong(1, id);
            result = deleteSelectedMarca.executeUpdate();
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
