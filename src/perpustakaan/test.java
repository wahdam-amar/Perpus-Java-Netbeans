/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpustakaan;

/**
 *
 * @author User
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import perpustakaan.DB;
import static perpustakaan.DB.buildTableModel;
import static perpustakaan.DB.rs;

public class test {

    public static void main(String[] args) throws SQLException {

        DB.start();
        ResultSet rs=DB.select("admin","*","id=1");
        //ResultSet rs=DB.all("admin");
//        System.out.print();
    JTable table = new JTable(buildTableModel(rs));

    // Closes the Connection
    JOptionPane.showMessageDialog(null, new JScrollPane(table));
    }
}
