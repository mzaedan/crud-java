/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mahasiswa;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;


/**
 *
 * @author Muhamad Zaedan
 */
public class model_mahasiswa {
   String jdbcDriver = "com.mysql.cj.jdbc.Driver";
   String dbUrl = "jdbc:mysql://localhost/mahasiswa_db";
   String user = "root";
   String password = "";
   
   Connection con;
   Statement st;
   ResultSet rs;
   PreparedStatement ps;
   
   boolean respons;
   
   public model_mahasiswa()
   {
       try {
           Class.forName(jdbcDriver);
           System.out.println("driver load.");
       } catch (ClassNotFoundException ex) {
           System.out.println("driver Tidak Ditemukan.");
           Logger.getLogger(model_mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       try {
           con = DriverManager.getConnection(dbUrl, user, password);
           System.out.println("Berhasil Terkonenksi.");
       } catch (SQLException ex) {
           System.out.println("Gagal Terkoneksi Periksa Config Mysql");
           Logger.getLogger(model_mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
   
    
    public boolean insertMhs(String nim, String nama, double uts, double uas)
    {
        String query = "INSERT INTO tbl_mahasiswa" + "(NIM, nama_mahasiswa, n_uts, n_uas)" + "VALUES (?, ?, ?, ?)";
       try {
           ps = con.prepareStatement(query);    
           ps.setString(1, nim);
           ps.setString(2, nama);
           ps.setString(3, Double.toString(uts));
           ps.setString(4, Double.toString(uas));
           ps.executeUpdate();
           respons = true;
           System.out.println("Sukses Insert");
       } catch (SQLException ex) {
           respons = false;
           System.out.println("Gagal Insert");
           Logger.getLogger(model_mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       return respons;
    }
    
    public ResultSet getAllMhs()
    {
       String query = "SELECT * FROM tbl_mahasiswa";
       try {
           st = con.createStatement();
           rs = st.executeQuery(query);
       } catch (SQLException ex) {
           Logger.getLogger(model_mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
       }
       return rs;
    }
    
    public boolean updateMhs(String nim, String nama, double uts, double uas)
    {
        String query = "UPDATE tbl_mahasiswa SET nama_mahasiswa = ?, n_uts = ?, n_uas = ? where NIM = ?";
       try {
           ps = con.prepareStatement(query);    
           ps.setString(1, nama);
           ps.setString(2, Double.toString(uts));
           ps.setString(3, Double.toString(uas));
           ps.setString(4, nim);
           ps.executeUpdate();
           respons = true;
           System.out.println("Sukses Update");
       } catch (SQLException ex) {
           respons = false;
           System.out.println("Gagal Update");
           Logger.getLogger(model_mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       return respons;
    }
    
    public void hapusMhs(String nim)
    {
        String query = "DELETE FROM tbl_mahasiswa WHERE NIM = ?";
       try {
           ps =  con.prepareStatement(query);
           ps.setString(1, nim);
           ps.executeUpdate();
       } catch (SQLException ex) {
           Logger.getLogger(model_mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
 }


        
