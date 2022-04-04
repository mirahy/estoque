/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.queries;

import br.edu.ifms.estoque.database.Conexao;
import br.edu.ifms.estoque.model.GrupoProduto;
import br.edu.ifms.estoque.model.Marca;
import br.edu.ifms.estoque.model.Produto;
import br.edu.ifms.estoque.model.UnidadeMedida;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author santos
 */
public class ProdutoQueries {

    private static final String SELECT_PRODUTO = "SELECT p.id, p.nome, p.descricao, p.preco, \n"
            + "	   p.estoque, p.estoque_minimo, p.data_ultima_compra,\n"
            + "	   m.id AS marca_id, m.nome AS marca_nome,\n"
            + "	   um.id AS unidade_medida_id, um.nome AS unidade_medida_nome,\n"
            + "	   um.fracionado AS unidade_medida_fracionado,\n"
            + "	   gp.id AS grupo_produto_id, gp.nome AS grupo_produto_nome,\n"
            + "	   lgp.id AS subgrupo_id, lgp.nome AS subgrupo_nome\n"
            + "  FROM produto AS p\n"
            + " INNER JOIN marca AS m ON m.id = p.id_marca\n"
            + " INNER JOIN unidade_medida AS um ON um.id = p.id_unidade_medida\n"
            + " INNER JOIN grupo_produto AS gp ON gp.id = p.id_grupo_produto\n"
            + "  LEFT JOIN grupo_produto AS lgp ON lgp.id = gp.sub_grupo ";
    private Conexao conexao;

