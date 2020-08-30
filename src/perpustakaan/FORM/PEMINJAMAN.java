/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpustakaan.FORM;

import FORM.ADMIN;
import FORM.KATEGORI;
import FORM.MENU;
import SEARCH.SEARCH;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import perpustakaan.DB;
import perpustakaan.REPORT;

/**
 *
 * @author User
 */
public class PEMINJAMAN extends javax.swing.JFrame {

    /**
     * Creates new form PEMINJAMAN
     */
    
    String[] judul = {"Id Peminjaman", "Nama", "Buku","Jumlah", "Tanggal Pinjam"};
    ResultSet rs;
    REPORT cetak = new perpustakaan.REPORT();
    
            private void setTable(){
        rs = DB.select("SELECT\n" +
"a.id,\n" +
"upper(b.nama),\n" +
"c.judul,\n" +
"a.jumlah_pinjam,\n" +
"a.tgl_pinjam\n" +
"FROM\n" +
"peminjaman a\n" +
"INNER JOIN siswa b on a.id_siswa=b.id\n" +
"INNER JOIN buku c on a.id_buku=c.id");
        try {
        jTable1.setModel(DB.buildTableModel(rs));
        DB.ChangeName(jTable1, judul);
            
        } catch (SQLException ex) {
            Logger.getLogger(ADMIN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
                public String thn() {
        Date dateFromDateChooser =  jDateChooser1.getDate();
        String dateString = String.format("%1$tY-%1$tm-%1$td", dateFromDateChooser);
        return dateString;
    }
            
    public void insert(){
       DB.closeConn();
       DB.start();
        String sql = "INSERT INTO `peminjaman`(`id`, `jumlah_pinjam`, `tgl_pinjam`, `id_buku`, `id_siswa`,`status`) VALUES ('%s','%s','%s','%s','%s','BELUM KEMBALI')";
        sql = String.format(sql, id.getText(), jumlah.getText(), thn(), buku.getText(), siswa.getText());
        System.out.print(sql);
        DB.insert(sql);
    }
    
    public void delete(){
        String sql = "DELETE FROM `peminjaman` WHERE id='%s'";
        sql = String.format(sql, id.getText());
        System.out.print(sql+" Stop here");
        DB.delete(sql);
    }
    
    public void edit(){
        String sql="UPDATE `peminjaman` SET `jumlah_pinjam`='%s',`tgl_pinjam`='%s',`id_buku`='%s',`id_siswa`='%s' WHERE `id`='%s'";
        sql = String.format(sql, jumlah.getText(),thn(),buku.getText(),siswa.getText(),id.getText());
        DB.update(sql);
    }
    
    private void setId() throws SQLException{
        int total;
        ResultSet select = DB.select("peminjaman", "max(id)");
        if (select.next() == false) {
            System.out.println("ResultSet in empty in Java");
            total=1;
        } else {
            do {
                //String data = rs.getString("emp_name");
                total = select.getInt(1)+1;
                System.out.println(total);
            } while (select.next());
        } 
        id.setText(String.valueOf(total));
        jumlah.setText("");
        
        buku.setText("");
        siswa.setText("");
        setTable();
    }
    public PEMINJAMAN() {
        initComponents();
        DB.start();
        try {
            setId();
        } catch (SQLException ex) {
            Logger.getLogger(PEMINJAMAN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jumlah = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        buku = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        siswa = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PEMINJAMAN");

        jLabel1.setText("ID");

        jLabel2.setText("Jumlah Pinjam");

        jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahActionPerformed(evt);
            }
        });

        jLabel3.setText("Tanggal Pinjam");

        jLabel6.setText("ID Buku");

        jLabel7.setText("ID Siswa");

        jButton1.setText("Tambah");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                none(evt);
            }
        });

        jButton2.setText("Edit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Hapus");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Kembali");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jDateChooser1.setDateFormatString("dd/MM/yyyy");

        jButton5.setText("CETAK");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("jButton6");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGap(65, 65, 65)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(id)
                                    .addComponent(jumlah)
                                    .addComponent(siswa)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(buku, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(29, 29, 29)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 471, Short.MAX_VALUE)
                        .addComponent(jButton4)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3))
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addComponent(jLabel4)
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jButton6))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(siswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 59, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton3)
                            .addComponent(jButton5)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jButton4)))
                .addGap(17, 17, 17))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
          // TODO add your handling code here:
          new MENU().setVisible(true);
          this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void none(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_none
        insert();
        try {
            setId();
        } catch (SQLException ex) {
            Logger.getLogger(KATEGORI.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "DATA BERHASIL DI TAMBAH");
    }//GEN-LAST:event_none

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
                edit();
        try {
            setId();
        } catch (SQLException ex) {
            Logger.getLogger(KATEGORI.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "DATA BERHASIL DI UBAH");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
                delete();
        try {
            setId();
        } catch (SQLException ex) {
            Logger.getLogger(KATEGORI.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "DATA BERHASIL DI DELETE");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlahActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        cetak.cetak("BUKTI_PEMINJAMAN", id.getText());
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        new SEARCH().setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PEMINJAMAN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PEMINJAMAN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PEMINJAMAN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PEMINJAMAN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PEMINJAMAN().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField buku;
    private javax.swing.JTextField id;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jumlah;
    private javax.swing.JTextField siswa;
    // End of variables declaration//GEN-END:variables
}
