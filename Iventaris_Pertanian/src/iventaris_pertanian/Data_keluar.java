/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package iventaris_pertanian;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;

public class Data_keluar extends javax.swing.JFrame {

    Connection conn;//
    private DefaultTableModel modelBibitDK;
    private DefaultTableModel modelPupukDK;
    private DefaultTableModel modelTanamanDK;
    
    public Data_keluar() {
        initComponents();
        jumlahbibitdk.setText("0");
        jumlahpupukdk.setText("0");
        jumlahtanamandk.setText("0");
        
        conn = Koneksi.getConnection();
        
        modelBibitDK = new DefaultTableModel();
        tblbibitdk.setModel(modelBibitDK);
        
        modelBibitDK.addColumn("ID");
        modelBibitDK.addColumn("Nama");
        modelBibitDK.addColumn("JUMLAH");
        modelBibitDK.addColumn("BULAN");
        
        modelPupukDK = new DefaultTableModel();
        tblpupukdk.setModel(modelPupukDK);
        
        modelPupukDK.addColumn("ID");
        modelPupukDK.addColumn("NAMA");
        modelPupukDK.addColumn("JUMLAH");
        modelPupukDK.addColumn("BULAN");
        
        modelTanamanDK = new DefaultTableModel();
        tbltanamandk.setModel(modelTanamanDK);
        
        modelTanamanDK.addColumn("ID");
        modelTanamanDK.addColumn("NAMA");
        modelTanamanDK.addColumn("JUMLAH"); 
        modelTanamanDK.addColumn("HARGA");
        modelTanamanDK.addColumn("BULAN");

        loadDataKeluarBibit();
        loadDataKeluarPupuk();
        loadDataKeluarTanaman();
    }
    
