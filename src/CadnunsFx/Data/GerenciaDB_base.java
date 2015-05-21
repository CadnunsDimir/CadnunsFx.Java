/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CadnunsFx.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tiagop
 */
public class GerenciaDB_base {

    /**
     *
     */
    private String nomeColunas[];
    
    public GerenciaDB_base(String[] colunas){
        this.nomeColunas = colunas;
    }
    
    public void ConvertResultSetToJTableModel(JTable aTable, ResultSet rs) throws SQLException{
        //String[] tableColumnsName = {"col 1", "col 2", "col 3"}; 
        DefaultTableModel aModel = (DefaultTableModel) aTable.getModel();
        aModel.setColumnIdentifiers(nomeColunas); // query
        //ResultSet rs = statement.executeQuery("select col1, col2, col3 from minhatabela"); // Loop com o ResultSet transferindo os dados para o modelo
        java.sql.ResultSetMetaData rsmd = rs.getMetaData();
        int colNo = rsmd.getColumnCount();
        while(rs.next()){
            Object[] objects = new Object[colNo];
            for(int i = 0; i < colNo; i++) {
                objects[i] = rs.getObject(i+1);
            }
            aModel.addRow(objects);
        }
        aTable.setModel(aModel);
    }
    
}
