package sbdkerja;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
//import java.util.Date;

public class PangkatKaryawan extends javax.swing.JFrame {
    Connect c = new Connect();
    private final String driverClass = c.driverClass;
    private final String url = c.url;
    
    Connection con;
    Statement st;
    PreparedStatement pst;
    ResultSet rs;
    String sql;
    
    String IDKaryawan, IDPangkat;

    public PangkatKaryawan() {
        initComponents();
        pangkatFillData();
    }
    public PangkatKaryawan(String id){
        initComponents();
        pangkatFillData();
        
        try {
            // Mendapatkan IDKaryawan dari input user Nama Karyawan
            sql = "SELECT Nama FROM Karyawan WHERE IDKaryawan='" + id + "'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            String NamaKaryawan = rs.getString("Nama");
            text_karyawan.setText(NamaKaryawan);
            
            // Mendapatkan NamaPangkat Terakhir dari Input Nama Karyawan terkait
            sql = "select NamaPangkat as Nama from Pangkat "
                 + "where IDPangkat=(select top 1 IDPangkat as ID from PangkatKaryawan where IDKaryawan='" + id + "' order by Tanggal desc)";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            String NamaPangkat = rs.getString("Nama");
            jComboPangkat.setSelectedItem(NamaPangkat);
            
            // Mendapatkan tanggal terakhir untuk case update data
            sql = "select top 1 Tanggal as Tgl from PangkatKaryawan where IDKaryawan='" + id + "' order by Tanggal desc";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("Tgl"));
            text_date.setDate(date);
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ParseException ex) {
            Logger.getLogger(PangkatKaryawan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void connect(){
        try{
            Class.forName(driverClass);
            con = DriverManager.getConnection(url);
            
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "Database Tidak Terhubung");
        }
    }
    private void pangkatFillData(){
        try{
            connect();
            sql = "SELECT NamaPangkat FROM Pangkat";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                jComboPangkat.addItem(rs.getString("NamaPangkat"));
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        text_karyawan = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        text_date = new com.toedter.calendar.JDateChooser();
        jComboPangkat = new javax.swing.JComboBox<>();
        button_pilihkaryawan = new javax.swing.JButton();
        button_clear = new javax.swing.JButton();
        button_update = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btn_Delete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(77, 81, 178));
        jPanel2.setPreferredSize(new java.awt.Dimension(666, 199));

        jLabel2.setFont(new java.awt.Font("Lato Black", 0, 29)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Perbarui Pangkat Karyawan");

        jPanel3.setBackground(new java.awt.Color(229, 229, 243));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel3.setPreferredSize(new java.awt.Dimension(666, 136));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel3.setText("Nama Karyawan");

        text_karyawan.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        text_karyawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_karyawanActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel4.setText("Pangkat");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel5.setText("Tanggal");
        jLabel5.setPreferredSize(new java.awt.Dimension(129, 28));

        text_date.setDateFormatString("yyyy-MM-dd");
        text_date.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        text_date.setPreferredSize(new java.awt.Dimension(103, 28));

        jComboPangkat.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jComboPangkat.setPreferredSize(new java.awt.Dimension(72, 28));
        jComboPangkat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboPangkatActionPerformed(evt);
            }
        });

