/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpustakaan;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author User
 */


public class REPORT {
Connection conn1 = null ;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String tgl;

public void cetak(String path){
      //public Fungsi_Cetak(String namaReport);
      //conn1 = Koneksi.KoneksiDB.ConnectDb();
          try{
                String namaFile = "/report/"+path+".jasper";
                HashMap hash = new HashMap();
                //File file = new File(namaFile);
                
               //JasperReport jasperReport = (JasperReport)JRLoader.loadObject(file.getPath());
               InputStream file=getClass().getResourceAsStream(namaFile);
                //JasperDesign jasperDesign=JRXmlLoader.load(file);
                //JasperReport jasperReport=JasperCompileManager.compileReport(jasperDesign); 
               JasperPrint jasperPrint = JasperFillManager.fillReport(file,hash,DB.conn);
            JasperViewer.viewReport(jasperPrint, false);      
            }
            catch(JRException ex){
               // JOptionPane.showMessageDialog(rootPane,"Dokumen Tidak Ada");
                System.out.print(ex);
            }
           
        
    }    

    public REPORT() {
        System.out.print("ASD");
    }

    public static void main(String args[]) {
        DB.start();
        
    }
}
