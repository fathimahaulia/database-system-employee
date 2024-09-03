package sbdkerja;

import java.sql.*;
import javax.swing.JOptionPane;

public class Divisi extends javax.swing.JFrame {    Connect c = new Connect();
    private final String driverClass = c.driverClass;
    private final String url = c.url;
    
    Connection con;
    Statement st;
    PreparedStatement pst;
    ResultSet rs;
    String sql;

    public Divisi(){
        initComponents();
        jLabel5.setVisible(false);
        text_karyawan.setVisible(false);
        PilihKaryawan.setVisible(false);
        Delete_Btn.setVisible(false);
    }
    public Divisi(String a){
        initComponents();
        title.setText(a + " Divisi");
        button_action.setText(a);
    }    
    public void connect(){
        try{
            Class.forName(driverClass);
            con = DriverManager.getConnection(url);
            
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "Database Tidak Terhubung");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        text_id = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        text_namadivisi = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        text_bidang = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        PilihKaryawan = new javax.swing.JButton();
        text_karyawan = new javax.swing.JTextField();
        button_clear = new javax.swing.JButton();
        button_action = new javax.swing.JButton();
        back = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        Delete_Btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(77, 81, 178));
        jPanel1.setPreferredSize(new java.awt.Dimension(666, 297));

        jPanel2.setBackground(new java.awt.Color(229, 229, 243));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.setLayout(null);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel1.setText("ID Divisi");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(21, 21, 126, 30);

        text_id.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        text_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_idActionPerformed(evt);
            }
        });
        jPanel2.add(text_id);
        text_id.setBounds(199, 21, 332, 30);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel3.setText("Nama Divisi");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(21, 57, 126, 30);

        text_namadivisi.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        text_namadivisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_namadivisiActionPerformed(evt);
            }
        });
        jPanel2.add(text_namadivisi);
        text_namadivisi.setBounds(199, 57, 332, 30);

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel4.setText("Bidang");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(21, 93, 126, 28);

        text_bidang.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        text_bidang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_bidangActionPerformed(evt);
            }
        });
        jPanel2.add(text_bidang);
        text_bidang.setBounds(199, 93, 332, 28);

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel5.setText("Kepala Divisi");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(21, 130, 126, 22);

        PilihKaryawan.setBackground(new java.awt.Color(226, 151, 161));
        PilihKaryawan.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        PilihKaryawan.setText("Pilih Karyawan");
        PilihKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PilihKaryawanActionPerformed(evt);
            }
        });
        jPanel2.add(PilihKaryawan);
        PilihKaryawan.setBounds(381, 127, 150, 29);

        text_karyawan.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        text_karyawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_karyawanActionPerformed(evt);
            }
        });
        jPanel2.add(text_karyawan);
        text_karyawan.setBounds(199, 127, 176, 28);

        button_clear.setBackground(new java.awt.Color(226, 151, 161));
        button_clear.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        button_clear.setText("Clear");
        button_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_clearActionPerformed(evt);
            }
        });

        button_action.setBackground(new java.awt.Color(226, 151, 161));
        button_action.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        button_action.setText("Input");
        button_action.setPreferredSize(new java.awt.Dimension(100, 29));
        button_action.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_actionActionPerformed(evt);
            }
        });

        back.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        back.setForeground(new java.awt.Color(255, 255, 255));
        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sbdkerja/previous.png"))); // NOI18N
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
        });

        title.setFont(new java.awt.Font("Lato Black", 0, 29)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("Input Divisi");

        Delete_Btn.setBackground(new java.awt.Color(226, 151, 161));
        Delete_Btn.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        Delete_Btn.setText("Delete");
        Delete_Btn.setPreferredSize(new java.awt.Dimension(100, 29));
        Delete_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete_BtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 666, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(button_action, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Delete_Btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(button_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(title)))
                .addGap(12, 12, 12)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(button_action, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Delete_Btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(button_clear))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void text_namadivisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_namadivisiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_namadivisiActionPerformed

    private void text_bidangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_bidangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_bidangActionPerformed

    private void button_actionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_actionActionPerformed
        try{
            connect();
            
            if(jLabel5.isVisible()){
                // Menjalankan code untuk skenario update divisi
            }else{
                // Menjalankan code untuk skenario input divisi
                pst = con.prepareStatement("SELECT IDDivisi AS ID FROM Divisi WHERE NamaDivisi='" + text_namadivisi.getText() + "'");
                rs = pst.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Data Sudah Ada");
                } else {
                    sql = "INSERT INTO Divisi (IDDivisi, NamaDivisi, Bidang, Deleted) VALUES ('" + text_id.getText() + "','" + text_namadivisi.getText() + "','" + text_bidang.getText() + "',(0))";
                    pst = con.prepareStatement(sql);
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
                }
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
             
//            JOptionPane.showMessageDialog(null, title.getText()+" Gagal", "Failed", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_button_actionActionPerformed

    private void text_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_idActionPerformed

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        dispose();
        Menu a = new Menu();
        a.setVisible(true);
    }//GEN-LAST:event_backMouseClicked

    private void PilihKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PilihKaryawanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PilihKaryawanActionPerformed

    private void text_karyawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_karyawanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_karyawanActionPerformed

    private void Delete_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete_BtnActionPerformed
       try {
            connect();
            
            String Keahlian = text_id.getText();
            String sql = "UPDATE Keahlian SET Deleted=1 where IDKeahlian='"+Keahlian+"'";
            
            pst = con.prepareStatement(sql);
           
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Penghapusan Data Berhasil");
            
            text_id.setText("");
            text_namadivisi.setText("");
            text_bidang.setText("");
            text_karyawan.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_Delete_BtnActionPerformed

    private void button_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_clearActionPerformed
            text_id.setText("");
            text_namadivisi.setText("");
            text_bidang.setText("");
            text_karyawan.setText("");
    }//GEN-LAST:event_button_clearActionPerformed

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
            java.util.logging.Logger.getLogger(Divisi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Divisi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Divisi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Divisi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Divisi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Delete_Btn;
    private javax.swing.JButton PilihKaryawan;
    private javax.swing.JLabel back;
    private javax.swing.JButton button_action;
    private javax.swing.JButton button_clear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField text_bidang;
    private javax.swing.JTextField text_id;
    private javax.swing.JTextField text_karyawan;
    private javax.swing.JTextField text_namadivisi;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
