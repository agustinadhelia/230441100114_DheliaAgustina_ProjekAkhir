/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iventaris_pertanian;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author LENOVO
 */
public class Koneksi {
    private static final String URL = "jdbc:mysql://localhost:3306/inventaris_pertanian";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() {//Connection pengganti void. Connection tipe data
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("terkoneksi");
        } catch (SQLException e) {
            System.out.println("error bang : " + e.getMessage());
        }
        return conn;
    }
    
    public static void main(String[] args) {// untuk memanggil koneksi(fungsion)
        getConnection();
    }
}
