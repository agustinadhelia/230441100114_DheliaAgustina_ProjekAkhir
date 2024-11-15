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

public class Data_Masuk extends javax.swing.JFrame {

    Connection conn;//
    private DefaultTableModel modelBibit;
    private DefaultTableModel modelPupuk;
    private DefaultTableModel modelTanaman;
   
    public Data_Masuk() {
        initComponents();
        jumlahbibitDM.setText("0");
        jumlahpupukDM.setText("0");
        jumlahtanamanDM.setText("0");
        
        conn = Koneksi.getConnection();
        
        modelBibit = new DefaultTableModel();
        tblbibitDM.setModel(modelBibit);
        
        modelBibit.addColumn("ID");
        modelBibit.addColumn("Nama");
        modelBibit.addColumn("JUMLAH");
        modelBibit.addColumn("HARGA");
        modelBibit.addColumn("NAMA_PEMASOK");
        modelBibit.addColumn("Tanggal");
        
        modelPupuk = new DefaultTableModel();
        tblpupukDM.setModel(modelPupuk);
        
        modelPupuk.addColumn("ID");
        modelPupuk.addColumn("NAMA");
        modelPupuk.addColumn("JUMLAH");
        modelPupuk.addColumn("HARGA");
        modelPupuk.addColumn("NAMA_PEMASOK");
        modelPupuk.addColumn("Tanggal");
        
        modelTanaman = new DefaultTableModel();
        tblTanamanDM.setModel(modelTanaman);
        
        modelTanaman.addColumn("ID");
        modelTanaman.addColumn("NAMA");
        modelTanaman.addColumn("JUMLAH"); 
        modelTanaman.addColumn("Tanggal");

        loadDataMasukBibit();
        loadDataMasukPupuk();
        loadDataMasukTanaman();

    }
    
