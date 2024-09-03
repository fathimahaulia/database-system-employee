package sbdkerja;

import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.JOptionPane;

public class Pangkat extends javax.swing.JFrame {
    Connect c = new Connect();
    private final String driverClass = c.driverClass;
    private final String url = c.url;
    
    Connection con;
    Statement st;
    PreparedStatement pst;
    ResultSet rs;
    String sql;

    public Pangkat(){
        initComponents();
        jLabel7.setVisible(false);
        jComboIDPangkat.setVisible(false);
        DeleteBtn.setVisible(false);
    }  
    public Pangkat(String a){
        initComponents();
        title.setText(a + " Pangkat");
        button_action.setText(a);
        pangkatFillData();
    }
    public void connect(){
        try{
            Class.forName(driverClass);
            con = DriverManager.getConnection(url);
            
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "Database Tidak Terhubung");
        }
    }  
    private String IDOtomatis(){
        try{
            pst = con.prepareStatement("SELECT COUNT(*) AS count FROM Pangkat");
            rs = pst.executeQuery();
            rs.next();
            int count = rs.getInt("count") + 1;
            return String.format("P%03d", count);
        }catch(SQLException E){
            return "";
        }
    }
    private void pangkatFillData(){
        try{
            connect();
            sql = "SELECT IDPangkat FROM Pangkat where Deleted=0";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                jComboIDPangkat.addItem(rs.getString("IDPangkat"));
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        text_pangkat = new javax.swing.JTextField();
        text_ket = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboIDPangkat = new javax.swing.JComboBox<>();
        button_clear = new javax.swing.JButton();
        button_action = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        DeleteBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Input Data Pangkat");

        jPanel2.setBackground(new java.awt.Color(77, 81, 178));

        title.setFont(new java.awt.Font("Lato Black", 0, 29)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("Input Pangkat");

        jPanel3.setBackground(new java.awt.Color(229, 229, 243));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel3.setPreferredSize(new java.awt.Dimension(666, 145));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel3.setText("Nama Pangkat");
        jLabel3.setMaximumSize(new java.awt.Dimension(64, 16));
        jLabel3.setMinimumSize(new java.awt.Dimension(64, 16));
        jLabel3.setName(""); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(64, 16));

        text_pangkat.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        text_pangkat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_pangkatActionPerformed(evt);
            }
        });

        text_ket.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        text_ket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_ketActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel5.setText("Keterangan");
        jLabel5.setMaximumSize(new java.awt.Dimension(64, 16));
        jLabel5.setMinimumSize(new java.awt.Dimension(64, 16));
        jLabel5.setName(""); // NOI18N
        jLabel5.setPreferredSize(new java.awt.Dimension(64, 16));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel7.setText("ID Pangkat");
        jLabel7.setMaximumSize(new java.awt.Dimension(64, 16));
        jLabel7.setMinimumSize(new java.awt.Dimension(64, 16));
        jLabel7.setName(""); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(64, 16));

        jComboIDPangkat.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jComboIDPangkat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        jComboIDPangkat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboIDPangkatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(text_ket, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboIDPangkat, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(text_pangkat, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(4, 4, 4))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboIDPangkat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(text_pangkat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(text_ket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        button_clear.setBackground(new java.awt.Color(226, 151, 161));
        button_clear.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        button_clear.setText("Clear");
        button_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_clearActionPerformed(evt);
            }
        });

        button_action.setBackground(new java.awt.Color(153, 255, 255));
        button_action.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        button_action.setText("Input");
        button_action.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_actionActionPerformed(evt);
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

        DeleteBtn.setBackground(new java.awt.Color(226, 151, 161));
        DeleteBtn.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        DeleteBtn.setText("Delete");
        DeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(116, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button_action, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(DeleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(button_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(title))
                .addGap(12, 12, 12)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_clear)
                    .addComponent(button_action)
                    .addComponent(DeleteBtn))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void button_actionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_actionActionPerformed
        try {
            connect();

            if (jLabel7.isVisible()) {
                String IDPangkat = jComboIDPangkat.getSelectedItem().toString();
                sql = "UPDATE Pangkat SET NamaPangkat='" + text_pangkat.getText()
                        + "', Keterangan='" + text_ket.getText()
                        + "' where IDPangkat='" + IDPangkat + "'";
                pst = con.prepareStatement(sql);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Update Data Berhasil");
            } else {
                // mengecheck apakah ada data dengan nama Pangkat yang sama
                pst = con.prepareStatement("SELECT IDPangkat AS ID FROM Pangkat WHERE NamaPangkat='" + text_pangkat.getText() + "'");
                rs = pst.executeQuery();
                
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Data Sudah Ada");
                } else {
                    sql = "INSERT INTO Pangkat (IDPangkat, NamaPangkat, Keterangan, Deleted) VALUES ('" + IDOtomatis() + "','" + text_pangkat.getText() + "','" + text_ket.getText() + "', 0)";
                    pst = con.prepareStatement(sql);
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, title.getText()+" Gagal", "Failed", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_button_actionActionPerformed
    private void button_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_clearActionPerformed
        text_pangkat.setText("");
        text_ket.setText("");
    }//GEN-LAST:event_button_clearActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        dispose();
        Menu a = new Menu();
        a.setVisible(true);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void text_ketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_ketActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_ketActionPerformed

    private void text_pangkatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_pangkatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_pangkatActionPerformed

    private void jComboIDPangkatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboIDPangkatActionPerformed
        try{
            connect();
            sql = "SELECT NamaPangkat, Keterangan FROM Pangkat where IDPangkat='"+jComboIDPangkat.getSelectedItem().toString()+"'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                text_pangkat.setText(rs.getString("NamaPangkat"));
                text_ket.setText(rs.getString("Keterangan"));
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }//GEN-LAST:event_jComboIDPangkatActionPerformed

    private void DeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBtnActionPerformed
        try {
            connect();
            
            String Pangkat = jComboIDPangkat.getSelectedItem().toString();
            String sql = "UPDATE Pangkat SET Deleted=1 where IDPangkat='"+Pangkat+"'";
            
            pst = con.prepareStatement(sql);
           
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Penghapusan Data Berhasil");
            jComboIDPangkat.removeItem(Pangkat);
            jComboIDPangkat.setSelectedIndex(0);
            text_ket.setText("");
            text_pangkat.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_DeleteBtnActionPerformed
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
            java.util.logging.Logger.getLogger(Pangkat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pangkat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pangkat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pangkat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pangkat().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DeleteBtn;
    private javax.swing.JButton button_action;
    private javax.swing.JButton button_clear;
    private javax.swing.JComboBox<String> jComboIDPangkat;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField text_ket;
    private javax.swing.JTextField text_pangkat;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}