package sbdkerja;

import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TabelBin extends javax.swing.JFrame {
    Connect c = new Connect();
    private final String driverClass = c.driverClass;
    private final String url = c.url;
    
    Connection con = null;
    Statement st = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String sql;
    String sqltable=" ";

    public TabelBin(){
        initComponents();
    }
    public void UpdateDB(String a, String b, String c){
        try{
            Class.forName(driverClass);
            con = DriverManager.getConnection(url);
            
            pst = con.prepareStatement(sqltable + a);
            rs = pst.executeQuery();
            ResultSetMetaData stData = rs.getMetaData();
            
            int q = stData.getColumnCount();
            
            DefaultTableModel model = (DefaultTableModel) tableBin.getModel();
            model.setRowCount(0);
            
            while(rs.next()){
                Vector colData = new Vector();
                for(int i=1;i<=q;i++){
                    colData.add(rs.getString(b));
                    colData.add(rs.getString(c));
                }
                model.addRow(colData);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        button_Restore = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBin = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        CBox_Tabel = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(77, 81, 178));

        jLabel2.setFont(new java.awt.Font("Lato Black", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Recycle Bin");

        button_Restore.setBackground(new java.awt.Color(226, 151, 161));
        button_Restore.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        button_Restore.setText("Restore Data");
        button_Restore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_RestoreActionPerformed(evt);
            }
        });

        tableBin.setAutoCreateRowSorter(true);
        tableBin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tableBin.setFont(new java.awt.Font("Lato Medium", 0, 12)); // NOI18N
        tableBin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama"
            }
        ));
        tableBin.setAlignmentY(1.0F);
        tableBin.setGridColor(new java.awt.Color(229, 229, 243));
        tableBin.setMinimumSize(new java.awt.Dimension(30, 30));
        tableBin.setShowGrid(true);
        tableBin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBinMouseClicked(evt);
            }
        });
        tableBin.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tableBinPropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(tableBin);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sbdkerja/previous.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        jLabel1.setText("Tabel");

        CBox_Tabel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Karyawan", "Divisi", "Keahlian", "Pangkat", "PangkatKaryawan", "ProgramKerja", "ProkerDivisi", " " }));
        CBox_Tabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBox_TabelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CBox_Tabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(CBox_Tabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(button_Restore)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 21, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(button_Restore)
                .addGap(66, 66, 66))
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

    private void tableBinPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tableBinPropertyChange

    }//GEN-LAST:event_tableBinPropertyChange

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        dispose();
        Menu a = new Menu();
        a.setVisible(true);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void tableBinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBinMouseClicked

    }//GEN-LAST:event_tableBinMouseClicked

    private void button_RestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_RestoreActionPerformed
    try{
            Class.forName(driverClass);
            con = DriverManager.getConnection(url);
            int row = tableBin.getSelectedRow();
            String value = (tableBin.getModel().getValueAt(row, 0).toString());
            String value2 = (tableBin.getModel().getValueAt(row,1).toString());
            //mengupdate nilai tabel sesuai dengan baris yg dipilih
            if (CBox_Tabel.getSelectedItem().equals("Karyawan")){
                sql="UPDATE Karyawan set Deleted=0 where IDKaryawan='"+value+"'";
            } else if (CBox_Tabel.getSelectedItem().equals("Divisi")){
                sql="UPDATE Divisi set Deleted=0 where IDDivisi='"+value+"'";
            } else if (CBox_Tabel.getSelectedItem().equals("Keahlian")){
                sql="UPDATE Keahlian set Deleted=0 where IDKeahlian='"+value+"'";
            } else if(CBox_Tabel.getSelectedItem().equals("Pangkat")) {
                sql="UPDATE Pangkat set Deleted=0 where IDPangkat='"+value+"'";
            } else if(CBox_Tabel.getSelectedItem().equals("ProgramKerja")) {
                sql="UPDATE ProgramKerja set Deleted=0 where IDProKer='"+value+"'";
            } else if(CBox_Tabel.getSelectedItem().equals("ProkerDivisi")) {
                sql="UPDATE ProkerDivisi set Deleted=0 where IDProKer='"+value+"' and IDDivisi='"+value2+"'";
            } else if(CBox_Tabel.getSelectedItem().equals("PangkatKaryawan")) {
                sql="UPDATE PangkatKaryawan set Deleted=0 where IDPangkat='"+value+"' and IDKaryawan='"+value2+"'";
            }
            
            JOptionPane.showMessageDialog(null, "Restorasi Data Berhasil");
            CBox_Tabel.setSelectedIndex(0);
            pst = con.prepareStatement(sql);
            pst.executeUpdate();

             }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_button_RestoreActionPerformed
    public void RemoveTable() {
        //mengosongkan tabel
        try {
            DefaultTableModel dtm = (DefaultTableModel) tableBin.getModel();
            dtm.setNumRows(0);
        } catch (Exception e) {
        }
    }
    private void changeHeader(String id, String nama){
        //mengganti header title tabel
        tableBin.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    id, nama
                }
        ));
    }
    private void CBox_TabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBox_TabelActionPerformed
        //menampilkan data pada tabel sesuai dengan dropdown yg dipilih
        if(CBox_Tabel.getSelectedItem().equals(" ")){
            RemoveTable();
        } else if (CBox_Tabel.getSelectedItem().equals("Karyawan")){
            changeHeader("ID Karyawan","Nama Karyawan");
            UpdateDB("SELECT IDKaryawan, Nama from Karyawan where Deleted=1","IDKaryawan","Nama");
        } else if (CBox_Tabel.getSelectedItem().equals("Divisi")){
            changeHeader("ID Divisi","Nama Divisi");
            UpdateDB("SELECT IDDivisi, NamaDivisi from Divisi where Deleted=1","IDDivisi","NamaDivisi");
        } else if (CBox_Tabel.getSelectedItem().equals("Keahlian")){
            changeHeader("ID Keahlian","Nama Keahlian");
            UpdateDB("SELECT IDKeahlian, NamaKeahlian from Keahlian where Deleted=1","IDKeahlian","NamaKeahlian");
        } else if (CBox_Tabel.getSelectedItem().equals("Pangkat")){
            changeHeader("ID Pangkat","Nama Pangkat");
            UpdateDB("SELECT IDPangkat, NamaPangkat from Pangkat where Deleted=1","IDPangkat","NamaPangkat");
        } else if (CBox_Tabel.getSelectedItem().equals("PangkatKaryawan")){
            changeHeader("ID Pangkat","ID Karyawan");
            UpdateDB("SELECT IDPangkat, IDKaryawan from PangkatKaryawan where Deleted=1","IDPangkat","IDKaryawan");
        } else if (CBox_Tabel.getSelectedItem().equals("ProgramKerja")){
            changeHeader("ID Program Kerja","Nama Program Kerja");
            UpdateDB("SELECT IDProKer, NamaProKer from ProgramKerja where Deleted=1","IDProKer","NamaProKer");
        } else if (CBox_Tabel.getSelectedItem().equals("ProkerDivisi")){
            changeHeader("Nama Proker","NamaDivisi");
            UpdateDB(" select prik.NamaProKer, div.NamaDivisi from ProkerDivisi"
                    + " left join (select * from ProgramKerja where ProgramKerja.Deleted=0) as prik"
                    + " on prik.IDProKer = ProkerDivisi.IDProKer"
                    + " left join (select * from Divisi where Divisi.Deleted=0) "
                    + " as  div on div.IDDivisi=ProkerDivisi.IDProKer","NamaProker","NamaDivisi");
        } 
    }//GEN-LAST:event_CBox_TabelActionPerformed

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
            java.util.logging.Logger.getLogger(TabelBin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TabelBin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TabelBin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TabelBin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TabelBin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBox_Tabel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton button_Restore;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableBin;
    // End of variables declaration//GEN-END:variables
}
