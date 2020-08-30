/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTIL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class PERPUSTAKAAN {


    /**
     * @param args the command line arguments
     */
    
    public static String name="SEKOLAH";
    public static String alamat="alamat";
    public static String npsn="NO NPSN";

    public PERPUSTAKAAN() {
        DB.start();
       ResultSet rs = DB.select("SELECT * FROM sekolah");
       
        try {
            while(rs.next()){
                setName(rs.getString(2));
                setgetAlamat(rs.getString(3));
                setNpsn(rs.getString(4));
                
            }} catch (SQLException ex) {
            Logger.getLogger(PERPUSTAKAAN.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setgetAlamat(String desc) {
        this.alamat = desc;
    }

    public String getNpsn() {
        return npsn;
    }

    public void setNpsn(String npsn) {
        this.npsn = npsn;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        
    }
    
}
