/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CadnunsFx.Data;

/**
 *
 * @author Tiago Silva do Nascimento
 */

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.SQLException;

public class MySqlConn {
    
    private boolean statusConn = false;
    private Connection conn = null;
    private String host;
    private String db;
    private String user;
    private String pswrd;
    
    public MySqlConn(String hostname, String db, String usuario, String senha) throws ClassNotFoundException, SQLException{
        
        String driverName = "com.mysql.jdbc.Driver";
        Class.forName(driverName);
        this.host = hostname;
        this.db = db;
        this.user = usuario;
        this.pswrd = senha;
        
        this.TryConn();
    }
    
    public void TryConn() throws SQLException{
        if(!isConnected()){
            String connString = "jdbc:mysql://"+host+"/"+db;
            conn = DriverManager.getConnection(connString, user, pswrd);
        
            if(conn != null){
                this.statusConn = true;
            }   
        }
    }
    
    public void CloseConn() throws SQLException{
        conn.close();
        this.statusConn = false;
    }
    
    public void RebootConn() throws SQLException{
        this.CloseConn();
        this.TryConn();
    }
    
    public ResultSet GetData(String query) throws SQLException{
        if(isConnected()){
            return conn.prepareStatement(query).executeQuery();
        }else
            return null;
    }
    
    public void Command(String command) throws SQLException{
        if(isConnected()){
            conn.prepareStatement(command).execute();
        }
    }
    
    public Object UniqueResult(String command) throws SQLException{
        ResultSet rs = GetData(command);
        rs.next();
        return rs.getObject(1);
    }

    /**
     * @return the statusConn
     */
    public boolean isConnected() {
        return statusConn;
    }

    /**
     * @return the conn
     */
    public Connection getConn() {
        return conn;
    }

    /**
     * @param conn the conn to set
     */
    public void setConn(Connection conn) {
        this.conn = conn;
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the db
     */
    public String getDb() {
        return db;
    }

    /**
     * @param db the db to set
     */
    public void setDb(String db) {
        this.db = db;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the pswrd
     */
    public String getPswrd() {
        return pswrd;
    }

    /**
     * @param pswrd the pswrd to set
     */
    public void setPswrd(String pswrd) {
        this.pswrd = pswrd;
    }
    
    
    
}
