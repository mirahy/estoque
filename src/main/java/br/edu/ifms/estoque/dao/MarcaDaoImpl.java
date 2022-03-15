/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.dao;

import br.edu.ifms.estoque.database.Conexao;
import br.edu.ifms.estoque.model.Marca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nicholas.santos
 */
public class MarcaDaoImpl implements IMarcaDao {

    private static final String MARCA_SQL = "SELECT id, nome FROM marca";

    private Conexao conexao;
    private Connection conn;

    public MarcaDaoImpl(Conexao conexao) {
        this.conexao = conexao;
        this.conn = conexao.getConn();
    }

    @Override
    public Marca buscarPorId(Long id) {
        ResultSet rs = null;
        Marca marca = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(MARCA_SQL
                    + " WHERE id = ? ");
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                marca = new Marca();
                marca.setId(rs.getLong(1));
                marca.setNome(rs.getString(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return marca;
    }

    @Override
    public List buscarPorNome(String nome) {
        ResultSet rs = null;
        List<Marca> resultList = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(MARCA_SQL
                    + " WHERE nome LIKE ? ");
            stmt.setString(1, nome);
            rs = stmt.executeQuery();
            resultList = new ArrayList();
            while (rs.next()) {
                Marca marca = new Marca();
                marca.setId(rs.getLong(1));
                marca.setNome(rs.getString(2));
                resultList.add(marca);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return resultList;
    }

    @Override
    public Object inserir(Object object) {
        Marca marca = (Marca) object;
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO marca (nome) VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            stmt.setString(1, marca.getNome());
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating Marca failed, no rows affected.");
            }
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    marca.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating Marca failed, no ID obtained.");
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return marca;
    }

    @Override
    public Object alterar(Object object) {
        Marca marca = (Marca) object;
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    " UPDATE marca SET nome = ? WHERE id = ? "
            );
            stmt.setString(1, marca.getNome());
            stmt.setLong(2, marca.getId());
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating Marca failed, no rows affected.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return marca;
    }

    @Override
    public void excluir(Object object) {
        Marca marca = (Marca) object;
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    " DELETE FROM marca WHERE id = ? "
            );
            stmt.setLong(1, marca.getId());
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting Marca failed, no rows affected.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List listar() {
        ResultSet rs = null;
        List<Marca> resultList = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(MARCA_SQL);
            rs = stmt.executeQuery();
            resultList = new ArrayList();
            while (rs.next()) {
                Marca marca = new Marca();
                marca.setId(rs.getLong(1));
                marca.setNome(rs.getString(2));
                resultList.add(marca);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return resultList;
    }

    @Override
    public Object buscarPorId(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