    private void loadDataMasukBibit() {//
        modelBibit.setRowCount(0); 
        try {
            String sql = "SELECT * FROM dm_bibit";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                modelBibit.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nama_bibitdm"),
                    rs.getString("jumlah_bibitdm"),
                    rs.getString("harga_bibitdm"),
                    rs.getString("nama_pemasokbibitdm"),
                    rs.getString("bulan_bibitdm")
                });
            }
        } catch (SQLException e) {
            System.out.println("Error Load Data Karyawan: " + e.getMessage());
        }
    }
    
    private void loadDataMasukPupuk() {//
        modelPupuk.setRowCount(0); 
        try {
            String sql = "SELECT * FROM dm_pupuk";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                modelPupuk.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nama_pupukdm"),
                    rs.getString("jumlah_pupukdm"),
                    rs.getString("harga_pupukdm"),
                    rs.getString("nama_pemasokpupukdm"),
                    rs.getString("bulan_pupukdm")
                });
            }
        } catch (SQLException e) {
            System.out.println("Error Load Data Karyawan: " + e.getMessage());
        }
    }
    
    private void loadDataMasukTanaman() {//
        modelTanaman.setRowCount(0); 
        try {
            String sql = "SELECT * FROM dm_tanaman";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                modelTanaman.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nama_tanamandm"),
                    rs.getString("jumlah_tanamandm"),
                    rs.getString("bulan_tanamandm")
                });
            }
        } catch (SQLException e) {
            System.out.println("Error Load Data Karyawan: " + e.getMessage());
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

    
    private void saveDataMasukBibit() {
    try {
        int id = Integer.parseInt(idbibitDM.getText());
        // Cek apakah ID sudah ada
        if (checkIdExists("dm_bibit", id)) {
            JOptionPane.showMessageDialog(this, "ID sudah ada. Gunakan ID yang berbeda.");
            return;
        }
        
       String sql = "INSERT INTO dm_bibit (id, nama_bibitdm, jumlah_bibitdm, harga_bibitdm, nama_pemasokbibitdm, bulan_bibitdm) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.setString(2, namabibitDM.getText());
        ps.setString(3, jumlahbibitDM.getText());
        ps.setString(4, hargabibitDM.getText());
        ps.setString(5, namapemasokbibitDM.getText());

        // Menggunakan JDateChooser untuk mengambil tanggal
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Sesuaikan format
        String tanggal = dateFormat.format(tanggalbibitDM.getDate());
        ps.setString(6, tanggal);

        ps.executeUpdate();
        JOptionPane.showMessageDialog(this, "Data saved successfully");
        loadDataMasukBibit();
    } catch (SQLException e) {
        System.out.println("Error Save Data: " + e.getMessage());
    }
}

    private void saveDataMasukPupuk() {
    try {
        int id = Integer.parseInt(idpupukDM.getText());
        // Cek apakah ID sudah ada
        if (checkIdExists("dm_pupuk", id)) {
            JOptionPane.showMessageDialog(this, "ID sudah ada. Gunakan ID yang berbeda.");
            return;
        }
        
        String sql = "INSERT INTO dm_pupuk (id, nama_pupukdm, jumlah_pupukdm, harga_pupukdm, nama_pemasokpupukdm, bulan_pupukdm) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.setString(2, namapupukDM.getText());
        ps.setString(3, jumlahpupukDM.getText());
        ps.setString(4, hargapupukDM.getText());
        ps.setString(5, namapemasokpupukDM.getText());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Sesuaikan format
        String tanggal = dateFormat.format(tanggalpupukDM.getDate());
        ps.setString(6, tanggal);
        ps.executeUpdate();
        JOptionPane.showMessageDialog(this, "Data saved successfully");
        loadDataMasukPupuk();
    } catch (SQLException e) {
        System.out.println("Error Save Data: " + e.getMessage());
    }
}
    
   private void saveDataMasukTanaman() {
    try {
        int id = Integer.parseInt(idtanamanDM.getText());
        // Cek apakah ID sudah ada
        if (checkIdExists("dm_tanaman", id)) {
            JOptionPane.showMessageDialog(this, "ID sudah ada. Gunakan ID yang berbeda.");
            return;
        }
        
        String sql = "INSERT INTO dm_tanaman (id, nama_tanamandm, jumlah_tanamandm, bulan_tanamandm) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.setString(2, namatanamanDM.getText());
        ps.setString(3, jumlahtanamanDM.getText());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Sesuaikan format
        String tanggal = dateFormat.format(tanggaltanamanDM.getDate());
        ps.setString(4, tanggal);
        ps.executeUpdate();
        JOptionPane.showMessageDialog(this, "Data saved successfully");
        loadDataMasukTanaman();
    } catch (SQLException e) {
        System.out.println("Error Save Data: " + e.getMessage());
    }
}


    
    private void updateDataMasukBibit() {
        try {
            String sql = "UPDATE dm_bibit SET nama_bibitdm = ?, jumlah_bibitdm = ? , harga_bibitdm= ?, nama_pemasokbibitdm= ?, bulan_bibitdm= ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, namabibitDM.getText());
            ps.setString(2, jumlahbibitDM.getText());
            ps.setString(3, hargabibitDM.getText());
            ps.setString(4, namapemasokbibitDM.getText());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Sesuaikan format
            String tanggal = dateFormat.format(tanggalbibitDM.getDate());
            ps.setString(5, tanggal);
            ps.setInt(6, Integer.parseInt(idbibitDM.getText()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data updated successfully");
            loadDataMasukBibit();
        }catch (SQLException e) {
            System.out.println("Error Save Data" + e.getMessage());
        }
    }
    
    private void updateDataMasukPupuk() {
        try {
            String sql = "UPDATE dm_pupuk SET nama_pupukdm = ?, jumlah_pupukdm = ? , harga_pupukdm= ?, nama_pemasokpupukdm= ?, bulan_pupukdm= ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, namapupukDM.getText());
            ps.setString(2, jumlahpupukDM.getText());
            ps.setString(3, hargapupukDM.getText());
            ps.setString(4, namapemasokpupukDM.getText());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Sesuaikan format
            String tanggal = dateFormat.format(tanggalpupukDM.getDate());
            ps.setString(5, tanggal);
            ps.setInt(6, Integer.parseInt(idpupukDM.getText()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data updated successfully");
            loadDataMasukPupuk();
        }catch (SQLException e) {
            System.out.println("Error Save Data" + e.getMessage());
        }
    }
    
    private void updateDataMasukTanaman() {
        try {
            String sql = "UPDATE dm_tanaman SET nama_tanamandm = ?, jumlah_tanamandm = ? , bulan_tanamandm= ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, namatanamanDM.getText());
            ps.setString(2, jumlahtanamanDM.getText());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Sesuaikan format
            String tanggal = dateFormat.format(tanggaltanamanDM.getDate());
            ps.setString(3, tanggal);
            ps.setInt(4, Integer.parseInt(idtanamanDM.getText()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data updated successfully");
            loadDataMasukTanaman();
        }catch (SQLException e) {
            System.out.println("Error Save Data" + e.getMessage());
        }
    }
    
    private void deleteDataMasukBibit() {
     try  {
          String sql = "DELETE FROM dm_bibit WHERE id = ?";
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setInt(1, Integer.parseInt(idbibitDM.getText()));
          ps.executeUpdate();
          JOptionPane.showMessageDialog(this, "Data deleted successfully");
          loadDataMasukBibit();
     } catch (SQLException e) {
          System.out.println("Error Save Data" + e.getMessage());
      }
    }
    
    private void deleteDataMasukPupuk() {
     try  {
          String sql = "DELETE FROM dm_pupuk WHERE id = ?";
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setInt(1, Integer.parseInt(idpupukDM.getText()));
          ps.executeUpdate();
          JOptionPane.showMessageDialog(this, "Data deleted successfully");
          loadDataMasukPupuk();
     } catch (SQLException e) {
          System.out.println("Error Save Data" + e.getMessage());
      }
    }
    
    private void deleteDataMasukTanaman() {
     try  {
          String sql = "DELETE FROM dm_tanaman WHERE id = ?";
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setInt(1, Integer.parseInt(idtanamanDM.getText()));
          ps.executeUpdate();
          JOptionPane.showMessageDialog(this, "Data deleted successfully");
          loadDataMasukTanaman();
     } catch (SQLException e) {
          System.out.println("Error Save Data" + e.getMessage());
      }
    }
    
    
    ////////////////////////////////////////////////////////
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        idbibitDM = new javax.swing.JTextField();
        namabibitDM = new javax.swing.JTextField();
        jumlahbibitDM = new javax.swing.JTextField();
        hargabibitDM = new javax.swing.JTextField();
        namapemasokbibitDM = new javax.swing.JTextField();
        btntambah1 = new javax.swing.JButton();
        btnkurang1 = new javax.swing.JButton();
        tanggalbibitDM = new com.toedter.calendar.JDateChooser();
        jPanel7 = new javax.swing.JPanel();
        tambah1 = new javax.swing.JButton();
        update1 = new javax.swing.JButton();
        hapus1 = new javax.swing.JButton();
        kembali1 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblbibitDM = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        idpupukDM = new javax.swing.JTextField();
        namapupukDM = new javax.swing.JTextField();
        jumlahpupukDM = new javax.swing.JTextField();
        hargapupukDM = new javax.swing.JTextField();
        namapemasokpupukDM = new javax.swing.JTextField();
        btntambah2 = new javax.swing.JButton();
        btnkurang2 = new javax.swing.JButton();
        tanggalpupukDM = new com.toedter.calendar.JDateChooser();
        jPanel11 = new javax.swing.JPanel();
        tambah2 = new javax.swing.JButton();
        update2 = new javax.swing.JButton();
        delete2 = new javax.swing.JButton();
        kembali2 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblpupukDM = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        idtanamanDM = new javax.swing.JTextField();
        namatanamanDM = new javax.swing.JTextField();
        jumlahtanamanDM = new javax.swing.JTextField();
        btnTambah3 = new javax.swing.JButton();
        btnKurang3 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        tanggaltanamanDM = new com.toedter.calendar.JDateChooser();
        jPanel15 = new javax.swing.JPanel();
        tambah3 = new javax.swing.JButton();
        update3 = new javax.swing.JButton();
        hapus3 = new javax.swing.JButton();
        kembali3 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTanamanDM = new javax.swing.JTable();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(97, 130, 100));

        jLabel1.setFont(new java.awt.Font("Swis721 Blk BT", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Data Masuk");

        jDesktopPane1.setBackground(new java.awt.Color(97, 130, 100));

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 12, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.setBackground(new java.awt.Color(176, 217, 177));

        jPanel2.setBackground(new java.awt.Color(97, 130, 100));
        jPanel2.setLayout(new java.awt.GridLayout(2, 1, 3, 3));

        jPanel5.setBackground(new java.awt.Color(97, 130, 100));
        jPanel5.setLayout(new java.awt.GridLayout(1, 2, 3, 3));

        jPanel8.setBackground(new java.awt.Color(121, 172, 120));
        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel8.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("id_bibit");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel8.add(jLabel8, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Nama_bibit");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel8.add(jLabel9, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Jumlah");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel8.add(jLabel10, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Harga");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel8.add(jLabel11, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Nama pemasok");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel8.add(jLabel12, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Tanggal beli");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel8.add(jLabel13, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel8.add(idbibitDM, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 4, 0);
        jPanel8.add(namabibitDM, gridBagConstraints);

        jumlahbibitDM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahbibitDMActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 47;
        gridBagConstraints.insets = new java.awt.Insets(0, 17, 4, 8);
        jPanel8.add(jumlahbibitDM, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 4, 0);
        jPanel8.add(hargabibitDM, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 4, 0);
        jPanel8.add(namapemasokbibitDM, gridBagConstraints);

        btntambah1.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        btntambah1.setText("+");
        btntambah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambah1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel8.add(btntambah1, gridBagConstraints);

        btnkurang1.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        btnkurang1.setText("-");
        btnkurang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkurang1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel8.add(btnkurang1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel8.add(tanggalbibitDM, gridBagConstraints);

        jPanel5.add(jPanel8);

        jPanel7.setBackground(new java.awt.Color(121, 172, 120));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tambah1.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        tambah1.setText("Tambah");
        tambah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambah1ActionPerformed(evt);
            }
        });
        jPanel7.add(tambah1, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 64, -1, -1));

        update1.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        update1.setText("Update");
        update1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update1ActionPerformed(evt);
            }
        });
        jPanel7.add(update1, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 120, 82, -1));

        hapus1.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        hapus1.setText("Hapus");
        hapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapus1ActionPerformed(evt);
            }
        });
        jPanel7.add(hapus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 92, 82, -1));

        kembali1.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        kembali1.setText("Kembali");
        kembali1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembali1ActionPerformed(evt);
            }
        });
        jPanel7.add(kembali1, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 148, -1, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Downloads\\totoro-removebg-preview_11zon (2).png")); // NOI18N
        jPanel7.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 280, 140));

        jLabel18.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Downloads\\jamur-removebg-preview_11zon.png")); // NOI18N
        jPanel7.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, -1, 60));

        jLabel20.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Downloads\\jamur-removebg-preview_11zon.png")); // NOI18N
        jPanel7.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, -1, 60));

        jLabel21.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Downloads\\lampu1-removebg-preview_11zon.png")); // NOI18N
        jPanel7.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 90, 110));

        jPanel5.add(jPanel7);

        jPanel2.add(jPanel5);

        jPanel6.setBackground(new java.awt.Color(121, 172, 120));

        tblbibitDM.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblbibitDM);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
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

        jPanel3.setBackground(new java.awt.Color(97, 130, 100));
        jPanel3.setLayout(new java.awt.GridLayout(2, 1, 3, 3));

        jPanel9.setBackground(new java.awt.Color(97, 130, 100));
        jPanel9.setLayout(new java.awt.GridLayout(1, 2, 3, 3));

        jPanel12.setBackground(new java.awt.Color(121, 172, 120));
        jPanel12.setLayout(new java.awt.GridBagLayout());

        jLabel5.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("id_pupuk");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel12.add(jLabel5, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nama pupuk");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel12.add(jLabel6, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Jumlah");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel12.add(jLabel7, gridBagConstraints);

        jLabel14.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Harga");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel12.add(jLabel14, gridBagConstraints);

        jLabel15.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Nama pemasok");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel12.add(jLabel15, gridBagConstraints);

        jLabel16.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Tanggal beli");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel12.add(jLabel16, gridBagConstraints);

        idpupukDM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idpupukDMActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 4, 0);
        jPanel12.add(idpupukDM, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        jPanel12.add(namapupukDM, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 47;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 4, 0);
        jPanel12.add(jumlahpupukDM, gridBagConstraints);

        hargapupukDM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargapupukDMActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 4, 0);
        jPanel12.add(hargapupukDM, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 4, 0);
        jPanel12.add(namapemasokpupukDM, gridBagConstraints);

        btntambah2.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        btntambah2.setText("+");
        btntambah2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambah2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        jPanel12.add(btntambah2, gridBagConstraints);

        btnkurang2.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        btnkurang2.setText("-");
        btnkurang2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkurang2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 4, 5);
        jPanel12.add(btnkurang2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel12.add(tanggalpupukDM, gridBagConstraints);

        jPanel9.add(jPanel12);

        jPanel11.setBackground(new java.awt.Color(121, 172, 120));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tambah2.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        tambah2.setText("Tambah");
        tambah2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambah2ActionPerformed(evt);
            }
        });
        jPanel11.add(tambah2, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 67, -1, -1));

        update2.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        update2.setText("Update");
        update2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update2ActionPerformed(evt);
            }
        });
        jPanel11.add(update2, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 101, 82, -1));

        delete2.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        delete2.setText("Delete");
        delete2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete2ActionPerformed(evt);
            }
        });
        jPanel11.add(delete2, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 135, 82, -1));

        kembali2.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        kembali2.setText("Kembali");
        kembali2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembali2ActionPerformed(evt);
            }
        });
        jPanel11.add(kembali2, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 169, -1, -1));

        jLabel23.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Downloads\\kucing-removebg-preview_11zon (1).png")); // NOI18N
        jPanel11.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 290, 170));

        jLabel22.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Downloads\\jamur-removebg-preview_11zon.png")); // NOI18N
        jPanel11.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, -1, 60));

        jLabel24.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Downloads\\lampu1-removebg-preview_11zon.png")); // NOI18N
        jPanel11.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 90, 110));

        jPanel9.add(jPanel11);

        jPanel3.add(jPanel9);

        jPanel10.setBackground(new java.awt.Color(121, 172, 120));

        tblpupukDM.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblpupukDM);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel10);

        jTabbedPane1.addTab("Pupuk", jPanel3);

        jPanel4.setBackground(new java.awt.Color(97, 130, 100));
        jPanel4.setLayout(new java.awt.GridLayout(2, 1, 3, 3));

        jPanel13.setBackground(new java.awt.Color(97, 130, 100));
        jPanel13.setLayout(new java.awt.GridLayout(1, 2, 3, 3));

        jPanel16.setBackground(new java.awt.Color(121, 172, 120));
        jPanel16.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("id_Tanaman");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel16.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nama Tanaman");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel16.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Jumlah");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel16.add(jLabel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 4, 0);
        jPanel16.add(idtanamanDM, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 4, 0);
        jPanel16.add(namatanamanDM, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 47;
        gridBagConstraints.insets = new java.awt.Insets(0, 9, 4, 0);
        jPanel16.add(jumlahtanamanDM, gridBagConstraints);

        btnTambah3.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        btnTambah3.setText("+");
        btnTambah3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambah3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 4, 0);
        jPanel16.add(btnTambah3, gridBagConstraints);

        btnKurang3.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        btnKurang3.setText("-");
        btnKurang3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKurang3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);
        jPanel16.add(btnKurang3, gridBagConstraints);

        jLabel17.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Tanggal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel16.add(jLabel17, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel16.add(tanggaltanamanDM, gridBagConstraints);

        jPanel13.add(jPanel16);

        jPanel15.setBackground(new java.awt.Color(121, 172, 120));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tambah3.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        tambah3.setText("Tambah");
        tambah3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambah3ActionPerformed(evt);
            }
        });
        jPanel15.add(tambah3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 83, -1));

        update3.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        update3.setText("Update");
        update3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update3ActionPerformed(evt);
            }
        });
        jPanel15.add(update3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 83, -1));

        hapus3.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        hapus3.setText("Hapus");
        hapus3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapus3ActionPerformed(evt);
            }
        });
        jPanel15.add(hapus3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 83, -1));

        kembali3.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        kembali3.setText("Kembali");
        kembali3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembali3ActionPerformed(evt);
            }
        });
        jPanel15.add(kembali3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, -1, -1));

        jLabel26.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Downloads\\jamur2-removebg-preview_11zon.png")); // NOI18N
        jPanel15.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, -1, 50));

        jLabel27.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Downloads\\jamur3-removebg-preview_11zon.png")); // NOI18N
        jPanel15.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 210, -1, 100));

        jLabel28.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Downloads\\jamur4-removebg-preview_11zon.png")); // NOI18N
        jPanel15.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, -1, 90));

        jLabel29.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Downloads\\jamur3-removebg-preview_11zon.png")); // NOI18N
        jPanel15.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, -1, 100));

        jLabel30.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Downloads\\lampu1-removebg-preview_11zon.png")); // NOI18N
        jPanel15.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 90, 110));

        jPanel13.add(jPanel15);

        jPanel4.add(jPanel13);

        jPanel14.setBackground(new java.awt.Color(121, 172, 120));

        tblTanamanDM.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tblTanamanDM);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel14);

        jTabbedPane1.addTab("Tanaman", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 712, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(289, 289, 289)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDesktopPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 736, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tambah3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambah3ActionPerformed
        // TODO add your handling code here:
        saveDataMasukTanaman();
    }//GEN-LAST:event_tambah3ActionPerformed

    private void tambah1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambah1ActionPerformed
        // TODO add your handling code here:
        saveDataMasukBibit();
    }//GEN-LAST:event_tambah1ActionPerformed

    private void hargapupukDMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargapupukDMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hargapupukDMActionPerformed

    private void idpupukDMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idpupukDMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idpupukDMActionPerformed

    private void update1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update1ActionPerformed
        // TODO add your handling code here:
        updateDataMasukBibit();
    }//GEN-LAST:event_update1ActionPerformed

    private void kembali1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembali1ActionPerformed
        // TODO add your handling code here:
        new Home().setVisible(true);
        dispose();
    }//GEN-LAST:event_kembali1ActionPerformed

    private void tambah2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambah2ActionPerformed
        // TODO add your handling code here:
        saveDataMasukPupuk();
    }//GEN-LAST:event_tambah2ActionPerformed

    private void update2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update2ActionPerformed
        // TODO add your handling code here:
        updateDataMasukPupuk();
    }//GEN-LAST:event_update2ActionPerformed

    private void delete2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete2ActionPerformed
        // TODO add your handling code here:
        deleteDataMasukPupuk();
    }//GEN-LAST:event_delete2ActionPerformed

    private void update3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update3ActionPerformed
        // TODO add your handling code here:
        updateDataMasukTanaman();
    }//GEN-LAST:event_update3ActionPerformed

    private void hapus3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapus3ActionPerformed
        // TODO add your handling code here:
        deleteDataMasukTanaman();
    }//GEN-LAST:event_hapus3ActionPerformed

    private void kembali3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembali3ActionPerformed
        // TODO add your handling code here:
        new Home().setVisible(true);
        dispose();
    }//GEN-LAST:event_kembali3ActionPerformed

    private void kembali2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembali2ActionPerformed
        // TODO add your handling code here:
        new Home().setVisible(true);
        dispose();
    }//GEN-LAST:event_kembali2ActionPerformed

    private void hapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapus1ActionPerformed
        // TODO add your handling code here:
        deleteDataMasukBibit();
    }//GEN-LAST:event_hapus1ActionPerformed

    private void btntambah1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambah1ActionPerformed
        // TODO add your handling code here:
        int jumlah = Integer.parseInt(jumlahbibitDM.getText());
        jumlahbibitDM.setText(String.valueOf(jumlah + 1));
    }//GEN-LAST:event_btntambah1ActionPerformed

    private void jumlahbibitDMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahbibitDMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlahbibitDMActionPerformed

    private void btnkurang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkurang1ActionPerformed
        // TODO add your handling code here:
        int jumlah = Integer.parseInt(jumlahbibitDM.getText());
        if (jumlah > 1){
            jumlahbibitDM.setText(String.valueOf(jumlah - 1));
        }else{
           JOptionPane.showMessageDialog(this, "Jumlah minimal adalah 1", "Peringatan", JOptionPane.WARNING_MESSAGE); 
        }
    }//GEN-LAST:event_btnkurang1ActionPerformed

    private void btntambah2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambah2ActionPerformed
        // TODO add your handling code here:
        int jumlah = Integer.parseInt(jumlahpupukDM.getText());
        jumlahpupukDM.setText(String.valueOf(jumlah + 1));
    }//GEN-LAST:event_btntambah2ActionPerformed

    private void btnTambah3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambah3ActionPerformed
        // TODO add your handling code here:
        int jumlah = Integer.parseInt(jumlahtanamanDM.getText());
        jumlahtanamanDM.setText(String.valueOf(jumlah + 1));
    }//GEN-LAST:event_btnTambah3ActionPerformed

    private void btnKurang3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKurang3ActionPerformed
        // TODO add your handling code here:
        int jumlah = Integer.parseInt(jumlahtanamanDM.getText());
        if (jumlah > 1){
            jumlahtanamanDM.setText(String.valueOf(jumlah - 1));
        }else{
           JOptionPane.showMessageDialog(this, "Jumlah minimal adalah 1", "Peringatan", JOptionPane.WARNING_MESSAGE); 
        }
    }//GEN-LAST:event_btnKurang3ActionPerformed

    private void btnkurang2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkurang2ActionPerformed
        // TODO add your handling code here:
        int jumlah = Integer.parseInt(jumlahpupukDM.getText());
        if (jumlah > 1){
            jumlahpupukDM.setText(String.valueOf(jumlah - 1));
        }else{
           JOptionPane.showMessageDialog(this, "Jumlah minimal adalah 1", "Peringatan", JOptionPane.WARNING_MESSAGE); 
        }
    }//GEN-LAST:event_btnkurang2ActionPerformed

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
            java.util.logging.Logger.getLogger(Data_Masuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Data_Masuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Data_Masuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Data_Masuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Data_Masuk().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKurang3;
    private javax.swing.JButton btnTambah3;
    private javax.swing.JButton btnkurang1;
    private javax.swing.JButton btnkurang2;
    private javax.swing.JButton btntambah1;
    private javax.swing.JButton btntambah2;
    private javax.swing.JButton delete2;
    private javax.swing.JButton hapus1;
    private javax.swing.JButton hapus3;
    private javax.swing.JTextField hargabibitDM;
    private javax.swing.JTextField hargapupukDM;
    private javax.swing.JTextField idbibitDM;
    private javax.swing.JTextField idpupukDM;
    private javax.swing.JTextField idtanamanDM;
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
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
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
    private javax.swing.JTextField jumlahbibitDM;
    private javax.swing.JTextField jumlahpupukDM;
    private javax.swing.JTextField jumlahtanamanDM;
    private javax.swing.JButton kembali1;
    private javax.swing.JButton kembali2;
    private javax.swing.JButton kembali3;
    private javax.swing.JTextField namabibitDM;
    private javax.swing.JTextField namapemasokbibitDM;
    private javax.swing.JTextField namapemasokpupukDM;
    private javax.swing.JTextField namapupukDM;
    private javax.swing.JTextField namatanamanDM;
    private javax.swing.JButton tambah1;
    private javax.swing.JButton tambah2;
    private javax.swing.JButton tambah3;
    private com.toedter.calendar.JDateChooser tanggalbibitDM;
    private com.toedter.calendar.JDateChooser tanggalpupukDM;
    private com.toedter.calendar.JDateChooser tanggaltanamanDM;
    private javax.swing.JTable tblTanamanDM;
    private javax.swing.JTable tblbibitDM;
    private javax.swing.JTable tblpupukDM;
    private javax.swing.JButton update1;
    private javax.swing.JButton update2;
    private javax.swing.JButton update3;
    // End of variables declaration//GEN-END:variables
}
