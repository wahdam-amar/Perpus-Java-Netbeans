


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
*/

import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class DB {

    // Menyiapkan paramter JDBC untuk koneksi ke datbase
    static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static String NAME = "perpus";
    static String DB_URL = "jdbc:mysql://localhost/"+NAME;
    static String USER = "root";
    static String PASS = "";
    
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    

    public static Connection getConnection() {
        Connection connection = null;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/perpus"; //ganti dengan database mu
        String user = "root";
        String password = "";
        if (connection == null) {
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException | SQLException error) {
               System.exit(0);
            }

        }
        return connection;
    }
    
    public static void insert(String kategori, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DB() {
            try {
            // register driver yang akan dipakai
            Class.forName(JDBC_DRIVER);
            
            // buat koneksi ke database
            conn = DriverManager.getConnection(DB_URL, USER, PASS);            
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.print(e);
        }  
    }

    public static void setNAME(String NAME) {
        DB.NAME = NAME;
    }

    public static void setDB_URL(String DB_URL) {
        DB.DB_URL = DB_URL;
    }

    public static void setUSER(String USER) {
        DB.USER = USER;
    }

    public static void setPASS(String PASS) {
        DB.PASS = PASS;
    }
    
    public static void closeConn(){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void start() {        
            try {
            // register driver yang akan dipakai
            Class.forName(JDBC_DRIVER);
            
            // buat koneksi ke database
            conn = DriverManager.getConnection(DB_URL, USER, PASS);            
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.print(e);
        }
    }

    public static DefaultTableModel buildTableModel(ResultSet rs)
        throws SQLException {

    ResultSetMetaData metaData = rs.getMetaData();

    // names of columns
    Vector<String> columnNames = new Vector<String>();
    int columnCount = metaData.getColumnCount();
    for (int column = 1; column <= columnCount; column++) {
        columnNames.add(metaData.getColumnName(column));
    }

    // data of the table
    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
    while (rs.next()) {
        Vector<Object> vector = new Vector<Object>();
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
            vector.add(rs.getObject(columnIndex));
        }
        data.add(vector);
    }
    DefaultTableModel model = new DefaultTableModel(data, columnNames);
    model.setColumnCount(columnCount);
    return model;

}
    public static void ChangeName(JTable table,String[] judul){
    for(int i = 0;i<judul.length;i++){
        table.getColumnModel().getColumn(i).setHeaderValue(judul[i]);
    }
    
}
    
    //select statement
    public static ResultSet select(String tableName,String colName){
        ResultSet rsSelect = null;
        try {
            Statement stmtSelect = conn.createStatement();
            rsSelect = stmtSelect.executeQuery("SELECT "+colName+" FROM "+tableName);            
            
            return rsSelect;
            
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rsSelect;
    }

    public static ResultSet select(String tableName,String colName,String whereClause){
        ResultSet rsSelect = null;
        try {
            Statement stmtSelect = conn.createStatement();
            rsSelect = stmtSelect.executeQuery("SELECT "+colName+" FROM "+tableName+" where "+whereClause);            
            return rsSelect;
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rsSelect;
       
    }
    public static ResultSet select(String query){
        ResultSet rsSelect = null;
        try {
            Statement stmtSelect = conn.createStatement();
            rsSelect = stmtSelect.executeQuery(query);            
            
            return rsSelect;
            
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rsSelect;
    }

    /**
     *
     * @param tableName
     * @return
     */
    public static ResultSet all(String tableName){
        ResultSet rsSelect = null;
        try {
            Statement stmtSelect = conn.createStatement();
            rsSelect = stmtSelect.executeQuery("SELECT * FROM "+tableName);            
            //Retrieving the ResultSetMetaData object
            ResultSetMetaData rsmd = rsSelect.getMetaData();
                return rsSelect;
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return rsSelect;
    }
    
    public static ResultSet all(String tableName,String whereClause){
       ResultSet rsSelect = null;
        try {
            Statement stmtSelect = conn.createStatement();
            rsSelect = stmtSelect.executeQuery("SELECT * FROM "+tableName+" where "+whereClause);            
            //Retrieving the ResultSetMetaData object
            ResultSetMetaData rsmd = rsSelect.getMetaData();
            //getting the column type
            int column_count = rsmd.getColumnCount();            
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rsSelect;
    }
    
    //update statement
    public static void update(String query){
        try {
            Statement stmtSelect = conn.createStatement();
            stmtSelect.execute(query);            
      
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //insert statement
    public static void insert(String tableName,String[] inputValue){
        try {
            Statement stmtSelect = conn.createStatement();
            StringBuilder str = new StringBuilder();
            str.append("insert into ");
            str.append(tableName).append(" values(LAST_INSERT_ID(),");
            int index = 1;
            for (String i : inputValue) {  
              if(index==inputValue.length){
              str.append("'").append(i).append("'");    
              }else{  
              str.append("'").append(i).append("',");
              }
              index++;
            }
            str.append(");");
            System.out.print(str.toString());
            stmtSelect.execute(str.toString(), Statement.RETURN_GENERATED_KEYS);                       
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    public static void insert(String query){
        try {
            Statement stmtSelect = conn.createStatement();
            stmtSelect.execute(query, Statement.RETURN_GENERATED_KEYS);                       
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //delete statement
    public static boolean delete(String tableName,String whereClause){
        boolean execute = false;
        try {
            Statement stmtSelect = conn.createStatement();
            execute = stmtSelect.execute("DELETE FROM "+tableName+" where "+whereClause);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return execute;
    }    
    
        public static void delete(String query){
        try {
            Statement stmtSelect = conn.createStatement();
            stmtSelect.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public static void main(String args[]) throws SQLException {
        setNAME("");
    start();    

    all("admin");
    
//    select("admin","nama","id!=1");
//    
//    update("admin","nama", "wahdam", "id=2");
//
   rs = all("admin");
   buildTableModel(rs);
   
//    

//        
//    delete("admin", "id=2");    
        
    }
}