        button_pilihkaryawan.setBackground(new java.awt.Color(226, 151, 161));
        button_pilihkaryawan.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        button_pilihkaryawan.setText("Pilih Karyawan");
        button_pilihkaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_pilihkaryawanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboPangkat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(text_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_pilihkaryawan))
                    .addComponent(text_date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(button_pilihkaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(text_karyawan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jComboPangkat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(text_date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                        .addGap(18, 18, 18))))
        );

        button_clear.setBackground(new java.awt.Color(226, 151, 161));
        button_clear.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        button_clear.setText("Clear");
        button_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_clearActionPerformed(evt);
            }
        });

        button_update.setBackground(new java.awt.Color(226, 151, 161));
        button_update.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        button_update.setText("Update");
        button_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_updateActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sbdkerja/previous.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        btn_Delete.setBackground(new java.awt.Color(226, 151, 161));
        btn_Delete.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        btn_Delete.setText("Delete");
        btn_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button_update)
                .addGap(18, 18, 18)
                .addComponent(btn_Delete)
                .addGap(18, 18, 18)
                .addComponent(button_clear)
                .addGap(24, 24, 24))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_update)
                    .addComponent(button_clear)
                    .addComponent(btn_Delete))
                .addGap(24, 24, 24))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 290));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void text_karyawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_karyawanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_karyawanActionPerformed

    private void button_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_clearActionPerformed
        text_karyawan.setText("");
        jComboPangkat.setSelectedIndex(0);
    }//GEN-LAST:event_button_clearActionPerformed

    private void button_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_updateActionPerformed
        try{
            connect();

            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = formatDate.format(text_date.getDate());
            
            // Mendapatkan IDPangkat dari Input Nama Pangkat
            sql = "SELECT IDPangkat AS ID FROM Pangkat WHERE NamaPangkat='"+jComboPangkat.getSelectedItem()+"'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            this.IDPangkat = rs.getString("ID");

            // Mendapatkan IDKaryawan dari input user Nama Karyawan
            sql = "SELECT IDKaryawan AS ID FROM Karyawan WHERE Nama='"+text_karyawan.getText()+"'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            this.IDKaryawan = rs.getString("ID");
            
            // Check apakah data dengan Karyawan dan Tanggal yang sama telah ada
            pst = con.prepareStatement("SELECT * FROM PangkatKaryawan WHERE IDKaryawan='"+IDKaryawan+"' and IDPangkat='"+IDPangkat+"' and Tanggal='"+strDate+"'");
            rs = pst.executeQuery();

            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Data Sudah Ada");
                // update tanggal untuk data yang sudah ada
            }else{
                // input new record : data karyawan, pangkat barunya, & tanggal mendapatkan pangkat baru
                sql = "INSERT INTO PangkatKaryawan VALUES ('" + IDKaryawan + "', '" + IDPangkat + "', '" + strDate + "', 0)";
                pst = con.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
            }
        }catch(SQLException e){
            // update tanggal jika primary key sudah ada
            try{
                SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = formatDate.format(text_date.getDate());
                sql = "UPDATE PangkatKaryawan SET Tanggal='"+strDate+"' WHERE IDKaryawan='"+IDKaryawan+"' and IDPangkat='"+IDPangkat+"'";
                pst = con.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Update Data Tanggal untuk Data Berhasil");
            } catch(SQLException se){
                JOptionPane.showMessageDialog(null, "Update Data Gagal", "Failed",JOptionPane.ERROR_MESSAGE);
            }
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Masukkan Tanggal Pangkat", "Failed",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_button_updateActionPerformed

    private void jComboPangkatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboPangkatActionPerformed
        
    }//GEN-LAST:event_jComboPangkatActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        dispose();
        Menu a = new Menu();
        a.setVisible(true);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void btn_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeleteActionPerformed
        try {
            connect();
            
            // Mendapatkan IDPangkat dari Input Nama Pangkat
            sql = "SELECT IDPangkat AS ID FROM Pangkat WHERE NamaPangkat='" + jComboPangkat.getSelectedItem().toString() + "'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            String IDPangkat = rs.getString("ID");

            // Mendapatkan IDKaryawan dari input user Nama Karyawan
            sql = "SELECT IDKaryawan AS ID FROM Karyawan WHERE Nama='" + text_karyawan.getText() + "'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            String IDKaryawan = rs.getString("ID");
            
            String sql = "UPDATE PangkatKaryawan SET Deleted=1 where IDPangkat='" + IDPangkat 
                                                            + "' and IDKaryawan='"+ IDKaryawan + "'";

            pst = con.prepareStatement(sql);
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Penghapusan Data Berhasil");
            
            text_karyawan.setText("");
            jComboPangkat.setSelectedIndex(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btn_DeleteActionPerformed
    private void button_pilihkaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_pilihkaryawanActionPerformed
        TabelKaryawan n = new TabelKaryawan("Ambil Data Pangkat Karyawan");
        n.setVisible(true);
        dispose();
    }//GEN-LAST:event_button_pilihkaryawanActionPerformed

    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(PangkatKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PangkatKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PangkatKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PangkatKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PangkatKaryawan().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Delete;
    private javax.swing.JButton button_clear;
    private javax.swing.JButton button_pilihkaryawan;
    private javax.swing.JButton button_update;
    private javax.swing.JComboBox<String> jComboPangkat;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private com.toedter.calendar.JDateChooser text_date;
    private javax.swing.JTextField text_karyawan;
    // End of variables declaration//GEN-END:variables
}