     private void loadDataKeluarBibit() {//
        modelBibitDK.setRowCount(0); 
        try {
            String sql = "SELECT * FROM dk_bibit";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                modelBibitDK.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nama_bibitdK"),
                    rs.getString("jumlah_bibitdK"),
                    rs.getString("bulan_bibitdK")
                });
            }
        } catch (SQLException e) {
            System.out.println("Error Load Data Keluar: " + e.getMessage());
        }
    }
    
    private void loadDataKeluarPupuk() {//
        modelPupukDK.setRowCount(0); 
        try {
            String sql = "SELECT * FROM dk_pupuk";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                modelPupukDK.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nama_pupukdk"),
                    rs.getString("jumlah_pupukdk"),
                    rs.getString("bulan_pupukdk")
                });
            }
        } catch (SQLException e) {
            System.out.println("Error Load Data Keluar: " + e.getMessage());
        }
    }
    
    private void loadDataKeluarTanaman() {//
        modelTanamanDK.setRowCount(0); 
        try {
            String sql = "SELECT * FROM dk_tanaman";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                modelTanamanDK.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nama_tanamandk"),
                    rs.getString("jumlah_tanamandk"),
                    rs.getString("harga_tanamandk"),
                    rs.getString("bulan_tanamandk")
                });
            }
        } catch (SQLException e) {
            System.out.println("Error Load Data Keluar: " + e.getMessage());
        }
    }
    
    // Fungsi untuk mengecek apakah ID sudah ada dalam tabel
    private boolean checkIdExists(String tableName, int id) throws SQLException {
        String sql = "SELECT id FROM " + tableName + " WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    
    private void saveDataKeluarBibit() {
    try {
        int id = Integer.parseInt(idbibitdk.getText());
        // Cek apakah ID sudah ada
        if (checkIdExists("dk_bibit", id)) {
            JOptionPane.showMessageDialog(this, "ID sudah ada. Gunakan ID yang berbeda.");
            return;
        }
        
        String sql = "INSERT INTO dk_bibit (id, nama_bibitdk, jumlah_bibitdk, bulan_bibitdk) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.setString(2, namabibitdk.getText());
        ps.setString(3, jumlahbibitdk.getText());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Sesuaikan format
        String tanggal = dateFormat.format(tanggalbibitdk.getDate());
        ps.setString(4, tanggal);
        ps.executeUpdate();
        JOptionPane.showMessageDialog(this, "Data saved successfully");
        loadDataKeluarBibit();
    } catch (SQLException e) {
        System.out.println("Error Save Data: " + e.getMessage());
    }
}
    private void saveDataKeluarPupuk() {
    try {
        int id = Integer.parseInt(idpupukdk.getText());
        // Cek apakah ID sudah ada
        if (checkIdExists("dk_pupuk", id)) {
            JOptionPane.showMessageDialog(this, "ID sudah ada. Gunakan ID yang berbeda.");
            return;
        }
        
        String sql = "INSERT INTO dk_pupuk (id, nama_pupukdk, jumlah_pupukdk,  bulan_pupukdk) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.setString(2, namapupukdk.getText());
        ps.setString(3, jumlahpupukdk.getText());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Sesuaikan format
        String tanggal = dateFormat.format(tanggalpupukdk.getDate());
        ps.setString(4, tanggal);
        ps.executeUpdate();
        JOptionPane.showMessageDialog(this, "Data saved successfully");
        loadDataKeluarPupuk();
    } catch (SQLException e) {
        System.out.println("Error Save Data: " + e.getMessage());
    }
}
    
    private void saveDataKeluarTanaman() {
    try {
        int id = Integer.parseInt(idtanamandk.getText());
        // Cek apakah ID sudah ada
        if (checkIdExists("dk_tanaman", id)) {
            JOptionPane.showMessageDialog(this, "ID sudah ada. Gunakan ID yang berbeda.");
            return;
        }
        
        String sql = "INSERT INTO dk_tanaman (id,nama_tanamandk, jumlah_tanamandk, harga_tanamandk, bulan_tanamandk) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.setString(2, namatanamandk.getText());
        ps.setString(3, jumlahtanamandk.getText());
        ps.setString(4, hargatanamandk.getText());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Sesuaikan format
        String tanggal = dateFormat.format(tanggaltanamandk.getDate());
        ps.setString(5, tanggal);
        ps.executeUpdate();
        JOptionPane.showMessageDialog(this, "Data saved successfully");
        loadDataKeluarTanaman();
    } catch (SQLException e) {
        System.out.println("Error Save Data: " + e.getMessage());
    }
}

    
    private void updateDataKeluarBibit() {
    try {
        String sql = "UPDATE dk_bibit SET nama_bibitdk = ?, jumlah_bibitdk = ?, bulan_bibitdk = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, namabibitdk.getText()); // Nama bibit
        ps.setString(2, jumlahbibitdk.getText()); // Jumlah bibit
        
        // Menggunakan JDateChooser untuk mengambil tanggal
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Sesuaikan format
        String tanggal = dateFormat.format(tanggalbibitdk.getDate());
        ps.setString(3, tanggal); // Bulan bibit (tanggal)

        ps.setInt(4, Integer.parseInt(idbibitdk.getText())); // ID bibit

        ps.executeUpdate();
        JOptionPane.showMessageDialog(this, "Data updated successfully");
        loadDataKeluarBibit();
    } catch (SQLException e) {
        System.out.println("Error Update Data: " + e.getMessage());
    }
}

    
//    private void updateDataKeluarPupuk() {
//        try {
//            String sql = "UPDATE dk_pupuk SET nama_pupukdk = ?, jumlah_pupukdk = ? , bulan_pupukdk= ? WHERE id = ?";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1, namapupukdk.getText());
//            ps.setString(2, jumlahpupukdk.getText());
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Sesuaikan format
//            String tanggal = dateFormat.format(tanggalpupukdk.getDate());
//            ps.setString(3, tanggal);
//            ps.setInt(6, Integer.parseInt(idpupukdk.getText()));
//            ps.executeUpdate();
//            JOptionPane.showMessageDialog(this, "Data updated successfully");
//            loadDataKeluarPupuk();
//        }catch (SQLException e) {
//            System.out.println("Error Save Data" + e.getMessage());
//        }
//    }
    
    private void updateDataKeluarPupuk() {
    try {
        String sql = "UPDATE dk_pupuk SET nama_pupukdk = ?, jumlah_pupukdk = ?, bulan_pupukdk = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, namapupukdk.getText()); // Nama pupuk
        ps.setString(2, jumlahpupukdk.getText()); // Jumlah pupuk
        
        // Menggunakan JDateChooser untuk mengambil tanggal
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Format tanggal
        String tanggal = dateFormat.format(tanggalpupukdk.getDate());
        ps.setString(3, tanggal); // Bulan pupuk (tanggal)

        ps.setInt(4, Integer.parseInt(idpupukdk.getText())); // ID pupuk

        ps.executeUpdate();
        JOptionPane.showMessageDialog(this, "Data updated successfully");
        loadDataKeluarPupuk();
    } catch (SQLException e) {
        System.out.println("Error Update Data: " + e.getMessage());
    }
}

    private void updateDataKeluarTanaman() {
        try {
            String sql = "UPDATE dk_tanaman SET nama_tanamandk = ?, jumlah_tanamandk = ?, harga_tanamandk = ?, bulan_tanamandk = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, namatanamandk.getText());
            ps.setString(2, jumlahtanamandk.getText());
            ps.setString(3, hargatanamandk.getText());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Sesuaikan format
            String tanggal = dateFormat.format(tanggaltanamandk.getDate());
            ps.setString(4, tanggal);
            ps.setInt(5, Integer.parseInt(idtanamandk.getText()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data updated successfully");
            loadDataKeluarTanaman();
        }catch (SQLException e) {
            System.out.println("Error Save Data" + e.getMessage());
        }
    }
    
    private void deleteDataKeluarBibit() {
     try  {
          String sql = "DELETE FROM dk_bibit WHERE id = ?";
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setInt(1, Integer.parseInt(idbibitdk.getText()));
          ps.executeUpdate();
          JOptionPane.showMessageDialog(this, "Data deleted successfully");
          loadDataKeluarBibit();
     } catch (SQLException e) {
          System.out.println("Error Save Data" + e.getMessage());
      }
    }
    
    private void deleteDataKeluarPupuk() {
     try  {
          String sql = "DELETE FROM dk_pupuk WHERE id = ?";
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setInt(1, Integer.parseInt(idpupukdk.getText()));
          ps.executeUpdate();
          JOptionPane.showMessageDialog(this, "Data deleted successfully");
          loadDataKeluarPupuk();
     } catch (SQLException e) {
          System.out.println("Error Save Data" + e.getMessage());
      }
    }
    
    private void deleteDataKeluarTanaman() {
     try  {
          String sql = "DELETE FROM dk_tanaman WHERE id = ?";
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setInt(1, Integer.parseInt(idtanamandk.getText()));
          ps.executeUpdate();
          JOptionPane.showMessageDialog(this, "Data deleted successfully");
          loadDataKeluarTanaman();
     } catch (SQLException e) {
          System.out.println("Error Save Data" + e.getMessage());
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
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        idbibitdk = new javax.swing.JTextField();
        namabibitdk = new javax.swing.JTextField();
        jumlahbibitdk = new javax.swing.JTextField();
        btntambahbibitdk = new javax.swing.JButton();
        btnkurangbibitdk = new javax.swing.JButton();
        tanggalbibitdk = new com.toedter.calendar.JDateChooser();
        jPanel7 = new javax.swing.JPanel();
        tambah4 = new javax.swing.JButton();
        update4 = new javax.swing.JButton();
        delete4 = new javax.swing.JButton();
        kembali4 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblbibitdk = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        idpupukdk = new javax.swing.JTextField();
        namapupukdk = new javax.swing.JTextField();
        jumlahpupukdk = new javax.swing.JTextField();
        btntambahpupukdk = new javax.swing.JButton();
        btnkurangpupukdk = new javax.swing.JButton();
        tanggalpupukdk = new com.toedter.calendar.JDateChooser();
        jPanel11 = new javax.swing.JPanel();
        tambah5 = new javax.swing.JButton();
        update5 = new javax.swing.JButton();
        delete5 = new javax.swing.JButton();
        kembali5 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblpupukdk = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        idtanamandk = new javax.swing.JTextField();
        namatanamandk = new javax.swing.JTextField();
        jumlahtanamandk = new javax.swing.JTextField();
        btntambahdk6 = new javax.swing.JButton();
        btnkurangdk6 = new javax.swing.JButton();
        tanggaltanamandk = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        hargatanamandk = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        tambah6 = new javax.swing.JButton();
        update6 = new javax.swing.JButton();
        delete6 = new javax.swing.JButton();
        kembali6 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbltanamandk = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(179, 201, 156));

        jLabel1.setFont(new java.awt.Font("Swis721 Blk BT", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Data Keluar");

        jDesktopPane1.setBackground(new java.awt.Color(97, 130, 100));

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.setBackground(new java.awt.Color(221, 255, 187));

        jPanel2.setBackground(new java.awt.Color(179, 201, 156));
        jPanel2.setLayout(new java.awt.GridLayout(2, 1, 3, 3));

        jPanel5.setBackground(new java.awt.Color(179, 201, 156));
        jPanel5.setLayout(new java.awt.GridLayout(1, 2, 3, 3));

        jPanel8.setBackground(new java.awt.Color(199, 233, 176));
        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jLabel2.setText("id bibit");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel8.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jLabel3.setText("Nama bibit");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel8.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jLabel4.setText("Jumlah");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel8.add(jLabel4, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jLabel5.setText("Bulan pemakaian");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel8.add(jLabel5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 150;
        jPanel8.add(idbibitdk, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 150;
        jPanel8.add(namabibitdk, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 44;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 2);
        jPanel8.add(jumlahbibitdk, gridBagConstraints);

        btntambahbibitdk.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        btntambahbibitdk.setText("+");
        btntambahbibitdk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahbibitdkActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel8.add(btntambahbibitdk, gridBagConstraints);

        btnkurangbibitdk.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        btnkurangbibitdk.setText("-");
        btnkurangbibitdk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkurangbibitdkActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel8.add(btnkurangbibitdk, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 50;
        jPanel8.add(tanggalbibitdk, gridBagConstraints);

        jPanel5.add(jPanel8);

        jPanel7.setBackground(new java.awt.Color(199, 233, 176));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tambah4.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        tambah4.setText("Tambah");
        tambah4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambah4ActionPerformed(evt);
            }
        });
        jPanel7.add(tambah4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        update4.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        update4.setText("Update");
        update4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update4ActionPerformed(evt);
            }
        });
        jPanel7.add(update4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 80, -1));

        delete4.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        delete4.setText("Delete");
        delete4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete4ActionPerformed(evt);
            }
        });
        jPanel7.add(delete4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 82, -1));

        kembali4.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        kembali4.setText("Kembali");
        kembali4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembali4ActionPerformed(evt);
            }
        });
        jPanel7.add(kembali4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, -1, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Downloads\\akodok-removebg-preview_11zon.png")); // NOI18N
        jPanel7.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, -1, 140));

        jPanel5.add(jPanel7);

        jPanel2.add(jPanel5);

        jPanel6.setBackground(new java.awt.Color(199, 233, 176));

        tblbibitdk.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblbibitdk);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel6);

        jTabbedPane1.addTab("Bibit", jPanel2);

        jPanel3.setBackground(new java.awt.Color(179, 201, 156));
        jPanel3.setLayout(new java.awt.GridLayout(2, 1, 3, 3));

        jPanel9.setBackground(new java.awt.Color(179, 201, 156));
        jPanel9.setLayout(new java.awt.GridLayout(1, 2, 3, 3));

        jPanel12.setBackground(new java.awt.Color(199, 233, 176));
        jPanel12.setLayout(new java.awt.GridBagLayout());

        jLabel6.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jLabel6.setText("id_pupuk");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel12.add(jLabel6, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jLabel7.setText("Nama pupuk");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel12.add(jLabel7, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jLabel8.setText("Jumlah");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel12.add(jLabel8, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jLabel9.setText("Bulan pemakaian");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel12.add(jLabel9, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 4, 0);
        jPanel12.add(idpupukdk, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 4, 0);
        jPanel12.add(namapupukdk, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 44;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 4, 0);
        jPanel12.add(jumlahpupukdk, gridBagConstraints);

        btntambahpupukdk.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        btntambahpupukdk.setText("+");
        btntambahpupukdk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahpupukdkActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 0);
        jPanel12.add(btntambahpupukdk, gridBagConstraints);

        btnkurangpupukdk.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        btnkurangpupukdk.setText("-");
        btnkurangpupukdk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkurangpupukdkActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel12.add(btnkurangpupukdk, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 0);
        jPanel12.add(tanggalpupukdk, gridBagConstraints);

        jPanel9.add(jPanel12);

        jPanel11.setBackground(new java.awt.Color(199, 233, 176));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tambah5.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        tambah5.setText("Tambah");
        tambah5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambah5ActionPerformed(evt);
            }
        });
        jPanel11.add(tambah5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, -1));

        update5.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        update5.setText("Update");
        update5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update5ActionPerformed(evt);
            }
        });
        jPanel11.add(update5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 80, -1));

        delete5.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        delete5.setText("Delete");
        delete5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete5ActionPerformed(evt);
            }
        });
        jPanel11.add(delete5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 80, -1));

        kembali5.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        kembali5.setText("Kembali");
        kembali5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembali5ActionPerformed(evt);
            }
        });
        jPanel11.add(kembali5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, -1, -1));

        jLabel16.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Downloads\\abebek-removebg-preview_11zon (1).png")); // NOI18N
        jPanel11.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 250, 240));

        jPanel9.add(jPanel11);

        jPanel3.add(jPanel9);

        jPanel10.setBackground(new java.awt.Color(199, 233, 176));

        tblpupukdk.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblpupukdk);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel10);

        jTabbedPane1.addTab("Pupuk", jPanel3);

        jPanel4.setBackground(new java.awt.Color(179, 201, 156));
        jPanel4.setLayout(new java.awt.GridLayout(2, 1, 3, 3));

        jPanel13.setBackground(new java.awt.Color(179, 201, 156));
        jPanel13.setLayout(new java.awt.GridLayout(1, 2, 3, 3));

        jPanel16.setBackground(new java.awt.Color(199, 233, 176));
        jPanel16.setLayout(new java.awt.GridBagLayout());

        jLabel10.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jLabel10.setText("id_tanaman");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel16.add(jLabel10, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jLabel11.setText("Nama tanaman");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel16.add(jLabel11, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jLabel12.setText("Jumlah");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel16.add(jLabel12, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jLabel13.setText("Bulan keluar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel16.add(jLabel13, gridBagConstraints);

        idtanamandk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idtanamandkActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 4, 0);
        jPanel16.add(idtanamandk, gridBagConstraints);

        namatanamandk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namatanamandkActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 4, 0);
        jPanel16.add(namatanamandk, gridBagConstraints);

        jumlahtanamandk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahtanamandkActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 47;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 4, 0);
        jPanel16.add(jumlahtanamandk, gridBagConstraints);

        btntambahdk6.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        btntambahdk6.setText("+");
        btntambahdk6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahdk6ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel16.add(btntambahdk6, gridBagConstraints);

        btnkurangdk6.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        btnkurangdk6.setText("-");
        btnkurangdk6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkurangdk6ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel16.add(btnkurangdk6, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel16.add(tanggaltanamandk, gridBagConstraints);

        jLabel14.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jLabel14.setText("Harga");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel16.add(jLabel14, gridBagConstraints);

        hargatanamandk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargatanamandkActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 4, 0);
        jPanel16.add(hargatanamandk, gridBagConstraints);

        jPanel13.add(jPanel16);

        jPanel15.setBackground(new java.awt.Color(199, 233, 176));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tambah6.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        tambah6.setText("Tambah");
        tambah6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambah6ActionPerformed(evt);
            }
        });
        jPanel15.add(tambah6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 90, -1));

        update6.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        update6.setText("Update");
        update6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update6ActionPerformed(evt);
            }
        });
        jPanel15.add(update6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 90, -1));

        delete6.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        delete6.setText("Delete");
        delete6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete6ActionPerformed(evt);
            }
        });
        jPanel15.add(delete6, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 110, 90, -1));

        kembali6.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        kembali6.setText("Kembali");
        kembali6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembali6ActionPerformed(evt);
            }
        });
        jPanel15.add(kembali6, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 140, 90, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Downloads\\aa-removebg-preview_11zon.png")); // NOI18N
        jPanel15.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, -1, 200));

        jPanel13.add(jPanel15);

        jPanel4.add(jPanel13);

        jPanel14.setBackground(new java.awt.Color(199, 233, 176));

        tbltanamandk.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tbltanamandk);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 695, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel14);

        jTabbedPane1.addTab("Tanaman", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDesktopPane1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(285, 285, 285)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDesktopPane1)
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void idtanamandkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idtanamandkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idtanamandkActionPerformed

    private void namatanamandkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namatanamandkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namatanamandkActionPerformed

    private void jumlahtanamandkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahtanamandkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlahtanamandkActionPerformed

    private void kembali6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembali6ActionPerformed
        // TODO add your handling code here:
        new Home().setVisible(true);
        dispose();
    }//GEN-LAST:event_kembali6ActionPerformed

    private void kembali5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembali5ActionPerformed
        // TODO add your handling code here:
        new Home().setVisible(true);
        dispose();
    }//GEN-LAST:event_kembali5ActionPerformed

    private void kembali4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembali4ActionPerformed
        // TODO add your handling code here:
        new Home().setVisible(true);
        dispose();
    }//GEN-LAST:event_kembali4ActionPerformed

    private void tambah6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambah6ActionPerformed
        // TODO add your handling code here:
        saveDataKeluarTanaman();
    }//GEN-LAST:event_tambah6ActionPerformed

    private void update6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update6ActionPerformed
        // TODO add your handling code here:
        updateDataKeluarTanaman();
    }//GEN-LAST:event_update6ActionPerformed

    private void delete6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete6ActionPerformed
        // TODO add your handling code here:
        deleteDataKeluarTanaman();
    }//GEN-LAST:event_delete6ActionPerformed

    private void tambah5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambah5ActionPerformed
        // TODO add your handling code here:
        saveDataKeluarPupuk();
    }//GEN-LAST:event_tambah5ActionPerformed

    private void update5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update5ActionPerformed
        // TODO add your handling code here:
        updateDataKeluarPupuk();
    }//GEN-LAST:event_update5ActionPerformed

    private void delete5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete5ActionPerformed
        // TODO add your handling code here:
        deleteDataKeluarPupuk();
    }//GEN-LAST:event_delete5ActionPerformed

    private void tambah4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambah4ActionPerformed
        // TODO add your handling code here:
        saveDataKeluarBibit();
    }//GEN-LAST:event_tambah4ActionPerformed

    private void update4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update4ActionPerformed
        // TODO add your handling code here:
        updateDataKeluarBibit();
    }//GEN-LAST:event_update4ActionPerformed

    private void delete4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete4ActionPerformed
        // TODO add your handling code here:
        deleteDataKeluarBibit();
    }//GEN-LAST:event_delete4ActionPerformed

    private void btntambahbibitdkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahbibitdkActionPerformed
        // TODO add your handling code here:
    int jumlah = Integer.parseInt(jumlahbibitdk.getText());
    jumlahbibitdk.setText(String.valueOf(jumlah + 1)); // Menambah 1 pada jumlah bibit
    }//GEN-LAST:event_btntambahbibitdkActionPerformed

    private void btntambahpupukdkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahpupukdkActionPerformed
        // TODO add your handling code here:
        int jumlah = Integer.parseInt(jumlahpupukdk.getText());
        jumlahpupukdk.setText(String.valueOf(jumlah + 1));
    }//GEN-LAST:event_btntambahpupukdkActionPerformed

    private void btntambahdk6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahdk6ActionPerformed
        // TODO add your handling code here:
        int jumlah = Integer.parseInt(jumlahtanamandk.getText());
        jumlahtanamandk.setText(String.valueOf(jumlah + 1));
    }//GEN-LAST:event_btntambahdk6ActionPerformed

    private void btnkurangbibitdkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkurangbibitdkActionPerformed
        // TODO add your handling code here:
        int jumlah = Integer.parseInt(jumlahbibitdk.getText());
        if (jumlah > 1){
            jumlahbibitdk.setText(String.valueOf(jumlah - 1));
        }else{
           JOptionPane.showMessageDialog(this, "Jumlah minimal adalah 1", "Peringatan", JOptionPane.WARNING_MESSAGE); 
        }
    }//GEN-LAST:event_btnkurangbibitdkActionPerformed

    private void btnkurangpupukdkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkurangpupukdkActionPerformed
        // TODO add your handling code here:
        int jumlah = Integer.parseInt(jumlahpupukdk.getText());
        if (jumlah > 1){
            jumlahpupukdk.setText(String.valueOf(jumlah - 1));
        }else{
           JOptionPane.showMessageDialog(this, "Jumlah minimal adalah 1", "Peringatan", JOptionPane.WARNING_MESSAGE); 
        }
    }//GEN-LAST:event_btnkurangpupukdkActionPerformed

    private void btnkurangdk6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkurangdk6ActionPerformed
        // TODO add your handling code here:
        int jumlah = Integer.parseInt(jumlahtanamandk.getText());
        if (jumlah > 1){
            jumlahtanamandk.setText(String.valueOf(jumlah - 1));
        }else{
           JOptionPane.showMessageDialog(this, "Jumlah minimal adalah 1", "Peringatan", JOptionPane.WARNING_MESSAGE); 
        }
    }//GEN-LAST:event_btnkurangdk6ActionPerformed

    private void hargatanamandkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargatanamandkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hargatanamandkActionPerformed

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
            java.util.logging.Logger.getLogger(Data_keluar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Data_keluar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Data_keluar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Data_keluar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Data_keluar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnkurangbibitdk;
    private javax.swing.JButton btnkurangdk6;
    private javax.swing.JButton btnkurangpupukdk;
    private javax.swing.JButton btntambahbibitdk;
    private javax.swing.JButton btntambahdk6;
    private javax.swing.JButton btntambahpupukdk;
    private javax.swing.JButton delete4;
    private javax.swing.JButton delete5;
    private javax.swing.JButton delete6;
    private javax.swing.JTextField hargatanamandk;
    private javax.swing.JTextField idbibitdk;
    private javax.swing.JTextField idpupukdk;
    private javax.swing.JTextField idtanamandk;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jumlahbibitdk;
    private javax.swing.JTextField jumlahpupukdk;
    private javax.swing.JTextField jumlahtanamandk;
    private javax.swing.JButton kembali4;
    private javax.swing.JButton kembali5;
    private javax.swing.JButton kembali6;
    private javax.swing.JTextField namabibitdk;
    private javax.swing.JTextField namapupukdk;
    private javax.swing.JTextField namatanamandk;
    private javax.swing.JButton tambah4;
    private javax.swing.JButton tambah5;
    private javax.swing.JButton tambah6;
    private com.toedter.calendar.JDateChooser tanggalbibitdk;
    private com.toedter.calendar.JDateChooser tanggalpupukdk;
    private com.toedter.calendar.JDateChooser tanggaltanamandk;
    private javax.swing.JTable tblbibitdk;
    private javax.swing.JTable tblpupukdk;
    private javax.swing.JTable tbltanamandk;
    private javax.swing.JButton update4;
    private javax.swing.JButton update5;
    private javax.swing.JButton update6;
    // End of variables declaration//GEN-END:variables
}
