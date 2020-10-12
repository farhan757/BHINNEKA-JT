/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fungsi;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author farhan
 */
public class MySqlConnection {
    static Connection konek = null;
    
    public MySqlConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Berhasil Regist Driver");
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return;
        }
        
        try {
            konek = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/cms","root","");
            if(konek != null){
                System.out.println("Berhasil Konek");
            }else{
                System.out.println("gagal konek");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySqlConnection.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
    }
    
    public static String InsertId(String query) throws SQLException{
        Statement st = (Statement) konek.createStatement();
        st.execute(query);
        ResultSet rs = st.getGeneratedKeys();
        String Id = "";
        if(rs.next()){
            Id = rs.getString(1);
        }
        konek.close();
        return Id;        
    }
    
    public static void Insert(String query) throws SQLException{
        Statement st = (Statement) konek.createStatement();
        st.execute(query);
        konek.close();
    }
    
    public static String SelectFirts(String query, String kolom) throws SQLException{
        Statement st = (Statement) konek.createStatement();
        ResultSet rs = st.executeQuery(query);
        String result = "";
        if(rs.next()){
            result = rs.getString(kolom);
        }
        konek.close();
        return result;
    }
}
