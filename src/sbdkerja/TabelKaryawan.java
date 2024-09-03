package sbdkerja;

import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TabelKaryawan extends javax.swing.JFrame {
    Connect c = new Connect();
    private final String driverClass = c.driverClass;
    private final String url = c.url;
    
    Connection con = null;
    Statement st = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String sql;
    String sqltabel =    "select kar.IDKaryawan, kar.Nama,"
                    + " Karyawan.Alamat, Karyawan.TglLahir, Div.NamaDivisi,"
                    + " Ahli.NamaKeahlian, Sup.Nama as NamaSup, Pengawas.NamaDivisi as NamaAwas"
                    + " from Karyawan"
                    + " inner join (select * from Karyawan where Karyawan.Deleted=0) as kar on Karyawan.IDKaryawan=kar.IDKaryawan "
                    + " left join (select * from Divisi where Divisi.Deleted=0) "
                    + " as Div on Karyawan.IDDivisi=Div.IDDivisi "
                    + " left join (select * from Keahlian where Keahlian.Deleted=0)"
                    + " as Ahli on Karyawan.IDKeahlian=Ahli.IDKeahlian"
                    + " left join (select * from Karyawan where Karyawan.Deleted=0)"
                    + " as Sup on Karyawan.IDSupervisor=Sup.IDKaryawan "
                    + " left join (select * from Divisi where Divisi.Deleted=0) "
                    + " as Pengawas on Karyawan.IDPengawas=Pengawas.IDDivisi ";

    String returnID;
    String state = "";
    
    public TabelKaryawan() {
        initComponents();
        button_pilihkaryawan.setVisible(false);
        UpdateDB("");
    }
    public TabelKaryawan(String a) {
        initComponents();
        UpdateDB("");
        if (a.equalsIgnoreCase("Ambil Data")) {
        }
        if (a.equalsIgnoreCase("Ambil Data Pangkat Karyawan")) {
            button_pilihkaryawan.setVisible(true);
            this.state = "Pangkat Karyawan";
        }
    }
    public TabelKaryawan(String a, int del){
        initComponents();
        UpdateDB("WHERE Karyawan.Deleted="+del);
    }
    private void KeahlianFillData(){
        try{
            Class.forName(driverClass);
            con = DriverManager.getConnection(url);
            sql = "SELECT NamaKeahlian FROM Keahlian where Deleted=0";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                CBox_Item.addItem(rs.getString("NamaKeahlian"));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    private void DivisiFillData(){
        try{
            Class.forName(driverClass);
            con = DriverManager.getConnection(url);
            sql = "SELECT NamaDivisi FROM Divisi where Deleted=0";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                CBox_Item.addItem(rs.getString("NamaDivisi"));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
     private void SupervisorFillData(){
        try{
            Class.forName(driverClass);
            con = DriverManager.getConnection(url);
            sql = "SELECT Nama FROM Karyawan where Deleted=0 and IDSupervisor is null";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                CBox_Item.addItem(rs.getString("Nama"));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void UpdateDB(String a){
        try{
            Class.forName(driverClass);
            con = DriverManager.getConnection(url);
            
            pst = con.prepareStatement(sqltabel + a);
            rs = pst.executeQuery();
            ResultSetMetaData stData = rs.getMetaData();
            
            int q = stData.getColumnCount();
            
            DefaultTableModel model = (DefaultTableModel) tablekwn.getModel();
            model.setRowCount(0);
            
            while(rs.next()){
                Vector colData = new Vector();
                for(int i=1;i<=q;i++){
                    colData.add(rs.getString("IDKaryawan"));
                    colData.add(rs.getString("Nama"));
                    colData.add(rs.getString("Alamat"));
                    colData.add(rs.getString("TglLahir"));
                    colData.add(rs.getString("NamaDivisi"));
                    colData.add(rs.getString("NamaKeahlian"));
                    colData.add(rs.getString("NamaSup"));
                    colData.add(rs.getString("NamaAwas"));
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
        button_pilihkaryawan = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablekwn = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Btn_Divisi = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        Btn_Keahlian = new javax.swing.JRadioButton();
        Btn_Supervisor = new javax.swing.JRadioButton();
        Btn_Pengawas = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        CBox_Item = new javax.swing.JComboBox<>();
        BtnGenerate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(77, 81, 178));

        jLabel2.setFont(new java.awt.Font("Lato Black", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tabel Karyawan");

        button_pilihkaryawan.setBackground(new java.awt.Color(226, 151, 161));
        button_pilihkaryawan.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        button_pilihkaryawan.setText("Pilih Karyawan");
        button_pilihkaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_pilihkaryawanActionPerformed(evt);
            }
        });

        tablekwn.setAutoCreateRowSorter(true);
        tablekwn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tablekwn.setFont(new java.awt.Font("Lato Medium", 0, 12)); // NOI18N
        tablekwn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama", "Alamat", "Tanggal Lahir", "Divisi", "Keahlian", "Supervisor", "Pengawas"
            }
        ));
        tablekwn.setGridColor(new java.awt.Color(229, 229, 243));
        tablekwn.setShowGrid(true);
        tablekwn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablekwnMouseClicked(evt);
            }
        });
        tablekwn.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tablekwnPropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(tablekwn);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sbdkerja/previous.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        buttonGroup1.add(Btn_Divisi);
        Btn_Divisi.setFont(new java.awt.Font("Lato", 0, 12)); // NOI18N
        Btn_Divisi.setText("Divisi");
        Btn_Divisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_DivisiActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lato", 1, 14)); // NOI18N
        jLabel1.setText("Filter");

        buttonGroup1.add(Btn_Keahlian);
        Btn_Keahlian.setFont(new java.awt.Font("Lato", 0, 12)); // NOI18N
        Btn_Keahlian.setText("Keahlian");
        Btn_Keahlian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_KeahlianActionPerformed(evt);
            }
        });

        buttonGroup1.add(Btn_Supervisor);
        Btn_Supervisor.setFont(new java.awt.Font("Lato", 0, 12)); // NOI18N
        Btn_Supervisor.setText("Supervisor");
        Btn_Supervisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_SupervisorActionPerformed(evt);
            }
        });

        buttonGroup1.add(Btn_Pengawas);
        Btn_Pengawas.setFont(new java.awt.Font("Lato", 0, 12)); // NOI18N
        Btn_Pengawas.setText("Pengawas");
        Btn_Pengawas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_PengawasActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Lato", 1, 14)); // NOI18N
        jLabel3.setText("Nama:");

        CBox_Item.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        CBox_Item.setToolTipText("");
        CBox_Item.setActionCommand("");
        CBox_Item.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBox_ItemItemStateChanged(evt);
            }
        });
        CBox_Item.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CBox_ItemMouseClicked(evt);
            }
        });
        CBox_Item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBox_ItemActionPerformed(evt);
            }
        });

        BtnGenerate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sbdkerja/magnifier (1).png"))); // NOI18N
        BtnGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGenerateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(Btn_Divisi, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Btn_Keahlian)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Btn_Supervisor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Btn_Pengawas)
                .addGap(33, 33, 33)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CBox_Item, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_Divisi)
                    .addComponent(jLabel1)
                    .addComponent(Btn_Keahlian)
                    .addComponent(Btn_Supervisor)
                    .addComponent(Btn_Pengawas)
                    .addComponent(jLabel3)
                    .addComponent(CBox_Item, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnGenerate)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 695, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(button_pilihkaryawan)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 16, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(button_pilihkaryawan)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
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

    private void tablekwnPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tablekwnPropertyChange
        // TODO add your handling code here:
        //        jTable1.getTableHeader().setOpaque(false);
        //        jTable1.getTableHeader().setBackground(Color.red);
    }//GEN-LAST:event_tablekwnPropertyChange

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        dispose();
        Menu a = new Menu();
        a.setVisible(true);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void tablekwnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablekwnMouseClicked
        DefaultTableModel model = (DefaultTableModel) tablekwn.getModel();
        int i = tablekwn.getSelectedRow(); 
        this.returnID = model.getValueAt(i,0).toString();
    }//GEN-LAST:event_tablekwnMouseClicked

    private void button_pilihkaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_pilihkaryawanActionPerformed
        try{
            if (state.equalsIgnoreCase("Pangkat Karyawan")) {
                PangkatKaryawan n = new PangkatKaryawan(returnID);
                n.setVisible(true);
                dispose();
            } else {
                Karyawan n = new Karyawan("Update", returnID);
                n.setVisible(true);
                n.fillData(returnID);
                dispose();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }//GEN-LAST:event_button_pilihkaryawanActionPerformed

    private void Btn_DivisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_DivisiActionPerformed
        CBox_Item.removeAllItems();
        CBox_Item.addItem("");
        DivisiFillData();
      
    }//GEN-LAST:event_Btn_DivisiActionPerformed

    private void CBox_ItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBox_ItemActionPerformed

    }//GEN-LAST:event_CBox_ItemActionPerformed

    private void Btn_KeahlianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_KeahlianActionPerformed
        CBox_Item.removeAllItems();
        CBox_Item.addItem("");
        KeahlianFillData();
    }//GEN-LAST:event_Btn_KeahlianActionPerformed

    private void CBox_ItemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBox_ItemItemStateChanged
    
    }//GEN-LAST:event_CBox_ItemItemStateChanged

    private void CBox_ItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CBox_ItemMouseClicked
    
    }//GEN-LAST:event_CBox_ItemMouseClicked

    private void BtnGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGenerateActionPerformed
        if (Btn_Keahlian.isSelected()) {
            String Keahlian = CBox_Item.getSelectedItem().toString();
            UpdateDB("where NamaKeahlian='" + Keahlian + "'");
        } else if (Btn_Divisi.isSelected()) {
            String Divisi = CBox_Item.getSelectedItem().toString();
            UpdateDB("where Div.NamaDivisi='" + Divisi + "'");
        } else if (Btn_Supervisor.isSelected()) {
            String Supervisor = CBox_Item.getSelectedItem().toString();
            UpdateDB("where Sup.Nama='" + Supervisor + "'");
        } else if (Btn_Pengawas.isSelected()) {
            UpdateDB("where Pengawas.NamaDivisi is not NULL");
        }
    }//GEN-LAST:event_BtnGenerateActionPerformed

    private void Btn_SupervisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_SupervisorActionPerformed
        CBox_Item.removeAllItems();
        CBox_Item.addItem("");
        SupervisorFillData();
    }//GEN-LAST:event_Btn_SupervisorActionPerformed

    private void Btn_PengawasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_PengawasActionPerformed
        CBox_Item.removeAllItems();
        CBox_Item.addItem("");
    }//GEN-LAST:event_Btn_PengawasActionPerformed

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
            java.util.logging.Logger.getLogger(TabelKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TabelKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TabelKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TabelKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TabelKaryawan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnGenerate;
    private javax.swing.JRadioButton Btn_Divisi;
    private javax.swing.JRadioButton Btn_Keahlian;
    private javax.swing.JRadioButton Btn_Pengawas;
    private javax.swing.JRadioButton Btn_Supervisor;
    private javax.swing.JComboBox<String> CBox_Item;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton button_pilihkaryawan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablekwn;
    // End of variables declaration//GEN-END:variables
}