    public ProdutoQueries() {
        try {
            conexao = Conexao.getInstance();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    private Produto converter(ResultSet rs) throws SQLException {
        GrupoProduto subGrupo = new GrupoProduto(
                rs.getLong("subgrupo_id"),
                rs.getString("subgrupo_nome"), null);
        GrupoProduto grupoProduto = new GrupoProduto(
                rs.getLong("grupo_produto_id"),
                rs.getString("grupo_produto_nome"),
                subGrupo);
        Marca marca = new Marca(
                rs.getLong("marca_id"),
                rs.getString("marca_nome"));
        UnidadeMedida unidadeMedida = new UnidadeMedida(
                rs.getLong("unidade_medida_id"),
                rs.getString("unidade_medida_nome"),
                rs.getBoolean("unidade_medida_fracionado")
        );

        Produto produto = new Produto(
                rs.getLong("id"),
                rs.getString("nome"),
                rs.getString("descricao"),
                rs.getBigDecimal("preco"),
                rs.getBigDecimal("estoque"),
                rs.getBigDecimal("estoque_minimo"),
                rs.getTimestamp("data_ultima_compra").toLocalDateTime(),
                grupoProduto,
                marca,
                unidadeMedida);
        return produto;
    }

    public List<Produto> getProdutosPor(String nome, Marca marca, GrupoProduto grupoProduto) {
        List<Produto> results = null;
        ResultSet resultSet = null;
        Boolean hasNome = nome != null && !nome.isBlank() && !nome.isEmpty(),
                hasMarca = marca != null,
                hasGrupoProduto = grupoProduto != null;
        PreparedStatement select = null;

        try {
            StringBuilder sb = new StringBuilder();
            sb.append(SELECT_PRODUTO + " WHERE p.id >= 0");

            if (hasNome && hasMarca && hasGrupoProduto) {
                sb.append(" AND p.nome LIKE ? ");
                sb.append(" AND m.id LIKE ? ");
                sb.append(" AND gp.id LIKE ? ");
                select = conexao.getConn().prepareStatement(sb.toString());
                select.setString(1, nome);
                select.setLong(2, marca.getId());
                select.setLong(3, grupoProduto.getId());
            } else if (hasNome && hasMarca && !hasGrupoProduto) {
                sb.append(" AND p.nome LIKE ? ");
                sb.append(" AND m.id LIKE ? ");
                select = conexao.getConn().prepareStatement(sb.toString());
                select.setString(1, nome);
                select.setLong(2, marca.getId());
            } else if (hasNome && !hasMarca && hasGrupoProduto) {
                sb.append(" AND p.nome LIKE ? ");
                sb.append(" AND gp.id LIKE ? ");
                select = conexao.getConn().prepareStatement(sb.toString());
                select.setString(1, nome);
                select.setLong(2, grupoProduto.getId());
            } else if (!hasNome && hasMarca && hasGrupoProduto) {
                sb.append(" AND m.id LIKE ? ");
                sb.append(" AND gp.id LIKE ? ");
                select = conexao.getConn().prepareStatement(sb.toString());
                select.setLong(1, marca.getId());
                select.setLong(2, grupoProduto.getId());
            } else if (hasNome && !hasMarca && !hasGrupoProduto) {
                sb.append(" AND p.nome LIKE ? ");
                select = conexao.getConn().prepareStatement(sb.toString());
                select.setString(1, nome);
            } else if (!hasNome && !hasMarca && hasGrupoProduto) {
                sb.append(" AND gp.id LIKE ? ");
                select = conexao.getConn().prepareStatement(sb.toString());
                select.setLong(1, grupoProduto.getId());
            } else if (!hasNome && hasMarca && !hasGrupoProduto) {
                sb.append(" AND m.id LIKE ? ");
                select = conexao.getConn().prepareStatement(sb.toString());
                select.setLong(1, marca.getId());
            } else {
                select = conexao.getConn().prepareStatement(sb.toString());
            }

            resultSet = select.executeQuery();
            results = new ArrayList<Produto>();
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

    public List<Produto> getAllProdutos() {
        return getProdutosPor(null, null, null);
    }

    public List<Produto> getProdutosPorNome(String nome) {
        return getProdutosPor(nome, null, null);
    }

    public Produto getProdutoPorId(Long id) {
        ResultSet resultSet = null;
        Produto produto = null;
        try {
            PreparedStatement select = conexao.getConn().prepareStatement(
                    SELECT_PRODUTO + " WHERE p.id = ?");
            select.setLong(1, id);
            resultSet = select.executeQuery();
            if (resultSet.next()) {
                produto = converter(resultSet);
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
        return produto;
    }

    public int addProduto(String nome, String descricao, BigDecimal preco,
            BigDecimal estoque, BigDecimal estoqueMinimo,
            LocalDateTime dataUltimaCompra, GrupoProduto grupoProduto,
            Marca marca, UnidadeMedida unidadeMedida) {
        int result = 0;

        try {
            PreparedStatement insert = conexao.getConn().prepareStatement(
                    "INSERT INTO produto\n"
                    + "(nome, descricao, preco, estoque, estoque_minimo,\n"
                    + "data_ultima_compra, id_marca, id_unidade_medida, id_grupo_roduto)\n"
                    + "VALUES (?,?,?,?,?,?,?,?,?)"
            );
            insert.setString(1, nome);
            insert.setString(2, descricao);
            insert.setBigDecimal(3, preco);
            insert.setBigDecimal(4, estoque);
            insert.setBigDecimal(5, estoqueMinimo);
            insert.setTimestamp(6, java.sql.Timestamp.valueOf(dataUltimaCompra));
            insert.setLong(7, grupoProduto.getId());
            insert.setLong(8, marca.getId());
            insert.setLong(9, unidadeMedida.getId());
            result = insert.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
        }
        return result;
    }

    public int updateProduto(Long id, String nome, String descricao, BigDecimal preco,
            BigDecimal estoque, BigDecimal estoqueMinimo,
            LocalDateTime dataUltimaCompra, GrupoProduto grupoProduto,
            Marca marca, UnidadeMedida unidadeMedida) {
        int result = 0;

        try {
            PreparedStatement update = conexao.getConn().prepareStatement(
                    "UPDATE produto SET\n"
                    + "nome = ?, descricao = ?, preco = ?, estoque = ?, estoque_minimo = ?,\n"
                    + "data_ultima_compra = ?, id_marca = ?, id_unidade_medida = ?, id_grupo_roduto = ?\n"
                    + "WHERE id = ?"
            );
            update.setString(1, nome);
            update.setString(2, descricao);
            update.setBigDecimal(3, preco);
            update.setBigDecimal(4, estoque);
            update.setBigDecimal(5, estoqueMinimo);
            update.setTimestamp(6, java.sql.Timestamp.valueOf(dataUltimaCompra));
            update.setLong(7, grupoProduto.getId());
            update.setLong(8, marca.getId());
            update.setLong(9, unidadeMedida.getId());
            update.setLong(10, id);
            result = update.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public int deleteProduto(Long id) {
        int result = 0;

        try {
            PreparedStatement delete = conexao.getConn().prepareStatement(
                    "DELETE FROM produto\n"
                    + "WHERE id = ?"
            );
            delete.setLong(1, id);
            result = delete.executeUpdate();
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
