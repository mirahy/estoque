/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.database;

import java.sql.SQLException;

/**
 *
 * @author 1513003
 */
public class MarcaResultSetTableModel extends ResultSetTableModel {
    
    private static final String QUERY = "SELECT id, nome FROM marca";
    
    public MarcaResultSetTableModel() throws SQLException {
        super(QUERY);
    }
    
    public void reload() throws SQLException {
        setQuery(QUERY);
    }
    
}
