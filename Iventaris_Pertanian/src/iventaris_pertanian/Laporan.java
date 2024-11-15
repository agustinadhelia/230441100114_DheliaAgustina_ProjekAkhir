/*tbldatamasuklp
//tbllaporankeuangan
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package iventaris_pertanian;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class Laporan extends javax.swing.JFrame {
    
    private DefaultTableModel modelBibitMasuk;
    private DefaultTableModel modelPupukMasuk;
    private DefaultTableModel modelTanamanMasuk;
    
    private DefaultTableModel modelBibitKeluar;
    private DefaultTableModel modelPupukKeluar;
    private DefaultTableModel modelTanamanKeluar;
    
    private DefaultTableModel modelLaporanKeuanganMasuk;
    private DefaultTableModel modelLaporanKeuanganKeluar;
     
    public Laporan() {
        initComponents();
        modelBibitMasuk = new DefaultTableModel();
        tbldatabibitmasuklp.setModel(modelBibitMasuk);

        modelBibitMasuk.addColumn("ID");
        modelBibitMasuk.addColumn("Nama");
        modelBibitMasuk.addColumn("Jumlah");
        modelBibitMasuk.addColumn("Harga"); // Kolom harga akan kosong untuk tabel `dm_tanaman`
        modelBibitMasuk.addColumn("Nama Pemasok");
        modelBibitMasuk.addColumn("Tanggal");  
        
        modelPupukMasuk = new DefaultTableModel();
        tbldatamasukpupuklp.setModel(modelPupukMasuk);

        modelPupukMasuk.addColumn("ID");
        modelPupukMasuk.addColumn("Nama");
        modelPupukMasuk.addColumn("Jumlah");
        modelPupukMasuk.addColumn("Harga"); // Kolom harga akan kosong untuk tabel `dm_tanaman`
        modelPupukMasuk.addColumn("Nama Pemasok");
        modelPupukMasuk.addColumn("Tanggal");
        
        modelTanamanMasuk = new DefaultTableModel();
        tbldatamasuktanamanlp.setModel(modelTanamanMasuk);

        modelTanamanMasuk.addColumn("ID");
        modelTanamanMasuk.addColumn("Nama");
        modelTanamanMasuk.addColumn("Jumlah");
        modelTanamanMasuk.addColumn("Tanggal");
        
        //////////////////////////////////////////////////////////////////
        
        modelBibitKeluar = new DefaultTableModel();
        tbldatabibitkeluarlp.setModel(modelBibitKeluar);
        modelBibitKeluar.addColumn("ID");
        modelBibitKeluar.addColumn("Nama");
        modelBibitKeluar.addColumn("Jumlah");
        modelBibitKeluar.addColumn("Tanggal");
        
        modelPupukKeluar = new DefaultTableModel();
        tbldatapupukkeluarlp.setModel(modelPupukKeluar);
        modelPupukKeluar.addColumn("ID");
        modelPupukKeluar.addColumn("Nama");
        modelPupukKeluar.addColumn("Jumlah");
        modelPupukKeluar.addColumn("Tanggal");
        
        modelTanamanKeluar = new DefaultTableModel();
        tbldatatanamankeluarlp.setModel(modelTanamanKeluar);
        modelTanamanKeluar.addColumn("ID");
        modelTanamanKeluar.addColumn("Nama");
        modelTanamanKeluar.addColumn("Jumlah");
        modelTanamanKeluar.addColumn("Harga");
        modelTanamanKeluar.addColumn("Tanggal");
        
        ////////////////////////////////////////////////////////////////
        
        modelLaporanKeuanganMasuk = new DefaultTableModel();
        tbllaporanmasukkeuangan.setModel(modelLaporanKeuanganMasuk);
        modelLaporanKeuanganMasuk.addColumn("Tanggal");
        modelLaporanKeuanganMasuk.addColumn("Jumlah");
        modelLaporanKeuanganMasuk.addColumn("Harga");
        
        modelLaporanKeuanganKeluar = new DefaultTableModel();
        tbllaporankeluarkeuangan.setModel(modelLaporanKeuanganKeluar);
        modelLaporanKeuanganKeluar.addColumn("Tanggal");
        modelLaporanKeuanganKeluar.addColumn("Jumlah");
        modelLaporanKeuanganKeluar.addColumn("Harga");

        loadDataMasukLaporan();
        loadDataKeluarLaporan();
        loadLaporanKeuanganMasuk();
        loadLaporanKeuanganKeluar();
    }
    
      private void loadDataMasukLaporan() {
        // Membersihkan tabel sebelum memuat data baru
        modelBibitMasuk.setRowCount(0);
        modelPupukMasuk.setRowCount(0);
        modelTanamanMasuk.setRowCount(0);

        Connection conn = Koneksi.getConnection();

        try {
            // Definisikan data untuk setiap tabel
            String[][] dataSources = {
                {"dm_bibit", "nama_bibitdm", "jumlah_bibitdm", "harga_bibitdm", "nama_pemasokbibitdm", "bulan_bibitdm"},
                {"dm_pupuk", "nama_pupukdm", "jumlah_pupukdm", "harga_pupukdm", "nama_pemasokpupukdm", "bulan_pupukdm"},
                {"dm_tanaman", "nama_tanamandm", "jumlah_tanamandm", null, null, "bulan_tanamandm"}
            };

            for (int i = 0; i < dataSources.length; i++) {
                String tableName = dataSources[i][0];
                String columnNama = dataSources[i][1];
                String columnJumlah = dataSources[i][2];
                String columnHarga = dataSources[i][3];
                String columnPemasok = dataSources[i][4];
                String columnBulan = dataSources[i][5];

                // Buat SQL query untuk mengambil data
                String sql;
                if (columnHarga == null) { // Untuk tanaman (tidak ada harga)
                    sql = "SELECT id, " + columnNama + " AS nama, " + columnJumlah + " AS jumlah, "
                            + columnBulan + " AS bulan FROM " + tableName;
                } else {
                    sql = "SELECT id, " + columnNama + " AS nama, " + columnJumlah + " AS jumlah, "
                            + columnHarga + " AS harga, " + columnPemasok + " AS pemasok, " + columnBulan + " AS bulan FROM " + tableName;
                }

                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                // Tentukan model tabel mana yang akan diisi
                DefaultTableModel targetModel = switch (i) {
                    case 0 -> modelBibitMasuk;
                    case 1 -> modelPupukMasuk;
                    case 2 -> modelTanamanMasuk;
                    default -> null;
                };

                // Masukkan data ke model tabel
                while (rs.next()) {
                    if (targetModel != null) {
                        // Jika model adalah `modelTanamanMasuk`, masukkan 4 nilai saja
                        if (targetModel == modelTanamanMasuk) {
                            targetModel.addRow(new Object[]{
                                rs.getInt("id"),
                                rs.getString("nama"),
                                rs.getString("jumlah"),
                                rs.getString("bulan")
                            });
                        } else {
                            // Untuk `modelBibitMasuk` dan `modelPupukMasuk`, tambahkan 6 nilai (termasuk `harga` dan `pemasok`)
                            Object hargaValue = (columnHarga == null) ? "" : rs.getString("harga");
                            Object pemasokValue = (columnPemasok == null) ? "" : rs.getString("pemasok");
                            targetModel.addRow(new Object[]{
                                rs.getInt("id"),
                                rs.getString("nama"),
                                rs.getString("jumlah"),
                                hargaValue,
                                pemasokValue,
                                rs.getString("bulan")
                            });
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error Load Data Laporan: " + e.getMessage());
        }
    }

    private void loadDataKeluarLaporan() {
    // Membersihkan tabel sebelum memuat data baru
    modelBibitKeluar.setRowCount(0);
    modelPupukKeluar.setRowCount(0);
    modelTanamanKeluar.setRowCount(0);

    Connection conn = Koneksi.getConnection();

    try {
        // Definisikan data untuk setiap tabel
        String[][] dataSources = {
            {"dk_bibit", "nama_bibitdk", "jumlah_bibitdk", "bulan_bibitdk"},
            {"dk_pupuk", "nama_pupukdk", "jumlah_pupukdk", "bulan_pupukdk"},
            {"dk_tanaman", "nama_tanamandk", "jumlah_tanamandk", "harga_tanamandk", "bulan_tanamandk"}
        };

        for (int i = 0; i < dataSources.length; i++) {
            String tableName = dataSources[i][0];
            String columnNama = dataSources[i][1];
            String columnJumlah = dataSources[i][2];
            String columnHarga = dataSources[i].length > 3 ? dataSources[i][3] : null; // Memastikan harga ada
            String columnBulan = dataSources[i].length > 4 ? dataSources[i][4] : null; // Memastikan bulan ada

            // Membuat query SQL berdasarkan kolom yang ada
            String sql = "SELECT id, " + columnNama + " AS nama, " + columnJumlah + " AS jumlah, ";
            if (columnHarga != null) {
                sql += columnHarga + " AS harga, ";
            }
            sql += columnBulan + " AS bulan FROM " + tableName;

            // Debug: Menampilkan SQL query yang dijalankan - Hapus atau komentar baris ini
            // System.out.println("Executing SQL: " + sql);

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Tentukan model tabel mana yang akan diisi
            DefaultTableModel targetModel = switch (i) {
                case 0 -> modelBibitKeluar;
                case 1 -> modelPupukKeluar;
                case 2 -> modelTanamanKeluar;
                default -> null;
            };

            // Masukkan data ke model tabel
            while (rs.next()) {
                if (targetModel != null) {
                    // Masukkan hanya kolom nama, jumlah, dan bulan
                    if (columnHarga == null) {
                        targetModel.addRow(new Object[] {
                            rs.getInt("id"),
                            rs.getString("nama"),
                            rs.getString("jumlah"),
                            rs.getString("bulan")
                        });
                    } else {
                        targetModel.addRow(new Object[] {
                            rs.getInt("id"),
                            rs.getString("nama"),
                            rs.getString("jumlah"),
                            rs.getString("harga"), // Menambahkan harga jika ada
                            rs.getString("bulan")
                        });
                    }
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Menampilkan stack trace untuk debugging
    }
}
   
   private void loadLaporanKeuanganMasuk() {
    modelLaporanKeuanganMasuk.setRowCount(0); // Membersihkan tabel
    Connection conn = Koneksi.getConnection();

    try {
        // Hanya mengambil data dari dm_bibit dan dm_pupuk
        String[] tables = {"dm_bibit", "dm_pupuk"};
        String[] bulan = {"bulan_bibitdm", "bulan_pupukdm"};
        String[] jumlah = {"jumlah_bibitdm", "jumlah_pupukdm"};
        String[] harga = {"harga_bibitdm", "harga_pupukdm"}; // Tidak ada tanaman

        for (int i = 0; i < tables.length; i++) {
            String sql = "SELECT " + bulan[i] + " AS tanggal, " + jumlah[i] + " AS jumlah, " + harga[i] + " AS harga FROM " + tables[i];

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                modelLaporanKeuanganMasuk.addRow(new Object[] {
                    rs.getString("tanggal"),
                    rs.getString("jumlah"),
                    rs.getString("harga")  // Pastikan harga ada untuk setiap tabel yang dipilih
                });
            }
        }
    } catch (SQLException e) {
        System.out.println("Error Load Laporan Keuangan: " + e.getMessage());
    }
}

   private void loadLaporanKeuanganKeluar() {
    modelLaporanKeuanganKeluar.setRowCount(0); // Membersihkan tabel
    Connection conn = Koneksi.getConnection();

    try {
        // Hanya mengambil data dari dk_tanaman
        String table = "dk_tanaman";
        String bulan = "bulan_tanamandk";
        String jumlah = "jumlah_tanamandk";
        String harga = "harga_tanamandk"; // Kolom harga ada pada tanaman

        // Query SQL untuk mengambil data hanya dari tanaman
        String sql = "SELECT " + bulan + " AS tanggal, " + jumlah + " AS jumlah, " + harga + " AS harga FROM " + table;

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            modelLaporanKeuanganKeluar.addRow(new Object[] {
                rs.getString("tanggal"),
                rs.getString("jumlah"),
                rs.getString("harga")  // Mengambil harga tanaman
            });
        }
    } catch (SQLException e) {
        System.out.println("Error Load Laporan Keuangan Keluar: " + e.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jDesktopPane2 = new javax.swing.JDesktopPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbldatabibitmasuklp = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbldatamasukpupuklp = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbldatamasuktanamanlp = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jDesktopPane3 = new javax.swing.JDesktopPane();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbldatabibitkeluarlp = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbldatapupukkeluarlp = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbldatatanamankeluarlp = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jDesktopPane4 = new javax.swing.JDesktopPane();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbllaporanmasukkeuangan = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbllaporankeluarkeuangan = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(182, 227, 136));

        jLabel1.setFont(new java.awt.Font("Swis721 Blk BT", 1, 24)); // NOI18N
        jLabel1.setText("Laporan");

        jDesktopPane1.setBackground(new java.awt.Color(182, 227, 136));

        jTabbedPane1.setBackground(new java.awt.Color(225, 255, 177));

        jPanel2.setBackground(new java.awt.Color(199, 242, 164));

        jDesktopPane2.setBackground(new java.awt.Color(199, 242, 164));

        jTabbedPane2.setBackground(new java.awt.Color(136, 194, 115));
        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jPanel6.setBackground(new java.awt.Color(199, 242, 164));

        tbldatabibitmasuklp.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tbldatabibitmasuklp);

        jButton3.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jButton3.setText("Kembali");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Downloads\\apot-removebg-preview_11zon.png")); // NOI18N

        jButton7.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jButton7.setText("Kembali");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3)
                            .addComponent(jButton7))))
                .addGap(23, 23, 23))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addContainerGap())))
        );

        jTabbedPane2.addTab("Bibit", jPanel6);

        jPanel7.setBackground(new java.awt.Color(199, 242, 164));

        tbldatamasukpupuklp.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbldatamasukpupuklp);

        jButton8.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jButton8.setText("Kembali");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton8)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton8)
                .addGap(26, 26, 26))
        );

        jTabbedPane2.addTab("Pupuk", jPanel7);

        jPanel5.setBackground(new java.awt.Color(199, 242, 164));

        tbldatamasuktanamanlp.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(tbldatamasuktanamanlp);

        jButton6.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jButton6.setText("Kembali");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton6)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6)
                .addGap(0, 28, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Tanaman", jPanel5);

        jDesktopPane2.setLayer(jTabbedPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane2)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane2)
        );

        jTabbedPane1.addTab("Data masuk", jPanel2);

        jPanel3.setBackground(new java.awt.Color(199, 242, 164));

        jDesktopPane3.setBackground(new java.awt.Color(199, 242, 164));

        jTabbedPane3.setBackground(new java.awt.Color(136, 194, 115));
        jTabbedPane3.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jPanel8.setBackground(new java.awt.Color(199, 242, 164));

        tbldatabibitkeluarlp.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(tbldatabibitkeluarlp);

        jButton4.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jButton4.setText("Kembali");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Bibit", jPanel8);

        jPanel9.setBackground(new java.awt.Color(199, 242, 164));

        tbldatapupukkeluarlp.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(tbldatapupukkeluarlp);

        jButton2.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jButton2.setText("Kembali");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Pupuk", jPanel9);

        jPanel10.setBackground(new java.awt.Color(199, 242, 164));

        tbldatatanamankeluarlp.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tbldatatanamankeluarlp);

        jButton9.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jButton9.setText("Kembali");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton9)
                .addGap(30, 30, 30))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Tanaman", jPanel10);

        jDesktopPane3.setLayer(jTabbedPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane3Layout = new javax.swing.GroupLayout(jDesktopPane3);
        jDesktopPane3.setLayout(jDesktopPane3Layout);
        jDesktopPane3Layout.setHorizontalGroup(
            jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );
        jDesktopPane3Layout.setVerticalGroup(
            jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane3Layout.createSequentialGroup()
                .addGap(0, 21, Short.MAX_VALUE)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane3)
        );

        jTabbedPane1.addTab("Data keluar", jPanel3);

        jPanel4.setBackground(new java.awt.Color(199, 242, 164));

        jDesktopPane4.setBackground(new java.awt.Color(199, 242, 164));

        jTabbedPane4.setBackground(new java.awt.Color(136, 194, 115));
        jTabbedPane4.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jPanel11.setBackground(new java.awt.Color(199, 242, 164));

        tbllaporanmasukkeuangan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane8.setViewportView(tbllaporanmasukkeuangan);

        jButton5.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jButton5.setText("Kembali");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton5)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addGap(0, 7, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Masuk", jPanel11);

        jPanel12.setBackground(new java.awt.Color(199, 242, 164));

        tbllaporankeluarkeuangan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane9.setViewportView(tbllaporankeluarkeuangan);

        jButton1.setFont(new java.awt.Font("Swis721 Blk BT", 0, 12)); // NOI18N
        jButton1.setText("Kembali");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Keluar", jPanel12);

        jDesktopPane4.setLayer(jTabbedPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane4Layout = new javax.swing.GroupLayout(jDesktopPane4);
        jDesktopPane4.setLayout(jDesktopPane4Layout);
        jDesktopPane4Layout.setHorizontalGroup(
            jDesktopPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4)
        );
        jDesktopPane4Layout.setVerticalGroup(
            jDesktopPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jTabbedPane4)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane4)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane4)
        );

        jTabbedPane1.addTab("Laporan keuangan", jPanel4);

        jDesktopPane1.setLayer(jTabbedPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Data masuk");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(272, 272, 272))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDesktopPane1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        new Home().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new Home().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new Home().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        new Home().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        new Home().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        new Home().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        new Home().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        new Home().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        new Home().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

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
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Laporan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JDesktopPane jDesktopPane3;
    private javax.swing.JDesktopPane jDesktopPane4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
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
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTable tbldatabibitkeluarlp;
    private javax.swing.JTable tbldatabibitmasuklp;
    private javax.swing.JTable tbldatamasukpupuklp;
    private javax.swing.JTable tbldatamasuktanamanlp;
    private javax.swing.JTable tbldatapupukkeluarlp;
    private javax.swing.JTable tbldatatanamankeluarlp;
    private javax.swing.JTable tbllaporankeluarkeuangan;
    private javax.swing.JTable tbllaporanmasukkeuangan;
    // End of variables declaration//GEN-END:variables
}
