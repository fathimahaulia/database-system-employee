package sbdkerja;

import java.awt.HeadlessException;
import java.sql.*;
import java.text.ParseException;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Karyawan extends javax.swing.JFrame {
    Connect c = new Connect();
    private final String driverClass = c.driverClass;
    private final String url = c.url;
    
    Connection con = null;
    Statement st = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String sql;

    String IDSupervisor, IDDivisi, IDKeahlian, IDPangkat;
    String IDPengawas;
    
    public Karyawan() {
        initComponents();
        jComboSup.setVisible(true);
        button_delete.setVisible(false);
        pangkatFillData();
        divisiFillData();
        keahlianFillData();
        pengawasFillData();
        supervisorFillData();
        button_pilihkaryawan.setVisible(false);
        button_delete.setVisible(false);
    }
    public Karyawan(String a, String id){
        initComponents();
        pangkatFillData();
        divisiFillData();
        keahlianFillData();
        pengawasFillData();
        supervisorFillData();
        title.setText(a+" Karyawan");
        button_input.setText(a);
        button_pilihkaryawan.setVisible(true);
        button_delete.setVisible(true);
        jLabel9.setVisible(false);
        jComboPangkat.setVisible(false);
//        jLabel11.setVisible(false);
//        checkKepala.setVisible(false);
        
//        try{
//            fillData(id);
//        }catch(Exception e){
//            System.out.println(e);
//        }
    }
    public void fillData(String id){
        // Mengisi data
        if (id.equalsIgnoreCase("0")) {

        } else {
            try {
                connect();
                pst = con.prepareStatement("SELECT * FROM Karyawan where IDKaryawan='" + id + "'");
                rs = pst.executeQuery();
                while (rs.next()) {
                    text_nama.setText(rs.getString("Nama"));
                    text_alamat.setText(rs.getString("Alamat"));
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("TglLahir"));
                    text_date.setDate(date);
                    this.IDDivisi = rs.getString("IDDivisi");
                    jComboDivisi.setSelectedItem(getNama("Divisi", "NamaDivisi", "IDDivisi", IDDivisi));
                }
                pst = con.prepareStatement("SELECT * FROM Karyawan where IDKaryawan='" + id + "'");
                rs = pst.executeQuery();
                while (rs.next()) {
                    this.IDKeahlian = rs.getString("IDKeahlian");
                    jComboAhli.setSelectedItem(getNama("Keahlian", "NamaKeahlian", "IDKeahlian", IDKeahlian));
                }
                st = con.createStatement();
                rs = st.executeQuery("SELECT IDSupervisor FROM Karyawan where IDKaryawan='" + id + "'");
                while (rs.next()) {
                    if (!rs.wasNull()) {
                        if (!rs.getBoolean("IDSupervisor")) {
                            this.IDSupervisor = rs.getString("IDSupervisor");
                            checkSupervisor.setSelected(false);
                            if(!getNama("Karyawan", "Nama", "IDKaryawan", IDSupervisor).equalsIgnoreCase("NULL")){
                            jComboSup.setSelectedItem(getNama("Karyawan", "Nama", "IDKaryawan", IDSupervisor));
                            } else {
                                checkSupervisor.setSelected(true);
                                jComboSup.setVisible(false);
                            }
                        }
                    } else {
                        checkSupervisor.setSelected(true);
                    }
                }
                pst = con.prepareStatement("SELECT IDPengawas FROM Karyawan where IDKaryawan='" + id + "'");
                rs = pst.executeQuery();
                if (!rs.isBeforeFirst()) {
                    jComboPengawas.setSelectedItem("Bukan Dewan Pengawas");
                } else {
                    rs.next();
                    this.IDPengawas = rs.getString("IDPengawas");
                    if (!getNama("Divisi", "NamaDivisi", "IDDivisi", IDPengawas).equalsIgnoreCase("NULL")) {
                        jComboPengawas.setSelectedItem(getNama("Divisi", "NamaDivisi", "IDDivisi", IDPengawas));
                    } else {
                        jComboPengawas.setSelectedItem("Bukan Dewan Pengawas");
                    }
                }            
            } catch (SQLException e) {
                System.out.println(e);
            } catch (ParseException ex) {
                Logger.getLogger(Karyawan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void connect(){
        try{
            Class.forName(driverClass);
            this.con = DriverManager.getConnection(url);
            
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "Database Tidak Terhubung");
        }
    }
    private void pangkatFillData(){
        try{
            connect();
            sql = "SELECT NamaPangkat FROM Pangkat where Deleted=0";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                jComboPangkat.addItem(rs.getString("NamaPangkat"));
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    private void divisiFillData(){
        try{
            connect();
            sql = "SELECT NamaDivisi FROM Divisi where Deleted=0";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                jComboDivisi.addItem(rs.getString("NamaDivisi"));
            }
        }catch(SQLException e){
            System.out.println(e);
        }        
    }
    private void pengawasFillData(){
        try{
            jComboPengawas.addItem("Bukan Dewan Pengawas");
            connect();
            sql = "SELECT NamaDivisi FROM Divisi where Deleted=0";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                jComboPengawas.addItem(rs.getString("NamaDivisi"));
            }
        }catch(SQLException e){
            System.out.println(e);
        }        
    }
    private void supervisorFillData(){
        try{
            connect();
            sql = "SELECT Nama FROM Karyawan WHERE IDSupervisor IS NULL AND Deleted=0";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                jComboSup.addItem(rs.getString("Nama"));
            }
        }catch(SQLException e){
            System.out.println(e);
        }        
    }
    private void keahlianFillData(){
        try{
            connect();
            sql = "SELECT NamaKeahlian FROM Keahlian where Deleted=0";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                jComboAhli.addItem(rs.getString("NamaKeahlian"));
            }
        }catch(SQLException e){
            System.out.println(e);
        }        
    }
    private String IDOtomatis(){
        try{
            pst = con.prepareStatement("SELECT COUNT(*) AS count FROM Karyawan");
            rs = pst.executeQuery();
            rs.next();
            int count = rs.getInt("count") + 1;
            return String.format("K%03d", count);
        }catch(SQLException E){
            return "";
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jRadioButton1 = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        text_nama = new javax.swing.JTextField();
        text_alamat = new javax.swing.JTextField();
        text_date = new com.toedter.calendar.JDateChooser();
        jComboDivisi = new javax.swing.JComboBox<>();
        jComboAhli = new javax.swing.JComboBox<>();
        checkKepala = new javax.swing.JCheckBox();
        jComboPangkat = new javax.swing.JComboBox<>();
        checkSupervisor = new javax.swing.JCheckBox();
        jComboSup = new javax.swing.JComboBox<>();
        jComboPengawas = new javax.swing.JComboBox<>();
        button_clear = new javax.swing.JButton();
        button_input = new javax.swing.JButton();
        back = new javax.swing.JLabel();
        button_delete = new javax.swing.JButton();
        button_pilihkaryawan = new javax.swing.JButton();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Input Data Karyawan");

        jPanel2.setBackground(new java.awt.Color(77, 81, 178));
        jPanel2.setPreferredSize(new java.awt.Dimension(666, 5000));

        title.setFont(new java.awt.Font("Lato Black", 0, 29)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("Input Karyawan");

        jPanel3.setBackground(new java.awt.Color(229, 229, 243));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel3.setMinimumSize(new java.awt.Dimension(0, 370));
        jPanel3.setPreferredSize(new java.awt.Dimension(214, 370));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel3.setText("Nama Karyawan");
        jLabel3.setPreferredSize(new java.awt.Dimension(157, 30));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel6.setText("Alamat");
        jLabel6.setPreferredSize(new java.awt.Dimension(157, 30));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel5.setText("Tanggal Lahir");
        jLabel5.setPreferredSize(new java.awt.Dimension(157, 30));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel4.setText("Divisi");
        jLabel4.setPreferredSize(new java.awt.Dimension(157, 30));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel7.setText("Keahlian");
        jLabel7.setPreferredSize(new java.awt.Dimension(157, 30));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel9.setText("Pangkat");
        jLabel9.setPreferredSize(new java.awt.Dimension(157, 30));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel8.setText("Supervisor");
        jLabel8.setPreferredSize(new java.awt.Dimension(157, 30));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel10.setText("Dewan Pengawas");
        jLabel10.setPreferredSize(new java.awt.Dimension(86, 30));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel11.setText("Kepala Divisi");
        jLabel11.setPreferredSize(new java.awt.Dimension(157, 30));

        text_nama.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        text_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_namaActionPerformed(evt);
            }
        });

        text_alamat.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        text_alamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_alamatActionPerformed(evt);
            }
        });

        text_date.setDateFormatString("yyyy-MM-dd");
        text_date.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jComboDivisi.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jComboDivisi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        jComboAhli.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jComboAhli.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        checkKepala.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        checkKepala.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        checkKepala.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        checkKepala.setMaximumSize(new java.awt.Dimension(40, 40));
        checkKepala.setName(""); // NOI18N
        checkKepala.setPreferredSize(new java.awt.Dimension(40, 20));
        checkKepala.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkKepalaItemStateChanged(evt);
            }
        });
        checkKepala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkKepalaActionPerformed(evt);
            }
        });

        jComboPangkat.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jComboPangkat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        checkSupervisor.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        checkSupervisor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        checkSupervisor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        checkSupervisor.setMaximumSize(new java.awt.Dimension(40, 40));
        checkSupervisor.setName(""); // NOI18N
        checkSupervisor.setPreferredSize(new java.awt.Dimension(40, 20));
        checkSupervisor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkSupervisorItemStateChanged(evt);
            }
        });
        checkSupervisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkSupervisorActionPerformed(evt);
            }
        });

        jComboSup.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jComboSup.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        jComboSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboSupActionPerformed(evt);
            }
        });

        jComboPengawas.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jComboPengawas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        jComboPengawas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboPengawasActionPerformed(evt);
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
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125)
                        .addComponent(text_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125)
                        .addComponent(text_alamat, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125)
                        .addComponent(text_date, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125)
                        .addComponent(jComboDivisi, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125)
                        .addComponent(jComboAhli, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125)
                        .addComponent(jComboPangkat, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125)
                        .addComponent(checkSupervisor, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jComboSup, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125)
                        .addComponent(jComboPengawas, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125)
                        .addComponent(checkKepala, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(text_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(text_alamat, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(text_date, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jComboDivisi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboAhli, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jComboPangkat, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jComboSup, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(checkSupervisor, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jComboPengawas, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(checkKepala, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        button_clear.setBackground(new java.awt.Color(226, 151, 161));
        button_clear.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        button_clear.setText("Clear");
        button_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_clearActionPerformed(evt);
            }
        });

        button_input.setBackground(new java.awt.Color(226, 151, 161));
        button_input.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        button_input.setText("Input");
        button_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_inputActionPerformed(evt);
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

        button_delete.setBackground(new java.awt.Color(226, 151, 161));
        button_delete.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        button_delete.setText("Delete");
        button_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_deleteActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(button_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button_input, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(button_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(button_pilihkaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 666, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(title))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(button_pilihkaryawan)))
                .addGap(12, 12, 12)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(button_delete)
                    .addComponent(button_input)
                    .addComponent(button_clear))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void text_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_namaActionPerformed
    }//GEN-LAST:event_text_namaActionPerformed
    private void text_alamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_alamatActionPerformed
    }//GEN-LAST:event_text_alamatActionPerformed
    private void button_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_clearActionPerformed
        text_nama.setText("");
        text_alamat.setText("");
        jComboDivisi.setSelectedIndex(0);
        jComboAhli.setSelectedItem(0);
        jComboPangkat.setSelectedIndex(0);
        jComboPengawas.setSelectedIndex(0);
        jComboSup.setSelectedIndex(0);
    }//GEN-LAST:event_button_clearActionPerformed
    // Method yang digunakan untuk mereturn ID dengan input Nama Kolom dan Value
    private String getID(String table, String NameCol, String name){
        try{
            sql = "SELECT ID"+table+" AS ID FROM "+table+" WHERE "+NameCol+"='"+name+"'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            return rs.getString("ID");
        }catch(Exception e){
            System.out.println("");
            return "";
        }
    }
    private String getNama(String table, String nama, String idCol, String id){
        try{
            sql = "SELECT "+nama+" as Nama FROM "+table+" WHERE "+idCol+"='"+id+"'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            return rs.getString("Nama");
        }catch(SQLException e){
            return "NULL";
        }
    }
    private void button_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_inputActionPerformed
        try{
            connect();
            
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
            String tglLahir = formatDate.format(text_date.getDate());
            
            // Mendapatkan IDDivisi dari pilihan divisi untuk input IDDivisi
            IDDivisi = getID("Divisi", "NamaDivisi", jComboDivisi.getSelectedItem().toString());
            // Mendapatkan IDKeahlian dari Input Nama Keahlian
            IDKeahlian = getID("Keahlian", "NamaKeahlian", jComboAhli.getSelectedItem().toString()); 
            // Mendapatkan IDPangkat dari Input Nama Pangkat
            IDPangkat = getID("Pangkat", "NamaPangkat", jComboPangkat.getSelectedItem().toString()); 
            // Mendapatkan IDSupervisor dari pilihan supervisor
            if(checkSupervisor.isSelected()){
                IDSupervisor = "NULL";
            } else{
                IDSupervisor = "'"+getID("Karyawan", "Nama", jComboSup.getSelectedItem().toString())+"'";
            }
            // Mendapatkan IDDivisi dari pilihan pengawas untuk input IDPengawas
            if(jComboPengawas.getSelectedItem().toString().equalsIgnoreCase("Bukan Dewan Pengawas")){
                IDPengawas = "NULL";
            } else{
                IDPengawas = "'"+getID("Divisi", "NamaDivisi", jComboPengawas.getSelectedItem().toString())+"'";
            }
            
            // Branching untuk input/update
            if(title.getText().equalsIgnoreCase("Input Karyawan")){ // Kalau title == input
                pst = con.prepareStatement("SELECT IDKaryawan AS ID FROM Karyawan WHERE Nama='"+ text_nama.getText()
                                                                                   +"' and Alamat='"+text_alamat.getText()
                                                                                   +"' and TglLahir='"+tglLahir+"'");
                rs = pst.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Data Sudah Ada");
                } else {
                    inputKaryawan(tglLahir, IDDivisi, IDKeahlian, IDSupervisor, IDPengawas);
                    // Menambahkan data baru ke tabel PangkatKaryawan, regardless input/update 
                    String IDKaryawan = getID("Karyawan", "Nama", text_nama.getText());
                    System.out.println(IDKaryawan);
                    inputPangkatKaryawan(IDPangkat, IDKaryawan);
                    if (checkKepala.isSelected()) {
                        UpdateKepalaDivisi(IDKaryawan, IDDivisi);
                    }
                }                
            } else{   
                String IDKaryawan = getID("Karyawan", "Nama", text_nama.getText());
                updateKaryawan(IDKaryawan, tglLahir, IDDivisi, IDKeahlian, IDSupervisor, IDPengawas);
            }
          
        } catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_button_inputActionPerformed
    private void inputKaryawan(String tglLahir, String div, String ahli, String sup, String awas){
        try{
            sql = "INSERT INTO Karyawan (IDKaryawan, Nama, Alamat, TglLahir, IDDivisi, IDKeahlian, IDSupervisor, IDPengawas, Deleted)"
                            + "VALUES ('"+IDOtomatis()+"','"
                                                 +text_nama.getText()+"','"
                                                 +text_alamat.getText()+"','"
                                                 +tglLahir+"','"
                                                 +div+"','"
                                                 +ahli+"',"
                                                 +sup+","
                                                 +awas+",0)";
                    pst = con.prepareStatement(sql);
                    pst.execute();
            
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
        } catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void UpdateKepalaDivisi(String idk, String idd){
        try{
            sql = "UPDATE Divisi SET IDKepalaDivisi='"+idk+"' WHERE IDDivisi='"+idd+"'";
            pst = con.prepareStatement(sql);
            pst.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    private void updateKaryawan(String ID, String tglLahir, String div, String ahli, String sup, String awas){
        try{
            sql = "UPDATE Karyawan SET Nama='" + text_nama.getText()
                    + "', Alamat='" + text_alamat.getText()
                    + "', tglLahir='" + tglLahir
                    + "', IDDivisi='" + div
                    + "', IDKeahlian='" + ahli
                    + "', IDSupervisor=" + sup
                    + ", IDPengawas=" + awas
                    + " where IDKaryawan='" + ID + "'";
            pst = con.prepareStatement(sql);
            pst.executeUpdate();
            if (checkKepala.isSelected()) {
                UpdateKepalaDivisi(ID, div);
            }
            JOptionPane.showMessageDialog(null, "Update Data Berhasil");
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void inputPangkatKaryawan(String IDPangkat, String IDKaryawan){
        try{
            connect();

            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");  
            Date date = new Date();
            String strDate = formatDate.format(date);
            
            sql = "INSERT INTO PangkatKaryawan VALUES ('"+IDKaryawan+"', '"+IDPangkat+"', '"+strDate+"', 0)";
            pst = con.prepareStatement(sql);
            pst.execute();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Input Data Pangkat Karyawan Tidak Berhasil");
        }
    }
    private void checkSupervisorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkSupervisorItemStateChanged
        jComboSup.setVisible(!checkSupervisor.isSelected());
        invalidate();
        validate();
    }//GEN-LAST:event_checkSupervisorItemStateChanged
    private void checkSupervisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkSupervisorActionPerformed
        
    }//GEN-LAST:event_checkSupervisorActionPerformed
    private void checkKepalaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkKepalaItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_checkKepalaItemStateChanged
    private void checkKepalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkKepalaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkKepalaActionPerformed
    private void jComboSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboSupActionPerformed

    }//GEN-LAST:event_jComboSupActionPerformed
    private void jComboPengawasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboPengawasActionPerformed

    }//GEN-LAST:event_jComboPengawasActionPerformed
    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        dispose();
        Menu a = new Menu();
        a.setVisible(true);
    }//GEN-LAST:event_backMouseClicked
    private void button_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_deleteActionPerformed
        try {
            String IDKaryawan = getID("Karyawan", "Nama", text_nama.getText());
            sql = "UPDATE Karyawan SET Deleted=1 where IDKaryawan='" + IDKaryawan + "'";
            pst = con.prepareStatement(sql);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Hapus Data Berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_button_deleteActionPerformed

    private void button_pilihkaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_pilihkaryawanActionPerformed
        TabelKaryawan tbl = new TabelKaryawan("Ambil Data");
        tbl.setVisible(true);
        dispose();
    }//GEN-LAST:event_button_pilihkaryawanActionPerformed

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
            java.util.logging.Logger.getLogger(Karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Karyawan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back;
    private javax.swing.JButton button_clear;
    private javax.swing.JButton button_delete;
    private javax.swing.JButton button_input;
    private javax.swing.JButton button_pilihkaryawan;
    private javax.swing.JCheckBox checkKepala;
    private javax.swing.JCheckBox checkSupervisor;
    private javax.swing.JComboBox<String> jComboAhli;
    private javax.swing.JComboBox<String> jComboDivisi;
    private javax.swing.JComboBox<String> jComboPangkat;
    private javax.swing.JComboBox<String> jComboPengawas;
    private javax.swing.JComboBox<String> jComboSup;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JTextField text_alamat;
    private com.toedter.calendar.JDateChooser text_date;
    private javax.swing.JTextField text_nama;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
