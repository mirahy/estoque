/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.queries;

import br.edu.ifms.estoque.database.Conexao;
import br.edu.ifms.estoque.model.GrupoProduto;
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
public class GrupoProdutoQueries {

    private Conexao conexao;
    private PreparedStatement selectAllGrupos;
    private PreparedStatement selectGrupoByNome;
    private PreparedStatement selectGrupoById;
    private PreparedStatement insertNovoGrupo;
    private PreparedStatement insertNovoGrupoSemSubgrupo;
    private PreparedStatement deleteSelectedGrupo;

    public GrupoProdutoQueries() {
        try {
            conexao = Conexao.getInstance();
            Connection conn = conexao.getConn();
            /**
             * Cria a consulta que seleciona todos os dados do grupo de produtos
             */
            selectAllGrupos = conn.prepareStatement(
                    "SELECT gp.id, gp.nome, lgp.id as subgrupo_id, lgp.nome as subgrupo_nome "
                    + "  FROM grupo_produto AS gp"
                    + "  LEFT JOIN grupo_produto AS lgp"
                    + "    ON gp.sub_grupo = lgp.id");
            /**
             * Cria consulta que seleciona os grupos de produtos com o nome
             * informado
             */
            selectGrupoByNome = conn.prepareStatement(
                    "SELECT gp.id, gp.nome, lgp.id as subgrupo_id, lgp.nome as subgrupo_nome "
                    + "  FROM grupo_produto AS gp"
                    + "  LEFT JOIN grupo_produto AS lgp ON gp.sub_grupo = lgp.id"
                    + " WHERE gp.nome LIKE ?");

            /**
             * Cria consulta que seleciona um grupo de produto pelo ID informado
             */
            selectGrupoById = conn.prepareStatement(
                    "SELECT gp.id, gp.nome, lgp.id as subgrupo_id, lgp.nome as subgrupo_nome "
                    + "  FROM grupo_produto AS gp"
                    + "  LEFT JOIN grupo_produto AS lgp ON gp.sub_grupo = lgp.id"
                    + " WHERE gp.id = ?");

            /**
             * Cria a inserção que adiciona uma nova entrada no banco de dados
             */
            insertNovoGrupo = conn.prepareStatement("INSERT INTO grupo_produto (nome, sub_grupo) VALUES (?, ?)");
            insertNovoGrupoSemSubgrupo = conn.prepareStatement("INSERT INTO grupo_produto (nome) VALUES (?)");

            /**
             * Criar a SQL para exclusão dos dados
             */
            deleteSelectedGrupo = conn.prepareStatement("DELETE FROM grupo_produto WHERE id = ?");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public List<GrupoProduto> getAllGrupos() {
        List<GrupoProduto> results = null;
        ResultSet resultSet = null;

        try {
            resultSet = selectAllGrupos.executeQuery();
            results = new ArrayList<GrupoProduto>();
            while (resultSet.next()) {
                GrupoProduto subGrupo = new GrupoProduto(
                        resultSet.getLong("subgrupo_id"),
                        resultSet.getString("subgrupo_nome"), null);

                results.add(new GrupoProduto(
                        resultSet.getLong("id"),
                        resultSet.getString("nome"),
                        subGrupo
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
//                close();
            }
        }
        return results;
    }

    public List<GrupoProduto> getGruposPorNome(String nome) {
        List<GrupoProduto> results = null;
        ResultSet resultSet = null;

        try {
            selectGrupoByNome.setString(1, nome);
            resultSet = selectGrupoByNome.executeQuery();
            results = new ArrayList<GrupoProduto>();
            while (resultSet.next()) {
                GrupoProduto subGrupo = new GrupoProduto(
                        resultSet.getLong("subgrupo_id"),
                        resultSet.getString("subgrupo_nome"), null);

                results.add(new GrupoProduto(
                        resultSet.getLong("id"),
                        resultSet.getString("nome"),
                        subGrupo
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
//                close();
            }
        }
        return results;
    }

    public GrupoProduto getGrupoPorId(Long id) {
        ResultSet resultSet = null;
        GrupoProduto grupo = null;
        try {
            selectGrupoById.setLong(1, id);
            resultSet = selectGrupoById.executeQuery();
            if (resultSet.next()) {
                GrupoProduto subGrupo = new GrupoProduto(
                        resultSet.getLong("subgrupo_id"),
                        resultSet.getString("subgrupo_nome"), null);

                grupo = new GrupoProduto(
                        resultSet.getLong("id"),
                        resultSet.getString("nome"),
                        subGrupo
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
//                close();
            }
        }
        return grupo;
    }

    public int addGrupoProduto(String nome, GrupoProduto subGrupo) {
        int result = 0;

        try {
            if (subGrupo == null) {
                insertNovoGrupoSemSubgrupo.setString(1, nome);
                result = insertNovoGrupoSemSubgrupo.executeUpdate();
            } else {
                insertNovoGrupo.setString(1, nome);
                insertNovoGrupo.setLong(2, subGrupo.getId());

                result = insertNovoGrupo.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
//            close();
        }
        return result;
    }

    public int deleteGrupoProduto(Long id) {
        int result = 0;

        try {
            deleteSelectedGrupo.setLong(1, id);
            result = deleteSelectedGrupo.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
//            close();
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
