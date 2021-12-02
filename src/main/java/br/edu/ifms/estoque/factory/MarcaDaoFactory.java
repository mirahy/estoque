/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.factory;

import br.edu.ifms.estoque.dao.MarcaDaoImpl;
import br.edu.ifms.estoque.database.Conexao;
import java.sql.SQLException;

/**
 *
 * @author nicholas.santos
 */
public class MarcaDaoFactory implements IDaoFactory {

    @Override
    public Object createObject(String sgbd) {
        if ("postgres".equals(sgbd)) {
            try {
                return new MarcaDaoImpl(Conexao.getInstance());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
    
}
